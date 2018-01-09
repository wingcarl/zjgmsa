<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>关键字回复管理</title>
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
		<li class="active"><a href="${ctx}/setting/weixinAutoresponse/">关键字回复列表</a></li>
		<shiro:hasPermission name="setting:weixinAutoresponse:edit"><li><a href="${ctx}/setting/weixinAutoresponse/form">关键字回复添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="weixinAutoresponse" action="${ctx}/setting/weixinAutoresponse/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>关键字：</label>
				<form:input path="keyword" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>关键字</th>
				<th>内容类型</th>
				<th>内容模板ID</th>
				<th>内容模板</th>
				<th>公众号设置ID</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="setting:weixinAutoresponse:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinAutoresponse">
			<tr>
				<td><a href="${ctx}/setting/weixinAutoresponse/form?id=${weixinAutoresponse.id}">
					${weixinAutoresponse.keyword}
				</a></td>
				<td>
					${weixinAutoresponse.msgtype}
				</td>
				<td>
					${weixinAutoresponse.rescontent}
				</td>
				<td>
					${weixinAutoresponse.templatename}
				</td>
				<td>
					${weixinAutoresponse.accountid}
				</td>
				<td>
					<fmt:formatDate value="${weixinAutoresponse.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${weixinAutoresponse.remarks}
				</td>
				<shiro:hasPermission name="setting:weixinAutoresponse:edit"><td>
    				<a href="${ctx}/setting/weixinAutoresponse/form?id=${weixinAutoresponse.id}">修改</a>
					<a href="${ctx}/setting/weixinAutoresponse/delete?id=${weixinAutoresponse.id}" onclick="return confirmx('确认要删除该关键字回复吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>