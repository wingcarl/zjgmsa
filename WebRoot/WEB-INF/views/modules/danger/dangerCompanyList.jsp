<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>隐患排查对象信息管理</title>
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
		<li class="active"><a href="${ctx}/danger/dangerCompany/">隐患排查对象信息列表</a></li>
		<shiro:hasPermission name="danger:dangerCompany:edit"><li><a href="${ctx}/danger/dangerCompany/form">隐患排查对象信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dangerCompany" action="${ctx}/danger/dangerCompany/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>对象名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>对象类别：</label>
				<form:select path="type" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('company_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>所属部门：</label>
				<sys:treeselect id="office" name="office" value="${dangerCompany.office.id}" labelName="" labelValue="${dangerCompany.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>排查对象名称</th>
				<th>排查对象类别</th>
				<th>责任人</th>
				<th>联系电话</th>
				<th>所属部门</th>
				<th>备注信息</th>
				<th>更新者</th>
				<th>更新时间</th>
				<shiro:hasPermission name="danger:dangerCompany:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dangerCompany">
			<tr>
				<td><a href="${ctx}/danger/dangerCompany/form?id=${dangerCompany.id}">
					${dangerCompany.name}
				</a></td>
				
				<td>
					${fns:getDictLabel(dangerCompany.type, 'company_type', '')}
				</td>
				<td>
					${dangerCompany.leader}
				</td>
				<td>
					${dangerCompany.telephone}
				</td>
				<td>
					${dangerCompany.office.name}
				</td>
				<td>
					${dangerCompany.remarks}
				</td>
				<td>
					${dangerCompany.updateBy.name}
				</td>
				<td>
					<fmt:formatDate value="${dangerCompany.updateDate}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<shiro:hasPermission name="danger:dangerCompany:edit"><td>
    				<a href="${ctx}/danger/dangerCompany/form?id=${dangerCompany.id}">修改</a>
					<a href="${ctx}/danger/dangerCompany/delete?id=${dangerCompany.id}" onclick="return confirmx('确认要删除该隐患排查对象信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>