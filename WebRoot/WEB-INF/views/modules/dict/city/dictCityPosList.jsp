<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>城市经纬度字典管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#searchForm").attr("action","${ctx}/dict/city/dictCityPos/export");
					$("#searchForm").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
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
		<form id="importForm" action="${ctx}/dict/city/dictCityPos/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/dict/city/dictCityPos/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dict/city/dictCityPos/">城市经纬度字典列表</a></li>
		<shiro:hasPermission name="dict:city:dictCityPos:edit"><li><a href="${ctx}/dict/city/dictCityPos/form">城市经纬度字典添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dictCityPos" action="${ctx}/dict/city/dictCityPos/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>省份：</label>
				<form:input path="province" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>
			<li><label>地级市：</label>
				<form:input path="city" htmlEscape="false" maxlength="12" class="input-medium"/>
			</li>
			<li><label>区县：</label>
				<form:input path="county" htmlEscape="false" maxlength="12" class="input-medium"/>
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
				<th>省份</th>
				<th>地级市</th>
				<th>区县</th>
				<th>经度</th>
				<th>纬度</th>
				<shiro:hasPermission name="dict:city:dictCityPos:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dictCityPos">
			<tr>
				<td><a href="${ctx}/dict/city/dictCityPos/form?id=${dictCityPos.id}">
					${dictCityPos.province}
				</a></td>
				<td>
					${dictCityPos.city}
				</td>
				<td>
					${dictCityPos.county}
				</td>
				<td>
					${dictCityPos.longitude}
				</td>
				<td>
					${dictCityPos.latitude}
				</td>
				<shiro:hasPermission name="dict:city:dictCityPos:edit"><td>
    				<a href="${ctx}/dict/city/dictCityPos/form?id=${dictCityPos.id}">修改</a>
					<a href="${ctx}/dict/city/dictCityPos/delete?id=${dictCityPos.id}" onclick="return confirmx('确认要删除该城市经纬度字典吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>