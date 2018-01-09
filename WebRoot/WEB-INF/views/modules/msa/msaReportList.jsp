<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>政务信息管理</title>
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
		<li class="active"><a href="${ctx}/msa/msaReport/">政务信息列表</a></li>
		<shiro:hasPermission name="msa:msaReport:edit"><li><a href="${ctx}/msa/msaReport/form">政务信息添加</a></li></shiro:hasPermission>
		<li><a href="${ctx}/msa/msaReport/stat">政务信息统计</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="msaReport" action="${ctx}/msa/msaReport/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标题：</label>
				<form:input path="title" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>作者：</label>
				<form:input path="author" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>部门：</label>
				<sys:treeselect id="depid" name="depid" value="${msaReport.depid.id}" labelName="" labelValue="${depName}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" />
			</li>
			<li><label>发表日期：</label>
				<input name="beginPublicDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${msaReport.beginPublicDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endPublicDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${msaReport.endPublicDate}" pattern="yyyy-MM-dd"/>"
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
				<th>标题</th>
				<th>作者</th>
				<th>发表类别</th>
				<th>部门</th>
				<th>发表日期</th>
				<shiro:hasPermission name="msa:msaReport:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="msaReport">
			<tr>
				<td><a href="${msaReport.url}" target="_blank">
					${msaReport.title}
				</a></td>
				<td>
					${msaReport.author}
				</td>
				
				<td>
					<c:forEach items="${msaReport.type.split(\",\")}" var="type">
						${fns:getDictLabel(type, 'msa_report_type', '')}/
					</c:forEach>
				</td>
				<td>
					${msaReport.depid}
				</td>
				<td>
					<fmt:formatDate value="${msaReport.publicDate}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="msa:msaReport:edit"><td>
    				<a href="${ctx}/msa/msaReport/form?id=${msaReport.id}">修改</a>
					<a href="${ctx}/msa/msaReport/delete?id=${msaReport.id}" onclick="return confirmx('确认要删除该政务信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>