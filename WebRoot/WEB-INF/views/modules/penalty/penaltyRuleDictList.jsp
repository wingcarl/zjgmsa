<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>违章字典管理</title>
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
		<li class="active"><a href="${ctx}/penalty/penaltyRuleDict/">违章字典列表</a></li>
		<shiro:hasPermission name="penalty:penaltyRuleDict:edit"><li><a href="${ctx}/penalty/penaltyRuleDict/form">违章字典添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="penaltyRuleDict" action="${ctx}/penalty/penaltyRuleDict/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>具体案由：</label>
				<form:input path="cause" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>总体案由：</label>
				<form:input path="summaryCause" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>依据法规：</label>
				<form:input path="clause" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>具体案由</th>
				<th>总体案由</th>
				<th>依据法规</th>
				<shiro:hasPermission name="penalty:penaltyRuleDict:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="penaltyRuleDict">
			<tr>
				<td><a href="${ctx}/penalty/penaltyRuleDict/form?id=${penaltyRuleDict.id}">
					${penaltyRuleDict.cause}
				</a></td>
				<td>
					${penaltyRuleDict.summaryCause}
				</td>
				<td>
					${penaltyRuleDict.clause}
				</td>
				<shiro:hasPermission name="penalty:penaltyRuleDict:edit"><td>
    				<a href="${ctx}/penalty/penaltyRuleDict/form?id=${penaltyRuleDict.id}">修改</a>
					<a href="${ctx}/penalty/penaltyRuleDict/delete?id=${penaltyRuleDict.id}" onclick="return confirmx('确认要删除该违章字典吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>