<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>停航检修计划管理</title>
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
		<li class="active"><a href="${ctx}/cruise/cruiseStopPlan/">停航检修计划列表</a></li>
		<shiro:hasPermission name="cruise:cruiseStopPlan:edit"><li><a href="${ctx}/cruise/cruiseStopPlan/form">停航检修计划添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cruiseStopPlan" action="${ctx}/cruise/cruiseStopPlan/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>检修日期：</label>
				<input name="beginStopDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cruiseStopPlan.beginStopDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endStopDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cruiseStopPlan.endStopDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li><label>停航海巡艇：</label>
				<sys:treeselect id="office" name="office.id" value="${cruiseStopPlan.office.id}" labelName="" labelValue="${cruiseStopPlan.office.name}"
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
				<th>月份</th>
				<th>停航海巡艇</th>
				<th>停航检修日期</th>
				<th>操作人</th>
				<shiro:hasPermission name="cruise:cruiseStopPlan:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cruiseStopPlan">
			<tr>
				<td>
					<fmt:formatDate value="${cruiseStopPlan.stopDate}" pattern="M月"/>
				</td>
				<td>
					${cruiseStopPlan.office.name}
				</td>
				<td>
					<fmt:formatDate value="${cruiseStopPlan.stopDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${cruiseStopPlan.createBy.name}
				</td>
				<shiro:hasPermission name="cruise:cruiseStopPlan:edit"><td>
    				<a href="${ctx}/cruise/cruiseStopPlan/form?id=${cruiseStopPlan.id}">修改</a>
					<a href="${ctx}/cruise/cruiseStopPlan/delete?id=${cruiseStopPlan.id}" onclick="return confirmx('确认要删除该停航检修计划吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>