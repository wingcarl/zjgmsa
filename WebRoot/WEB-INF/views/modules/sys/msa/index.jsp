]<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>首页</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/nifty/js/nifty.min.js"></script>
	<script src="${ctxStatic}/nifty/js/demo/dashboard.js"></script>
	<script src="${ctxStatic}/nifty/js/demo/nifty-demo.min.js"></script>
	<script src="${ctxStatic}/nifty/plugins/morris-js/morris.min.js"></script>
	<script src="${ctxStatic}/nifty/plugins/morris-js/raphael-js/raphael.min.js"></script>
	<script src="${ctxStatic}/nifty/plugins/sparkline/jquery.sparkline.min.js"></script>
	<script src="${ctxStatic}/nifty/js/bootstrap.min.js"></script>
	<script src="${ctxStatic}/echarts/echarts.js" charset="UTF-8"></script>
	<script src="${ctxStatic}/echarts/china.js" charset="UTF-8"></script>
	
   

	<!--Bootstrap Stylesheet [ REQUIRED ]-->
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/nifty/css/bootstrap.min.css">
	
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/nifty/css/nifty.min.css">
	<!--Nifty Premium Icon [ DEMONSTRATION ]-->
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/nifty/css/demo/nifty-demo-icons.min.css">

    <!--Demo [ DEMONSTRATION ]-->
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/nifty/css/demo/nifty-demo.min.css">
        <link rel="stylesheet" type="text/css" href="${ctxStatic}/nifty/plugins/morris-js/morris.min.css">
    <script type="text/javascript">
		$(document).ready(function() {
			 $('#cjg').hide();
			 $('#pbb').hide();
			 var myChart = echarts.init(document.getElementById('mainChart'));
				var leg = ${echartsLegend};
				var ct = ${cruiseTotalTime};
				var yh = ${cruiseYehangTime};
				var pie = ${pie};
				var today = ${today};
				var mo = ${mo};
				var weekPlan = ${weekPlan};
			
				function getValue(plan){
					if(typeof(plan) == 'undefined'){
						return '未填写';
						
					}else{
						return plan;
					}
				}
	
				$('#sd').html(today.sd.join(","));
				$('#si').html(today.si.join(","));
				$('#ss').html(today.ss.join(","));
				$('#vts').html(mo.vts+" 值班领导:"+mo.vtsld);
				var zhzx = "局领导："+mo.jld+ " 中层："+mo.zbzc+" 指挥中心白班："+mo.zhzxbb+"  夜班："+mo.zhzxyb+"  0512-80152751(751)";
				var zfzd = "白班："+mo.zfzdbb+" 本部夜班："+mo.zfzdyb+"  福中夜班："+mo.fjsyb +" 0512-58931993 <br>"+"<strong>海巡06809:</strong>"+getValue(weekPlan['海巡06809'])+"<strong> 海巡06813:</strong>"+getValue(weekPlan['海巡06813']);
				var gqc = "白班1号台：" + mo.gqc1+" 白班2号台："+mo.gqc2+"  本部夜班："+mo.gqcyb+"  水上夜班："+mo.gqcss+" 0512-58331953(361)<br>"+"<strong>海巡06808:</strong>"+getValue(weekPlan['海巡06808'])+"<strong> 海巡06812:</strong>"+getValue(weekPlan['海巡06812']);
				var jfc = "白班1号台：" + mo.jfc1+" 白班2号台："+mo.jfc2+"  本部夜班："+mo.jfcyb+"  水上夜班："+mo.jfcss+" 0512-58559189<br>"+"<strong>海巡06811:</strong>"+getValue(weekPlan['海巡06811']);
				var dq = "白班：" + mo.dqbb +"  夜班："+mo.dqyb +" 0512-55391872 <br>"+"<strong>海巡06816:</strong>"+getValue(weekPlan['海巡06816']);
				var nfc = "白班：" + mo.nfcbb +"  夜班："+mo.nfcyb+" 0512-58635110 <br>"+"<strong>海巡06810:</strong>"+getValue(weekPlan['海巡06810']);
				var bsq = "白班：" + mo.bsqbb +"  夜班："+mo.bsqyb+" 0512-56366900 <br>"+"<strong>海巡06806:</strong>"+getValue(weekPlan['海巡06806']);
				$('#zhzx').html(zhzx);
				$('#zfzd').html(zfzd);
				$('#gqc').html(gqc);
				$('#jfc').html(jfc);
				$('#dq').html(dq);
				$('#nfc').html(nfc);
				$('#bsq').html(bsq);
		        var optionC = {
		        	    backgroundColor: "#394056",
		        	    color: ['#ffd285', '#ff733f', '#ec4863'],

		        	    title: [{
		        	        text: '张家港海事局巡航数据',
		        	        left: '1%',
		        	        top: '6%',
		        	        textStyle: {
		        	            color: '#ffd285'
		        	        }
		        	    }, {
		        	        text: '占比分析',
		        	        left: '83%',
		        	        top: '6%',
		        	        textAlign: 'center',
		        	        textStyle: {
		        	            color: '#ffd285'
		        	        }
		        	    }],
		        	    tooltip: {
		        	        trigger: 'axis'
		        	    },
		        	    legend: {
		        	        x: 300,
		        	        top: '7%',
		        	        textStyle: {
		        	            color: '#ffd285',
		        	        },
		        	        data: ['全局巡航时间','全局夜航时间']
		        	    },
		        	    grid: {
		        	        left: '1%',
		        	        right: '35%',
		        	        top: '16%',
		        	        bottom: '6%',
		        	        containLabel: true
		        	    },
		        	    toolbox: {
		        	        "show": false,
		        	        feature: {
		        	            saveAsImage: {}
		        	        }
		        	    },
		        	    xAxis: {
		        	        type: 'category',
		        	        "axisLine": {
		        	            lineStyle: {
		        	                color: '#c0576d'
		        	            }
		        	        },
		        	        "axisTick": {
		        	            "show": false
		        	        },
		        	        axisLabel: {
		        	            textStyle: {
		        	                color: '#ffd285'
		        	            }
		        	        },
		        	        boundaryGap: false,
		        	        data:leg 
		        	    },
		        	    yAxis: {
		        	        "axisLine": {
		        	            lineStyle: {
		        	                color: '#c0576d'
		        	            }
		        	        },
		        	        splitLine: {
		        	            show: true,
		        	            lineStyle: {
		        	                color: '#c0576d'
		        	            }
		        	        },
		        	        "axisTick": {
		        	            "show": false
		        	        },
		        	        axisLabel: {
		        	            textStyle: {
		        	                color: '#ffd285'
		        	            }
		        	        },
		        	        type: 'value'
		        	    },
		        	    series: [{
		        	        name: '全局巡航时间',
		        	        smooth: true,
		        	        type: 'line',
		        	        symbolSize: 8,
		        	      	symbol: 'circle',
		        	        data: ct
		        	    }, 
		        	    {
		        	        name: '全局夜航时间',
		        	        smooth: true,
		        	        type: 'line',
		        	        symbolSize: 8,
		        	      	symbol: 'circle',
		        	        data: yh
		        	    },
		        	    {
		        	        type: 'pie',
		        	        center: ['83%', '33%'],
		        	        radius: ['25%', '30%'],
		        	        label: {
		        	            normal: {
		        	                position: 'center'
		        	            }
		        	        },
		        	        data: [{
		        	            value: pie.sumYehang,
		        	            name: '夜航占比分析',
		        	            itemStyle: {
		        	                normal: {
		        	                    color: '#ffd285'
		        	                }
		        	            },
		        	            label: {
		        	                normal: {
		        	                    formatter: '{d} %',
		        	                    textStyle: {
		        	                        color: '#ffd285',
		        	                        fontSize: 20

		        	                    }
		        	                }
		        	            }
		        	        }, {
		        	            value: pie.sumCruise-pie.sumYehang,
		        	            name: '占位',
		        	            tooltip: {
		        	                show: false
		        	            },
		        	            itemStyle: {
		        	                normal: {
		        	                    color: '#b04459'
		        	                }
		        	            },
		        	            label: {
		        	                normal: {
		        	                    textStyle: {
		        	                        color: '#ffd285',
		        	                    },
		        	                    formatter: '\n夜航占比'
		        	                }
		        	            }
		        	        }]
		        	    },


		        	    {
		        	        type: 'pie',
		        	        center: ['83%', '72%'],
		        	        radius: ['25%', '30%'],
		        	        label: {
		        	            normal: {
		        	                position: 'center'
		        	            }
		        	        },
		        	        data: [{
		        	            value: today.count,
		        	            name: '今日数据完成度',
		        	            itemStyle: {
		        	                normal: {
		        	                    color: '#ff733f'
		        	                }
		        	            },
		        	            label: {
		        	                normal: {
		        	                    formatter: '{d} %',
		        	                    textStyle: {
		        	                        color: '#ff733f',
		        	                        fontSize: 20

		        	                    }
		        	                }
		        	            }
		        	        }, {
		        	            value: 21-today.count,
		        	            name: '占位',
		        	            tooltip: {
		        	                show: false
		        	            },
		        	            itemStyle: {
		        	                normal: {
		        	                    color: '#b04459'


		        	                }
		        	            },
		        	            label: {
		        	                normal: {
		        	                    textStyle: {
		        	                        color: '#ff733f',
		        	                    },
		        	                    formatter: '\n数据完成度'
		        	                }
		        	            }
		        	        }]
		        	    }]
		        	}
		     // 显示标题，图例和空的坐标轴
		        myChart.hideLoading();
	            myChart.setOption(optionC);

	          
			if (!(navigator.userAgent.indexOf('MSIE') >= 0) ){
				 $('#cjg').show();
				 $('#pbb').show();
				 var dateList = ${scheduleMap};
		            var heatmapData = [];
		            var lunarData = [];
		            for (var i = 0; i < dateList.length; i++) {
		                var date1 = new Date(dateList[i][0]);
		                var date2 = new Date();
		                var s;
		                s = date2.getFullYear()+"-"+(date2.getMonth()+1)+"-"+date2.getDate();
		                date2 = new Date(s);
		                if(date1.getTime()==date2.getTime()){
		                	heatmapData.push([
			                    dateList[i][0],
			                    201
			                ]);
		                }else if(date1.getDay()==0 || date1.getDay()==6){
								heatmapData.push([
					                dateList[i][0],
					                101
					            ]);
						}else{
		                	heatmapData.push([
			                    dateList[i][0],
			                    1
			                ]);
		                }
		            	
		                var aaa = [];
		                aaa = dateList[i][1].split(",")
		                if(aaa.length==1){
		                	if(aaa[0]=='夜班'){
		                		aaa[0] = '';
		                		aaa[1] = '夜班';
		                	}
		                }
		                lunarData.push([
		                    dateList[i][0],
		                    1,
		                    aaa[0],
		                   	aaa[1]
		                ]);
		            }


		            option = {
		                tooltip: {
		                    formatter: function (params) {
		                        return ''
		                    }
		                },

		                visualMap:{
		                	
		                	show:false,
		                	min:0,
		                	max:300,
		                	splitNumber:100,
		                	calculable:true,
		                	seriesIndex:[2],
		                	orient:'horizontal',
		                	left:'center',
		                	bottom:20,
		                	color:['#50a3ba','#bbffff'],
		                	
		                	controller:{
		                		inRange:{
		                			opacity:0.5
		                		}
		                	}
		                },
		                calendar: [{
		                    left: 'center',
		                    top: 'middle',
		                    cellSize: [70, 70],
		                    yearLabel: {show: false},
		                    orient: 'vertical',
		                    dayLabel: {
		                        firstDay: 1,
		                        nameMap: 'cn'
		                    },
		                    monthLabel: {
		                        show: false
		                    },
		                    range: '${sdfd}'
		                }],

		                series: [{
		                    type: 'scatter',
		                    coordinateSystem: 'calendar',
		                    symbolSize: 1,
		                    label: {
		                        normal: {
		                            show: true,
		                            formatter: function (params) {
		                                var d = echarts.number.parseDate(params.value[0]);
		                                return d.getDate() + '\n\n' +(params.value[2] || '') + '\n\n';
		                            },
		                            textStyle: {
		                                color: '#000'
		                            }
		                        }
		                    },
		                  
		                    data: lunarData
		                }, {
		                    type: 'scatter',
		                    coordinateSystem: 'calendar',
		                    symbolSize: 1,
		                    label: {
		                        normal: {
		                            show: true,
		                            formatter: function (params) {
		                                return '\n\n\n' + (params.value[3] || '');
		                            },
		                            textStyle: {
		                                fontSize: 14,
		                                fontWeight: 700,
		                                color: '#a00'
		                            }
		                        }
		                    },
		                    data: lunarData
		                },{
		                	name:'',
		                	type:'heatmap',
		                	coordinateSystem:'calendar',
		                	data:heatmapData
		                }]
		            };
		            $('#calendar').css('height',$('#list_group').height());

					 var calendar = echarts.init(document.getElementById('calendar'));

					calendar.setOption(option);
					
				  var geoCoordMap = ${md.geoPos}
		            

		            var data = ${md.geoName}

		            var convertData = function (data) {
		                var res = [];
		                for (var i = 0; i < data.length; i++) {
		                    var geoCoord = geoCoordMap[data[i].name];
		                    if (geoCoord) {
		                        res.push({
		                            name: data[i].name,
		                            value: geoCoord.concat(data[i].value)
		                        });
		                    }
		                }
		                return res;
		            };

		            var convertedData = [
		                convertData(data),
		                convertData(data.sort(function (a, b) {
		                    return b.value - a.value;
		                }).slice(0, 6))
		            ];

		            
		            option = {
		                    backgroundColor: '#404a59',
		                    animation: true,
		                    animationDuration: 1000,
		                    animationEasing: 'cubicInOut',
		                    animationDurationUpdate: 1000,
		                    animationEasingUpdate: 'cubicInOut',
		                    title: [
		                        {
		                            text: '2017年张家港辖区在港船舶累计船次',
		                            subtext: 'data from zjgmsa',
		                            left: 'center',
		                            textStyle: {
		                                color: '#fff'
		                            }
		                        },
		                        {
		                            id: 'statistic',
		                            right: 120,
		                            top: 40,
		                            width: 100,
		                            textStyle: {
		                                color: '#fff',
		                                fontSize: 16
		                            }
		                        }
		                    ],
		                    toolbox: {
		                        iconStyle: {
		                            normal: {
		                                borderColor: '#fff'
		                            },
		                            emphasis: {
		                                borderColor: '#b1e4ff'
		                            }
		                        },
		                        feature: {
		                         
		                            brush: {
		                                type: ['rect', 'polygon', 'clear'],
		                                title:{'rect':'矩形选择','polygon':'圈选','clear':'清除选择'}
		                            },
		                            saveAsImage : {show: true,title:'下载图片',type:'jpeg'}
		                        }
		                    },
		                    brush: {
		                        outOfBrush: {
		                            color: '#abc'
		                        },
		                        brushStyle: {
		                            borderWidth: 2,
		                            color: 'rgba(0,0,0,0.2)',
		                            borderColor: 'rgba(0,0,0,0.5)',
		                        },
		                        seriesIndex: [0, 1],
		                        throttleType: 'debounce',
		                        throttleDelay: 300,
		                        geoIndex: 0
		                    },
		                    geo: {
		                        map: 'china',
		                        left: '10',
		                        right: '35%',
		                        center: [117.98561551896913, 31.205000490896193],
		                        zoom: 2.5,
		                        label: {
		                            emphasis: {
		                                show: false
		                            }
		                        },
		                        roam: true,
		                        itemStyle: {
		                            normal: {
		                                areaColor: '#323c48',
		                                borderColor: '#111'
		                            },
		                            emphasis: {
		                                areaColor: '#2a333d'
		                            }
		                        }
		                    },
		                    tooltip : {
		                        trigger: 'item'
		                    },
		                    grid: {
		                        right: 40,
		                        top: 100,
		                        bottom: 40,
		                        width: '30%'
		                    },
		                    xAxis: {
		                        type: 'value',
		                        scale: true,
		                        position: 'top',
		                        boundaryGap: false,
		                        splitLine: {show: false},
		                        axisLine: {show: false},
		                        axisTick: {show: false},
		                        axisLabel: {margin: 2, textStyle: {color: '#aaa'}},
		                    },
		                    yAxis: {
		                        type: 'category',
		                        name: 'TOP 20',
		                        nameGap: 16,
		                        axisLine: {show: false, lineStyle: {color: '#ddd'}},
		                        axisTick: {show: false, lineStyle: {color: '#ddd'}},
		                        axisLabel: {interval: 0, textStyle: {color: '#ddd'}},
		                        data: []
		                    },
		                    visualMap: {
		                        min: 10,
		                        max: 2000,
		                        type: 'continuous', 
		                        realtime: false,
		                        calculable : true,
		                        inRange: {
		                            color: ['#d94e5d','#eac736','#50a3ba'].reverse()
		                        },
		                        textStyle: {
		                            color: '#fff'
		                        }
		                    },
		                    
		                    series : [
		                        {
		                            name: '艘次',
		                            type: 'scatter',
		                            coordinateSystem: 'geo',
		                            data: convertedData[0],
		                            symbolSize: function (val) {
		                                if((val[2]/50) > 20)
		                                	return 20;
		                                else{
		                                	return Math.max(val[2] / 50, 5);
		                                }
		                            	
		                            },
		                            label: {
		                                normal: {
		                                    formatter: '{b}',
		                                    position: 'right',
		                                    show: false
		                                },
		                                emphasis: {
		                                    show: true
		                                }
		                            },
		                            itemStyle: {
		                                normal: {
		                                    color: '#ddb926'
		                                }
		                            }
		                        },
		                        {
		                            name: 'Top 5',
		                            type: 'effectScatter',
		                            coordinateSystem: 'geo',
		                            data: convertedData[1],
		                            symbolSize: function (val) {
		                            	if((val[2]/50) > 20)
		                                	return 20;
		                                else{
		                                	return Math.max(val[2] / 50, 5);
		                                }
		                            	
		                            },
		                            showEffectOn: 'emphasis',
		                            rippleEffect: {
		                                brushType: 'stroke'
		                            },
		                            hoverAnimation: true,
		                            label: {
		                                normal: {
		                                    formatter: '{b}',
		                                    position: 'right',
		                                    show: true
		                                }
		                            },
		                            itemStyle: {
		                                normal: {
		                                    color: '#f4e925',
		                                    shadowBlur: 10,
		                                    shadowColor: '#333'
		                                }
		                            },
		                            zlevel: 1
		                        },
		                        {
		                            id: 'bar',
		                            zlevel: 2,
		                            type: 'bar',
		                            symbol: 'none',
		                            itemStyle: {
		                                normal: {
		                                    color: '#ddb926'
		                                }
		                            },
		                            data: []
		                        },{
		                            name: '艘次',
		                            type: 'heatmap',
		                            coordinateSystem: 'geo',
		                            data: convertedData[0]
		                        }
		                    ]
		                };

						var myChartGeo = echarts.init(document.getElementById('mainGeo'));
		                myChartGeo.on('brushselected', renderBrushed);
		                myChartGeo.setOption(option);
		                setTimeout(function() {
		                    myChartGeo.dispatchAction({
		                        type: 'brush',
		                        areas: [{
		                            geoIndex: 0,
		                            brushType: 'polygon',
		                            coordRange: [
		                                [119.72, 34.85],
		                                [119.68, 34.85],
		                                [119.5, 34.84],
		                                [119.19, 34.77],
		                                [118.76, 34.63],
		                                [118.6, 34.6],
		                                [118.46, 34.6],
		                                [118.33, 34.57],
		                                [118.05, 34.56],
		                                [117.6, 34.56],
		                                [117.41, 34.56],
		                                [117.25, 34.56],
		                                [117.11, 34.56],
		                                [117.02, 34.56],
		                                [117, 34.56],
		                                [116.94, 34.56],
		                                [116.94, 34.55],
		                                [116.9, 34.5],
		                                [116.88, 34.44],
		                                [116.88, 34.37],
		                                [116.88, 34.33],
		                                [116.88, 34.24],
		                                [116.92, 34.15],
		                                [116.98, 34.09],
		                                [117.05, 34.06],
		                                [117.19, 33.96],
		                                [117.29, 33.9],
		                                [117.43, 33.8],
		                                [117.49, 33.75],
		                                [117.54, 33.68],
		                                [117.6, 33.65],
		                                [117.62, 33.61],
		                                [117.64, 33.59],
		                                [117.68, 33.58],
		                                [117.7, 33.52],
		                                [117.74, 33.5],
		                                [117.74, 33.46],
		                                [117.8, 33.44],
		                                [117.82, 33.41],
		                                [117.86, 33.37],
		                                [117.9, 33.3],
		                                [117.9, 33.28],
		                                [117.9, 33.27],
		                                [118.09, 32.97],
		                                [118.21, 32.7],
		                                [118.29, 32.56],
		                                [118.31, 32.5],
		                                [118.35, 32.46],
		                                [118.35, 32.42],
		                                [118.35, 32.36],
		                                [118.35, 32.34],
		                                [118.37, 32.24],
		                                [118.37, 32.14],
		                                [118.37, 32.09],
		                                [118.44, 32.05],
		                                [118.46, 32.01],
		                                [118.54, 31.98],
		                                [118.6, 31.93],
		                                [118.68, 31.86],
		                                [118.72, 31.8],
		                                [118.74, 31.78],
		                                [118.76, 31.74],
		                                [118.78, 31.7],
		                                [118.82, 31.64],
		                                [118.82, 31.62],
		                                [118.86, 31.58],
		                                [118.86, 31.55],
		                                [118.88, 31.54],
		                                [118.88, 31.52],
		                                [118.9, 31.51],
		                                [118.91, 31.48],
		                                [118.93, 31.43],
		                                [118.95, 31.4],
		                                [118.97, 31.39],
		                                [118.97, 31.37],
		                                [118.97, 31.34],
		                                [118.97, 31.27],
		                                [118.97, 31.21],
		                                [118.97, 31.17],
		                                [118.97, 31.12],
		                                [118.97, 31.02],
		                                [118.97, 30.93],
		                                [118.97, 30.87],
		                                [118.97, 30.85],
		                                [118.95, 30.8],
		                                [118.95, 30.77],
		                                [118.95, 30.76],
		                                [118.93, 30.7],
		                                [118.91, 30.63],
		                                [118.91, 30.61],
		                                [118.91, 30.6],
		                                [118.9, 30.6],
		                                [118.88, 30.54],
		                                [118.88, 30.51],
		                                [118.86, 30.51],
		                                [118.86, 30.46],
		                                [118.72, 30.18],
		                                [118.68, 30.1],
		                                [118.66, 30.07],
		                                [118.62, 29.91],
		                                [118.56, 29.73],
		                                [118.52, 29.63],
		                                [118.48, 29.51],
		                                [118.44, 29.42],
		                                [118.44, 29.32],
		                                [118.43, 29.19],
		                                [118.43, 29.14],
		                                [118.43, 29.08],
		                                [118.44, 29.05],
		                                [118.46, 29.05],
		                                [118.6, 28.95],
		                                [118.64, 28.94],
		                                [119.07, 28.51],
		                                [119.25, 28.41],
		                                [119.36, 28.28],
		                                [119.46, 28.19],
		                                [119.54, 28.13],
		                                [119.66, 28.03],
		                                [119.78, 28],
		                                [119.87, 27.94],
		                                [120.03, 27.86],
		                                [120.17, 27.79],
		                                [120.23, 27.76],
		                                [120.3, 27.72],
		                                [120.42, 27.66],
		                                [120.52, 27.64],
		                                [120.58, 27.63],
		                                [120.64, 27.63],
		                                [120.77, 27.63],
		                                [120.89, 27.61],
		                                [120.97, 27.6],
		                                [121.07, 27.59],
		                                [121.15, 27.59],
		                                [121.28, 27.59],
		                                [121.38, 27.61],
		                                [121.56, 27.73],
		                                [121.73, 27.89],
		                                [122.03, 28.2],
		                                [122.3, 28.5],
		                                [122.46, 28.72],
		                                [122.5, 28.77],
		                                [122.54, 28.82],
		                                [122.56, 28.82],
		                                [122.58, 28.85],
		                                [122.6, 28.86],
		                                [122.61, 28.91],
		                                [122.71, 29.02],
		                                [122.73, 29.08],
		                                [122.93, 29.44],
		                                [122.99, 29.54],
		                                [123.03, 29.66],
		                                [123.05, 29.73],
		                                [123.16, 29.92],
		                                [123.24, 30.02],
		                                [123.28, 30.13],
		                                [123.32, 30.29],
		                                [123.36, 30.36],
		                                [123.36, 30.55],
		                                [123.36, 30.74],
		                                [123.36, 31.05],
		                                [123.36, 31.14],
		                                [123.36, 31.26],
		                                [123.38, 31.42],
		                                [123.46, 31.74],
		                                [123.48, 31.83],
		                                [123.48, 31.95],
		                                [123.46, 32.09],
		                                [123.34, 32.25],
		                                [123.22, 32.39],
		                                [123.12, 32.46],
		                                [123.07, 32.48],
		                                [123.05, 32.49],
		                                [122.97, 32.53],
		                                [122.91, 32.59],
		                                [122.83, 32.81],
		                                [122.77, 32.87],
		                                [122.71, 32.9],
		                                [122.56, 32.97],
		                                [122.38, 33.05],
		                                [122.3, 33.12],
		                                [122.26, 33.15],
		                                [122.22, 33.21],
		                                [122.22, 33.3],
		                                [122.22, 33.39],
		                                [122.18, 33.44],
		                                [122.07, 33.56],
		                                [121.99, 33.69],
		                                [121.89, 33.78],
		                                [121.69, 34.02],
		                                [121.66, 34.05],
		                                [121.64, 34.08]
		                            ]
		                        }]
		                    });
		                }, 0);

		                function renderBrushed(params) {
		                    var mainSeries = params.batch[0].selected[0];

		                    var selectedItems = [];
		                    var categoryData = [];
		                    var barData = [];
		                    var maxBar = 30;
		                    var sum = 0;
		                    var count = 0;

		                    for (var i = 0; i < mainSeries.dataIndex.length; i++) {
		                        var rawIndex = mainSeries.dataIndex[i];
		                        var dataItem = convertedData[0][rawIndex];
		                        var pmValue = dataItem.value[2];

		                        sum += pmValue;
		                        count++;

		                        selectedItems.push(dataItem);
		                    }

		                    selectedItems.sort(function (a, b) {
		                        return b.value[2] - a.value[2];
		                    });

		                    for (var i = 0; i < Math.min(selectedItems.length, maxBar); i++) {
		                        categoryData.push(selectedItems[i].name);
		                        barData.push(selectedItems[i].value[2]);
		                    }
		                    categoryData = categoryData.reverse();
		                    barData = barData.reverse();
		                    this.setOption({
		                        yAxis: {
		                            data: categoryData
		                        },
		                        xAxis: {
		                            axisLabel: {show: !!count}
		                        },
		                        title: {
		                            id: 'statistic',
		                            text: '选定区域前二十的城市'
		                        },
		                        series: {
		                            id: 'bar',
		                            data: barData
		                        }
		                    });
		                }
				}
			
            
			
		});
	</script>
</head>
<body>
       <div id="page-content">
					<div class="row">
					    <div class="col-lg-6">					      
					            <div class="panel">
					                <div class="panel-heading">
					                    <h3 class="panel-title">今日值班动态</h3>
					                </div>
					                <div class="panel-body"  id="list_group">
					                    <!--Custom Content-->
					                    <!--===================================================-->
					                    <div class="list-group">
					                        <a href="#" class="list-group-item active">
					                            <h4 class="list-group-item-heading">机关值班</h4>
					                            <p class="list-group-item-text"><h5><label id="zhzx"></label></h5></p>
					                        </a>
					                         <a href="#" class="list-group-item">
					                            <h4 class="list-group-item-heading">VTS中心</h4>
					                            <p class="list-group-item-text"><label id="vts"></label>  0512-58330432(300)</p>
					                        </a>
					                        <a href="#" class="list-group-item">
					                            <h4 class="list-group-item-heading">港区海事处</h4>
					                            <p class="list-group-item-text"><label id="gqc"></label> </p>
					                        </a>
					                        <a href="#" class="list-group-item">
					                            <h4 class="list-group-item-heading">锦丰海事处</h4>
					                            <p class="list-group-item-text"> <label id="jfc"></label> </p>
					                        </a>
					                        <a href="#" class="list-group-item">
					                            <h4 class="list-group-item-heading">海巡执法支队</h4>
					                            <p class="list-group-item-text"><label id="zfzd"></label> </p>
					                        </a>
					                         <a href="#" class="list-group-item">
					                            <h4 class="list-group-item-heading">保税区办事处</h4>
					                            <p class="list-group-item-text"><label id="bsq"></label> </p>
					                        </a>
					                        <a href="#" class="list-group-item">
					                            <h4 class="list-group-item-heading">南丰办事处</h4>
					                            <p class="list-group-item-text"> <label id="nfc"></label> </p>
					                        </a>
					                        <a href="#" class="list-group-item">
					                            <h4 class="list-group-item-heading">沪通大桥办事处</h4>
					                            <p class="list-group-item-text"><label id="dq"></label>  </p>
					                        </a>
					                    </div>
					                    <!--===================================================-->
					
					                </div>
					            </div>
					        </div>
					        <div class="col-lg-6" id="pbb">
						       
						            <div class="panel">
						                <div class="panel-heading">
						                    <h3 class="panel-title">个人排班表</h3>
						                </div>
						                <div class="panel-body">
						                 <div id="calendar" ></div>
						       			</div>
						      		</div>
						      </div>
					    </div>
					 <div class="row" id="cjg" >
					    <div class="col-lg-12">
					        <div class="panel">
					            <div class="panel-heading">
					                <h3 class="panel-title">张家港辖区在港船舶船籍港分布</h3>
					            </div>
					            <div class="panel-body">
					
					                <div id="mainGeo" style="height:600px" ></div>

					
					            </div>
					        </div>
					    </div>
					    
					  </div>   
					<div class="row" >
					    <div class="col-lg-12">
					        <div class="panel">
					            <div class="panel-heading">
					                <h3 class="panel-title">统计分析图</h3>
					            </div>
					            <div class="panel-body">
					
					                <div id="mainChart" style="height:400px" ></div>

					
					            </div>
					        </div>
					    </div>
					    
					  </div>
					  
					  
						 <div class="row" >
						 <div class="col-lg-12">
						        <div class="panel">
					                <div class="panel-heading">
					                    <h3 class="panel-title">今日待办</h3>
					                </div>
					                <div class="panel-body">
					                    <div class="list-group">
					                        <a href="#" class="list-group-item">
					                            <h4 class="list-group-item-heading">未填写巡航数据的海巡艇</h4>
					                            <p class="list-group-item-text" id="ss"></p>
					                        </a>
					                        <a href="#" class="list-group-item">
					                            <h4 class="list-group-item-heading">未填写电子巡航的部门</h4>
					                            <p class="list-group-item-text" id="sd"></p>
					                        </a>
					                        <a href="#" class="list-group-item">
					                            <h4 class="list-group-item-heading">未导入在港船舶的部门</h4>
					                            <p class="list-group-item-text" id="si"></p>
					                        </a>
					                        
					                    </div>
					                    <!--===================================================-->
									</div>
				            	</div>
						</div>
					  </div>
					
					
					
                </div>
                <!--===================================================-->
                <!--End page content-->


</body>
</html>