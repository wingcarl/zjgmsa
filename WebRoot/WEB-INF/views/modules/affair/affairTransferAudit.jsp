<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>水水中转货物核销登记薄管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.text{text-align:center}
	</style>
	
	<script src="${ctxStatic}/layer/layer.js"></script>
	<script src="${ctxStatic}/layer/lay/dest/layui.all.js"></script>
		
	<script src="${ctxStatic}/layer/layui.js"></script>
	<link href="${ctxStatic}/layer/css/layui.css" rel="stylesheet">
	
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#btnSubmit").click(function(){
				
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
		<li><a href="${ctx}/affair/affairTransferDetail/">水水中转货物核销登记薄列表</a></li>
		<li class="active"><a href="${ctx}/affair/affairTransferDetail/form?id=${affairTransferInfo.id}">水水中转货物核销登记薄<shiro:hasPermission name="affair:affairTransferInfo:edit">${not empty affairTransferInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="affair:affairTransferInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="affairTransferSecond" action="${ctx}/affair/affairTransferDetail/saveAudit" method="post" class="form-horizontal">
		<form:hidden path="act.taskId"/>
		<form:hidden path="act.taskName"/>
		<form:hidden path="act.taskDefKey"/>
		<form:hidden path="act.procInsId"/>
		<form:hidden path="act.procDefId"/>
		<form:hidden id="flag" path="act.flag"/>
		<sys:message content="${message}"/>	
		<table class="table-form">
	
			<tr>
					<td rowspan="2" class="tit">一程船信息</td>
					<td class="tit">船名</td>
					<td class="tit">始发港</td>
					<td class="tit">航次号</td>
					<td class="tit">货物名称</td>
					<td class="tit">卸货码头</td>
					<td class="tit">申报日期</td>
			</tr>
			<tr>
					
					<td class="text">${affairTransferInfo.firstShipName}</td>
					<td class="text">${affairTransferInfo.firstShipDeparture}</td>
					<td class="text">${affairTransferInfo.firstMoveNum}</td>
					<td class="text">${affairTransferInfo.firstCargo}</td>
					<td class="text">${affairTransferInfo.firstUnloadPort}</td>
					<td class="text"><fmt:formatDate value="${affairTransferInfo.createDate}" pattern="yyyy年MM月dd日HH时mm分"/></td>
			</tr>
			<tr>
					<td class="tit">中转信息</td>
					<td class="tit">收货人</td>
					<td class="text">${affairTransferSecond.receiver}</td>
					<td class="tit">目的港</td>
					<td class="text">${affairTransferSecond.destination}</td>
					<td class="tit">预报数量</td>
					<td class="text">${affairTransferSecond.number}</td>
			</tr>
			
		</table>
		
			<div class="control-group">
				<label class="control-label">水水中转货物二程船信息表：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>二程船名</th>
								<th>运单号</th>
								<th>实际数量</th>
								<th>累计数量</th>
								<th>日期</th>
								<th>舱单</th>
								<shiro:hasPermission name="affair:affairTransferInfo:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
							</tr>
						</thead>
						<tbody id="affairTransferDetailList">
						</tbody>
						<shiro:hasPermission name="affair:affairTransferInfo:edit"><tfoot>
							<tr><td colspan="8"><a href="javascript:" onclick="addRow('#affairTransferDetailList', affairTransferDetailRowIdx, affairTransferDetailTpl);affairTransferDetailRowIdx = affairTransferDetailRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="affairTransferDetailTpl">//<!--
						<tr id="affairTransferDetailList{{idx}}">
							<td class="hide">
								<input id="affairTransferDetailList{{idx}}_id" name="affairTransferDetailList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="affairTransferDetailList{{idx}}_delFlag" name="affairTransferDetailList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="affairTransferDetailList{{idx}}_shipName" name="affairTransferDetailList[{{idx}}].shipName" type="text" value="{{row.shipName}}" maxlength="64" class="input-small "/>
							</td>
							<td>
								<input id="affairTransferDetailList{{idx}}_no" name="affairTransferDetailList[{{idx}}].no" type="text" value="{{row.no}}" maxlength="64" class="input-small "/>
							</td>
					
							<td>
								<input id="affairTransferDetailList{{idx}}_count" name="affairTransferDetailList[{{idx}}].count" type="text" value="{{row.count}}" maxlength="64" class="input-small "/>
							</td>
						
							<td>
								<input id="affairTransferDetailList{{idx}}_totalCount" name="affairTransferDetailList[{{idx}}].totalCount" type="text" value="{{row.totalCount}}" maxlength="64" class="input-small "/>
							</td>
							<td>
									<input id="affairTransferDetailList{{idx}}_auditDate" name="affairTransferDetailList[{{idx}}].auditDate" type="text" value="{{row.auditDate}}"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
										value=""
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
								
							</td>
							<td>
 								<input type="hidden" id="affairTransferDetailList{{idx}}_imgSrc" name="affairTransferDetailList{{idx}}_imgSrc" value="{{row.imgSrc}}" />
								<sys:ckfinder input="affairTransferDetailList{{idx}}_imgSrc" type="thumb" uploadPath="/affair/transferDetail" selectMultiple="false"/>
							</td>
							<shiro:hasPermission name="affair:affairTransferInfo:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#affairTransferDetailList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var affairTransferDetailRowIdx = 0, affairTransferDetailTpl = $("#affairTransferDetailTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(affairTransferSecond.affairTransferDetailList)};
							console.log(data);
							for (var i=0; i<data.length; i++){
								addRow('#affairTransferDetailList', affairTransferDetailRowIdx, affairTransferDetailTpl, data[i]);
								affairTransferDetailRowIdx = affairTransferDetailRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
			<input type="hidden" value="${affairTransferSecond.id}" name="secondId"/>
			<div class="control-group">
				<label class="control-label">接受人：</label>
				<div class="controls">
	                <sys:treeselect id="candidatesIds" name="candidatesIds" value="${affairTransferDetail.candidatesIds}" labelName="candidatesNames" labelValue="${affairTransferDetail.candidatesNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" notAllowSelectParent="true" checked="true"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			
			
			</div>
			<div class="control-group">
					<label class="control-label">审核意见：</label>
					<div class="controls">
						<textarea name="audit_comment" class="input-xxlarge required" rows="3"></textarea>
					</div>
			</div>
		<div class="form-actions">
			<shiro:hasPermission name="affair:affairTransferInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="提交"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
	<c:if test="${not empty affairTransferSecond.id}">
			<act:histoicFlow procInsId="${affairTransferSecond.act.procInsId}" />
	</c:if>
</body>
</html>