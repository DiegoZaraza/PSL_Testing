package automationFramework;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import pageObjects.AgendarCita_Page;
import pageObjects.Index_Page;

public class POM
{
	private static WebDriver driver = null;
	private static String baseUrl;
	
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.gecko.driver", "C:/drivers/geckodriver.exe");
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		dc.setCapability("marionette", true);
		
		driver = new FirefoxDriver(dc);
		baseUrl = "http://automatizacion.herokuapp.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl + "/dzaraza/");
		
		Index_Page.AgendarCita(driver).click();
		
		AgendarCita_Page.Calendario(driver).sendKeys("10/31/2016");
		AgendarCita_Page.DocPaciente(driver).sendKeys("1026250293");
		AgendarCita_Page.DocMedico(driver).sendKeys("11223344");
		AgendarCita_Page.Observaciones(driver).sendKeys("prueba");		
		AgendarCita_Page.Btn_Guardar(driver).click();
		
		
		Thread.sleep(5000);
		
		System.out.println(AgendarCita_Page.PanelRespuesta(driver).getText());
		
		driver.quit();
	}
	
}
