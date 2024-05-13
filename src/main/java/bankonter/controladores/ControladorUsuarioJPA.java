package bankonter.controladores;

import bankonter.entities.Usuario;

public class ControladorUsuarioJPA extends SuperControladorJPA {

	
	private static ControladorUsuarioJPA instance = null;
	
	
	public ControladorUsuarioJPA() {
		super("usuario", Usuario.class);
	}
	
	
	/**
	 * Singleton
	 * @return
	 */
	public static ControladorUsuarioJPA getInstance() {
		if (instance == null) {
			instance = new ControladorUsuarioJPA();
		}
		return instance;
	}
	
	
}
