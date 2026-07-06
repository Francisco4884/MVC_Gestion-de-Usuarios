<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nuevo Usuario</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
</head>
<body>
	<div class="contenedor">
		<!-- Vista nuevoUsuario: mostrarFormulario() y mostraMensaje(...) -->
		<h1>Nuevo Usuario</h1>

		<%
			// mostraMensaje("Ya existe un usuario con ese correo") cuando save=fail
			String mensaje = (String) request.getAttribute("mensaje");
			if (mensaje != null) {
		%>
			<p class="mensaje"><%= mensaje %></p>
		<%
			}
		%>

		<!-- mostrarFormulario(): formulario con campos vacios -->
		<form action="<%= request.getContextPath() %>/GestionUsuarios" method="post">
			<input type="hidden" name="accion" value="guardarUsuarioNuevo">
			<label>Nombre: <input type="text" name="nombre"></label>
			<label>Correo: <input type="text" name="correo"></label>
			<label>Perfil:
				<select name="perfil">
					<option value="Empleado">Empleado</option>
					<option value="Administrador">Administrador</option>
				</select>
			</label>
			<button type="submit">Guardar</button>
			<a class="btn btn-secondary" href="<%= request.getContextPath() %>/GestionUsuarios?accion=ingresar">Volver al listado</a>
		</form>
	</div>
</body>
</html>
