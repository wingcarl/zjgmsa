<!DOCTYPE html>
<html>
<head>
<title>整治建筑物查看</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${ctxStatic}/layer/layer.js"></script>
<script src="${ctxStatic}/layer/lay/dest/layui.all.js"></script>
	
<script src="${ctxStatic}/layer/layui.js"></script>
<link href="${ctxStatic}/layer/css/layui.css" rel="stylesheet">

<script src="${ctxStatic}/zjgmsa/static/baidumap/baidumap_offline_v2_load.js"></script>
<link href="${ctxStatic}/zjgmsa/static/baidumap/css/baidu_map_v2.css" rel="stylesheet">


	
</head>
<body>
<div id="map" style="width:1200px;height:640px"></div>
<script type="text/javascript">
var a = ${ctxStatic}
alert(a);
var tileLayer = new BMap.TileLayer();
tileLayer.getTilesUrl = function(tileCoord, zoom) {
    var x = tileCoord.x;
    var y = tileCoord.y;
    var url = 'http://localhost:8080/zjgmsa/static/baidumap/tiles1/';
    return url + zoom + '/tile' + x + '_' + y + '.png';
}
var MyMap = new BMap.MapType('MyMap', tileLayer, {minZoom: 1, maxZoom: 5});
var map = new BMap.Map('map', {mapType: MyMap});

map.addControl(new BMap.NavigationControl());
map.centerAndZoom(new BMap.Point(120.414611, 32.028109), 4);
map.addEventListener("click", function(e){    
 alert(e.point.lng + ", " + e.point.lat);    
});
var polygon = new BMap.Polygon([      
          new BMap.Point(108.787519, 41.035860),      
          new BMap.Point(167.217334, 66.125187),  
		  new BMap.Point(174.764079, 36.960288),  		  
          new BMap.Point(120.856146, 20.789477),      
           		  
      ]);  
polygon.addEventListener("click", function(){    
 alert("hello world");    
});
map.addOverlay(polygon);  
</script>
</body>
</html>
