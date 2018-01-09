<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>水水中转货物核销登记薄管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出台账数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/affair/affairTransferInfo/export");
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctx}/affair/affairTransferInfo/");
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
		function audit(url){
			top.$.jBox.confirm("确认要导出审批表吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#searchForm").attr("action",url);
					$("#searchForm").submit();
					$("#searchForm").attr("action","${ctx}/affair/affairTransferInfo/");
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');

		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/affair/affairTransferInfo/">水水中转货物核销登记薄列表</a></li>
		<shiro:hasPermission name="affair:affairTransferInfo:edit"><li><a href="${ctx}/affair/affairTransferInfo/form">水水中转货物核销登记薄添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="affairTransferSecond" action="${ctx}/affair/affairTransferInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>创建时间：</label>
				<input name="firstId.beginCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${affairTransferSecond.firstId.beginCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/> - 
				<input name="firstId.endCreateDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${affairTransferSecond.firstId.endCreateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:true});"/>
			</li>
			<li><label>一程船船名：</label>
				<form:input path="firstId.firstShipName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>一程船始发港：</label>
				<form:input path="firstId.firstShipDeparture" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>航次号：</label>
				<form:input path="firstId.firstMoveNum" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>货物：</label>
				<form:input path="firstId.firstCargo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>卸载码头：</label>
				<form:input path="firstId.firstUnloadPort" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>内外贸：</label>
				<form:radiobuttons path="firstId.firstInOut" items="${fns:getDictList('in_out')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>申请单位：</label>
				<form:input path="firstId.applyOffice" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>部门：</label>
				<form:select path="firstId.office.id" class="input-medium">
					<form:option value="" label="所有部门"/>
					<form:options items="${infoList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出台账"/></li>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				
				<th>船名</th>
				<th>始发港</th>
				<th>航次号</th>
				<th>货物</th>
				<th>卸载码头</th>
				<th>内外贸</th>
				<th>实际载货量</th>
				
				<th>申请单位</th>
				<th>收货人</th>
				<th>目的港</th>
				<th>中转数量</th>
				<th>票号</th>
				<th>海事处</th>
				<th>操作员</th>
				<th>更新时间</th>
				<shiro:hasPermission name="affair:affairTransferInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="second">
			<tr><td>
				<a href="${ctx}/affair/affairTransferInfo/form?id=${second.firstId.id}">
					
						${second.firstId.firstShipName}
					
				</a>
				</td>
				<td>
					${second.firstId.firstShipDeparture}
				</td>
				<td>
					${second.firstId.firstMoveNum}
				</td>
				<td>
					${second.firstId.firstCargo}
				</td>
				<td>
					${second.firstId.firstUnloadPort}
				</td>
				<td>
					${fns:getDictLabel(second.firstId.firstInOut, 'in_out', '')}
				</td>
				<td>
					${second.firstId.firstLoad}
				</td>
				
				<td>
					${second.firstId.applyOffice}
				</td>
				<td>
					${second.receiver}
				</td>
				<td>
					${second.destination}
				</td>
				<td>
					${second.number}
				</td>
				<td>
					${second.leafNum}
				</td>
				<td>
					${second.firstId.office.name}
				</td>
				<td>
					${second.firstId.createBy.name}
				</td>
				<td>
					<fmt:formatDate value="${second.firstId.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="affair:affairTransferInfo:edit"><td>
				    <a href="${ctx}/affair/affairTransferInfo/formSecondDetail?id=${second.firstId.id}&secondId=${second.id}">办理</a>
				
    				<a href="${ctx}/affair/affairTransferInfo/form?id=${second.firstId.id}">修改</a>
					<a onclick=audit("${ctx}/affair/affairTransferInfo/exportAudit?sid=${second.firstId.id}"); href="javascript:void(0);">审批</a>
					
					<a href="${ctx}/affair/affairTransferInfo/delete?id=${second.firstId.id}" onclick="return confirmx('确认要删除该水水中转货物核销登记薄吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
			</c:forEach>
		
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>