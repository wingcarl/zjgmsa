<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>陆域巡查数据管理</title>
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
		<li><a href="${ctx}/oa/oaLuyustatics/">陆域巡查数据列表</a></li>
		<li class="active"><a href="${ctx}/oa/oaLuyustatics/form?id=${oaLuyustatics.id}">陆域巡查数据<shiro:hasPermission name="oa:oaLuyustatics:edit">${not empty oaLuyustatics.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:oaLuyustatics:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaLuyustatics" action="${ctx}/oa/oaLuyustatics/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<table class="table-form">
			<tr>
					<td class="tit">参加巡查人数：</td>
					<td>
						<form:input path="cyxcrs" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					<td class="tit">出动执法车辆：</td>
					<td>
						<form:input path="cdzfcl" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					<td class="tit">检查船舶：</td>
					<td>
						<form:input path="jccb" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
			</tr>
			<tr>
					<td class="tit">客汽渡码头\渡运区域：</td>
					<td>
						<form:input path="dkmt" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					<td class="tit">危险品码头：</td>
					<td>
						<form:input path="wxpmt" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					<td class="tit">水工作业区：</td>
					<td>
						<form:input path="sgzyq" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
			</tr>
			<tr>
					<td class="tit">锚地\停泊区：</td>
					<td>
						<form:input path="mdtbq" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					<td class="tit">其他码头：</td>
					<td>
						<form:input path="qtmt" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					<td class="tit">查处违法行为：</td>
					<td>
						<form:input path="ccwfxw" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
			</tr>
			<tr>
					<td class="tit">发现存在问题或不足：</td>
					<td>
						<form:input path="czwt" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					<td class="tit">实施行政处罚：</td>
					<td>
						<form:input path="xzcf" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
					
					<td class="tit">向指挥中心反馈：</td>
					<td>
						<form:input path="zhzxfk" htmlEscape="false" maxlength="11" class="input-block-level  digits"/>
					</td>
			</tr>
			<tr>
					<td class="tit">巡查发现或缺陷简况：</td>
					<td colspan="6">
						<form:textarea path="xcfx" maxlength="1024" class="input-block-level required" rows="6" />
					</td>
			</tr>
			<tr>
					<td class="tit">备注：</td>
					<td colspan="6">
						<form:textarea path="beizhu" maxlength="1024" class="input-block-level required" rows="6" />
					</td>
			</tr>		
			</table>
		<div class="form-actions">
			<shiro:hasPermission name="oa:oaLuyustatics:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>