
/**
 * 地图放大
 */
function mapZoomMax(){
    var zoomlevel = map.getZoom();
    map.setZoom(zoomlevel++);
}

/**
 * 地图缩小
 */
function mapZoomMin(){
    var zoomlevel = map.getZoom();
    map.setZoom(zoomlevel--);
}

/**
 * 地图居中
 * @param point
 * @returns
 */
function mapCenter(point,lvl) {
	if(!isNaN(lvl)){
		map.setZoom(lvl);
	}
    map.centerAt(point);
}

/**
 * 获得图层
 * @param layerId
 * @returns
 */
function getCreateLayerById(layerId) {
	var layer = map.getLayer(layerId);
	if (layer == undefined) {
	    layer = new esri.layers.GraphicsLayer({ displayOnPan: true, id: layerId });
	    map.addLayer(layer);
	    clearLayers.push(layerId);
	}
	return layer;
}

/**
 * 获得并清空图层
 * @param layerId
 * @returns layer 图层
 */
function getOrCreateLayerById(layerId) {
  var layer = map.getLayer(layerId);
  if (layer == undefined||layer==null) {
      layer = new esri.layers.GraphicsLayer({ displayOnPan: true, id: layerId });
      map.addLayer(layer);
  }
  layer.clear();
  return layer;
}
/**
 * 清空并移除图层
 * @param layerId
 * @returns
 */
function getClearLayerById(layerId){
	var layer = map.getLayer(layerId);
	if (layer != undefined) {
		layer.clear();
		map.removeLayer(layer);
	}
	for(var i=0; i<clearLayers.length; i++){
		if(clearLayers[i]==layerId){
			clearLayers.splice(i,1);//从位置i删除一个元素
		}
	}
}

/**
 * 根据x,y定位并获取point
 * @param x
 * @param y
 * @returns
 */
function getMapCenter(x, y){
    var point = esri.geometry.geographicToWebMercator(new esri.geometry.Point({
        "x": Number(x),
        "y": Number(y),
        "spatialReference": {
            "wkid": 4326
        }
    }));
    map.centerAt(point);
    return point;
}

/**
 * 获取TextGraphic
 * @param x
 * @param y
 * @param txt
 * @param color
 * @param size
 * @returns
 */
function getTextGraphic(x, y, txt, color, size){
	if (size == 0){
		size = 9;
	}
    var point = esri.geometry.geographicToWebMercator(new esri.geometry.Point({
        "x": Number(x),
        "y": Number(y),
        "spatialReference": {
            "wkid": 4326
        }
    }));
    var textSymbol = new esri.symbol.TextSymbol(txt);
    textSymbol.setColor(color);
    var font = new esri.symbol.Font();
    font.setSize(size+"pt");
    textSymbol.setFont(font);
    return new esri.Graphic(point, textSymbol);
}

/**
 * 获取PointGraphic
 * @param x
 * @param y
 * @param shape
 * @param color
 * @returns
 */
function getPointGraphic(x, y, shape, color){
    var point = esri.geometry.geographicToWebMercator(new esri.geometry.Point({
        "x": Number(x),
        "y": Number(y),
        "spatialReference": {
            "wkid": 4326
        }
    }));

    var pointSymbol = new esri.symbol.SimpleMarkerSymbol();
    pointSymbol.style = shape;//esri.symbol.SimpleMarkerSymbol.STYLE_CIRCLE;
    pointSymbol.setOutline(new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, color, 1));
    pointSymbol.setSize(15);
    pointSymbol.setColor(color);
    return new esri.Graphic(point, pointSymbol);
}

/**
 * 获取ImgGraphic
 * @param x
 * @param y
 * @param url
 * @param width
 * @param height
 * @returns
 */
function getImgGraphic(x, y, url, width, height){
    var point = esri.geometry.geographicToWebMercator(new esri.geometry.Point({
        "x": Number(x),
        "y": Number(y),
        "spatialReference": {
            "wkid": 4326
        }
    }));
    var symbol = new esri.symbol.PictureMarkerSymbol(url, width, height);
    return new esri.Graphic(point, symbol);
}

/**
 * 获取RectGraphic
 * @param max_long
 * @param min_long
 * @param max_lat
 * @param min_lat
 * @param color
 * @param border_color
 * @returns
 */
function getRectGraphic(max_long, min_long, max_lat, min_lat, color, border_color){
	if (isEmptyOrNull(border_color)) {
		border_color = new dojo.Color("white");
	}
	var polygon = { "rings": [[[min_long, min_lat], [max_long, min_lat]
		, [max_long, max_lat], [min_long, max_lat], [min_long, min_lat]]]
		, "spatialReference": { " wkid": 4326} };
	var poly = new esri.geometry.Polygon(polygon);
	var sfs = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID,
		new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, border_color, 1), color);	
	return new esri.Graphic(poly, sfs);
}

/**
 * 获取PieGraphic
 * @param x
 * @param y
 * @param direction
 * @param angle
 * @param radius
 * @param color
 * @param border_color
 * @returns
 */
function getPieGraphic(x, y, direction, angle, radius, color, border_color){
	if (isEmptyOrNull(direction)) {
		direction = 90;
	} else {
		direction = Number(direction) + 90;
	}
	if (isEmptyOrNull(angle)) {
		angle = 30;
	}
    if (isEmptyOrNull(radius)){
    	radius = 500;
    }
    if (isEmptyOrNull(color)){
    	color = new dojo.Color("blue");
    }
	var maxAngle = direction;
    var aAngle = angle;
    var r = 0.00001145 * radius;
    var color = color;
	var points = new Array;
    points.push(new esri.geometry.Point({
        "x": x,
        "y": y
    })); //中心点
    for (var i = 360 - maxAngle - aAngle / 2; i <= 360 - maxAngle - aAngle / 2 + aAngle; i++) {
        var radian = (Math.PI * i) / 180;
        points.push(new esri.geometry.Point({
            "x": eval(x - r * Math.cos(radian) / 2),
            "y": eval(y - r * Math.sin(radian) / 2)
        }));
    }
    points.push(new esri.geometry.Point({
        "x": x,
        "y": y
    }));
    var poly = new esri.geometry.Polygon(new esri.SpatialReference({ wkid: 4326 }));
    poly.addRing(points)
    var symbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID,
    	new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, border_color, 1), color);
    return new esri.Graphic(poly, symbol);
}

/**
 * 获取EllipseGraphic
 * @param x
 * @param y
 * @param radius
 * @param color
 * @param border_color
 * @returns
 */
function getEllipseGraphic(x, y, radius, color, border_color){
    if (isEmptyOrNull(radius)){
    	radius = 500;
    }
    if (isEmptyOrNull(color)){
    	color = new dojo.Color("red");
    }
    if (isEmptyOrNull(border_color)){
    	border_color = new dojo.Color("white");
	}
    var r = 0.00001145 * radius;
    var points = new Array;
    for (var i = 0; i <= 360; i++) {
        var radian = (Math.PI * i) / 180;
        points.push(new esri.geometry.Point({
            "x": (x - r * Math.cos(radian)),
            "y": (y - r * Math.sin(radian) * 0.788)
        }));
    }    
    var poly = new esri.geometry.Polygon(new esri.SpatialReference({ wkid: 4326 }));
    poly.addRing(points);
    var symbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID,
    	new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, border_color, 1), color);
    return new esri.Graphic(poly, symbol);
}

var map = undefined;//地图实例
var baseURL = "http://www.hebmcbass.com/arcgis/rest/services/HBMAP_NEW/MapServer";//地图底层服务地址
var queryURL = "http://www.hebmcbass.com/arcgis/rest/services/HB50QS_NEW_2016/MapServer/";//查询服务地址
var tool,toolbar_create,toolbar_edit;//图形绘制工具和编辑工具
//初始化绘图工具
function initDrawTool(){
	tool=esri.toolbars.Edit.MOVE | esri.toolbars.Edit.SCALE | esri.toolbars.Edit.EDIT_VERTICES | esri.toolbars.Edit.ROTATE;
	if(toolbar_create==null||toolbar_create==undefined){
		toolbar_create = new esri.toolbars.Draw(map);
	}
	if(toolbar_edit==null||toolbar_edit==undefined){
		toolbar_edit = new esri.toolbars.Edit(map);
	}
}
//根据图形获取rings
function getgraphicrings(graphic){
	var polygon=new esri.geometry.Polygon(graphic.geometry);
 	return polygon.rings[0]+"";
}
//根据图形获取中心点
function getgraphiccenter(graphic){
	var polygon=new esri.geometry.Polygon(graphic.geometry);
	var point = polygon.getExtent().getCenter();
 	return point;
}
var mapModel = {
    heatMapConfig: undefined,
    heatMapLayer: undefined
};//热力图配置信息
var geometryEngine;
/**
 * 初始化地图
 */
function init() {
	window.moveTo(0, 0);
	window.resizeTo(screen.availWidth, screen.availHeight);
	  //resize();//-------------------重新调用
    var initExtent = new esri.geometry.Extent({ xmin: 114.373814669172, xmax: 114.614085207367, ymin: 37.9851717192208, ymax: 38.0980808067185 });
    //创建地图
    map = new esri.Map("map", {
        extent: initExtent
        , isZoomSilder: false
        , slider: false
        , nav: false
        , logo: false
    });
    initDrawTool();
	var myTiledMapServiceLayer = new esri.layers.ArcGISTiledMapServiceLayer(baseURL);//创建地图底图图层
	map.addLayer(myTiledMapServiceLayer);
	map.setZoom(8);
	/*map.on("load",function(){
		resize();
	});*/	
	initLayerBindMapEvent();//图层绑定事件
	/*initBase();*/
	/**
	 * 绑定地图加载事件
	 */
	dojo.connect(map, "onLoad", function () {
//		/*添加热力图层*/
//        $("#map_gc").after("<div id='heatmapArea' class='well'></div>");
//        $("#map_gc").css("z-index", "2");
//        /*配置热力图信息*/
//        mapModel.heatMapConfig = {
//            "element": document.getElementById("heatmapArea"),
//            "radius": 20,
//            "gradient": {
//                0.15: "rgb(106,90,205)",
//                0.25: "rgb(65,105,225)",
//                0.35: "rgb(38,176,249)",
//                0.45: "rgb(000,255,000)",
//                0.75: "rgb(238,232,170)",
//                0.82: "rgb(255,255,000)",
//                0.88: "rgb(250,128,114)",
//                0.90: "rgb(255,79,000)",
//                1.00: "rgb(255,000,000)"
//            },
//            "visible": true
//        };

        /*地图移动事件*/
        dojo.connect(map, "onExtentChange", function (extent) {
        	 
        });

		/*地图鼠标移动事件*/
		dojo.connect(map, "onMouseMove", function(event) {
		
		});
        
		/*地图鼠标双击事件*/
		dojo.connect(map, "onDblClick", function(event) {
		
		});
		/*地图点击事件*/
		dojo.connect(map, "onClick", function(event) {
			
		});

	});

}
dojo.require("esri.map");//引入esri地图
dojo.require("esri.toolbars.draw");//引入esri工具
dojo.require("esri.toolbars.edit");//引入esri工具
dojo.addOnLoad(init);//绑定初始化方法
require(["esri/geometry/geometryEngine"],function(GeometryEngine){
	geometryEngine = GeometryEngine;
});
/*对象查询*/
function getSearchQueryTask(querylayId,whereStr,callback,returnGeometry,outFields,orderArray){
 	var searchMapServer = queryURL + querylayId;
    var searchQueryTask = new esri.tasks.QueryTask(searchMapServer);
    var searchQuery = new esri.tasks.Query();
    searchQuery.where = whereStr;
    if($.isArray(orderArray)&&orderArray.length>0){
    	searchQuery.orderByFields  = orderArray;
    }
    if($.isArray(outFields)&&outFields.length>0){
    	searchQuery.outFields = outFields;
    }else{
    	searchQuery.outFields = ["*"];
    }
    searchQuery.returnGeometry = returnGeometry;
//    searchQuery.Result Record Count = returnGeometry;
    searchQueryTask.execute(searchQuery, callback);
}
/*转换成Rgba颜色*/
function toRgba(color,alpha){
	var colorRgba = (new dojo.Color(color)).toRgb();
	if(!isNaN(alpha)&&alpha>=0&&alpha<=1){
		colorRgba.push(alpha);
	}else{
		colorRgba.push(0.8);
	}
	return colorRgba;
}
/*地图标记*/
function getCreateMark(){
	markModel.isShow = markModel.isShow == true ? false : true;
	if(!markModel.isShow){
		getClearLayerById(markModel.moveId);
	}
}
function markSave(){
	map.infoWindow.hide();
    var x = $("#markLong").val();
    var y = $("#markLat").val();
    var name = $("#markName").val();
    var explain = $("#markExplain").val();
    var width = 15; var height = 25;
    var url = markModel.imgUrl;
    var symbol = new esri.symbol.PictureMarkerSymbol(url, width, height);
    var graphic = getImgGraphic(x, y, url, width, height);
    graphic.setAttributes({ "markLong": x, "markLat": y, "markName": name, "markExplain": explain });
    var layer = getCreateLayerById(markModel.layerId);
    layer.add(graphic);
    layer.onClick = undefined;
	dojo.connect(layer, "onClick", function (event) {
	    var markLong = event.graphic.attributes.markLong;
	    var markLat = event.graphic.attributes.markLat;
	    var markName = event.graphic.attributes.markName;
	    var markExplain = event.graphic.attributes.markName;
        map.infoWindow.setTitle("标记内容：");
        var infoContent = new Array();
        infoContent.push("<div style='width:300px;height:200px;'>");
        infoContent.push("<table>");
        infoContent.push("<tr><td style=' width:80px;'>经度：</td><td><input id='markLong' style=' width:220px;' value='"+markLong+"' /></td></tr>");
        infoContent.push("<tr><td style=' width:80px;'>纬度：</td><td><input id='markLat' style=' width:220px;' value='"+markLat+"' /></td></tr>");
        infoContent.push("<tr><td style=' width:80px;'>名称：</td><td><input id='markName' style=' width:220px;' value='"+markName+"' /></td></tr>");
        infoContent.push("<tr><td style=' width:80px;'>备注：</td><td><textarea id='markExplain' name='markExplain' rows='3' style=' width:220px;'>"+markExplain+"</textarea></td></tr>");
        infoContent.push("<tr><td></td><td><div align='right'><input type='button' value='保存' id='markSave' onclick='markSave();' /><input type='button' id='markDelete' value='删除'  onclick='markDelete();' style='margin-left:15px;' /></div></td></tr>");
        infoContent.push("</table>");
        infoContent.push("</div>");
        map.infoWindow.setContent(infoContent.join(''));
        map.infoWindow.show(event.screenPoint, map.getInfoWindowAnchor(event.screenPoint));
        map.infoWindow.resize(330, 240);
	});
}
function markDelete(){
    map.infoWindow.hide();
    var layer = getCreateLayerById(markModel.layerId);
    var points = new Array();
    $.each(layer.graphics, function (i, item){
    	if (item.attributes.markLong == $("#markLong").val() && item.attributes.markLat == $("#markLat").val()){
    		points.push(item);
    	}
    });
    for (var i = 0; i < points.length; i++) {
    	layer.remove(points[i]);
    }
}
/*地图测距*/
var meterDistance;
var distanceLayerId = "distanceLayer";
/**
 * 创建测距工具
 */
function getCreateDistance() {
    if (!isEmptyOrNull(meterDistance)) {
    	clearDistance();
    	return;
    }
    meterDistance = new esri.toolbars.Draw(map, { showTooltips: false });
    meterDistance.activate(esri.toolbars.Draw.LINE); //POLYLINE
    dojo.connect(meterDistance, "onDrawEnd", addToMapDistance);
    meterDistance.finishDrawing();
}

var EARTH_RADIUS = 6378137.0; //单位M 
/**
 * 弧度转换
 * @param d
 * @returns {Number}
 */
function getRad(d) {
    return d * Math.PI / 180.0;
}
/**
 * 计算地图上两点间距离（米）
 * @param lat1
 * @param lng1
 * @param lat2
 * @param lng2
 * @returns {Number}
 */
function getFlatternDistance(lat1, lng1, lat2, lng2) {
    var f = getRad((lat1 + lat2) / 2);
    var g = getRad((lat1 - lat2) / 2);
    var l = getRad((lng1 - lng2) / 2);

    var sg = Math.sin(g);
    var sl = Math.sin(l);
    var sf = Math.sin(f);

    var s, c, w, r, d, h1, h2;
    var a = EARTH_RADIUS;
    var fl = 1 / 298.257;

    sg = sg * sg;
    sl = sl * sl;
    sf = sf * sf;

    s = sg * (1 - sl) + (1 - sf) * sl;
    c = (1 - sg) * (1 - sl) + sf * sl;

    w = Math.atan(Math.sqrt(s / c));
    r = Math.sqrt(s * c) / w;
    d = 2 * w * a;
    h1 = (3 * r - 1) / 2 / c;
    h2 = (3 * r + 1) / 2 / s;

    return d * (1 + fl * (h1 * sf * (1 - sg) - h2 * (1 - sf) * sg));
}
/**
 * 添加阶段距离
 * @param geometry
 */
function addToMapDistance(geometry) {
    var graphicLayer = getOrCreateLayerById(distanceLayerId);
    var symbol = new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID,
                              new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color("red"), 3),
                              new dojo.Color("transparent"));
    var graphic = new esri.Graphic(geometry, symbol);
    graphicLayer.add(graphic);
    var distanceNum = getFlatternDistance(geometry.getExtent().ymin, geometry.getExtent().xmin, geometry.getExtent().ymax, geometry.getExtent().xmax);
    var textSymbol = new esri.symbol.TextSymbol(parseInt(Number(distanceNum)) + "米");
    textSymbol.setColor(new dojo.Color("black"));
    //textSymbol.setAngle(3);
    var font = new esri.symbol.Font();
    font.setSize("9pt");
    textSymbol.setFont(font);
    var txtGraphic = new esri.Graphic(geometry.getExtent().getCenter(), textSymbol);
    graphicLayer.add(txtGraphic);
}
/**
 * 清除测距
 */
function clearDistance() {
	getClearLayerById(distanceLayerId);
    if (meterDistance != undefined){
    	meterDistance.deactivate();	
    }
    meterDistance = undefined;
}

