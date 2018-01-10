<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>隐患跟踪管理</title>
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
		<li class="active"><a href="${ctx}/danger/dangerInfoSpy/">隐患跟踪列表</a></li>
		<shiro:hasPermission name="danger:dangerInfoSpy:edit"><li><a href="${ctx}/danger/dangerInfoSpy/form?detail=${dangerInfo}">隐患跟踪情况添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dangerInfoSpy" action="${ctx}/danger/dangerInfoSpy/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>检查时间：</label>
				<input name="beginSpyTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dangerInfoSpy.beginSpyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endSpyTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dangerInfoSpy.endSpyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>创建者</th>
				<th>跟踪检查时间</th>
				<th>检查情况</th>
				<shiro:hasPermission name="danger:dangerInfoSpy:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dangerInfoSpy">
			<tr>
				<td><a href="${ctx}/danger/dangerInfoSpy/form?id=${dangerInfoSpy.id}">
					${dangerInfoSpy.createBy.name}
				</a></td>
				<td>
					<fmt:formatDate value="${dangerInfoSpy.spyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${dangerInfoSpy.spyNote}
				</td>
				<shiro:hasPermission name="danger:dangerInfoSpy:edit"><td>
    				<a href="${ctx}/danger/dangerInfoSpy/form?id=${dangerInfoSpy.id}">修改</a>
					<a href="${ctx}/danger/dangerInfoSpy/delete?id=${dangerInfoSpy.id}" onclick="return confirmx('确认要删除该隐患吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>