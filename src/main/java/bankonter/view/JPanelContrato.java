package bankonter.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bankonter.entities.Contrato;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JSlider;

public class JPanelContrato extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfDescripcion;
	private JTextField jtfTipoContrato;
	private JTextField jtfUsuario;
	private JButton btnNew;
	private JButton btnEliminar;
	private JFormattedTextField jftfFecha;
	private JSlider sliderSaldo;
	private JSpinner spinnerLimite;
	private JButton btnSeleccionaTipoDe;
	private JButton btnSeleccionaUsuario;
	private JButton btnFirst;
	private JButton btnNext;
	private JButton btnPrevious;
	private JButton btnLast;
	private JButton btnGuardar;
	private JLabel lblEurosSaldo;
	
	// Nos servirá como puntero para tener el objeto representado en pantalla
	private Contrato current = null;
	

	/**
	 * Create the panel.
	 */
	public JPanelContrato() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 295, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.anchor = GridBagConstraints.WEST;
		gbc_toolBar.insets = new Insets(0, 0, 5, 5);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		add(toolBar, gbc_toolBar);
		
		btnNew = new JButton("New");
		btnNew.setIcon(new ImageIcon(JPanelContrato.class.getResource("/bankonter/res/nuevo.png")));
		toolBar.add(btnNew);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		GridBagConstraints gbc_toolBar_1 = new GridBagConstraints();
		gbc_toolBar_1.anchor = GridBagConstraints.EAST;
		gbc_toolBar_1.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar_1.gridx = 2;
		gbc_toolBar_1.gridy = 0;
		add(toolBar_1, gbc_toolBar_1);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(JPanelContrato.class.getResource("/bankonter/res/eliminar.png")));
		toolBar_1.add(btnEliminar);
		
		JLabel lblGestinDeContrato = new JLabel("Gestión de Contrato");
		lblGestinDeContrato.setFont(new Font("Cascadia Code", Font.BOLD, 16));
		GridBagConstraints gbc_lblGestinDeContrato = new GridBagConstraints();
		gbc_lblGestinDeContrato.gridwidth = 3;
		gbc_lblGestinDeContrato.insets = new Insets(0, 0, 5, 0);
		gbc_lblGestinDeContrato.gridx = 0;
		gbc_lblGestinDeContrato.gridy = 1;
		add(lblGestinDeContrato, gbc_lblGestinDeContrato);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		GridBagConstraints gbc_lblDescripcin = new GridBagConstraints();
		gbc_lblDescripcin.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcin.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcin.gridx = 0;
		gbc_lblDescripcin.gridy = 2;
		add(lblDescripcin, gbc_lblDescripcin);
		
		jtfDescripcion = new JTextField();
		GridBagConstraints gbc_jtfDescripcion = new GridBagConstraints();
		gbc_jtfDescripcion.gridwidth = 2;
		gbc_jtfDescripcion.insets = new Insets(0, 0, 5, 0);
		gbc_jtfDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfDescripcion.gridx = 1;
		gbc_jtfDescripcion.gridy = 2;
		add(jtfDescripcion, gbc_jtfDescripcion);
		jtfDescripcion.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		GridBagConstraints gbc_lblFecha = new GridBagConstraints();
		gbc_lblFecha.anchor = GridBagConstraints.EAST;
		gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
		gbc_lblFecha.gridx = 0;
		gbc_lblFecha.gridy = 3;
		add(lblFecha, gbc_lblFecha);
		
		jftfFecha = new JFormattedTextField();
		GridBagConstraints gbc_jftfFecha = new GridBagConstraints();
		gbc_jftfFecha.gridwidth = 2;
		gbc_jftfFecha.insets = new Insets(0, 0, 5, 0);
		gbc_jftfFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_jftfFecha.gridx = 1;
		gbc_jftfFecha.gridy = 3;
		add(jftfFecha, gbc_jftfFecha);
		
		JLabel lblLmite = new JLabel("Límite:");
		GridBagConstraints gbc_lblLmite = new GridBagConstraints();
		gbc_lblLmite.anchor = GridBagConstraints.EAST;
		gbc_lblLmite.insets = new Insets(0, 0, 5, 5);
		gbc_lblLmite.gridx = 0;
		gbc_lblLmite.gridy = 4;
		add(lblLmite, gbc_lblLmite);
		
		spinnerLimite = new JSpinner(new SpinnerNumberModel(500, 0, 3000, 25));
		spinnerLimite.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// Con un operador ternario comprobamos si el Object no es null 
				// y si el Object es un Integer
				sliderSaldo.setMaximum(
						(spinnerLimite.getValue() != null && 
						spinnerLimite.getValue() instanceof Integer) ? 
								(Integer) spinnerLimite.getValue() : 0 );
				
			}
		});
		GridBagConstraints gbc_spinnerLimite = new GridBagConstraints();
		gbc_spinnerLimite.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerLimite.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerLimite.gridx = 1;
		gbc_spinnerLimite.gridy = 4;
		add(spinnerLimite, gbc_spinnerLimite);
		
		JLabel lblEuros = new JLabel("Euros (€)");
		GridBagConstraints gbc_lblEuros = new GridBagConstraints();
		gbc_lblEuros.anchor = GridBagConstraints.WEST;
		gbc_lblEuros.insets = new Insets(0, 0, 5, 0);
		gbc_lblEuros.gridx = 2;
		gbc_lblEuros.gridy = 4;
		add(lblEuros, gbc_lblEuros);
		
		JLabel lblSaldo = new JLabel("Saldo:");
		GridBagConstraints gbc_lblSaldo = new GridBagConstraints();
		gbc_lblSaldo.anchor = GridBagConstraints.EAST;
		gbc_lblSaldo.insets = new Insets(0, 0, 5, 5);
		gbc_lblSaldo.gridx = 0;
		gbc_lblSaldo.gridy = 5;
		add(lblSaldo, gbc_lblSaldo);
		
		
		sliderSaldo = new JSlider(0, (Integer) spinnerLimite.getValue(), 1);
		sliderSaldo.setValue(0);
		sliderSaldo.setMaximum((int) spinnerLimite.getValue());
		sliderSaldo.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				lblEurosSaldo.setText(sliderSaldo.getValue() + " Euros (€)");
				
			}
		});
		GridBagConstraints gbc_sliderSaldo = new GridBagConstraints();
		gbc_sliderSaldo.fill = GridBagConstraints.HORIZONTAL;
		gbc_sliderSaldo.insets = new Insets(0, 0, 5, 5);
		gbc_sliderSaldo.gridx = 1;
		gbc_sliderSaldo.gridy = 5;
		add(sliderSaldo, gbc_sliderSaldo);
		
		lblEurosSaldo = new JLabel("Euros (€)");
		GridBagConstraints gbc_lblEurosSaldo = new GridBagConstraints();
		gbc_lblEurosSaldo.anchor = GridBagConstraints.WEST;
		gbc_lblEurosSaldo.insets = new Insets(0, 0, 5, 0);
		gbc_lblEurosSaldo.gridx = 2;
		gbc_lblEurosSaldo.gridy = 5;
		add(lblEurosSaldo, gbc_lblEurosSaldo);
		
		JLabel lblTipoDeContrato = new JLabel("Tipo de Contrato:");
		GridBagConstraints gbc_lblTipoDeContrato = new GridBagConstraints();
		gbc_lblTipoDeContrato.anchor = GridBagConstraints.EAST;
		gbc_lblTipoDeContrato.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoDeContrato.gridx = 0;
		gbc_lblTipoDeContrato.gridy = 6;
		add(lblTipoDeContrato, gbc_lblTipoDeContrato);
		
		jtfTipoContrato = new JTextField();
		jtfTipoContrato.setEnabled(false);
		GridBagConstraints gbc_jtfTipoContrato = new GridBagConstraints();
		gbc_jtfTipoContrato.insets = new Insets(0, 0, 5, 5);
		gbc_jtfTipoContrato.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfTipoContrato.gridx = 1;
		gbc_jtfTipoContrato.gridy = 6;
		add(jtfTipoContrato, gbc_jtfTipoContrato);
		jtfTipoContrato.setColumns(10);
		
		btnSeleccionaTipoDe = new JButton("Selecciona tipo contrato");
		GridBagConstraints gbc_btnSeleccionaTipoDe = new GridBagConstraints();
		gbc_btnSeleccionaTipoDe.insets = new Insets(0, 0, 5, 0);
		gbc_btnSeleccionaTipoDe.gridx = 2;
		gbc_btnSeleccionaTipoDe.gridy = 6;
		add(btnSeleccionaTipoDe, gbc_btnSeleccionaTipoDe);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		GridBagConstraints gbc_lblUsuario = new GridBagConstraints();
		gbc_lblUsuario.anchor = GridBagConstraints.EAST;
		gbc_lblUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsuario.gridx = 0;
		gbc_lblUsuario.gridy = 7;
		add(lblUsuario, gbc_lblUsuario);
		
		jtfUsuario = new JTextField();
		jtfUsuario.setEnabled(false);
		GridBagConstraints gbc_jtfUsuario = new GridBagConstraints();
		gbc_jtfUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_jtfUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfUsuario.gridx = 1;
		gbc_jtfUsuario.gridy = 7;
		add(jtfUsuario, gbc_jtfUsuario);
		jtfUsuario.setColumns(10);
		
		btnSeleccionaUsuario = new JButton("Selecciona usuario");
		btnSeleccionaUsuario.setIcon(new ImageIcon(JPanelContrato.class.getResource("/bankonter/res/usuario.png")));
		GridBagConstraints gbc_btnSeleccionaUsuario = new GridBagConstraints();
		gbc_btnSeleccionaUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSeleccionaUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_btnSeleccionaUsuario.gridx = 2;
		gbc_btnSeleccionaUsuario.gridy = 7;
		add(btnSeleccionaUsuario, gbc_btnSeleccionaUsuario);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(100, 0, 0, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 8;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{50, 50, 92, 50, 50, 0};
		gbl_panel.rowHeights = new int[]{26, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnFirst = new JButton("");
		btnFirst.setIcon(new ImageIcon(JPanelContrato.class.getResource("/bankonter/res/gotostart.png")));
		GridBagConstraints gbc_btnFirst = new GridBagConstraints();
		gbc_btnFirst.anchor = GridBagConstraints.WEST;
		gbc_btnFirst.insets = new Insets(0, 0, 0, 5);
		gbc_btnFirst.gridx = 0;
		gbc_btnFirst.gridy = 0;
		panel.add(btnFirst, gbc_btnFirst);
		
		btnPrevious = new JButton("");
		GridBagConstraints gbc_btnPrevious = new GridBagConstraints();
		gbc_btnPrevious.anchor = GridBagConstraints.WEST;
		gbc_btnPrevious.insets = new Insets(0, 0, 0, 5);
		gbc_btnPrevious.gridx = 1;
		gbc_btnPrevious.gridy = 0;
		panel.add(btnPrevious, gbc_btnPrevious);
		btnPrevious.setIcon(new ImageIcon(JPanelContrato.class.getResource("/bankonter/res/previous.png")));
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon(JPanelContrato.class.getResource("/bankonter/res/guardar.png")));
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.anchor = GridBagConstraints.WEST;
		gbc_btnGuardar.insets = new Insets(0, 0, 0, 5);
		gbc_btnGuardar.gridx = 2;
		gbc_btnGuardar.gridy = 0;
		panel.add(btnGuardar, gbc_btnGuardar);
		
		btnNext = new JButton("");
		btnNext.setIcon(new ImageIcon(JPanelContrato.class.getResource("/bankonter/res/next.png")));
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.anchor = GridBagConstraints.WEST;
		gbc_btnNext.insets = new Insets(0, 0, 0, 5);
		gbc_btnNext.gridx = 3;
		gbc_btnNext.gridy = 0;
		panel.add(btnNext, gbc_btnNext);
		
		btnLast = new JButton("");
		btnLast.setIcon(new ImageIcon(JPanelContrato.class.getResource("/bankonter/res/gotoend.png")));
		GridBagConstraints gbc_btnLast = new GridBagConstraints();
		gbc_btnLast.anchor = GridBagConstraints.WEST;
		gbc_btnLast.gridx = 4;
		gbc_btnLast.gridy = 0;
		panel.add(btnLast, gbc_btnLast);
		
		showContrato(current);

	}
	
	public void showContrato(Contrato c) {
		this.jtfDescripcion.setText(c.getId() + "");
	}

}
