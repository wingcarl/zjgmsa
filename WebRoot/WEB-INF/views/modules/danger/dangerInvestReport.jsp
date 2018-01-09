<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>隐患排查表单</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/jquery-easyui/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery-easyui/themes/icon.css">
	 <link rel="stylesheet" type="text/css" href="${ctxStatic}/bootstrap/2.3.1/css_default/bootstrap.min.css">
	<link href="${ctxStatic}/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<script src="${ctxStatic}/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script src="${ctxStatic}/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="${ctxStatic}/layer/layer.js"></script>
	<script src="${ctxStatic}/layer/lay/dest/layui.all.js"></script>
	<script src="${ctxStatic}/bootstrap/2.3.1/js/bootstrap.min.js"></script>
	<script src="${ctxStatic}/layer/layui.js"></script>
	<link href="${ctxStatic}/layer/css/layui.css" rel="stylesheet">
	
	
	<!--<script src="${ctxStatic}/bootstrap4/jquery-3.2.1.slim.min.js"></script>
	<script src="${ctxStatic}/bootstrap4/popper.min.js"></script>
	<script src="${ctxStatic}/bootstrap4/bootstrap.min.js"></script>
	<link href="${ctxStatic}/bootstrap4/bootstrap.min.css" rel="stylesheet">
	 -->
	<style>
			.container{width:800px;min-height:1000px;border:1px solid red;}
			.row{display:flex;}
			/*.row{margin-left:0px;}
			.span1{margin-left:0px;}
			.span2{margin-left:0px;}
			.span3{margin-left:0px;}
			.span4{margin-left:0px;}
			.span5{margin-left:0px;}
			.span6{margin-left:0px;}
			.span7{margin-left:0px;}
			.span8{margin-left:0px;}
			.span9{margin-left:0px;}
			.span10{margin-left:0px;}
			.span11{margin-left:0px;}
			.span12{margin-left:0px;}*/
			.bg{background-color:#DCDCDC}
			.bo-bottom{border-bottom:1px solid #000;}
			/*#title{width:800px;height:50px;border-bottom:2px solid red;background:-webkit-gradient(linear, 0% 0%, 0% 100%,from(#b8c4cb), to(#f6f6f8));}*/
			#hxt{width:100%;height:40px;margin:0 auto;text-align:center; margin-top:10px;font:bold 26px arial,sans-serif;float:left;}
		/* 	#container{width:800px;min-height:1000px;border:1px solid red;margin:0 auto;}
			#title{width:800px;height:50px;border-bottom:2px solid red;background:-webkit-gradient(linear, 0% 0%, 0% 100%,from(#b8c4cb), to(#f6f6f8));}
			#hxt{width:150px;height:40px;margin:0 auto;text-align:center; margin-top:10px;font:bold 26px arial,sans-serif;float:left;}
			#report_date{width:180px;height:40px;margin:0 auto;text-align:center; margin-top:14px;font:20px arial,sans-serif;float:left;}
			#dep{width:200px;height:40px;margin:0 auto;text-align:center;margin-top:10px;margin-left:20px;font:bold 26px arial,sans-serif;float:left;}
			
			#cruise_range{width:800px;min-height:400px;border-bottom:2px solid red;border-top:1px solid red;}
			#range_title{width:790px;height:22px;font-size:16px;font-weight:bold;float:left;padding-left:10px;padding-top:2px;border-bottom:1px solid black}
			.range_row{width:800px;height:22px;border-bottom:1px solid black;float:left;}
			.rt{width:135px;height:22px;border-left:1px solid black;float:left;text-align:center}
			.rc{width:60px;height:22px;border-left:1px solid black;float:left;text-align:center}
			.rcl{min-width:160px;padding:0px 5px;min-height:22px;border-left:1px solid black;float:left;text-align:center}
			.range_textarea{width:800px;min-height:22px;border-bottom:1px solid black;} */
		
	</style>
	<script type="text/javascript">
		
		
	</script>
</head>
<body>
	
	<div class="container">
		<div id="title">
		<div class="row-fluid bg">
				<div class="span10 text-center" id="hxt">${dangerInvest.findOffice.name }隐患报表 </div>
		</div>
		</div>
		<div class="row">
			<div class="span2 text-center">隐患名称</div>
			<div class="span8 text-center bo-bottom">${dangerInvest.dangerName }</div>
		</div>
		<div class="row">
			<div class="span2 text-center">隐患分级</div>
			<div class="span3 text-center bo-bottom">${fns:getDictLabel(dangerInvest.dangerClassify, 'danger_classify', '')}</div>
			<div class="span2 text-center">隐患分类</div>
			<div class="span3 text-center bo-bottom">${fns:getDictLabel(dangerInvest.dangerType, 'danger_type', '')}</div>
		</div>
		<div class="row">
			<div class="span2 text-center ">整改责任单位</div>
			<div class="span3 text-center bo-bottom">	${dangerInvest.dangerCompany }</div>
			<div class="span2 text-center">发现部门</div>
			<div class="span3 text-center bo-bottom">${dangerInvest.findOffice.name }</div>
		</div>
		<div class="row">
			<div class="span2 text-center">发现时间</div>
			<div class="span3 text-center bo-bottom"><fmt:formatDate value="${dangerInvest.findTime}" dateStyle="full"/></div>
		</div>
		<c:if test="${dangerInvest.consequences ne ''}">
		<div class="row">
			<div class="span2 text-center">隐患可能后果</div>
			<div class="span8 text-center bo-bottom">${dangerInvest.consequences }</div>
		</div>
		</c:if>
		<c:if test="${dangerInvest.againstLaw ne ''}">
		
		<div class="row">
			<div class="span2 text-center">违反的法律、法规等</div>
			<div class="span8 text-center bo-bottom">${dangerInvest.againstLaw}</div>
		</div>
		
		</c:if>
		<div class="row  bg bo-bottom" style="margin-left:1px">
			<div class="span10 text-center bg"><h3><strong>隐患具体情况</strong></h3></div>
		</div>
		<table class="table">
			<tr>
				<th>隐患项目内容</th>
				<th>隐患整改要求</th>
				<th>整改期限</th>
			</tr>
			<c:forEach items="${dangerInvest.dangerInvestDetailList}" var="d">
			<tr>
				<td>${d.detail}</th>
				<td>${d.requirement}</th>
				<td>${d.deadtime}天</th>
			</tr>
			</c:forEach>
		</table>
		
		<div class="row  bg bo-bottom" style="margin-left:1px">
			<div class="span10 text-center bg"><h3><strong>隐患整改情况</strong></h3></div>
		</div>
		<c:if test="${dangerInvest.dangerDifficulty ne ''}">
		<div class="row">
			<div class="span2 text-center">整改存在的难点</div>
			<div class="span8 text-center bo-bottom">${dangerInvest.dangerDifficulty }</div>
		</div>
		</c:if>
		<c:if test="${dangerInvest.dealWay ne ''}">
		
		<div class="row">
			<div class="span2 text-center">目前海事采取的对策</div>
			<div class="span8 text-center bo-bottom">${dangerInvest.dealWay}</div>
		</div>
		
		</c:if>
		<div class="row">
			<div class="span2 text-center">是否下达隐患通知书</div>
			<div class="span3 text-center bo-bottom">${fns:getDictLabel(dangerInvest.isTransmit, 'yes_no', '')}</div>
		</div>
		<c:if test="${dangerInvest.isTransmit eq '1'}">
		<div class="row">
			<div class="span2 text-center">通知书发放时间</div>
			<div class="span3 text-center bo-bottom"><fmt:formatDate value="${dangerInvest.noticeTime}" dateStyle="full"/></div>
			<div class="span2 text-center">整改通知书编号</div>
			<div class="span3 text-center bo-bottom">${dangerInvest.noticeNo}</div>
		</div>
		</c:if>
		<c:if test="${dangerInvest.enforceMeasure ne ''}">
		<div class="row">
			<div class="span2 text-center">采取强制措施情况</div>
			<div class="span3 text-center bo-bottom">${dangerInvest.enforceMeasure }</div>
		</div>
		</c:if>
		<div class="row">
			<div class="span2 text-center">整改情况</div>
			<div class="span3 text-center bo-bottom">${fns:getDictLabel(dangerInvest.dangerRecify, 'danger_recify', '')}</div>
			<div class="span2 text-center">下一步工作措施</div>
			<div class="span3 text-center bo-bottom">${dangerInvest.nextWay }</div>
		
		</div>
		<div class="row">
			<div class="span2 text-center">整改是否完成</div>
			<div class="span3 text-center bo-bottom">${fns:getDictLabel(dangerInvest.isComplete, 'yes_no', '')}</div>
			<div class="span2 text-center">整改完成日期</div>
			<div class="span3 text-center bo-bottom"><fmt:formatDate value="${dangerInvest.completeDate}" dateStyle="full"/></div>
		
		</div>
		
		<div class="row  bg bo-bottom" style="margin-left:1px">
			<div class="span10 text-center bg"><h3><strong>隐患跟踪检查情况</strong></h3></div>
		</div>
		<table class="table">
			<tr>
				<th>隐患情况</th>
				<th>检查时间</th>
				<th>检查情况</th>
				<th>检查人</th>
			</tr>
			<c:forEach items="${dangerInvest.dangerInvestDetailList}" var="d">
				<c:forEach items="${d.dangerInvestSpyList}" var="spy">
					<tr>
						<td>${d.detail}</th>
						<td><fmt:formatDate value="${spy.spyTime}" dateStyle="full"/></th>
						<td>${spy.spyNote}</th>
						<td>${spy.createBy.name}</th>
					</tr>
				</c:forEach>
			</c:forEach>
		</table>
	</div>
			
			
			<!--  	<div id="range_title">
					隐患信息
				</div>
				<div class="range_row">
					<div class="rt">
					隐患名称
					</div>
					<div class="rcl">
					${dangerInvest.dangerName }
					</div>	
				</div>
				<div class="range_row">
					<div class="rt">
					隐患分级
					</div>
					<div class="rcl">
					${fns:getDictLabel(dangerInvest.dangerClassify, 'danger_classify', '')}
					</div>	
					<div class="rt">
					隐患分类
					</div>
					<div class="rcl">
					${fns:getDictLabel(dangerInvest.dangerType, 'danger_type', '')}
					</div>	
					
				</div>
				<div class="range_row">
					<div class="rt">
					隐患整改责任单位
					</div>
					<div class="rcl">
					${dangerInvest.dangerCompany }
					</div>	
					
					<div class="rt">
					发现时间
					</div>
					<div class="rcl">
					${dangerInvest.findTime }
					</div>	
				</div>
				
				-->
				
		
	
   
	
</body>

</html>