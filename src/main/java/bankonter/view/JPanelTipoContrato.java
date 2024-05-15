package bankonter.view;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import bankonter.controladores.ControladorTipoContratoJPA;
import bankonter.entities.Tipocontrato;
import bankonter.utils.CacheImagenes;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class JPanelTipoContrato extends JPanel {
	
	private JPanelContrato panelContrato;
	private JDialog jd;

	private static final long serialVersionUID = 1L;
	
	private Tipocontrato selectedTc;
	
	private List<Tipocontrato> tiposContrato = 
			(List<Tipocontrato>) ControladorTipoContratoJPA.getInstance().findAll();
	
	private DefaultTableModel dtm = null;
	private Object datosEnTabla[][] = DatosDeTabla.getDatosDeTabla(tiposContrato);
	private String titulosEnTabla[] = DatosDeTabla.getTitulosColumnas();
	private JTextField jtfFiltro;
	private JTable table;
	

	/**
	 * Create the panel.
	 */
	public JPanelTipoContrato(JPanelContrato panelContrato, JDialog jd) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 82, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{19, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTiposDeContrato = new JLabel("Tipos de Contrato");
		lblTiposDeContrato.setVerticalAlignment(SwingConstants.TOP);
		lblTiposDeContrato.setFont(new Font("Cascadia Code", Font.BOLD, 16));
		GridBagConstraints gbc_lblTiposDeContrato = new GridBagConstraints();
		gbc_lblTiposDeContrato.gridwidth = 3;
		gbc_lblTiposDeContrato.insets = new Insets(0, 0, 5, 5);
		gbc_lblTiposDeContrato.gridx = 1;
		gbc_lblTiposDeContrato.gridy = 0;
		add(lblTiposDeContrato, gbc_lblTiposDeContrato);
		
		JLabel lblFiltro = new JLabel("Filtro:");
		GridBagConstraints gbc_lblFiltro = new GridBagConstraints();
		gbc_lblFiltro.anchor = GridBagConstraints.EAST;
		gbc_lblFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiltro.gridx = 1;
		gbc_lblFiltro.gridy = 1;
		add(lblFiltro, gbc_lblFiltro);
		
		jtfFiltro = new JTextField();
		GridBagConstraints gbc_jtfFiltro = new GridBagConstraints();
		gbc_jtfFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFiltro.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFiltro.gridx = 2;
		gbc_jtfFiltro.gridy = 1;
		add(jtfFiltro, gbc_jtfFiltro);
		jtfFiltro.setColumns(10);
		
		JButton btnFiltrar = new JButton("Filtrar");
		GridBagConstraints gbc_btnFiltrar = new GridBagConstraints();
		gbc_btnFiltrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnFiltrar.gridx = 3;
		gbc_btnFiltrar.gridy = 1;
		add(btnFiltrar, gbc_btnFiltrar);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		
		scrollPane.setViewportView(table);
		
		// Inicializo el DefaultTableModel
				this.dtm = getDefaultTableModel();
				// Creo la tabla con el DefaultTableModel del método más abajo
				this.table = new JTable(dtm);
				
				// Limitamos el modo selección de filas a una única selección.
				this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
				// Agregamos la tabla al ScrollPane.
				scrollPane.setViewportView(table);
				
				// Demostración de como acceder al clic del ratón sobre la tabla y sobrescribir un valor en la misma
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2) {
							
							jd.dispose();
						}
					}
				});
				
	}
	
	private DefaultTableModel getDefaultTableModel () {
		DefaultTableModel dtm = new DefaultTableModel(datosEnTabla, titulosEnTabla) {
			
			/**
			 * La sobreescritura de este método nos permite controlar qué celdas queremos que sean editables
			 */
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		return dtm;
	}
	
	/*
	 * Obtenemos el objeto y con él lo pasaremos al jtf y nos servirá para guardar el objeto
	 */
	private Tipocontrato getTipoContratoFromTable() {
		int indexRow = this.table.getSelectedRow();
		if (indexRow != -1) {
			Integer idTc = (Integer) dtm.getValueAt(indexRow, 0);
			return (Tipocontrato) ControladorTipoContratoJPA
					.getInstance().findById(idTc);
		}
		return null;
	}

	private boolean isFilterValid(Tipocontrato tc) {
		String str = this.jtfFiltro.getText();
		
		// Si el campo está vacío, se cargará 
		// en la tabla.
		if (str.trim().isEmpty()) {
			return true;
		}
		
		// Si el nombre del fichero contiene la cadena de texto
		// del filtro, se cargará en la tabla.
		if (tc.getDescripcion().toUpperCase()
				.contains(str.trim().toUpperCase())) {
			return true;
		} else {
			return false;
		}
	}
	
	private void filterTipoContrato() {
		
		List<Tipocontrato> filteredTiposContrato = new ArrayList<Tipocontrato>();
		
		for (Tipocontrato tc : tiposContrato) {
			if (isFilterValid(tc)) {
				filteredTiposContrato.add(tc);
			}
		}
		
		// Actualizamos los datos de la tabla. De esta manera, mantenemos
		// el mouseListener de la tabla.
		this.dtm.setDataVector(DatosDeTabla.getDatosDeTabla(filteredTiposContrato),
				DatosDeTabla.getTitulosColumnas());
		// Se notifican los posibles cambios de las celdas de la tabla.
		this.dtm.fireTableDataChanged();
	}
	
}
