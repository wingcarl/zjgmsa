<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>处罚基础信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/penalty/penaltyBasicInfo/">处罚基础信息列表</a></li>
		<shiro:hasPermission name="penalty:penaltyBasicInfo:edit"><li><a href="${ctx}/penalty/penaltyBasicInfo/form">处罚基础信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="penaltyBasicInfo" action="${ctx}/penalty/penaltyBasicInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>处罚类别：</label>
				<form:input path="reason" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				
				<th>处罚类别</th>
				<th>案由</th>
				<th>违章代码</th>
				<th>违法计分</th>
				<th>更新者</th>
				<th>更新时间</th>
				<shiro:hasPermission name="penalty:penaltyBasicInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="penaltyBasicInfo">
			<tr>
				
				<td>
				<a href="${ctx}/penalty/penaltyBasicInfo/form?id=${penaltyBasicInfo.id}">
					${penaltyBasicInfo.reason}
				</a>
				</td>
				<td>
					${penaltyBasicInfo.fullReason}
				</td>
				<td>
					${penaltyBasicInfo.illegalCode}
				</td>
				<td>
					${penaltyBasicInfo.illegalPoint}
				</td>
				<td>
					${penaltyBasicInfo.createBy.name}
				</td>
				<td>
					<fmt:formatDate value="${penaltyBasicInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="penalty:penaltyBasicInfo:edit"><td>
    				<a href="${ctx}/penalty/penaltyBasicInfo/form?id=${penaltyBasicInfo.id}">修改</a>
					<a href="${ctx}/penalty/penaltyBasicInfo/delete?id=${penaltyBasicInfo.id}" onclick="return confirmx('确认要删除该处罚基础信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>