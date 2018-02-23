package org.ko.${appName}.${componentName}.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.ko.framework.bean.logging.annotation.LogIgnore;
import org.ko.framework.extension.utils.DataUtils;
import org.ko.framework.extension.utils.NumberUtils;
import org.ko.framework.extension.utils.data.Constructor;
import org.ko.prototype.admin.bean.domain.${Table}Grid;
import org.ko.prototype.admin.bean.domain.${Table}Model;
import org.ko.prototype.admin.service.${Table}Service;
import org.ko.prototype.support.bean.view.BeanView;
import org.ko.prototype.support.bean.view.PageView;
import org.ko.prototype.support.bean.view.View;
import org.ko.prototype.support.builder.RequestParamBuilder;
import org.ko.prototype.support.constants.ApiConstraintConstants.Id;

/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: ${now}
 * 
 */
@Api(name="${Table}维护",description="${Table}Controller")
@RestController
@RequestMapping("${Table?uncap_first}")
@Validated
public class ${Table}Controller {

	private static final Logger log = LoggerFactory.getLogger(${Table}Controller.class);
	
	@Autowired private ${Table}Service ${Table?uncap_first}Service;
	
	@ApiResponseObject
	@ApiMethod(summary="查询")
	@RequestMapping(value="/search",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public PageView<List<${Table}Grid>> search(
		@ApiQueryParam(description="当前页号") @RequestParam(value="page",required=false,defaultValue="1") String page,
		@ApiQueryParam(description="每页行数") @RequestParam(value="rows",required=false,defaultValue="10") String rows,
		@LogIgnore HttpServletRequest request
	) throws Exception {
		int p = NumberUtils.convertToInt(page);
		int r = NumberUtils.convertToInt(rows);
		// 将请求参数数组变成单个元素，多个值用逗号分隔
		Map<String, String> params = RequestParamBuilder.buildPlainParams(request.getParameterMap());
		return ${Table?uncap_first}Service.search(params, p == 0 ? 1 : p, r == 0 ? 1 : r);
	}
	
	@ApiResponseObject
	@ApiMethod(summary="添加")
	@RequestMapping(value="/add",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public View add(@LogIgnore HttpServletRequest request) throws Exception {
		Map<String, String> params = RequestParamBuilder.buildPlainParams(request.getParameterMap());
		return ${Table?uncap_first}Service.add(params);
	}

	@ApiResponseObject
	@ApiMethod(summary="加载编辑")
	@RequestMapping(value="/edit",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public BeanView<${Table}Model> load(@ApiQueryParam(description="id") @RequestParam("id") @Pattern(regexp=Id.Pattern,message="id格式错误") String id) throws Exception {
		return ${Table?uncap_first}Service.load(NumberUtils.convertToInt(id));
	}
	
	@ApiResponseObject
	@ApiMethod(summary="编辑")
	@RequestMapping(value="/edit",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public View edit(@LogIgnore HttpServletRequest request) throws Exception {
		Map<String, String> params = RequestParamBuilder.buildPlainParams(request.getParameterMap());
		return ${Table?uncap_first}Service.edit(params);
	}
	
	@ApiResponseObject
	@ApiMethod(summary="删除")
	@RequestMapping(value="/delete",method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public View delete(@ApiQueryParam(description="id",format="逗号分隔主键id") @RequestParam("id") @NotBlank(message="id不能为空") String id) throws Exception {
		return ${Table?uncap_first}Service.delete(DataUtils.splitTrimDistinct(id, ",", Constructor.Integer));
	}
	
	@ApiResponseObject
	@ApiMethod(summary="按查询条件导出")
	@RequestMapping(value="/export/query",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public View exportQuery(@ApiQueryParam(description="async",format="0-同步导出;1-异步导出") @RequestParam(value="async",defaultValue="0",required=false) String async) throws Exception {
		return ${Table?uncap_first}Service.exportQuery(NumberUtils.convertToInt(async) == 1);
	}
	
	@ApiResponseObject
	@ApiMethod(summary="导出全部数据")
	@RequestMapping(value="/export/all",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public View exportAll(@ApiQueryParam(description="async",format="0-同步导出;1-异步导出") @RequestParam(value="async",defaultValue="0",required=false) String async) throws Exception {
		return ${Table?uncap_first}Service.exportAll(NumberUtils.convertToInt(async) == 1);
	}
	
	@ApiResponseObject
	@ApiMethod(summary="导入")
	@RequestMapping(value="/import",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public View importExcel(
		@ApiBodyObject @RequestParam("file") MultipartFile file,
		@ApiQueryParam(description="async",format="0-同步导出;1-异步导出") @RequestParam(value="async",defaultValue="0",required=false) String async
	) throws Exception {
		return ${Table?uncap_first}Service.importExcel(file, NumberUtils.convertToInt(async) == 1);
	}
}
