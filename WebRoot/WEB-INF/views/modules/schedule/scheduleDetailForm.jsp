<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>排班表管理</title>
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
		<li><a href="${ctx}/schedule/scheduleDetail/">排班表列表</a></li>
		<li class="active"><a href="${ctx}/schedule/scheduleDetail/form?id=${scheduleDetail.id}">排班表<shiro:hasPermission name="schedule:scheduleDetail:edit">${not empty scheduleDetail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="schedule:scheduleDetail:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="scheduleDetail" action="${ctx}/schedule/scheduleDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<div class="control-group">
			<label class="control-label">部门：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${scheduleDetail.office.id}" labelName="" labelValue="${scheduleDetail.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排班日期：</label>
			<div class="controls">
				<input name="scheduleDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${scheduleDetail.scheduleDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">白班/一号台：</label>
			<div class="controls">
					<form:input path="userList1" htmlEscape="false"  class="input-xlarge" maxlength="128"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中班/二号台：</label>
			<div class="controls">
					<form:input path="userList2" htmlEscape="false"  class="input-xlarge" maxlength="128"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">夜班：</label>
			<div class="controls">
					<form:input path="userList3" htmlEscape="false"  class="input-xlarge" maxlength="128"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">领导带班：</label>
			<div class="controls">
					<form:input path="userList4" htmlEscape="false"  class="input-xlarge" maxlength="128"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="schedule:scheduleDetail:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>