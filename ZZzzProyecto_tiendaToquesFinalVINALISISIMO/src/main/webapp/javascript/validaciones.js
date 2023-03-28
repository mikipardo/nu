let regexp_nombre = /^[a-z áéíóúñ]{2,10}$/i;
let regexp_pass = /^[a-z0-9áéíóú]{3,10}$/i;
let regexp_email =/^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,60})$/i
;



function validarNombre(nombre){
	if( regexp_nombre.test(nombre) ){
			$("#nomRegistro").html("");
		return true;
	}else{
		$("#nomRegistro").html("<strong>Formato no valido</strong>");
		return false;
	}
}

function validarEmail(email){
	if( regexp_email.test(email) ){
			$("#mailRegistro").html("");
		return true;
	}else{
	$("#mailRegistro").html("<strong>Email no valido</strong>");
		return false;
	}
}

function validarPass(pass){
	if( regexp_pass.test(pass) ){
			$("#passRegistro").html("");
		return true;
	}else{
		$("#passRegistro").html("<strong>Formato de pass no valido</strong>");
		return false;
	}
}