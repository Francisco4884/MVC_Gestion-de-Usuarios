<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Modelo.Entidades.Usuario"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Actualizar Usuario</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
</head>
<body>
	<div class="contenedor">
		<!-- Vista actualizarUsuario: mostrarDatosUsuario(usuario) -->
		<h1>Actualizar Usuario</h1>

		<%
			Usuario u = (Usuario) request.getAttribute("usuario");
			Integer idUsuario = (Integer) request.getAttribute("idUsuario");
			String perfilActual = u.getPerfil();
		%>

		<form action="<%= request.getContextPath() %>/GestionUsuarios" method="post">
			<input type="hidden" name="accion" value="guardarCambiosUsuario">
			<input type="hidden" name="idUsuario" value="<%= idUsuario %>">
			<label>Nombre: <input type="text" name="nombre" value="<%= u.getNombre() %>"></label>
			<!-- El correo no se edita: campo de solo lectura -->
			<label>Correo: <input type="text" name="correo" value="<%= u.getCorreo() %>" readonly></label>
			<label>Perfil:
				<select name="perfil">
					<option value="Empleado" <%= "Empleado".equals(perfilActual) ? "selected" : "" %>>Empleado</option>
					<option value="Administrador" <%= "Administrador".equals(perfilActual) ? "selected" : "" %>>Administrador</option>
				</select>
			</label>
			<button type="submit">Guardar Cambios</button>
			<a class="btn btn-secondary" href="<%= request.getContextPath() %>/GestionUsuarios?accion=ingresar">Volver al listado</a>
		</form>
	</div>
</body>
</html>
