package pagesObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AgendarCita_Page
{
	private static WebElement element =  null;
	
	public static WebElement Calendario(WebDriver driver)
	{
		element = driver.findElement(By.id("datepicker"));
		
		return element;
	}
	
	public static WebElement DocPaciente(WebDriver driver)
	{
		element = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
		return element;
	}
	
	public static WebElement DocMedico(WebDriver driver)
	{
		element = driver.findElement(By.xpath("(//input[@type='text'])[3]"));
		return element;
	}
	
	public static WebElement Observaciones(WebDriver driver)
	{
		element = driver.findElement(By.cssSelector("textarea.form-control"));
		return element;
	}
	
	public static WebElement Btn_Guardar(WebDriver driver)
	{
		element = driver.findElement(By.linkText("Guardar"));
		return element;
	}
	
	public static WebElement PanelRespuesta(WebDriver driver)
	{
		element = driver.findElement(By.className("panel-body"));
		return element;
	}
	
	public static WebElement PanelTitulo(WebDriver driver)
	{
		element = driver.findElement(By.className("panel-title"));
		return element;
	}
	
	public static WebElement Inicio(WebDriver driver)
	{
		element = driver.findElement(By.linkText("Inicio"));
		return element;
	}
}