<!--
	此文件初版由工具生成，请定制开发
	生成时间: ${now}
-->
<link rel="stylesheet" href="css/page/${Table?uncap_first}.css" />

<div id="${Table?uncap_first}Alert" class="row" style="display: none;">
	<div class="alert alert-success">
		<a href="#" class="close" data-dismiss="alert">&times;</a>
		{{taskName}}任务已切换到后台运行，请到&nbsp;<a v-bind:href="taskMenu">计划任务</a>&nbsp;菜单中查看
	</div>
</div>

<div id="${Table?uncap_first}QueryBlock" action="query">
	<!-- 查询条件 开始 -->
	<div class="row">
		<div class="widget-box">
			<div class="widget-header">
				<strong class="widget-title">查询</strong>
				<div class="widget-toolbar">
					<a href="#" data-action="collapse">
						<i class="ace-icon fa fa-chevron-up"></i>
					</a>
				</div>
			</div>
			<div class="widget-body">
				<div class="widget-main">
					<form id="${Table?uncap_first}QueryForm" class="form-inline">
						<input type="hidden" name="page">
						<input type="hidden" name="rows" value="1">
<#if textElement??>
						<div class="form-group">
							<label>${textElement.text}</label>
							<div class="input-group input-group-sm">
								<input name="${textElement.columnName}" maxlength="${textElement.length}" type="text" ctl-type="text" class="form-control">
							</div>
						</div>
						&nbsp;&nbsp;
</#if>
<#if dateElement??>
						<div class="form-group">
							<label>${dateElement.text}&nbsp;从</label>
							<div class="input-group input-group-sm">
								<input name="${dateElement.columnName}_from" type="text" ctl-type="date" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<label>至</label>
							<div class="input-group input-group-sm">
								<input name="${dateElement.columnName}_to" type="text" ctl-type="date" class="form-control"/>
							</div>
						</div>
						&nbsp;&nbsp;
</#if>
<#if selectElement??>
						<div class="form-group">
							<label>${selectElement.text}</label>
							<div class="input-group input-group-sm">
								<select name="${selectElement.columnName}" class="form-control" ctl-type="select">
									<option value="">全部</option>
	<#if selectElement.options??>
		<#list selectElement.options as option>
									<option value="${option.value}">${option.text}</option>
		</#list>
	</#if>
								</select>
							</div>
						</div>
						&nbsp;&nbsp;
</#if>
						<div class="form-group">
							<button id="${Table?uncap_first}QueryButton" type="button" class="btn btn-xs btn-primary">
								<i class="ace-icon fa fa-search bigger-110"></i>
								查询
							</button>
						</div>
						<div class="form-group">
							<button type="reset" class="btn btn-xs btn-purple">
								<i class="ace-icon fa fa-undo bigger-110"></i>
								重置
							</button>
						</div>
						<div class="form-group">
							<div class="col-xs-1">
								<a href="javascript:void(0)" class="ace-icon glyphicon glyphicon-plus blue" title="添加" onclick="goAdd()"></a>
							</div>
							<!-- 导入按钮 开始-->
							<!--
							<div class="col-xs-1">
								<a href="javascript:void(0)" id="${Table?uncap_first}ImportExcel" class="ace-icon fa fa-upload green" title="导入"></a>
							</div>
							<div class="col-xs-1">
								<a href="/resources/templates/${Table}.xlsx" class="ace-icon fa fa-file-excel-o green" title="下载导入模板"></a>
							</div>
							-->
							<!-- 导入按钮 结束-->
						</div>
				  	</form>
					<form id="${Table?uncap_first}UploadForm" style="display: none;" method="post" enctype="multipart/form-data">
						<input id="${Table?uncap_first}ExcelFile" type="file" name="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
						<input name="async" value="0" type="hidden">
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 查询条件 结束 -->
	
	<div id="${Table?uncap_first}TableBlock" class="row" style="display: none;">
		<!-- 表格 开始 -->
		<div class="table-responsive">
			<table id="${Table?uncap_first}Table" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">
							<label class="pos-rel">
								<input type="checkbox" id="${Table?uncap_first}CheckAll" class="ace" v-on:click="checkAll"/>
								<span class="lbl"></span>
							</label>
						</th>
						<th style="text-align: center;">序号</th>
<#list meta as m>
	<#if m.columnName != "id" && m.columnName != "delete_status" && m.columnName != "tx_code">
						<th>${m.commentHeader}</th>
	</#if>
</#list>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<tr v-on:click="checkRow" v-bind:pk="item.id" v-for="(item, index) in grid.items">
						<td class="center">
							<label class="pos-rel">
								<input type="checkbox" class="ace" v-bind:pk="item.id"/>
								<span class="lbl"></span>
							</label>
						</td>
						<td style="text-align: center;">{{index+1}}</td>
<#list meta as m>
	<#if m.columnName != "id" && m.columnName != "delete_status" && m.columnName != "tx_code">
		<#if m.comment?contains("#") || m.dataType?contains("text")>
						<td>${r'{'}${r'{'}format${m.fieldName?cap_first}(item.${m.fieldName})${r'}'}${r'}'}</td>
		<#elseif m.dataType?lower_case == "date">
						<td>${r'{'}${r'{'}formatDate(item.${m.fieldName})${r'}'}${r'}'}</td>
		<#else>
						<td>${r'{'}${r'{'}item.${m.fieldName}${r'}'}${r'}'}</td>
		</#if>
	</#if>
</#list>					
						<td>
							<!-- 大屏行编辑 开始 -->
							<div class="hidden-sm hidden-xs btn-group">
								<button class="btn btn-xs btn-info" data-rel="tooltip" title="编辑" v-on:click="goEdit" v-bind:pk="item.${pk}">
									<i class="ace-icon fa fa-pencil bigger-120" v-on:click.stop="goEdit" v-bind:pk="item.${pk}"></i>
								</button>
								<button class="btn btn-xs btn-danger" data-rel="tooltip" title="删除" v-on:click="deleteRow" v-bind:pk="item.${pk}">
									<i class="ace-icon fa fa-trash-o bigger-120" v-on:click.stop="deleteRow" v-bind:pk="item.${pk}"></i>
								</button>
							</div>
							<!-- 大屏行编辑 结束 -->
							
							<!-- 小屏行编辑 开始 -->
							<div class="hidden-md hidden-lg">
								<div class="inline pos-rel">
									<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
										<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
									</button>
									<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
										<li>
											<a href="#" class="tooltip-success" data-rel="tooltip" title="编辑" v-bind:href="'#' + pageName + '?action=edit&${pk}=' + item.${pk}">
												<span class="green">
													<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
												</span>
											</a>
										</li>
										<li>
											<a href="javascript: void(0)" class="tooltip-error" data-rel="tooltip" title="删除" v-on:click="deleteRow" v-bind:pk="item.${pk}">
												<span class="red">
													<i class="ace-icon fa fa-trash-o bigger-120"></i>
												</span>
											</a>
										</li>
									</ul>
								</div>
							</div>
							<!-- 小屏行编辑 结束 -->
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<!-- 表格 结束 -->
		
		<!-- 页号 开始 -->
		<div class="row" v-if="grid.items && grid.items.length">
			<!-- 底部编辑按钮 开始 -->
			<div class="col-xs-2 pull-left">
				<div>
					<!--
					<div class="col-xs-1">
						<a href="javascript:void(0)" class="ace-icon fa fa-download red" title="全部导出" v-on:click="exportAll"></a>
					</div>
					<div class="col-xs-1">
						<a id="${Table?uncap_first}ExportQuery" href="javascript:void(0)" class="ace-icon fa fa-download" title="按查询条件导出" v-on:click="exportQuery"></a>
					</div>
					-->
					<div class="col-xs-1">
						<a href="javascript: void(0)" class="ace-icon glyphicon glyphicon-trash grey" title="删除选中" v-on:click="deleteRows"></a>
					</div>
				</div>
			</div>
			<!-- 底部编辑按钮 结束-->
			
			<!-- 页码区域 开始-->
			<div class="btn-group pull-right" style="padding-right: 10px;">
				<button class="btn btn-grey" data-rel="tooltip" title="首页" v-bind:class="{'disabled':grid.currentPage==1}" v-on:click="firstPage">|&lt;</button>
				<button class="btn btn-grey" data-rel="tooltip" title="下一页" v-bind:class="{'disabled':grid.currentPage==1}" v-on:click="previousPage">&lt;&lt;</button>
				<template v-for="page in grid.pages">
					<button page class="btn" v-bind:class="{'btn-inverse':page==grid.currentPage,'btn-grey':page!=grid.currentPage}" v-on:click="turnPage">{{page}}</button>
				</template>
				<button class="btn btn-grey" data-rel="tooltip" title="下一页" v-bind:class="{'disabled':grid.currentPage==grid.totalPage}" v-on:click="nextPage">&gt;&gt;</button>
				<button class="btn btn-grey" data-rel="tooltip" title="尾页" v-bind:class="{'disabled':grid.currentPage==grid.totalPage}" v-on:click="lastPage">&gt;|</button>
				&nbsp;
				<select id="${Table?uncap_first}RefreshRows" style="height:35px" v-on:change="refreshPage">
					<option value="10">10</option>
					<option value="20">20</option>
					<option value="30">30</option>
					<option value="40">40</option>
					<option value="50">50</option>
					<option value="60">60</option>
					<option value="70">70</option>
					<option value="80">80</option>
					<option value="90">90</option>
					<option value="100">100</option>
				</select>
				&nbsp;
				<input id="${Table?uncap_first}JumpNo" type="number" min="1" data-rel="tooltip" data-placement="left" class="tooltip-warning" style="width: 50px;" v-on:keydown.enter="jumpTo">&nbsp;
				<a href="#" onclick="return false;" v-on:click="jumpTo">跳转</a>
				&nbsp;&nbsp;第<strong>{{grid.currentPage}}</strong>页 共<strong>{{grid.totalPage}}</strong>页
			</div>
		</div>
		<!-- 页码区域 结束-->
		<!-- 页号 结束-->
	</div>
</div>

<!-- 添加区块 开始 -->
<div id="${Table?uncap_first}AddBlock" action="add" class="row" style="display: none;">
	<div class="col-xs-12">
		<form id="${Table?uncap_first}AddForm" class="form-horizontal" role="form">
<#list meta as m>
	<#if m.columnName != "id" && m.columnName != "delete_status" && m.columnName != "tx_code" 
		&& m.columnName != "create_user_id" && m.columnName != "create_by" && m.columnName != "create_date" 
		&& m.columnName != "update_user_id" && m.columnName != "update_by" && m.columnName != "update_date">
		<#if m.dataType?contains("text")>
			<div class="form-group">
				<label class="col-sm-1 control-label no-padding-right">${m.commentHeader}</label>
				<div class="col-sm-8">
					<textarea id="${Table?uncap_first}AddEditor" class="tooltip-warning" style="height: 400px;"></textarea>
					<textarea name="${m.columnName}" style="display: none;"></textarea>
				</div>
			</div>
		<#else>
			<#if (m.commentElements?size>0)>
				<div class="form-group">
					<label class="col-sm-<#if textColumnCount != 0>1<#else>3</#if> control-label no-padding-right">${m.commentHeader}</label>
					<div class="col-sm-<#if textColumnCount != 0>11<#else>9</#if>">
						<select name="${m.columnName}">
				<#list m.commentElements as e>
							<option value="${e.value}">${e.text}</option>
				</#list>
						</select>
					</div>
				</div>
			<#else>
				<div class="form-group">
					<label class="col-sm-<#if textColumnCount != 0>1<#else>3</#if> control-label no-padding-right">${m.commentHeader}</label>
					<div class="col-sm-<#if textColumnCount != 0>11<#else>9</#if>">
				<#if m.dataType?contains("int")>
						<input type="text" name="${m.columnName}" maxlength="${m.length}" class="tooltip-warning" ctl-type="number" placeholder="请输入${m.commentHeader}" title="请输入${m.commentHeader}" data-rel="tooltip" data-placement="right"/>
				<#elseif m.dataType?contains("float") || m.dataType?contains("double") || m.dataType?contains("decimal")>
						<input type="text" name="${m.columnName}" maxlength="${m.length}" class="tooltip-warning" ctl-type="decimal" placeholder="请输入${m.commentHeader}" title="请输入${m.commentHeader}" data-rel="tooltip" data-placement="right"/>
				<#elseif m.dataType == "date">
						<input type="text" name="${m.columnName}" class="tooltip-warning" ctl-type="date" placeholder="请输入${m.commentHeader}" title="请输入${m.commentHeader}" data-rel="tooltip" data-placement="right"/>
				<#elseif m.dataType?contains("time")>
						<input type="text" name="${m.columnName}" class="tooltip-warning" ctl-type="datetime" placeholder="请输入${m.commentHeader}" title="请输入${m.commentHeader}" data-rel="tooltip" data-placement="right"/>
				<#else>
						<input type="text" name="${m.columnName}" maxlength="${m.length}" class="tooltip-warning" placeholder="请输入${m.commentHeader}" title="请输入${m.commentHeader}" data-rel="tooltip" data-placement="right"/>
				</#if>	
					</div>
				</div>
			</#if>
		</#if>
	</#if>
</#list>
			<div class="col-md-offset-3 col-md-9">
				<button id="${Table?uncap_first}AddButton" class="btn btn-sm btn-primary" type="button">
					<i class="ace-icon fa fa-floppy-o bigger-110"></i>
					提交
				</button>
				&nbsp; &nbsp; &nbsp;
				<button class="btn btn-sm btn-purple" type="reset">
					<i class="ace-icon fa fa-undo bigger-110"></i>
					重置
				</button>
				&nbsp; &nbsp; &nbsp;
				<button class="btn btn-sm btn-grey" type="button">
					<i class="ace-icon fa fa-times bigger-110"></i>
					取消
				</button>
			</div>
		</form>
	</div>
</div>
<!-- 添加区块 结束 -->

<!-- 编辑区块 开始 -->
<div id="${Table?uncap_first}EditBlock" action="edit" class="row" style="display: none;">
	<div class="col-xs-12">
		<form id="${Table?uncap_first}EditForm" class="form-horizontal" role="form">
			<input type="hidden" name="${pk}" v-bind:value="model.${pk}">
<#list meta as m>
	<#if m.columnName != "id" && m.columnName != "delete_status" && m.columnName != "tx_code" 
		&& m.columnName != "create_user_id" && m.columnName != "create_by" && m.columnName != "create_date" 
		&& m.columnName != "update_user_id" && m.columnName != "update_by" && m.columnName != "update_date">
		<#if m.dataType?contains("text")>
			<div class="form-group">
				<label class="col-sm-1 control-label no-padding-right">${m.commentHeader}</label>
				<div class="col-sm-8">
					<textarea id="${Table?uncap_first}EditEditor" class="tooltip-warning" style="height: 400px;"></textarea>
					<textarea name="${m.columnName}" style="display: none;"></textarea>
				</div>
			</div>
		<#else>
			<#if (m.commentElements?size>0)>
				<div class="form-group">
					<label class="col-sm-<#if textColumnCount != 0>1<#else>3</#if> control-label no-padding-right">${m.commentHeader}</label>
					<div class="col-sm-<#if textColumnCount != 0>11<#else>9</#if>">
						<select name="${m.columnName}" v-model="model.${m.fieldName}">
				<#list m.commentElements as e>
							<option value="${e.value}">${e.text}</option>
				</#list>
						</select>
					</div>
				</div>
			<#else>
				<div class="form-group">
					<label class="col-sm-<#if textColumnCount != 0>1<#else>3</#if> control-label no-padding-right">${m.commentHeader}</label>
					<div class="col-sm-<#if textColumnCount != 0>11<#else>9</#if>">
				<#if m.dataType?contains("int")>
						<input type="text" name="${m.columnName}" maxlength="${m.length}" class="tooltip-warning" ctl-type="number" placeholder="请输入${m.commentHeader}" title="请输入${m.commentHeader}" data-rel="tooltip" data-placement="right"/>
				<#elseif m.dataType?contains("float") || m.dataType?contains("double") || m.dataType?contains("decimal")>
						<input type="text" name="${m.columnName}" maxlength="${m.length}" class="tooltip-warning" ctl-type="decimal" placeholder="请输入${m.commentHeader}" title="请输入${m.commentHeader}" data-rel="tooltip" data-placement="right"/>
				<#elseif m.dataType == "date">
						<input type="text" name="${m.columnName}" class="tooltip-warning" ctl-type="date" placeholder="请输入${m.commentHeader}" title="请输入${m.commentHeader}" data-rel="tooltip" data-placement="right"/>
				<#elseif m.dataType?contains("time")>
						<input type="text" name="${m.columnName}" class="tooltip-warning" ctl-type="datetime" placeholder="请输入${m.commentHeader}" title="请输入${m.commentHeader}" data-rel="tooltip" data-placement="right"/>
				<#else>
						<input type="text" name="${m.columnName}" maxlength="${m.length}" class="tooltip-warning" placeholder="请输入${m.commentHeader}" title="请输入${m.commentHeader}" data-rel="tooltip" data-placement="right"/>
				</#if>	
					</div>
				</div>
			</#if>
		</#if>
	</#if>
</#list>		
			<div class="col-md-offset-3 col-md-9">
				<button class="btn btn-sm btn-primary" type="button" v-on:click="doSubmit">
					<i class="ace-icon fa fa-floppy-o bigger-110"></i>
					保存
				</button>
				&nbsp; &nbsp; &nbsp;
				<button class="btn btn-sm btn-purple" type="reset"<#if textColumnCount != 0> v-on:click="clearForm"</#if>>
					<i class="ace-icon fa fa-undo bigger-110"></i>
					重置
				</button>
				&nbsp; &nbsp; &nbsp;
				<button class="btn btn-sm btn-grey" type="button" v-on:click="doCancel">
					<i class="ace-icon fa fa-times bigger-110"></i>
					取消
				</button>
			</div>
		</form>
	</div>
</div>
<!-- 编辑区块 结束 -->

<script type="text/javascript" src="js/page/${Table?uncap_first}.js" ></script>
