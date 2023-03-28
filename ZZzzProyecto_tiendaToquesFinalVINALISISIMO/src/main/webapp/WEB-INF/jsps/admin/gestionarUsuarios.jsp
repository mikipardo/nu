<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://getbootstrap.com/docs/5.2/assets/css/docs.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../css/adminStyle.css">

<title>Gestionar Usuarios</title>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<h1>Zona de Gesti�n de Usuarios</h1>


	<div style="margin: 10px" class="buscadorCamisetas">
		<form action="gestionarUsuarios">
			<!-- importante el name para hibernate  -->
			Buscar por nombre: <input type="text" name="nombre" value="${nombre}" />
			<input type="submit" value="BUSCAR">
		</form>
	</div>

	<section id="counter-stats" class="wow fadeInRight"
		data-wow-duration="1.4s">
		<div class="container">
			<div class="row">

				<div class="col-lg-3 stats">
					<i class="fa fa-user" aria-hidden="true"></i>
					<div class="counting" data-count="${totalUSer}">0</div>
					<h5>Usuarios Registrados</h5>
				</div>

			</div>
			<!-- end row -->
		</div>
		<!-- end container -->

	</section>



	<div class="container" style="margin-bottom: 5rem">
		<div class="row">
			<c:forEach var="user" items="${todosUsuarios}">
				<div style="margin-top: 3rem;" class="col-sm">
					<object style="height: 15rem; width: 18rem" class="card-img-top"
						data="../subidas/u${user.id}.jpg">
						<img style="height: 15rem; width: 18rem"
							src="../imagenes/standardUser.jpg" />
					</object>
					<div class="card" style="width: 18rem;">

						<!-- token para actualizar foto  -->
						<!-- <img style="height: 18rem"  class="card-img-top" src="../subidas/${user.id}.jpg"/> -->
						<div class="card-body">
							<h5 class="card-title">nombre: ${user.nombre}</h5>

						</div>
						<ul class="list-group list-group-flush">

							<li class="list-group-item"><div>
									<span><b>Email:</b></span>
								</div>${user.email}</li>

							<li class="list-group-item"><div>
									<span><b>Telefono:</b></span>
								</div>${user.telefono}</li>
							<li class="list-group-item"><div>
									<span><b>DNI:</b></span>
								</div> ${user.dni}</li>

						</ul>
						<div class="card-body">
							<a onclick="return confirm('Est�s seguro?');"
								href="borrarUsuario?idBorrar=${user.id}"
								class="card-link btn btn-danger">borrar</a> <a
								href="editarUsuario?idEditar=${user.id}"
								class="card-link btn btn-primary"> editar </a>

						</div>
					</div>

				</div>

			</c:forEach>
			<div style="text-align: center; margin-top: 3rem;">
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<c:if test="${ anterior >= 0 }">
							<li class="page-item"><a class="page-link"
								href="gestionarUsuarios?comienzo=${anterior}">anterior</a>
							</li>
						</c:if>
						<c:if test="${ siguiente < totalUSer }">
							<li class="page-item"><a class="page-link"
								href="gestionarUsuarios?comienzo=${siguiente}">siguiente</a>
							</li>
						</c:if>
					</ul>
				</nav>
			</div>
		</div>

	</div>
		<script type="text/javascript" src="librerias_js/jquery.js"></script>
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