<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>周巡航计划管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/cruise/cruiseWeekPlan/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctx}/cruise/cruiseWeekPlan/")
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
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
		<li class="active"><a href="${ctx}/cruise/cruiseWeekPlan/">周巡航计划列表</a></li>
		<shiro:hasPermission name="cruise:cruiseWeekPlan:edit"><li><a href="${ctx}/cruise/cruiseWeekPlan/form">周巡航计划添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="cruiseWeekPlan" action="${ctx}/cruise/cruiseWeekPlan/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>导出日期：</label>
				<input name="happenDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cruiseWeekPlan.happenDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>开始日期</th>
				<th>结束日期</th>
				<th>海巡艇</th>
				<th>巡航网格</th>
				<th>巡航时间</th>
				<th>重点部件</th>
				<th>巡航工作重点</th>
				<shiro:hasPermission name="cruise:cruiseWeekPlan:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cruiseWeekPlan">
			<tr>
				<td><a href="${ctx}/cruise/cruiseWeekPlan/form?id=${cruiseWeekPlan.id}">
					<fmt:formatDate value="${cruiseWeekPlan.startDate}" pattern="yyyy-MM-dd"/>
				</a></td>
				<td>
					<fmt:formatDate value="${cruiseWeekPlan.endDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${cruiseWeekPlan.office.name}
				</td>
				<td>
					${cruiseWeekPlan.cruiseGrid}
				</td>
				<td>
					${cruiseWeekPlan.cruiseTime}
				</td>
				<td>
					${cruiseWeekPlan.importantPos}
				</td>
				<td>
					${cruiseWeekPlan.importantContent}
				</td>
				<shiro:hasPermission name="cruise:cruiseWeekPlan:edit"><td>
    				<a href="${ctx}/cruise/cruiseWeekPlan/form?id=${cruiseWeekPlan.id}">修改</a>
					<a href="${ctx}/cruise/cruiseWeekPlan/delete?id=${cruiseWeekPlan.id}" onclick="return confirmx('确认要删除该周巡航计划吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>