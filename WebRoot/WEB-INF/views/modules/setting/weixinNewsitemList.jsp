<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图文消息内容列管理</title>
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
		<li class="active"><a href="${ctx}/setting/weixinNewsitem/">图文消息内容列列表</a></li>
		<shiro:hasPermission name="setting:weixinNewsitem:edit"><li><a href="${ctx}/setting/weixinNewsitem/form">图文消息内容列添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="weixinNewsitem" action="${ctx}/setting/weixinNewsitem/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>图文类型：图文还是外部url</th>
				<th>作者</th>
				<th>内容</th>
				<th>描述</th>
				<th>图片地址</th>
				<th>排序</th>
				<th>标题</th>
				<th>图文模板ID</th>
				<th>外部URL</th>
				<th>微信公众号设置ID</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="setting:weixinNewsitem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinNewsitem">
			<tr>
				<td><a href="${ctx}/setting/weixinNewsitem/form?id=${weixinNewsitem.id}">
					${weixinNewsitem.newType}
				</a></td>
				<td>
					${weixinNewsitem.author}
				</td>
				<td>
					${weixinNewsitem.content}
				</td>
				<td>
					${weixinNewsitem.description}
				</td>
				<td>
					${weixinNewsitem.imagepath}
				</td>
				<td>
					${weixinNewsitem.orders}
				</td>
				<td>
					${weixinNewsitem.title}
				</td>
				<td>
					${weixinNewsitem.templateid}
				</td>
				<td>
					${weixinNewsitem.url}
				</td>
				<td>
					${weixinNewsitem.accountid}
				</td>
				<td>
					<fmt:formatDate value="${weixinNewsitem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${weixinNewsitem.remarks}
				</td>
				<shiro:hasPermission name="setting:weixinNewsitem:edit"><td>
    				<a href="${ctx}/setting/weixinNewsitem/form?id=${weixinNewsitem.id}">修改</a>
					<a href="${ctx}/setting/weixinNewsitem/delete?id=${weixinNewsitem.id}" onclick="return confirmx('确认要删除该图文消息内容列吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>