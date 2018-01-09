<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流量观测尺度数据管理</title>
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
		<li><a href="${ctx}/flow/flowMeasureScale/">流量观测尺度数据列表</a></li>
		<shiro:hasPermission name="flow:flowMeasureScale:edit"><li class="active"><a href="${ctx}/flow/flowMeasureScale/form">流量观测尺度数据添加</a></li></shiro:hasPermission>
		<li><a href="${ctx}/flow/flowMeasureScale/analysis">流量观测尺度数据分析</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="flowMeasureScale" action="${ctx}/flow/flowMeasureScale/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">断面名称：</label>
			<div class="controls">
				<form:input path="sectionName" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">当量合计：</label>
			<div class="controls">
				<form:input path="equivalentWeight" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合计：</label>
			<div class="controls">
				<form:input path="total" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上行船舶小计：</label>
			<div class="controls">
				<form:input path="upTotal" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上行（小于30米）：</label>
			<div class="controls">
				<form:input path="upLess30" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上行（30-50米）：</label>
			<div class="controls">
				<form:input path="upBetween3050" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上行（50-90米）：</label>
			<div class="controls">
				<form:input path="upBetween5090" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上行（90-180米）：</label>
			<div class="controls">
				<form:input path="upBetween90180" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上行（大于180米）：</label>
			<div class="controls">
				<form:input path="upMore180" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
	<div class="control-group">
			<label class="control-label">下行船舶小计：</label>
			<div class="controls">
				<form:input path="upTotal" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下行（小于30米）：</label>
			<div class="controls">
				<form:input path="upLess30" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下行（30-50米）：</label>
			<div class="controls">
				<form:input path="upBetween3050" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下行（50-90米）：</label>
			<div class="controls">
				<form:input path="upBetween5090" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下行（90-180米）：</label>
			<div class="controls">
				<form:input path="upBetween90180" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下行（大于180米）：</label>
			<div class="controls">
				<form:input path="upMore180" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">观测日期：</label>
			<div class="controls">
				<input name="flowMeasureDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${flowMeasureScale.flowMeasureDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="flow:flowMeasureScale:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>