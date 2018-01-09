<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡航水域管理</title>
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
		<li class="active"><a href="${ctx}/oa/oaCruisearea/">巡航水域列表</a></li>
		<shiro:hasPermission name="oa:oaCruisearea:edit"><li><a href="${ctx}/oa/oaCruisearea/form">巡航水域添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaCruisearea" action="${ctx}/oa/oaCruisearea/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>所属部门：</label>
				<sys:treeselect id="office" name="office.id" value="${oaCruisearea.office.id}" labelName="office.name" labelValue="${oaCruisearea.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>所属种类：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('cruise_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>所属网格：</label>
				<form:select path="grid" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('grid')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>所属部门</th>
				<th>具体内容</th>
				<th>所属种类</th>
				<th>所属网格</th>
				<shiro:hasPermission name="oa:oaCruisearea:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaCruisearea">
			<tr>
				<td><a href="${ctx}/oa/oaCruisearea/form?id=${oaCruisearea.id}">
					${oaCruisearea.office.name}
				</a></td>
				<td>
					${oaCruisearea.content}
				</td>
				<td>
					${fns:getDictLabel(oaCruisearea.type, 'cruise_type', '')}
				</td>
				<td>
					${fns:getDictLabel(oaCruisearea.grid, 'grid', '')}
				</td>
				<shiro:hasPermission name="oa:oaCruisearea:edit"><td>
    				<a href="${ctx}/oa/oaCruisearea/form?id=${oaCruisearea.id}">修改</a>
					<a href="${ctx}/oa/oaCruisearea/delete?id=${oaCruisearea.id}" onclick="return confirmx('确认要删除该巡航水域吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>