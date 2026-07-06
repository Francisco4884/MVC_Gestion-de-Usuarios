<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,Modelo.Entidades.Usuario"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Usuarios</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/estilos.css">
</head>
<body>
	<div class="contenedor">
		<!-- Vista listarUsuarios: mostrarUsuarios(usuarios) -->
		<h1>Lista de Usuarios</h1>

		<!-- accion: nuevo -->
		<div class="acciones-top">
			<form action="<%= request.getContextPath() %>/GestionUsuarios" method="get">
				<input type="hidden" name="accion" value="crearNuevoUsuario">
				<button type="submit">Nuevo</button>
			</form>
		</div>

		<table>
			<tr>
				<th>Nombre</th>
				<th>Correo</th>
				<th>Perfil</th>
				<th>Acciones</th>
			</tr>
			<%
				List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");

				// Paginacion: se muestran 6 usuarios por hoja.
				int usuariosPorPagina = 5;
				int total = (usuarios != null) ? usuarios.size() : 0;
				int totalPaginas = (int) Math.ceil((double) total / usuariosPorPagina);
				if (totalPaginas == 0) {
					totalPaginas = 1;
				}

				// Hoja actual (parametro "pagina"), acotada al rango valido.
				int pagina = 1;
				String paramPagina = request.getParameter("pagina");
				if (paramPagina != null) {
					try {
						pagina = Integer.parseInt(paramPagina);
					} catch (NumberFormatException e) {
						pagina = 1;
					}
				}
				if (pagina < 1) {
					pagina = 1;
				}
				if (pagina > totalPaginas) {
					pagina = totalPaginas;
				}

				// Rango de usuarios que corresponden a la hoja actual.
				int inicio = (pagina - 1) * usuariosPorPagina;
				int fin = Math.min(inicio + usuariosPorPagina, total);

				if (usuarios != null) {
					for (int i = inicio; i < fin; i++) {
						Usuario u = usuarios.get(i);
			%>
			<tr>
				<td><%= u.getNombre() %></td>
				<td><%= u.getCorreo() %></td>
				<td><%= u.getPerfil() %></td>
				<td>
					<!-- accion: editar -->
					<form action="<%= request.getContextPath() %>/GestionUsuarios" method="get" style="display:inline">
						<input type="hidden" name="accion" value="actualizarUsuario">
						<input type="hidden" name="idUsuario" value="<%= u.getId() %>">
						<button type="submit">Editar</button>
					</form>
					<!-- accion: eliminar -->
					<form action="<%= request.getContextPath() %>/GestionUsuarios" method="get" style="display:inline">
						<input type="hidden" name="accion" value="eliminarUsuario">
						<input type="hidden" name="idUsuario" value="<%= u.getId() %>">
						<button type="submit" class="btn-secondary">Eliminar</button>
					</form>
				</td>
			</tr>
			<%
					}
				}
			%>
		</table>

		<% if (totalPaginas > 1) { %>
		<div class="paginacion">
			<% if (pagina > 1) { %>
				<a class="btn btn-secondary" href="<%= request.getContextPath() %>/GestionUsuarios?accion=ingresar&amp;pagina=<%= pagina - 1 %>">Anterior</a>
			<% } %>

			<% for (int p = 1; p <= totalPaginas; p++) { %>
				<% if (p == pagina) { %>
					<span class="pagina-actual"><%= p %></span>
				<% } else { %>
					<a class="btn btn-secondary" href="<%= request.getContextPath() %>/GestionUsuarios?accion=ingresar&amp;pagina=<%= p %>"><%= p %></a>
				<% } %>
			<% } %>

			<% if (pagina < totalPaginas) { %>
				<a class="btn btn-secondary" href="<%= request.getContextPath() %>/GestionUsuarios?accion=ingresar&amp;pagina=<%= pagina + 1 %>">Siguiente</a>
			<% } %>
		</div>
		<% } %>
	</div>
</body>
</html>
