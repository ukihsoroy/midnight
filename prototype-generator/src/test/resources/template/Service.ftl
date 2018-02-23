package org.ko.${appName}.${componentName}.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import org.ko.framework.data.adaptor.bean.Page;
import org.ko.framework.data.adaptor.bean.PageRecord;
import org.ko.framework.extension.utils.NumberUtils;
import org.ko.framework.support.session.SessionProvider;
import org.ko.${appName}.${componentName}.bean.domain.${Table}Grid;
import org.ko.${appName}.${componentName}.bean.domain.${Table}Model;
import org.ko.${appName}.${componentName}.dao.repository.${Table}Repository;
import org.ko.${appName}.${componentName}.service.file.${Table}ExcelService;
import org.ko.${appName}.admin.task.BlindTaskMonitor;
import org.ko.${appName}.admin.task.ITaskProcessor;
import org.ko.${appName}.admin.task.TaskDaemon;
import org.ko.${appName}.admin.task.TaskMonitor;
import org.ko.${appName}.data.bean.domain.${Table};
import org.ko.${appName}.data.bean.domain.${Table}Example;
import org.ko.${appName}.data.constants.domain.${Table}Constants;
import org.ko.${appName}.support.bean.DiskFile;
import org.ko.${appName}.support.bean.view.BeanView;
import org.ko.${appName}.support.bean.view.FileView;
import org.ko.${appName}.support.bean.view.PageView;
import org.ko.${appName}.support.bean.view.View;
import org.ko.${appName}.support.builder.SqlParamBuilder;
import org.ko.${appName}.support.builder.ViewBuilder;
import org.ko.${appName}.support.constants.AppConstants;
import org.ko.prototype.support.service.impl.FileService;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: ${now}
 * 
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class ${Table}Service {

	private static final Logger log = LoggerFactory.getLogger(${Table}Service.class);
	
	private static final String QUERY_NAME = "${Table?uncap_first}Query";
	
	@Autowired private ${Table}Repository ${Table?uncap_first}Repository;
	@Autowired private ${Table}ExcelService ${Table?uncap_first}ExcelService;
	@Autowired private FileService fileService;
	
	public PageView<List<${Table}Grid>> search(Map<String, String> params, int page, int rows) throws Exception {
		Page p = new Page(page, rows);
		
		/**
		 * buildSelectParams - 去除请求参数空值，对范围查询增加_from和_to后缀
		 * filter - 过滤不在本表中的键值
		 */
		Map<String, Object> sqlParams = filter(SqlParamBuilder.buildSelectParams(params));
		
		SessionProvider.setValue(AppConstants.Session.PreviousQueryCriteria.get(QUERY_NAME), sqlParams);	// 记录最后一次查询条件
		
		PageRecord<${Table}Grid> record = ${Table?uncap_first}Repository.find("selectGrid", sqlParams, p, ${Table}Grid.class);
		
		return ViewBuilder.buildPageView(record);
	}
	
	public View add(Map<String, String> params) throws Exception {
		View view = new View();
		
		// TODO 重复校验
				
		Map<String, Object> sqlParams = filter(SqlParamBuilder.buildInsertParams(params));
		${Table?uncap_first}Repository.insert("insert", sqlParams);
		
		return view;
	}
	
	public BeanView<${Table}Model> load(int id) throws Exception {
		BeanView<${Table}Model> view = new BeanView<>();
		
		${Table}Model item = ${Table?uncap_first}Repository.findById(id, ${Table}Model.class);
		view.setModel(item);
		
		return view;
	}
	
	public View edit(Map<String, String> params) throws Exception {
		View view = new View();
		
		// TODO 重复校验
		int id = NumberUtils.convertToInt(params.get(${Table}Constants.Columns.ID));
		
		Map<String, Object> sqlParams = filter(SqlParamBuilder.buildUpdateParams(params));
		${Table?uncap_first}Repository.updateById(sqlParams);
		
		return view;
	}
	
	public View delete(List<Integer> ids){
		View view = new View();
		
<#if deleteStatus == "delete_status">		
		for(Integer id : ids){
			${Table} record = new ${Table}();
			record.setId(id);
			record.setDeleteStatus(${Table}Constants.Values.DeleteStatus.Deleted);
			${Table?uncap_first}Repository.updateById(record);
		}
<#else>
		${Table}Example e = new ${Table}Example();
		e.createCriteria().andIdIn(ids);
		${Table?uncap_first}Repository.delete(e);
</#if>

		return view;
	}
	
	public FileView exportQuery(boolean async) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, Object> sqlParams = SessionProvider.getValue(AppConstants.Session.PreviousQueryCriteria.get(QUERY_NAME), Map.class);
		return exportExcel(sqlParams, async, "导出当前查询${Table}");
	}
	
	public FileView exportAll(boolean async) throws Exception {
		return exportExcel(new HashMap<>(), async, "导出全部${Table}");
	}

	public View importExcel(MultipartFile file, boolean async) throws Exception {
		View view = new View();
		
		DiskFile df = fileService.writeUserFile(file);
		
		if(async){
			// 异步导入
			new TaskDaemon("导入${Table}", new ITaskProcessor(){
				@Override
				public void process(TaskMonitor monitor) throws Exception {
					${Table?uncap_first}ExcelService.importExcel(df, monitor);
				}
			}).start();
			
		}else{
			// 同步导入
			view = ${Table?uncap_first}ExcelService.importExcel(df, new BlindTaskMonitor());
		}
		
		return view;
	}

	private FileView exportExcel(Map<String, Object> sqlParams, boolean async, String taskName) throws Exception {
		FileView view = new FileView();
		
		if(async){
			// 异步导出
			new TaskDaemon(taskName, new ITaskProcessor(){
				@Override
				public void process(TaskMonitor monitor) throws Exception {
					${Table?uncap_first}ExcelService.exportExcel(sqlParams, monitor);
				}
			}).start();
			
			view.setTaskName(taskName);
			
		}else{
			// 同步导出
			view = ${Table?uncap_first}ExcelService.exportExcel(sqlParams, new BlindTaskMonitor());
		}
		
		return view;		
	}
	
	private Map<String, Object> filter(Map<String, Object> params){
		Map<String, Object> map = new HashMap<>();
		
		Set<Entry<String, Object>> entries = params.entrySet();
		for(Entry<String, Object> entry : entries){
			if(${Table}Constants.Columns.contains(entry.getKey()) || entry.getKey().endsWith(SqlParamBuilder.FROM_SUFFIX)|| entry.getKey().endsWith(SqlParamBuilder.TO_SUFFIX)){
				map.put(entry.getKey(), entry.getValue());
			}
		}
		
		return map;
	}
}
