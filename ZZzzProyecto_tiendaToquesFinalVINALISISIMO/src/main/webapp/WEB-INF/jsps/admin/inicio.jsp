
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css"
	rel="stylesheet">
<title>Bootstrap Example</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
	<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
<title>Admin</title>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<h1>Bienvenido a la zona de Administraciï¿½n</h1>




	<div class="container-fluid">
		<section>
			<div class="row">
				<div class="col-12 mt-3 mb-1">
					<h5 class="text-uppercase">Esta semana</h5>
					<p>Datos de la tienda online</p>
				</div>
			</div>
			<div class="row">
				<div class="col-xl-3 col-sm-6 col-12 mb-4">
					<div class="card">
						<div class="card-body">
							<div class="d-flex justify-content-between px-md-1">
								<div class="align-self-center">
									<i class="fa fa-users text-info fa-3x"></i>
								</div>
								<div class="text-end">
									<h3>${datos.clientes}</h3>
									<p class="mb-0">Clientes</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 col-12 mb-4">
					<div class="card">
						<div class="card-body">
							<div class="d-flex justify-content-between px-md-1">
								<div class="align-self-center">
									<i class="far fa-comment-alt text-warning fa-3x"></i>
								</div>
								<div class="text-end">
									<h3>${datos.camisetas}</h3>
									<p class="mb-0">Camisetas</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 col-12 mb-4">
					<div class="card">
						<div class="card-body">
							<div class="d-flex justify-content-between px-md-1">
								<div class="align-self-center">
									<i class="fas fa-chart-line text-success fa-3x"></i>
								</div>
								<div class="text-end">
									<h3>${datos.pedidos}</h3>
									<p class="mb-0">Pedidos</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 col-12 mb-4">
					<div class="card">
						<div class="card-body">
							<div class="d-flex justify-content-between px-md-1">
								<div class="align-self-center">
									<i class="fas fa-map-marker-alt text-danger fa-3x"></i>
								</div>
								<div class="text-end">
									<h3>${datos.ventas}</h3>
									<p class="mb-0">Ventas</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xl-3 col-sm-6 col-12 mb-4">
					<div class="card">
						<div class="card-body">
							<div class="d-flex justify-content-between px-md-1">
								<div>
									<h3 class="text-danger">${datos.bajas}</h3>
									<p class="mb-0">Productos con Bajas</p>
								</div>
								<div class="align-self-center">
									<i class="fas fa-rocket text-danger fa-3x"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 col-12 mb-4">
					<div class="card">
						<div class="card-body">
							<div class="d-flex justify-content-between px-md-1">
								<div>
									<h3 class="text-success">${datos.altas}</h3>
									<p class="mb-0">Productos con Altas</p>
								</div>
								<div class="align-self-center">
									<i class="far fa-user text-success fa-3x"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 col-12 mb-4">
					<div class="card">
						<div class="card-body">
							<div class="d-flex justify-content-between px-md-1">
								<div>
									<h3 class="text-warning">${datos.precio} eu.</h3>
									<p class="mb-0">Valor de la Mercancia</p>
								</div>
								<div class="align-self-center">
									<i class="fas fa-chart-pie text-warning fa-3x"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 col-12 mb-4">
					<div class="card">
						<div class="card-body">
							<div class="d-flex justify-content-between px-md-1">
								<div>
									<h3 class="text-info">${datos.totales}%</h3>
									<p class="mb-0">Fuera de Temporada</p>
								</div>
								<div class="align-self-center">
									<i class="far fa-life-ring text-info fa-3x"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-xl-3 col-sm-6 col-12 mb-4">
					<div class="card">
						<div class="card-body">
							<div class="d-flex justify-content-between px-md-1">
								<div>
									<h3 class="text-info">${datos.caro} eu.</h3>
									<p class="mb-0">La Más Cara</p>
								</div>
								<div class="align-self-center">
									<i class="fas fa-book-open text-info fa-3x"></i>
								</div>
							</div>
							<div class="px-md-1">
								<div class="progress mt-3 mb-1 rounded" style="height: 7px;">
									<div class="progress-bar bg-info" role="progressbar"
										style="width: ${datos.caro}%;" aria-valuenow="${datos.caro}" aria-valuemin="0"
										aria-valuemax="100"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 col-12 mb-4">
					<div class="card">
						<div class="card-body">
							<div class="d-flex justify-content-between px-md-1">
								<div>
									<h3 class="text-warning">${datos.bajo} eu.</h3>
									<p class="mb-0">LA Más Económica</p>
								</div>
								<div class="align-self-center">
									<i class="far fa-comments text-warning fa-3x"></i>
								</div>
							</div>
							<div class="px-md-1">
								<div class="progress mt-3 mb-1 rounded" style="height: 7px;">
									<div class="progress-bar bg-warning" role="progressbar"
										style="width: ${datos.bajo}%;" aria-valuenow="${datos.bajo}" aria-valuemin="0"
										aria-valuemax="100"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 col-12 mb-4">
					<div class="card">
						<div class="card-body">
							<div class="d-flex justify-content-between px-md-1">
								<div>
									<h3 class="text-success">${datos.porcentaje} eu.</h3>
									<p class="mb-0">Valor Medio de Compra</p>
								</div>
								<div class="align-self-center">
									<i class="fas fa-mug-hot text-success fa-3x"></i>
								</div>
							</div>
							<div class="px-md-1">
								<div class="progress mt-3 mb-1 rounded" style="height: 7px;">
									<div class="progress-bar bg-success" role="progressbar"
										style="width: ${datos.porcentaje}%;" aria-valuenow="${datos.porcentaje}" aria-valuemin="0"
										aria-valuemax="100"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 col-12 mb-4">
					<div class="card">
						<div class="card-body">
							<div class="d-flex justify-content-between px-md-1">
								<div>
									<h3 class="text-danger">${datos.media} eu.</h3>
									<p class="mb-0">Media de Precios de Producto</p>
								</div>
								<div class="align-self-center">
									<i class="fas fa-map-signs text-danger fa-3x"></i>
								</div>
							</div>
							<div class="px-md-1">
								<div class="progress mt-3 mb-1 rounded" style="height: 7px;">
									<div class="progress-bar bg-danger" role="progressbar"
										style="width: ${datos.media}%;" aria-valuenow="${datos.media}" aria-valuemin="0"
										aria-valuemax="100"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	<div style="padding: 10rem;">
	<h1 style="text-align: center;">Estadísticas:</h1>
	<canvas id="myChart" style="width:100% ; margin-bottom: 2rem;"></canvas>
	<h4>Dias :</h4>
	<h4 style="color: green;">Venta Total Finalizada</h4>
	<h4 style="color: blue;">Venta en Proceso</h4>
	<h4 style="color: red;">Media de Presupuesto</h4>
	</div>
<script src="https://kit.fontawesome.com/794e2f8ba9.js" crossorigin="anonymous"></script>
	<script type="text/javascript" src="../librerias_js/jquery.js"></script>
<script>

var listaDias;
var ventaProceso;
var listaVentas;


$.getJSON("chartsDias", {

}).done(
	function(res) {	
		
		listaDias = res.listaDias;
	}
);

$.getJSON("ventaEnProceso", {

}).done(
	function(res) {	
		console.log(res)
		ventaProceso = res.listaVentaProceso
;
		
	}
);

$.getJSON("chartsVentas", {

}).done(
	function(res) {	
		
	 listaVentas = res.listaVentas;
	
		console.log(listaVentas)
	
//hasta que no termine la segunda peticion no podemos cargar canvas

	 for (x in listaDias) {
		  console.log(listaDias[x]);
		  listaDias[x]="\""+listaDias[x]+"\"";
		}
	 //Comilleaamos el arrray ya que si no resta 7-5-2022 por el menos 

		var xValues = listaDias;

			var redNumber = [300,400,500,200,300,200,150,290,630,785,900]

			var ventasValues = listaVentas;
			
			var ventasProcesoTodas =ventaProceso;

			new Chart("myChart", {
			  type: "line",
			  data: {
			    labels: xValues,
			    datasets: [{ 
			 
			      data: ventasValues,
			      borderColor: "green",
			      fill: false
			    }, { 
			    	
			      data: ventasProcesoTodas,
			      
			      borderColor: "blue",
			      fill: false
			    }, { 
			        data: redNumber,
			        borderColor: "red",
			        fill: false
			      }]
			  },
			  
			  
			  options: {
			    legend: {display: false}
			  }
			  
			  
			});
			
			
		
	}
);



</script>
</body>
</html>