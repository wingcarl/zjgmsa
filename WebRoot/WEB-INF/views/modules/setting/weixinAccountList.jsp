<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信公众号设置管理</title>
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
		<li class="active"><a href="${ctx}/setting/weixinAccount/">微信公众号设置列表</a></li>
		<shiro:hasPermission name="setting:weixinAccount:edit"><li><a href="${ctx}/setting/weixinAccount/form">微信公众号设置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="weixinAccount" action="${ctx}/setting/weixinAccount/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>公众帐号名称</th>
				<th>公众帐号TOKEN</th>
				<th>公众微信号</th>
				<th>公众号类型</th>
				<th>电子邮箱</th>
				<th>公众帐号描述</th>
				<th>公众帐号APPID</th>
				<th>公众帐号APPSECRET</th>
				<th>原始ID</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="setting:weixinAccount:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinAccount">
			<tr>
				<td><a href="${ctx}/setting/weixinAccount/form?id=${weixinAccount.id}">
					${weixinAccount.accountname}
				</a></td>
				<td>
					${weixinAccount.accounttoken}
				</td>
				<td>
					${weixinAccount.accountnumber}
				</td>
				<td>
					${weixinAccount.accounttype}
				</td>
				<td>
					${weixinAccount.accountemail}
				</td>
				<td>
					${weixinAccount.accountdesc}
				</td>
				<td>
					${weixinAccount.accountappid}
				</td>
				<td>
					${weixinAccount.accountappsecret}
				</td>
				<td>
					${weixinAccount.weixinAccountid}
				</td>
				<td>
					<fmt:formatDate value="${weixinAccount.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${weixinAccount.remarks}
				</td>
				<shiro:hasPermission name="setting:weixinAccount:edit"><td>
    				<a href="${ctx}/setting/weixinAccount/form?id=${weixinAccount.id}">修改</a>
					<a href="${ctx}/setting/weixinAccount/delete?id=${weixinAccount.id}" onclick="return confirmx('确认要删除该微信公众号设置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>