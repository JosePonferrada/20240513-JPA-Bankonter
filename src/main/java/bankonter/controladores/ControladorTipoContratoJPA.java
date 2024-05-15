package bankonter.controladores;

import javax.persistence.EntityManager;

import bankonter.entities.Tipocontrato;

public class ControladorTipoContratoJPA extends SuperControladorJPA {

	
	private static ControladorTipoContratoJPA instance = null;
	
	
	public ControladorTipoContratoJPA() {
		super("tipocontrato", Tipocontrato.class);
	}
	
	
	/**
	 * Singleton
	 * @return
	 */
	public static ControladorTipoContratoJPA getInstance() {
		if (instance == null) {
			instance = new ControladorTipoContratoJPA();
		}
		return instance;
	}
	
	
	public void deleteContrato(int id) {
		EntityManager em = getEntityManager();
		
		Tipocontrato tc = (Tipocontrato) findById(id);
		
		em.getTransaction().begin();
		// Volvemos a enlazar nuestra entidad con nuestro manager.
		tc = em.merge(tc);
		em.remove(tc);
		em.getTransaction().commit();
	}
	
}
