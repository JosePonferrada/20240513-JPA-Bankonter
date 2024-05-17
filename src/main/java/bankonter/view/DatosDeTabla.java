package bankonter.view;

import java.util.List;

import bankonter.controladores.ControladorTipoContratoJPA;
import bankonter.entities.Tipocontrato;

public class DatosDeTabla {
	
	/** 
	 * 
	 * @return
	 */
	public static String[] getTitulosColumnas() {
		return new String[] {"Id", "Descripción"};
	}

	/**
	 * 
	 * @return
	 */
	public static Object[][] getDatosDeTabla(List<Tipocontrato> listTipoContrato) {
		// Obtengo todos los tipoContrato.
		List<Tipocontrato> tiposContrato = listTipoContrato;
		
		
		// Preparo una estructura para pasar al constructor de la JTable
		Object[][] datos = new Object[tiposContrato.size()][2];
		// Cargo los datos de la lista de estudiantes en la matriz de los datos
		for (int i = 0; i < tiposContrato.size(); i++) {
			Tipocontrato tc = tiposContrato.get(i);
			datos[i][0] = tc.getId();
			datos[i][1] = tc.getDescripcion();
			
		}
		
		return datos;
	}
	
	
}
