package automationFramework;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.opera.OperaDriver;

import com.itextpdf.text.DocumentException;

import Utilitarios.BD;
import Utilitarios.createPDF;
import pagesObjects.AgendarCita_Page;
import pagesObjects.Index_Page;

public class POM
{
	private static WebDriver driver = null;
	private static String baseUrl;

	public static void main(String[] args) throws DocumentException
	{
		BD conexion = new BD();
		createPDF pdf = new createPDF();

		try
		{
			Object options[] = { "Firefox", "Chrome", "Safari", "Internet Explorer", "Opera" };

			int Opcion = JOptionPane.showOptionDialog(null, "Seleccionar Navegador", "Seleccionar", JOptionPane.DEFAULT_OPTION,
			        JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

			pdf.createPdf();

			conexion.conectar();

			ResultSet RS = conexion.Consulta("SELECT * FROM CasosPrueba");

			while (RS.next())
			{
				if (Opcion == 0)
					driver = new FirefoxDriver();
				else if (Opcion == 1)
				{
					System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver.exe");
					driver = new ChromeDriver();
				}
				else if (Opcion == 2)
				{
					System.setProperty("webdriver.safari.driver", "C:/drivers/SafariDriver.safariextz");
					driver = new SafariDriver();
				}
				else if (Opcion == 3)
				{
					System.setProperty("webdriver.ie.driver", "C:/drivers/IEDriverServer.exe");
					driver = new InternetExplorerDriver();
				}
				else
				{
					System.setProperty("webdriver.opera.driver", "C:/drivers/operadriver.exe");
					driver = new OperaDriver();
				}

				baseUrl = "http://automatizacion.herokuapp.com";
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.get(baseUrl + "/dzaraza/");

				Index_Page.AgendarCita(driver).click();

				AgendarCita_Page.Calendario(driver).sendKeys(RS.getString("FechaCita"));
				AgendarCita_Page.DocPaciente(driver).sendKeys(RS.getString("Id_Paciente"));
				AgendarCita_Page.DocMedico(driver).sendKeys(RS.getString("Id_Medico"));
				AgendarCita_Page.Observaciones(driver).sendKeys(RS.getString("Observaciones"));
				AgendarCita_Page.Btn_Guardar(driver).click();

				Thread.sleep(10000);

				String Resultado = AgendarCita_Page.PanelTitulo(driver).getText();
				String Panel = AgendarCita_Page.PanelRespuesta(driver).getText();

				if (Resultado.equals((RS.getString("ResultadoEsperado"))))
				{
					pdf.addTexto("Ejecución Caso Prueba Nro: " + RS.getString("Caso_Prueba"));
					pdf.addTexto("Resultado: " + Resultado);
					pdf.addTexto("Validación: Caso de Prueba Exitoso");
					pdf.addTexto(Panel);
					pdf.addTexto("---------------------------------------------");
					conexion.insertarSQL("INSERT INTO ResultadosEjecucion VALUES ('" + RS.getString("Caso_Prueba") + "','"
							+ Resultado + "','Caso de Prueba Exitoso','" + new Date(new java.util.Date().getTime()).toString()+ "')");
				}
				else
				{
					pdf.addTexto("Ejecución Caso Prueba Nro: " + RS.getString("Caso_Prueba"));
					pdf.addTexto("Resultado: " + Resultado);
					pdf.addTexto("Validación: Caso de Prueba Fallido");
					pdf.addTexto(Panel);
					pdf.addTexto("---------------------------------------------");
					conexion.insertarSQL("INSERT INTO ResultadosEjecucion VALUES ('" + RS.getString("Caso_Prueba") + "','"
							+ Resultado + "','Caso de Prueba Fallido','" + new Date(new java.util.Date().getTime()).toString()+ "')");
				}

				AgendarCita_Page.Inicio(driver).click();
				driver.quit();
			}

			pdf.closePDF();
			conexion.desconectar();
		}
		catch (Exception e)
		{
			pdf.closePDF();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}