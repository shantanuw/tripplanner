<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Google Maps JavaScript API Example</title>
    <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true_or_false&amp;key=ABQIAAAAoZiUXoRFxkbmV6RTTlZzORRE4wFPIZ2DyYQJQi6-zGMv7vNFnBTBQNpKfq4swo16pOIkyKx2C69rrQ" type="text/javascript"></script>
    <script type="text/javascript">
    //<![CDATA[
	var usCenterPoint = new GLatLng(39.833333, -98.583333);
	var usZoom = 4;
    function load() {
      if (GBrowserIsCompatible()) {
        var map = new GMap2(document.getElementById("map"));
        map.setCenter(usCenterPoint, usZoom);
        map.addControl(new GLargeMapControl());
        map.addControl(new GMapTypeControl());

        <g:each in="${airportList}" status="i" var="airport">
	        var point${airport.id} = new GLatLng(${airport.lat}, ${airport.lng});
	     	var marker${airport.id} = new GMarker(point${airport.id});
	     	marker${airport.id}.bindInfoWindowHtml("${airport.iata}<br/>${airport.name}");
	        map.addOverlay(marker${airport.id});
	     </g:each>
      }
    }
    //]]>
    </script>
  </head>
  <body onload="load()" onunload="GUnload()">
    <div id="map" style="width: 800px; height: 500px"></div>
  </body>
</html>