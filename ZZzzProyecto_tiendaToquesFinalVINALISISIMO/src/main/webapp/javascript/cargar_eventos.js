function cargarEventos() {

	muestraSlide()
	//con jquery digo que va a pasar cuando se haga click en un enlace
	$("#inicio").click(function() {
		//pedir al servicio de Camisetas
		//y mostrar su resultado usando una plantilla ahora esta en plantilla funciones
		muestraSlide()
		mostrar_productos();
		//$("#contenedor").html(plantillas.productos);
	});

	$("#carrito").click(function() {
		ocultaSlide()
		mostrar_productos_carrito();
	});

	$("#registrarme").click(function() {
		ocultaSlide()
		$("#contenedor").html(plantillas.registrarme);
	});



	$("#login").click(function() {
		ocultaSlide()
		$("#contenedor").html(plantillas.login);
		//si estas cookies existen metelas en el input
		if (typeof (Cookies.get("email")) != "undefined") {
			$("#email").val(Cookies.get("email"));
		}// el operador type of te da que tipo de dato es
		if (typeof (Cookies.get("pass")) != "undefined") {
			$("#pass").val(Cookies.get("pass"));
		}
		$("#form_login").submit(function(e) {
			e.preventDefault();
			identificar_usuario();
		});
	});//finde de login

	$("#logout").click(function(e) {
		//este evento va con alert y desconectar la funcion logOut
		alertEstasSeguro()
	});//end click logout

	$("#registrarme").click(function() {
		$("#contenedor").html(plantillas.registrarme);
		$("#form_registro_usuario").submit(function(e) {

			//este codigo se ejecuta cuando se 
			//pulsa el boton de submit del form 
			e.preventDefault();//se cancela el envio de form de forma tradicional
			//forma nueva para mandar directamente toda la informacion de un form
			//que tenga o no, uno o mas input type file
			// le metemos el this YA QUE LA CLASE EN LA QUE ESTÄ ES EL FORMULARIO

			let formulario = this;
			let formData = new FormData(formulario);
			let email=formData.get("email");
			let pass=formData.get("pass");

			//validar los campos del formulario
			//de formData puedo obtener el input que quiera
			//por su name, no su id

			if (!validarNombre(formData.get("nombre"))
			 || !validarEmail(formData.get("email")) 
			 || !validarPass(formData.get("pass"))) {

				return false;
			}
			$.ajax("ServicioWebUsuarios/registrarUsuario", {
				type: "POST",
				data: formData,
				cache: false,
				contentType: false,
				processData: false,
				success: function(res) {

					if (res == "ok") {
						alertInfo("ya puedes identificarte con tus datos");
						autoLogin(email,pass);
					} else {

						alertOopsEnlace(res);
					}//end else
				}
			});
		});//end submit

	});//end click

}//cargar eventos fin de cargar eventos------------------------>

//funcion para redirigir conectado despue de registrar

function autoLogin(email, pass) {

	$.ajax("ServicioWebUsuarios/identificarUsuario", {
		type: "post",
		data: "email=" + email + "&pass=" + pass,
		success: function(res) {
			var respuesta = res.split(",")[0];
			var mensaje = res.split(",")[1];
			if (respuesta == "ok") {
				alertDesciende("bienvenido " + mensaje + " ya puedes comprar");
				nombre_login = mensaje;
				mostrar_productos();
				$("#inicio_span_nombre_usuario").html("hola " + nombre_login);
				$("#login").hide();
				$("#registrarme").hide();
				$("#logout").show();
			} else if (respuesta == "error") {
				alertOopsEnlace(mensaje);
			}
			muestraSlide()
		}//end success
	});//end ajax
}

function muestraSlide() {
	$("#slideMain").show();
}

function ocultaSlide() {
	$("#slideMain").hide();;
}

