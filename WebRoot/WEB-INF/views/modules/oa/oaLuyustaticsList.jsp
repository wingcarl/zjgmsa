<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>陆域巡查数据管理</title>
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
		<li class="active"><a href="${ctx}/oa/oaLuyustatics/">陆域巡查数据列表</a></li>
		<shiro:hasPermission name="oa:oaLuyustatics:edit"><li><a href="${ctx}/oa/oaLuyustatics/form">陆域巡查数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaLuyustatics" action="${ctx}/oa/oaLuyustatics/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>发现部门：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${oaLuyustatics.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${oaLuyustatics.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>创建时间：</label>
				<input name="beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${oaLuyustatics.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${oaLuyustatics.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>添加时间</th>
				<th>部门</th>
				<th>参加巡查<br>人数</th>
				<th>出动执法<br>车辆</th>
				<th>检查船舶</th>
				<th>客汽渡码头<br>渡运区域</th>
				<th>危险品码头</th>
				<th>水工作业区</th>
				<th>锚地\停泊区</th>
				<th>其他码头</th>
				<th>查处违法行为</th>
				<th>发现存在问题<br>或不足</th>
				<th>实施<br>行政处罚</th>
				<th>向指挥中心<br>反馈</th>
				<shiro:hasPermission name="oa:oaLuyustatics:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaLuyustatics">
			<tr>
				<td><a href="${ctx}/oa/oaLuyustatics/form?id=${oaLuyustatics.id}">
					<fmt:formatDate value="${oaLuyustatics.updateDate}" pattern="yyyy年MM月"/>
				</a></td>
				<td>
				    ${oaLuyustatics.createBy.office.name}
				</td>
				<td>
					${oaLuyustatics.cyxcrs}
				</td>
				<td>
					${oaLuyustatics.cdzfcl}
				</td>
				<td>
					${oaLuyustatics.jccb}
				</td>
				<td>
					${oaLuyustatics.dkmt}
				</td>
				<td>
					${oaLuyustatics.wxpmt}
				</td>
				<td>
					${oaLuyustatics.sgzyq}
				</td>
				<td>
					${oaLuyustatics.mdtbq}
				</td>
				<td>
					${oaLuyustatics.qtmt}
				</td>
				<td>
					${oaLuyustatics.ccwfxw}
				</td>
				<td>
					${oaLuyustatics.czwt}
				</td>
				<td>
					${oaLuyustatics.xzcf}
				</td>
				<td>
					${oaLuyustatics.zhzxfk}
				</td>
				<shiro:hasPermission name="oa:oaLuyustatics:edit"><td>
    				<a href="${ctx}/oa/oaLuyustatics/form?id=${oaLuyustatics.id}">修改</a>
					<a href="${ctx}/oa/oaLuyustatics/delete?id=${oaLuyustatics.id}" onclick="return confirmx('确认要删除该陆域巡查数据吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>