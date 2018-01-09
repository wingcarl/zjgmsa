<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡航数据管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/jquery-easyui/jquery.easyui.min.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/jquery-easyui/themes/icon.css">
	<link href="${ctxStatic}/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
	<script src="${ctxStatic}/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
	<script src="${ctxStatic}/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	<script src="${ctxStatic}/layer/layer.js"></script>
	<script src="${ctxStatic}/layer/lay/dest/layui.all.js"></script>
	
	<script src="${ctxStatic}/layer/layui.js"></script>
	<link href="${ctxStatic}/layer/css/layui.css" rel="stylesheet">
	
	
	
	<style>
		#container{width:800px;min-height:1000px;border:1px solid red;margin:0 auto;}
			#title{width:800px;height:50px;border-bottom:2px solid red;background:-webkit-gradient(linear, 0% 0%, 0% 100%,from(#b8c4cb), to(#f6f6f8));}
			#hxt{width:150px;height:40px;margin:0 auto;text-align:center; margin-top:10px;font:bold 26px arial,sans-serif;float:left;}
			#report_date{width:200px;height:40px;margin:0 auto;text-align:center; margin-top:14px;font:20px arial,sans-serif;float:left;}
			#dep{width:200px;height:40px;margin:0 auto;text-align:center;margin-top:10px;margin-left:20px;font:bold 26px arial,sans-serif;float:left;}
			
			#cruise_range{width:800px;min-height:400px;border-bottom:2px solid red;border-top:1px solid red;}
			#range_title{width:790px;height:22px;font-size:16px;font-weight:bold;float:left;padding-left:10px;padding-top:2px;border-bottom:1px solid black}
			.range_row{width:800px;height:22px;border-bottom:1px solid black;float:left;}
			.rt{width:135px;height:22px;border-left:1px solid black;float:left;text-align:center}
			.rc{width:60px;height:22px;border-left:1px solid black;float:left;text-align:center}
			.rcl{min-width:160px;padding:0px 5px;min-height:22px;border-left:1px solid black;float:left;text-align:center}
			.range_textarea{width:800px;min-height:22px;border-bottom:1px solid black;}
		
	</style>
	<script type="text/javascript">
		
		$(document).ready(function() {
		});
	</script>
</head>
<body>
	
	<div id="container">
			<div id="title">
				<div id="hxt">${oaCruisedata.createBy.office.name}</div>
				<div id="report_date">${dateStr}报表</div>
				<div id="dep">${depName}</div>
			</div>
			<div id="cruise_range">
				<div id="range_title">
					巡航范围数据
				</div>
				<div class="range_row">
					<div class="rt">
					重要航路
					</div>
					<div class="rc">
					${oaCruisedata.zyhl}
					</div>
					<div class="rt">
					锚地停泊区
					</div>
					<div class="rc">
					${oaCruisedata.mdtbq}
					</div>
					<div class="rt">
					施工作业区
					</div>
					<div class="rc">
					${oaCruisedata.sgzyq}
					</div>
					<div class="rt">
					客运码头、渡口
					</div>
					<div class="rc">
					${oaCruisedata.kymt}
					</div>
				</div>
				<div class="range_row">
					<div class="rt" style="font-size:10px">
					危险品码头、装卸作业区
					</div>
					<div class="rc">
					${oaCruisedata.dangerPort}
					</div>
					<div class="rt">
					抛泥区
					</div>
					<div class="rc">
					${oaCruisedata.pnq}
					</div>
					<div class="rt">
					取水口
					</div>
					<div class="rc">
					${oaCruisedata.qsk}
					</div>
					<div class="rt">
					桥（坝）区
					</div>
					<div class="rc">
					${oaCruisedata.qbq}
					</div>
				</div>
				<div class="range_row">
					<div class="rt">
					交通组织、护航
					</div>
					<div class="rc">
					${oaCruisedata.jtzz}
					</div>
					<div class="rt">
					发放宣传资料
					</div>
					<div class="rc">
					${oaCruisedata.ffxczl}
					</div>
					<div class="rt">
					参与救助
					</div>
					<div class="rc">
					${oaCruisedata.cyjz}
					</div>
					<div class="rt">
					救助船舶
					</div>
					<div class="rc">
					${oaCruisedata.jzcb}
					</div>
				</div>
				<div class="range_row">
					<div class="rt">
					救助人员
					</div>
					<div class="rc">
					${oaCruisedata.jzry}
					</div>
					<div class="rt">
					检查船舶
					</div>
					<div class="rc">
					${oaCruisedata.psc}
					</div>
					<div class="rt">
					发现助航标志异常
					</div>
					<div class="rc">
					${oaCruisedata.zhbzyc}
					</div>
					<div class="rt">
					其他必查水域
					</div>
					<div class="rc">
					${oaCruisedata.otherArea}
					</div>
				</div>
				<div id="range_title">
					巡航水域
				</div>
				<div class="range_textarea">
					
				${oaCruisedata.cruiseArea}
				
				</div>
				<div id="range_title">
					违章数据
				</div>
				<div class="range_row">
					<div class="rt">
					违反航行规定行为
					</div>
					<div class="rc">
					${oaCruisedata.wfhxgd}
					</div>
					<div class="rt">
					发现违法排放行为
					</div>
					<div class="rc">
					${oaCruisedata.wfpf}
					</div>
					<div class="rt" style="font-size:14px">
					违法养殖、采砂行为
					</div>
					<div class="rc">
					${oaCruisedata.wfpf}
					</div>
					<div class="rt">
					违法施工作业行为
					</div>
					<div class="rc">
					${oaCruisedata.wfsgzy}
					</div>
				</div>
				<div class="range_row">
					<div class="rt">
					海巡艇发现违章
					</div>
					<div class="rc">
					${oaCruisedata.fxwz}
					</div>
					<div class="rt" style="font-size:14px">
					海巡艇违章处理起数
					</div>
					<div class="rc">
					${oaCruisedata.clwz}
					</div>
					<div class="rt">
					其他违法行为
					</div>
					<div class="rc">
					${oaCruisedata.qtwfxw}
					</div>
				</div>
				<div id="range_title">
					查处违章船舶清单
				</div>
				<table class="table table-bordered">
					<th>
						<td>船名</td>
						<td>发生地点</td>
						<td>违法行为</td>
						<td>处置结果</td>
						
					</th>
				<c:forEach items="${illegalList}" var="oaIllegal">
					<tr>
						<td>#</td>
						<td>${oaIllegal.shipName}</td>
						<td>${oaIllegal.happenLocation}</td>
						<td>${fns:getDictLabel(oaIllegal.illegalDetail, 'illegal_detail', '')}</td>
						<td>${fns:getDictLabel(oaIllegal.dealWay, 'deal_way', '')}</td>
					</tr>
				</c:forEach>	
				</table>
			
			</div>
			<div id="range_title">
					巡航人员
			</div>
				<div class="range_row">
					<div class="rt">
					出动执法人员次数
					</div>
					<div class="rc">
						${oaCruisedata.cdzfrycs}
					</div>
					<div class="rt">
					其他人员次数
					</div>
					<div class="rc">
						${oaCruisedata.qtrycs}
					</div>
				</div>
				<div class="range_row">
					<div class="rt">
					执法人员名单
					</div>
					<div class="rcl">
						${oaCruisedata.cruisePeople}
					</div>
					
				</div>
			<div id="range_title">
					发现解决的问题
			</div>
				
			<div class="range_textarea">
				<pre>	
${oaCruisedata.solveProblem}
				</pre>
				
			</div>
			<div id="range_title">
					巡航时间
			</div>
			<div class="range_row">
				<div class="rt">
					巡航时间
				</div>
				<div class="rc">
						${timeParam.totalTime}
				</div>
				<div class="rt">
					巡航艘次
				</div>
				<div class="rc">
						${timeParam.xhcs}
				</div>
				<div class="rt">
					夜航时间
				</div>
				<div class="rc">
							${timeParam.yehangTime}
				</div>
				<div class="rt">
					夜航艘次
				</div>
				<div class="rc">
							${timeParam.yehangcs}
				</div>
			</div>
			<div id="range_title">
					巡航时间段
			</div>
				
			<div class="range_textarea">
				<pre>
<c:forEach items="${oaCruisedata.oaCruisetimeList}" var="oaTime">
<fmt:formatDate value="${oaTime.startTime}" pattern="yyyy年MM月dd日HH时mm分"/>~<fmt:formatDate value="${oaTime.endTime}" pattern="yyyy年MM月dd日HH时mm分"/>
</c:forEach>	
				</pre>
			</div>	
				
		</div>
	
   
	
</body>

</html>