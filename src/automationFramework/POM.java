package automationFramework;

import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import Utilitarios.BD;
import pagesObjects.AgendarCita_Page;
import pagesObjects.Index_Page;

public class POM
{
	private static WebDriver driver = null;
	private static String baseUrl;

	public static void main(String[] args) throws Exception
	{
		BD conexion = new BD();

		conexion.conectar();

		ResultSet RS = conexion.Consulta("SELECT * FROM CasosPrueba");

		System.setProperty("webdriver.gecko.driver", "C:/drivers/geckodriver.exe");
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		dc.setCapability("marionette", true);

		driver = new FirefoxDriver(dc);
		baseUrl = "http://automatizacion.herokuapp.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl + "/dzaraza/");
		
		while(RS.next())
		{
			Index_Page.AgendarCita(driver).click();

			AgendarCita_Page.Calendario(driver).sendKeys(RS.getString("FechaCita"));
			AgendarCita_Page.DocPaciente(driver).sendKeys(RS.getString("Id_Paciente"));
			AgendarCita_Page.DocMedico(driver).sendKeys(RS.getString("Id_Medico"));
			AgendarCita_Page.Observaciones(driver).sendKeys(RS.getString("Observaciones"));
			AgendarCita_Page.Btn_Guardar(driver).click();

			Thread.sleep(10000);

			String Resultado = AgendarCita_Page.PanelTitulo(driver).getText();
			String Panel = AgendarCita_Page.PanelRespuesta(driver).getText();
			
			if(Resultado.equals((RS.getString("ResultadoEsperado"))))
			{
				System.out.println("Ejecución Caso Prueba Nro: " + RS.getString("Caso_Prueba"));
				System.out.println("Resultado: " + Resultado);
				System.out.println("Validación: Caso de Prueba Exitoso");
				System.out.println(Panel);
				System.out.println("---------------------------------------------");
			}
			else
			{
				System.out.println("Ejecución Caso Prueba Nro: " + RS.getString("Caso_Prueba"));
				System.out.println("Resultado: " + Resultado);
				System.out.println("Validación: Caso de Prueba Fallido");
				System.out.println(Panel);
				System.out.println("---------------------------------------------");
			}
			
			AgendarCita_Page.Inicio(driver).click();
		}
		driver.quit();
		conexion.desconectar();

	}

}