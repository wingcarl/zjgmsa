<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>违法行为管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var parentParam;
			//$("#name").focus();
			$("#illegalDetail").change(function(){
				var type = $(this).val();
				if(type=="8"){
					$('#illegalDiv').after('<div class="control-group">'+
					'<label class="control-label">请补充其它违法行为描述：</label>'+
					'<div class="controls">'+
						'<input id="qtwf" name="qtwf" htmlEscape="false" maxlength="255" class="input-xlarge "/>'+
					'</div>'+
				'</div>')
				}
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
</head>
<body>
	<br>
	<form:form id="inputForm" modelAttribute="oaIllegal" action="${ctx}/oa/oaIllegal/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">船名：</label>
			<div class="controls">
				<form:input path="shipName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发生地点：</label>
			<div class="controls">
				<form:input id="happenLocation" name="happenLocation" path="happenLocation" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group" id="illegalDiv">
			<label class="control-label">违法行为：</label>
			<div class="controls">
				<form:select id="illegalDetail" path="illegalDetail" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('illegal_detail')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">违章类别(江苏局报表分类)：</label>
			<div class="controls">
				<form:select path="illegalType" class="input-xlarge ">
					<form:options items="${fns:getDictList('illegal_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处置结果：</label>
			<div class="controls">
				<form:select path="dealWay" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('deal_way')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注：</label>
			<div class="controls">
				<form:input id="beizhu" name="beizhu" path="beizhu" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		
	</form:form>
</body>
</html>