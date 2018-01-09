<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>船籍港字典信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/dict/port/dictRegistryPort/export");
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
		<form id="importForm" action="${ctx}/dict/port/dictRegistryPort/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/dict/port/dictRegistryPort/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dict/port/dictRegistryPort/">船籍港字典信息列表</a></li>
		<shiro:hasPermission name="dict:port:dictRegistryPort:edit"><li><a href="${ctx}/dict/port/dictRegistryPort/form">船籍港字典信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dictRegistryPort" action="${ctx}/dict/port/dictRegistryPort/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>船籍港名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="24" class="input-medium"/>
			</li>
			<li><label>所在省份：</label>
				<form:input path="province" htmlEscape="false" maxlength="11" class="input-medium"/>
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
				<th>船籍港编号</th>
				<th>船籍港名称</th>
				<th>船籍港英文</th>
				<th>所在省份</th>
				<shiro:hasPermission name="dict:port:dictRegistryPort:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dictRegistryPort">
			<tr>
				<td><a href="${ctx}/dict/port/dictRegistryPort/form?id=${dictRegistryPort.id}">
					${dictRegistryPort.code}
				</a></td>
				<td>
					${dictRegistryPort.name}
				</td>
				<td>
					${dictRegistryPort.pinyin}
				</td>
				<td>
					${dictRegistryPort.province}
				</td>
				<shiro:hasPermission name="dict:port:dictRegistryPort:edit"><td>
    				<a href="${ctx}/dict/port/dictRegistryPort/form?id=${dictRegistryPort.id}">修改</a>
					<a href="${ctx}/dict/port/dictRegistryPort/delete?id=${dictRegistryPort.id}" onclick="return confirmx('确认要删除该船籍港字典信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>