<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>隐患排查登记表管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/layer/layer.js"></script>
	<script src="${ctxStatic}/layer/lay/dest/layui.all.js"></script>	
	<script src="${ctxStatic}/layer/layui.js"></script>
	<link href="${ctxStatic}/layer/css/layui.css" rel="stylesheet">
	<script type="text/javascript">
		$(document).ready(function() {
			$('.spy').click(function(){
				layer.open({
					type:2,
					title:$(this).attr('detail'),
					area:['890px','560px'],
					shade:0,
					maxmin:true,
					content:'${ctx}/danger/dangerInvestSpy?dangerInvest='+$(this).val(),
					btn:['关闭'],
					btn1:function(){
						layer.closeAll();
					},
					zIndex:layer.zIndex
				});  
			});
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出隐患数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/danger/dangerInvest/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctx}/danger/dangerInvest/")
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
		function audit(url){
			top.$.jBox.confirm("确认要导出审批表吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#searchForm").attr("action",url);
					$("#searchForm").submit();
					$("#searchForm").attr("action","${ctx}/danger/dangerInvest/");
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');

		}
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
<div id="importBox" class="hide">
				<form id="importForm" action="${ctx}/danger/dangerInvest/import" method="post" enctype="multipart/form-data"
					class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
					<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
					<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
					<a href="${ctx}/danger/dangerInvest/import/template">下载模板</a>
				</form>
			</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/danger/dangerInvest/">隐患排查登记表列表</a></li>
		<li><a href="${ctx}/danger/dangerInvest/form?id=${dangerInvest.id}">隐患排查登记表<shiro:hasPermission name="danger:dangerInvest:edit">${not empty dangerInvest.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="danger:dangerInvest:edit">查看</shiro:lacksPermission></a></li>
		<li><a href="${ctx}/danger/dangerInvest/statics">隐患数据统计</a></li>
	
	</ul><br/>
	<form:form id="searchForm" modelAttribute="dangerInvest" action="${ctx}/danger/dangerInvest/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>隐患名称：</label>
				<form:input path="dangerName" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>隐患分类：</label>
				<form:select path="dangerClassify" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('danger_classify')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>发现时间：</label>
				<input name="beginFindTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dangerInvest.beginFindTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endFindTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dangerInvest.endFindTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>是否下达整改通知书：</label>
				<form:radiobuttons path="isTransmit" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>整改是否完成：</label>
				<form:radiobuttons path="isComplete" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered">

		<thead>
			<tr>
				<th>发现部门</th>
				<th>分类</th>
				<th>隐患描述</th>
				<th>隐患整改责任单位</th>
				<th>发现时间</th>
				<th>是否下<br>达整改<br>通知书</th>
				<th>整改<br>是否<br>完成</th>
				<th>隐患名称</th>
				<th>隐患详情</th>
				<th>整改要求</th>
				<th>整改期限(天)</th>
				<shiro:hasPermission name="danger:dangerInvest:edit"><th>跟踪检查</th></shiro:hasPermission>
				
				<shiro:hasPermission name="danger:dangerInvest:edit"><th style="min-width:150px;">具体 操作</th></shiro:hasPermission>
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dangerInvest">
		<c:if test="${dangerInvest.isComplete eq '0'}">
				<tr style="color:red;font-weight:bold">
		</c:if>
		<c:if test="${dangerInvest.isComplete eq '1'}">
				<tr>
		</c:if>
				<td  rowspan="${dangerInvest.dangerInvestDetailList.size()}"><a href="${ctx}/danger/dangerInvest/dangerInvestReport?id=${dangerInvest.id}" target="_blank">
					${dangerInvest.findOffice.name}
					</a>
				</td>
				<td  rowspan="${dangerInvest.dangerInvestDetailList.size()}">
					${fns:getDictLabel(dangerInvest.dangerClassify, 'danger_classify', '')}
				</a>
				</td>
				<td  rowspan="${dangerInvest.dangerInvestDetailList.size()}">
					${dangerInvest.againstLaw}
				</td>
				
				<td  rowspan="${dangerInvest.dangerInvestDetailList.size()}">
					${dangerInvest.dangerCompany}
				</td>
				
				<td  rowspan="${dangerInvest.dangerInvestDetailList.size()}">
					<fmt:formatDate value="${dangerInvest.findTime}" pattern="yyyy-MM-dd"/>
				</td>

				<td  rowspan="${dangerInvest.dangerInvestDetailList.size()}">
					${fns:getDictLabel(dangerInvest.isTransmit, 'yes_no', '')}
				</td>
				
				
				<td  rowspan="${dangerInvest.dangerInvestDetailList.size()}">
					${fns:getDictLabel(dangerInvest.isComplete, 'yes_no', '')}
				</td>
					<td >
						${dangerInvest.dangerInvestDetailList[0].dangerName}
					</td>
					<td >
						${dangerInvest.dangerInvestDetailList[0].detail}
					</td>
					<td >
						${dangerInvest.dangerInvestDetailList[0].requirement}
					</td>
					<td >
						${dangerInvest.dangerInvestDetailList[0].deadtime}
					</td>
					<shiro:hasPermission name="danger:dangerInvest:edit"><td>
						<button class="btn spy" detail="${dangerInvest.dangerInvestDetailList[0].detail}" value="${dangerInvest.dangerInvestDetailList[0].id}">跟踪</button>
					</td></shiro:hasPermission>
					<shiro:hasPermission name="danger:dangerInvest:edit"><td rowspan="${dangerInvest.dangerInvestDetailList.size()}">
    				<c:if test="${dangerInvest.isConfirm ne '1'}">
    				<a class="btn" href="${ctx}/danger/dangerInvest/form?id=${dangerInvest.id}">修改</a>
					<a class="btn" href="${ctx}/danger/dangerInvest/delete?id=${dangerInvest.id}" onclick="return confirmx('确认要删除该隐患排查登记表吗？', this.href)">删除</a>
					<br><br>
					</c:if>
					<shiro:hasPermission name="danger:dangerInvest:confirm">
					<a class="btn" href="${ctx}/danger/dangerInvest/confirm?id=${dangerInvest.id}"><c:if test="${dangerInvest.isConfirm ne '1'}">确认</c:if>
					<c:if test="${dangerInvest.isConfirm eq '1'}">取消</c:if>
					</shiro:hasPermission>
					</a>
					
					<!--  <a class="btn btn-success" onclick=audit("${ctx}/danger/dangerInvest/exportAudit?sid=${dangerInvest.id}"); href="javascript:void(0);">整改通知书导出</a>-->
					
				</td></shiro:hasPermission>
				</tr>
				<c:forEach items="${dangerInvest.dangerInvestDetailList}" begin="1" var="d">
				<c:if test="${dangerInvest.isComplete eq '0'}">
					<tr style="color:red;font-weight:bold">
				</c:if>
				<c:if test="${dangerInvest.isComplete eq '1'}">
					<tr>
				</c:if>
					<td>
						${d.dangerName }
					</td>
					<td >
						${d.detail}
					</td>
					<td >
						${d.requirement}
					</td>
					<td >
						${d.deadtime}
					</td>
					<shiro:hasPermission name="danger:dangerInvest:edit"><td>
						<button class="btn spy" detail="${d.detail}" value="${d.id}">跟踪</a>
					</td></shiro:hasPermission>
					
				</tr>
				</c:forEach>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>