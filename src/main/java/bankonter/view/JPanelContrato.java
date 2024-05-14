package bankonter.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.Highlighter;

import bankonter.controladores.ControladorContratoJPA;
import bankonter.entities.Contrato;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.persistence.EntityManager;
import javax.swing.BoxLayout;

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
	private JPanel panel_1;
	private JLabel lblEstadoOperacion;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	

	/**
	 * Create the panel.
	 */
	public JPanelContrato() {
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 295, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
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
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newContrato();
			}
		});
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
		
		jftfFecha = new JFormattedTextField(
				new JFormattedTextField.AbstractFormatter() {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

					@Override
					public String valueToString(Object value) throws ParseException {
						if (value != null && value instanceof Date) {
							return sdf.format(((Date) value));
						}
						return "";
					}

					@Override
					public Object stringToValue(String text) throws ParseException {
						try {
							jftfFecha.setBackground(null);
							lblEstadoOperacion.setText("Fecha correcta");
							
							// Después de 2s ponemos el label vacío de nuevo
							setLblEstadoToDefault();
							return sdf.parse(text);							
						} catch (Exception e) {
//							JOptionPane.showMessageDialog(null, "Error en la fecha");
							jftfFecha.setBackground(Color.RED);
							lblEstadoOperacion.setText("Error en la fecha. Fórmato válido: dd/MM/yyyy");
							
							// Después de 2s ponemos el label vacío de nuevo
							setLblEstadoToDefault();
							return null;
						}
					}
				});
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
		
		spinnerLimite = new JSpinner(new SpinnerNumberModel(500, 0, 100000000, 25));
		spinnerLimite.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// Con un operador ternario comprobamos si el Object no es null 
				// y si el Object es un Integer
				sliderSaldo.setMaximum(
						(spinnerLimite.getValue() != null && 
						spinnerLimite.getValue() instanceof Number) ? 
								((Number) spinnerLimite.getValue()).intValue() : 0 );
				
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
		btnSeleccionaTipoDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showJDialogTipoContrato();
			}
		});
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
		gbc_panel.insets = new Insets(100, 0, 5, 0);
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
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showFirstContrato();
				btnPrevious.setEnabled(false);
				btnNext.setEnabled(true);
			}
		});
		btnFirst.setIcon(new ImageIcon(JPanelContrato.class.getResource("/bankonter/res/gotostart.png")));
		GridBagConstraints gbc_btnFirst = new GridBagConstraints();
		gbc_btnFirst.anchor = GridBagConstraints.WEST;
		gbc_btnFirst.insets = new Insets(0, 0, 0, 5);
		gbc_btnFirst.gridx = 0;
		gbc_btnFirst.gridy = 0;
		panel.add(btnFirst, gbc_btnFirst);
		
		btnPrevious = new JButton("");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPreviousContrato();
				btnNext.setEnabled(true);
			}
		});
		GridBagConstraints gbc_btnPrevious = new GridBagConstraints();
		gbc_btnPrevious.anchor = GridBagConstraints.WEST;
		gbc_btnPrevious.insets = new Insets(0, 0, 0, 5);
		gbc_btnPrevious.gridx = 1;
		gbc_btnPrevious.gridy = 0;
		panel.add(btnPrevious, gbc_btnPrevious);
		btnPrevious.setIcon(new ImageIcon(JPanelContrato.class.getResource("/bankonter/res/previous.png")));
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					saveContrato();
					lblEstadoOperacion.setText("Guardado correctamente");
					
					setLblEstadoToDefault();
					
				} catch (ParseException e1) {
					lblEstadoOperacion.setText("Error. No se ha guardado.");
					
					setLblEstadoToDefault();
					
				}
			}
		});
		btnGuardar.setIcon(new ImageIcon(JPanelContrato.class.getResource("/bankonter/res/guardar.png")));
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.anchor = GridBagConstraints.WEST;
		gbc_btnGuardar.insets = new Insets(0, 0, 0, 5);
		gbc_btnGuardar.gridx = 2;
		gbc_btnGuardar.gridy = 0;
		panel.add(btnGuardar, gbc_btnGuardar);
		
		btnNext = new JButton("");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showNextContrato();
				btnPrevious.setEnabled(true);
				btnFirst.setEnabled(true);
			}
		});
		btnNext.setIcon(new ImageIcon(JPanelContrato.class.getResource("/bankonter/res/next.png")));
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.anchor = GridBagConstraints.WEST;
		gbc_btnNext.insets = new Insets(0, 0, 0, 5);
		gbc_btnNext.gridx = 3;
		gbc_btnNext.gridy = 0;
		panel.add(btnNext, gbc_btnNext);
		
		btnLast = new JButton("");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showLastContrato();
				btnNext.setEnabled(false);
				btnPrevious.setEnabled(true);
				btnFirst.setEnabled(true);
			}
		});
		btnLast.setIcon(new ImageIcon(JPanelContrato.class.getResource("/bankonter/res/gotoend.png")));
		GridBagConstraints gbc_btnLast = new GridBagConstraints();
		gbc_btnLast.anchor = GridBagConstraints.WEST;
		gbc_btnLast.gridx = 4;
		gbc_btnLast.gridy = 0;
		panel.add(btnLast, gbc_btnLast);
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.SOUTH;
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.gridheight = 2;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 9;
		add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		lblEstadoOperacion = new JLabel("");
		lblEstadoOperacion.setHorizontalAlignment(SwingConstants.LEFT);
		lblEstadoOperacion.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_1.add(lblEstadoOperacion);
		
		showFirstContrato();
		btnFirst.setEnabled(false);
		btnPrevious.setEnabled(false);

	}
	
	public void showFirstContrato() {
		current = (Contrato) ControladorContratoJPA.getInstance().findFirst();
		
		showContratoData(current);
	}
	
	public void showContratoData(Contrato c) {
		if (c != null) {
			this.jtfDescripcion.setText(c.getDescripcion());
			//Here goes JFormattedTextField
			this.jftfFecha.setValue(c.getFechaFirma());
			
			this.spinnerLimite.setValue(c.getLimite());
			this.sliderSaldo.setValue((int) c.getSaldo());
			this.jtfTipoContrato.setText(c.getTipocontrato().getId() + "- " + c.getTipocontrato().getDescripcion());
			this.jtfUsuario.setText(c.getUsuario().getNombreUsuario());
			
		}
	}
	
	public void showNextContrato() {
		current = (Contrato) ControladorContratoJPA.getInstance().findNext(current.getId());
		
		showContratoData(current);
	}
	
	public void showPreviousContrato() {
		current = (Contrato) ControladorContratoJPA.getInstance().findPrevious(current.getId());
		
		showContratoData(current);
	}
	
	public void showLastContrato() {
		current = (Contrato) ControladorContratoJPA.getInstance().findLast();
		
		showContratoData(current);
	}

	public void setLblEstadoToDefault() {
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lblEstadoOperacion.setText("");					
			}
		});
		t.start();

	}
	
	public void newContrato() {
		this.jtfDescripcion.setText("");
		this.jftfFecha.setText("");
		this.sliderSaldo.setValue(0);
		this.spinnerLimite.setValue(0);
		this.jtfTipoContrato.setText("");
		this.jtfUsuario.setText("");
	}

	public void saveContrato() throws ParseException {
		
		Contrato c = new Contrato();
		
		c.setDescripcion(this.jtfDescripcion.getText());
		
		if (this.jftfFecha.getText().trim().equals(""))
			c.setFechaFirma(null);
		else {
			c.setFechaFirma(sdf.parse(this.jftfFecha.getText()));
		}
		
		c.setLimite((float) this.spinnerLimite.getValue());
		c.setSaldo(this.sliderSaldo.getValue());
		
		// Here goes TipoContrato and Usuario
		
		
		ControladorContratoJPA.getInstance().update(c);
		
	}
	
	public void deleteContrato() {
		
	}
	
	private void showJDialogTipoContrato() {
		
		JDialog dialogo = new JDialog();
		// El usuario no puede redimensionar el diálogo
		dialogo.setResizable(true);
		// título del díalogo
		dialogo.setTitle("JDialog - Gestión Proveedor");
		// Introducimos el PanelProveedor al JDialog,
		// pasándole como parámetro el PanelArticulo actual. Es decir,
		// estamos pasando una referencia a la instancia actual de
		// PanelArticulo.
		// Además, le pasamos el panelProveedor el propio JDialog, consiguiendo
		// una instancia del mismo para poder realizar la actualización de los
		// datos del jcbProveedores.
		dialogo.setContentPane(new JPanelTipoContrato(this, dialogo));
		// Empaquetar el di�logo hace que todos los componentes ocupen el espacio que deben y el lugar adecuado
		dialogo.pack();
		// El usuario no puede hacer clic sobre la ventana padre, si el Di�logo es modal
		dialogo.setModal(true);
		// Centro el di�logo en pantalla
		dialogo.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - dialogo.getWidth()/2, 
				(Toolkit.getDefaultToolkit().getScreenSize().height)/2 - dialogo.getHeight()/2);
		// Muestro el di�logo en pantalla
		dialogo.setVisible(true);
	}
	
}
