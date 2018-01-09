<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>处罚基础信息管理</title>
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
		<li><a href="${ctx}/penalty/penaltyBasicInfo/">处罚基础信息列表</a></li>
		<li class="active"><a href="${ctx}/penalty/penaltyBasicInfo/form?id=${penaltyBasicInfo.id}">处罚基础信息<shiro:hasPermission name="penalty:penaltyBasicInfo:edit">${not empty penaltyBasicInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="penalty:penaltyBasicInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="penaltyBasicInfo" action="${ctx}/penalty/penaltyBasicInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">处罚类别：</label>
			<div class="controls">
				<form:input path="reason" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">案由：</label>
			<div class="controls">
				<form:input path="fullReason" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">违法条款：</label>
			<div class="controls">
				<form:textarea path="violationRegulation" maxlength="512" class="input-block-level required" rows="3" />
				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处罚依据：</label>
			<div class="controls">
				<form:textarea path="punishBasis" maxlength="512" class="input-block-level required" rows="3" />				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">从轻处罚依据：</label>
			<div class="controls">
				<form:textarea path="punishBasisLighten" maxlength="512" class="input-block-level required" rows="3" />				
			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">从重处罚依据：</label>
			<div class="controls">
				<form:textarea path="punishBasisHeavy" maxlength="512" class="input-block-level required" rows="3" />				
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">违章代码：</label>
			<div class="controls">
				<form:input path="illegalCode" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">违法计分：</label>
			<div class="controls">
				<form:input path="illegalPoint" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">违章事实：</label>
			<div class="controls">
				<form:textarea path="illegalDetail" maxlength="512" class="input-block-level required" rows="3" />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="penalty:penaltyBasicInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>