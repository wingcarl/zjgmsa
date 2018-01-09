<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡航数据管理</title>
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
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/test/oaMulCruisedata/">巡航数据列表</a></li>
		<li class="active"><a href="${ctx}/test/oaMulCruisedata/form?id=${oaMulCruisedata.id}">巡航数据<shiro:hasPermission name="test:oaMulCruisedata:edit">${not empty oaMulCruisedata.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="test:oaMulCruisedata:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaMulCruisedata" action="${ctx}/test/oaMulCruisedata/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">zyhl：</label>
			<div class="controls">
				<form:input path="zyhl" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">mdtbq：</label>
			<div class="controls">
				<form:input path="mdtbq" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">sgzyq：</label>
			<div class="controls">
				<form:input path="sgzyq" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">wfhxgd：</label>
			<div class="controls">
				<form:input path="wfhxgd" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">wfsgzy：</label>
			<div class="controls">
				<form:input path="wfsgzy" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">kymt：</label>
			<div class="controls">
				<form:input path="kymt" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">zhbzyc：</label>
			<div class="controls">
				<form:input path="zhbzyc" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">yhcs：</label>
			<div class="controls">
				<form:input path="yhcs" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">pnq：</label>
			<div class="controls">
				<form:input path="pnq" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">wfyzcs：</label>
			<div class="controls">
				<form:input path="wfyzcs" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">psc：</label>
			<div class="controls">
				<form:input path="psc" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">danger_port：</label>
			<div class="controls">
				<form:input path="dangerPort" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">jtzz：</label>
			<div class="controls">
				<form:input path="jtzz" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">ffxczl：</label>
			<div class="controls">
				<form:input path="ffxczl" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">qbq：</label>
			<div class="controls">
				<form:input path="qbq" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">cyjz：</label>
			<div class="controls">
				<form:input path="cyjz" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">fxwz：</label>
			<div class="controls">
				<form:input path="fxwz" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">clwz：</label>
			<div class="controls">
				<form:input path="clwz" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">qsk：</label>
			<div class="controls">
				<form:input path="qsk" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">wfpf：</label>
			<div class="controls">
				<form:input path="wfpf" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">solve_problem：</label>
			<div class="controls">
				<form:input path="solveProblem" htmlEscape="false" maxlength="1024" class="input-xlarge "/>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">巡航时间段数据：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>备注信息</th>
								<th>start_time</th>
								<th>end_time</th>
								<shiro:hasPermission name="test:oaMulCruisedata:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="oaCruisetimeList">
						</tbody>
						<shiro:hasPermission name="test:oaMulCruisedata:edit"><tfoot>
							<tr><td colspan="5"><a href="javascript:" onclick="addRow('#oaCruisetimeList', oaCruisetimeRowIdx, oaCruisetimeTpl);oaCruisetimeRowIdx = oaCruisetimeRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="oaCruisetimeTpl">//<!--
						<tr id="oaCruisetimeList{{idx}}">
							<td class="hide">
								<input id="oaCruisetimeList{{idx}}_id" name="oaCruisetimeList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="oaCruisetimeList{{idx}}_delFlag" name="oaCruisetimeList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<textarea id="oaCruisetimeList{{idx}}_remarks" name="oaCruisetimeList[{{idx}}].remarks" rows="4" maxlength="255" class="input-small ">{{row.remarks}}</textarea>
							</td>
							<td>
								<input id="oaCruisetimeList{{idx}}_startTime" name="oaCruisetimeList[{{idx}}].startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
									value="{{row.startTime}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</td>
							<td>
								<input id="oaCruisetimeList{{idx}}_endTime" name="oaCruisetimeList[{{idx}}].endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
									value="{{row.endTime}}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</td>
							<shiro:hasPermission name="test:oaMulCruisedata:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#oaCruisetimeList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var oaCruisetimeRowIdx = 0, oaCruisetimeTpl = $("#oaCruisetimeTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(oaMulCruisedata.oaCruisetimeList)};
							for (var i=0; i<data.length; i++){
								addRow('#oaCruisetimeList', oaCruisetimeRowIdx, oaCruisetimeTpl, data[i]);
								oaCruisetimeRowIdx = oaCruisetimeRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="test:oaMulCruisedata:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>