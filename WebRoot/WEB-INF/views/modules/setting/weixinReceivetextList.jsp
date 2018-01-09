<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信接受消息管理</title>
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
		<li class="active"><a href="${ctx}/setting/weixinReceivetext/">微信接受消息列表</a></li>
		<shiro:hasPermission name="setting:weixinReceivetext:edit"><li><a href="${ctx}/setting/weixinReceivetext/form">微信接受消息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="weixinReceivetext" action="${ctx}/setting/weixinReceivetext/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>用户昵称：</label>
				<form:input path="nickname" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>内容</th>
				<th>时间</th>
				<th>open_id</th>
				<th>消息id</th>
				<th>类型</th>
				<th>回复内容</th>
				<th>是否回复</th>
				<th>接收方帐号</th>
				<th>公众号ID</th>
				<th>用户昵称</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="setting:weixinReceivetext:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinReceivetext">
			<tr>
				<td><a href="${ctx}/setting/weixinReceivetext/form?id=${weixinReceivetext.id}">
					${weixinReceivetext.content}
				</a></td>
				<td>
					<fmt:formatDate value="${weixinReceivetext.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${weixinReceivetext.fromusername}
				</td>
				<td>
					${weixinReceivetext.msgid}
				</td>
				<td>
					${weixinReceivetext.msgtype}
				</td>
				<td>
					${weixinReceivetext.rescontent}
				</td>
				<td>
					${weixinReceivetext.response}
				</td>
				<td>
					${weixinReceivetext.tousername}
				</td>
				<td>
					${weixinReceivetext.accountid}
				</td>
				<td>
					${weixinReceivetext.nickname}
				</td>
				<td>
					<fmt:formatDate value="${weixinReceivetext.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${weixinReceivetext.remarks}
				</td>
				<shiro:hasPermission name="setting:weixinReceivetext:edit"><td>
    				<a href="${ctx}/setting/weixinReceivetext/form?id=${weixinReceivetext.id}">修改</a>
					<a href="${ctx}/setting/weixinReceivetext/delete?id=${weixinReceivetext.id}" onclick="return confirmx('确认要删除该微信接受消息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>