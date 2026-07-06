<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// Punto de entrada: redirige al GestionUsuariosControlador,
	// que ejecuta el ruteador (accion por defecto: ingresar).
	response.sendRedirect(request.getContextPath() + "/GestionUsuarios");
%>
