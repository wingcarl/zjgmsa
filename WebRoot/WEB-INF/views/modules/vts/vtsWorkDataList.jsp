<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>VTS工作数据管理</title>
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
	<style>
		th{width:5%;}
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/vts/vtsWorkData/">VTS工作数据列表</a></li>
		<shiro:hasPermission name="vts:vtsWorkData:edit"><li><a href="${ctx}/vts/vtsWorkData/form">VTS工作数据添加</a></li></shiro:hasPermission>
		<shiro:hasPermission name="vts:vtsWorkData:edit"><li><a href="${ctx}/vts/vtsWorkData/sum">VTS工作数据统计</a></li></shiro:hasPermission>
		
	</ul>
	<form:form id="searchForm" modelAttribute="vtsWorkData" action="${ctx}/vts/vtsWorkData/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${vtsWorkData.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${vtsWorkData.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th style="width:10%">创建<br>时间</th>
				<th>到港<br>受限船舶</th>
				<th>过境<br>受限船舶</th>
				<th>接受<br>船位报告</th>
				<th>重点<br>监控船舶</th>
				<th>船舶<br>跟踪</th>
				<th>超大<br>型船舶</th>
				<th>四客<br>一危</th>
				<th>信息<br>服务</th>
				<th>交通<br>组织服务</th>
				<th>助航<br>服务</th>
				<th>避免<br>险情</th>
				<th>执法<br>联动</th>
				<th>锚泊<br>船监控</th>
				<th>违章<br>纠正</th>
				<th style="width:8%">创建人</th>
				<shiro:hasPermission name="vts:vtsWorkData:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="vtsWorkData">
			<tr>
			
				<td>
				<a href="${ctx}/vts/vtsWorkData/form?id=${vtsWorkData.id}">
					<fmt:formatDate value="${vtsWorkData.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				
				</a></td>
				<td>
					${vtsWorkData.inportLimitShip}
				</td>
				<td>
					${vtsWorkData.transitLimitShip}
				</td>
				<td>
					${vtsWorkData.positionReport}
				</td>
				<td>
					${vtsWorkData.importantShip}
				</td>
				<td>
					${vtsWorkData.shipFollow}
				</td>
				<td>
					${vtsWorkData.giantShip}
				</td>
				<td>
					${vtsWorkData.fourPassenger}
				</td>
				<td>
					${vtsWorkData.informationService}
				</td>
				<td>
					${vtsWorkData.trafficOperation}
				</td>
				<td>
					${vtsWorkData.navaid}
				</td>
				<td>
					${vtsWorkData.avoidDanger}
				</td>
				<td>
					${vtsWorkData.enforceUnion}
				</td>
				<td>
					${vtsWorkData.mooringSpying}
				</td>
				<td>
					${vtsWorkData.illegal}
				</td>
				<td><a href="${ctx}/vts/vtsWorkData/form?id=${vtsWorkData.id}">
					${vtsWorkData.createBy.name}
				</a></td>
				<shiro:hasPermission name="vts:vtsWorkData:edit"><td>
    				<a href="${ctx}/vts/vtsWorkData/form?id=${vtsWorkData.id}">修改</a>
					<a href="${ctx}/vts/vtsWorkData/delete?id=${vtsWorkData.id}" onclick="return confirmx('确认要删除该VTS工作数据吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>