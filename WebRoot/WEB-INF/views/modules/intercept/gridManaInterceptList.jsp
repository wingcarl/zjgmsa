<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>拦截船舶情况管理</title>
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
				<form id="importForm" action="${ctx}/intercept/gridManaIntercept/import" method="post" enctype="multipart/form-data"
					class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
					<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
					<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
					<a href="${ctx}/intercept/gridManaIntercept/import/template">下载模板</a>
				</form>
			</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/intercept/gridManaIntercept/">拦截船舶情况列表</a></li>
		<shiro:hasPermission name="intercept:gridManaIntercept:edit"><li><a href="${ctx}/intercept/gridManaIntercept/form">拦截船舶情况添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gridManaIntercept" action="${ctx}/intercept/gridManaIntercept/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>拦截时间：</label>
				<input name="beginInterceptDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${gridManaIntercept.beginInterceptDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endInterceptDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${gridManaIntercept.endInterceptDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>警戒船：</label>
				<form:input path="shipName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>拦截船名：</label>
				<form:input path="interceptShipName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>拦截地点：</label>
				<form:input path="shipLocation" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th>拦截时间</th>
				<th>警戒船</th>
				<th>拦截船名</th>
				<th>拦截地点</th>
				<th>处理情况</th>
				<shiro:hasPermission name="intercept:gridManaIntercept:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gridManaIntercept">
			<tr>
				<td><a href="${ctx}/intercept/gridManaIntercept/form?id=${gridManaIntercept.id}">
					<fmt:formatDate value="${gridManaIntercept.interceptDate}" pattern="yyyy-MM-dd"/>
				</a></td>
				<td>
					${gridManaIntercept.shipName}
				</td>
				<td>
					${gridManaIntercept.interceptShipName}
				</td>
				<td>
					${gridManaIntercept.shipLocation}
				</td>
				<td>
					${gridManaIntercept.dealDetail}
				</td>
				<shiro:hasPermission name="intercept:gridManaIntercept:edit"><td>
    				<a href="${ctx}/intercept/gridManaIntercept/form?id=${gridManaIntercept.id}">修改</a>
					<a href="${ctx}/intercept/gridManaIntercept/delete?id=${gridManaIntercept.id}" onclick="return confirmx('确认要删除该拦截船舶情况吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>