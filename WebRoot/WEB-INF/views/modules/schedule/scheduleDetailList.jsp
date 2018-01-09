<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>排班表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#searchForm").attr("action","${ctx}/schedule/scheduleDetail/export");
					$("#searchForm").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
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
		function confirmSchedule(url){
			$.ajax({
    	        type: "get",
    	        dataType: "text",
    	        url: url,
    	        success: function (data) {
    	        	alert(data);
    	        	$('#searchForm').submit();
    	        },
    	        complete:function(data){
    	        	
    	        }
    	        });
		}
	</script>
</head>
<body>
<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/schedule/scheduleDetail/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/schedule/scheduleDetail/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/schedule/scheduleDetail/">排班表列表</a></li>
		<shiro:hasPermission name="schedule:scheduleDetail:edit"><li><a href="${ctx}/schedule/scheduleDetail/form">排班表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="scheduleDetail" action="${ctx}/schedule/scheduleDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>部门：</label>
				<sys:treeselect id="office" name="office.id" value="${scheduleDetail.office.id}" labelName="" labelValue="${scheduleDetail.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" />
			</li>
			<li><label>排班日期：</label>
				<input name="scheduleDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${scheduleDetail.scheduleDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
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
				<th>部门</th>
				<th>排班日期</th>
				<th>白班1/vts(0-8)</th>
				<th>白班2/vts(8-16)</th>
				<th>夜班1/vts(16-24)</th>
				<th>领导带班</th>
				<th>确认状态</th>
				<th>确认人</th>
				<shiro:hasPermission name="schedule:scheduleDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="scheduleDetail">
			<tr>
				<td><a href="${ctx}/schedule/scheduleDetail/form?id=${scheduleDetail.id}">
					${scheduleDetail.office.name}
				</a></td>
				<td>
					<fmt:formatDate value="${scheduleDetail.scheduleDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${scheduleDetail.userList1}
				</td>
				<td>
					${scheduleDetail.userList2}
				</td>
				<td>
					${scheduleDetail.userList3}
				</td>
				<td>
					${scheduleDetail.userList4}
				</td>
				<td>
					${scheduleDetail.confirm}
				</td>
				<td>
					${scheduleDetail.confirmBy}
				</td>
				
				<shiro:hasPermission name="schedule:scheduleDetail:edit"><td>
				    
					<a onclick=confirmSchedule("${ctx}/schedule/scheduleDetail/confirm?id=${scheduleDetail.id}"); href="javascript:void(0);">确认</a>
				
    				<a href="${ctx}/schedule/scheduleDetail/form?id=${scheduleDetail.id}">修改</a>
					<a href="${ctx}/schedule/scheduleDetail/delete?id=${scheduleDetail.id}" onclick="return confirmx('确认要删除该排班表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>