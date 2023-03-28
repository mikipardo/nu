cargarEventosFotoJPG();
function cargarEventosFotoJPG() {

	//con jquery digo que va a pasar cuando se haga click en un enlace
	$("#btnNuevaCamiseta").click(function() {
		console.log("tramitando .jpg");
		pasarAlAction()
	});

}//cargar eventos


function pasarAlAction() {
	//instancio el form y el posible valor del action
	var valorAction = "guardarNuevaCamiseta";
	var formAction = $('#formNuevaCamiseta');

	var foto = $('#img1').val();
	var foto2 = $('#img2').val();

	var campo1Validado = comprobarJPG(foto);
	var campo2Validado = comprobarJPG(foto2);

	//si los dos estan totalmente vacios o acaba en .jpg pasan
	//le cambio el action al form y lo mando al webService de guardar nueva camiseta
	if (true) {
		formAction.attr("action", valorAction);
		formAction.submit()
	} else {//caso de false cualquiera de los dos
		alert("La imagen tiene que ser en formato .jpg" + "\nUna o mas imagenes no están en formato .jpg");
	}

}

//metodo que al para una cadena comprueba si acaba en JPG o estan vacios que tambien vale true
function comprobarJPG(foto) {
	var terminaJPG = false;
	var posicionPunto = 0;
	var terminacion = "";
	//saco la posicion del punto
	for (let step = (foto.length - 1); 0 <= step; step--) {
		if (foto[step] == ".") {
			posicionPunto = step;
			step = 0;
		}
	}
	//extraigo desde el punto hasta el final (.jpg)
	terminacion = foto.substring(posicionPunto, (foto.length));

	//si termina en .jpg o esta vacio se valida
	if (terminacion == ".jpg" || foto.length < 1) {
		terminaJPG = true;
	}
	return terminaJPG;
}

//data

