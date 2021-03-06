<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>扩展接口管理</title>
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
		<li class="active"><a href="${ctx}/setting/weixinExpandconfig/">扩展接口列表</a></li>
		<shiro:hasPermission name="setting:weixinExpandconfig:edit"><li><a href="${ctx}/setting/weixinExpandconfig/form">扩展接口添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="weixinExpandconfig" action="${ctx}/setting/weixinExpandconfig/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>功能名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>微信公众号设置ID</th>
				<th>功能类</th>
				<th>功能描述</th>
				<th>关键字</th>
				<th>功能名称</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="setting:weixinExpandconfig:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinExpandconfig">
			<tr>
				<td><a href="${ctx}/setting/weixinExpandconfig/form?id=${weixinExpandconfig.id}">
					${weixinExpandconfig.accountid}
				</a></td>
				<td>
					${weixinExpandconfig.classname}
				</td>
				<td>
					${weixinExpandconfig.content}
				</td>
				<td>
					${weixinExpandconfig.keyword}
				</td>
				<td>
					${weixinExpandconfig.name}
				</td>
				<td>
					<fmt:formatDate value="${weixinExpandconfig.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${weixinExpandconfig.remarks}
				</td>
				<shiro:hasPermission name="setting:weixinExpandconfig:edit"><td>
    				<a href="${ctx}/setting/weixinExpandconfig/form?id=${weixinExpandconfig.id}">修改</a>
					<a href="${ctx}/setting/weixinExpandconfig/delete?id=${weixinExpandconfig.id}" onclick="return confirmx('确认要删除该扩展接口吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>