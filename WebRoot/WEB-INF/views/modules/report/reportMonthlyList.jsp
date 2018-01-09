<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>海事业务月度通报管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
				<form id="importForm" action="${ctx}/report/reportMonthly/import" method="post" enctype="multipart/form-data"
					class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
					<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
					<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
					<a href="${ctx}/report/reportMonthly/import/template">下载模板</a>
				</form>
</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/report/reportMonthly/">海事业务月度通报列表</a></li>
		<shiro:hasPermission name="report:reportMonthly:edit"><li><a href="${ctx}/report/reportMonthly/form">海事业务月度通报添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="reportMonthly" action="${ctx}/report/reportMonthly/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>创建时间</th>
				<th>巡航时间（小时）</th>
				<th>实施船舶跟踪（艘次）</th>
				<th>遇险人数（人）</th>
				<shiro:hasPermission name="report:reportMonthly:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="reportMonthly">
			<tr>
				<td><a href="${ctx}/report/reportMonthly/form?id=${reportMonthly.id}">
					<fmt:formatDate value="${reportMonthly.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${reportMonthly.cruiseTotalTime}
				</td>
				<td>
					${reportMonthly.vtsShipMonitor}
				</td>
				<td>
					${reportMonthly.upDangerPeople}
				</td>
				<shiro:hasPermission name="report:reportMonthly:edit"><td>
    				<a href="${ctx}/report/reportMonthly/form?id=${reportMonthly.id}">修改</a>
					<a href="${ctx}/report/reportMonthly/delete?id=${reportMonthly.id}" onclick="return confirmx('确认要删除该海事业务月度通报吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>