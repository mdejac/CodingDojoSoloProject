/**
 *
<script src="/js/mapScript.js"></script>
<script>
	const eventLocation = "${tournament.eventLocation}";
	initalizeMap(eventLocation);
</script>
 *
 */

"use strict";

function initMap(){}

function initMapNew(latitude, longitude) {
	const myLatLng = {
  		lat: latitude,
  		lng: longitude
	};
	const map = new google.maps.Map(document.getElementById("gmp-map"), {
		zoom: 11,
  		center: myLatLng,
  		fullscreenControl: false,
  		zoomControl: true,
  		streetViewControl: false
	});
	new google.maps.Marker({
  		position: myLatLng,
  		map,
  		title: "Event location"
	});
}

function initalizeMap(eventLocation) {
	const geocoder = new google.maps.Geocoder();
	geocoder.geocode({address:eventLocation}, (results, status) => {
		if (status === "OK") {
			const latitude = results[0].geometry.location.lat();
			const longitude = results[0].geometry.location.lng();
			initMapNew(latitude, longitude);
		} else {
			console.error("Geocode was not successful :" + status);
		}
	});	
}

/** 
 * For use in JSP
<script>
   	"use strict";

   	function initMap(latitude, longitude) {
       	const myLatLng = {
       		lat: latitude,
       		lng: longitude
       	};
       	const map = new google.maps.Map(document.getElementById("gmp-map"), {
       		zoom: 11,
       		center: myLatLng,
       		fullscreenControl: false,
       		zoomControl: true,
       		streetViewControl: false
       	});
      	new google.maps.Marker({
	   		position: myLatLng,
       		map,
       		title: "Event location"
       	});
    }
      	
    const eventLocation = "${tournament.eventLocation}";
    const geocoder = new google.maps.Geocoder();
    geocoder.geocode({address:eventLocation}, (results, status) => {
    	if (status === "OK") {
    		const latitude = results[0].geometry.location.lat();
      		const longitude = results[0].geometry.location.lng();
      		initMap(latitude, longitude);
     	} else {
     		console.error("Geocode was not successful :" + status);
     	}
     });
</script>

**/
