<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡航数据管理</title>
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
		<li class="active"><a href="${ctx}/test/oaMulCruisedata/">巡航数据列表</a></li>
		<shiro:hasPermission name="test:oaMulCruisedata:edit"><li><a href="${ctx}/test/oaMulCruisedata/form">巡航数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaMulCruisedata" action="${ctx}/test/oaMulCruisedata/" method="post" class="breadcrumb form-search">
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
				<th>zyhl</th>
				<th>mdtbq</th>
				<th>sgzyq</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>wfhxgd</th>
				<th>wfsgzy</th>
				<th>kymt</th>
				<th>zhbzyc</th>
				<th>yhcs</th>
				<th>pnq</th>
				<th>wfyzcs</th>
				<th>psc</th>
				<th>danger_port</th>
				<th>jtzz</th>
				<th>ffxczl</th>
				<th>qbq</th>
				<th>cyjz</th>
				<th>fxwz</th>
				<th>clwz</th>
				<th>qsk</th>
				<th>wfpf</th>
				<shiro:hasPermission name="test:oaMulCruisedata:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaMulCruisedata">
			<tr>
				<td><a href="${ctx}/test/oaMulCruisedata/form?id=${oaMulCruisedata.id}">
					${oaMulCruisedata.zyhl}
				</a></td>
				<td>
					${oaMulCruisedata.mdtbq}
				</td>
				<td>
					${oaMulCruisedata.sgzyq}
				</td>
				<td>
					${oaMulCruisedata.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${oaMulCruisedata.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${oaMulCruisedata.wfhxgd}
				</td>
				<td>
					${oaMulCruisedata.wfsgzy}
				</td>
				<td>
					${oaMulCruisedata.kymt}
				</td>
				<td>
					${oaMulCruisedata.zhbzyc}
				</td>
				<td>
					${oaMulCruisedata.yhcs}
				</td>
				<td>
					${oaMulCruisedata.pnq}
				</td>
				<td>
					${oaMulCruisedata.wfyzcs}
				</td>
				<td>
					${oaMulCruisedata.psc}
				</td>
				<td>
					${oaMulCruisedata.dangerPort}
				</td>
				<td>
					${oaMulCruisedata.jtzz}
				</td>
				<td>
					${oaMulCruisedata.ffxczl}
				</td>
				<td>
					${oaMulCruisedata.qbq}
				</td>
				<td>
					${oaMulCruisedata.cyjz}
				</td>
				<td>
					${oaMulCruisedata.fxwz}
				</td>
				<td>
					${oaMulCruisedata.clwz}
				</td>
				<td>
					${oaMulCruisedata.qsk}
				</td>
				<td>
					${oaMulCruisedata.wfpf}
				</td>
				<shiro:hasPermission name="test:oaMulCruisedata:edit"><td>
    				<a href="${ctx}/test/oaMulCruisedata/form?id=${oaMulCruisedata.id}">修改</a>
					<a href="${ctx}/test/oaMulCruisedata/delete?id=${oaMulCruisedata.id}" onclick="return confirmx('确认要删除该巡航数据吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>