package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import view.botones.BotonActualizar;
import view.botones.BotonCancelar;
import view.botones.BotonGuardar;
import view.rendes.PanelPadre;
import controlador.CtlCliente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class ViewCrearCliente extends JDialog{
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtMovil;
	private JTextField txtRtn;
	
	private BotonCancelar btnCancelar;
	private BotonActualizar btnActualizar;
	private BotonGuardar btnGuardar;
	private JTextField txtLimiteCredito;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	
	public ViewCrearCliente() {
		
	
	
		
		setTitle("Crear Cliente");
		
		this.setSize(365,522);
		getContentPane().setLayout(null);
		
		JPanel JplPrincipal = new PanelPadre();
		JplPrincipal.setBounds(0, 0, 375, 500);
		getContentPane().add(JplPrincipal);
		JplPrincipal.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(31, 60, 60, 14);
		JplPrincipal.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(31, 78, 311, 32);
		JplPrincipal.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setBounds(31, 114, 64, 14);
		JplPrincipal.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(31, 126, 311, 32);
		JplPrincipal.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(31, 168, 60, 14);
		JplPrincipal.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(31, 180, 311, 32);
		JplPrincipal.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		JLabel lblMovil = new JLabel("Movil:");
		lblMovil.setBounds(31, 222, 64, 14);
		JplPrincipal.add(lblMovil);
		
		txtMovil = new JTextField();
		txtMovil.setBounds(31, 234, 311, 32);
		JplPrincipal.add(txtMovil);
		txtMovil.setColumns(10);
		
		JLabel lblRtn = new JLabel("RTN:");
		lblRtn.setBounds(31, 276, 60, 14);
		JplPrincipal.add(lblRtn);
		
		txtRtn = new JTextField();
		txtRtn.setBounds(31, 294, 311, 32);
		JplPrincipal.add(txtRtn);
		txtRtn.setColumns(10);
		
		// botones de accion
		btnCancelar = new BotonCancelar();
		btnCancelar.setLocation(193, 409);
		JplPrincipal.add(btnCancelar);
		
		btnGuardar = new BotonGuardar();
		btnGuardar.setLocation(31, 409);
		JplPrincipal.add(btnGuardar);
		
		btnActualizar=new BotonActualizar();
		btnActualizar.setLocation(31, 409);
		JplPrincipal.add(btnActualizar);
		
		JLabel lblLimiteDeCredito = new JLabel("Limite de credito");
		lblLimiteDeCredito.setBounds(31, 337, 121, 14);
		JplPrincipal.add(lblLimiteDeCredito);
		
		txtLimiteCredito = new JTextField();
		txtLimiteCredito.setColumns(10);
		txtLimiteCredito.setBounds(31, 355, 311, 32);
		JplPrincipal.add(txtLimiteCredito);
		
		lblCodigo = new JLabel("Codigo:");
		lblCodigo.setBounds(31, 6, 60, 14);
		JplPrincipal.add(lblCodigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setEditable(false);
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(31, 24, 311, 32);
		JplPrincipal.add(txtCodigo);
		btnActualizar.setVisible(false);
		
		setResizable(false);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		
	}
	public JTextField getTxtCodigo(){
		return txtCodigo;
	}
	public JTextField getTxtNombre(){
		return txtNombre;
	}
	public  JTextField getTxtDireccion(){
		return  txtDireccion;
	}
	public JTextField getTxtTelefono(){
		return txtTelefono;
	}
	public JTextField getTxtMovil(){
		return txtMovil;
	}
	public JTextField getTxtRtn(){
		return txtRtn;
	}
	public JTextField getTxtLimitecredito() {
		// TODO Auto-generated method stub
		return txtLimiteCredito;
	}
	public BotonActualizar getBtnActualizar(){
		return btnActualizar;
	}
	public BotonGuardar getBtnGuardar(){
		return btnGuardar;
	}
	public void conectarControlador(CtlCliente c){
		
		btnCancelar.addActionListener(c);
		btnCancelar.setActionCommand("CANCELAR");
		
		btnGuardar.addActionListener(c);
		btnGuardar.setActionCommand("GUARDAR");
		
		btnActualizar.addActionListener(c);
		btnActualizar.setActionCommand("ACTUALIZAR");
	}
	public void configActualizar() {
		// TODO Auto-generated method stub
		this.btnActualizar.setVisible(true);
		this.btnGuardar.setVisible(false);
		
	}
}
