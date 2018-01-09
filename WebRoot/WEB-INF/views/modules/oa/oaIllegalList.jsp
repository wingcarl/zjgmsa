<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>违法行为管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/oa/oaIllegal/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctx}/oa/oaInportShip/")
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oa/oaIllegal/">违法行为列表</a></li>
		<shiro:hasPermission name="oa:oaIllegal:edit"><li><a href="${ctx}/oa/oaIllegal/form">违法行为添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaIllegal" action="${ctx}/oa/oaIllegal/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>船名：</label>
				<form:input path="shipName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>发生日期：</label>
				<input name="beginHappenDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${oaIllegal.beginHappenDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endHappenDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${oaIllegal.endHappenDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			
				<th>发生地点</th>
				<th>违法行为</th>
				<th>船名</th>
				<th>发生日期</th>
				<th>处置结果</th>
				<shiro:hasPermission name="oa:oaIllegal:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaIllegal">
			<tr>
				
				<td>
					${oaIllegal.happenLocation}
				</td>
				<td>
					${fns:getDictLabel(oaIllegal.illegalDetail, 'illegal_detail', '')}
				</td>
				<td>
					${oaIllegal.shipName}
				</td>
				<td>
					<fmt:formatDate value="${oaIllegal.happenDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${fns:getDictLabel(oaIllegal.dealWay, 'deal_way', '')}
				</td>
				<shiro:hasPermission name="oa:oaIllegal:edit"><td>
    				<a href="${ctx}/oa/oaIllegal/form?id=${oaIllegal.id}">修改</a>
					<a href="${ctx}/oa/oaIllegal/delete?id=${oaIllegal.id}" onclick="return confirmx('确认要删除该违法行为吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>