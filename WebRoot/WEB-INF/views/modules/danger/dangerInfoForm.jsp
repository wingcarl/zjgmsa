<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>隐患信息表管理</title>
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
		<li><a href="${ctx}/danger/dangerInfo/">隐患信息表列表</a></li>
		<li class="active"><a href="${ctx}/danger/dangerInfo/form?id=${dangerInfo.id}">隐患信息表<shiro:hasPermission name="danger:dangerInfo:edit">${not empty dangerInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="danger:dangerInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dangerInfo" action="${ctx}/danger/dangerInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">排查对象：</label>
			<div class="controls">
				<form:select path="dangerCompany" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${companyList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">隐患类别：</label>
			<div class="controls">
				<form:select path="dangerType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dangerInfo_dangerType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">隐患概况描述：</label>
			<div class="controls">
				<form:input path="dangerDetail" htmlEscape="false" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">可能性级别：</label>
			<div class="controls">
				<form:select path="possbility" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dangerInfo_possbility')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">后果严重程度：</label>
			<div class="controls">
				<form:select path="result" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dangerInfo_result')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">整改难度：</label>
			<div class="controls">
				<form:select path="difficulty" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dangerInfo_difficulty')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">隐患级别：</label>
			<div class="controls">
				<form:select path="level" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dangerInfo_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">隐患评级：</label>
			<div class="controls">
				<form:select path="grade" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dangerInfo_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发现途径：</label>
			<div class="controls">
				<form:select path="findWay" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dangerInfo_findWay')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发现时间：</label>
			<div class="controls">
				<input name="findTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${dangerInfo.findTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发现部门：</label>
			<div class="controls">
				<sys:treeselect id="findOffice" name="findOffice" value="${dangerInfo.findOffice.id}" labelName="" labelValue="${dangerInfo.findOffice.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处理意见：</label>
			<div class="controls">
				<form:input path="suggestion" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">直接责任人：</label>
			<div class="controls">
				<form:input path="liable" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">整改情况：</label>
			<div class="controls">
				<form:input path="dealResult" htmlEscape="false" maxlength="512" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">整改是否已完成：</label>
			<div class="controls">
				<form:radiobuttons path="isComplete" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销号时间：</label>
			<div class="controls">
				<input name="completeTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${dangerInfo.completeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单位负责人：</label>
			<div class="controls">
				<form:input path="leader" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:input path="remarks" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="danger:dangerInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>