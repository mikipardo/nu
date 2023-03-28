<%@ taglib uri="http://www.springframework.org/tags/form"
	prefix="springform"%>
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

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
<title>INuevo Usuario</title>
</head>
<body>


	<jsp:include page="menu.jsp"></jsp:include>



	<div
		class="container col-12 col-sm-10 col-md-9 d-flex flex-column align-items-center">
		<h3>datos del usuario a editar:</h3>

		<hr>


		<springform:form modelAttribute="usuario" class="col-sm-10"
			action="guardarNuevoUsuario">
			<div class="form-group row">
				<label class="col-form-label col-sm-4 col-md-3 col-lg-2"
					for="user-name"> nombre:</label>
				<div class="input-group col-sm-8 col-md-9 col-lg-10">
					<span class="input-group-addon"> <i style="margin: 1rem;"
						class="fa fa-user"></i>
					</span>
					<springform:input class="form-control" path="nombre" />
				
					<span style="color: red"> <springform:errors path="nombre" />
					</span> <br>

				</div>
			</div>

			<div class="form-group row">
				<label class="control-label col-sm-4 col-md-3 col-lg-2" for="email">E-mail</label>
				<div class="input-group col-sm-8 col-md-9 col-lg-10">
					<span class="input-group-addon"> <i style="margin: 1rem;"
						class="fa fa-envelope"></i>
					</span>
					<springform:input class="form-control" type="email" path="email" />
					<span style="color: red"> <springform:errors path="email" />
					</span>
					<br>

				</div>
			</div>

			<div class="form-group row">

				<label class="control-label col-sm-4 col-md-3 col-lg-2" for="number">pass
				</label>
				<div class="input-group col-sm-8 col-md-9 col-lg-10">
					<span class="input-group-addon"> <i style="margin: 1rem;"
						class="fa fa-shield"></i>
					</span>
					<springform:input type="password" path="pass" class="form-control" />
					<span style="color: red"> <springform:errors path="pass" />
					</span>
					<br>

				</div>
			</div>
			<div class="form-group row">

				<label class="control-label col-sm-4 col-md-3 col-lg-2" for="number">
					telefono: </label>
				<div class="input-group col-sm-8 col-md-9 col-lg-10">
					<span class="input-group-addon"> <i style="margin: 1rem;"
						class="fa fa-phone"></i>
					</span>
					<springform:input path="telefono" class="form-control" />
					<br>

				</div>
			</div>
			<div class="form-group row">

				<label class="control-label col-sm-4 col-md-3 col-lg-2" for="number">dni:
					:</label>
				<div class="input-group col-sm-8 col-md-9 col-lg-10">
					<span class="input-group-addon"> <i style="margin: 1rem;"
						class="fa fa-address-card-o"></i>
					</span>
					<springform:input path="dni" class="form-control" />
					<br>

				</div>
			</div>

			<springform:hidden path="id" />
			<div style="margin-top: 2rem;">
				<button type="submit" class="btn btn-success"
					value="GUARDAR CAMBIOS">GUARDAR CAMBIOS</button>
				<a type="reset" href="gestionarUsuarios"
					class="btn btn-outline-secondary">Cancel</a>

			</div>

		</springform:form>


	</div>
	<script src="https://kit.fontawesome.com/794e2f8ba9.js"
		crossorigin="anonymous"></script>
</body>
</html>