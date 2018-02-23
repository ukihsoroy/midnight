/**
 * 此文件初版由工具生成，请定制开发
 * 生成时间: ${now}
 * 
 */
$(function(){
	var pageName = getPageName();
	$("#_content").attr('page', pageName);

<#if textColumnCount != 0>
	if(${Table?uncap_first}AddEditor){
		${Table?uncap_first}AddEditor.destroy();
	}
    ${Table?uncap_first}AddEditor = UE.getEditor('${Table?uncap_first}AddEditor', {
    	serverUrl: ueditorServletUrl,
    	autoHeightEnabled: false,
    	enableAutoSave: false,
    	saveInterval: 1000 * 60 * 60 * 24,
    	toolbars: ueditorToolbars
    });

	if(${Table?uncap_first}EditEditor){
		${Table?uncap_first}EditEditor.destroy();
	}
    ${Table?uncap_first}EditEditor = UE.getEditor('${Table?uncap_first}EditEditor', {
    	serverUrl: ueditorServletUrl,
    	autoHeightEnabled: false,
    	enableAutoSave: false,
    	saveInterval: 1000 * 60 * 60 * 24,
    	toolbars: ueditorToolbars
    });

	// 重置按钮
	$("#${Table?uncap_first}AddForm button[type=reset]").click(function(){
		${Table?uncap_first}AddEditor.setContent('');
	});
</#if>
	
	// 查询条件日期控件
	$("#${Table?uncap_first}QueryForm input[ctl-type=date]").datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-CN',
	});
	
	// 新增页日期控件
	$("#${Table?uncap_first}AddForm input[ctl-type=date]").datetimepicker({
		format: 'YYYY-MM-DD',
		locale: 'zh-CN',
	});
	// 新增页时间日期控件
	$("#${Table?uncap_first}AddForm input[ctl-type=datetime]").datetimepicker({
		format: 'YYYY-MM-DD HH:mm:ss',
		locale: 'zh-CN',
		icons: {
			time: 'fa fa-clock-o',
			date: 'fa fa-calendar',
			up: 'fa fa-chevron-up',
			down: 'fa fa-chevron-down',
			previous: 'fa fa-chevron-left',
			next: 'fa fa-chevron-right',
			today: 'fa fa-arrows',
			clear: 'fa fa-trash',
			close: 'fa fa-times'
		}
	});
	// 整数输入
	$("#${Table?uncap_first}AddForm input[ctl-type=number]").blur(function(){
		var value = $(this).val().replace(/[^\d]/g, '');
		$(this).val(value);
	});
	// 小数输入
	$("#${Table?uncap_first}AddForm input[ctl-type=decimal]").blur(function(){
		var value = $(this).val().replace(/[^\d\.]/g, '');
		var dotIndex = value.indexOf('.');
		if(dotIndex){
			var other = value.substr(dotIndex + 1);
			other = other.replace(/\./g, '');
			value = value.substr(0, dotIndex + 1) + other;
		}
		$(this).val(value);
	});


	// 点击导航，显示当前菜单
	$("#_menuLink").click(function(){
		window.location.href = '#' + pageName;
	});
	
	// 取消按钮
	$($('#${Table?uncap_first}AddForm button[type=button]')[1]).click(function(){
		$('#${Table?uncap_first}AddForm button[type=reset]').click();
		window.location.href = '#' + pageName;
	});
	
	// 查询按钮
	$('#${Table?uncap_first}QueryForm button[type="button"]').click(function(){
		doQuery();
	});
	
	// 新增记录提交
	$('#${Table?uncap_first}AddButton').click(function(){
<#list meta as m>
	<#if m.columnName != "id" && m.columnName != "delete_status" && m.columnName != "tx_code" 
		&& m.columnName != "create_user_id" && m.columnName != "create_by" && m.columnName != "create_date" 
		&& m.columnName != "update_user_id" && m.columnName != "update_by" && m.columnName != "update_date">
		// ${m.commentHeader}
		<#if m.dataType?contains("text")>
		var ${Table?uncap_first}${m.fieldName?cap_first}Node = $("#${Table?uncap_first}AddEditor");
		if(${Table?uncap_first}${m.fieldName?cap_first}Node && ${Table?uncap_first}${m.fieldName?cap_first}Node.length && !$.trim(${Table?uncap_first}AddEditor.getContent())){
			alertError('请输入内容');
			return false;
		}
		$("#${Table?uncap_first}AddForm textarea[name=${m.columnName}]").val(${Table?uncap_first}AddEditor.getContent());
		<#else>
		var ${Table?uncap_first}${m.fieldName?cap_first}Node = $("#${Table?uncap_first}AddForm input[name=${m.columnName}]");
			<#if m.dataType?contains("int")>
		if(${Table?uncap_first}${m.fieldName?cap_first}Node && ${Table?uncap_first}${m.fieldName?cap_first}Node.length && (!$.trim(${Table?uncap_first}${m.fieldName?cap_first}Node.val()) || !validator.isNumeric($.trim(${Table?uncap_first}${m.fieldName?cap_first}Node.val())))){
			${Table?uncap_first}${m.fieldName?cap_first}Node.attr('title', '格式错误').tooltip('fixTitle');
			<#elseif m.dataType?contains("float") || m.dataType?contains("double") || m.dataType?contains("decimal")>
		if(${Table?uncap_first}${m.fieldName?cap_first}Node && ${Table?uncap_first}${m.fieldName?cap_first}Node.length && (!$.trim(${Table?uncap_first}${m.fieldName?cap_first}Node.val()) || !validator.isDecimal($.trim(${Table?uncap_first}${m.fieldName?cap_first}Node.val())))){
			${Table?uncap_first}${m.fieldName?cap_first}Node.attr('title', '格式错误').tooltip('fixTitle');
			<#else>
		if(${Table?uncap_first}${m.fieldName?cap_first}Node && ${Table?uncap_first}${m.fieldName?cap_first}Node.length && !$.trim(${Table?uncap_first}${m.fieldName?cap_first}Node.val())){
			</#if>
			showToolTip(${Table?uncap_first}${m.fieldName?cap_first}Node);
			return false;
		}
		</#if>
	</#if>
</#list>
		
		$.loading.show({tips:'正在保存...'});
		
		apiClient({
			url: '${Table?uncap_first}/add',
			method: 'post',
			data: $("#${Table?uncap_first}AddForm").serialize(),
			success: function(data){
				if(data.code == 0){
					$.loading.hide();
					$("#${Table?uncap_first}QueryForm button[type=reset]").click();
					<#if textColumnCount != 0>
					${Table?uncap_first}AddEditor.setContent('');
					</#if>
					window.location.href = '#' + pageName;
					$('#${Table?uncap_first}AddForm button[type=reset]').click();
					doQuery();
				}else{
					$.loading.hide();
					alertError(data.message);
				}
			},
			error: function(){
				$.loading.hide();
			}
		})
	});
	
	// 导入文件
	$("#${Table?uncap_first}ImportExcel").click(function(){
		$("#${Table?uncap_first}ExcelFile").val('');
		$("#${Table?uncap_first}ExcelFile").click();
	});
	// 同步导入
	$("#${Table?uncap_first}UploadForm").ajaxForm({
		url: _baseUrl + '${Table?uncap_first}/import',
//		uploadProgress: function(event, position, total, percentComplete){
//			$.loading.hide();
//			$.loading.show({tips:'正在上传' + percentComplete + '% ...'});
//			if(percentComplete >= 100){
//				setTimeout(function(){
//					$.loading.hide();
//					$.loading.show({tips:'正在导入...'});
//				}, 500);
//			}
//		},
		success: function(data, textStatus, jqXHR, form){
			if(data.code == 0){
				$.loading.hide();
				doQuery();
			}else{
				$.loading.hide();
				alertError(data.message);
			}
		},
		error: function(){
			$.loading.hide();
		}
	});
	$("#${Table?uncap_first}ExcelFile").change(function(){
//		$.loading.show({tips:'正在上传 0% ...'});
		$("#${Table?uncap_first}UploadForm").submit();
	});

/*
	// 异步导入
	$("#${Table?uncap_first}UploadForm").ajaxForm({
		url: _baseUrl + '${Table?uncap_first}/import',
//		uploadProgress: function(event, position, total, percentComplete){
//			$.loading.hide();
//			$.loading.show({tips:'正在上传' + percentComplete + '% ...'});
//			if(percentComplete >= 100){
//				$.loading.hide();
//			}
//		},
		success: function(data, textStatus, jqXHR, form){
			if(data.code == 0){
				taskHintModel.taskName = '文件导入';
				$("#${Table?uncap_first}Alert").show();
			}else{
				alertError(data.message);
			}
		}
	});
	$("#${Table?uncap_first}ExcelFile").change(function(){
		$("#${Table?uncap_first}Alert").hide();
		$('#${Table?uncap_first}UploadForm input[name="async"]').val(1);
//		$.loading.show({tips:'正在上传 0% ...'});
		$("#${Table?uncap_first}UploadForm").submit();
	});
*/

	var taskHintModel = new Vue({
		el: '#${Table?uncap_first}Alert',
		data: {
			taskName: '文件导出',
			taskMenu: '/index.html#task'
		}
	});

	// 查询结果model
	${Table?uncap_first}ListModel = new Vue({
		el: '#${Table?uncap_first}TableBlock',
		data: {
			grid: {items:[]},
			pageName: pageName
		},
		methods: {
<#list meta as m>
	<#if m.dataType?contains("text")>
			format${m.fieldName?cap_first}: function(value){
				var max = 100;
				var text = value ? value : '';
				return text.length > max ? text.substr(0, max) + '...': text;
			},
	</#if>
	<#if (m.commentElements?size>0) && m.columnName != "delete_status">
			format${m.fieldName?cap_first}: function(value){
				var text = value;
		<#list m.commentElements as e>
			<#if e_index == 0>
				if(value == ${e.value}){
			<#else>
				}else if(value == ${e.value}){
			</#if>
					text = '${e.text}';
			<#if e_index == m.commentElements?size - 1>
				}
			</#if>
		</#list>
				return text;
			},
	</#if>
</#list>
			formatDate: function(value){
				return moment(value).format('YYYY-MM-DD');
			},
			checkRow: function(e){
				var pk = $(e.target).parent().attr('pk');
				var node = $('input[type=checkbox][pk=' + pk + ']');
				node.prop('checked', !node.prop('checked'));
				
				if($('input[type=checkbox][pk]').length == $('input[type=checkbox][pk]:checked').length){
					$("#${Table?uncap_first}CheckAll").prop('checked', true);
				}else{
					$("#${Table?uncap_first}CheckAll").prop('checked', false);
				}
			},
			checkAll: function(e){
				$('input[type=checkbox][pk]').prop('checked', $(e.target).prop('checked'));
			},
			goEdit: function(e){
				window.location.href = "#" + pageName + "?action=edit&${pk}=" + $(e.target).attr('pk');
			},
			deleteRow: function(e){
				doDelete([$(e.target).attr('pk')]);
			},
			deleteRows: function(e){
				var ids = [];
				$('input[type=checkbox][pk]:checked').each(function(){
					ids.push($(this).attr('pk'));
				});
				
				doDelete(ids, '是否要删除选中行？');
			},
			turnPage: function(e){
				// 页码翻页
				var page = $(e.target).text();
				if(this.grid.currentPage != page){
					doQuery(page);
				}
			},
			refreshPage: function(e){
				// 行数刷新
				doQuery(this.grid.currentPage);
			},
			firstPage: function(e){
				// 第一页
				if(this.grid.currentPage > 1){
					doQuery(1);
				}
			},
			previousPage: function(e){
				// 上一页
				if(this.grid.currentPage > 1){
					doQuery(this.grid.currentPage - 1);
				}
			},
			nextPage: function(e){
				// 下一页
				if(this.grid.currentPage < this.grid.totalPage){
					doQuery(this.grid.currentPage + 1);
				}
			},
			lastPage: function(e){
				// 最后一页
				if(this.grid.currentPage < this.grid.totalPage){
					doQuery(this.grid.totalPage);
				}
			},
			jumpTo: function(e){
				// 跳转至
				var jumpNode = $('#${Table?uncap_first}JumpNo');
				var regexp = new RegExp(/^\d+$/);
				if(!regexp.test(jumpNode.val())){
					jumpNode.attr('title', '页码格式错误');
					showToolTip(jumpNode);
					return;
				}
				
				if(jumpNode.val() < 1 || jumpNode.val() > this.grid.totalPage){
					jumpNode.attr('title', '无效的页码');
					showToolTip(jumpNode);
					return;
				}
				
				doQuery(jumpNode.val());
			},
			exportQuery: function(e){
				// 同步导出
				$.loading.show({tips:'正在导出...'});
				apiClient({
					url: '${Table?uncap_first}/export/query',
					success: function(data){
						if(data.code == 0){
							window.location.href = data.url;
						}else{
							alertError(data.message);
						}
					},
					complete: function(){
						$.loading.hide();
					}
				});
/*
				// 异步导出
				$("#${Table?uncap_first}Alert").hide();
				apiClient({
					url: '${Table?uncap_first}/export/query',
					data:{async:1},
					success: function(data){
						if(data.code == 0){
							taskHintModel.taskName = '文件导出';
							$("#${Table?uncap_first}Alert").show();
						}else{
							alertError(data.message);
						}
					}
				});
*/
			},
			exportAll: function(e){
				bootbox.confirm('是否要导出全部' + this.grid.totalRows + '条数据？', function(result){
					if(result){
						// 同步导出
						$.loading.show({tips:'正在导出...'});
						apiClient({
							url: '${Table?uncap_first}/export/all',
							success: function(data){
								if(data.code == 0){
									window.location.href = data.url;
								}else{
									alertError(data.message);
								}
							},
							complete: function(){
								$.loading.hide();
							}
						});
/*
						// 异步导出
						$("#${Table?uncap_first}Alert").hide();
						apiClient({
							url: '${Table?uncap_first}/export/all',
							data:{async:1},
							success: function(data){
								if(data.code == 0){
									taskHintModel.taskName = '文件导出';
									$("#${Table?uncap_first}Alert").show();
								}else{
									alertError(data.message);
								}
							}
						});
*/
					}
				});	
			}
		},
		mounted: function(){
			// 列表查询
			doQuery();
		}
	});
	
	// 编辑回显model
	${Table?uncap_first}ItemModel = new Vue({
		el: '#${Table?uncap_first}EditForm',
		data: {
			model: {}
		},
		methods: {
<#if textColumnCount != 0>
			clearForm: function(e){
				${Table?uncap_first}EditEditor.setContent('');
			},
</#if>
			doCancel: function(e){
				window.location.href = '#' + pageName;
				$("#${Table?uncap_first}EditForm button[type=reset]").click();
			},
			doSubmit: function(e){
<#list meta as m>
	<#if m.columnName != "id" && m.columnName != "delete_status" && m.columnName != "tx_code" 
		&& m.columnName != "create_user_id" && m.columnName != "create_by" && m.columnName != "create_date" 
		&& m.columnName != "update_user_id" && m.columnName != "update_by" && m.columnName != "update_date">
				// ${m.commentHeader}
		<#if textColumnCount != 0>
				var ${Table?uncap_first}${m.fieldName?cap_first}Node = $("#${Table?uncap_first}EditEditor");
				if(${Table?uncap_first}${m.fieldName?cap_first}Node && ${Table?uncap_first}${m.fieldName?cap_first}Node.length && !$.trim(${Table?uncap_first}EditEditor.getContent())){
					alertError('请输入内容');
					return false;
				}
				$("#${Table?uncap_first}EditForm textarea[name=${m.columnName}]").val(${Table?uncap_first}EditEditor.getContent());
		<#else>
				var ${Table?uncap_first}${m.fieldName?cap_first}Node = $("#${Table?uncap_first}EditForm input[name=${m.columnName}]");
			<#if m.dataType?contains("int")>
				if(${Table?uncap_first}${m.fieldName?cap_first}Node && ${Table?uncap_first}${m.fieldName?cap_first}Node.length && (!$.trim(${Table?uncap_first}${m.fieldName?cap_first}Node.val()) || !validator.isNumeric($.trim(${Table?uncap_first}${m.fieldName?cap_first}Node.val())))){
					${Table?uncap_first}${m.fieldName?cap_first}Node.attr('title', '格式错误').tooltip('fixTitle');
			<#elseif m.dataType?contains("float") || m.dataType?contains("double") || m.dataType?contains("decimal")>
				if(${Table?uncap_first}${m.fieldName?cap_first}Node && ${Table?uncap_first}${m.fieldName?cap_first}Node.length && (!$.trim(${Table?uncap_first}${m.fieldName?cap_first}Node.val()) || !validator.isDecimal($.trim(${Table?uncap_first}${m.fieldName?cap_first}Node.val())))){
					${Table?uncap_first}${m.fieldName?cap_first}Node.attr('title', '格式错误').tooltip('fixTitle');
			<#else>
				if(${Table?uncap_first}${m.fieldName?cap_first}Node && ${Table?uncap_first}${m.fieldName?cap_first}Node.length && !$.trim(${Table?uncap_first}${m.fieldName?cap_first}Node.val())){
			</#if>
					showToolTip(${Table?uncap_first}${m.fieldName?cap_first}Node);
					return false;
				}
		</#if>
	</#if>
</#list>
				$.loading.show({tips:'正在保存...'});
				
				apiClient({
					url: '${Table?uncap_first}/edit',
					method: 'post',
					data: $("#${Table?uncap_first}EditForm").serialize(),
					success: function(data){
						if(data.code == 0){
							$.loading.hide();
							window.location.href = '#' + pageName;
							<#if textColumnCount != 0>
							${Table?uncap_first}EditEditor.setContent('');
							</#if>
							$('#${Table?uncap_first}EditForm button[type=reset]').click();
							doQuery();
						}else{
							$.loading.hide();
							alertError(data.message);
						}
					},
					error: function(){
						$.loading.hide();
					}
				})
			}
		},
		mounted: function(){
			// 编辑页日期控件
			$("#${Table?uncap_first}EditForm input[ctl-type=date]").datetimepicker({
				format: 'YYYY-MM-DD',
				locale: 'zh-CN'
			});
			// 编辑页时间日期控件
			$("#${Table?uncap_first}EditForm input[ctl-type=datetime]").datetimepicker({
				format: 'YYYY-MM-DD HH:mm:ss',
				locale: 'zh-CN',
				icons: {
					time: 'fa fa-clock-o',
					date: 'fa fa-calendar',
					up: 'fa fa-chevron-up',
					down: 'fa fa-chevron-down',
					previous: 'fa fa-chevron-left',
					next: 'fa fa-chevron-right',
					today: 'fa fa-arrows',
					clear: 'fa fa-trash',
					close: 'fa fa-times'
				}
			});
			// 整数输入
			$("#${Table?uncap_first}EditForm input[ctl-type=number]").blur(function(){
				var value = $(this).val().replace(/[^\d]/g, '');
				$(this).val(value);
			});
			// 小数输入
			$("#${Table?uncap_first}EditForm input[ctl-type=decimal]").blur(function(){
				var value = $(this).val().replace(/[^\d\.]/g, '');
				var dotIndex = value.indexOf('.');
				if(dotIndex){
					var other = value.substr(dotIndex + 1);
					other = other.replace(/\./g, '');
					value = value.substr(0, dotIndex + 1) + other;
				}
				$(this).val(value);
			});
		}
	});

})

var ${Table?uncap_first}ListModel, ${Table?uncap_first}ItemModel;
<#if textColumnCount != 0>
var ${Table?uncap_first}AddEditor, ${Table?uncap_first}EditEditor;
</#if>

function goAdd(){
	var pageName = getPageName();
	window.location.href = '#' + pageName + '?action=add';
}

/**
 * 查询
 * @param {Integer} page	页码
 */
function doQuery(page){
	var action = Utils.parseUrlParam(window.location.href, 'action');
	if(!action){
		$.loading.show({tips:'正在查询...'});
	}
	$("#${Table?uncap_first}QueryForm input[name=page]").val(page ? page : 1);
	
	var rows = $("#${Table?uncap_first}RefreshRows").val();
	$("#${Table?uncap_first}QueryForm input[name='rows']").val(rows ? rows : 10);
	
	apiClient({
		url: '${Table?uncap_first}/search',
		data: $("#${Table?uncap_first}QueryForm").serialize(),
		success: function(data){
			if(data.code == 0){
				${Table?uncap_first}ListModel.grid = data;
				$("#${Table?uncap_first}TableBlock").show();
			}else{
				alertError(data.message);
			}
		},
		complete: function(){
			$('input[type=checkbox][pk]').prop('checked', false);
			if(!action){
				$.loading.hide();
			}
		}
	})
}

/**
 * 删除记录
 * @param {Array} ids	记录主键id
 */
function doDelete(ids, message){
	var message = message ? message : '是要否删除当前行？';
	if(ids && ids.length){
		bootbox.confirm(message, function(result){
			if(result){
				$.loading.show({tips:'正在删除...'});
				apiClient({
					url: '${Table?uncap_first}/delete?id=' + ids.join(','),
					type: 'delete',
					success: function(data){
						if(data.code == 0){
							$.loading.hide();
							doQuery();
						}else{
							alertError(data.message);
							$.loading.hide();
						}
					}
				});
			}
		});	
	}
}

/**
 * 加载明细数据
 */
function load${Table}Item(){
	var id = Utils.parseUrlParam(window.location.href, 'id');
	if(id){
		apiClient({
			url: '${Table?uncap_first}/edit',
			data: {id:id},
			success: function(data){
				if(data.code == 0 && data.model){
					${Table?uncap_first}ItemModel.model = data.model;
					<#if textColumnCount != 0>
					try{
						${Table?uncap_first}EditEditor.setContent(data.model.${textColumnName} ? data.model.${textColumnName} : '');
					}catch(e){
						${Table?uncap_first}EditEditor.addListener('ready', function(){
							${Table?uncap_first}EditEditor.setContent(data.model.${textColumnName} ? data.model.${textColumnName} : '');
							${Table?uncap_first}EditEditor.removeListener('ready');
						});
					}
					</#if>
				}else{
					alertError(data.message);
				}
			},
			complete: function(){
				$.loading.hide();
			}
		})
	}else{
		$.loading.hide();
	}
}

/**
 * 路由地址回调
 * {String} page 页面名称	
 */
function route${Table}CallBack(page){
	var pageName = getPageName();
	if(page != pageName){
		// 跳至主页
		$('#_content').empty();
		return;
	}
	
	var action = Utils.parseUrlParam(window.location.href, 'action');
	if(action){
		// 根据用户动作显示相应画面
		$('div[action]').hide();
		$('div[action="' + action + '"]').show();
		
		if(action == 'add'){
			
		}else if(action == 'edit'){
			$.loading.show({tips:'正在加载...'});
			load${Table}Item();
		}
	}else{
		if($("#_content").attr('page') != pageName){
			$('#_content').load(pageName + '.html');
		}
		$('div[action]').hide();
		$('div[action="query"]').show();
	}
}
