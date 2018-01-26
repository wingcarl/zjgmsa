<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>春运专项数据管理</title>
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
		<li class="active"><a href="${ctx}/spring/springData/">春运专项数据列表</a></li>
		<shiro:hasPermission name="spring:springData:edit"><li><a href="${ctx}/spring/springData/form">春运专项数据添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="springData" action="${ctx}/spring/springData/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>创建部门：</label>
				<sys:treeselect id="createOffice" name="createOffice" value="${springData.createOffice.id}" labelName="" labelValue="${springData.createOffice.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li>
			<li><label>录入时间：</label>
				<input name="beginInputDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${springData.beginInputDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endInputDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${springData.endInputDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
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
				<th>创建部门</th>
				<th>录入时间</th>
				<th>船舶数</th>
				<th>客位数</th>
				<th>完成客运量</th>
				<th>运送车辆</th>
				<th>重特大安<br>全事故次数</th>
				<th>伤</th>
				<th>亡</th>
				<th>失踪</th>
				<th>参加执<br>法人员数</th>
				<th>出动执<br>法车辆</th>
				<th>出动执<br>法船艇</th>
				<th>海巡艇<br>巡航时间</th>
				<th>海巡艇<br>巡航里程</th>
				<th>查处违<br>章行为</th>
				<th>辖区事<br>故险情</th>
				<th>发放宣<br>传材料</th>
				<th>发布安<br>全信息</th>
			
				<shiro:hasPermission name="spring:springData:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="springData">
			<tr>
				<td><a href="${ctx}/spring/springData/form?id=${springData.id}">
					${springData.createOffice.name}
				</a></td>
				<td>
					<fmt:formatDate value="${springData.inputDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${springData.shipNum}
				</td>
				<td>
					${springData.shipPersonNum}
				</td>
				
				<td>
					${springData.personNum}
				</td>
				
				<td>
					${springData.carNum}
				</td>
				
				<td>
					${springData.accidentNum}
				</td>
				<td>
					${springData.injuryNum}
				</td>
				<td>
					${springData.deadNum}
				</td>
				<td>
					${springData.missingNum}
				</td>
				<td>
					${springData.zfryNum}
				</td>
				<td>
					${springData.zfcarNum}
				</td>
				<td>
					${springData.zfshipNum}
				</td>
				<td>
					${springData.xhsj}
				</td>
				<td>
					${springData.xhlc}
				</td>
				<td>
					${springData.illegalNum}
				</td>
				<td>
					${springData.xqNum}
				</td>
				<td>
					${springData.fbzl}
				</td>
				<td>
					${springData.aqxx}
				</td>
			
				<shiro:hasPermission name="spring:springData:edit"><td>
    				<a href="${ctx}/spring/springData/form?id=${springData.id}">修改</a>
					<a href="${ctx}/spring/springData/delete?id=${springData.id}" onclick="return confirmx('确认要删除该春运专项数据吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>