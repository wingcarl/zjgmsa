<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流量观测尺度数据管理</title>
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
				<form id="importForm" action="${ctx}/flow/flowMeasureScale/import" method="post" enctype="multipart/form-data"
					class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
					<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
					<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
					<a href="${ctx}/flow/flowMeasureScale/import/template">下载模板</a>
				</form>
</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/flow/flowMeasureScale/">流量观测尺度数据列表</a></li>
		<shiro:hasPermission name="flow:flowMeasureScale:edit"><li><a href="${ctx}/flow/flowMeasureScale/form">流量观测尺度数据添加</a></li></shiro:hasPermission>
		<li><a href="${ctx}/flow/flowMeasureScale/analysis">流量观测尺度数据分析</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="flowMeasureScale" action="${ctx}/flow/flowMeasureScale/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>断面名称：</label>
				<form:input path="sectionName" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th>断面名称</th>
				<th>当量合计</th>
				<th>合计</th>
				<th>上行船<br>舶小计</th>
				<th>上行<br>（小于30米）</th>
				<th>上行<br>（30-50米）</th>
				<th>上行<br>（50-90米）</th>
				<th>上行<br>（90-180米）</th>
				<th>上行<br>（大于米）</th>
				<th>下行船<br>舶小计</th>
				<th>下行<br>（小于30米）</th>
				<th>下行<br>（30-50米）</th>
				<th>下行<br>（50-90米）</th>
				<th>下行<br>（90-180米）</th>
				<th>下行<br>（大于180米）</th>
				<th>观测日期</th>
				<shiro:hasPermission name="flow:flowMeasureScale:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="flowMeasureScale">
			<tr>
				<td><a href="${ctx}/flow/flowMeasureScale/form?id=${flowMeasureScale.id}">
					${flowMeasureScale.sectionName}
				</a></td>
				<td>
					${flowMeasureScale.equivalentWeight}
				</td>
				<td>
					${flowMeasureScale.total}
				</td>
				<td>
					${flowMeasureScale.upTotal}
				</td>
				<td>
					${flowMeasureScale.upLess30}
				</td>
				<td>
					${flowMeasureScale.upBetween3050}
				</td>
				<td>
					${flowMeasureScale.upBetween5090}
				</td>
				<td>
					${flowMeasureScale.upBetween90180}
				</td>
				<td>
					${flowMeasureScale.upMore180}
				</td>
				<td>
					${flowMeasureScale.downTotal}
				</td>
				<td>
					${flowMeasureScale.downLess30}
				</td>
				<td>
					${flowMeasureScale.downBetween3050}
				</td>
				<td>
					${flowMeasureScale.downBetween5090}
				</td>
				<td>
					${flowMeasureScale.downBetween90180}
				</td>
				<td>
					${flowMeasureScale.downMore180}
				</td>
				<td>
					<fmt:formatDate value="${flowMeasureScale.flowMeasureDate}" pattern="yyyy年M月"/>
				</td>
				<shiro:hasPermission name="flow:flowMeasureScale:edit"><td>
    				<a href="${ctx}/flow/flowMeasureScale/form?id=${flowMeasureScale.id}">修改</a>
					<a href="${ctx}/flow/flowMeasureScale/delete?id=${flowMeasureScale.id}" onclick="return confirmx('确认要删除该流量观测尺度数据吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>