<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Gestionar Pedidos</title>
</head>
<body>

	<jsp:include page="menu.jsp"></jsp:include>

	<div class="container-fluid" id="myLista">
		<h1 class="display-4 text-center" id="tituloLista">Listado de pedidos:</h1>
		<table id="tabla1" class="table table-inverse">
			<thead class="thead-default">
				<tr>
					<th>No</th>
					<th>Nombre</th>
					<th>Direción</th>
					<th>Date</th>
					<th>Status</th>
					<th>vista</th>
				</tr>
			</thead>
			<c:forEach var="pedido" items="${pedidos}">
				<tbody>
					<tr>
						<td>${pedido.id}</td>
						<td>${pedido.nombreCompleto}</td>
						<td>${pedido.direccion}</td>
						<td>${pedido.fecha}  [${pedido.horario}h]</td>
						<td>${pedido.status}</td>
						<td><a href="verDetallesPedido?id=${pedido.id}"
							target="_blank">
								<button type="button" class="btn btn-outline-success">
									ver pedido</button>
						</a></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</div>
	<script>
document.getElementById("myLista").style.maxWidth = "100%";
document.getElementById("myLista").style.marginTop = "5rem";
document.getElementById("tabla1").style.marginTop = "5rem";
</script>
</body>
</html>

