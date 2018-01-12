<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>通航环境表管理</title>
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
		<li class="active"><a href="${ctx}/msa/msaTonghang/">通航环境表列表</a></li>
		<shiro:hasPermission name="msa:msaTonghang:edit"><li><a href="${ctx}/msa/msaTonghang/form">通航环境表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="msaTonghang" action="${ctx}/msa/msaTonghang/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>更新时间：</label>
				<input name="beginUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${msaTonghang.beginUpdateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${msaTonghang.endUpdateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>更新者</th>
				<th>更新时间</th>
				<th>通航环境内容</th>
				<shiro:hasPermission name="msa:msaTonghang:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="msaTonghang">
			<tr>
				<td><a href="${ctx}/msa/msaTonghang/form?id=${msaTonghang.id}">
					${msaTonghang.updateBy.name}
				</a></td>
				<td>
					<fmt:formatDate value="${msaTonghang.updateDate}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
					${msaTonghang.content}
				</td>
				<shiro:hasPermission name="msa:msaTonghang:edit"><td>
    				<a href="${ctx}/msa/msaTonghang/form?id=${msaTonghang.id}">修改</a>
					<a href="${ctx}/msa/msaTonghang/delete?id=${msaTonghang.id}" onclick="return confirmx('确认要删除该通航环境表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>