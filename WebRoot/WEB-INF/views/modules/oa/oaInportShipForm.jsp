<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>每日在港船舶动态管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/jquery-ui/jquery-ui.js"></script>
	<link href="${ctxStatic}/jquery-ui/jquery-ui.css" rel="stylesheet">
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
			 var availableTags = ${shipNameList}
			$("#shipName").autocomplete({
			      source: availableTags,
			      select: function( event, ui ) {
			    	 var name = ui.item.value;
			    	 $.ajax({
			    	        type: "post",
			    	        dataType: "json",
			    	        url: '${ctx}/oa/oaInportShip/searchByShipName',
			    	        data: {shipName:name},
			    	        success: function (data) {
			    	            $('#channel').val(data.channel);
			    	            $('#grid').val(data.grid);
			    	            $('#shipLocation').val(data.shipLocation);
			    	            $('#shipType').val(data.shipType);
			    	            $('#shipLength').val(data.shipLength);
			    	            $('#departurePort').val(data.departurePort);
			    	            $('#destinationPort').val(data.destinationPort);
			    	            $('#carryCargo').val(data.carryCargo);
			    	            $('#workStatus').val(data.workStatus);
			    	            $('#location').val(data.location);
			    	            $('#inchargePeople').val(data.incharge);
			    	            $('#telephone').val(data.telephone);
			    	            $('#constructStatus').val(data.constructStatus);
			    	            $('#remark').val(data.remark);
			    	        }
			    	    });

			    	 
			      }
		    });
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/oaInportShip/">每日在港船舶动态列表</a></li>
		<li class="active"><a href="${ctx}/oa/oaInportShip/form?id=${oaInportShip.id}">每日在港船舶动态<shiro:hasPermission name="oa:oaInportShip:edit">${not empty oaInportShip.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:oaInportShip:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaInportShip" action="${ctx}/oa/oaInportShip/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<div class="control-group">
			<label class="control-label">船名：</label>
			<div class="controls">
				<form:input id="shipName" path="shipName" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label">网格：</label>
			<div class="controls">
				<form:input id="grid" path="grid" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">水道：</label>
			<div class="controls">
				<form:input id="channel" path="channel" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">船(国)籍港：</label>
			<div class="controls">
				<form:input id="shipLocation" path="shipLocation" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">船舶种类：</label>
			<div class="controls">
				<form:input id="shipType" path="shipType" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">船长：</label>
			<div class="controls">
				<form:input id="shipLength" path="shipLength" htmlEscape="false" class="input-xlarge  number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">始发港：</label>
			<div class="controls">
				<form:input id="departurePort" path="departurePort" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">目的港：</label>
			<div class="controls">
				<form:input id="destinationPort" path="destinationPort" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">载货情况：</label>
			<div class="controls">
				<form:input id="carryCargo" path="carryCargo" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">具体位置：</label>
			<div class="controls">
				<form:input id="location" path="location" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">作业状态：</label>
			<div class="controls">
				<form:input id="workStatus" path="workStatus" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">负责人：</label>
			<div class="controls">
				<form:input id="inchargePeople" path="inchargePeople" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input id="telephone" path="telephone" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">施工情况：</label>
			<div class="controls">
				<form:input id="constructStatus" path="constructStatus" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:input id="remarks" path="remarks" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="oa:oaInportShip:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>