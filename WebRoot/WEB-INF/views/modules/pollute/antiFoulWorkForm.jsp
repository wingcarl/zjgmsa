<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>防污染作业现场检查登记表管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<script src="${ctxStatic}/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script src="${ctxStatic}/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			var antiFoulCompanyList = ${antiFoulCompany}
			var shipList = ${shipList}
			var workDep = '${workDep}';
			var workShip = '${workShip}';
			$('#workDep').empty();
			$('#shipName').empty();
			
			if($('#workType').val()=='21'){
				$('#canyou').text('残油数量(吨)');
				$('#youwu').show();

			}else if($('#workType').val()=='2'){
				$('#canyou').text('作业数量(立方)');
				$('#youwu').hide();

			}else{
				$('#canyou').text('作业数量(吨)');
				$('#youwu').hide();

			}
			var $option = $('<option>').attr({'value':''}).text('');
			$('#workDep').append($option);
			for(var i=0;i<antiFoulCompanyList.length;i++){
				if(antiFoulCompanyList[i].foulType == $('#workType').val()){
					var $option = $('<option>').attr({'value':antiFoulCompanyList[i].companyId}).text(antiFoulCompanyList[i].companyName);
					$('#workDep').append($option);
				}
			}
			$('#workDep').val(workDep);
			$('#workDep').change();
			var $option = $('<option>').attr({'value':''}).text('');
			$('#shipName').append($option);
			for(var i=0;i<shipList.length;i++){
				if(shipList[i].companyId == workDep){
					var $option = $('<option>').attr({'value':shipList[i].shipId}).text(shipList[i].shipName);
					$('#shipName').append($option);
				}
			}
			$option = $('<option>').attr({'value':'000000'}).text('其它');
			$('#shipName').append($option);
			$('#shipName').val(workShip);
			$('#shipName').change();
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
			$('#workType').change(function(){
				$('#workDep').empty();
				$('#shipName').empty();
				$('#workDep').change();
				$('#shipName').change();
				if($(this).val()=='21'){
					$('#canyou').text('残油数量(吨)');
					$('#youwu').show();

				}else if($('#workType').val()=='2'){
					$('#canyou').text('作业数量(立方)');
					$('#youwu').hide();
				}else{
					$('#canyou').text('作业数量(吨)');
					$('#youwu').hide();

				}
				var $option = $('<option>').attr({'value':''}).text('');
				$('#workDep').append($option);
			
				for(var i=0;i<antiFoulCompanyList.length;i++){
					if(antiFoulCompanyList[i].foulType == $(this).val()){
						var $option = $('<option>').attr({'value':antiFoulCompanyList[i].companyId}).text(antiFoulCompanyList[i].companyName);
						$('#workDep').append($option);
					}
				}
				
			});
			$('#workDep').change(function(){
				if($('#workDep option:selected').text()=='其它'){
					$('.other-dep').show();
				}else{
					$('.other-dep').hide();
				}
				$('#shipName').empty();
				$('#shipName').change();
				var $option = $('<option>').attr({'value':''}).text('');
				$('#shipName').append($option);
				for(var i=0;i<shipList.length;i++){
					if(shipList[i].companyId == $(this).val()){
						var $option = $('<option>').attr({'value':shipList[i].shipId}).text(shipList[i].shipName);
						$('#shipName').append($option);
					}
				}
				$option = $('<option>').attr({'value':'000000'}).text('其它');
				$('#shipName').append($option);
			});
			$('#shipName').change(function(){
				if($(this).val()=='000000'){
					$('.other-ship').show();
				}else{
					$('.other-ship').hide();
				}
				
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pollute/antiFoulWork/">防污染作业现场检查登记表列表</a></li>
		<li class="active"><a href="${ctx}/pollute/antiFoulWork/form?id=${antiFoulWork.id}">防污染作业现场检查登记表<shiro:hasPermission name="pollute:antiFoulWork:edit">${not empty antiFoulWork.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="pollute:antiFoulWork:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="antiFoulWork" action="${ctx}/pollute/antiFoulWork/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">信息接收时间：</label>
			<div class="controls">
				<input name="receiveTime"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${antiFoulWork.receiveTime}" pattern="yyyy-MM-dd HH:mm"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
			</div> 
		
		</div>
		
		<div class="control-group">
			<label class="control-label">所属单位：</label>
			<div class="controls">
				<sys:treeselect id="office" name="office" value="${antiFoulWork.office.id}" labelName="" labelValue="${antiFoulWork.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">作业类别：</label>
			<div class="controls">
				<form:select id="workType" path="workType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pollutant_work_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">作业单位：</label>
			<div class="controls">
				<form:select id='workDep' path="workDep" class="input-xlarge ">
					<form:option value="" label=""/>
				</form:select>
			</div>
		</div>
		<div class="control-group other-dep hide">
			<label class="control-label">补充作业单位：</label>
			<div class="controls">
				<input name="otherCompany" class="input-xlarge "/>	
				</div>
		</div>
		<div class="control-group">
			<label class="control-label">作业单位船名/车名：</label>
			<div class="controls">
				<form:select id='shipName' path="shipName" class="input-xlarge ">
					<form:option value="" label=""/>
				</form:select>			
				</div>
		</div>
		<div class="control-group other-ship hide">
			<label class="control-label">补充作业单位船名/车名：</label>
			<div class="controls">
				<input name="otherShip" class="input-xlarge "/>	
				</div>
		</div>
		<div class="control-group">
			<label class="control-label" id="canyou">作业数量(吨/垃圾为立方)：</label>
			<div class="controls">
				<form:input path="num" htmlEscape="false" maxlength="64" class="input-xlarge number"/>
			</div>
		</div>
		<div class="control-group hide" id="youwu">
			<label class="control-label">油污水数量(吨)：</label>
			<div class="controls">
				<form:input path="workNumYouwu" htmlEscape="false" maxlength="64" class="input-xlarge number" value="0"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">接收船名：</label>
			<div class="controls">
				<form:input path="receiveShipName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始时间：</label>
			<div class="controls">
				<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${antiFoulWork.startTime}" pattern="yyyy-MM-dd HH:mm"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间：</label>
			<div class="controls">
				<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${antiFoulWork.endTime}" pattern="yyyy-MM-dd HH:mm"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">作业地点：</label>
			<div class="controls">
				<form:input path="workLocation" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
			</div>
		</div>
		
	
		
		<div class="control-group">
			<label class="control-label">现场抽查：</label>
			<div class="controls">
				<form:radiobuttons path="exam" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处置结果：</label>
			<div class="controls">
				<form:select path="result" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pollutant_deal_result')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:input path="remarks" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处置人员：</label>
			<div class="controls">
				<form:input path="dealPeople" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="pollute:antiFoulWork:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>