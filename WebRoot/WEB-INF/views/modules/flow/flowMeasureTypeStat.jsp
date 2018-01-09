<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流量观测数据分析</title>
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
			$('.total_sum').each(function(index,element){
							
							var a = parseFloat($(this).html());
							
								$(this).html(a.toFixed(1));
							
							
			});
			 // 使用刚指定的配置项和数据显示图表。
	        var myChart2 = echarts.init(document.getElementById('main2'));
	     // 显示标题，图例和空的坐标轴
	        myChart2.setOption({
	            title: {
	                text: '流量观测数据按尺度分'
	            },
	            tooltip: {},
	           
	            xAxis: {
	                data: []
	            },
	            yAxis: {},
	            series: []
	        });
        	//定义图表options
            var options = {
                color : [ '#3398DB' ],
                title : {
                    text : "流量观测数据",
                   
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
                series : [ ]
            };
        	options.xAxis[0].data = ${ddjson}

        	var aaa = ${listjson}
        	var countkey = 0;
        	var legend_data = [];
        	for(var key in aaa){
        		var ser = {};
        		ser.data = aaa[key];
        		ser.name = key;
        		ser.type = 'line';
        		options.series.push(ser);
        		legend_data.push(key);
        	}
        	var se = {};
        	se.data = ${mtsjson};
        	se.name = "合计";
        	se.type = 'line';
         	options.series.push(se);
        	legend_data.push('合计');
            options.legend.data = legend_data;
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
		<li><a href="${ctx}/flow/flowMeasureType/">流量观测船种数据列表</a></li>
		<shiro:hasPermission name="flow:flowMeasureType:edit"><li><a href="${ctx}/flow/flowMeasureType/form">流量观测船种数据添加</a></li></shiro:hasPermission>
		<li  class="active"><a href="${ctx}/flow/flowMeasureType/analysis">流量观测船种数据分析</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="flowMeasureType" action="${ctx}/flow/flowMeasureType/analysis" method="post" class="breadcrumb form-search">
		
		<ul class="ul-form" style="min-height:100px;">
			
			<li><label>统计月份：</label>
				<li><label>发生日期：</label>
				<input id="beginFindTime" name="beginFlowMeasureDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${flowMeasureType.beginFlowMeasureDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input id="endFindTime" name="endFlowMeasureDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${flowMeasureType.endFlowMeasureDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				</li>
			</li>
			<li><label>统计类别：</label>
			<form:select path="flowType" class="input-xxlarge " multiple="multiple">
					<form:options items="${fns:getDictList('flow_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>断面筛选：</label>
			<form:select path="sectionVal" class="input-xxlarge " multiple="multiple">
					<form:options items="${fns:getDictList('section_name')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>断面名称</th>
				<c:forEach items="${dd}" var="month">
					<th>${month}</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${flowMeasureTypeList}" var="flowMeasures">
			<tr>
				
				<td>
					${flowMeasures.key}
				</td>
				<c:forEach items="${flowMeasures.value}" var="num">
					<td>${num}</td>
				</c:forEach>
				
			</tr>
			
		</c:forEach>
		<tr>
				<td>合计</td>
					<c:forEach items="${mts}" var="mt">
						<td class="total_sum">${mt}</td>
					</c:forEach>
				
			</tr>
		</tbody>
	</table>
	<hr>
	
	 <div id="main2" style="width: 1000px;height:400px;"></div>
	 <div id="main3" style="width: 800px;height:400px;"></div>
	 
</body>
</html>