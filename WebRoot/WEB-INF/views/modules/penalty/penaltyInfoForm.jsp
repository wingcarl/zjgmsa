<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<script src="${ctxStatic}/layer/lay/dest/layui.all.js"></script>
	
	<script src="${ctxStatic}/layer/layui.js"></script>
	<link href="${ctxStatic}/layer/css/layui.css" rel="stylesheet">
	<title>违章数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			//全局变量
			var config = {};
			config.severity = 2;
			config.basisInfo = {};
			$("#penaltySeverity").change(function(){
				var se = $(this).children('option:selected').val();
				config.severity = se;
				if(config.basisInfo != null){
					if(se == 2){
		        		$('#punishBasis').val(config.basisInfo.punishBasis);
		        	}else if(se == 3){
		        		$('#punishBasis').val(config.basisInfo.punishBasisHeavy);
		        	}else if(se == 1){
		        		$('#punishBasis').val(config.basisInfo.punishBasisLighten);
		        	}
				}
				
			});
			$("#reason").change(function(){
				var id = $(this).children('option:selected').val();
				$.ajax({
		    	        type: "get",
		    	        dataType: "json",
		    	        url: '${ctx}/penalty/penaltyBasicInfo/getPenaltyBasicInfo?id='+id,
		    	        success: function (data) {
		    	        	config.basisInfo = data;
		    	        	$('#fullReason').val(data.fullReason);
		    	        	$('#illegalPoint').val(data.illegalPoint);
		    	        	$('#illegalCode').val(data.illegalCode);
		    	        	
		    	        	$('#violationRegulation').val(data.violationRegulation);
		    	        	if(config.severity == 2){
		    	        		$('#punishBasis').val(data.punishBasis);
		    	        	}else if(config.severity == 3){
		    	        		$('#punishBasis').val(data.punishBasisHeavy);
		    	        	}else if(config.severity == 1){
		    	        		$('#punishBasis').val(data.punishBasisLighten);
		    	        	}
		    	        	
		    	        }
				 });
			});
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
		<li><a href="${ctx}/penalty/penaltyInfo/">违章数据列表</a></li>
		<li class="active"><a href="${ctx}/penalty/penaltyInfo/form?id=${penaltyInfo.id}">违章数据<shiro:hasPermission name="penalty:penaltyInfo:edit">${not empty penaltyInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="penalty:penaltyInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="penaltyInfo" action="${ctx}/penalty/penaltyInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<fieldset>
			<legend>违章船舶基本信息</legend>
			<table class="table-form">
				<tr>
						<td class="tit">船名</td><td style="width:10%">
							<form:input path="shipName" htmlEscape="false" class="input-block-level" maxlength="20"/>
						</td>
						<td class="tit">吃水</td><td style="width:10%">
							<form:input path="cs" htmlEscape="false"  class="input-block-level" maxlength="20"/>
						</td>
						<td class="tit">船籍港</td><td style="width:10%">
							<form:input path="shipPort" htmlEscape="false" class="input-block-level" maxlength="20"/>	
						</td><td class="tit">船舶种类</td><td style="width:10%">
							<form:input path="shipType" htmlEscape="false"  class="input-block-level" maxlength="20"/>
						</td>
						<td class="tit">船舶类别</td><td style="width:10%">
							<form:input path="shipArea" htmlEscape="false"  class="input-block-level" maxlength="20"/>
						</td>
						
					</tr>
			
				<tr>
					<td class="tit">船舶总吨</td><td>
							<form:input path="shipLoad" htmlEscape="false" class="input-block-level" maxlength="20"/>
						</td>
					<td class="tit">主机功率</td><td>
							<form:input path="shipPower" htmlEscape="false" class="input-block-level" maxlength="20"/>
						</td>
					<td class="tit">船舶长度</td><td>
							<form:input path="shipLength" htmlEscape="false" class="input-block-level" maxlength="20"/>
						</td><td class="tit">船舶登记号码</td><td colspan="3">
							<form:input path="shipRegist" htmlEscape="false" class="input-block-level" maxlength="20"/>	
						</td>
						
				</tr>
				<tr>
						<td class="tit">船舶所有人</td><td colspan="3">
							<form:input path="shipOwner" htmlEscape="false" class="input-block-level" maxlength="20"/>
						</td>
						<td class="tit">船舶所有人地址</td>
						<td colspan="5"><form:input path="shipOwnerLoc" htmlEscape="false" class="input-block-level"/></td>
					
					
				</tr>
				<tr>
						<td class="tit">船舶经营人</td><td colspan="3">
							<form:input path="shipTransactor" htmlEscape="false" class="input-block-level" maxlength="20"/>
						</td>
						<td class="tit">船舶经营人地址</td>
						<td colspan="5"><form:input path="shipTransactorLoc" htmlEscape="false" class="input-block-level"/></td>
				</tr>
				
		</table>
		</fieldset>
		<fieldset>
			<legend>航次基本信息</legend>
		
		<table class="table-form">
			<tr>
						<td class="tit">始发港</td><td style="width:10%">
							<form:input path="departurePort" htmlEscape="false" class="input-block-level" maxlength="20"/>
						</td>
						<td class="tit">目的港</td><td style="width:10%">
							<form:input path="destinationPort" htmlEscape="false"  class="input-block-level" maxlength="20"/>
						</td>
						<td class="tit">违法行为发生日期</td><td style="width:20%">
						<input name="illegalHappenDate" type="text" readonly="readonly" maxlength="20" class="input-block-level Wdate "
					value="<fmt:formatDate value="${penaltyInfo.illegalHappenDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							
						</td><td class="tit">违法行为发生地点</td><td style="width:20%">
							<form:input path="illegalHappenLoc" htmlEscape="false"  class="input-block-level" maxlength="20"/>
						</td>
						
						
					</tr>
		</table>
		</fieldset>
		
		<fieldset>
			<legend>处理人基本信息</legend>
			<table class="table-form"
			<tr>
						<td class="tit">处理人</td><td style="width:10%">
							<form:input path="dealerName" htmlEscape="false" class="input-block-level" maxlength="20"/>
						</td>
						<td class="tit">身份证号</td><td style="width:20%">
							<form:input path="idcardNum" htmlEscape="false"  class="input-block-level" maxlength="20"/>
						</td>
						<td class="tit">联系电话</td><td style="width:15%">
							<form:input path="contactTelephone" htmlEscape="false" class="input-block-level" maxlength="20"/>	
						</td><td class="tit">联系地址</td><td style="width:30%">
							<form:input path="contactAddress" htmlEscape="false"  class="input-block-level" maxlength="20"/>
						</td>
						
						
					</tr>
				</table>
		</fieldset>
			<fieldset>
			<legend>案卷信息</legend>
			<table class="table-form">
			<tr>
						<td class="tit">执法人员1</td><td style="width:10%">
							<form:select id="officer1" path="officer1" class="input-block-level">
							<form:option value="" label=""/>
							<form:options items="${cUsers}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
						</td>
						<td class="tit">执法人员2</td><td style="width:10%">
							<form:select id="officer2" path="officer2" class="input-block-level">
							<form:option value="" label=""/>
							<form:options items="${cUsers}" itemLabel="name" itemValue="id" htmlEscape="false"/>
						</form:select>
						</td>
						<td class="tit">立卷时间</td><td style="width:20%">
							<input name="fileCreateDate" type="text" readonly="readonly" maxlength="20" class="input-block-level Wdate "
							value="<fmt:formatDate value="${penaltyInfo.fileCreateDate}" pattern="yyyy-MM-dd HH:mm"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/>
						</td><td class="tit">案号</td><td style="width:30%">
							<form:input path="fileNum" htmlEscape="false"  class="input-block-level" maxlength="20"/>
						</td>
					</tr>
				<tr>
						<td class="tit">处罚轻重</td><td style="width:10%">
							<form:select id="penaltySeverity" path="penaltySeverity" class="input-block-level">
							<form:option value="2" label=""/>
							<form:option value="1" label="从轻处罚"/>
							<form:option value="2" label="一般处罚"/>
							<form:option value="3" label="从重处罚"/>
						</form:select>
						</td>
						
						<td class="tit">处罚金额</td><td style="width:10%">
							<form:input path="penaltyMoney" htmlEscape="false"  class="input-block-level" maxlength="20"/>
						</td>
						<td class="tit">案由</td><td style="width:20%">
						<form:select id="reason" path="penaltyReason" class="input-block-level">
							<form:option value="" label=""/>
							<form:options items="${infoList}" itemLabel="reason" itemValue="id" htmlEscape="false"/>
						</form:select>
						</td>
						<td class="tit">完整案由</td><td style="width:30%">
							<form:input path="fullReason" htmlEscape="false"  class="input-block-level" maxlength="20"/>
						</td>
				</tr>
				<tr>
				
					<td class="tit">违法处置代码</td><td style="width:10%">
							<form:input path="illegalCode" htmlEscape="false" class="input-block-level" maxlength="20"/>
					</td>
					<td class="tit">违法计分</td><td style="width:10%">
							<form:input path="illegalPoint" htmlEscape="false" class="input-block-level" maxlength="20"/>
					</td>
				</tr>
				
			
				</table>
				<table class="table-form">
					<tr>
						<td class="tit">违反条例</td>
						<td style="width:90%">
							<form:input path="violationRegulation" htmlEscape="false"  class="input-block-level" />
						</td>
					
					</tr>
					<tr>
						<td class="tit">处罚依据</td>
						<td style="width:90%">
							<form:input path="punishBasis" htmlEscape="false"  class="input-block-level" />
						</td>
					</tr>
				</table>
		</fieldset>
		<div id="wgnx" style="display:none">
			<fieldset>
				<legend>违章逆行补充内容</legend>
				<table class="table-form">
						<td class="tit">逆行方式</td><td style="width:10%">
							<form:input path="retrogradeWay" htmlEscape="false" class="input-block-level" maxlength="20"/>
						</td>
						<td class="tit">逆行开始位置</td><td style="width:30%">
							<form:input path="retroBeginPos" htmlEscape="false"  class="input-block-level" maxlength="20"/>
						</td>
						<td class="tit">逆行结束位置</td><td style="width:30%">
							<form:input path="retroEndPos" htmlEscape="false" class="input-block-level" maxlength="20"/>	
						</td>
						
				</table>
			</fieldset>
		</div>
		<div id="overload" style="display:none">
			<fieldset>
				<legend>超载补充内容</legend>
				<table class="table-form">
					<td class="tit">实际干舷</td><td style="width:10%">
							<form:input path="realFreeboard" htmlEscape="false" class="input-block-level" maxlength="20"/>
						</td>
						<td class="tit">A级航区核定干舷</td><td style="width:10%">
							<form:input path="aFreeboard" htmlEscape="false"  class="input-block-level" maxlength="20"/>
						</td>
						<td class="tit">干舷单位为mm，直接填数字，不需要单位</td>
				</table>
			</fieldset>
		</div>
		
		<div class="form-actions">
			<shiro:hasPermission name="penalty:penaltyInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>