package bankonter.view;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import bankonter.controladores.ControladorUsuarioJPA;
import bankonter.entities.Usuario;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JPanelUsuario extends JPanel {
	
	private JPanelContrato panelContrato;
	private Usuario selectedUser;

	private JDialog jd;
	// Modelo del elemento JList, necesario para que podamos c�modamente agregar y eliminar elementos
	private DefaultListModel<Usuario> listModelUsuarios;
	// Lista de todos los usuarios de la BBDD, para incluir en el elemento JList
	private List<Usuario> usuarios = 
			(List<Usuario>) ControladorUsuarioJPA.getInstance().findAll();
	
	private List<Usuario> usuariosFiltrados = new ArrayList<Usuario>();

	private static final long serialVersionUID = 1L;
	private JTextField jtfFiltro;
	private JList<Usuario> listaUsuarios;
	private JButton btnSeleccionar;
	private JCheckBox chckbxCasesensitive;
	private JRadioButton rdbtnEmail;
	private JRadioButton rdbtnNombreDeUsuario;
	private JButton btnFiltrar;
	private JScrollPane scrollPane;
	
	
	/**
	 * Create the panel.
	 */
	public JPanelUsuario(JPanelContrato panelContrato, JDialog jd) {

		this.panelContrato = panelContrato;
		
		this.jd = jd;
		
		this.usuarios = (List<Usuario>) ControladorUsuarioJPA.getInstance().findAll();
        this.listModelUsuarios = new DefaultListModel<>();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setFont(new Font("Cascadia Code", Font.BOLD, 16));
		GridBagConstraints gbc_lblUsuarios = new GridBagConstraints();
		gbc_lblUsuarios.gridwidth = 3;
		gbc_lblUsuarios.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuarios.gridx = 1;
		gbc_lblUsuarios.gridy = 0;
		add(lblUsuarios, gbc_lblUsuarios);
		
		JLabel lblFiltro = new JLabel("Filtro:");
		GridBagConstraints gbc_lblFiltro = new GridBagConstraints();
		gbc_lblFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_lblFiltro.anchor = GridBagConstraints.EAST;
		gbc_lblFiltro.gridx = 1;
		gbc_lblFiltro.gridy = 1;
		add(lblFiltro, gbc_lblFiltro);
		
		jtfFiltro = new JTextField();
		GridBagConstraints gbc_jtfFiltro = new GridBagConstraints();
		gbc_jtfFiltro.insets = new Insets(0, 0, 5, 5);
		gbc_jtfFiltro.fill = GridBagConstraints.BOTH;
		gbc_jtfFiltro.gridx = 2;
		gbc_jtfFiltro.gridy = 1;
		add(jtfFiltro, gbc_jtfFiltro);
		jtfFiltro.setColumns(10);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterList();
			}
		});
		GridBagConstraints gbc_btnFiltrar = new GridBagConstraints();
		gbc_btnFiltrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnFiltrar.gridx = 3;
		gbc_btnFiltrar.gridy = 1;
		add(btnFiltrar, gbc_btnFiltrar);
		
		JLabel lblCampoAFiltrar = new JLabel("Campo a filtrar:");
		GridBagConstraints gbc_lblCampoAFiltrar = new GridBagConstraints();
		gbc_lblCampoAFiltrar.insets = new Insets(0, 0, 5, 5);
		gbc_lblCampoAFiltrar.gridx = 1;
		gbc_lblCampoAFiltrar.gridy = 2;
		add(lblCampoAFiltrar, gbc_lblCampoAFiltrar);
		
		rdbtnNombreDeUsuario = new JRadioButton("Nombre de usuario");
		GridBagConstraints gbc_rdbtnNombreDeUsuario = new GridBagConstraints();
		gbc_rdbtnNombreDeUsuario.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNombreDeUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnNombreDeUsuario.gridx = 2;
		gbc_rdbtnNombreDeUsuario.gridy = 2;
		add(rdbtnNombreDeUsuario, gbc_rdbtnNombreDeUsuario);
		
		rdbtnEmail = new JRadioButton("Email");
		GridBagConstraints gbc_rdbtnEmail = new GridBagConstraints();
		gbc_rdbtnEmail.anchor = GridBagConstraints.WEST;
		gbc_rdbtnEmail.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnEmail.gridx = 2;
		gbc_rdbtnEmail.gridy = 3;
		add(rdbtnEmail, gbc_rdbtnEmail);
		
		chckbxCasesensitive = new JCheckBox("Case-Sensitive");
		GridBagConstraints gbc_chckbxCasesensitive = new GridBagConstraints();
		gbc_chckbxCasesensitive.anchor = GridBagConstraints.WEST;
		gbc_chckbxCasesensitive.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCasesensitive.gridx = 2;
		gbc_chckbxCasesensitive.gridy = 4;
		add(chckbxCasesensitive, gbc_chckbxCasesensitive);
		
		scrollPane = new JScrollPane(listaUsuarios);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 6;
		add(scrollPane, gbc_scrollPane);
		
//		listaUsuarios = new JList<Usuario>(listModelUsuarios);
		
		
		btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectUsuarioFromList();
			}
		});
		GridBagConstraints gbc_btnSeleccionar = new GridBagConstraints();
		gbc_btnSeleccionar.insets = new Insets(0, 0, 0, 5);
		gbc_btnSeleccionar.gridx = 2;
		gbc_btnSeleccionar.gridy = 8;
		add(btnSeleccionar, gbc_btnSeleccionar);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnNombreDeUsuario);
		bg.add(rdbtnEmail);
		
		// Seleccionamos uno por defecto
		rdbtnNombreDeUsuario.setSelected(true);
		
		this.listModelUsuarios = getDefaultListModel();
		this.listaUsuarios = new JList<Usuario>(listModelUsuarios);
		this.scrollPane.setViewportView(listaUsuarios);
//		loadAllUsuariosInJList();
//		this.listaUsuarios = new JList<Usuario>(listModelUsuarios);
		this.listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Valor por defecto
		
		
		loadAllUsuariosInJList(usuarios);
		
	}
	
	private void loadAllUsuariosInJList(List<Usuario> listUsuarios) {
		this.listModelUsuarios.clear();
		
		for (Usuario usuario : listUsuarios) {
			this.listModelUsuarios.addElement(usuario);
		}
		
	}
	
	
	private void selectUsuarioFromList() {
		if (this.listaUsuarios.getSelectedValue() != null) {
			this.selectedUser = this.listaUsuarios.getSelectedValue();
			this.panelContrato.getCurrent()
				.setUsuario(selectedUser);
			// Cerramos el JDialog.
			this.jd.dispose();
		} else {
			JOptionPane.showMessageDialog(null,
					"Seleccione un usuario");
			
			
		}
	}
	
	private void filterList() {
		String str = this.jtfFiltro.getText();
		
		if (str.isEmpty()) {
			loadAllUsuariosInJList(usuarios);
			return;
		}
		
		usuariosFiltrados.clear();
		
		for (Usuario usuario : usuarios) {
			if (chckbxCasesensitive.isSelected()) {
				if (this.rdbtnNombreDeUsuario.isSelected()) {
					
					if (usuario.getNombreUsuario().contains(str.trim())) {
						usuariosFiltrados.add(usuario);
					}
					
				} else {
					
					if (usuario.getEmail().contains(str.trim())) {
						usuariosFiltrados.add(usuario);
					}
					
				}
			} else {
				if (this.rdbtnNombreDeUsuario.isSelected()) {
					
					if (usuario.getNombreUsuario().toUpperCase()
							.contains(str.trim().toUpperCase())) {
						usuariosFiltrados.add(usuario);
					}
					
				} else {
					
					if (usuario.getEmail().toUpperCase()
							.contains(str.trim().toUpperCase())) {
						usuariosFiltrados.add(usuario);
					}
					
				}
			}
		}
		
		loadAllUsuariosInJList(usuariosFiltrados);
		
	}

	/**
	 * M�todo que construye el modelo de JList, a utilizar para agregar y eliminar provincias
	 */
	private DefaultListModel getDefaultListModel () {
		this.listModelUsuarios = new DefaultListModel<Usuario>();
		return this.listModelUsuarios;
	}
	
	public JPanelContrato getPanelContrato() {
		return panelContrato;
	}

	public void setPanelContrato(JPanelContrato panelContrato) {
		this.panelContrato = panelContrato;
	}
}
