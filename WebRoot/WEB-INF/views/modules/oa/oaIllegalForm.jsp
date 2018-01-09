<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>违法行为管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var parentParam;
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/oaIllegal/">违法行为列表</a></li>
		<li class="active"><a href="${ctx}/oa/oaIllegal/form?id=${oaIllegal.id}">违法行为<shiro:hasPermission name="oa:oaIllegal:edit">${not empty oaIllegal.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:oaIllegal:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaIllegal" action="${ctx}/oa/oaIllegal/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<div class="control-group">
			<label class="control-label">发生地点：</label>
			<div class="controls">
				<form:input name="happenLocation" path="happenLocation" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">违法行为：</label>
			<div class="controls">
				<form:select path="illegalDetail" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('illegal_detail')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">船名：</label>
			<div class="controls">
				<form:input path="shipName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发生日期：</label>
			<div class="controls">
				<input name="happenDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${oaIllegal.happenDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处置结果：</label>
			<div class="controls">
				<form:select path="dealWay" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('deal_way')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="oa:oaIllegal:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>