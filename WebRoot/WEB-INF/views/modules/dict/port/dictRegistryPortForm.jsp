<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>船籍港字典信息管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dict/port/dictRegistryPort/">船籍港字典信息列表</a></li>
		<li class="active"><a href="${ctx}/dict/port/dictRegistryPort/form?id=${dictRegistryPort.id}">船籍港字典信息<shiro:hasPermission name="dict:port:dictRegistryPort:edit">${not empty dictRegistryPort.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dict:port:dictRegistryPort:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dictRegistryPort" action="${ctx}/dict/port/dictRegistryPort/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">船籍港编号：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">船籍港名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="24" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">船籍港英文：</label>
			<div class="controls">
				<form:input path="pinyin" htmlEscape="false" maxlength="24" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所在省份：</label>
			<div class="controls">
				<form:input path="province" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dict:port:dictRegistryPort:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>