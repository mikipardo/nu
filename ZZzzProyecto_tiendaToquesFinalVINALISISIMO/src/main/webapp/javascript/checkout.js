
function checkout_paso_1() {
	//datos de la fecha pasamos por parametro un dia actual para usarlo en 2 metodos separados
	var now = new Date();
	var day = ("0" + now.getDate()).slice(-2);
	var month = ("0" + (now.getMonth() + 1)).slice(-2);
	var today = now.getFullYear() + "-" + (month) + "-" + (day);

	//datos de envio del pedido
	$("#contenedor").html(plantillas.checkout_1);
	dameFecha(today);
	cargarHorarios();
	$("#aceptar_paso_1").click(function() {
		let nombre = $("#campo_nombre").val();
		let direccion = $("#campo_direccion").val();
		let provincia = $("#campo_provincia").val();
		let fecha = $("#campo_fecha").val();
		let horario = $("#campo_horario").val();

		if (fecha >= today) {
			$.post("ServicioWebPedidos/paso1",
				{
					nombre: nombre,
					direccion: direccion,
					provincia: provincia,
					fecha: fecha,
					horario: horario
				}
			).done(function(res) {
				if (res == "ok") {
					//mostrar la plantilla del paso 2
					checkout_paso_2();
				} else {
					alertOopsEnlace("Ha ocurrido un error con su pedido, intenteló más tarde");
				}
			});	//end done

		} else {
			alert('No puede usar una fecha inferior al mismo día actual');
		};	//end else

	});//end click	
}//end checkout_paso_1


//Zona de pasos de CheckOut

function checkout_paso_2() {
	$("#contenedor").html(plantillas.checkout_2);

	$("#aceptar_paso_2").click(function() {
		//juntamos los input
		let anno = $("#anno").val();
		let mes = $("#mes").val();
		let caducidad = mes + "/" + anno;

		$.post("ServicioWebPedidos/paso2", {
			titular: $("#titular_tarjeta").val(),
			numero: $("#numero_tarjeta").val(),
			fechaCaducida: caducidad
		}).done(function(res) {
			if (res.substring(0, 2) == "ok") {
				//lo pasamos a JSON
				if (res == "ok") {
					//mostrar la plantilla del paso 2
					checkout_paso_2BIS();
				} else {
					alertOopsEnlace("Ha ocurrido un error con su pedido paso2, intenteló más tarde");
				}
				//el resumen va a checOut3 los campos son iguales de resumenPedido
			}
		});

	});
}
//opcional

function checkout_paso_2BIS() {
	$("#contenedor").html(plantillas.checkout_2BIS);

	$("#aceptar_paso2BIS").click(function() {
		//juntamos los input
		let comentario = $("#areaComentario").val();
	

		$.post("ServicioWebPedidos/paso2BIS", {
			comentario: comentario
		}).done(function(res) {
			if (res.substring(0, 2) == "ok") {
				//lo pasamos a JSON
				let json = JSON.parse(res.substring(3, res.length));
				//lo pasamos a Formato Mustache
				let html = Mustache.render(plantillas.checkout_3, json);
				//se lo incrustamos al html
				$("#contenedor").html(html);
				$("#boton_confirmar_pedido").click(checkout_confirmar);
				//el resumen va a checOut3 los campos son iguales de resumenPedido
			}
		});

	});
}

//confirmacion de pedido de checkOut3.html
function checkout_confirmar() {
	$.ajax("ServicioWebPedidos/confirmarPedido", {
		success: function(res) {
			alertDesciende(res);
			mostrar_productos();
		}
	});
}//end checkout_confirmar

//establece por defecto minimo la fecha de hoy y no deja poner una fecha menor 
function dameFecha(today) {
	$(document).ready(function() {
		$('#campo_fecha').val(today);
		$('#campo_fecha').attr('min', new Date().toISOString().split('T')[0])
	});
}

function cargarHorarios() {
	const $select = document.querySelector("#campo_horario");
	for (let step = 9; step < 21; step++) {
		const option = document.createElement('option');
		const valor = "de " + step + " h a " + (step + 1) + " h";
		option.value = step + "-" + (step + 1);
		option.text = valor;
		$select.appendChild(option);
	};
}