<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>沪通大桥水工动态表管理</title>
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
		<li class="active"><a href="${ctx}/msa/msaHutongProject/">沪通大桥水工动态表列表</a></li>
		<shiro:hasPermission name="msa:msaHutongProject:edit"><li><a href="${ctx}/msa/msaHutongProject/form">沪通大桥水工动态表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="msaHutongProject" action="${ctx}/msa/msaHutongProject/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>更新时间：</label>
				<input name="beginUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${msaHutongProject.beginUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endUpdateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${msaHutongProject.endUpdateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>更新者</th>
				<th>更新时间</th>
				<th>沪通大桥最新水工动态</th>
				<shiro:hasPermission name="msa:msaHutongProject:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="msaHutongProject">
			<tr>
				<td><a href="${ctx}/msa/msaHutongProject/form?id=${msaHutongProject.id}">
					${msaHutongProject.updateBy.name}
				</a></td>
				<td>
					<fmt:formatDate value="${msaHutongProject.updateDate}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
					${msaHutongProject.content}
				</td>
				<shiro:hasPermission name="msa:msaHutongProject:edit"><td>
    				<a href="${ctx}/msa/msaHutongProject/form?id=${msaHutongProject.id}">修改</a>
					<a href="${ctx}/msa/msaHutongProject/delete?id=${msaHutongProject.id}" onclick="return confirmx('确认要删除该沪通大桥水工动态表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>