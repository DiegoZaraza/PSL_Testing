package Utilitarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BD
{
	private Connection conexion; 												// Abstrae una conexion a la base de datos
	private String usuario = "sa"; 										// usuario con permisos para conectarse a Base de datos
	private String password = "AutomatiSA2016"; 							// contrasena del usuario que se puede conectar a la base de datos
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 	// Clase del Driver de jConnector
	private String baseDatos = "jdbc:sqlserver://W7SIDD001\\SQLEXPRESS";		//cadena de coneccion
	private String nombreBD = "databaseName = PSL";					//nombre de la Base de Datos 
	private static BD instancia;

	/* Crea una nueva instancia de  Conexion */

	public static BD getInstancia ()
	{
		if(BD.instancia == null)
		{
			BD.instancia = new BD();
		}
		return instancia;
	}

	/* Metodo que se encarga de conectar a la base de datos*/

	public void conectar()throws Exception 
	{
		//si la conexión es null nos conectamos
		if(this.getConexion() != null){
			return;
		}
		else 
			if(this.getConexion() == null)
			{
				try 
				{
					Class.forName(this.getDriver()) ; // obtiene una instancia de la clase Driver
					// establece la conexion con el Diver jconector y este a su vez con la base de datos
					this.setConexion(DriverManager.getConnection(this.getBaseDatos() + ";" + this.getNombreBD(),this.getUsuario(), this.getPassword()));
				} 
				catch (SQLException ex) 
				{
					throw new Exception("ERROR AL CONECTARSE CON LA BASE DE DATOS");
				} 
				catch (ClassNotFoundException ex) 
				{
					throw new Exception("Clase no encontrada");
				}
			}
	}
	
	public ResultSet Consulta (String Sentencia) throws Exception {

		Statement st = getConexion().createStatement();
		ResultSet rs = st.executeQuery(Sentencia); 
		return rs;

	}
	
	public void insertarSQL(String consulta)
	{
		try
		{
			Statement stamt = getConexion().createStatement();
			stamt.executeUpdate(consulta);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/** desconecta de la base de datos */
	public void desconectar()throws Exception
	{
		if(!this.getConexion().isClosed())
			this.setConexion(null);
	}

	/*Metodos getter y setter*/
	public Connection getConexion() 
	{
		return conexion;
	}
	public void setConexion(Connection conexion) 
	{
		this.conexion = conexion;
	}
	public String getUsuario() 
	{
		return usuario;
	}
	public String getPassword() 
	{
		return password;
	}
	public String getDriver() 
	{
		return driver;
	}
	public String getBaseDatos() 
	{
		return baseDatos;
	}
	public String getNombreBD() 
	{
		return nombreBD;
	}
}
