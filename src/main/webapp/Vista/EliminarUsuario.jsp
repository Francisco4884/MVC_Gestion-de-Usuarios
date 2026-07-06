<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Eliminar Usuario</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
</head>
<body>
	<div class="contenedor">
		<!-- Vista eliminarUsuario: mostrarMensaje("¿Estás seguro...?") -->
		<h1>Eliminar Usuario</h1>

		<%
			String mensaje = (String) request.getAttribute("mensaje");
			Integer idUsuario = (Integer) request.getAttribute("idUsuario");
		%>
		<p class="mensaje"><%= mensaje %></p>

		<!-- confirmar (save=ok) -->
		<form action="<%= request.getContextPath() %>/GestionUsuarios" method="post" style="display:inline">
			<input type="hidden" name="accion" value="confirmarEliminacion">
			<input type="hidden" name="idUsuario" value="<%= idUsuario %>">
			<button type="submit">Confirmar</button>
		</form>
		<!-- cancelar (save=cancel): regresa al listado -->
		<form action="<%= request.getContextPath() %>/GestionUsuarios" method="post" style="display:inline">
			<input type="hidden" name="accion" value="cancelarEliminacion">
			<button type="submit" class="btn-secondary">Cancelar</button>
		</form>
	</div>
</body>
</html>
