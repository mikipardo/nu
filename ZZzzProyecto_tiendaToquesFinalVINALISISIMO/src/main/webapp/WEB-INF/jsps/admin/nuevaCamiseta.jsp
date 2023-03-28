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
<title>Bootstrap Example</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Nueva Camiseta</title>
</head>
<body>


	<jsp:include page="menu.jsp"></jsp:include>



	<main style="margin-top: 10rem;">
		<div class="container">

			<div class="row">

				<div class="col-lg-8 col-lg-offset-2">

					<h1>
						Crear Camiseta <a href="#"></a>
					</h1>


					<springform:form modelAttribute="camiseta" id="formNuevaCamiseta"
						action="guardarNuevaCamiseta" enctype="multipart/form-data">


						<div class="messages"></div>



						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="form_name">Modelo </label>
									<springform:input path="modelo" class="form-control" />
									<span style="color: red"> <springform:errors
											path="modelo" />
									</span>
									<div class="help-block with-errors"></div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="form_email">Precio </label>
									<springform:input path="precio" class="form-control" />

									<span style="color: red"> <springform:errors
											path="precio" />
									</span>
									<div class="help-block with-errors"></div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="form_lastname">Temporada *</label>
									<springform:input path="temporada" class="form-control" />
									<span style="color: red"> <springform:errors
											path="temporada"/>
									</span>
									<div class="help-block with-errors"></div>
								</div>
							</div>
								<div class="col-md-6">
								<div class="form-group">
									<label for="form_lastname">Color *</label>
									<springform:input path="color" class="form-control" />
									<span style="color: red"> <springform:errors
											path="color"/>
									</span>
									<div class="help-block with-errors"></div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="form_email">Talla </label>
									<springform:input path="talla" class="form-control" />
									<div class="help-block with-errors"></div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="form_phone">Categoria</label>
									<springform:select path="idCategoria" class="form-control">
										<springform:options items="${categorias}" />
									</springform:select>
									<div class="help-block with-errors"></div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label for="form_message">Descripcion </label>
									<springform:input path="descripcion" class="form-control" />
									<span style="color: red"> <springform:errors
											path="descripcion" />
									</span>
									<div class="help-block with-errors"></div>
								</div>
								<div style="margin-top: 1rem;">

									<springform:input id="img1" path="fotoDelante" type="file" />
									<label for="form_message">Foto Principal *</label>
								</div>
								<div style="margin-top: 1rem;">
									<springform:input id="img2" path="fotoAtras" type="file" />
									<label for="form_message">Foto Secundaria *</label>
								</div>
								<div style="margin-top: 1rem;">
									<springform:input id="img2" path="fotoOpcional" type="file" />
									<label for="form_message">Foto Opcional *</label>
								</div>

								<br>
								<div class="col-md-12">
									<input id="btnNuevaCamiseta" class="btn btn-success btn-send"
										type="submit" value="Crear Camiseta" />

								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<p class="text-muted">
										<strong>*</strong> Regresar a <a href="./gestionarCamisetas">gestión
											de camiseta</a>.
									</p>
								</div>
							</div>
						</div>


					</springform:form>
				</div>

			</div>

		</div>
	</main>
	<script type="text/javascript" src="../librerias_js/jquery.js"></script>
	<script type="text/javascript"
		src="../javascript/cargar_eventosAdmin.js"></script>
</body>
</html>


