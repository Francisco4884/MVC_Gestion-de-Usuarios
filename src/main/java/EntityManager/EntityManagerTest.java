package EntityManager;

import Modelo.Entidades.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerTest {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPersona");
		EntityManager em = emf.createEntityManager();

		insertar(em);
		// leer(em);
		// actualizar(em);
		// eliminar(em);
	}

	private static void insertar(EntityManager em) {
		Usuario u = new Usuario("Ana Torres", "ana@empresa.com", "admin");
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
	}

	private static void leer(EntityManager em) {
		Usuario u = em.find(Usuario.class, 1);
		System.out.println(u);
	}

	private static void actualizar(EntityManager em) {
		Usuario u = em.find(Usuario.class, 1);
		u.setPerfil("empleado");
		em.getTransaction().begin();
		em.merge(u);
		em.getTransaction().commit();
	}

	private static void eliminar(EntityManager em) {
		Usuario u = em.find(Usuario.class, 1);
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
	}

}
