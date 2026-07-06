package Controlador;

import java.io.IOException;
import java.util.List;

import Modelo.DAO.UsuarioDAO;
import Modelo.Entidades.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/GestionUsuarios")
public class GestionUsuariosControlador extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final UsuarioDAO usuarioDAO = new UsuarioDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ruteador(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ruteador(request, response);
	}

	private void ruteador(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if (accion == null) {
			accion = "ingresar";
		}

		switch (accion) {
			case "ingresar":
				ingresar(request, response);
				break;
			case "crearNuevoUsuario":
				crearNuevoUsuario(request, response);
				break;
			case "guardarUsuarioNuevo":
				guardarUsuarioNuevo(request, response);
				break;
			case "actualizarUsuario":
				actualizarUsuario(request, response);
				break;
			case "guardarCambiosUsuario":
				guardarCambiosUsuario(request, response);
				break;
			case "eliminarUsuario":
				eliminarUsuario(request, response);
				break;
			case "confirmarEliminacion":
				confirmarEliminacion(request, response);
				break;
			case "cancelarEliminacion":
				cancelarEliminacion(request, response);
				break;
			default:
				ingresar(request, response);
				break;
		}
	}

	// 1. ingresar(): void
	// Flujo inicial: obtiene los datos de los usuarios y muestra la lista.
	public void ingresar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Usuario> usuarios = usuarioDAO.obtenerDatos();
		request.setAttribute("usuarios", usuarios);
		request.getRequestDispatcher("/Vista/ListadeUsuarios.jsp").forward(request, response);
	}

	// 3.A.1 crearNuevoUsuario()
	// Pide a la vista Nuevo Usuario mostrar el formulario vacio.
	public void crearNuevoUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/Vista/NuevoUsuario.jsp").forward(request, response);
	}

	// 3.A.3 guardarUsuarioNuevo(nombre, correo, perfil)
	// Valida el correo. Si es valido registra y muestra la lista; si ya existe
	// permanece en Nuevo Usuario mostrando el mensaje de error.
	public void guardarUsuarioNuevo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String correo = request.getParameter("correo");
		String perfil = request.getParameter("perfil");

		boolean resultado = usuarioDAO.validarDatos(correo);

		if (resultado) {
			usuarioDAO.registrarNuevoUsuario(nombre, correo, perfil);
			List<Usuario> usuarios = usuarioDAO.obtenerDatos();
			request.setAttribute("usuarios", usuarios);
			request.getRequestDispatcher("/Vista/ListadeUsuarios.jsp").forward(request, response);
		} else {

			request.setAttribute("mensaje", "Ya existe un usuario con ese correo");
			request.getRequestDispatcher("/Vista/NuevoUsuario.jsp").forward(request, response);
		}
	}

	// 3.B.1 actualizarUsuario(idUsuario)
	// Obtiene los datos del usuario seleccionado y los muestra para actualizar.
	public void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		Usuario u = usuarioDAO.obtenerDatosUsuario(idUsuario);
		request.setAttribute("idUsuario", idUsuario);
		request.setAttribute("usuario", u);
		request.getRequestDispatcher("/Vista/ActualizarUsuario.jsp").forward(request, response);
	}

	// 3.B.4 guardarCambiosUsuario(nombre, correo, perfil)
	// Actualiza el usuario (nombre y perfil) y muestra la lista actualizada.
	public void guardarCambiosUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		String nombre = request.getParameter("nombre");
		String perfil = request.getParameter("perfil");

		usuarioDAO.actualizarUsuario(idUsuario, nombre, perfil);

		List<Usuario> usuarios = usuarioDAO.obtenerDatos();
		request.setAttribute("usuarios", usuarios);
		request.getRequestDispatcher("/Vista/ListadeUsuarios.jsp").forward(request, response);
	}

	// 3.C.1 eliminarUsuario(idUsuario)
	// Pide a la vista Eliminar Usuario mostrar el mensaje de confirmacion.
	public void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		request.setAttribute("idUsuario", idUsuario);
		request.setAttribute("mensaje", "¿Estás seguro de que deseas eliminar este usuario?");
		request.getRequestDispatcher("/Vista/EliminarUsuario.jsp").forward(request, response);
	}

	// 3.C.3 confirmarEliminacion()
	// Elimina el usuario seleccionado y muestra la lista actualizada.
	public void confirmarEliminacion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		usuarioDAO.eliminarUsuario(idUsuario);

		List<Usuario> usuarios = usuarioDAO.obtenerDatos();
		request.setAttribute("usuarios", usuarios);
		request.getRequestDispatcher("/Vista/ListadeUsuarios.jsp").forward(request, response);
	}

	// 3.C.3.A cancelarEliminacion()
	// No elimina nada y vuelve a mostrar la lista de usuarios.
	public void cancelarEliminacion(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Usuario> usuarios = usuarioDAO.obtenerDatos();
		request.setAttribute("usuarios", usuarios);
		request.getRequestDispatcher("/Vista/ListadeUsuarios.jsp").forward(request, response);
	}

}
