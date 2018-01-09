<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>二程船详细信息表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var trs = $('tr');
			console.log(trs);
			for(var i=0;i<trs.length;i++){
				
				if(trs[i].innerText.indexOf('待办理')!=-1)
					$(trs[i]).css('color','red');
			}
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function archive(url){
			$.ajax({
    	        type: "get",
    	        dataType: "json",
    	        url: url,
    	        success: function (data) {
    	        	$('#searchForm').submit();
    	        }	
    	        });
			
		}
	</script>
	<style>
		
	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/affair/affairTransferDetail/">二程船详细信息表列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="affairTransferDetail" action="${ctx}/affair/affairTransferDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>一程船船名：</label>
				<form:input path="second.firstId.firstShipName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>靠泊码头：</label>
				<form:input path="second.firstId.firstUnloadPort" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>中转目的地：</label>
				<form:input path="second.destination" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>二程船名：</label>
				<form:input path="shipName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>一程船名</th>
				<th>靠泊码头</th>
				<th>中转目的地</th>
				<th>二程船名</th>
				<th>操作员</th>
				<th>审核日期</th>
				<th>所属海事处</th>
				<th>海事处办理状态</th>
				<shiro:hasPermission name="affair:affairTransferDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		
				<c:forEach items="${page.list}" var="detail">
			<tr>
				<td><a target="_blank" href="${ctx}/affair/affairTransferDetail/form?id=${detail.second.firstId.id}&secondId=${detail.second.id}">
					${detail.second.firstId.firstShipName}
				</a></td>
				
				<td>
					${detail.second.firstId.firstUnloadPort}
				</td>
				<td>
					${detail.second.destination}
				</td>
				<td>
					${detail.shipName}
				</td>
				<td>
					${detail.createBy.name}
				</td>
				
				<td>
					<fmt:formatDate value="${detail.auditDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${detail.second.firstId.office.name}
				</td>
				<td>
					${detail.isArchive}
				</td>
				<shiro:hasPermission name="affair:affairTransferDetail:edit"><td>
    				<a target="_blank" href="${ctx}/affair/affairTransferDetail/form?id=${detail.second.firstId.id}&secondId=${detail.second.id}">查看</a>
					<a onclick=archive("${ctx}/affair/affairTransferDetail/deal?sid=${detail.id}"); href="javascript:void(0);">办理</a>
					<a onclick=archive('${ctx}/affair/affairTransferDetail/deal?sid=${detail.id}&archive=1'); href="javascript:void(0);">撤回</a>

				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>