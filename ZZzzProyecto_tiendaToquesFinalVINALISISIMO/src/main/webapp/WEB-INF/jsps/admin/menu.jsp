<nav class="navbar bg-light fixed-top"
	style="background-color: #e3f2fd;">
	<div class="container-fluid">
		<a class="navbar-brand" href="#">Area de administración</a>
		<button class="navbar-toggler" type="button"
			data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
			aria-controls="offcanvasNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="offcanvas offcanvas-end" tabindex="-1"
			id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
			<div class="offcanvas-header">
				<h5 class="offcanvas-title" id="offcanvasNavbarLabel">Administración</h5>
				<a type="button" class="btn-close" data-bs-dismiss="offcanvas" href="../admin/"
					aria-label="Close"></a>
			</div>
			<div class="offcanvas-body">
				<ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="../admin/">Home</a></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> Usuarios </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="gestionarUsuarios">Gestionar
									Usuarios</a></li>

							<li>
								<hr class="dropdown-divider">
							</li>
							<li><a class="dropdown-item" href="nuevoUsuario">Nuevo
									Usuario</a></li>
						</ul></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> Camisetas </a>
						<ul class="dropdown-menu">
							<li><a class="dropdown-item" href="gestionarCamisetas">Gestionar
									Camisetas</a></li>

							<li>
								<hr class="dropdown-divider">
							</li>
							<li><a class="dropdown-item" href="nuevaCamiseta">Nuevo
									Camiseta</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-link" href="gestionarPedidos">Pedidos</a>
					</li>
				</ul>


				<form class="d-flex mt-3" role="search">
					<input class="form-control me-2" type="search" placeholder="Search"
						aria-label="Search">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
			</div>
		</div>
	</div>
</nav>

