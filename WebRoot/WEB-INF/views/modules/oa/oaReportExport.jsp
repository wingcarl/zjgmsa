<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡航数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/oa/oaCruisedata/reports");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			
			
	       
		       
		});
		
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
		<li><a href="${ctx}/oa/oaCruisedata/">巡航数据列表</a></li>
		<shiro:hasPermission name="oa:oaCruisedata:edit"><li><a href="${ctx}/oa/oaCruisedata/form">巡航数据添加</a></li></shiro:hasPermission>
		<shiro:hasPermission name="oa:oaCruisedata:edit"><li  class="active"><a href="${ctx}/oa/oaCruisedata/stat">巡航数据分类统计</a></li></shiro:hasPermission>
		
	</ul>
	<form:form id="searchForm" modelAttribute="oaReport" action="${ctx}/oa/oaCruisedata/reports" method="post" class="breadcrumb form-search">
		
		<ul class="ul-form">
			<li><label>报表类型：</label>
			<form:select path="reportType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('report_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>选择时间：</label>
				<li><label>发生日期：</label>
				<input id="createDate" name="createDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${oaReport.createDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				
				</li>
			</li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	
	 
</body>
</html>