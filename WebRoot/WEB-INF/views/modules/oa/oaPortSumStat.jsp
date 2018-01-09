<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡航数据管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.result').each(function(index,element){
				
				
				var a = parseFloat($('.this_state:eq('+index+')').html())	
				var b = parseFloat($('.last_state:eq('+index+')').html());	
				$(this).html(toPercent(((a-b)/b).toFixed(3)));
			})
			function toPercent(data){
			    var strData =data*100;
			    strData = strData.toFixed(1);
			    var ret = strData.toString()+"%";
			    return ret;
			}
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
		<li class="active"><a href="${ctx}/oa/oaDigitalcruise/portSumStat">巡航数据汇总表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="portData" action="${ctx}/oa/oaDigitalcruise/portSumStat" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
			<label class="control-label">归属部门:</label>
			 <sys:treeselect id="office" name="office.id" value="${officeId}" labelName="office.name" labelValue="${officeName}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="required" />
			
			</li>
			<li>
			<label class="control-label">本期时间段:</label>
			<input id="beginHappenDate" name="beginHappenDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${statics.beginHappenDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>-
				
		   <input name="endHappenDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${statics.endHappenDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			
			
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table class="table static-table table-striped table-bordered  table-condensed">
		<thead>汇总表:${portName}</thead>
		<tbody>
			<tr>
				<th>数据项目</th>
				<th>单位</th>
				<th>本期</th>
				
			</tr>
		
			<tr>
				<td>渡口运次</td>
				<td>次</td>
				<td><label class="this_state">${statics.dkyc}</label></td>
				
			</tr>
			<tr>
				<td>渡口运输车次</td>
				<td>次</td>
				<td><label class="this_state">${statics.yscc}</label></td>
				
			</tr>
			<tr>
				<td>渡口运输人次</td>
				<td>次</td>
				<td><label class="this_state">${statics.ysrc}</label></td>
				
			</tr>
			
		</tbody>
	</table>
</body>
</html>