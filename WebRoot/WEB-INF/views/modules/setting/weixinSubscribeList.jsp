<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>欢迎语管理</title>
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
		<li class="active"><a href="${ctx}/setting/weixinSubscribe/">欢迎语列表</a></li>
		<shiro:hasPermission name="setting:weixinSubscribe:edit"><li><a href="${ctx}/setting/weixinSubscribe/form">欢迎语添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="weixinSubscribe" action="${ctx}/setting/weixinSubscribe/" method="post" class="breadcrumb form-search">
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
				<th>公众号设置ID</th>
				<th>时间</th>
				<th>消息类型</th>
				<th>模板ID</th>
				<th>模板名称</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="setting:weixinSubscribe:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinSubscribe">
			<tr>
				<td><a href="${ctx}/setting/weixinSubscribe/form?id=${weixinSubscribe.id}">
					${weixinSubscribe.accountid}
				</a></td>
				<td>
					${weixinSubscribe.addtime}
				</td>
				<td>
					${weixinSubscribe.msgtype}
				</td>
				<td>
					${weixinSubscribe.templateid}
				</td>
				<td>
					${weixinSubscribe.templatename}
				</td>
				<td>
					<fmt:formatDate value="${weixinSubscribe.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${weixinSubscribe.remarks}
				</td>
				<shiro:hasPermission name="setting:weixinSubscribe:edit"><td>
    				<a href="${ctx}/setting/weixinSubscribe/form?id=${weixinSubscribe.id}">修改</a>
					<a href="${ctx}/setting/weixinSubscribe/delete?id=${weixinSubscribe.id}" onclick="return confirmx('确认要删除该欢迎语吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>