package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.SystemColor;

import javax.swing.SwingConstants;

import controlador.CtlFacturar;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import view.botones.BotonActualizar;
import view.botones.BotonBuscar1;
import view.botones.BotonBuscarClientes;
import view.botones.BotonCancelar;
import view.botones.BotonCierreCaja;
import view.botones.BotonCobrar;
import view.botones.BotonGuardar;
import view.botones.BotonPendientes;
import view.rendes.RenderizadorTablaFactura;
import view.tablemodel.CbxTmEmpleado;
import view.tablemodel.ComboBoxImpuesto;
import view.tablemodel.TablaModeloFactura;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ViewFacturar extends JDialog {
	
	protected BorderLayout miEsquema;
	private JTable tableDetalle;
	private TablaModeloFactura modeloTabla;
	
	private JPanel panelAcciones;
	private JPanel panelBuscar;
	private JPanel panelDatosFactura;
	protected JPanel panelNorte;
	protected JPanel panel_1;
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
	
	
	private JTextField txtBuscar;
	private JTextField txtImpuesto18;
	private JButton btnPendientes;
	
	private JTextField txtRtn;
	
	private JComboBox cbxEmpleados;
	//se crea el modelo de la lista de los impuestos
	private CbxTmEmpleado modeloEmpleado;//=new ComboBoxImpuesto();
	
	protected JPanel panel;
	protected JLabel lblBuscar;
	private JPanel panel_2;
	private JLabel lblLogo;
	
	public ViewFacturar(Window view) {
		
		super(view,"Facturar",Dialog.ModalityType.DOCUMENT_MODAL);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewFacturar.class.getResource("/view/recursos/logo-admin-tool1.png")));
		//getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		grupoOpciones = new ButtonGroup();
		modeloEmpleado=new CbxTmEmpleado();
		modeloTabla=new TablaModeloFactura();
		RenderizadorTablaFactura renderizador = new RenderizadorTablaFactura();
		miEsquema=new BorderLayout();
		//Color color1 =Color.decode("#0009999");
		Color color1 =new Color(60, 179, 113);
		Color color2 =Color.decode("#33cccc");
		Color color3 =Color.decode("#d4f4ff");
		Color color4 =Color.decode("#f4fbfe");
		
		
		
		panelNorte=new JPanel();
	
		this.getContentPane().setBackground(color3);
		
		//this.setTitle("Articulos");
		getContentPane().setLayout(miEsquema);
		panelAcciones=new JPanel();
		panelAcciones.setPreferredSize(new Dimension(140,128));
		panelAcciones.setBackground(color3);
		//panelAcciones.setBounds(20, 11, 178, 459);
		//panelAcciones.setLayout(null);
		//panelAcciones.setVisible(false);
		JPanel panelNorte=new JPanel();
		panelNorte.setBackground(color1);
		getContentPane().add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BorderLayout(0, 0));
				
		
		
		
		
		
		
		
		
		tableDetalle = new JTable();
		tableDetalle.setModel(modeloTabla);
		/*tableDetalle.getTableHeader().setBackground(color1);
		tableDetalle.getTableHeader().setForeground(Color.WHITE);*/
	    
		
		//tableDetalle.setBackground(color4);
		tableDetalle.setDefaultRenderer(String.class, renderizador);
		//tableDetalle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tableDetalle.getColumnModel().getColumn(0).setPreferredWidth(420);	//
		tableDetalle.getColumnModel().getColumn(1).setPreferredWidth(80);	//
		tableDetalle.getColumnModel().getColumn(2).setPreferredWidth(80);	//
		tableDetalle.getColumnModel().getColumn(3).setPreferredWidth(80);	//
		tableDetalle.getColumnModel().getColumn(4).setPreferredWidth(80);	//
		tableDetalle.getColumnModel().getColumn(5).setPreferredWidth(80);	//
		tableDetalle.getColumnModel().getColumn(6).setPreferredWidth(100);
		
		tableDetalle.setRowHeight(30);
		//registerEnterKey( );
		
		JScrollPane scrollPane = new JScrollPane(tableDetalle);
		
		scrollPane.setBackground(color3);
		scrollPane.getViewport().setBackground(color3);
		
		panel_2 = new JPanel();
		panel_2.setBackground(color3);
		panelNorte.add(panel_2, BorderLayout.WEST);
		
		lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(ViewFacturar.class.getResource("/view/recursos/logo_facturar.png")));
		panel_2.add(lblLogo);
		
		panel_1 = new JPanel();
		panel_1.setLayout(new BorderLayout(0, 0));
		panelNorte.add(panel_1, BorderLayout.CENTER);
		
		
		
		
		panelDatosFactura=new JPanel();
		panel_1.add(panelDatosFactura, BorderLayout.CENTER);
		panelDatosFactura.setBackground(color3);
		
		//panelDatosFactura.setBackground(Color.WHITE);
		panelDatosFactura.setBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "Datos Generales", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		//panelDatosFactura.setBounds(196, 11, 802, 84);
		//panelDatosFactura.setVisible(false);
		panelDatosFactura.setLayout(new GridLayout(0, 6, 10, 0));
		lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Georgia", Font.BOLD, 13));
		panelDatosFactura.add(lblFecha);
		
		lblCodigoCliente = new JLabel("Id Cliente");
		lblCodigoCliente.setFont(new Font("Georgia", Font.BOLD, 13));
		panelDatosFactura.add(lblCodigoCliente);
		
		lblNombreCliente = new JLabel("Nombre Cliente");
		lblNombreCliente.setFont(new Font("Georgia", Font.BOLD, 13));
		panelDatosFactura.add(lblNombreCliente);
		
		
		JLabel lblRtn = new JLabel("R:T:N");
		lblRtn.setFont(new Font("Georgia", Font.BOLD, 13));
		panelDatosFactura.add(lblRtn);
		
		lblContado = new JLabel("Contado");
		lblContado.setFont(new Font("Georgia", Font.BOLD, 13));
		lblContado.setHorizontalAlignment(SwingConstants.CENTER);
		panelDatosFactura.add(lblContado);
		
		lblCredito = new JLabel("Credito");
		lblCredito.setFont(new Font("Georgia", Font.BOLD, 13));
		lblCredito.setHorizontalAlignment(SwingConstants.CENTER);
		panelDatosFactura.add(lblCredito);
		
		txtFechafactura = new JTextField();
		txtFechafactura.setBackground(color4);
		txtFechafactura.setEditable(false);
		panelDatosFactura.add(txtFechafactura);
		txtFechafactura.setColumns(10);
		
		txtIdcliente = new JTextField();
		txtIdcliente.setBackground(color4);
		panelDatosFactura.add(txtIdcliente);
		txtIdcliente.setColumns(5);
		
		txtNombrecliente = new JTextField();
		txtNombrecliente.setBackground(color4);
		txtNombrecliente.setToolTipText("Nombre Cliente");
		panelDatosFactura.add(txtNombrecliente);
		txtNombrecliente.setColumns(20);
		
		txtRtn = new JTextField();
		txtRtn.setBackground(color4);
		panelDatosFactura.add(txtRtn);
		txtRtn.setColumns(10);
		
		rdbtnContado = new JRadioButton("");
		rdbtnContado.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnContado.setSelected(true);
		grupoOpciones.add(rdbtnContado);
		panelDatosFactura.add(rdbtnContado);
		rdbtnCredito = new JRadioButton("");
		rdbtnCredito.setHorizontalAlignment(SwingConstants.CENTER);
		grupoOpciones.add(rdbtnCredito);
		panelDatosFactura.add(rdbtnCredito);
		
		
		
		
		
		
		
		
		panelBuscar= new JPanel();
		panelBuscar.setBackground(color3);
		panel_1.add(panelBuscar, BorderLayout.SOUTH);
		//panelBuscar.setBounds(196, 94, 802, 50);
		//getContentPane().geti
		//panelBuscar.setVisible(false);*/
		
		panelBuscar.setLayout(new GridLayout(2, 3, 7, 1));
		
	
		
		JLabel lblBuscar = new JLabel(" Buscar");
		lblBuscar.setFont(new Font("Georgia", Font.BOLD, 13));
		lblBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		lblBuscar.setVerticalAlignment(SwingConstants.BOTTOM);
		panelBuscar.add(lblBuscar);
		
		
		
		txtBuscar = new JTextField();
		txtBuscar.setForeground(Color.WHITE);
		txtBuscar.setBackground(color1);
		panelBuscar.add(txtBuscar);
		txtBuscar.setColumns(10);
		
	
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		//panelNorte.add(scrollPane);
		//scrollPane.setBounds(196, 144, 802, 326);
		//panelNorte
		
		//this.setTitle("Facturar");
		getContentPane().add(panelAcciones, BorderLayout.WEST);
		panelAcciones.setLayout(new GridLayout(8, 1, -20, 0));
		
		btnBuscar = new BotonBuscar1();
		btnBuscar.setBackground(color1);
		panelAcciones.add(btnBuscar);
		
		btnCobrar = new BotonCobrar();
		btnCobrar.setBackground(color1);
		btnCobrar.setText("F2 Cobrar");
		panelAcciones.add(btnCobrar);
		
		btnCliente = new BotonBuscarClientes();
		btnCliente.setBackground(color1);
		btnCliente.setText("F3 Clientes");
		panelAcciones.add(btnCliente);
		
		btnGuardar = new BotonGuardar();
		btnGuardar.setBackground(color1);
		btnGuardar.setText("F4 Guardar");
		panelAcciones.add(btnGuardar);
		
		btnPendientes = new BotonPendientes();// new JButton("F5 Pendientes");
		btnPendientes.setBackground(color1);
		btnPendientes.setText("F5 Pendientes");
		panelAcciones.add(btnPendientes);
		
		btnCierreCaja = new BotonCierreCaja();// JButton("F6 Cierre");
		btnCierreCaja.setBackground(color1);
		btnCierreCaja.setText("F6 Cierre");
		btnCierreCaja.setHorizontalTextPosition(SwingConstants.CENTER);
		panelAcciones.add(btnCierreCaja);
		
		btnActualizar=new BotonActualizar();
		btnActualizar.setEnabled(false);
		btnActualizar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnActualizar.setBackground(color1);
		btnActualizar.setText("F7 Actualizar");
		panelAcciones.add(btnActualizar);
		//btnActualizar.setVisible(false);
		
		btnCerrar = new BotonCancelar();
		btnCerrar.setBackground(color1);
		btnCerrar.setText("Esc Cerrar");
		panelAcciones.add(btnCerrar);
		
		
		
		
		
		
		//getContentPane().setLayout(null);
		
		Font myFont=new Font("OCR A Extended", Font.PLAIN, 45);
		
		panel = new JPanel();
		panel.setBackground(color3);
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(2, 10, -20, -20));
		//lblImpuesto_1.setBounds(424, 490, 82, 14);
		
		lblSubtotal = new JLabel("SubTotal");
		lblSubtotal.setFont(new Font("Georgia", Font.BOLD, 13));
		lblSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblSubtotal);
		
		JLabel lblDescuento = new JLabel("Descuento");
		lblDescuento.setFont(new Font("Georgia", Font.BOLD, 13));
		lblDescuento.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblDescuento);
		
		lblImpuesto = new JLabel("Impuesto 15");
		lblImpuesto.setFont(new Font("Georgia", Font.BOLD, 13));
		lblImpuesto.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblImpuesto);
		
		JLabel lblImpuesto_1 = new JLabel("Impuesto 18");
		lblImpuesto_1.setFont(new Font("Georgia", Font.BOLD, 13));
		lblImpuesto_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblImpuesto_1);
		
		lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Georgia", Font.BOLD, 13));
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTotal);
		
		
		//lblImpuesto.setBounds(237, 490, 92, 14);
		
		
		txtSubtotal = new JTextField();
		txtSubtotal.setBackground(color4);
		panel.add(txtSubtotal);
		txtSubtotal.setFont(new Font("Dialog", Font.PLAIN, 35));
		txtSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtSubtotal.setText("00");
		txtSubtotal.setEditable(false);
		
		
		txtDescuento = new JTextField();
		txtDescuento.setBackground(color4);
		panel.add(txtDescuento);
		txtDescuento.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDescuento.setEditable(false);
		txtDescuento.setText("00");
		txtDescuento.setFont(new Font("Dialog", Font.PLAIN, 35));
		
		
		
		txtImpuesto = new JTextField();
		txtImpuesto.setBackground(color4);
		panel.add(txtImpuesto);
		txtImpuesto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtImpuesto.setFont(new Font("Dialog", Font.PLAIN, 35));
		txtImpuesto.setText("00");
		txtImpuesto.setEditable(false);
		
		
		
		txtImpuesto18 = new JTextField();
		txtImpuesto18.setBackground(color4);
		panel.add(txtImpuesto18);
		txtImpuesto18.setText("00");
		txtImpuesto18.setHorizontalAlignment(SwingConstants.RIGHT);
		txtImpuesto18.setFont(new Font("Dialog", Font.PLAIN, 35));
		txtImpuesto18.setEditable(false);
		//txtImpuesto18.setBounds(424, 506, 171, 44);
		//txtImpuesto18.setColumns(10);
		//lblDescuento.setBounds(605, 490, 92, 14);
		
		
		
		
		
		
		txtTotal = new JTextField();
		txtTotal.setBackground(color4);
		panel.add(txtTotal);
		txtTotal.setForeground(Color.RED);
		txtTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTotal.setFont(new Font("Dialog", Font.PLAIN, 35));
		txtTotal.setText("00");
		txtTotal.setEditable(false);
		//txtTotal.setBounds(778, 506, 220, 44);
		txtTotal.setColumns(8);
		
		
		
		
		//txtDescuento.setBounds(605, 506, 163, 44);
		//txtDescuento.setColumns(10);
		
		
		
		
		
		
		
		//setSize(1024, 600);
		setSize(this.getToolkit().getScreenSize());
		
		//this.setPreferredSize(new Dimension(1024, 600));
		//this.setResizable(false);
		//centrar la ventana en la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//this.pack();
		
		/*super(view,"Facturar",Dialog.ModalityType.DOCUMENT_MODAL);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewFacturar.class.getResource("/view/recursos/logo-admin-tool1.png")));
		panelAcciones=new JPanel();
		panelAcciones.setBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "Opciones", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelAcciones.setBounds(20, 11, 178, 459);
		panelAcciones.setLayout(null);
		//panelAcciones.setVisible(false);
		
		
		//this.setTitle("Facturar");
		getContentPane().add(panelAcciones);
		
		btnBuscar = new BotonBuscar1();
		btnBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		btnBuscar.setBounds(10, 24,158, 38);
		panelAcciones.add(btnBuscar);
		//btnBuscar.getInputMap().put(KeyStroke.getKeyStroke("F1"), sumar());
		
		btnCliente = new BotonBuscarClientes();
		btnCliente.setText("F3 Clientes");
		btnCliente.setHorizontalAlignment(SwingConstants.LEFT);
		btnCliente.setBounds(10, 148, 158, 38);
		panelAcciones.add(btnCliente);
		
		btnCobrar = new BotonCobrar();
		btnCobrar.setText("F2 Cobrar");
		btnCobrar.setHorizontalAlignment(SwingConstants.LEFT);
		btnCobrar.setBounds(10, 86, 158, 38);
		
		panelAcciones.add(btnCobrar);
		
		btnActualizar=new BotonActualizar();
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
		btnCierreCaja.setHorizontalAlignment(SwingConstants.LEFT);
		btnCierreCaja.setIcon(new ImageIcon(ViewFacturar.class.getResource("/view/recursos/caja.png")));
		btnCierreCaja.setBounds(10, 334, 158, 38);
		panelAcciones.add(btnCierreCaja);
		
		btnPendientes = new JButton("F5 Pendientes");
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
		cbxEmpleados.setModel(modeloEmpleado);//comentar para moder ver la vista de dise�o
		cbxEmpleados.setBounds(581, 48, 199, 20);
		panelDatosFactura.add(cbxEmpleados);
		
		
		tableDetalle = new JTable();
		modeloTabla=new TablaModeloFactura();
		tableDetalle.setModel(modeloTabla);
		
		RenderizadorTablaFactura renderizador = new RenderizadorTablaFactura();
		tableDetalle.setDefaultRenderer(String.class, renderizador);
		//tableDetalle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tableDetalle.getColumnModel().getColumn(0).setPreferredWidth(100);     //Tama�o de las columnas de las tablas
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
		this.pack();*/
		
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
	public TablaModeloFactura getModeloTabla(){
		return modeloTabla;
	}
	public JTable getTableDetalle(){
		return tableDetalle;
	}
	public JTextField getTxtBuscar(){
		return txtBuscar;
	}
	
	public JTextField getTxtFechafactura(){
		return txtFechafactura;
	}
	public void conectarContralador(CtlFacturar c){
		
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
		
		txtBuscar.addActionListener(c);
		txtBuscar.setActionCommand("BUSCARARTICULO2");
		
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
		txtBuscar.addKeyListener(c);
		//txtBuscar.
		//cbxEmpleados.addKeyListener(c);
		
		//cbxEmpleados.addKeyListener(c);
		//KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		//manager.addKeyEventDispatcher( c);
		//this.addWindowListener(c);
		//this.addw
	}
}
