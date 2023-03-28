<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Acuerdate de esta linea para el jstl********************************** -->
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
<link rel="stylesheet" type="text/css" href="../css/adminStyle.css">
<title>Gestionar Camisetas</title>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<h1>Administracion de Camisetas</h1>


	<div style="margin: 10px" class="buscadorCamisetas">
		<form action="gestionarCamisetas">
			<!-- importante el name para hibernate  -->
			Buscar por modelo: <input type="text" name="modelo" value="${modelo}" />
			<input type="submit" value="BUSCAR">
		</form>
	</div>


	
		<section id="counter-stats" class="wow fadeInRight"
		data-wow-duration="1.4s">
		<div class="container">
			<div class="row">

				<div class="col-lg-3 stats">
					<i class="fa fa-tachometer" aria-hidden="true"></i>
					<div class="counting" data-count="${total}">0</div>
					<h5>Camisetas Registradas</h5>
				</div>

			</div>
			<!-- end row -->
		</div>
		<!-- end container -->

	</section>

	<div class="container">
		<div class="row">
			<c:forEach var="camiseta" items="${todasCamisetas}">
				<div class="col-sm" style="margin-top: 5rem">
					<div class="card justify-content-center" style="width: 20rem;">
						<!-- token para actualizar foto  -->
						<object style="height: 20rem" class="card-img-top"
							data="../subidas/${camiseta.id}.jpg?fai=${camiseta.fechaImagenPortada}"
							style="height: 20rem;">
							<img style="height: 20rem; width: 100%;"
								src="../imagenes/standard.jpg" />
						</object>
						<div class="card-body">
							<h5 class="card-title">Modelo: ${camiseta.modelo}</h5>
							<p class="card-text">Descripci�n: ${camiseta.descripcion}</p>
						</div>
						<ul class="list-group list-group-flush">
							<li class="list-group-item">Precio: ${camiseta.precio} EUR</li>
							<li class="list-group-item">Teemporada:
								${camiseta.temporada}</li>
							<li class="list-group-item">Talla: ${camiseta.talla}</li>
							<li class="list-group-item">Color: ${camiseta.color}</li>
							<li class="list-group-item">categoria:
								${camiseta.categoria.nombre}</li>
						</ul>
						<div class="card-body">
							<a onclick="return confirm('Borrar seguro?');"
								href="borrarCamiseta?idBorrar=${camiseta.id}"
								class="btn btn-danger">borrar</a> <a
								href="editarCamiseta?idEditar=${camiseta.id}"
								class="btn btn-primary"> editar </a>
						</div>
					</div>
				</div>
			</c:forEach>
			<div style="text-align: center; margin-top: 3rem;">
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<c:if test="${ anterior >= 0 }">
							<li class="page-item"><a class="page-link"
								href="gestionarCamisetas?comienzo=${anterior}&modelo=${modelo}">anterior</a>
							</li>
						</c:if>
						<c:if test="${ siguiente < total }">
							<li class="page-item"><a class="page-link"
								href="gestionarCamisetas?comienzo=${siguiente}&modelo=${modelo}">siguiente</a>
							</li>
						</c:if>
					</ul>
				</nav>
			</div>

		</div>
	</div>

	<script src="https://kit.fontawesome.com/794e2f8ba9.js"
		crossorigin="anonymous"></script>
		<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
		<script src="../librerias_js/jquery.js"
		></script>
		<script src="../javascript/specialEffects.js"
		></script>
</body>
</html>