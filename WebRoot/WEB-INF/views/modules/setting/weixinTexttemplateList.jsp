<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信文本消息管理</title>
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
		<li class="active"><a href="${ctx}/setting/weixinTexttemplate/">微信文本消息列表</a></li>
		<shiro:hasPermission name="setting:weixinTexttemplate:edit"><li><a href="${ctx}/setting/weixinTexttemplate/form">微信文本消息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="weixinTexttemplate" action="${ctx}/setting/weixinTexttemplate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>内容：</label>
				<form:input path="content" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>时间</th>
				<th>内容</th>
				<th>模板名称</th>
				<th>微信公众号设置ID</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="setting:weixinTexttemplate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinTexttemplate">
			<tr>
				<td><a href="${ctx}/setting/weixinTexttemplate/form?id=${weixinTexttemplate.id}">
					${weixinTexttemplate.addtime}
				</a></td>
				<td>
					${weixinTexttemplate.content}
				</td>
				<td>
					${weixinTexttemplate.templatename}
				</td>
				<td>
					${weixinTexttemplate.accountid}
				</td>
				<td>
					<fmt:formatDate value="${weixinTexttemplate.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${weixinTexttemplate.remarks}
				</td>
				<shiro:hasPermission name="setting:weixinTexttemplate:edit"><td>
    				<a href="${ctx}/setting/weixinTexttemplate/form?id=${weixinTexttemplate.id}">修改</a>
					<a href="${ctx}/setting/weixinTexttemplate/delete?id=${weixinTexttemplate.id}" onclick="return confirmx('确认要删除该微信文本消息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>