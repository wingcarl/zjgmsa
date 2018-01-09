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
		<li class="active"><a href="${ctx}/intercept/gridManaIntercept/listView?grid=${grid}">${grid}号网格工作数据汇总</a></li>
		<li><a href="${ctx}/intercept/gridManaIntercept/listDetail?grid=${grid}">${grid}号网格拦截船舶数据清单</a></li>
		<li><a href="${ctx}/oa/oaCruisedata/list1?grid=${grid}" >${grid}号网格详细工作数据</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="gridManaIntercept" action="${ctx}/intercept/gridManaIntercept/listView?grid=${grid}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="grid" name="grid" type="hidden" value="${grid}"/>
		
		<ul class="ul-form">
			<li><label>时间段：</label>
				<input name="beginInterceptDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${gridManaIntercept.beginInterceptDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/> - 
				<input name="endInterceptDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${gridManaIntercept.endInterceptDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>数据项目</th>
				<th>单位</th>
				<th>数值</th>
				
				
			</tr>
			
			
		</thead>
		<tbody>
		<tr>
			<tr>
				<td>巡航艘次</td>
				<td>次数</td>
				<td>${stat.xhcs }</td>
			</tr>
			<tr>
				<td>巡航时长</td>
				<td>小时</td>
				<td>${stat.totalTime}</td>
			</tr>
			<tr>
				<td>出动执法人员数量</td>
				<td>次数</td>
				<td>${stat.cdzfry }</td>
			</tr>
			<tr>
				<td>拦截船舶数量</td>
				<td>次数</td>
				<td>${counter}</td>
			</tr>
				
				
				
		</tr>
		</tbody>
	</table>
	
</body>
</html>