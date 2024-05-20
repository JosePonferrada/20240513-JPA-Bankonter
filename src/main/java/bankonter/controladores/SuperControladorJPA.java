package bankonter.controladores;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import bankonter.entities.Entidad;

public class SuperControladorJPA {

	private static EntityManager em = null;
	private String nombreTabla = "";
	private Class tipoEntidad;
	
	/**
	 * Constructor.
	 * @param nombreTabla El nombre de la tabla.
	 * @param tipoEntidad La entidad deseada.
	 */
	public SuperControladorJPA(String nombreTabla, Class tipoEntidad) {
		this.nombreTabla = nombreTabla;
		this.tipoEntidad = tipoEntidad;
	}
	
	/**
	 * Método que obtiene un EntityManager para la unidad
	 * de Persistencia CentroEducativo.
	 * @return
	 */
	protected EntityManager getEntityManager() {
		if (em == null) {
			return Persistence.createEntityManagerFactory("20240513-JPA-Bankonter")
					.createEntityManager();
		}
		return em;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Entidad findById(int id) {
		try {
			return (Entidad) getEntityManager()
				.find(this.tipoEntidad, id);
		} catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<? extends Entidad> findAll() {
		return (List<? extends Entidad>)
		getEntityManager()
		.createNativeQuery("select * from " + this.nombreTabla, this.tipoEntidad)
		.getResultList();
	}
	
	/**
	 * Método que devuelve el primer registro de la tabla seleccionada.
	 * @return Primer Registro.
	 */
	public Entidad findFirst() {
		
		try {
			return (Entidad) getEntityManager()
			.createNativeQuery(
				"select * from " + this.nombreTabla + " order by id", this.tipoEntidad)
			.setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	/**
	 * Método que devuelve el último registro de la tabla seleccionada.
	 * @return Último Registro.
	 */
	public Entidad findLast() {
		
		try {
			return (Entidad) getEntityManager()
			.createNativeQuery(
				"select * from " + this.nombreTabla + " order by id desc", this.tipoEntidad)
			.setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	/**
	 * 
	 * @param idActual
	 * @return
	 */
	public Entidad findNext(int idActual) {
		
		try {
			return (Entidad) getEntityManager()
			.createNativeQuery(
				"select * from " + this.nombreTabla + " where id > " + idActual 
				+ " order by id", this.tipoEntidad)
			.setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
		
	}
	
	/**
	 * 
	 * @param idActual
	 * @return
	 */
	public Entidad findPrevious(int idActual) {
		
		try {
			return (Entidad) getEntityManager()
			.createNativeQuery(
				"select * from " + this.nombreTabla + " where id < " + idActual 
				+ " order by id desc", this.tipoEntidad)
			.setMaxResults(1).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	public void update (Entidad e) {
		EntityManager em = getEntityManager();
		
		em.getTransaction().begin();
		em.merge(e);
		em.getTransaction().commit();
	}
	
	public void insertEntidad(Entidad e) {
		EntityManager em = getEntityManager();
		
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
	}
	
	
	
}
