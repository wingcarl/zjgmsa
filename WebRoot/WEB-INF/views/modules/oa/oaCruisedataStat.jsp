<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡航数据管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/echarts/echarts.js"  charset="UTF-8"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/oa/oaCruisedata/stat/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			
			
	        // 使用刚指定的配置项和数据显示图表。
		        var myChart2 = echarts.init(document.getElementById('main2'));
		     // 显示标题，图例和空的坐标轴
		        myChart2.setOption({
		            title: {
		                text: '异步数据加载示例'
		            },
		            tooltip: {},
		            legend: {
		                data:['销量']
		            },
		            xAxis: {
		                data: []
		            },
		            yAxis: {},
		            series: [{
		                name: '销量',
		                type: 'bar',
		                data: []
		            }]
		        });
	        	//定义图表options
	            var options = {
	                color : [ '#3398DB' ],
	                title : {
	                    text : "海巡艇巡航时间比对图表",
	                   
	                },
	                tooltip : {
	                    trigger : 'axis'
	                },
	                legend : {
	                    data : []
	                },
	                toolbox : {
	                    show : true,
	                    feature : {
	                        mark : false,
	                        saveAsImage : {show: true,title :'下载'},
	                        magicType: {
	                            type: ['line', 'bar'],
	                            title:['折线图','柱状图']
	                        }
	                    }
	                },
	                calculable : true,
	                xAxis : [ {
	                    type : 'category',
	                    data : [],
	                    axisLabel : {
	                    	interval:0
	                    	
	                    }
	                } ],
	                yAxis : [ {
	                    type : 'value',
	                    splitArea : {
	                        show : true
	                    }
	                } ],
	                series : [ {
	                    barWidth : '60%'
	                } ]
	            };
	            var echartData =${echartsData};
	        	
	        	options.xAxis[0].data = echartData.category;
              	options.series = echartData.series;
                options.legend.data = echartData.legend;
                myChart2.hideLoading();
                myChart2.setOption(options);
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
		<shiro:hasPermission name="oa:oaCruisedata:add"><li><a href="${ctx}/oa/oaCruisedata/form">巡航数据添加</a></li></shiro:hasPermission>
		<shiro:hasPermission name="oa:oaCruisedata:edit"><li  class="active"><a href="${ctx}/oa/oaCruisedata/stat">巡航数据分类统计</a></li></shiro:hasPermission>
		
	</ul>
	<form:form id="searchForm" modelAttribute="oaCruiseStat" action="${ctx}/oa/oaCruisedata/stat" method="post" class="breadcrumb form-search">
		
		<ul class="ul-form">
			
			<li><label>统计月份：</label>
				<li><label>发生日期：</label>
				<input id="beginHappenDate" name="beginHappenDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${oaCruiseStat.beginHappenDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input id="endHappenDate" name="endHappenDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${oaCruiseStat.endHappenDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				</li>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered  table-condensed">
		<thead>
			<tr >
				<th>数据船艇来源</th>
				<th>海巡艇管理部门</th>
				<th>巡航时间</th>
				<th>巡航次数</th>
				<th>夜航时间</th>
				<th>夜航次数</th>
				<th>重要航路</th>
				<th>锚地停泊区</th>
				<th>施工作业区</th>
				<th>发现违反航<br>行规定行为</th>
				<th>发现违法施<br>工作业行为</th>
				<th>客运码头<br>渡口</th>
				<th>发现助航<br>标志异常</th>
				<th>夜航（次）</th>
				<th>抛泥区</th>
				<th>发现违法养<br>殖采砂行为</th>
				<th>检查船舶<br>（艘次）</th>
				<th>危险品码头<br>装卸作业区</th>
				<th>交通组织<br>护航</th>
				<th>发放宣传<br>资料（份）</th>
				<th>桥（坝）区</th>
				<th>参与救助</th>
				<th>海巡艇发现<br>违章（艘次）</th>
				<th>海巡艇发现违<br>章处理起数</th>
				<th>取水口</th>
				<th>发现违法<br>排放行为</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${statics}" var="oaCruiseStat">
			<tr>
				
				<td>
					${oaCruiseStat.name}
				</td>
				<td>
					${oaCruiseStat.depName}
				</td>
				<td>
					${oaCruiseStat.totalTime}
				</td>
				<td>
					${oaCruiseStat.xhcs}
				</td>
				<td>
					${oaCruiseStat.yehangTime}
				</td>
				<td>
					${oaCruiseStat.yehangcs}
				</td>
				<td>
					${oaCruiseStat.zyhl}
				</td>
				<td>
					${oaCruiseStat.mdtbq}
				</td>
				<td>
					${oaCruiseStat.sgzyq}
				</td>
				<td>
					${oaCruiseStat.wfhxgd}
				</td>
				<td>
					${oaCruiseStat.wfsgzy}
				</td>
				<td>
					${oaCruiseStat.kymt}
				</td>
				<td>
					${oaCruiseStat.zhbzyc}
				</td>
				<td>
					${oaCruiseStat.yhcs}
				</td>
				<td>
					${oaCruiseStat.pnq}
				</td>
				<td>
					${oaCruiseStat.wfyzcs}
				</td>
				<td>
					${oaCruiseStat.psc}
				</td>
				<td>
					${oaCruiseStat.dangerPort}
				</td>
				<td>
					${oaCruiseStat.jtzz}
				</td>
				<td>
					${oaCruiseStat.ffxczl}
				</td>
				<td>
					${oaCruiseStat.qbq}
				</td>
				<td>
					${oaCruiseStat.cyjz}
				</td>
				<td>
					${oaCruiseStat.fxwz}
				</td>
				<td>
					${oaCruiseStat.clwz}
				</td>
				<td>
					${oaCruiseStat.qsk}
				</td>
				<td>
					${oaCruiseStat.wfpf}
				</td>
				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<hr>
	
	 <div id="main2" style="width: 800px;height:400px;"></div>
	 <div id="main3" style="width: 800px;height:400px;"></div>
	 
</body>
</html>