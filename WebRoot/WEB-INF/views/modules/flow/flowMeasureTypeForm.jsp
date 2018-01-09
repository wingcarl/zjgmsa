<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流量观测按船种分管理</title>
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
		<li><a href="${ctx}/flow/flowMeasureType/">流量观测按船种分列表</a></li>
		<li class="active"><a href="${ctx}/flow/flowMeasureType/form?id=${flowMeasureType.id}">流量观测按船种分<shiro:hasPermission name="flow:flowMeasureType:edit">${not empty flowMeasureType.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="flow:flowMeasureType:edit">查看</shiro:lacksPermission></a></li>
		<li><a href="${ctx}/flow/flowMeasureType/analysis">流量观测船种数据分析</a></li>
		
	</ul><br/>
	<form:form id="inputForm" modelAttribute="flowMeasureType" action="${ctx}/flow/flowMeasureType/save" method="post" class="form-horizontal">
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
			<label class="control-label">上行小计：</label>
			<div class="controls">
				<form:input path="upTotal" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上行客船：</label>
			<div class="controls">
				<form:input path="upPassengerShip" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上行普通货船：</label>
			<div class="controls">
				<form:input path="upCargoShip" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上行集装箱船：</label>
			<div class="controls">
				<form:input path="upContainerShip" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上行危险品船：</label>
			<div class="controls">
				<form:input path="upDangerousShip" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上行船队：</label>
			<div class="controls">
				<form:input path="upBoatTrain" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上行渔船：</label>
			<div class="controls">
				<form:input path="upFishBoat" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上行工程船：</label>
			<div class="controls">
				<form:input path="upProjectShip" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上行公务船：</label>
			<div class="controls">
				<form:input path="upPublicShip" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上行其它船舶：</label>
			<div class="controls">
				<form:input path="upOthers" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下行小计：</label>
			<div class="controls">
				<form:input path="downTotal" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下行客船：</label>
			<div class="controls">
				<form:input path="downPassengerShip" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下行普通货船：</label>
			<div class="controls">
				<form:input path="downCargoShip" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下行集装箱船：</label>
			<div class="controls">
				<form:input path="downContainerShip" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下行危险品船：</label>
			<div class="controls">
				<form:input path="downDangerousShip" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下行船队：</label>
			<div class="controls">
				<form:input path="downBoatTrain" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下行渔船：</label>
			<div class="controls">
				<form:input path="downFishBoat" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下行工程船：</label>
			<div class="controls">
				<form:input path="downProjectShip" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下行公务船：</label>
			<div class="controls">
				<form:input path="downPublicShip" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下行其它船舶：</label>
			<div class="controls">
				<form:input path="downOthers" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">观测日期：</label>
			<div class="controls">
				<input name="flowMeasureDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${flowMeasureType.flowMeasureDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="flow:flowMeasureType:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>