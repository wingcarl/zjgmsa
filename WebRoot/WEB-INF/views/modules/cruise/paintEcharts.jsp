<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>巡航数据统计分析表</title>
	<meta name="decorator" content="default"/>
	<meta charset="utf-8">
	
	<script src="${ctxStatic}/echarts/echarts.js"  charset="UTF-8"></script>
	<script src="${ctxStatic}/echarts/china.js"  charset="UTF-8"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/cruise/statics/paintEcharts");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			
			
	        // 使用刚指定的配置项和数据显示图表。
		        var myChart2 = echarts.init(document.getElementById('main2'));
		        var myChart3 = echarts.init(document.getElementById('main3'));
		        var myChart4 = echarts.init(document.getElementById('main4'));
		        var myChart5 = echarts.init(document.getElementById('main5'));
		        var myChart6 = echarts.init(document.getElementById('main6'));
		        var myChart7 = echarts.init(document.getElementById('main7'));
		        var myChart1 = echarts.init(document.getElementById('main1'));
				var leg = ${echartsLegend};
				var ct = ${cruiseTotalTime};
				var yh = ${cruiseYehangTime};
				var pie = ${pie};
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
		        	            value: 435,
		        	            name: '出动执法人员占比分析',
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
		        	            value: 100,
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
		        	                    formatter: '\n出动执法人员占比'
		        	                }
		        	            }
		        	        }]
		        	    }]
		        	}
		     // 显示标题，图例和空的坐标轴
		     
		     	
		        myChart1.hideLoading();
                myChart1.setOption(optionC);
	        	//定义图表options
	            var options = {
	        			
	        		
	                color : [ '#3398DB','#68228B'],
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
	                    	interval:0,
	                    	rotate:30
	                    	
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
          		options.series[0].barWidth = '30%';
          		options.series[1].barWidth = '30%';
          		var op = {normal:{textStyle:{color:'#000'},show:true,position:'top'}};
          		options.series[0].label= op;
          		options.series[1].label= op;
            	options.legend.data = echartData.legend;
	        	options.xAxis[0].data = echartData.category;
              	options.series = echartData.series;
                options.legend.data = echartData.legend;
                myChart2.hideLoading();
                myChart2.setOption(options);
                
                
               
	        	//定义图表options
	            var options1 = {
	                color :  [ '#3398DB','#68228B'],
	                title : {
	                    text : "海巡艇巡航次数比对图表",
	                   
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
	                } ],
	                series : [ {
	                    barWidth : '60%'
	                } ]
	            };
	            var echartData1 =${echartsCount};
	            options1.xAxis[0].data = echartData1.category;
          		options1.series = echartData1.series;
          		options1.series[0].barWidth = '30%';
          		options1.series[1].barWidth = '30%';
          		var op = {normal:{textStyle:{color:'#000'},show:true,position:'top'}};
          		options1.series[0].label= op;
          		options1.series[1].label= op;
            	options1.legend.data = echartData1.legend;
	        	
	        	options1.xAxis[0].data = echartData1.category;
              	options1.series = echartData1.series;
                options1.legend.data = echartData1.legend;
                myChart3.hideLoading();
                myChart3.setOption(options1);
                
                
            	//定义图表options
	            var options2 = {
	                color :  [ '#3398DB','#68228B'],
	                title : {
	                    text : "巡航时间个人排名",
	                   
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
	            var echartData2 =${echartsPeople};
	            options2.xAxis[0].data = echartData2.category;
          		options2.series = echartData2.series;
          		options2.series[0].barWidth = '50%';
          	
          		var op = {normal:{textStyle:{color:'#000'},show:true,position:'top'}};
          		options2.series[0].label= op;
          		
            	options2.legend.data = echartData2.legend;
	        	options2.xAxis[0].data = echartData2.category;
              	options2.series = echartData2.series;
                options2.legend.data = echartData2.legend;
                myChart4.hideLoading();
                myChart4.setOption(options2);
                
                
              //定义图表options
	            var options3 = {
	                color :  [ '#3398DB','#68228B'],
	                title : {
	                    text : "巡航艘次个人排名",
	                   
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
	                    barWidth : '60%',
	                    itemStyle:{
	                    	normal:{
	                    		label:{
	                    			show:true,
	                    			textStyle:{
	                    				fontWeight:'bolder',
	                    				fontSize:'12',
	                    				fontFamily:'微软雅黑',
	                    				position:'top'
	                    			}
	                    		}
	                    	}
	                    }
	                } ]
	            };
 				var echartData3 =${echartsPeopleNum};
 				options3.xAxis[0].data = echartData3.category;
           		options3.series = echartData3.series;
           		options3.series[0].barWidth = '60%';
           	
           		var op = {normal:{textStyle:{color:'#000'},show:true,position:'top'}};
           		options3.series[0].label= op;
           		
             	options3.legend.data = echartData3.legend;
	        	options3.xAxis[0].data = echartData3.category;
              	options3.series = echartData3.series;
                options3.legend.data = echartData3.legend;
                myChart5.hideLoading();
                myChart5.setOption(options3);
                
                
                //定义图表options
	            var options4 = {
	                color :  [ '#3398DB','#68228B'],
	                title : {
	                    text : "巡航水域排名",
	                   
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
	                        rotate:30//倾斜度 -90 至 90 默认为0  
	                    }
	                } ],
	                yAxis : [ {
	                    type : 'value',
	                    splitArea : {
	                        show : true
	                    }
	                } ],
	                series : [ {
	                    barWidth : '60%',
	                    
	                } ]
	            };
 				var echartData4 =${cruiseArea};
 				options4.xAxis[0].data = echartData4.category;
           		options4.series = echartData4.series;
           		options4.series[0].barWidth = '60%';
           	
           		var op = {normal:{textStyle:{color:'#000'},show:true,position:'top'}};
           		options4.series[0].label= op;
           		
             	options4.legend.data = echartData4.legend;
	        	options4.xAxis[0].data = echartData4.category;
              
              	options4.series = echartData4.series;
              	options4.legend.data = echartData4.legend;
                myChart6.hideLoading();
                myChart6.setOption(options4);
                
                
                //定义图表options
	            var options5 = {
	                color :  [ '#3398DB','#68228B'],
	                title : {
	                    text : "查处违章",
	                   
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
	                        rotate:30//倾斜度 -90 至 90 默认为0  
	                    }
	                } ],
	                yAxis : [ {
	                    type : 'value',
	                    splitArea : {
	                        show : true
	                    }
	                } ],
	                series : [ {
	                    barWidth : '60%',
	                    
	                } ]
	            };
				var echartData5 =${illegal};
				options5.xAxis[0].data = echartData5.category;
           		options5.series = echartData5.series;
           		options5.series[0].barWidth = '30%';
           		options5.series[1].barWidth = '30%';
           		var op = {normal:{textStyle:{color:'#000'},show:true,position:'top'}};
           		options5.series[0].label= op;
           		options5.series[1].label= op;
             	options5.legend.data = echartData5.legend;
	        	options5.xAxis[0].data = echartData5.category;
              
              	options5.series = echartData5.series;
              	options5.legend.data = echartData5.legend;
                myChart7.hideLoading();
                myChart7.setOption(options5);
                
                

                var geoCoordMap = ${mo.geoPos}
                

                var data = ${mo.geoName}

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
                            text: '全国主要城市 交易量',
                            subtext: 'data from yunqicl',
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
                            dataZoom: {},
                            brush: {
                                type: ['rect', 'polygon', 'clear']
                            },
                            saveAsImage : {show: true}
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
                    series : [
                        {
                            name: 'pm2.5',
                            type: 'scatter',
                            coordinateSystem: 'geo',
                            data: convertedData[0],
                            symbolSize: function (val) {
                                return Math.max(val[2] / 100, 8);
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
                                return Math.max(val[2] / 100, 8);
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
                        return a.value[2] - b.value[2];
                    });

                    for (var i = 0; i < Math.min(selectedItems.length, maxBar); i++) {
                        categoryData.push(selectedItems[i].name);
                        barData.push(selectedItems[i].value[2]);
                    }

                    this.setOption({
                        yAxis: {
                            data: categoryData
                        },
                        xAxis: {
                            axisLabel: {show: !!count}
                        },
                        title: {
                            id: 'statistic',
                            text: count ? '平均: ' + (sum / count).toFixed(4) : ''
                        },
                        series: {
                            id: 'bar',
                            data: barData
                        }
                    });
                }
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
	<form:form id="searchForm" modelAttribute="cruiseStatics" action="${ctx}/cruise/statics/paintEcharts" method="post" class="breadcrumb form-search">
		
		<ul class="ul-form">
			
			<li><label>统计月份：</label>
				<li><label>发生日期：</label>
				<input id="beginHappenDate" name="beginHappenDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cruiseStatics.beginHappenDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input id="endHappenDate" name="endHappenDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${cruiseStatics.endHappenDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				</li>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	
	<hr>
	<div >
		<div id="mainGeo" style="width: 800px;height:500px;margin:0 auto;border:2px solid red;border-radius:25px;"></div>
	
		<div id="main1" style="width: 800px;height:500px;margin:0 auto;border:2px solid red;border-radius:25px;"></div>
		
		 <div id="main2" style="width: 620px;height:400px;float:left;margin:10px;border:2px solid red;border-radius:25px;"></div>
		 <div id="main3" style="width: 620px;height:400px;float:left;margin:10px;border:2px solid red;border-radius:25px;"></div>
		 <div id="main4" style="width: 620px;height:400px;float:left;margin:10px;border:2px solid red;border-radius:25px;"></div>
		 <div id="main5" style="width: 620px;height:400px;float:left;margin:10px;border:2px solid red;border-radius:25px;"></div>
		 <div id="main6" style="width: 630px;height:450px;float:left;margin:10px;border:2px solid red;border-radius:25px;"></div>
		 <div id="main7" style="width: 630px;height:450px;float:left;margin:10px;border:2px solid red;border-radius:25px;"></div>
	 </div>
</body>
</html>