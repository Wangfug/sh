<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<meta name="description" content="百度地图地点搜索和鼠标点击地点获取经纬度，这些都是地图比较基本" />
	<meta name="keywords" content="百度地图,地点搜索,获取经纬度,改变地图鼠标样式,启用滚轮缩放" />
	<title>百度地图API地点搜索-获取经纬度DEMO</title>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2"></script>
	<style type="text/css">
		*{
			font-family: "微软雅黑";
		}
		#where,#lonlat,#lonlat2{
			width:100px; height:30px; font-size:16px; color:blue;
		}
		#but{
			width:100px; height:30px; font-size:16px;
		}
		input{
			margin :20px auto;
		}
	</style></head>

<body>
<center>
	<form>
		<div style=" position:absolute;left:0;width:600px;height:90%;border:2px solid gray; margin-bottom:50px;" id="container"></div>
		<div style="position:absolute;left:610px;display:inline;width:300px">
			<h5 style="color: red;">介绍：输入地点然后点击“地图查找”搜索，再点击地图地点获取相应经纬度</h5>
			<label>输入地点：</label>
			<input id="where" name="where" type="text" placeholder="请输入地址">
			<input id="but" type="button" value="地图查找" onClick="sear(document.getElementById('where').value);" /></br>
			经度：
			<input id="lonlat" name="lonlat" type="number" maxlength="10"></br>
			纬度：
			<input id="lonlat2" name="lonlat2" type="number" maxlength="9"></form>
</center>
		</div>

</body>
</html>
<script type="text/javascript">
    //如果经纬度没有给个默认值
    var longitude=105.386515;
    var latitude=28.91124;
    if(parent.document.getElementById('xpoint').value){
        longitude=parent.document.getElementById('xpoint').value;
	}
    if(parent.document.getElementById('ypoint').value){
        latitude=parent.document.getElementById('ypoint').value;
    }
//alert(longitude)
//    alert(latitude)
    var map = new BMap.Map("container");
    map.setDefaultCursor("crosshair");
    map.enableScrollWheelZoom();
    var point = new BMap.Point(longitude,latitude);
    map.centerAndZoom(point, 13);
    var gc = new BMap.Geocoder();
    map.addControl(new BMap.NavigationControl());
    map.addControl(new BMap.OverviewMapControl());
    map.addControl(new BMap.ScaleControl());
    map.addControl(new BMap.MapTypeControl());
    map.addControl(new BMap.CopyrightControl());
    var marker = new BMap.Marker(point);
    map.addOverlay(marker);
    marker.addEventListener("click",
        function(e) {
            document.getElementById("lonlat").value = e.point.lng;
            document.getElementById("lonlat2").value = e.point.lat;
        });
    marker.enableDragging();
    marker.addEventListener("dragend",
        function(e) {
            gc.getLocation(e.point,
                function(rs) {
                    showLocationInfo(e.point, rs);
                });
        });
    function showLocationInfo(pt, rs) {
        var opts = {
            width: 250,
            height: 150,
            title: "当前位置"
        };
        var addComp = rs.addressComponents;
        var addr = "当前位置：" + addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber + "<br/>";
        addr += "纬度: " + pt.lat + ", " + "经度：" + pt.lng;
        var infoWindow = new BMap.InfoWindow(addr, opts);
        marker.openInfoWindow(infoWindow);
    }
    map.addEventListener("click",
        function(e) {
            parent.document.getElementById('xpoint').value = e.point.lng;
            parent.document.getElementById('ypoint').value = e.point.lat;
            document.getElementById("lonlat").value = e.point.lng;
            document.getElementById("lonlat2").value = e.point.lat;
        });
    var traffic = new BMap.TrafficLayer();
    map.addTileLayer(traffic);
    function iploac(result) {
        var cityName = result.name;
    }
    var myCity = new BMap.LocalCity();
    myCity.get(iploac);
    function sear(result) {
        var local = new BMap.LocalSearch(map, {
            renderOptions: {
                map: map
            }
        });
        local.search(result);
    }

</script></html>