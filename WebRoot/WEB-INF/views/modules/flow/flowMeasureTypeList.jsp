<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流量观测按船种分管理</title>
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
				<form id="importForm" action="${ctx}/flow/flowMeasureType/import" method="post" enctype="multipart/form-data"
					class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
					<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
					<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
					<a href="${ctx}/flow/flowMeasureType/import/template">下载模板</a>
				</form>
</div>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/flow/flowMeasureType/">流量观测按船种分列表</a></li>
		<shiro:hasPermission name="flow:flowMeasureType:edit"><li><a href="${ctx}/flow/flowMeasureType/form">流量观测按船种分添加</a></li></shiro:hasPermission>
		<li><a href="${ctx}/flow/flowMeasureType/analysis">流量观测船种数据分析</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="flowMeasureType" action="${ctx}/flow/flowMeasureType/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>断面名称：</label>
				<form:input path="sectionName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>观测日期：</label>
				<input name="beginFlowMeasureDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${flowMeasureType.beginFlowMeasureDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endFlowMeasureDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${flowMeasureType.endFlowMeasureDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
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
				<th>合计</th>
				<th>上行<br>小计</th>
				<th>上行<br>客船</th>
				<th>上行<br>普通货船</th>
				<th>上行<br>集装箱船</th>
				<th>上行<br>危险品船</th>
				<th>上行<br>船队</th>
				<th>上行<br>渔船</th>
				<th>上行<br>工程船</th>
				<th>上行<br>公务船</th>
				<th>上行<br>其它船舶</th>
				<th>下行<br>小计</th>
				<th>下行<br>客船</th>
				<th>下行<br>普通货船</th>
				<th>下行<br>集装箱船</th>
				<th>下行<br>危险品船</th>
				<th>下行<br>船队</th>
				<th>下行<br>渔船</th>
				<th>下行<br>工程船</th>
				<th>下行<br>公务船</th>
				<th>下行<br>其它船舶</th>
				<th>观测日期</th>
				<shiro:hasPermission name="flow:flowMeasureType:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="flowMeasureType">
			<tr>
				<td><a href="${ctx}/flow/flowMeasureType/form?id=${flowMeasureType.id}">
					${flowMeasureType.sectionName}
				</a></td>
				
				<td>
					${flowMeasureType.total}
				</td>
				<td>
					${flowMeasureType.upTotal}
				</td>
				<td>
					${flowMeasureType.upPassengerShip}
				</td>
				<td>
					${flowMeasureType.upCargoShip}
				</td>
				<td>
					${flowMeasureType.upContainerShip}
				</td>
				<td>
					${flowMeasureType.upDangerousShip}
				</td>
				<td>
					${flowMeasureType.upBoatTrain}
				</td>
				<td>
					${flowMeasureType.upFishBoat}
				</td>
				<td>
					${flowMeasureType.upProjectShip}
				</td>
				<td>
					${flowMeasureType.upPublicShip}
				</td>
				<td>
					${flowMeasureType.upOthers}
				</td>
				<td>
					${flowMeasureType.downTotal}
				</td>
				<td>
					${flowMeasureType.downPassengerShip}
				</td>
				<td>
					${flowMeasureType.downCargoShip}
				</td>
				<td>
					${flowMeasureType.downContainerShip}
				</td>
				<td>
					${flowMeasureType.downDangerousShip}
				</td>
				<td>
					${flowMeasureType.downBoatTrain}
				</td>
				<td>
					${flowMeasureType.downFishBoat}
				</td>
				<td>
					${flowMeasureType.downProjectShip}
				</td>
				<td>
					${flowMeasureType.downPublicShip}
				</td>
				<td>
					${flowMeasureType.downOthers}
				</td>
				<td>
					<fmt:formatDate value="${flowMeasureType.flowMeasureDate}" pattern="yyyy年M月"/>
				</td>
				<shiro:hasPermission name="flow:flowMeasureType:edit"><td>
    				<a href="${ctx}/flow/flowMeasureType/form?id=${flowMeasureType.id}">修改</a>
					<a href="${ctx}/flow/flowMeasureType/delete?id=${flowMeasureType.id}" onclick="return confirmx('确认要删除该流量观测按船种分吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>