<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>水水中转货物核销登记薄管理</title>
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
		<li><a href="${ctx}/affair/affairTransferInfo/">水水中转货物核销登记薄列表</a></li>
		<li class="active"><a href="${ctx}/affair/affairTransferInfo/form?id=${affairTransferInfo.id}">水水中转货物核销登记薄<shiro:hasPermission name="affair:affairTransferInfo:edit">${not empty affairTransferInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="affair:affairTransferInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="affairTransferInfo" action="${ctx}/affair/affairTransferInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<div class="control-group">
			<label class="control-label">一程船船名：</label>
			<div class="controls">
				<form:input path="firstShipName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">一程船始发港：</label>
			<div class="controls">
				<form:input path="firstShipDeparture" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">航次号：</label>
			<div class="controls">
				<form:input path="firstMoveNum" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">货物：</label>
			<div class="controls">
				<form:input path="firstCargo" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">卸载码头：</label>
			<div class="controls">
				<form:input path="firstUnloadPort" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内外贸：</label>
			<div class="controls">
				<form:radiobuttons path="firstInOut" items="${fns:getDictList('in_out')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">一程船实际载货量：</label>
			<div class="controls">
				<form:input path="firstLoad" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">序号：</label>
			<div class="controls">
				<form:input path="number" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请单位：</label>
			<div class="controls">
				<form:input path="applyOffice" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人：</label>
			<div class="controls">
				<form:input path="contactor" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input path="telephone" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
				<label class="control-label">归属部门：</label>
				<div class="controls">
	                <sys:treeselect id="office" name="office.id" value="${affairTransferInfo.office.id}" labelName="office.name" labelValue="${affairTransferInfo.office.name}"
						title="部门" url="/sys/office/treeData?type=2" cssClass="required" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">水水中转货物二程船信息表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>收货人</th>
								<th>目的港</th>
								<th>预报数量</th>
								<th>票号</th>
								<shiro:hasPermission name="affair:affairTransferInfo:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="affairTransferSecondList">
						</tbody>
						<shiro:hasPermission name="affair:affairTransferInfo:edit"><tfoot>
							<tr><td colspan="8"><a href="javascript:" onclick="addRow('#affairTransferSecondList', affairTransferSecondRowIdx, affairTransferSecondTpl);affairTransferSecondRowIdx = affairTransferSecondRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="affairTransferSecondTpl">//<!--
						<tr id="affairTransferSecondList{{idx}}">
							<td class="hide">
								<input id="affairTransferSecondList{{idx}}_id" name="affairTransferSecondList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="affairTransferSecondList{{idx}}_delFlag" name="affairTransferSecondList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="affairTransferSecondList{{idx}}_receiver" name="affairTransferSecondList[{{idx}}].receiver" type="text" value="{{row.receiver}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="affairTransferSecondList{{idx}}_destination" name="affairTransferSecondList[{{idx}}].destination" type="text" value="{{row.destination}}" maxlength="64" class="input-small "/>
							</td>
					
							<td>
								<input id="affairTransferSecondList{{idx}}_number" name="affairTransferSecondList[{{idx}}].number" type="text" value="{{row.number}}" maxlength="64" class="input-small "/>
							</td>
						
							<td>
								<input id="affairTransferSecondList{{idx}}_leafNum" name="affairTransferSecondList[{{idx}}].leafNum" type="text" value="{{row.leafNum}}" maxlength="64" class="input-small "/>
							</td>
							<shiro:hasPermission name="affair:affairTransferInfo:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#affairTransferSecondList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var affairTransferSecondRowIdx = 0, affairTransferSecondTpl = $("#affairTransferSecondTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(affairTransferInfo.affairTransferSecondList)};
							for (var i=0; i<data.length; i++){
								addRow('#affairTransferSecondList', affairTransferSecondRowIdx, affairTransferSecondTpl, data[i]);
								affairTransferSecondRowIdx = affairTransferSecondRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="affair:affairTransferInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>