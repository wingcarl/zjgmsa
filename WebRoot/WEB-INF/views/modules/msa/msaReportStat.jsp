<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>政务信息管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/echarts/echarts.js"  charset="UTF-8"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
			
			var myChart = echarts.init(document.getElementById('main'));
			var isPerson = $('#statType').val();
			var te;
			if(isPerson == 1){
				te='部门政务信息统计'
			}
			if(isPerson == 2){
				te='个人政务信息统计'
			}
			var year = $('#year').val();
			var quater = $('#quater').val();
			if(quater==1){
				te=year+'年第一季度' + te;
			}else if(quater == 2){
				te=year+'年第二季度' + te;
			}else if(quater == 3){
				te=year+'年第三季度' + te;
			}else if(quater == 4){
				te=year+'年第四季度' + te;
			}else if(quater == 5){
				var begin = $('#beginPublicDate').val();
				var end = $('#endPublicDate').val();
				te = begin +'~'+ end +'政务信息统计'
			}
			//定义图表options
            var options = {
        			
        		
                color : [ '#3398DB'],
                title : {
                    text :te,
                   
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
                    	interval:0,
                    	rotate:30
                    	
                    }
                } ],
                yAxis : [ {
                    type : 'value',
                    splitArea : {
                        show : true
                    }
                } ]
               
            };
            var echartData =${echartsData};
        	if(echartData.category.length==0){
        		$('#main').css('display','none');
        	}
        	options.xAxis[0].data = echartData.category;
          	options.series = echartData.series;
          	options.series[0].barWidth = '40%';
          	var op = {normal:{textStyle:{color:'#000'},show:true,position:'top'}};
          	options.series[0].label= op;
            options.legend.data = echartData.legend;
            myChart.hideLoading();
            myChart.setOption(options);
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
<div id="importBox" class="hide">
				<form id="importForm" action="${ctx}/msa/msaReport/import" method="post" enctype="multipart/form-data"
					class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
					<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
					<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
					<a href="${ctx}/msa/msaReport/import/template">下载模板</a>
				</form>
</div>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/msa/msaReport/">政务信息列表</a></li>
		<shiro:hasPermission name="msa:msaReport:edit"><li><a href="${ctx}/msa/msaReport/form">政务信息添加</a></li></shiro:hasPermission>
		<li  class="active"><a href="${ctx}/msa/msaReport/stat">政务信息统计</a></li>
		
	</ul>
	<form:form id="searchForm" modelAttribute="msaReportStat" action="${ctx}/msa/msaReport/stat" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>统计类别：</label>
				<form:select id="statType" path="statType" class="input-medium">
					<form:option value="1" label="按部门统计"/>
					<form:option value="2" label="按个人统计"/>
					
				</form:select>			
			</li>
			<li><label>年份：</label>
				<form:select id="year" path="year" class="input-medium">
					<form:option value="2017" label="2017"/>
				</form:select>			
			</li>
			<li><label>季度：</label>
				<form:select id='quater' path="quater" class="input-medium">
							<form:option value="1" label="第一季度"/>
							<form:option value="2" label="第二季度"/>
							<form:option value="3" label="第三季度"/>
							<form:option value="4" label="第四季度"/>
							<form:option value="5" label="按具体时间范围查找"/>
							
				</form:select>
			</li>
			<li><label>作者：</label>
				<form:input path="author" htmlEscape="false" maxlength="11" class="input-medium"/>
			</li>
			<li><label>部门：</label>
				<sys:treeselect id="depid" name="depid" value="${msaReportStat.depid.id}" labelName="" labelValue="${depName}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" />
			</li>
			<li><label>统计日期：</label>
				<input name="beginPublicDate" id="beginPublicDate"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${msaReportStat.beginPublicDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> - 
				<input name="endPublicDate" id="endPublicDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${msaReportStat.endPublicDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
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
				<th>作者/部门</th>
				<th>数量</th>
				<th>积分</th>
				<th>专题/调研</th>
				<th>指标完成度</th>	
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="msaReportStat">
			<tr>
				<td>
					${msaReportStat.name}
				</td>
				<td>
					${msaReportStat.count}
				</td>
				<td>
					${msaReportStat.score }
				</td>
				<td>
					${msaReportStat.ztdy }
				</td>				
				<td>
					${msaReportStat.bar }
				</td>	
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<div id="main" style="width: 800px;height:500px;margin:0 auto;margin-top:20px;></div>
</body>
</html>