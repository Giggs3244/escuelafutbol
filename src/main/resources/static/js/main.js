$(document).ready(function() {
	changePageAndSize();
	filtrarDeportistas();
});

function changePageAndSize() {
	$('#pageSizeSelect').change(function(evt) {
		/* Si el valor de la opcion seleccionada es un numero, se redirecciona. */
		console.log(evt.target.value);
		if(this.value !== "") {
			window.location.replace("/players/inicio/?pageSize=" + this.value + "&page=1");			
		}
	});
}

function filtrarDeportistas() {
	$("#btnConsultar").click(function(evt) {
		validarCamposFormFiltros();
		evt.preventDefault();
	});
}

function validarCamposFormFiltros() {
	var hayFiltro = false; 
	$(".campo-form-filtro").each(function(k, v) {
		if($.trim($(v).val()) != "") {
			hayFiltro = true;
			return false;
		}
	});
	if(hayFiltro) {
		$("#formFiltros").submit();
	}
}