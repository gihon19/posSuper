package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;

import controlador.CtlDevoluciones;
import controlador.CtlFacturar;
import view.botones.BotonActualizar;
import view.botones.BotonBuscar1;
import view.botones.BotonBuscarClientes;
import view.botones.BotonCancelar;
import view.botones.BotonCobrar;
import view.botones.BotonGuardar;
import view.rendes.RenderizadorTablaFactura;
import view.tablemodel.CbxTmEmpleado;
import view.tablemodel.TablaModeloFactura;
import view.tablemodel.TmDevoluciones;

import javax.swing.UIManager;

public class ViewFacturaDevolucion extends JDialog {
	
	protected BorderLayout miEsquema;
	private JTable tableDetalle;
	private TmDevoluciones modeloTabla;
	
	protected JTextField txtBuscar;
	protected JTextField txtArticulo;
	protected JTextField txtPrecio;
	
	private JPanel panelAcciones;
	private JPanel panelDatosFactura;
	protected JPanel panelBuscar;
	private JPanel panelNorte;
	private JLabel lblFecha;
	private JTextField txtFechafactura;
	private JLabel lblCodigoCliente;
	private JTextField txtIdcliente;
	private JTextField txtNombrecliente;
	
	private ButtonGroup grupoOpciones;
	private JRadioButton rdbtnCredito;
	private JRadioButton rdbtnContado;
	
	private JTextField txtSubtotal;
	private JLabel lblSubtotal;
	private JTextField txtImpuesto;
	private JLabel lblImpuesto;
	private JTextField txtTotal;
	private JLabel lblTotal;
	private JLabel lblNombreCliente;
	private JLabel lblContado;
	private JLabel lblCredito;
	
	private BotonGuardar btnGuardar;
	private BotonCancelar btnCerrar;
	private BotonBuscar1 btnBuscar;
	private BotonBuscarClientes btnCliente;
	private BotonCobrar btnCobrar;
	private JButton btnCierreCaja;
	
	private JTextField txtDescuento;
	
	private BotonActualizar btnActualizar;
	private JTextField txtImpuesto18;
	private JButton btnPendientes;
	
	private JTextField txtRtn;
	
	
	private JComboBox cbxEmpleados;
	//se crea el modelo de la lista de los impuestos
	private CbxTmEmpleado modeloEmpleado;//=new ComboBoxImpuesto();
	private JPanel panel;
	private JPanel panel_1;

	public ViewFacturaDevolucion(Window view) {
		
		super(view,"Facturar",Dialog.ModalityType.DOCUMENT_MODAL);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewFacturar.class.getResource("/view/recursos/logo-admin-tool1.png")));
		panelAcciones=new JPanel();
		panelAcciones.setBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "Opciones", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelAcciones.setBounds(20, 11, 178, 459);
		panelAcciones.setLayout(null);
		//panelAcciones.setVisible(false);
		
		
		//this.setTitle("Facturar");
		getContentPane().add(panelAcciones);
		
		btnBuscar = new BotonBuscar1();
		btnBuscar.setEnabled(false);
		btnBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		btnBuscar.setBounds(10, 24,158, 38);
		panelAcciones.add(btnBuscar);
		//btnBuscar.getInputMap().put(KeyStroke.getKeyStroke("F1"), sumar());
		
		btnCliente = new BotonBuscarClientes();
		btnCliente.setEnabled(false);
		btnCliente.setText("F3 Clientes");
		btnCliente.setHorizontalAlignment(SwingConstants.LEFT);
		btnCliente.setBounds(10, 148, 158, 38);
		panelAcciones.add(btnCliente);
		
		btnCobrar = new BotonCobrar();
		btnCobrar.setEnabled(false);
		btnCobrar.setText("F2 Cobrar");
		btnCobrar.setHorizontalAlignment(SwingConstants.LEFT);
		btnCobrar.setBounds(10, 86, 158, 38);
		
		panelAcciones.add(btnCobrar);
		
		btnActualizar=new BotonActualizar();
		btnActualizar.setEnabled(false);
		btnActualizar.setText("F7 Actualizar");
		btnActualizar.setHorizontalAlignment(SwingConstants.LEFT);
		btnActualizar.setBounds(10, 210, 158, 38);
		//getContentPane().add(btnActualizar);
		panelAcciones.add(btnActualizar);
		btnActualizar.setVisible(false);
		
		btnGuardar = new BotonGuardar();
		btnGuardar.setHorizontalAlignment(SwingConstants.LEFT);
		btnGuardar.setText("F4 Guardar");
		btnGuardar.setBounds(10, 210, 158, 38);
		panelAcciones.add(btnGuardar);
		
		btnCerrar = new BotonCancelar();
		btnCerrar.setHorizontalAlignment(SwingConstants.LEFT);
		btnCerrar.setText("Esc Cerrar");
		btnCerrar.setBounds(10, 396, 158, 38);
		panelAcciones.add(btnCerrar);
		
		btnCierreCaja = new JButton("F6 Cierre");
		btnCierreCaja.setEnabled(false);
		btnCierreCaja.setHorizontalAlignment(SwingConstants.LEFT);
		btnCierreCaja.setIcon(new ImageIcon(ViewFacturar.class.getResource("/view/recursos/caja.png")));
		btnCierreCaja.setBounds(10, 334, 158, 38);
		panelAcciones.add(btnCierreCaja);
		
		btnPendientes = new JButton("F5 Pendientes");
		btnPendientes.setEnabled(false);
		btnPendientes.setIcon(new ImageIcon(ViewFacturar.class.getResource("/view/recursos/lista.png")));
		btnPendientes.setHorizontalAlignment(SwingConstants.LEFT);
		btnPendientes.setBounds(10, 272, 158, 38);
		panelAcciones.add(btnPendientes);
		
		
		panelDatosFactura=new JPanel();
		//panelDatosFactura.setBackground(Color.WHITE);
		panelDatosFactura.setBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "Datos Generales", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDatosFactura.setBounds(196, 11, 802, 84);
		panelDatosFactura.setLayout(null);
		
		getContentPane().add(panelDatosFactura);
		
		panelBuscar= new JPanel();
		panelBuscar.setBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "Buscar Articulo", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelBuscar.setBounds(196, 94, 802, 50);
		panelBuscar.setLayout(null);
		//getContentPane().geti
		getContentPane().add(panelBuscar);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(10, 19, 208, 20);
		panelBuscar.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		txtArticulo = new JTextField();
		txtArticulo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtArticulo.setForeground(new Color(0, 0, 255));
		txtArticulo.setEditable(false);
		txtArticulo.setBounds(284, 19, 258, 20);
		panelBuscar.add(txtArticulo);
		txtArticulo.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtPrecio.setForeground(new Color(0, 0, 255));
		txtPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPrecio.setEditable(false);
		txtPrecio.setBounds(611, 19, 104, 20);
		panelBuscar.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JLabel lblArticulo = new JLabel("Articulo:");
		lblArticulo.setBounds(228, 22, 56, 14);
		panelBuscar.add(lblArticulo);
		
		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(563, 22, 46, 14);
		panelBuscar.add(lblPrecio);
		
		lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(10, 23, 40, 14);
		panelDatosFactura.add(lblFecha);
		
		txtFechafactura = new JTextField();
		txtFechafactura.setEditable(false);
		txtFechafactura.setBounds(10, 44, 71, 29);
		panelDatosFactura.add(txtFechafactura);
		txtFechafactura.setColumns(10);
		
		lblCodigoCliente = new JLabel("Id Cliente");
		lblCodigoCliente.setBounds(91, 23, 61, 14);
		panelDatosFactura.add(lblCodigoCliente);
		
		txtIdcliente = new JTextField();
		txtIdcliente.setBounds(91, 44, 49, 29);
		panelDatosFactura.add(txtIdcliente);
		txtIdcliente.setColumns(10);
		
		txtNombrecliente = new JTextField();
		txtNombrecliente.setToolTipText("Nombre Cliente");
		txtNombrecliente.setBounds(150, 44, 160, 29);
		panelDatosFactura.add(txtNombrecliente);
		txtNombrecliente.setColumns(10);
		
		
		JLabel lblRtn = new JLabel("R:T:N");
		lblRtn.setBounds(320, 23, 112, 14);
		panelDatosFactura.add(lblRtn);
		
		txtRtn = new JTextField();
		txtRtn.setBounds(320, 44, 127, 29);
		panelDatosFactura.add(txtRtn);
		txtRtn.setColumns(10);
		
		grupoOpciones = new ButtonGroup();
		rdbtnCredito = new JRadioButton("");
		//rdbtnCredito.setEnabled(false);// para descativar los creditos
		rdbtnCredito.setBounds(526, 47, 21, 23);
		grupoOpciones.add(rdbtnCredito);
		panelDatosFactura.add(rdbtnCredito);
		
		rdbtnContado = new JRadioButton("");
		rdbtnContado.setVerticalAlignment(SwingConstants.TOP);
		rdbtnContado.setSelected(true);
		rdbtnContado.setBounds(468, 47, 21, 23);
		grupoOpciones.add(rdbtnContado);
		panelDatosFactura.add(rdbtnContado);
		
		lblNombreCliente = new JLabel("Nombre Cliente");
		lblNombreCliente.setBounds(150, 23, 160, 14);
		panelDatosFactura.add(lblNombreCliente);
		
		lblContado = new JLabel("Contado");
		lblContado.setBounds(454, 23, 49, 14);
		panelDatosFactura.add(lblContado);
		
		lblCredito = new JLabel("Credito");
		lblCredito.setBounds(513, 23, 46, 14);
		panelDatosFactura.add(lblCredito);
		
		JLabel lblVendedor = new JLabel("Vendedor");
		lblVendedor.setBounds(581, 23, 61, 14);
		panelDatosFactura.add(lblVendedor);
		
		cbxEmpleados = new JComboBox();
		this.modeloEmpleado=new CbxTmEmpleado();
		//cbxEmpleados.setModel(modeloEmpleado);//comentar para moder ver la vista de diseño
		cbxEmpleados.setBounds(581, 48, 199, 20);
		panelDatosFactura.add(cbxEmpleados);
		
		
		tableDetalle = new JTable();
		modeloTabla=new TmDevoluciones();
		tableDetalle.setModel(modeloTabla);
		
		RenderizadorTablaFactura renderizador = new RenderizadorTablaFactura();
		tableDetalle.setDefaultRenderer(String.class, renderizador);
		//tableDetalle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tableDetalle.getColumnModel().getColumn(0).setPreferredWidth(100);     //Tamaño de las columnas de las tablas
		tableDetalle.getColumnModel().getColumn(1).setPreferredWidth(200);	//
		tableDetalle.getColumnModel().getColumn(2).setPreferredWidth(80);	//
		tableDetalle.getColumnModel().getColumn(3).setPreferredWidth(80);	//
		tableDetalle.getColumnModel().getColumn(4).setPreferredWidth(80);	//
		tableDetalle.getColumnModel().getColumn(5).setPreferredWidth(80);	//
		tableDetalle.getColumnModel().getColumn(6).setPreferredWidth(80);	//
		tableDetalle.getColumnModel().getColumn(7).setPreferredWidth(100);	//
		
		tableDetalle.setRowHeight(30);
		//registerEnterKey( );
		
		JScrollPane scrollPane = new JScrollPane(tableDetalle);
		scrollPane.setBounds(196, 144, 802, 326);
		getContentPane().add(scrollPane);
		
		
		getContentPane().setLayout(null);
		
		Font myFont=new Font("OCR A Extended", Font.PLAIN, 45);
		txtSubtotal = new JTextField();
		txtSubtotal.setFont(myFont);
		txtSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSubtotal.setText("00");
		
		txtSubtotal.setEditable(false);
		txtSubtotal.setBounds(20, 506, 207, 44);
		getContentPane().add(txtSubtotal);
		txtSubtotal.setColumns(10);
		
		lblSubtotal = new JLabel("SubTotal");
		lblSubtotal.setBounds(20, 490, 59, 14);
		getContentPane().add(lblSubtotal);
		
		txtImpuesto = new JTextField();
		txtImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtImpuesto.setFont(myFont);
		txtImpuesto.setText("00");
		txtImpuesto.setEditable(false);
		txtImpuesto.setBounds(237, 506, 177, 44);
		getContentPane().add(txtImpuesto);
		txtImpuesto.setColumns(10);
		
		lblImpuesto = new JLabel("Impuesto 15");
		lblImpuesto.setBounds(237, 490, 92, 14);
		getContentPane().add(lblImpuesto);
		
		txtTotal = new JTextField();
		txtTotal.setForeground(Color.RED);
		txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotal.setFont(myFont);
		txtTotal.setText("00");
		txtTotal.setEditable(false);
		txtTotal.setBounds(778, 506, 220, 44);
		getContentPane().add(txtTotal);
		txtTotal.setColumns(10);
		
		lblTotal = new JLabel("Total");
		lblTotal.setBounds(780, 490, 46, 14);
		getContentPane().add(lblTotal);
		
		txtDescuento = new JTextField();
		txtDescuento.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDescuento.setEditable(false);
		txtDescuento.setText("00");
		txtDescuento.setFont(myFont);
		txtDescuento.setBounds(605, 506, 163, 44);
		getContentPane().add(txtDescuento);
		txtDescuento.setColumns(10);
		
		JLabel lblDescuento = new JLabel("Descuento");
		lblDescuento.setBounds(605, 490, 92, 14);
		getContentPane().add(lblDescuento);
		
		txtImpuesto18 = new JTextField();
		txtImpuesto18.setText("00");
		txtImpuesto18.setHorizontalAlignment(SwingConstants.RIGHT);
		txtImpuesto18.setFont(myFont);
		txtImpuesto18.setEditable(false);
		txtImpuesto18.setBounds(424, 506, 171, 44);
		getContentPane().add(txtImpuesto18);
		txtImpuesto18.setColumns(10);
		
		JLabel lblImpuesto_1 = new JLabel("Impuesto 18");
		lblImpuesto_1.setBounds(424, 490, 82, 14);
		getContentPane().add(lblImpuesto_1);
		setSize(1024, 600);
		
		this.setPreferredSize(new Dimension(1024, 600));
		this.setResizable(false);
		//centrar la ventana en la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.pack();
		
	}
	public JComboBox getCbxEmpleados(){
		return cbxEmpleados;
	}
	public CbxTmEmpleado getModeloEmpleados(){
		return this.modeloEmpleado;
	}
	
	public JRadioButton getRdbtnContado(){
		return rdbtnContado;
	}
	public  JRadioButton getRdbtnCredito(){
		return  rdbtnCredito;
	}
	public BotonActualizar getBtnActualizar(){
		return btnActualizar;
	}
	public JTextField getTxtRtn(){
		return txtRtn;
	}
	public BotonGuardar getBtnGuardar(){
		return btnGuardar;
	}
	public JButton getBtnBuscar(){
		return btnBuscar;
	}
	public JButton getBtnBuscarCliente(){
		return btnCliente;
	}
	public JButton getBtnCobrar(){
		return btnCobrar;
	}
	public JButton getBtnCerrar(){
		return btnCerrar;
	}
	public JButton getBtnPendientes(){
		return this.btnPendientes;
	}
	public JPanel getPanelAcciones(){
		return panelAcciones;
	}
	public JTextField getTxtDescuento(){
		return txtDescuento;		
	}
	public JTextField getTxtSubtotal(){
		return txtSubtotal;
	}
	public JTextField getTxtImpuesto(){
		return txtImpuesto;
	}
	public JTextField getTxtImpuesto18(){
		return txtImpuesto18;
	}
	public JTextField getTxtTotal(){
		return txtTotal;
	}
	public JTextField getTxtNombrecliente(){
		return txtNombrecliente;
	}
	public JTextField getTxtIdcliente(){
		return txtIdcliente;
	}
	public TmDevoluciones getModeloTabla(){
		return modeloTabla;
	}
	public JTable getTableDetalle(){
		return tableDetalle;
	}
	public JTextField getTxtFechafactura(){
		return txtFechafactura;
	}
	public void conectarContralador(CtlDevoluciones c){
		
		txtIdcliente.addActionListener(c);
		txtIdcliente.setActionCommand("BUSCARCLIENTE");
		
		tableDetalle.addKeyListener(c);
		tableDetalle.addMouseListener(c);
		modeloTabla.addTableModelListener(c);
		//tableDetalle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDetalle.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tableDetalle.setColumnSelectionAllowed(true);
		tableDetalle.setRowSelectionAllowed(true);
		tableDetalle.setCellSelectionEnabled(true);
		
		txtIdcliente.addKeyListener(c);
		txtNombrecliente.addKeyListener(c);
		txtFechafactura.addKeyListener(c);
		
		btnCierreCaja.addKeyListener(c);
		btnCierreCaja.addActionListener(c);
		btnCierreCaja.setActionCommand("CIERRECAJA");
		
		
		btnPendientes.addKeyListener(c);
		btnPendientes.addActionListener(c);
		btnPendientes.setActionCommand("PENDIENTES");
		
		this.btnBuscar.addKeyListener(c);
		this.btnBuscar.addActionListener(c);
		this.btnBuscar.setActionCommand("BUSCARARTICULO");
		
		
		this.btnCerrar.addKeyListener(c);
		this.btnCerrar.addActionListener(c);
		this.btnCerrar.setActionCommand("CERRAR");
		
		this.btnCliente.addKeyListener(c);
		this.btnCliente.addActionListener(c);
		this.btnCliente.setActionCommand("BUSCARCLIENTES");
		
		this.btnCobrar.addKeyListener(c);
		this.btnCobrar.addActionListener(c);
		this.btnCobrar.setActionCommand("COBRAR");
		
		this.btnGuardar.addKeyListener(c);
		this.btnGuardar.addActionListener(c);
		this.btnGuardar.setActionCommand("GUARDAR");
		
		btnActualizar.addKeyListener(c);
		this.btnActualizar.addActionListener(c);
		this.btnActualizar.setActionCommand("ACTUALIZAR");
		
		this.rdbtnContado.addKeyListener(c);
		this.rdbtnCredito.addKeyListener(c);
		this.txtDescuento.addKeyListener(c);
		this.txtImpuesto.addKeyListener(c);
		this.txtSubtotal.addKeyListener(c);
		txtRtn.addKeyListener(c);
		this.txtTotal.addKeyListener(c);
		
		
	}

}
