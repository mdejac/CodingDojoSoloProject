function initAutocomplete() {
	const addressInput = document.getElementById("addressInput");
	const autocomplete = new google.maps.places.Autocomplete(addressInput);
}
	
window.addEventListener("load", initAutocomplete);
