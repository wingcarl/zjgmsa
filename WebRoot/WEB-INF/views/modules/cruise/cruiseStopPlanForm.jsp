<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>停航检修计划管理</title>
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
		<li><a href="${ctx}/cruise/cruiseStopPlan/">停航检修计划列表</a></li>
		<li class="active"><a href="${ctx}/cruise/cruiseStopPlan/form?id=${cruiseStopPlan.id}">停航检修计划<shiro:hasPermission name="cruise:cruiseStopPlan:edit">${not empty cruiseStopPlan.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cruise:cruiseStopPlan:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="cruiseStopPlan" action="${ctx}/cruise/cruiseStopPlan/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		
		<sys:message content="${message}" type="warn"/>
	
		<div class="control-group">
			<label class="control-label">停航检修日期：</label>
			<div class="controls">
				<input name="stopDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${cruiseStopPlan.stopDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
			<label style="color:red">日期规则：锦丰海事处和保税区办事处不能同一天检修，南丰办事处和大桥办事处不能同一天检修，支队两条艇、港区海事处两条艇不能同一天检修。以系统登记时间为准，先到先得。</label>
		</div>
		
		<div class="control-group">
			<label class="control-label">停航海巡艇：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office.id" value="${cruiseStopPlan.office.id}" labelName="" labelValue="${cruiseStopPlan.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="cruise:cruiseStopPlan:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>