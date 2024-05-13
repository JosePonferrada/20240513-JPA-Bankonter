package bankonter.controladores;

import java.util.List;

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
	
	
}
