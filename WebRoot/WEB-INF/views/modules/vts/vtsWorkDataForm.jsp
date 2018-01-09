<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>VTS工作数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$('input[type="text"]').focus(function(){
				$(this).val('');
			})
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
	<style>
		.label_style{width:10%;}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/vts/vtsWorkData/">VTS工作数据列表</a></li>
		<li class="active"><a href="${ctx}/vts/vtsWorkData/form?id=${vtsWorkData.id}">VTS工作数据<shiro:hasPermission name="vts:vtsWorkData:edit">${not empty vtsWorkData.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="vts:vtsWorkData:edit">查看</shiro:lacksPermission></a></li>
		<shiro:hasPermission name="vts:vtsWorkData:edit"><li><a href="${ctx}/vts/vtsWorkData/sum">VTS工作数据统计</a></li></shiro:hasPermission>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="vtsWorkData" action="${ctx}/vts/vtsWorkData/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="label_style">到港受限船舶：</label>
				<form:input path="inportLimitShip" htmlEscape="false" maxlength="11" class="label_style digits required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			
			<label class="label_style">过境受限船舶：</label>
				<form:input path="transitLimitShip" htmlEscape="false" maxlength="11" class="label_style required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			<label class="label_style">接受船位报告：</label>
				<form:input path="positionReport" htmlEscape="false" maxlength="11" class="label_style required"/>
				<span class="help-inline"><font color="red">*</font> </span>
		</div>
		<div class="control-group">
			<label class="label_style">重点监控船舶：</label>
				<form:input path="importantShip" htmlEscape="false" maxlength="11" class="label_style required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			<label class="label_style">超大型船舶：</label>
				<form:input path="giantShip" htmlEscape="false" maxlength="11" class="label_style required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			<label class="label_style">船舶跟踪：</label>
				<form:input path="shipFollow" htmlEscape="false" maxlength="11" class="label_style required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			
		</div>
		<div class="control-group">
			<label class="label_style">四客一危：</label>
				<form:input path="fourPassenger" htmlEscape="false" maxlength="11" class="label_style required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			<label class="label_style">信息服务：</label>
				<form:input path="informationService" htmlEscape="false" maxlength="11" class="label_style required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			<label class="label_style">交通组织服务：</label>
				<form:input path="trafficOperation" htmlEscape="false" maxlength="11" class="label_style required"/>
				<span class="help-inline"><font color="red">*</font> </span>
		</div>
		
		<div class="control-group">
			<label class="label_style">助航服务：</label>
				<form:input path="navaid" htmlEscape="false" maxlength="11" class="label_style required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			<label class="label_style">避免险情：</label>
				<form:input path="avoidDanger" htmlEscape="false" maxlength="11" class="label_style required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			<label class="label_style">执法联动：</label>
				<form:input path="enforceUnion" htmlEscape="false" maxlength="11" class="label_style required"/>
				<span class="help-inline"><font color="red">*</font> </span>
		
		</div>
		<div class="control-group">
				<label class="label_style">锚泊船监控：</label>
				<form:input path="mooringSpying" htmlEscape="false" maxlength="11" class="label_style required"/>
				<span class="help-inline"><font color="red">*</font> </span>
				<label class="label_style">违章纠正：</label>
				<form:input path="illegal" htmlEscape="false" maxlength="11" class="label_style required"/>
				<span class="help-inline"><font color="red">*</font> </span>
				
		</div>
		
		<div class="control-group">
			<label class="label_style">值班动态：</label>
				<form:textarea path="toDoList" htmlEscape="false" rows="12" class="input-xxlarge"/>
				<span class="help-inline"><font color="red">每天白班工班（0800-1600）填写，中班与夜班工班空白即可！</font> </span>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="vts:vtsWorkData:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>