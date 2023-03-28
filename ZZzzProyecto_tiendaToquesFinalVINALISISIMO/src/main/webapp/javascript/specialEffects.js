
contador();
cambiaFotoDetalles();

// number count for stats, using jQuery animate
// contador de user y camisetas de zona admin


function contador() {
	$(".counting").each(function() {
		var $this = $(this),
			countTo = $this.attr('data-count');

		$({ countNum: $this.text() }).animate({
			countNum: countTo
		},

			{

				duration: 3000,
				easing: 'linear',
				step: function() {
					$this.text(Math.floor(this.countNum));
				},
				complete: function() {
					$this.text(this.countNum);
					//alert('finished');
				}

			});

	});
}

// Fin de contador de camisetas y user


//Detalles Producto
function cambiaFotoDetalles(){
	producto1Change();
	producto2Change();
	producto3Change();
	$(".enlace_comprar").click(comprar_producto);
}
function producto1Change(){
	let id = $("#idCampoDetalle").attr("valorImg");
	let ruta="subidas/"+id+".jpg"
	$("#producto1").click(function(){
		$("#obejoDetalle").attr('data', ruta);
		$("#detalleImgMain").attr('src', ruta);
	
	});
}
function producto2Change(){
	let id = $("#idCampoDetalle").attr("valorImg");
	let ruta="subidas/"+id+"B.jpg"
	$("#producto2").click(function(){
		$("#obejoDetalle").attr('data', ruta);
		$("#detalleImgMain").attr('src', ruta);
	
	});
}
function producto3Change(){
	let id = $("#idCampoDetalle").attr("valorImg");
	let ruta="subidas/"+id+"X.jpg"
	$("#producto3").click(function(){
		$("#obejoDetalle").attr('data', ruta);
		$("#detalleImgMain").attr('src', ruta);
	
	});
}

//Fin detalles producto