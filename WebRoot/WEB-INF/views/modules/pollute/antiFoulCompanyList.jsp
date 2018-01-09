<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>防污染配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
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
<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/pollute/antiFoulCompany/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/pollute/antiFoulCompany/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pollute/antiFoulCompany/">防污染配置列表</a></li>
		<shiro:hasPermission name="pollute:antiFoulCompany:edit"><li><a href="${ctx}/pollute/antiFoulCompany/form">防污染配置添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="antiFoulCompany" action="${ctx}/pollute/antiFoulCompany/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>企业名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>作业类别：</label>
				<form:select path="foulType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pollutant_work_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>更新时间</th>
				<th>企业名称</th>
				<th>防污染类别</th>
				<shiro:hasPermission name="pollute:antiFoulCompany:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="antiFoulCompany">
			<tr>
				<td><a href="${ctx}/pollute/antiFoulCompany/form?id=${antiFoulCompany.id}">
					<fmt:formatDate value="${antiFoulCompany.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${antiFoulCompany.name}
				</td>
				<td>
					${fns:getDictLabel(antiFoulCompany.foulType, 'pollutant_work_type', '')}
				</td>
				<shiro:hasPermission name="pollute:antiFoulCompany:edit"><td>
    				<a href="${ctx}/pollute/antiFoulCompany/form?id=${antiFoulCompany.id}">修改</a>
					<a href="${ctx}/pollute/antiFoulCompany/delete?id=${antiFoulCompany.id}" onclick="return confirmx('确认要删除该防污染配置吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>