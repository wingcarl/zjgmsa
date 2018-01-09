<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>电子巡查数据管理</title>
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
		<li class="active"><a href="${ctx}/oa/oaDigitalcruise/">电子巡查数据列表</a></li>
		<shiro:hasPermission name="oa:oaDigitalcruise:edit"><li><a href="${ctx}/oa/oaDigitalcruise/form">电子巡查数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaDigitalcruise" action="${ctx}/oa/oaDigitalcruise/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
				<li><label>巡查日期：</label>
				<input id="beginHappenDate" name="beginHappenDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${oaCruiseStat.beginHappenDate}" pattern="yyyy-MM-dd"/>"
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
				<th>部   门   名    称</th>
				<th>数据添加日期</th>
				<th>巡航人员</th>
				<th>巡航区域</th>
				<th>发现解决的问题</th>
				<th>巡航次数</th>
				<th>巡航时间</th>
				<th>发现违章数量</th>
				<th>填表人</th>
				<shiro:hasPermission name="oa:oaDigitalcruise:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaDigitalcruise">
			<tr>
				<td>
				<a href="${ctx}/oa/oaDigitalcruise/formSeen?id=${oaDigitalcruise.id}">
					${oaDigitalcruise.createBy.office.name}
				</a>
				</td>
				<td>
				<fmt:formatDate value="${oaDigitalcruise.createDate}" pattern="yyyy-MM-dd"/>
					
				</td>
				<td>
					${oaDigitalcruise.cruisePeople}
				</td>
				
				<td>
					${oaDigitalcruise.cruiseArea}
				</td>
				<td>
					${oaDigitalcruise.solveProblem}
				</td>
				<td>
					${oaDigitalcruise.cruiseTimes}
				</td>
				<td>
					${oaDigitalcruise.cruiseTotalTime}
				</td>
				<td>
					${oaDigitalcruise.illegalCount}
				</td>
				<td>
					${oaDigitalcruise.createBy.name}
				</td>
				<shiro:hasPermission name="oa:oaDigitalcruise:edit"><td>
					<c:if test="${oaDigitalcruise.editable}">
	    				<a href="${ctx}/oa/oaDigitalcruise/form?id=${oaDigitalcruise.id}">修改</a>
						<a href="${ctx}/oa/oaDigitalcruise/delete?id=${oaDigitalcruise.id}" onclick="return confirmx('确认要删除该电子巡查数据吗？', this.href)">删除</a>
					</c:if>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>