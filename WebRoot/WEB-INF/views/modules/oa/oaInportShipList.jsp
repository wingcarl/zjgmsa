<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>每日在港船舶动态管理</title>
	<meta name="decorator" content="default"/>
	
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出在港船舶动态数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/oa/oaInportShip/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctx}/oa/oaInportShip/");
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnWithdraw").click(function(){
				top.$.jBox.confirm("确认要删除今日的在港船舶动态数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/oa/oaInportShip/deleteToday");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctx}/oa/oaInportShip/");
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
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
				<form id="importForm" action="${ctx}/oa/oaInportShip/import" method="post" enctype="multipart/form-data"
					class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
					<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
					<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
					<a href="${ctx}/oa/oaInportShip/import/template">下载模板</a>
				</form>
			</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oa/oaInportShip/">每日在港船舶动态列表</a></li>
		<shiro:hasPermission name="oa:oaInportShip:edit"><li><a href="${ctx}/oa/oaInportShip/form">每日在港船舶动态添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaInportShip" action="${ctx}/oa/oaInportShip/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>创建时间：</label>
				<input name="createStartDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${oaInportShip.createStartDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
					-
				<input name="createEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${oaInportShip.createEndDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>部门：</label>
				<form:select path="officeId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${infoList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>船名：</label>
				<form:input path="shipName" htmlEscape="false" maxlength="64" class="input-medium"/>		
			</li>
			<li><label>始发港：</label>
				<form:input path="departurePort" htmlEscape="false" maxlength="64" class="input-medium"/>		
			</li>
			<li><label>目的港：</label>
				<form:input path="destinationPort" htmlEscape="false" maxlength="64" class="input-medium"/>		
			</li>
			<li><label>具体位置：</label>
				<form:input path="location" htmlEscape="false" maxlength="64" class="input-medium"/>		
			</li>
			<li><label>作业状态：</label>
				<form:input path="workStatus" htmlEscape="false" maxlength="64" class="input-medium"/>		
			</li>
			<li><label>船籍港：</label>
				<form:input path="shipLocation" htmlEscape="false" maxlength="64" class="input-medium"/>		
			</li>
			<li><label>水道：</label>
				<form:input path="channel" htmlEscape="false" maxlength="64" class="input-medium"/>		
			</li>
			<li><label>网格：</label>
				<form:input path="grid" htmlEscape="false" maxlength="64" class="input-medium"/>		
			</li>
			<li><label>船舶种类：</label>
				<form:input path="shipType" htmlEscape="false" maxlength="64" class="input-medium"/>		
			</li>
			<li><label>载货情况：</label>
				<form:input path="carryCargo" htmlEscape="false" maxlength="64" class="input-medium"/>		
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<shiro:hasPermission name="oa:oaInportShip:edit">
			<li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			<li class="btns"><input id="btnWithdraw" class="btn btn-primary" type="button" value="撤回当日数据"/>
			</shiro:hasPermission>
			</li>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>创建时间</th>
				<th>部门</th>
				<th>网格</th>
				<th>水道</th>
				<th>船名</th>
				<th>船(国)籍港</th>
				<th>船舶种类</th>
				<th>船长</th>
				<th>始发港</th>
				<th>目的港</th>
				<th>载货情况</th>
				<th>具体位置</th>
				<th>作业状态</th>
				<th>负责人</th>
				<th>联系电话</th>
				<th>施工情况</th>
				<shiro:hasPermission name="oa:oaInportShip:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaInportShip">
			<tr>
				<td><a href="${ctx}/oa/oaInportShip/form?id=${oaInportShip.id}">
					<fmt:formatDate value="${oaInportShip.createDate}" pattern="yyyy-MM-dd"/>
				</a></td>
				<td>
					${oaInportShip.createBy.office.name}
				</td>
				<td>
					${oaInportShip.grid}
				</td>
				<td>
					${oaInportShip.channel}
				</td>
				<td>
					${oaInportShip.shipName}
				</td>
				<td>
					${oaInportShip.shipLocation}
				</td>
				<td>
					${oaInportShip.shipType}
				</td>
				<td>
					${oaInportShip.shipLength}
				</td>
				<td>
					${oaInportShip.departurePort}
				</td>
				<td>
					${oaInportShip.destinationPort}
				</td>
				<td>
					${oaInportShip.carryCargo}
				</td>
				<td>
					${oaInportShip.location}
				</td>
				<td>
					${oaInportShip.workStatus}
				</td>
				<td>
					${oaInportShip.inchargePeople}
				</td>
				<td>
					${oaInportShip.telephone}
				</td>
				<td>
					${oaInportShip.constructStatus}
				</td>
				<shiro:hasPermission name="oa:oaInportShip:edit"><td>
    				<a href="${ctx}/oa/oaInportShip/form?id=${oaInportShip.id}">修改</a>
					<a href="${ctx}/oa/oaInportShip/delete?id=${oaInportShip.id}" onclick="return confirmx('确认要删除该每日在港船舶动态吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>