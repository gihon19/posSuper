package view;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;

import com.toedter.calendar.JMonthChooser;
import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.components.JSpinField;
import com.toedter.calendar.JYearChooser;

import controlador.CtlFiltroRepDei;

public class ViewFiltroReportDei extends  JDialog {
	private JButton btnBuscar;
	private JMonthChooser monthChooser;
	private JYearChooser yearChooser;

	public ViewFiltroReportDei(Window view) {
		
		super(view,"Filtro reporte DEI",Dialog.ModalityType.DOCUMENT_MODAL);
		getContentPane().setLayout(null);
		
		btnBuscar = new JButton("Ver Reporte");
		btnBuscar.setBounds(10, 52, 168, 49);
		getContentPane().add(btnBuscar);
		
		monthChooser = new JMonthChooser();
		monthChooser.setBounds(10, 11, 105, 30);
		getContentPane().add(monthChooser);
		
		yearChooser = new JYearChooser();
		yearChooser.setBounds(131, 11, 47, 30);
		getContentPane().add(yearChooser);
		
		this.setPreferredSize(new Dimension(212, 264));
		this.setSize(204, 150);
		// TODO Auto-generated constructor stub
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	public JYearChooser getAnio(){
		return yearChooser;
	}
	public JMonthChooser getMes(){
		return monthChooser;
	}
	
	public void conectarCtl(CtlFiltroRepDei c){
		btnBuscar.addActionListener( c);
		btnBuscar.setActionCommand("GENERAR");
	}
}
