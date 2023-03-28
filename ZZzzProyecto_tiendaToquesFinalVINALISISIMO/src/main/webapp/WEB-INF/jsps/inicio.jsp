<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>

<link href='https://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
<!--  Content  -->
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">
	<link rel="stylesheet" href="css/special.css">
<title>Mi Tienda Online</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-dark navStiky" id="navFirst">
		<div class="container-fluid navStiky">

			<a href="#" class="navbar-brand" id="inicio"><spring:message
					code="inicio.inicio" /> </a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#" id="carrito"><spring:message
								code="inicio.carrito" /></a></li>
					<li class="nav-item"><a href="#" class="nav-link" id="login"><spring:message
								code="inicio.identificarme" /></a></li>
					<li class="nav-item"><a href="#" class="nav-link"
						id="registrarme"><spring:message code="inicio.registrarme" /></a></li>

					<li class="nav-item"><a href="#" class="nav-link" id="logout"
						style="display: none" id="registrarme"><spring:message
								code="inicio.salir" /> </a></li>

					<li class="nav-item"><span class="nav-link disabled"
						id="inicio_span_nombre_usuario">(<spring:message
								code="inicio.userNoIdentificado" />)
					</span></li>
				</ul>
				<form class="d-flex" role="search">
					<input class="form-control me-2" type="search"
						placeholder=<spring:message code="inicio.buscar" />
						aria-label="Search" id="buscador_modelo">
					<button class="btn btn-outline-success" type="submit">
						<spring:message code="inicio.buscar" />
					</button>
				</form>
			</div>
		</div>
		<div class="displayFlex">
			<a href="?lang=es"><spring:message code="inicio.espannol" />|</a> <a
				href="?lang=en"><spring:message code="inicio.ingles" />|</a> <a
				href="?lang=fr"><spring:message code="inicio.frances" /></a>
		</div>
	</nav>

	<section class="slidesMik" id="slideMain">
		<div id="carouselExampleIndicators" class="carousel slide slidesMik"
			data-bs-ride="true">
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="0" class="active" aria-current="true"
					aria-label="Slide 1"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="1" aria-label="Slide 2"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="2" aria-label="Slide 3"></button>
			</div>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="imagenes/main1.jpg" class="d-block w-100 mainJPG"
						alt="...">
				</div>
				<div class="carousel-item">
					<img src="imagenes/main2.jpg" class="d-block w-100 mainJPG"
						alt="...">
				</div>
				<div class="carousel-item">
					<img src="imagenes/main3.jpg" class="d-block w-100 mainJPG"
						alt="...">
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>

	</section>

	
	<div class="contenedorMain" id="contenedor"></div>

	<footer class="bg-dark text-center text-white">
		<!-- Grid container -->
		<div class="container p-4 pb-0">
			<!-- Section: Social media -->
			<section class="mb-4">
				<!-- Facebook -->
				<a class="btn btn-outline-light btn-floating m-1" href="#!"
					role="button"><i class="fab fa-facebook-f"></i></a>

				<!-- Twitter -->
				<a class="btn btn-outline-light btn-floating m-1" href="#!"
					role="button"><i class="fab fa-twitter"></i></a>

				<!-- Google -->
				<a class="btn btn-outline-light btn-floating m-1" href="#!"
					role="button"><i class="fab fa-google"></i></a>

				<!-- Instagram -->
				<a class="btn btn-outline-light btn-floating m-1" href="#!"
					role="button"><i class="fab fa-instagram"></i></a>

				<!-- Linkedin -->
				<a class="btn btn-outline-light btn-floating m-1" href="#!"
					role="button"><i class="fab fa-linkedin-in"></i></a>

				<!-- Github -->
				<a class="btn btn-outline-light btn-floating m-1" href="#!"
					role="button"><i class="fab fa-github"></i></a>
			</section>
			<!-- Section: Social media -->
		</div>
		<!-- Grid container -->

		<!-- Copyright -->
		<div class="text-center p-3"
			style="background-color: rgba(0, 0, 0, 0.2);">

			<a class="text-white"
				href="https://www.linkedin.com/in/miguel-%C3%A1ngel-pardo-l%C3%B3pez-459a74130/"><spring:message
					code="inicio.siguenos" /></a>
		</div>
		<!-- Copyright -->
	</footer>


	<script type="text/javascript" src="librerias_js/jquery.js"></script>
	<script type="text/javascript" src="librerias_js/mustache.js"></script>
	<script type="text/javascript" src="librerias_js/js.cookie.min.js"></script>
	<script type="text/javascript" src="javascript/cargar_plantillas.js"></script>
	<script type="text/javascript" src="javascript/cargar_eventos.js"></script>
	<script type="text/javascript" src="javascript/funciones.js"></script>
	<script type="text/javascript" src="javascript/checkout.js"></script>
	<script type="text/javascript"
		src="librerias_js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript" src="librerias_js/bootstrap.min.js"></script>
	<script type="text/javascript" src="javascript/globales.js"></script>
		<script type="text/javascript" src="javascript/specialEffects.js"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script type="text/javascript" src="javascript/validaciones.js"></script>
		
</body>
</html>