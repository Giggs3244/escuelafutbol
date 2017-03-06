$(document).ready(function() {
	changePageAndSize();
	filtrarDeportistas();
});

function changePageAndSize() {
	$('#pageSizeSelect').change(function(evt) {
		window.location.replace("/deportistas/" + this.value + "/1");
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