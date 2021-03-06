<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡航数据管理</title>
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
		.table th, .table td { 
			text-align: center;
			vertical-align: middle!important;
			}
</style>
</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oa/oaCruisedata/">巡航数据列表</a></li>
		<shiro:hasPermission name="oa:oaCruisedata:add"><li><a href="${ctx}/oa/oaCruisedata/form">巡航数据添加</a></li></shiro:hasPermission>
		<shiro:hasPermission name="oa:oaCruisedata:edit"><li><a href="${ctx}/oa/oaCruisedata/stat">巡航数据分类统计</a></li></shiro:hasPermission>
		
	</ul>
	<form:form id="searchForm" modelAttribute="oaCruisedata" action="${ctx}/oa/oaCruisedata/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>巡查日期：</label>
				<input id="beginHappenDate" name="beginHappenDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${oaCruisedata.beginHappenDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>-
				
				<input name="endHappenDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${oaCruisedata.endHappenDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered  table-condensed">
		<thead>
			<tr >
				<th>巡航数据添加日期</th>
				<th>数据船艇来源</th>
				<th>重要航路</th>
				<th>锚地停泊区</th>
				<th>施工作业区</th>
				<th>发现违反航<br>行规定行为</th>
				<th>发现违法施<br>工作业行为</th>
				<th>客运码头<br>渡口</th>
				<th>发现助航<br>标志异常</th>
				<th>抛泥区</th>
				<th>发现违法养<br>殖采砂行为</th>
				<th>检查船舶<br>（艘次）</th>
				<th>危险品码头<br>装卸作业区</th>
				<th>交通组织<br>护航</th>
				<th>发放宣传<br>资料（份）</th>
				<th>桥（坝）区</th>
				<th>参与救助</th>
				<th>海巡艇发现<br>违章（艘次）</th>
				<th>海巡艇发现违<br>章处理起数</th>
				<th>取水口</th>
				<th>发现违法<br>排放行为</th>
				<th>其他违法行为</th>
				<shiro:hasPermission name="oa:oaCruisedata:edit"><th>数据编辑与删除</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaCruisedata">
			<tr>
				<td>
					<a href="${ctx}/oa/oaCruisedata/formSeen?id=${oaCruisedata.id}" target="_blank">
						<fmt:formatDate value="${oaCruisedata.createDate}" pattern="yyyy-MM-dd"/>
					</a>
					
				</td>
				<td>
					${oaCruisedata.createBy.office.name}
				</td>
				<td>
					${oaCruisedata.zyhl}
				</td>
				<td>
					${oaCruisedata.mdtbq}
				</td>
				<td>
					${oaCruisedata.sgzyq}
				</td>
				<td>
					${oaCruisedata.wfhxgd}
				</td>
				<td>
					${oaCruisedata.wfsgzy}
				</td>
				<td>
					${oaCruisedata.kymt}
				</td>
				<td>
					${oaCruisedata.zhbzyc}
				</td>
				
				<td>
					${oaCruisedata.pnq}
				</td>
				<td>
					${oaCruisedata.wfyzcs}
				</td>
				<td>
					${oaCruisedata.psc}
				</td>
				<td>
					${oaCruisedata.dangerPort}
				</td>
				<td>
					${oaCruisedata.jtzz}
				</td>
				<td>
					${oaCruisedata.ffxczl}
				</td>
				<td>
					${oaCruisedata.qbq}
				</td>
				<td>
					${oaCruisedata.cyjz}
				</td>
				<td>
					${oaCruisedata.fxwz}
				</td>
				<td>
					${oaCruisedata.clwz}
				</td>
				<td>
					${oaCruisedata.qsk}
				</td>
				<td>
					${oaCruisedata.wfpf}
				</td>
				<td>
					${oaCruisedata.qtwfxw}
				</td>
				<shiro:hasPermission name="oa:oaCruisedata:edit">
					
					<td>
						<c:if test="${oaCruisedata.editable}">
		    				<a href="${ctx}/oa/oaCruisedata/formEdit?id=${oaCruisedata.id}">修改</a>
							<a href="${ctx}/oa/oaCruisedata/delete?id=${oaCruisedata.id}" onclick="return confirmx('确认要删除该巡航数据吗？', this.href)">删除</a>
						</c:if>
						
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>