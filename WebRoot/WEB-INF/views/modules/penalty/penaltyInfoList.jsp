<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>违章数据管理</title>
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
		<li class="active"><a href="${ctx}/penalty/penaltyInfo/">违章数据列表</a></li>
		<shiro:hasPermission name="penalty:penaltyInfo:edit"><li><a href="${ctx}/penalty/penaltyInfo/form">违章数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="penaltyInfo" action="${ctx}/penalty/penaltyInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${penaltyInfo.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${penaltyInfo.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>更新时间</th>
				<th>船名</th>
				<th>船籍港</th>
				<th>船舶种类</th>
				<th>船舶总吨</th>
				<th>主机功率</th>
				<th>船舶长度</th>
				<th>船舶登记号码</th>
				<shiro:hasPermission name="penalty:penaltyInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="penaltyInfo">
			<tr>
				<td><a href="${ctx}/penalty/penaltyInfo/form?id=${penaltyInfo.id}">
					<fmt:formatDate value="${penaltyInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${penaltyInfo.shipName}
				</td>
				<td>
					${penaltyInfo.shipPort}
				</td>
				<td>
					${penaltyInfo.shipType}
				</td>
				<td>
					${penaltyInfo.shipLoad}
				</td>
				<td>
					${penaltyInfo.shipPower}
				</td>
				<td>
					${penaltyInfo.shipLength}
				</td>
				<td>
					${penaltyInfo.shipRegist}
				</td>
				<shiro:hasPermission name="penalty:penaltyInfo:edit"><td>
    				<a href="${ctx}/penalty/penaltyInfo/form?id=${penaltyInfo.id}">修改</a>
					<a href="${ctx}/penalty/penaltyInfo/delete?id=${penaltyInfo.id}" onclick="return confirmx('确认要删除该违章数据吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>