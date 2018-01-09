<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>处罚初始信息收集表管理</title>
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
		<li class="active"><a href="${ctx}/penalty/penaltyRegist/">处罚初始信息收集表列表</a></li>
		<shiro:hasPermission name="penalty:penaltyRegist:edit"><li><a href="${ctx}/penalty/penaltyRegist/form">处罚初始信息收集表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="penaltyRegist" action="${ctx}/penalty/penaltyRegist/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>发现日期：</label>
				<input name="beginFindDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${penaltyRegist.beginFindDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endFindDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${penaltyRegist.endFindDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>船名</th>
				<th>违法行为情况</th>
				<th>发现人员</th>
				<th>发现日期</th>
				<th>文书调阅情况</th>
				<th>联系方式</th>
				<th>处理状态</th>
				<shiro:hasPermission name="penalty:penaltyRegist:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="penaltyRegist">
			<tr>
				
				<td><a href="${ctx}/penalty/penaltyRegist/form?id=${penaltyRegist.id}">
					${penaltyRegist.shipName}
				</a></td>
				<td>
					${penaltyRegist.illegalDetail}
				</td>
				<td>
					${penaltyRegist.officer}
				</td>
				<td>
					<fmt:formatDate value="${penaltyRegist.findDate}" pattern="yyyy-MM-dd HHmm"/>
				</td>
				<td>
					${penaltyRegist.certificateGetDetail}
				</td>
				<td>
					${penaltyRegist.telephone}
				</td>
				<td>
					待处罚
				</td>
				<shiro:hasPermission name="penalty:penaltyRegist:edit"><td>
    				<a href="${ctx}/penalty/penaltyInfo/form?infoId=${penaltyRegist.id}">处理</a>
    				<a href="${ctx}/penalty/penaltyRegist/form?id=${penaltyRegist.id}">修改</a>
					<a href="${ctx}/penalty/penaltyRegist/delete?id=${penaltyRegist.id}" onclick="return confirmx('确认要删除该处罚初始信息收集表吗？', this.href)">删除</a>
				
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>