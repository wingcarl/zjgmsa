<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>电子巡查数据管理</title>
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
		<li><a href="${ctx}/oa/oaDigitalcruise/">电子巡查数据列表</a></li>
		<li class="active"><a href="${ctx}/oa/oaDigitalcruise/form?id=${oaDigitalcruise.id}">电子巡查数据<shiro:hasPermission name="oa:oaDigitalcruise:edit">${not empty oaDigitalcruise.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:oaDigitalcruise:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaDigitalcruise" action="${ctx}/oa/oaDigitalcruise/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<div class="control-group">
			<label class="control-label">巡航人员：</label>
			<div class="controls">
				<form:input path="cruisePeople" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">巡航起止时间：</label>
			<div class="controls">
				<form:textarea path="cruiseTime" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">巡航区域：</label>
			<div class="controls">
				<form:textarea path="cruiseArea" htmlEscape="false" rows="4" maxlength="1024" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发现解决执法业务方面的问题：</label>
			<div class="controls">
				<form:textarea path="solveProblem" htmlEscape="false" rows="4" maxlength="1024" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发现解决设备方面的问题：</label>
			<div class="controls">
				<form:textarea path="equipProblem" htmlEscape="false" rows="4" maxlength="1024" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="1" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">巡航次数：</label>
			<div class="controls">
				<form:input path="cruiseTimes" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">巡航时间（小时）十进制：</label>
			<div class="controls">
				<form:input path="cruiseTotalTime" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发现违章数量：</label>
			<div class="controls">
				<form:input path="illegalCount" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<hr>
		<table class="table-form">
			<tr>
			<th class="tit" colspan="6"><strong>双山渡口/张皋汽渡/通沙汽渡数据，港区处/保税区办事处/大桥办事处填写</strong></th>
			</tr>
			<tr>
				<td class="tit">渡口运次：</td>
					<td>
						<form:input id="dkyc" path="dkyc" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					<td class="tit">渡口运送车次：</td>
					<td>
						<form:input path="yscc" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>	
					<td class="tit">渡口运送人次：</td>
					<td>
						<form:input path="ysrc" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
			</tr>
		</table>
	
		<div class="form-actions">
		
			<shiro:hasPermission name="oa:oaDigitalcruise:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>