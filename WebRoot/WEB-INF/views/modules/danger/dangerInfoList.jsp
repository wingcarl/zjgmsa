<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>隐患信息表管理</title>
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
					content:'${ctx}/danger/dangerInfoSpy?dangerInfo='+$(this).val(),
					btn:['关闭'],
					btn1:function(){
						layer.closeAll();
					},
					zIndex:layer.zIndex
				});  
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
		<li class="active"><a href="${ctx}/danger/dangerInfo/">隐患信息表列表</a></li>
		<shiro:hasPermission name="danger:dangerInfo:edit"><li><a href="${ctx}/danger/dangerInfo/form">隐患信息表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dangerInfo" action="${ctx}/danger/dangerInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>排查对象：</label>
				<form:select path="dangerCompany" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>隐患类别：</label>
				<form:select path="dangerType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dangerInfo_dangerType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>可能性级别：</label>
				<form:select path="possbility" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dangerInfo_possbility')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>严重程度：</label>
				<form:select path="result" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dangerInfo_result')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>整改难度：</label>
				<form:select path="difficulty" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dangerInfo_difficulty')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>隐患级别：</label>
				<form:select path="level" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dangerInfo_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>隐患评级：</label>
				<form:select path="grade" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dangerInfo_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>发现途径：</label>
				<form:select path="findWay" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('dangerInfo_findWay')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>发现时间：</label>
				<input name="beginFindTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dangerInfo.beginFindTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endFindTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${dangerInfo.endFindTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>发现部门：</label>
				<sys:treeselect id="findOffice" name="findOffice" value="${dangerInfo.findOffice.id}" labelName="" labelValue="${dangerInfo.findOffice.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>整改是否完成：</label>
				<form:radiobuttons path="isComplete" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>排查<br>对象</th>
				<th>隐患<br>类别</th>
				<th>概况描述</th>
				<th>可能性<br>级别</th>
				<th>后果严<br>重程度</th>
				<th>整改<br>难度</th>
				<th>隐患<br>级别</th>
				<th>隐患<br>评级</th>
				<th>发现<br>途径</th>
				<th>发现<br>时间</th>
				<th>发现<br>部门</th>
				<th>处理意见</th>
				<th>直接<br>责任人</th>
				<th>整改<br>情况</th>
				<th>是否<br>完成</th>
				<th>销号<br>时间</th>
				<th>单位<br>负责人</th>
				<th>更新者</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>跟踪<br>检查</th>
				<shiro:hasPermission name="danger:dangerInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dangerInfo">
			<tr>
				<td><a href="${ctx}/danger/dangerInfo/form?id=${dangerInfo.id}">
					${dangerInfo.dangerCompanyName}
				</a></td>
				<td>
					${fns:getDictLabel(dangerInfo.dangerType, 'dangerInfo_dangerType', '')}
				</td>
				<td>
					${dangerInfo.dangerDetail}
				</td>
				<td>
					${fns:getDictLabel(dangerInfo.possbility, 'dangerInfo_possbility', '')}
				</td>
				<td>
					${fns:getDictLabel(dangerInfo.result, 'dangerInfo_result', '')}
				</td>
				<td>
					${fns:getDictLabel(dangerInfo.difficulty, 'dangerInfo_difficulty', '')}
				</td>
				<td>
					${fns:getDictLabel(dangerInfo.level, 'dangerInfo_level', '')}
				</td>
				<td>
					${fns:getDictLabel(dangerInfo.grade, 'dangerInfo_grade', '')}
				</td>
				<td>
					${fns:getDictLabel(dangerInfo.findWay, 'dangerInfo_findWay', '')}
				</td>
				<td>
					<fmt:formatDate value="${dangerInfo.findTime}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
					${dangerInfo.findOffice.name}
				</td>
				<td>
					${dangerInfo.suggestion}
				</td>
				<td>
					${dangerInfo.liable}
				</td>
				<td>
					${dangerInfo.dealResult}
				</td>
				<td>
					${fns:getDictLabel(dangerInvest.isComplete, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${dangerInfo.completeTime}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
					${dangerInfo.leader}
				</td>
				<td>
					${dangerInfo.updateBy.name}
				</td>
				<td>
					<fmt:formatDate value="${dangerInfo.updateDate}" pattern="yyyy-MM-dd HH:mm"/>
				</td>
				<td>
					${dangerInfo.remarks}
				</td>
				<td>
					<button class="btn spy" detail="${dangerInfo.dangerDetail}" value="${dangerInfo.id}">跟踪</button>
					
				</td>
				<shiro:hasPermission name="danger:dangerInfo:edit"><td>
    				<a href="${ctx}/danger/dangerInfo/form?id=${dangerInfo.id}">修改</a>
					<a href="${ctx}/danger/dangerInfo/delete?id=${dangerInfo.id}" onclick="return confirmx('确认要删除该隐患信息表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>