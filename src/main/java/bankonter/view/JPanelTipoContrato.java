package bankonter.view;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import bankonter.entities.Tipocontrato;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JPanelTipoContrato extends JPanel {
	
	private JPanelContrato panelContrato;
	private JDialog jd;
	
	JScrollPane scrollPane;

	private static final long serialVersionUID = 1L;
	private JTextField jtfFiltro;

	/**
	 * Create the panel.
	 */
	public JPanelTipoContrato(JPanelContrato panelContrato, JDialog jd) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTiposDeContrato = new JLabel("Tipos de Contrato");
		lblTiposDeContrato.setFont(new Font("Cascadia Code", Font.BOLD, 16));
		GridBagConstraints gbc_lblTiposDeContrato = new GridBagConstraints();
		gbc_lblTiposDeContrato.gridwidth = 3;
		gbc_lblTiposDeContrato.insets = new Insets(0, 0, 5, 0);
		gbc_lblTiposDeContrato.gridx = 0;
		gbc_lblTiposDeContrato.gridy = 0;
		add(lblTiposDeContrato, gbc_lblTiposDeContrato);
		
		JLabel lblFiltro = new JLabel("Filtro:");
		GridBagConstraints gbc_lblFiltro = new GridBagConstraints();
		gbc_lblFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiltro.anchor = GridBagConstraints.EAST;
		gbc_lblFiltro.gridx = 0;
		gbc_lblFiltro.gridy = 1;
		add(lblFiltro, gbc_lblFiltro);
		
		jtfFiltro = new JTextField();
		GridBagConstraints gbc_jtfFiltro = new GridBagConstraints();
		gbc_jtfFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFiltro.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfFiltro.gridx = 1;
		gbc_jtfFiltro.gridy = 1;
		add(jtfFiltro, gbc_jtfFiltro);
		jtfFiltro.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 0);
		gbc_btnBuscar.gridx = 2;
		gbc_btnBuscar.gridy = 1;
		add(btnBuscar, gbc_btnBuscar);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);

	}
	
	private void findTiposContratos() {
		List<Tipocontrato> tiposContrato = 
				FileUtils.getFilesIntoFolder(this.jtfFolder.getText());
				
		for (Tipocontrato f : tiposContrato) {
			if (f.getDescripcion().toUpperCase().contains(this.jtfFiltro.getText().toUpperCase())) {
				tiposContrato.add(f);
			}
		}
		
		Object tableData[][] = new Object[tiposContrato.size()][2];
		
		for (int i = 0; i < tiposContrato.size(); i++) {
			Tipocontrato f = tiposContrato.get(i);
			tableData[i][0] = f.getId();
			tableData[i][1] = (f.getDescripcion());
			
		}
		
		JTable table = new JTable(tableData, new String[] {"Id", "Descripción"});

		this.scrollPane.setViewportView(table);
	}

}
