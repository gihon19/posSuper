package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;

import modelo.AbstractJasperReports;
import view.BdConfig;

public class CtlBdConfig implements ActionListener {
	private BdConfig view=null;
	/*private Properties props;
	private Properties propsout;
	private InputStream file=null;
	
	private OutputStream output = null;
	private URL url=null;
	private File archivoOut=null;*/
	
	public CtlBdConfig(BdConfig v){
		
		view=v;
		
		
		/*propsout=new Properties();
		props = new Properties();
		
		file=AbstractJasperReports.class.getResourceAsStream("/config/db.config"); 
	     try {
			props.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	     
	     url=AbstractJasperReports.class.getResource("/config/db.config"); 
	     archivoOut=new File(url.getPath());
	     try {
	     output=new FileOutputStream(url.getPath());
	     } catch (FileNotFoundException e1) {
	     // TODO Auto-generated catch block
	     e1.printStackTrace();
	     } 
	     
		*/
		
	    loadParams();
		view.conectarControlador(this);
		
		view.setVisible(true);
		
	}

	/*private void cargarDatos() {
		// TODO Auto-generated method stub
	
	     
	     view.getTxtUrl().setText(props.getProperty("MYSQL_DB_URL"));
	     view.getTxtUser().setText(props.getProperty("MYSQL_DB_USERNAME"));
	     view.getTxtPwd().setText(props.getProperty("MYSQL_DB_PASSWORD"));
	     view.getTxtDataBase().setText(props.getProperty("MYSQL_DB"));
		
	}*/

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String comando=e.getActionCommand();
		
		switch (comando ){
		case "CANCELAR":
			view.setVisible(false);
			break;
		case "GUARDAR":
			 guardar();
			break;
		
		}
		
	}
	
	private void guardar() {
		// TODO Auto-generated method stub
		

		
		
		
		
		view.setVisible(false);
		
		
	}
	
	
	
	public void loadParams() {
		URL url=AbstractJasperReports.class.getResource("/config/db.config");
		
		FileInputStream in = null;
		try {
			in = new FileInputStream(url.getPath());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		view.getTxtUrl().setText(props.getProperty("MYSQL_DB_URL"));
	     view.getTxtUser().setText(props.getProperty("MYSQL_DB_USERNAME"));
	     view.getTxtPwd().setText(props.getProperty("MYSQL_DB_PASSWORD"));
	     view.getTxtDataBase().setText(props.getProperty("MYSQL_DB"));
		
		
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	    /*Properties props = new Properties();
	    InputStream is = null;
	 
	    // First try loading from the current directory
	    try {
	    	
	        File f = new File(url.getPath());
	        is = new FileInputStream( f );
	    }
	    catch ( Exception e ) { is = null; }
	 
	    try {
	        if ( is == null ) {
	            // Try loading from classpath
	            is = AbstractJasperReports.class.getResourceAsStream("/config/db.config"); 
	        }
	 
	        // Try loading properties from the file (if found)
	        props.load( is );
	    }
	    catch ( Exception e ) { }*/
	 
	    
	}

}
