//funciones de caracter generico
function mostrar_productos() {

	//pedir al servicio de libros los libros
	//y mostrar su resultado usando una plantilla
	$("#contenedor").html(plantillas.productos);
	comienzo = 0;

	modelo_buscar = "";
	$("#buscador_modelo").keyup(
		function() {
			modelo_buscar = this.value;
			ocultaSlide()
			refresca_listado();
		}
	);
	$("#enlace_anterior").click(
		function() {
			comienzo -= 6;
			refresca_listado();
			ocultaSlide();

		}
	);//end click enlace anterior
	$("#enlace_siguiente").click(
		function() {
			comienzo += 6;
			ocultaSlide()
			refresca_listado();

		}
	);
	los3Destacados()
	refresca_listado();

}//end mostrar_productos

//destacados de la Semana

function los3Destacados() {


	$.getJSON("ServicioCamisetas/destacadosSemana", {

	}).done(
		function(res) {		//SE CREA EL ATRIBUTO SI LO LLAMAS
			//es un JSON por lo tanto para sacar el nodo 
			//TIENES QUE PONERLO IGUAL "Camisetas"
			// 1h hasta que has pilladp que es con mayuscula
			//utiliza console.log si estás seguro
			let camisetas = res.Camiseta;

			$("#los3Destacados").html(Mustache.render(plantillas.destacadosMain, camisetas));
			$(".enlace_comprar").click(comprar_producto);

		}
	);//end get obtenerLibros
}//end refresca_listado
//y mostrar su resultado usando una plantilla

function refresca_listado() {

	if (comienzo <= 0) {
		$("#enlace_anterior").addClass("disabled");


	} else {
		$("#enlace_anterior").removeClass("disabled");
	}

	$.getJSON("ServicioCamisetas/obtenerCamisetas", {
		modelo: modelo_buscar,
		comienzo: comienzo
	}).done(
		function(res) {		//SE CREA EL ATRIBUTO SI LO LLAMAS
			//es un JSON por lo tanto para sacar el nodo 
			//TIENES QUE PONERLO IGUAL "Camisetas"
			// 1h hasta que has pilladp que es con mayuscula
			//utiliza console.log si estás seguro
			let camisetas = res.Camiseta;
			let total_camisetas = res.total;

			if (comienzo + 12 > total_camisetas) {
				$("#enlace_siguiente").addClass("disabled");

			} else {
				$("#enlace_siguiente").removeClass("disabled");
			}
			$("#productos_listado").html(Mustache.render(plantillas.productos_listados_for, camisetas));
			$("#total_Camisetas").html(total_camisetas);

			$(".enlace_comprar").click(comprar_producto);

			$(".enlace_detalles").click(mostrarDetallesProducto);//end click detales

		}
	);//end get obtenerLibros
}//end refresca_listado
//y mostrar su resultado usando una plantilla

//funciones de detalle producto

function mostrarDetallesProducto() {

	//recojo id- el atributo en la etiqueta me lo invento para diferenciar
	// a pesar de  que salga en amarillo en el html
	let idDetalle = $(this).attr("id_detalle");
	recomendaciones(idDetalle)
	ocultaSlide()
	$.post("ServicioCamisetas/obtenerDetalleCamiseta",
		{
			idDetalleProducto: idDetalle
		})
		.done(
			function(res) {
				var detallesProducto = JSON.parse(res);
				console.log("detalles " + detallesProducto)

				$("#contenedor").html(Mustache.render(plantillas.detalleProducto, detallesProducto));
			
			});

}//fin mostrar detalles producto

function recomendaciones(id) {
	//recojo id- el atributo en la etiqueta me lo invento para diferenciar
	// a pesar de  que salga en amarillo en el html


	$.getJSON("ServicioCamisetas/obtenerRecomendados",
		{
			idProducto: id
		})
		.done(
			function(res) {
				//var recomendacionesJson = JSON.parse(res);
				var recomendacionesJson = res.Camiseta;
				console.log("recomendaciones " + recomendacionesJson)

				$("#recomendador").html(Mustache.render(plantillas.footerDetalles, recomendacionesJson));
				$(".enlace_detalles").click(mostrarDetallesProducto);//end click detales
		
			});
}//fin recomendaciones

//fin funciones detalle producto
function mostrar_productos_carrito() {

	if (nombre_login == "") {
		alertInfo("debes identificarte para acceder a tu carrito");
		muestraSlide()
		return;
	}

	$.getJSON("ServicioWebCarrito/obtenerProductosCarrito", function(res) {
		console.log(res);

		$("#contenedor").html(Mustache.render(plantillas.carrito, res));
		$(".input_cantidad").change(function() {
			let idProducto = $(this).attr("idCantidad");
			let cantidadProducto = $("#inputProducto-cantidad-" + idProducto).val();

			actualizaCantiddadCamiseta(idProducto, cantidadProducto)
		});

		$(".enlace_borrar_producto").click(function() {
			let idProductoCarrito = $(this).attr("id-producto");

			ejecutaAlertMasBorrarProducto(idProductoCarrito)
		});
		$("#realizar_pedido").click(function() {
			checkout_paso_1();
		});
		//zona de si hay compra pasa o no

		if (Object.entries(res).length === 0) {

			$("#realizar_pedido").addClass("disabled");
			$("#carritoVacio").show();
			$("#listaCarrito").hide();
		} else {
			$("#listaCarrito").show();
			$("#carritoVacio").hide();
			$("#realizar_pedido").removeClass("disabled");
		}


	});

}//end mostrar_productos_carrito  


function ejecutaAlertMasBorrarProducto(idProductoCarrito) {
	alertFalseTrue("Eliminar producto", "¿Quiere quitar del carrito este producto?", "Quitar", "Cancelar", idProductoCarrito);
}


function borraProductoCarrito(idProductoCarrito) {
	//este metodo se ejecuta con el ejecutaAlertMasBorrarProducto-> en realidad se hace todo desde el alert de alertTrueFalse
	//ya uqe no podias hacer return y tienes que meter el metodo en el resultado de isConfirmed
	$.post("ServicioWebCarrito/borrarCamisetaCarrito",
		{
			idProducto: idProductoCarrito
		}
	).done(function(res) {
		mostrar_productos_carrito()
		console.log(res);
	});//end post

}//end actualizaCantiddadCamiseta

function actualizaCantiddadCamiseta(idProductoCarrito, cantidadProducto) {

	if (cantidadProducto < 1) {
		alertInfo("La cantidad no puede ser menor que 1")
	} else {
		$.post("ServicioWebCarrito/actualizaCantiddadCamiseta",
			{
				cantidad: cantidadProducto,
				idProducto: idProductoCarrito
			}
		).done(function(res) {

			console.log(res);
			if (res == "cantidadError") {
				alert("Hubo un error su producto no se agregó");
			}

		});//end post
	}//end else
}//end actualizaCantiddadCamiseta

function comprar_producto() {

	if (nombre_login == "") {
		alertOopsEnlace("debes identificarte para poder comprar productos")

	} else {

		//asi obtengo la id del producto a agregar al carrito
		var id = $(this).attr("id_producto");
		var cantidad = $("#input-cantidad-" + id).val();
			
if (cantidad === undefined) {
  cantidad=1;
}

		//2/3 en control cantidad de -1
		if (cantidad < 1) {

			alertInfo("No puede comprar articulos  de menos de una cantidad");
		} else {

			//para hacer un post con jquery es mas comodo 
			//lo siguiente:
			$.post("ServicioWebCarrito/agregarCamiseta",
				{
					cantidad: cantidad,
					idProducto: id
				}
			).done(function(res) {

				if (res == "cantidadError") {
					alert("Hubo un error su producto no se agregó");
				} else {
					alertMockUp('Su producto se agregó al carrito')
					console.log(res);
				}


			});//end post
		}
	}//end else

}//end comprar_producto

function logout() {

	$.ajax("ServicioWebUsuarios/logout", {
		success: function(res) {
			if (res == "ok") {
				alertInfo("Hasta pronto")
				nombre_login = "";
				$("#login").show();
				$("#registrarme").show();
				$("#logout").hide();
				$("#inicio_span_nombre_usuario").html("(Usuario no identificado)");
			}
		}
	});
}//end logout

function identificacion_auto(email, pass) {
	$.ajax("ServicioWebUsuarios/identificarUsuario", {
		type: "post",
		data: "email=" + email + "&pass=" + pass,
		success: function(res) {
			var respuesta = res.split(",")[0];
			var mensaje = res.split(",")[1];
			if (respuesta == "ok") {
				//si el usuario activo recordar datos
				//guardamos su email y pass en una cookie
				if ($("#recordar_datos").prop("checked")) {
					Cookies.set('email', email, { expires: 100 });
					Cookies.set('pass', pass, { expires: 100 });//js.cokies.min metodo setea cokies
				}//end checked
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
		}//end success
	});//end ajax
}


function identificar_usuario() {

	var email = $("#email").val();
	var pass = $("#pass").val();

	$.ajax("ServicioWebUsuarios/identificarUsuario", {
		type: "post",
		data: "email=" + email + "&pass=" + pass,
		success: function(res) {
			var respuesta = res.split(",")[0];
			var mensaje = res.split(",")[1];
			if (respuesta == "ok") {
				//si el usuario activo recordar datos
				//guardamos su email y pass en una cookie
				if ($("#recordar_datos").prop("checked")) {
					Cookies.set('email', email, { expires: 100 });
					Cookies.set('pass', pass, { expires: 100 });//js.cokies.min metodo setea cokies
				}//end checked
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
		}//end success
	});//end ajax

}//end identificar_usuario
//FUNCIONES ESPECIALES

//ZONA DE ALERTS PERSONALIZADOS
//************************************************ */

function alertDesciende(stringMensaje) {
	Swal.fire({
		title: stringMensaje,
		color: '#FFFFFF',
		background: '#272727',
		showClass: {
			popup: 'animate__animated animate__fadeInDown'
		},
		hideClass: {
			popup: 'animate__animated animate__fadeOutUp'
		}
		, confirmButtonColor: '#757678'
	})
}

function alertMockUp(stringMensaje) {
	//alert Personalizado
	Swal.fire({
		position: 'center',
		icon: 'success',
		color: '#FFFFFF',
		background: '#272727',
		title: stringMensaje,
		showConfirmButton: false,
		timer: 2000
	})
	//alert Personalizado
}

function alertInfo(stringMensaje) {
	//alert Personalizado
	Swal.fire({
		position: 'center',
		icon: 'info',
		color: '#FFFFFF',
		background: '#272727',
		title: stringMensaje,
		showConfirmButton: false,
		timer: 2000
	})
	//alert Personalizado
}

function alertOopsEnlace(stringFrase) {
	Swal.fire({
		icon: 'error',
		title: 'Oops...',
		text: stringFrase,
		color: '#FFFFFF',
		background: '#272727',
		confirmButtonColor: '#757678'
	})
}

//zona de desconectar/deslogeo
function alertEstasSeguro() {
	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success',
			cancelButton: 'btn btn-danger',

		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: "Desconectar",
		text: "Está seguro de que desea salir?",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonText: "Permanecer",
		cancelButtonText: "Desconectar",
		color: '#FFFFFF',
		background: '#272727',
		reverseButtons: true
	}).then((result) => {
		if (result.isConfirmed) {
			result.dismiss === Swal.DismissReason.cancel

		} else if (
			/* Read more about handling dismissals below */

			logout()
		) {

		}
	})
}

function alertFalseTrue(cabecera, texto, confirmText, cancelText, idProductoCarrito) {


	const swalWithBootstrapButtons = Swal.mixin({

		customClass: {//son clases de bootsrap
			confirmButton: 'btn btn-success mr-4',
			cancelButton: 'btn btn-danger mr-4',

		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: cabecera,
		text: texto,
		icon: 'warning',
		showCancelButton: true,
		confirmButtonText: confirmText,
		cancelButtonText: cancelText,
		color: '#FFFFFF',
		background: '#272727',
		reverseButtons: true,

	}).then((result) => {
		if (result.isConfirmed) {
			borraProductoCarrito(idProductoCarrito);
		}
	})
}
//FIN DE ZONA DE ALERTS PERSONALIZADOS
//************************************************ */
