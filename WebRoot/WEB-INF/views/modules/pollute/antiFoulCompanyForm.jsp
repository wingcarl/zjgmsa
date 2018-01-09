<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>防污染配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pollute/antiFoulCompany/">防污染配置列表</a></li>
		<li class="active"><a href="${ctx}/pollute/antiFoulCompany/form?id=${antiFoulCompany.id}">防污染配置<shiro:hasPermission name="pollute:antiFoulCompany:edit">${not empty antiFoulCompany.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pollute:antiFoulCompany:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="antiFoulCompany" action="${ctx}/pollute/antiFoulCompany/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<div class="control-group">
			<label class="control-label">企业名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">防污染类别：</label>
			<div class="controls">
				<form:select path="foulType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pollutant_work_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">防污染作业船舶：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>船名</th>
								<shiro:hasPermission name="pollute:antiFoulCompany:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="antiFoulShipList">
						</tbody>
						<shiro:hasPermission name="pollute:antiFoulCompany:edit"><tfoot>
							<tr><td colspan="3"><a href="javascript:" onclick="addRow('#antiFoulShipList', antiFoulShipRowIdx, antiFoulShipTpl);antiFoulShipRowIdx = antiFoulShipRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="antiFoulShipTpl">//<!--
						<tr id="antiFoulShipList{{idx}}">
							<td class="hide">
								<input id="antiFoulShipList{{idx}}_id" name="antiFoulShipList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="antiFoulShipList{{idx}}_delFlag" name="antiFoulShipList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="antiFoulShipList{{idx}}_shipName" name="antiFoulShipList[{{idx}}].shipName" type="text" value="{{row.shipName}}" maxlength="128" class="input-small "/>
							</td>
							<shiro:hasPermission name="pollute:antiFoulCompany:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#antiFoulShipList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var antiFoulShipRowIdx = 0, antiFoulShipTpl = $("#antiFoulShipTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(antiFoulCompany.antiFoulShipList)};
							for (var i=0; i<data.length; i++){
								addRow('#antiFoulShipList', antiFoulShipRowIdx, antiFoulShipTpl, data[i]);
								antiFoulShipRowIdx = antiFoulShipRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="pollute:antiFoulCompany:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>