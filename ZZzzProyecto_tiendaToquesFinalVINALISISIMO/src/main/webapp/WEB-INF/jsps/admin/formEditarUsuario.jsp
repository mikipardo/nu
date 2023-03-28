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


<title>Editar Usuarios</title>
</head>
<body>


	<jsp:include page="menu.jsp"></jsp:include>



	<div class="alert alert-danger d-none"></div>
	<springform:form id="myForm" class="needs-validation"
		modelAttribute="usuario" action="actualizaUsuario">

		<div class="mb-3">
			<h1>Datos del usuario a editar:</h1>
			<label for="exampleInputName" class="form-label">Nombre</label>
			<div class="input-group has-validation">
				<span class="input-group-text"></span>
				<springform:input type="text" class="form-control"
					id="exampleInputName" path="nombre" placeholder="Enter name" />
				<span style="color: red"> <springform:errors path="nombre" />
				</span> <br>

				<div class="invalid-feedback">Name can't be blank</div>
				<div class="valid-feedback">Looks good!</div>
			</div>

		</div>

		<div class="mb-3">
			<label for="exampleInputEmail" class="form-label">Email</label>
			<div class="input-group has-validation">
				<span class="input-group-text">example-</span>
				<springform:input type="email" path="email" class="form-control"
					id="exampleInputEmail" placeholder="Enter email" />
				<span style="color: red"> <springform:errors path="email" />
				</span> <br> <span class="input-group-text"></span>
				<div class="invalid-feedback">Email can't be blank</div>
				<div class="valid-feedback">Looks good!</div>
			</div>

		</div>

		<div class="mb-3">
			<label for="exampleInputPassword" class="form-label">Password</label>
			<div class="input-group has-validation"
				data-controller="password-visibility"
				data-password-visibility-hidden-class="d-none">
				<springform:input path="pass" type="password" class="form-control"
					id="exampleInputPassword" placeholder="Password"
					data-password-visibility-target="input" />
				<span style="color: red"> <springform:errors path="pass" />
				</span>
				<div class="invalid-feedback">Please provide a valid value.</div>
				<div class="valid-feedback">Looks good!</div>
			</div>

		</div>
		<div class="mb-3">
			<label for="exampleInputPassword" class="form-label">Teléfono</label>
			<div class="input-group has-validation"
				data-controller="password-visibility"
				data-password-visibility-hidden-class="d-none">
				<springform:input path="telefono" type="text" class="form-control"
					id="exampleInputPassword" placeholder="Telefon"
					data-password-visibility-target="input" />


				<div class="invalid-feedback">Please provide a valid value.</div>
				<div class="valid-feedback">Looks good!</div>
			</div>

		</div>
		<div class="mb-3">
			<label for="exampleInputPassword" class="form-label">DNI</label>
			<div class="input-group has-validation"
				data-controller="password-visibility"
				data-password-visibility-hidden-class="d-none">
				<springform:input path="dni" type="text" class="form-control"
					id="exampleInputPassword" placeholder="DNI"
					data-password-visibility-target="input" />

				<div class="invalid-feedback">Please provide a valid value.</div>
				<div class="valid-feedback">Looks good!</div>
			</div>

		</div>
		<springform:hidden path="id" />
		<button type="submit" class="btn btn-success" value="GUARDAR CAMBIOS">GUARDAR
			CAMBIOS</button>
		<a type="reset" href="gestionarUsuarios"
			class="btn btn-outline-secondary">Cancel</a>
	</springform:form>
	<script src="https://kit.fontawesome.com/794e2f8ba9.js"
		crossorigin="anonymous"></script>
		<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		document.getElementById("myForm").style.maxWidth = "50%";
		document.getElementById("myForm").style.marginTop = "5rem";
		document.getElementById("myForm").style.marginLeft = "2rem";
	</script>
</body>
</html>