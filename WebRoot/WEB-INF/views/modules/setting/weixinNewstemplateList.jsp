<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>图文消息管理</title>
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
		<li class="active"><a href="${ctx}/setting/weixinNewstemplate/">图文消息列表</a></li>
		<shiro:hasPermission name="setting:weixinNewstemplate:edit"><li><a href="${ctx}/setting/weixinNewstemplate/form">图文消息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="weixinNewstemplate" action="${ctx}/setting/weixinNewstemplate/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>模板名称：</label>
				<form:input path="tempatename" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th>模板名称</th>
				<th>类型</th>
				<th>微信公众号设置ID</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="setting:weixinNewstemplate:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinNewstemplate">
			<tr>
				<td><a href="${ctx}/setting/weixinNewstemplate/form?id=${weixinNewstemplate.id}">
					${weixinNewstemplate.addtime}
				</a></td>
				<td>
					${weixinNewstemplate.tempatename}
				</td>
				<td>
					${weixinNewstemplate.type}
				</td>
				<td>
					${weixinNewstemplate.accountid}
				</td>
				<td>
					<fmt:formatDate value="${weixinNewstemplate.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${weixinNewstemplate.remarks}
				</td>
				<shiro:hasPermission name="setting:weixinNewstemplate:edit"><td>
    				<a href="${ctx}/setting/weixinNewstemplate/form?id=${weixinNewstemplate.id}">修改</a>
					<a href="${ctx}/setting/weixinNewstemplate/delete?id=${weixinNewstemplate.id}" onclick="return confirmx('确认要删除该图文消息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>