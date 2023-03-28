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



<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<title>Editar Camisetas</title>
</head>
<body>
	<div style="margin: 10px">
		<form action="gestionarLibros">
			titulo: <input type="text" name="titulo" value="${titulo}" /> <input
				type="submit" value="BUSCAR">
		</form>
	</div>

	<jsp:include page="menu.jsp"></jsp:include>

	<main class="bg-light">
		<springform:form modelAttribute="camiseta" action="actualizaCamiseta"
			enctype="multipart/form-data">
			<div class="container">
				<div class="py-5 text-center"></div>

				<div class="row">
					<div class="col-md-6 order-md-2 mb-4">
						<h4 class="d-flex justify-content-between align-items-center mb-3">
							<span class="text-muted">Imágenes</span> <span
								class="badge badge-secondary badge-pill">3</span>

						</h4>

						<div class="flex relative py-7">
							<div class="">
								<img src="../subidas/${camiseta.id}.jpg" style="height: 200px"
									class="image-checkout h-full w-full object-contain object-center">


								<img src="../subidas/${camiseta.id}B.jpg" style="height: 200px"
									class="image-checkout h-full w-full object-contain object-center">

								<img src="../subidas/${camiseta.id}X.jpg" style="height: 200px"
									class="image-checkout h-full w-full object-contain object-center">
							</div>
							<div class="product ml-3 sm:ml-6 flex flex-1 flex-col">
								<div class="">
									<div class="flex justify-between">
										<div class="info">

											<div class="flex">

												<span class="mx-4 border-l"></span>
												<div>
													<h2>${camiseta.modelo}</h2>
												</div>

												<i class="bi bi-aspect-ratio-fill"></i><span
													style="padding: 0 5px; font-weight: 500;">
													${camiseta.talla} </span> <i class="bi bi-palette-fill"></i><span
													style="padding: 0 5px; font-weight: 500;">
													${camiseta.color} </span> <span class="price"><b
													style="font-size: 2rem">${camiseta.precio}</b><i
													class="bi bi-currency-euro"></i></span>
											</div>

										</div>

									</div>
								</div>
								<div class="flex items-end justify-between pt-4 mt-auto">

								</div>
							</div>
						</div>



					</div>
					<div class="col-md-6 order-md-1">
						<h4 class="mb-3">Datos Generales</h4>
						<form class="needs-validation" novalidate>
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="firstName">Modelo</label>
									<springform:input type="text" class="form-control rounded-2xl"
										id="firstName" placeholder="introduzca el nombre" value=""
										path="modelo" />
									<span style="color: red"> <springform:errors
											path="modelo" /></span>
								</div>
								<div class="col-md-6 mb-3">
									<label for="lastName">Temporada</label>
									<springform:input type="text" class="form-control rounded-2xl"
										id="firstName" placeholder="introduzca la Temporada" value=""
										path="temporada" />
									<span style="color: red"> <springform:errors
											path="temporada" /></span>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="email">Talla</label>
									<springform:input type="text" class="form-control rounded-2xl"
										id="firstName" placeholder="introduzca la Talla" value=""
										path="talla" />

								</div>
								<div class="col-md-6 mb-3">
									<label for="phonenumber">Precio Eu.</label>
									<springform:input type="text" class="form-control rounded-2xl"
										id="firstName" placeholder="introduzca el Precio" value=""
										path="precio" />
									<span style="color: red"> <springform:errors
											path="precio" />
									</span>
								</div>
							</div>
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="country">Color</label>
									<springform:input type="text" class="form-control rounded-2xl"
										id="firstName" placeholder="introduzca el Color" value=""
										path="color" />
								</div>
								<div class="col-md-6 mb-3">
									<label for="country">Categoría</label>
									<springform:select
										class="form-select d-block w-100 rounded-2xl" id="country"
										path="idCategoria">
										<springform:options items="${categorias}" />
									</springform:select>

								</div>
							</div>
							<div class="row">
								<div class="col-md-6 mb-3">
									<label for="country">Imágen Principal</label>
									<springform:input path="fotoDelante" type="file" />
								</div>
								<div class="col-md-6 mb-3">
									<label for="streets">Imágen Secundaria</label>
									<springform:input path="fotoAtras" type="file" />
								</div>
									<div class="col-md-6 mb-3">
									<label for="streets">Imágen Opcional</label>
									<springform:input path="fotoOpcional" type="file" />
								</div>
							</div>
							<div class="row">
								<div class="col-md-12 mb-3">
									<label for="streets">Descripción</label>
									<div class="input-group mt-1.5">
										<springform:input type="text"
											class="form-control rounded-2xl px-4 mr-075"
											path="descripcion" />
										<span style="color: red"> <springform:errors
												path="descripcion" />
										</span>
									</div>
									<label for="streets">Alta</label>

									<div class="input-group mt-1.5">
										<springform:checkbox path="alta" value="${camiseta.alta}" />
									</div>
								</div>
							</div>
							<div class="d-flex justify-content-center gap-12 d-md-block">
								<button class="btn btn-success" type="submit"
									style="color: white;" value="GUARDAR CAMBIOS">Confirmar
									Cambios</button>
								<a class="btn btn-danger" type="submit"
									href="./gestionarCamisetas" style="color: white;">Salir</a>
							</div>

							<hr class="mb-4">

							<springform:hidden path="id" />
						</form>
					</div>
				</div>
			</div>
		</springform:form>
	</main>
	<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://kit.fontawesome.com/794e2f8ba9.js"
		crossorigin="anonymous"></script>
</body>
</html>


