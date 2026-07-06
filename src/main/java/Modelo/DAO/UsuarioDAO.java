package Modelo.DAO;

import java.util.List;

import Modelo.Entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

/**
 * DAO (Data Access Object) de Usuario. Encapsula el acceso a la base de datos
 * mediante ORM (JPA + EclipseLink), igual que el UsuarioDAO del proyecto ORM.
 *
 * El controlador solo conoce estos metodos; toda la logica de persistencia
 * (EntityManager, transacciones y JPQL) queda aqui.
 */
public class UsuarioDAO {

	private EntityManager em = null;

	public UsuarioDAO() {
		// El nombre debe coincidir con el persistence-unit de META-INF/persistence.xml
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPersona");
		this.em = emf.createEntityManager();
	}

	// obtenerDatos(): List<Usuario>
	// Devuelve todos los usuarios registrados (SELECT u FROM Usuario u).
	public List<Usuario> obtenerDatos() {
		String sentenciaJPQL = "SELECT u FROM Usuario u";
		TypedQuery<Usuario> consulta = em.createQuery(sentenciaJPQL, Usuario.class);
		return consulta.getResultList();
	}

	// validarDatos(correo): boolean
	// Devuelve true si el correo es valido (todavia no existe) -> save=ok.
	// Devuelve false si el correo ya existe -> save=fail.
	public boolean validarDatos(String correo) {
		String sentenciaJPQL = "SELECT u FROM Usuario u WHERE u.correo = :correo";
		TypedQuery<Usuario> consulta = em.createQuery(sentenciaJPQL, Usuario.class);
		consulta.setParameter("correo", correo);
		return consulta.getResultList().isEmpty();
	}

	// obtenerDatosUsuario(id): Usuario
	// Busca un usuario por su clave primaria (id).
	public Usuario obtenerDatosUsuario(int id) {
		return em.find(Usuario.class, id);
	}

	// registrarNuevoUsuario(nombre, correo, perfil)
	// Crea y persiste un nuevo usuario.
	public boolean registrarNuevoUsuario(String nombre, String correo, String perfil) {
		Usuario usuario = new Usuario(nombre, correo, perfil);
		em.getTransaction().begin();
		try {
			em.persist(usuario);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println(">>>> ERROR: Creacion de Usuario");
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return false;
		}
	}

	// actualizarUsuario(id, nombre, perfil)
	// Actualiza el nombre y el perfil del usuario con ese id.
	public boolean actualizarUsuario(int id, String nombre, String perfil) {
		Usuario usuario = this.obtenerDatosUsuario(id);
		em.getTransaction().begin();
		try {
			usuario.setNombre(nombre);
			usuario.setPerfil(perfil);
			em.merge(usuario);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println(">>>>> ERROR: Actualizar usuario");
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return false;
		}
	}

	// eliminarUsuario(id)
	// Elimina el usuario con ese id.
	public boolean eliminarUsuario(int id) {
		Usuario usuario = this.obtenerDatosUsuario(id);
		em.getTransaction().begin();
		try {
			em.remove(usuario);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println(">>>>> ERROR: Eliminar usuario");
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return false;
		}
	}
}
