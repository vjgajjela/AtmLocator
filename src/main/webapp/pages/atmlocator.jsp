<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atm Locator</title>
<script
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAIjBZqBn1f7dARHtcELeTTLhFwQP1sYfM&libraries=drawing"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#results").hide();
	});
	
	var position = [ 52.36139, 4.939421 ];
	function initialize() {
		var myOptions = {
			zoom : 10,
			streetViewControl : true,
			scaleControl : true,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById('googlemaps'),
				myOptions);
		latLng = new google.maps.LatLng(position[0], position[1]);
		map.setCenter(latLng);
	}

	function addMarkers(list) {

		var myOptions = {
			zoom : 12,
			streetViewControl : true,
			scaleControl : true,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById('googlemaps'),
				myOptions);
		latLng = new google.maps.LatLng(list[0].address.geoLocation.lat, list[0].address.geoLocation.lng);
		map.setCenter(latLng)

		for (var i = 0; i < list.length; i++) {
			var myLatlng = new google.maps.LatLng(
					list[i].address.geoLocation.lat,
					list[i].address.geoLocation.lng);
			var marker = new google.maps.Marker({
				position : myLatlng,
				map : map,
				title : list[i].address.street
			});
			google.maps.event.addDomListener(document.getElementById("marker"+i), "click", function(ev) {
				var lat = document.getElementById(ev.target.id+'geolat').value;
				var lng = document.getElementById(ev.target.id+'geolng').value;
				map.setCenter(new google.maps.LatLng(lat, lng));
		        map.setZoom(18);
		      });
		}
	}
	google.maps.event.addDomListener(window, 'load', initialize);

	function getAtms() {
		var input = $('input').val();
		$("#results" ).empty();
		if (input != null && input != '') {
			var URL = 'http://localhost:8080/AtmLocator/search/' + input;
			$.ajax({
				type : 'GET',
				url : URL,
				dataType : 'json',
				contentType : 'application/json; charset=utf-8',
				success : function(response) {
					// sprinner();
					if ('code' in response) {
						$("#results").append('<span><h1>No Results<h1></span>');
					} else if (response.length > 0) {	
						processAtmList(response);
						addMarkers(response);
					} else {
						$("#results").append('<span><h1>No Results<h1></span>');
					}
					$("#results").show();
				},
				error : function(response) {
					$("#results").append('<span><h1>No Results<h1></span>');
					$("#results").show();
				},
				statusCode : {
				},
			});
		} else {
			$("#results").append('<span><h1>Please enter either City or Postal code before clicking on Go<h1></span>');
			$("#results").show();
		}
	}

	function processAtmList(list) {
		var htmlGen = '<table class="resultsTable">';
		var map = document.getElementById('googlemaps');
		for (var i = 0; i < list.length; i++) {
			
		htmlGen += '<tr><td><table class="record"><thead><tr><th scope="col" colspan="2"><a href="#" id="marker'+i
			+'"><h3><span id="mtag'+i+'">'
					+ list[i].address.street
					+ '</span></h3></a></th>'
					+ '</tr></tr></thead><tbody><tr><td>'
					+ list[i].address.housenumber
					+ '</td></tr><tr><td>'
					+ list[i].address.postalcode
					+ '</td></tr><tr><td>'
					+ list[i].address.city
					+ '</td></tr><tr><td>'
					+ '<input id="mtag'+i+'geolat" type="hidden" value="'+list[i].address.geoLocation.lat+'">'
					+ '</td></tr><tr><td>'
					+ '<input id="mtag'+i+'geolng" type="hidden" value="'+list[i].address.geoLocation.lng+'">'
					+'</td></tr></tbody></table></td></tr>';
		}
		htmlGen += '</table>';
		$("#results").append(htmlGen);
	}
</script>
<style>
#googlemaps {
	height: 100%;
	width: 100%;
	position: absolute;
	top: 0;
	left: 0;
	z-index: 0;
	opacity: .75
}

#info {
	position: relative;
	z-index: 1;
	width: 300px;
	margin: 50px 0
}

thead {
	display: table-header-group;
	vertical-align: middle;
	border-color: inherit;
}

input {
	border: none;
	outline: none;
	resize: none;
	height: 40px;
	width: 100%;
	-moz-appearance: none;
	-webkit-appearance: none;
	font-size: 13px;
	margin: 0;
	padding: 0;
	-moz-appearance: none;
	width: 100%;
}

.formTable {
	width: 375px;
}

.resultsTable {
	width: 100%
}

body {
	font-family: calibri;
	font-size: 12px;
	font-weight: 10px;
}

.record {
	margin: 0;
	padding: 0;
	width: 100%;
	border-bottom: 1px dashed #333;
	border-collapse: separate;
	font-size: 120%;
}

.resultsSection {
	background: #FFF;
	margin-top: -2px;
	position: relative;
	font-size: 90%;
	width: 370px;
	height: 500px;
	overflow: scroll;
}

.contact-form .button {
	background: #3498db;
	border: 1px #3498db solid;
	color: #fff;
	float: right;
	align: left;
	padding: 9px 0;
	width: 100px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	border-radius: 10px;
	padding: 9px 0;
}
</style>
</head>
<body>
	<div id="googlemaps"></div>
	<div id="info">
		<form class="contact-form">
			<div>
				<table class="formTable" border="0">
					<tr>
						<td width="80%"><input type="text" id="input"
							placeholder="  Enter city or post code" /></td>
						<td width="20%">
							<div class="submit">
								<input id="searchAtm" type="button" value="Go" class="button"
									onclick="getAtms(); return false;" />
							</div>
						</td>
					</tr>

				</table>
				<div id="results" class="resultsSection"></div>
			</div>
		</form>
	</div>
</body>
</html>
