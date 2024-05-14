package bankonter.controladores;

import javax.persistence.EntityManager;

import bankonter.entities.Contrato;

public class ControladorContratoJPA extends SuperControladorJPA {

	
	private static ControladorContratoJPA instance = null;
	
	
	public ControladorContratoJPA() {
		super("contrato", Contrato.class);
	}
	
	
	/**
	 * Singleton
	 * @return
	 */
	public static ControladorContratoJPA getInstance() {
		if (instance == null) {
			instance = new ControladorContratoJPA();
		}
		return instance;
	}
	
	
	public void deleteContrato(int id) {
		EntityManager em = getEntityManager();
		
		Contrato c = (Contrato) findById(id);
		
		em.getTransaction().begin();
		// Volvemos a enlazar nuestra entidad con nuestro manager.
		c = em.merge(c);
		em.remove(c);
		em.getTransaction().commit();
	}
	
}
