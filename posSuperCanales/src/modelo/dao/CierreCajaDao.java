package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.CierreCaja;
import modelo.Conexion;

public class CierreCajaDao {
	
	private Conexion conexion=null;
	private PreparedStatement seleccionarCierre=null;
	private PreparedStatement registrarCierre=null;
	public int idUltimoRequistro=0;
	
	public CierreCajaDao(Conexion conn){
		//Class(Conexion);
		conexion =conn;
	}
	
	public boolean registrarCierre(){
		boolean resultado=false;
		 Connection con = null;
		 
		 ResultSet rs=null;
		 
		 //SE CONSIGUE EL ITEM PARA EL CIERRE DE CAJA
		 CierreCaja unCierre=this.getCierre();
		 String sql= "INSERT INTO cierre_caja("
					+ "fecha,"
					+ "factura_inicial,"
					+ "factura_final,"
					+ "efectivo,"
					+ "creditos,"
					+ "totalventa,"
					+ "tarjeta,"
					+ "usuario,"
					+ "isv15,"
					+ "isv18)"
					+ " VALUES (now(),?,?,?,?,?,?,?,?,?)";
		 if(unCierre!=null)
		 try {
				con = conexion.getPoolConexion().getConnection();
				registrarCierre=
						con.prepareStatement(sql);
				
				registrarCierre.setInt(1,unCierre.getNoFacturaInicio() );
				registrarCierre.setInt(2,unCierre.getNoFacturaFinal() );
				registrarCierre.setBigDecimal(3, unCierre.getEfectivo());
				registrarCierre.setBigDecimal(4, unCierre.getCredito());
				registrarCierre.setBigDecimal(5, unCierre.getTotal());
				registrarCierre.setBigDecimal(6, unCierre.getTarjeta());
				registrarCierre.setString(7, unCierre.getUsuario());
				registrarCierre.setBigDecimal(8, unCierre.getIsv15());
				registrarCierre.setBigDecimal(9, unCierre.getIsv18());
				
				
				
				
				registrarCierre.executeUpdate();//se guarda el encabezado de la factura
				
				
				rs=registrarCierre.getGeneratedKeys(); //obtengo las ultimas llaves generadas
				while(rs.next()){
					this.idUltimoRequistro=rs.getInt(1);
					//this.setIdArticuloRegistrado(rs.getInt(1));
				}
				resultado=true;
				
		 }catch (SQLException e) {
				e.printStackTrace();
				resultado=false;
			}
		finally
		{
			try{
				
				if(rs != null) rs.close();
				if(registrarCierre != null)registrarCierre.close();
				if(con != null) con.close();
             
				
				} // fin de try
				catch ( SQLException excepcionSql )
				{
					excepcionSql.printStackTrace();
					//conexion.desconectar();
				} // fin de catch
		} // fin de finally
		
		
		return resultado;
	}
	
	
	
	
	
public CierreCaja getCierreUltimoUser(){
		
		//se crear un referencia al pool de conexiones
		//DataSource ds = DBCPDataSourceFactory.getDataSource("mysql");
		
		
        Connection con = null;
        
    	//String sql="select * from cierre where usuario = ?";
    	
    	String sql2="SELECT * FROM cierre_caja WHERE cierre_caja.usuario = ? ORDER BY cierre_caja.idCierre DESC LIMIT 1";
        //Statement stmt = null;
    	CierreCaja unaCierre=new CierreCaja();
		
		ResultSet res=null;
		
		boolean existe=false;
		try {
			con = conexion.getPoolConexion().getConnection();
			
			seleccionarCierre = con.prepareStatement(sql2);
			
			seleccionarCierre.setString(1, conexion.getUsuarioLogin().getUser());
			res = seleccionarCierre.executeQuery();
			while(res.next()){
				
				existe=true;
				
				unaCierre.setNoFacturaInicio(res.getInt("factura_inicial"));
				unaCierre.setNoFacturaFinal(res.getInt("factura_final"));
				unaCierre.setEfectivo(res.getBigDecimal("efectivo"));
				unaCierre.setCredito(res.getBigDecimal("creditos"));
				unaCierre.setTarjeta(res.getBigDecimal("tarjeta"));
				
				unaCierre.setIsv15(res.getBigDecimal("isv15"));
				unaCierre.setIsv18(res.getBigDecimal("isv18"));
				
				unaCierre.setTotal(res.getBigDecimal("totalventa"));
				unaCierre.setUsuario(res.getString("usuario"));//.setTotal(res.getBigDecimal("total"));
			
			 }
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		finally
		{
			try{
				
				if(res != null) res.close();
                if(seleccionarCierre != null)seleccionarCierre.close();
                if(con != null) con.close();
                
				
				} // fin de try
				catch ( SQLException excepcionSql )
				{
					excepcionSql.printStackTrace();
					//conexion.desconectar();
				} // fin de catch
		} // fin de finally
		
		return unaCierre;
			/*if (existe) {
				return unaCierre;
			}
			else return null;*/
		
	}
	public CierreCaja getCierre(){
		
		//se crear un referencia al pool de conexiones
		//DataSource ds = DBCPDataSourceFactory.getDataSource("mysql");
		
		
        Connection con = null;
        //SE CONSIGUE EL ITEM PARA EL CIERRE DE CAJA
		 CierreCaja ultimoCierreUser=this.getCierreUltimoUser();
        
    	String sql="select * from cierre where usuario = ?";
    	
    	String sql2=""
    			+ " select "
    			+ " date_format(now(),'%d/%m/%Y %h:%i:%s') AS `fecha`, "
    			+ "	("
    			+ "		select "
    			+ "			`encabezado_factura`.`numero_factura` "
    			+ "		from "
    			+ "			`encabezado_factura` "
    			+ "		WHERE "
    			+ "			encabezado_factura.usuario ='"
    			+ 		conexion.getUsuarioLogin().getUser()+"'"
    			+ "		order by "
    			+ "			`encabezado_factura`.`numero_factura` desc "
    			+ "		limit 1"
    			+ "	) AS `factura_ultima`,"
    			+ "	("
    			+ "		select "
    			+ "			sum("
    			+ "				`encabezado_factura`.`total` "
    			+ "			) AS `total_efectivo` "
    			+ "		from "
    			+ "			`encabezado_factura` "
    			+ "		where "
    			+ "			("
    			+ "				("
    			+ "					`encabezado_factura`.`tipo_factura` = 1"
    			+ "				) "
    			+ "				and "
    			+ "				("
    			+ "					`encabezado_factura`.`estado_factura` = 'ACT'"
    			+ "				) "
    			+ "				and "
    			+ "				("
    			+ "					`encabezado_factura`.`numero_factura` > "+ultimoCierreUser.getNoFacturaFinal()
    			+ "				) "
    			+ "				and "
    			+ "				("
    			+ "					`encabezado_factura`.`numero_factura` <= `factura_ultima`"
    			+ "				) "
    			+ "				and "
    			+ "				("
    			+ "					`encabezado_factura`.`tipo_pago` = 1"
    			+ "				)"
    			+ "				and "
    			+ "				( "
    			+ "					encabezado_factura.usuario ='"
    			+ 					conexion.getUsuarioLogin().getUser()+"'"
    			+ "				)"
    			+ "			)"
    			+ "	) AS `total_efectivo`,"
    			+ "	("
    			+ "		select "
    			+ "			sum("
    			+ "				`encabezado_factura`.`total` "
    			+ "			) AS `total_efectivo` "
    			+ "		from "
    			+ "			`encabezado_factura` "
    			+ "		where "
    			+ "			("
    			+ "				("
    			+ "					`encabezado_factura`.`tipo_factura` = 1"
    			+ "				) "
    			+ "				and ("
    			+ "					`encabezado_factura`.`estado_factura` = 'ACT'"
    			+ "				) "
    			+ "				and ("
    			+ "					`encabezado_factura`.`numero_factura` > "+ultimoCierreUser.getNoFacturaFinal()
    			+ "				) "
    			+ "				and ("
    			+ "					`encabezado_factura`.`numero_factura` <= `factura_ultima`"
    			+ "				) "
    			+ "				and ("
    			+ "					`encabezado_factura`.`tipo_pago` = 2"
    			+ "				) "
    			+ "				and("
    			+ "					encabezado_factura.usuario ='"
    			+ 				conexion.getUsuarioLogin().getUser()+"'"
    			+ "				)"
    			+ "			)"
    			+ "	) AS `total_tarjeta`,"
    			+ "	("
    			+ "		select "
    			+ "			sum("
    			+ "				`encabezado_factura`.`total` "
    			+ "			) AS `total_efectivo` "
    			+ "		from "
    			+ "			`encabezado_factura` "
    			+ "		where "
    			+ "			("
    			+ "				("
    			+ "					`encabezado_factura`.`tipo_factura` = 2"
    			+ "				) "
    			+ "				and ("
    			+ "					`encabezado_factura`.`estado_factura` = 'ACT'"
    			+ "				) "
    			+ "				and ("
    			+ "					`encabezado_factura`.`numero_factura` > "+ultimoCierreUser.getNoFacturaFinal()
    			+ "				) "
    			+ "				and ("
    			+ "					`encabezado_factura`.`numero_factura` <= `factura_ultima`"
    			+ "				) "
    			+ "				and( "
    			+ "					encabezado_factura.usuario = '"
    			+ 						conexion.getUsuarioLogin().getUser()+"'"
    			+ "				)"
    			+ "			)"
    			+ ") AS `total_credito`,"
    			+ "("
    			+ "		SELECT "
    			+ "			sum( "
				+ "				`encabezado_factura`.`impuesto` "
				+"			) AS `total_isv15` "
				+"		FROM "
				+"			`encabezado_factura` "
				+"		WHERE "
				+"			( "
				+"				( "
				+"					`encabezado_factura`.`tipo_factura` = 1 "
				+"				) "
				+"				AND ( "
				+"					`encabezado_factura`.`estado_factura` = 'ACT' "
				+"				) "
				+"				AND ( "
				+"					`encabezado_factura`.`numero_factura` > "+ultimoCierreUser.getNoFacturaFinal()
				+"				) "
				+"				AND ( "
				+"					`encabezado_factura`.`numero_factura` <= `factura_ultima` "
				+				")"
				+"				AND ( "
				+"					encabezado_factura.usuario = '"
				+ 					conexion.getUsuarioLogin().getUser()+"'"
				+"				) "
				+"			) "
				+") AS `total_isv15`, "
    			+ "("
    			+"		SELECT "
    			+"			sum( "
				+"				`encabezado_factura`.`isv18` "
				+"			) AS `total_isv18` "
				+"		FROM "
				+"			`encabezado_factura` "
				+"		WHERE  "
				+"		( "
				+"				( "
				+"					`encabezado_factura`.`tipo_factura` = 1 "
				+"				) "
				+"			AND ( "
				+"				`encabezado_factura`.`estado_factura` = 'ACT' "
				+"			) "
				+"			AND ( "
				+"				`encabezado_factura`.`numero_factura` > "+ultimoCierreUser.getNoFacturaFinal()
				+"			) "
				+"			AND ( "
				+"				`encabezado_factura`.`numero_factura` <= `factura_ultima` "
				+"			) "
				+"			AND ( "
				+"					encabezado_factura.usuario = '"
				+ 				conexion.getUsuarioLogin().getUser()+"'"
				+"			) "
				+"		) "
				+") AS `total_isv18`, "
    			+ "	("
    			+ "		select "
    			+ "			sum("
    			+ "				`encabezado_factura`.`total` "
    			+ "			) AS `total_efectivo` "
    			+ "			from "
    			+ "				`encabezado_factura` "
    			+ "			where "
    			+ "			("
    			+ "				("
    			+ "					`encabezado_factura`.`estado_factura` = 'ACT'"
    			+ "				) "
    			+ "				and ("
    			+ "					`encabezado_factura`.`numero_factura` > "+ultimoCierreUser.getNoFacturaFinal()
    			+ "				) "
    			+ "				and ("
    			+ "					`encabezado_factura`.`numero_factura` <= `factura_ultima`"
    			+ "				) "
    			+ "				and( "
    			+ "					encabezado_factura.usuario = '"
    			+ 					conexion.getUsuarioLogin().getUser()+"'"
    			+ "				)"
    			+ "			)"
    			+ "	) AS `total` "
    			+ "	from "
    			+ "		`encabezado_factura` "
    			+ "	where ((`encabezado_factura`.`numero_factura` > (select `cierre_caja`.`factura_final` from `cierre_caja` order by `cierre_caja`.`idCierre` desc limit 1)) and (`encabezado_factura`.`numero_factura` <= (select `encabezado_factura`.`numero_factura` from `encabezado_factura` order by `encabezado_factura`.`numero_factura` desc limit 1))) limit 1; ";
        //Statement stmt = null;
    	CierreCaja unaCierre=new CierreCaja();
		
		ResultSet res=null;
		
		boolean existe=false;
		try {
			con = conexion.getPoolConexion().getConnection();
			
			seleccionarCierre = con.prepareStatement(sql2);
			
			//seleccionarCierre.setString(1, conexion.getUsuarioLogin().getUser());
			res = seleccionarCierre.executeQuery();
			while(res.next()){
				
				existe=true;
				
				unaCierre.setNoFacturaInicio(ultimoCierreUser.getNoFacturaFinal());
				unaCierre.setNoFacturaFinal(res.getInt("factura_ultima"));
				unaCierre.setEfectivo(res.getBigDecimal("total_efectivo"));
				unaCierre.setCredito(res.getBigDecimal("total_credito"));
				unaCierre.setTarjeta(res.getBigDecimal("total_tarjeta"));
				
				unaCierre.setIsv15(res.getBigDecimal("total_isv15"));
				unaCierre.setIsv18(res.getBigDecimal("total_isv18"));
				
				unaCierre.setTotal(res.getBigDecimal("total"));
				unaCierre.setUsuario(conexion.getUsuarioLogin().getUser());//.setTotal(res.getBigDecimal("total"));
			
			 }
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		finally
		{
			try{
				
				if(res != null) res.close();
                if(seleccionarCierre != null)seleccionarCierre.close();
                if(con != null) con.close();
                
				
				} // fin de try
				catch ( SQLException excepcionSql )
				{
					excepcionSql.printStackTrace();
					//conexion.desconectar();
				} // fin de catch
		} // fin de finally
		
		return unaCierre;
			/*if (existe) {
				return unaCierre;
			}
			else return null;*/
		
	}
	
	public List<CierreCaja> todos() {
		List<CierreCaja> cierres =new ArrayList<CierreCaja>();
		
		ResultSet res=null;
		
		Connection conn=null;
		
		boolean existe=false;
		
		try{
			conn=conexion.getPoolConexion().getConnection();
			seleccionarCierre=conn.prepareStatement("SELECT * FROM v_cierre_caja ORDER BY idCierre Desc");
			res = seleccionarCierre.executeQuery();
			while(res.next()){
				existe=true;
				CierreCaja unaCierre=new CierreCaja();
				unaCierre.setFecha(res.getString("fecha"));
				unaCierre.setId(res.getInt("idCierre"));
				unaCierre.setNoFacturaInicio(res.getInt("factura_inicial"));
				unaCierre.setNoFacturaFinal(res.getInt("factura_final"));
				unaCierre.setEfectivo(res.getBigDecimal("efectivo"));
				unaCierre.setCredito(res.getBigDecimal("creditos"));
				unaCierre.setTarjeta(res.getBigDecimal("tarjeta"));
				
				unaCierre.setIsv15(res.getBigDecimal("isv15"));
				unaCierre.setIsv18(res.getBigDecimal("isv18"));
				
				unaCierre.setTotal(res.getBigDecimal("totalventa"));
				unaCierre.setUsuario(res.getString("usuario"));
				
				
				cierres.add(unaCierre);
				
				
			}
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
			System.out.println(e);
	}
	finally
	{
		try{
			if(res != null) res.close();
	        if(seleccionarCierre != null)seleccionarCierre.close();
	        if(conn != null) conn.close();
			
			} // fin de try
			catch ( SQLException excepcionSql )
			{
				excepcionSql.printStackTrace();
				//conexion.desconectar();
			} // fin de catch
	} // fin de finally
		
		
		if (existe) {
			return cierres;
		}
		else return null;
	}

	public List<CierreCaja> porFecha(String date, String date2) {
		List<CierreCaja> cierres =new ArrayList<CierreCaja>();
		
		ResultSet res=null;
		
		Connection conn=null;
		
		boolean existe=false;
		
		try{
			conn=conexion.getPoolConexion().getConnection();
			seleccionarCierre=conn.prepareStatement("SELECT * FROM v_cierre_caja where fecha2 BETWEEN ? and ?");
			seleccionarCierre.setString(1, date);
			seleccionarCierre.setString(2, date2);
			res = seleccionarCierre.executeQuery();
			while(res.next()){
				existe=true;
				CierreCaja unaCierre=new CierreCaja();
				unaCierre.setFecha(res.getString("fecha"));
				unaCierre.setId(res.getInt("idCierre"));
				unaCierre.setNoFacturaInicio(res.getInt("factura_inicial"));
				unaCierre.setNoFacturaFinal(res.getInt("factura_final"));
				unaCierre.setEfectivo(res.getBigDecimal("efectivo"));
				unaCierre.setCredito(res.getBigDecimal("creditos"));
				unaCierre.setTarjeta(res.getBigDecimal("tarjeta"));
				
				unaCierre.setIsv15(res.getBigDecimal("isv15"));
				unaCierre.setIsv18(res.getBigDecimal("isv18"));
				
				unaCierre.setTotal(res.getBigDecimal("totalventa"));
				unaCierre.setUsuario(res.getString("usuario"));
				
				
				cierres.add(unaCierre);
				
				
			}
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error, no se conecto");
			System.out.println(e);
	}
	finally
	{
		try{
			if(res != null) res.close();
	        if(seleccionarCierre != null)seleccionarCierre.close();
	        if(conn != null) conn.close();
			
			} // fin de try
			catch ( SQLException excepcionSql )
			{
				excepcionSql.printStackTrace();
				//conexion.desconectar();
			} // fin de catch
	} // fin de finally
		
		
		if (existe) {
			return cierres;
		}
		else return null;
	}

}
