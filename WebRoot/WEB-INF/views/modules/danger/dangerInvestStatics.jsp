<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>隐患排查登记表管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/layer/layer.js"></script>
	<script src="${ctxStatic}/layer/lay/dest/layui.all.js"></script>	
	<script src="${ctxStatic}/layer/layui.js"></script>
	<link href="${ctxStatic}/layer/css/layui.css" rel="stylesheet">
	<script type="text/javascript">
		$(document).ready(function() {
			$('.spy').click(function(){
				layer.open({
					type:2,
					title:$(this).attr('detail'),
					area:['890px','560px'],
					shade:0,
					maxmin:true,
					content:'${ctx}/danger/dangerInvestSpy?dangerInvest='+$(this).val(),
					btn:['关闭'],
					btn1:function(){
						layer.closeAll();
					},
					zIndex:layer.zIndex
				});  
			})
		});
		function audit(url){
			top.$.jBox.confirm("确认要导出审批表吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#searchForm").attr("action",url);
					$("#searchForm").submit();
					$("#searchForm").attr("action","${ctx}/danger/dangerInvest/");
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');

		}
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
		<li><a href="${ctx}/danger/dangerInvest/">隐患排查登记表列表</a></li>
		<li><a href="${ctx}/danger/dangerInvest/form?id=${dangerInvest.id}">隐患排查登记表<shiro:hasPermission name="danger:dangerInvest:edit">${not empty dangerInvest.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="danger:dangerInvest:edit">查看</shiro:lacksPermission></a></li>
		<li  class="active"><a href="${ctx}/danger/dangerInvest/statics">隐患数据统计</a></li>
	
	</ul><br/>
	<form:form id="searchForm" modelAttribute="dangerInvest" action="${ctx}/danger/dangerInvest/statics" method="post" class="breadcrumb form-search">
	
		<ul class="ul-form">
			<li><label>时间段：</label>
				<input name="beginFindTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dangerInvest.beginFindTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/> - 
				<input name="endFindTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dangerInvest.endFindTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>
			<li><label>隐患种类：</label>
				<form:select path="dangerType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('danger_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>整改情况：</label>
				<form:select path="dangerRecify" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('danger_recify')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>海事处</th>
				<th>隐患数量</th>
				<th>发放整改通知书</th>
				<th>已整改隐患</th>
				<th>未整改隐患</th>
				<th>隐患跟踪检查次数</th>
				
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page}" var="dangerInvestStat">
			<tr>
			
				<td>
					${dangerInvestStat.office.name}
				</td>
				<td>
					${dangerInvestStat.dangerSum}
				</td>
				<td>
					${dangerInvestStat.recifyFileSum}
				</td>
				<td>
					${dangerInvestStat.recifySum}
				</td>
				<td>
					${dangerInvestStat.noRecifySum}
				</td>
				<td>
					${dangerInvestStat.followTimes}
				</td>
				
				
			</tr>
		</c:forEach>
		<tr style="color:red;font-weight:bold">
			
				<td>
					总计
				</td>
				
				<td>
					${v.dangerSum}
				</td>
				<td>
					${v.recifyFileSum}
				</td>
				<td>
					${v.recifySum}
				</td>
				<td>
					${v.noRecifySum}
				</td>
				<td>
					${v.followTimes}
				</td>
				
			
			
			</tr>
		</tbody>
	</table>
</body>
</html>