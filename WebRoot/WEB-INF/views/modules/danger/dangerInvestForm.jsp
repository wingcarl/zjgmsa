<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>隐患排查登记表管理</title>
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
		<li><a href="${ctx}/danger/dangerInvest/">隐患排查登记表列表</a></li>
		<li class="active"><a href="${ctx}/danger/dangerInvest/form?id=${dangerInvest.id}">隐患排查登记表<shiro:hasPermission name="danger:dangerInvest:edit">${not empty dangerInvest.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="danger:dangerInvest:edit">查看</shiro:lacksPermission></a></li>
		<li><a href="${ctx}/danger/dangerInvest/statics">隐患数据统计</a></li>
	
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dangerInvest" action="${ctx}/danger/dangerInvest/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<div class="control-group">
			<label class="control-label">隐患分级：</label>
			<div class="controls">
				<form:select path="dangerClassify" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('danger_classify')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">隐患分类：</label>
			<div class="controls">
				<form:select path="dangerType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('danger_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">违反的法律、法规等，或其他因素可能导致水上交通事故发生的物的危险状态、人的不安全行为和管理上的缺陷：</label>
			<div class="controls">
				<form:textarea path="againstLaw" htmlEscape="false" rows="4" maxlength="1024" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">隐患可能产生的后果：</label>
			<div class="controls">
				<form:textarea path="consequences" htmlEscape="false" rows="4" maxlength="1024" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">隐患整改责任单位：</label>
			<div class="controls">
				<form:input path="dangerCompany" htmlEscape="false" maxlength="128" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发现单位或部门：</label>
			<div class="controls">
				<sys:treeselect id="findOffice" name="findOffice" value="${dangerInvest.findOffice.id}" labelName="" labelValue="${dangerInvest.findOffice.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="" allowClear="true" notAllowSelectParent="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发现时间：</label>
			<div class="controls">
				<input name="findTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${dangerInvest.findTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">隐患具体情况：</label>
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th style="text-decoration:none;text-align:center">隐患名称	</th>
								<th style="text-decoration:none;text-align:center">隐患项目内容	</th>
								<th style="text-decoration:none;text-align:center">整改要求	</th>
								<th style="text-decoration:none;text-align:center">整改期限	</th>
								<th width="10">&nbsp;</th>
							</tr>
						</thead>
						<tbody id="dangerInvestDetailList">
						</tbody>
						<tfoot>
							<tr><td colspan="5"><a href="javascript:" onclick="addRow('#dangerInvestDetailList', dangerInvestDetailRowIdx, dangerInvestDetailTpl);dangerInvestDetailRowIdx = dangerInvestDetailRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot>
			</table>
				<script type="text/template" id="dangerInvestDetailTpl">//<!--
						
					<tr id="dangerInvestDetailList{{idx}}">
							<td class="hide">
								<input id="dangerInvestDetailList{{idx}}_id" name="dangerInvestDetailList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="dangerInvestDetaileList{{idx}}_delFlag" name="dangerInvestDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td style="text-align:center">
								<textarea id="dangerInvestDetailList{{idx}}_dangerName" name="dangerInvestDetailList[{{idx}}].dangerName">{{row.dangerName}}</textarea>

							</td>
							<td style="text-align:center">
								<textarea id="dangerInvestDetailList{{idx}}_detail" name="dangerInvestDetailList[{{idx}}].detail">{{row.detail}}</textarea>

							</td>
							<td style="text-align:center">
							
								<textarea id="dangerInvestDetailList{{idx}}_requirement" name="dangerInvestDetailList[{{idx}}].requirement">{{row.requirement}}</textarea>

							</td>
					
							<td style="text-align:center">
								<input id="dangerInvestDetailList{{idx}}_deadtime" name="dangerInvestDetailList[{{idx}}].deadtime" type="text" value="{{row.deadtime}}" maxlength="64" class="input-small "/> 天
							</td>
							<td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#dangerInvestDetailList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var dangerInvestDetailRowIdx = 0, dangerInvestDetailTpl = $("#dangerInvestDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(dangerInvest.dangerInvestDetailList)};
							for (var i=0; i<data.length; i++){
								addRow('#dangerInvestDetailList', dangerInvestDetailRowIdx, dangerInvestDetailTpl, data[i]);
								dangerInvestDetailRowIdx = dangerInvestDetailRowIdx + 1;
							}
						});
					</script>
		</div>
		<div class="control-group">
			<label class="control-label">整改存在的难点：</label>
			<div class="controls">
				<form:textarea path="dangerDifficulty" htmlEscape="false" rows="4" maxlength="1024" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">目前海事采取的对策：</label>
			<div class="controls">
				<form:textarea path="dealWay" htmlEscape="false" rows="4" maxlength="1024" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否下达整改通知书：</label>
			<div class="controls">
				<form:radiobuttons path="isTransmit" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">通知书发放时间：</label>
			<div class="controls">
				<input name="noticeTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${dangerInvest.noticeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">通知书编号：</label>
			<div class="controls">
				<form:input path="noticeNo" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采取强制措施情况：</label>
			<div class="controls">
				<form:textarea path="enforceMeasure" htmlEscape="false" rows="4" maxlength="1024" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">整改情况：</label>
			<div class="controls">
				<form:select path="dangerRecify" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('danger_recify')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">下一步工作措施：</label>
			<div class="controls">
				<form:textarea path="nextWay" htmlEscape="false" rows="4" maxlength="1024" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">整改是否已完成：</label>
			<div class="controls">
				<form:radiobuttons path="isComplete" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">整改完成时间：</label>
			<div class="controls">
				<input name="completeDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${dangerInvest.completeDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="form-actions">
			<c:if test="${dangerInvest.isConfirm ne '1'}">
			<shiro:hasPermission name="danger:dangerInvest:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>