<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>春运专项数据管理</title>
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
		<li><a href="${ctx}/spring/springData/">春运专项数据列表</a></li>
		<li class="active"><a href="${ctx}/spring/springData/form?id=${springData.id}">春运专项数据<shiro:hasPermission name="spring:springData:edit">${not empty springData.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="spring:springData:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="springData" action="${ctx}/spring/springData/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">创建部门：</label>
			<div class="controls">
					<sys:treeselect id="createOffice" name="createOffice" value="${springData.createOffice.id}" labelName="" labelValue="${springData.createOffice.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">录入时间：</label>
			<div class="controls">
				<input name="inputDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${springData.inputDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">渡运船舶数(艘次)：</label>
			<div class="controls">
				<form:input path="shipNum" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">渡运客位数(艘次*额定载客)：</label>
			<div class="controls">
				<form:input path="shipPersonNum" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">渡运完成客运量（人）：</label>
			<div class="controls">
				<form:input path="personNum" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">渡运运送车辆（辆）：</label>
			<div class="controls">
				<form:input path="carNum" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重特大安全事故次数：</label>
			<div class="controls">
				<form:input path="accidentNum" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">受伤人数：</label>
			<div class="controls">
				<form:input path="injuryNum" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">死亡人数：</label>
			<div class="controls">
				<form:input path="deadNum" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">失踪人数：</label>
			<div class="controls">
				<form:input path="missingNum" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参加执法人员数：</label>
			<div class="controls">
				<form:input path="zfryNum" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出动执法车辆：</label>
			<div class="controls">
				<form:input path="zfcarNum" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出动执法船艇：</label>
			<div class="controls">
				<form:input path="zfshipNum" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">海巡艇巡航时间：</label>
			<div class="controls">
				<form:input path="xhsj" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">海巡艇巡航里程：</label>
			<div class="controls">
				<form:input path="xhlc" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">查处违章行为：</label>
			<div class="controls">
				<form:input path="illegalNum" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">辖区事故险情：</label>
			<div class="controls">
				<form:input path="xqNum" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发放宣传材料：</label>
			<div class="controls">
				<form:input path="fbzl" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发布安全信息：</label>
			<div class="controls">
				<form:input path="aqxx" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">当日春运主要工作情况简介：</label>
			<div class="controls">
				<form:textarea path="summary" htmlEscape="false" rows="4" maxlength="1024" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="spring:springData:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>