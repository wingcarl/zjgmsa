<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>防污染作业现场检查登记表管理</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出隐患数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/pollute/antiFoulWork/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctx}/danger/antiFoulWork/")
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
		<li class="active"><a href="${ctx}/pollute/antiFoulWork/">防污染作业现场检查登记表列表</a></li>
		<shiro:hasPermission name="pollute:antiFoulWork:edit"><li><a href="${ctx}/pollute/antiFoulWork/form">防污染作业现场检查登记表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="antiFoulWork" action="${ctx}/pollute/antiFoulWork/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>接收时间：</label>
				<input name="beginReceiveTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${antiFoulWork.beginReceiveTime}" pattern="yyyy-MM-dd HH:mm"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/> - 
				<input name="endReceiveTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${antiFoulWork.endReceiveTime}" pattern="yyyy-MM-dd HH:mm"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/>
			</li>
			<li><label>所属单位：</label>
				<sys:treeselect id="office" name="office" value="${antiFoulWork.office.id}" labelName="" labelValue="${antiFoulWork.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
			</li>
			<li><label>作业单位船名/车名：</label>
				<form:input path="shipName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>作业单位：</label>
				<form:select path="workDep" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pollutant_work_company')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>对象船名：</label>
				<form:input path="receiveShipName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>作业地点：</label>
				<form:input path="workLocation" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>作业类别：</label>
				<form:select path="workType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pollutant_work_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
		
			<li><label>现场抽查：</label>
				<form:radiobuttons path="exam" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>处置结果：</label>
				<form:select path="result" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pollutant_deal_result')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>处置人员：</label>
				<form:input path="dealPeople" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th>信息接收时间</th>
				<th>所属单位</th>
				<th>作业单位<br>船名/车名</th>
				<th>作业单位</th>
				<th>对象船名</th>
				<th>作业地点</th>
				<th>作业类别</th>
				<th>作业数量<br>(吨/垃圾为立方)</th>
				<th>残油<br>数量(吨)</th>
				<th>油污水<br>数量(吨)</th>
				<th>现场抽查</th>
				<th>处置结果</th>
				<th>备注信息</th>
				<th>处置人员</th>
				<th>填报人</th>
				<th>填报时间</th>
				<shiro:hasPermission name="pollute:antiFoulWork:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="antiFoulWork">
			<tr>
				<td><a href="${ctx}/pollute/antiFoulWork/form?id=${antiFoulWork.id}">
					<fmt:formatDate value="${antiFoulWork.receiveTime}" pattern="yyyy-MM-dd HH:mm"/>
				</a></td>
				<td>
					${antiFoulWork.office.name}
				</td>
				<td>
					${antiFoulWork.shipNameName}
				</td>
				<td>
					${antiFoulWork.workDepName}
				</td>
				<td>
					${antiFoulWork.receiveShipName}
				</td>
				<td>
					${antiFoulWork.workLocation}
				</td>
				<td>
					${fns:getDictLabel(antiFoulWork.workType, 'pollutant_work_type', '')}
				</td>
				
				<td>
					${antiFoulWork.workNumTotal}
				</td>
				<td>
					${antiFoulWork.workNum}
				</td>
				<td>
					${antiFoulWork.workNumYouwu}
				</td>
				<td>
					${fns:getDictLabel(antiFoulWork.exam, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(antiFoulWork.result, 'pollutant_deal_result', '')}
				</td>
				<td>
					${antiFoulWork.remarks}
				</td>
				<td>
					${antiFoulWork.dealPeople}
				</td>
				<td>
					${antiFoulWork.createBy.name}
				</td>
				<td>
					<fmt:formatDate value="${antiFoulWork.updateDate}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<shiro:hasPermission name="pollute:antiFoulWork:edit"><td>
    				<a href="${ctx}/pollute/antiFoulWork/form?id=${antiFoulWork.id}">修改</a>
					<a href="${ctx}/pollute/antiFoulWork/delete?id=${antiFoulWork.id}" onclick="return confirmx('确认要删除该防污染作业现场检查登记表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>