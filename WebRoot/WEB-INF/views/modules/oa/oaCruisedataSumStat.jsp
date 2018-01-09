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
		<li class="active"><a href="${ctx}/oa/oaCruisedata/sumStat">巡航数据汇总表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="oaCruisedata" action="${ctx}/oa/oaCruisedata/sumStat" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
			<label class="control-label">归属部门:</label>
			 <sys:treeselect id="office" name="office.id" value="${officeId}" labelName="office.name" labelValue="${officeName}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="required" />
			<label class="control-label">执法人员:</label>
			 <sys:treeselect id="user" name="username" value="${userId}" labelName="user.name" labelValue="${userName}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li>
			<label class="control-label">本期时间段:</label>
			<input id="beginHappenDate" name="beginHappenDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${statics.beginHappenDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>-
				
		   <input name="endHappenDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${statics.endHappenDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			
			<label class="control-label">上期时间段:</label>
			<input id="beginHappenDate1" name="beginHappenDate1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${statics.beginHappenDate1}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>-
				
		   <input name="endHappenDate1" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
							value="<fmt:formatDate value="${statics.endHappenDate1}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table class="table static-table table-striped table-bordered  table-condensed">
		<thead>汇总表:${officeName}</thead>
		<tbody>
			<tr>
				<th>数据项目</th>
				<th>单位</th>
				<th>本期</th>
				<th>上期</th>
				<th>增长率</th>
			</tr>
		
			<tr>
				<td>出航次数</td>
				<td>次</td>
				<td><label class="this_state">${statics.cruiseTimes}</label></td>
				<td><label class="last_state">${statics2.cruiseTimes}</label></td>
				<td><label class="result"></label></td>
			</tr>
			<tr>
				<td>航行时间</td>
				<td>小时</td>
				<td><label class="this_state">${statics.hxsj}</label></td>
				<td><label class="last_state">${statics2.hxsj}</label></td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>巡航时间</td>
				<td>小时</td>
				<td><label class="this_state">${statics.xhsj}</label></td>
				<td class="last_state">${statics2.xhsj}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>巡航里程</td>
				<td>海里</td>
				<td><label class="this_state">${statics.xhlc}</label></td>
				<td class="last_state">${statics2.xhlc}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>夜航次数</td>
				<td>次</td>
				<td><label class="this_state">${statics.yhcs}</label></td>
				<td class="last_state">${statics2.yhcs}</td>
				<td><labe class="result"l>+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>夜航时间</td>
				<td>小时</td>
				<td><label class="this_state">${statics.yhsj}</label></td>
				<td class="last_state">${statics2.yhsj}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>夜航里程</td>
				<td>海里</td>
				<td><label class="this_state">${statics.yhlc}</label></td>
				<td class="last_state">${statics2.yhlc}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td colspan="5"><strong>巡航范围</strong></td>
			</tr>
			<tr>
				<td>重要航路巡航</td>
				<td>次</td>
				<td><label class="this_state">${statics.zyhl}</label></td>
				<td class="last_state">${statics2.zyhl}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>锚地、停泊区</td>
				<td>次</td>
				<td><label class="this_state">${statics.mdtbq}</label></td>
				<td class="last_state">${statics2.mdtbq}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>施工作业区</td>
				<td>次</td>
				<td><label class="this_state">${statics.sgzyq}</label></td>
				<td class="last_state">${statics2.sgzyq}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>客运码头、渡口</td>
				<td>次</td>
				<td><label class="this_state">${statics.kymt}</label></td>
				<td class="last_state">${statics2.kymt}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>抛泥区</td>
				<td>次</td>
				<td><label class="this_state">${statics.pnq}</label></td>
				<td class="last_state">${statics2.pnq}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>危险品码头、装卸作业区</td>
				<td>次</td>
				<td><label class="this_state">${statics.dangerPort}</label></td>
				<td class="last_state">${statics2.dangerPort}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td> 桥(坝)区</td>
				<td>次</td>
				<td><label class="this_state">${statics.qbq}</label></td>
				<td class="last_state">${statics2.qbq}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>其他</td>
				<td>次</td>
				<td><label class="this_state">${statics.other}</label></td>
				<td class="last_state">${statics2.other}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td><strong>合    计</strong></td>
				<td>次</td>
				<td><label class="this_state">${statics.sumTotalForRange}</label></td>
				<td class="last_state">${statics2.sumTotalForRange}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td colspan="5"><strong>完成巡航工作任务</strong></td>
			</tr>
			<tr>
				<td>发现违法排放行为</td>
				<td>次</td>
				<td><label class="this_state">${statics.wfpf}</label></td>
				<td class="last_state">${statics2.wfpf}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>发现违反航行规定行为</td>
				<td>次</td>
				<td><label class="this_state">${statics.wfhxgd}</label></td>
				<td class="last_state">${statics2.wfhxgd}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>发现违法施工作业行为</td>
				<td>次</td>
				<td><label class="this_state">${statics.wfsgzy}</label></td>
				<td class="last_state">${statics2.wfsgzy}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>发现助航标志异常</td>
				<td>次</td>
				<td><label class="this_state">${statics.zhbzyc}</label></td>
				<td class="last_state">${statics2.zhbzyc}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>发现违法养殖、采砂行为</td>
				<td>次</td>
				<td><label class="this_state">${statics.wfyzcs}</label></td>
				<td class="last_state">${statics2.wfyzcs}</td>
				<td><label class="result">+12.%/+11.4%</label></td>
			</tr>
			<tr>
				<td>其它违法行为</td>
				<td>次</td>
				<td><label class="this_state">${statics.otherwfxw}</label></td>
				<td class="last_state">${statics2.otherwfxw}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>交通组织、护航</td>
				<td>次</td>
				<td><label class="this_state">${statics.jtzz}</label></td>
				<td class="last_state">${statics2.jtzz}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>参与救助</td>
				<td>次</td>
				<td><label class="this_state">${statics.cyjz}</label></td>
				<td class="last_state">${statics2.cyjz}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>被救船舶</td>
				<td>艘次</td>
				<td><label class="this_state">${statics.bjcb}</label></td>
				<td class="last_state">${statics2.bjcb}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>获救人员</td>
				<td>人</td>
				<td><label class="this_state">${statics.bjry}</label></td>
				<td class="last_state">${statics2.bjry}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td><strong>合    计</strong></td>
				<td>次</td>
				<td><label class="this_state">${statics.sumTotalForTask}</label></td>
				<td class="last_state">${statics2.sumTotalForTask}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			
			<tr>
				<td colspan="5"><strong>出动人员</strong></td>
			</tr>
			<tr>
				<td>其他人员</td>
				<td>人次</td>
				<td><label class="this_state">${statics.qtry}</label></td>
				<td class="last_state">${statics2.qtry}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>出动执法人员</td>
				<td>人次</td>
				<td><label class="this_state">${statics.cdzfry}</label></td>
				<td class="last_state">${statics2.cdzfry}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td><strong>合   计</strong></td>
				<td>人次</td>
				<td><label class="this_state">${statics.sumTotalForRy}</label></td>
				<td class="last_state">${statics2.sumTotalForRy}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>查取水口</td>
				<td>次</td>
				<td><label class="this_state">${statics.qsk}</label></td>
				<td class="last_state">${statics2.qsk}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>发放宣传资料</td>
				<td>次</td>
				<td><label class="this_state">${statics.ffxczl}</label></td>
				<td class="last_state">${statics2.ffxczl}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>检查船舶</td>
				<td>次</td>
				<td><label class="this_state">${statics.psc}</label></td>
				<td class="last_state">${statics2.psc}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>非计划停航</td>
				<td>次</td>
				<td><label>-</label></td>
				<td>--</td>
				<td><label>--</label></td>
			</tr>
			<tr>
				<td>发现违法行为合计</td>
				<td>件</td>
				<td><label class="this_state">${statics.wfxwTotal}</label></td>
				<td class="last_state">${statics2.wfxwTotal}</td>
				<td><label class="result">+12.3%/+11.4%</label></td>
			</tr>
			<tr>
				<td>计划停航</td>
				<td>次</td>
				<td><label>-</label></td>
				<td>-</td>
				<td><label>-</label></td>
			</tr>
		</tbody>
	</table>
</body>
</html>