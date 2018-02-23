package org.ko.${appName}.${componentName}.service.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.ko.framework.extension.utils.DateTimeUtils;
import org.ko.framework.extension.utils.NumberUtils;
import org.ko.${appName}.admin.bean.domain.${Table}Grid;
import org.ko.${appName}.admin.dao.repository.${Table}Repository;
import org.ko.${appName}.admin.task.TaskMonitor;
import org.ko.${appName}.data.bean.domain.${Table};
import org.ko.${appName}.data.constants.domain.${Table}Constants;
import org.ko.${appName}.data.constants.domain.TaskConstants;
import org.ko.${appName}.support.bean.DiskFile;
import org.ko.${appName}.support.bean.view.BeanView;
import org.ko.${appName}.support.bean.view.FileView;
import org.ko.${appName}.support.service.impl.FilePathBuilder;
import org.ko.${appName}.support.utils.ExcelUtils;
import org.ko.${appName}.support.utils.Utils;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: ${now}
 * 
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class ${Table}ExcelService {

	private static final Logger log = LoggerFactory.getLogger(${Table}ExcelService.class);
	
	private static final short DOWNLOAD_UPDATE_INTERVAL = 100;
	private static final short UPLOAD_UPDATE_INTERVAL = 10;
	
	@Autowired private FilePathBuilder filePathBuilder;
	@Autowired private ${Table}Repository ${Table?uncap_first}Repository;

	public FileView exportExcel(Map<String, Object> sqlParams, TaskMonitor monitor) throws Exception {
		FileView view = new FileView();
		
		List<${Table}Grid> records = ${Table?uncap_first}Repository.find("selectGrid", sqlParams, ${Table}Grid.class);
		
		if(CollectionUtils.isNotEmpty(records)){
			// 标题
			List<String> heads = Arrays.asList(new String[]{
				"序号"
<#list titles as title>
				, "${title}"
</#list>
			});
			
			// 数据行
			List<List<String>> rows = new ArrayList<>();
			for(int i = 0; i < records.size(); i++){
				${Table}Grid record = records.get(i);
				List<String> list = new ArrayList<>();
				
				list.add(String.valueOf(i + 1));
<#list meta as m>
	<#if !m.primaryKey && m.columnName?lower_case != "delete_status" && m.columnName?lower_case != "tx_code">
		<#if m.dataType?lower_case == "datetime" || m.dataType?lower_case == "timestamp">
				list.add(ExcelUtils.formatDateTime(record.get${m.fieldName?cap_first}()));
		<#elseif m.dataType?lower_case == "date">
				list.add(ExcelUtils.formatDate(record.get${m.fieldName?cap_first}()));
		<#else>
			<#if m.comment?contains("#")>
				list.add(ExcelUtils.getStringValue(${Table}Constants.Values.${m.fieldName?cap_first}.convert(record.get${m.fieldName?cap_first}())));
			<#else>
				list.add(ExcelUtils.getStringValue(record.get${m.fieldName?cap_first}()));
			</#if>
		</#if>
	</#if>
</#list>				
				
				rows.add(list);
				
				if(i % DOWNLOAD_UPDATE_INTERVAL == 0){
					monitor.setPercent(NumberUtils.convertToShort((i + 1) * TaskMonitor.HUNDRED / records.size()));
				}
				if(monitor.isStopped()){
					break;
				}
			}
			
			if(!monitor.isStopped()){
				String traceCode = Utils.getTraceCode().getThreadCode();
				if(StringUtils.isBlank(traceCode)){
					traceCode = UUID.randomUUID().toString().split("-")[0];
				}
				DiskFile excel = filePathBuilder.buildArchivePath(DateFormatUtils.format(new Date(), "yyyyMMdd_HHmmss_") + traceCode + ".xlsx");
				File dir = new File(FilenameUtils.getFullPath(excel.getLocalPath()));
				if(!dir.exists()){
					dir.mkdirs();
				}
				ExcelUtils.export(heads, rows, excel.getLocalPath());
				
				view.setUrl(excel.getHttpPath());
				
				monitor.setUrlPath(excel.getHttpPath());
				monitor.setFinishStatus(TaskConstants.Values.FinishStatus.Success);
			}else{
				monitor.setFinishStatus(TaskConstants.Values.FinishStatus.Abort);
			}
		}
		
		return view;		
	}

	public BeanView<List<String>> importExcel(DiskFile df, TaskMonitor monitor) throws Exception {
		BeanView<List<String>> view = new BeanView<>();
		
		Workbook wb = new XSSFWorkbook(new FileInputStream(df.getLocalPath()));
		Sheet sheet = wb.getSheetAt(0);
		Iterator<Row> rows = sheet.rowIterator();
		
		List<List<String>> lines = new ArrayList<>();
		int rowIndex = 0;
		while(rows.hasNext()){
			Row row = rows.next();
			
			if(rowIndex++ == 0){
				// 跳过标题行
				continue;
			}
			
			// 读取所有行
			int column = 0;
			String value = ExcelUtils.getCellValue(row.getCell(column++));
			if(StringUtils.isBlank(value)){
				// 第一列没有值，认为数据读取完毕
				break;
			}
			
			// 从第二列开始读取
			List<String> line = new ArrayList<>();
			
			for(String columnName : ${Table}Constants.Columns.COLUMNS){
				if(!${Table}Constants.Columns.ID.equals(columnName.toLowerCase()) && !${Table}Constants.Columns.AUDIT_COLUMNS.contains(columnName.toLowerCase())){
					line.add(ExcelUtils.getCellValue(row.getCell(column++)));
				}
			}
			
			lines.add(line);
		}
		
		wb.close();

		do{
			if(CollectionUtils.isEmpty(lines)){
				break;
			}
			
			// 校验单元格数据格式
			List<String> violations = validateFormats(lines);
			if(CollectionUtils.isNotEmpty(violations)){
				view.setModel(violations);
				break;
			}
			
			// 更新数据库
			for(int i = 0; i < lines.size(); i++){
				List<String> line = lines.get(i);
				
				int index = 0;
				Map<String, Object> params = new HashMap<>();
				
				${Table} record = checkExistence(line);
				if(record != null){
					params.put(${Table}Constants.Columns.ID, record.getId());
				}
				
<#list meta as m>
	<#if !m.primaryKey && m.columnName?lower_case != "delete_status" && m.columnName?lower_case != "tx_code"
		 && m.columnName?lower_case != "create_user_id" && m.columnName?lower_case != "create_by" && m.columnName?lower_case != "create_date"
		 && m.columnName?lower_case != "update_user_id" && m.columnName?lower_case != "update_by" && m.columnName?lower_case != "update_date">
		<#if m.dataType?lower_case == "datetime" || m.dataType?lower_case == "timestamp">
				params.put(${Table}Constants.Columns.${m.columnName?upper_case}, DateTimeUtils.convertToDate(line.get(index++), "yyyy-MM-dd HH:mm:ss"));
		<#elseif m.dataType?lower_case == "date">
				params.put(${Table}Constants.Columns.${m.columnName?upper_case}, DateTimeUtils.convertToDate(line.get(index++), "yyyy-MM-dd"));
		<#else>
			<#if m.comment?contains("#")>
				params.put(${Table}Constants.Columns.${m.columnName?upper_case}, ${Table}Constants.Values.${m.fieldName?cap_first}.convert(line.get(index++)));
			<#else>
				params.put(${Table}Constants.Columns.${m.columnName?upper_case}, line.get(index++));
			</#if>
		</#if>
	</#if>
</#list>				

				if(record != null){
					// 更新
					${Table?uncap_first}Repository.updateById(params);
				}else{
					// 插入
					${Table?uncap_first}Repository.insert(params);
				}
				
				if(i % UPLOAD_UPDATE_INTERVAL == 0){
					monitor.setPercent(NumberUtils.convertToShort((i + 1) * TaskMonitor.HUNDRED / lines.size()));
				}
				if(monitor.isStopped()){
					break;
				}
			}
		}while(false);
		
		if(!monitor.isStopped()){
			monitor.setUrlPath(df.getHttpPath());
			monitor.setFinishStatus(TaskConstants.Values.FinishStatus.Success);
		}else{
			monitor.setFinishStatus(TaskConstants.Values.FinishStatus.Abort);
		}
		
		return view;
	}
	
	/**
	 * 校验excel行内容是否有格式错误
	 * @param lines
	 * @return	错误消息
	 */
	private List<String> validateFormats(List<List<String>> lines){
		return null;
	}
	
	/**
	 * 检查数据库中是否有重复记录
	 * @param line
	 * @return	重复的记录
	 */
	private ${Table} checkExistence(List<String> line){
		return null;
	}
}
