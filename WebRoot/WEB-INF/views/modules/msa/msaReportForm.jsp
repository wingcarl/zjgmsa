<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>政务信息管理</title>
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
		<li><a href="${ctx}/msa/msaReport/">政务信息列表</a></li>
		<li class="active"><a href="${ctx}/msa/msaReport/form?id=${msaReport.id}">政务信息<shiro:hasPermission name="msa:msaReport:edit">${not empty msaReport.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="msa:msaReport:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="msaReport" action="${ctx}/msa/msaReport/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">作者：</label>
			<div class="controls">
				<form:input path="author" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">链接：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="256" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">种类：</label>
			<div class="controls">
				<form:checkboxes path="typeList" items="${fns:getDictList('msa_report_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">部门：</label>
			<div class="controls">
				<sys:treeselect id="depid" name="depid" value="${msaReport.depid.id}" labelName="" labelValue="${msaReport.depid.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发表日期：</label>
			<div class="controls">
				<input name="publicDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${msaReport.publicDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="msa:msaReport:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>