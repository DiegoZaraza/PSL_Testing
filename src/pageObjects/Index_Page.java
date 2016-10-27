package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Index_Page
{
	private static WebElement element = null;
	
	public static WebElement AgregarDoctor(WebDriver driver)
	{
		element = driver.findElement(By.linkText("Agregar Doctor"));
		return element;
	}
	
	public static WebElement AgregarPaciente(WebDriver driver)
	{
		element = driver.findElement(By.linkText("Agregar Paciente"));
		return element;
	}
	
	public static WebElement AgregarHabitacion(WebDriver driver)
	{
		element = driver.findElement(By.linkText("Agregar Habitacion"));
		return element;
	}
	
	public static WebElement AgregarHospital(WebDriver driver)
	{
		element = driver.findElement(By.linkText("Agregar Hospital"));
		return element;
	}
	
	public static WebElement VerHospitales(WebDriver driver)
	{
		element = driver.findElement(By.linkText("Ver Hospitales"));
		return element;
	}
	
	public static WebElement AgendarCita(WebDriver driver)
	{
		element = driver.findElement(By.linkText("Agendar Cita"));
		return element;
	}
}
