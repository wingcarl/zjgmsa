<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>网管方法整治建筑物</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>

<script src="${ctxStatic}/layer/layer.js"></script>
<script src="${ctxStatic}/layer/lay/dest/layui.all.js"></script>
	
<script src="${ctxStatic}/layer/layui.js"></script>
<link href="${ctxStatic}/layer/css/layui.css" rel="stylesheet">

<script src="${ctxStatic}/baidumap/baidumap_offline_v2_load.js"></script>
<link href="${ctxStatic}/baidumap/css/baidu_map_v2.css" rel="stylesheet">


	
</head>
<body>
<div id="map" style="width:95%;height:640px"></div>
<script type="text/javascript">
$(document).ready(function() {
	var tileLayer = new BMap.TileLayer();
	tileLayer.getTilesUrl = function(tileCoord, zoom) {
	    var x = tileCoord.x;
	    var y = tileCoord.y;
	    var url = '${ctxStatic}/baidumap/tiles/';
	    return url + zoom + '/tile' + x + '_' + y + '.png';
	}
	var MyMap = new BMap.MapType('MyMap', tileLayer, {minZoom: 1, maxZoom:7});
	var map = new BMap.Map('map', {mapType: MyMap});

	map.addControl(new BMap.NavigationControl());
	map.centerAndZoom(new BMap.Point(120.414611, 32.028109),4);
	/*map.addEventListener("click", function(e){    
	alert(e.point.lng + ", " + e.point.lat);    
	});*/
	var polygon7 = new BMap.Polygon([      
	          new BMap.Point(113.350049, 34.011884),      
	          new BMap.Point(167.217334, 66.125187),  
			  new BMap.Point(172.074221, 49.243783),  		  
	          new BMap.Point(120.856146, 20.789477),      
	           		  
	      ]);  
	polygon7.addEventListener("click", function(){    
		layer.open({
			type:2,
			title:'7号网格工作数据',
			area:['890px','560px'],
			shade:0,
			maxmin:true,
			content:'${ctx}/intercept/gridManaIntercept/listView?grid=7',
			btn:['关闭'],
			btn1:function(){
				layer.closeAll();
			},
			zIndex:layer.zIndex
		});  
	});
	
	map.addOverlay(polygon7);  
	polygon7.setFillColor('#fff');
	polygon7.setFillOpacity(0.1);
	polygon7.setStrokeColor();
	var polygon11 = new BMap.Polygon([      
        new BMap.Point(52.418202, 2.891799),      
        new BMap.Point(57.711623, -16.433744),  
		  new BMap.Point(106.8742, 14.154826),  		  
        new BMap.Point(93.039433, 30.638446),      
         		  
    ]);  
	polygon11.addEventListener("click", function(){    
	layer.open({
		type:2,
		title:'11号网格工作数据',
		area:['890px','560px'],
		shade:0,
		maxmin:true,
		content:'${ctx}/intercept/gridManaIntercept/listView?grid=11',
		btn:['关闭'],
		btn1:function(){
			layer.closeAll();
		},
		zIndex:layer.zIndex
	});  
});
	map.addOverlay(polygon11);  
	
	var polygon10 = new BMap.Polygon([      
        new BMap.Point(44.029034, -17.848861),      
        new BMap.Point(50.79924, -34.373014),  
		  new BMap.Point(76.849812, -24.605939),  		  
        new BMap.Point(65.811434, -15.294041),      
         		  
    ]);  
	polygon10.addEventListener("click", function(){    
	layer.open({
		type:2,
		title:'10号网格工作数据',
		area:['890px','560px'],
		shade:0,
		maxmin:true,
		content:'${ctx}/intercept/gridManaIntercept/listView?grid=10',
		btn:['关闭'],
		btn1:function(){
			layer.closeAll();
		},
		zIndex:layer.zIndex
	});  
});
	map.addOverlay(polygon10);  
	polygon11.setFillColor('#fff');
	polygon11.setFillOpacity(0.1);
	polygon11.setStrokeColor();
	polygon10.setFillColor('#fff');
	polygon10.setFillOpacity(0.1);
	polygon10.setStrokeColor();
});

</script>

</body>
</html>
