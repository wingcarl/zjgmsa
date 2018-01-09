<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>拦截船舶情况管理</title>
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
		<li><a href="${ctx}/intercept/gridManaIntercept/listView?grid=${grid}">${grid}号网格工作数据汇总</a></li>
		<li  class="active"><a href="${ctx}/intercept/gridManaIntercept/listDetail?grid=${grid}">${grid}号网格拦截船舶数据清单</a></li>
		<li><a href="${ctx}/oa/oaCruisedata/list1?grid=${grid}">${grid}号网格详细工作数据</a></li>
		
	</ul>
	
	<form:form id="searchForm" modelAttribute="gridManaIntercept" action="${ctx}/intercept/gridManaIntercept/listDetail?grid=${grid}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>时间段：</label>
				<input name="beginInterceptDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${gridManaIntercept.beginInterceptDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/> - 
				<input name="endInterceptDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${gridManaIntercept.endInterceptDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>拦截船名：</label>
				<form:input path="interceptShipName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>拦截地点：</label>
				<form:input path="shipLocation" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>拦截时间</th>
				<th>拦截船名</th>
				<th>拦截地点</th>
				<th>处理情况</th>
			</tr>
			
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gridManaIntercept">
			<tr>
				<td><a href="${ctx}/intercept/gridManaIntercept/form?id=${gridManaIntercept.id}">
					<fmt:formatDate value="${gridManaIntercept.interceptDate}" pattern="yyyy-MM-dd"/>
				</a></td>
				
				<td>
					${gridManaIntercept.interceptShipName}
				</td>
				<td>
					${gridManaIntercept.shipLocation}
				</td>
				<td>
					${gridManaIntercept.dealDetail}
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>