import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AgendarCita {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:/drivers/geckodriver.exe");
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		dc.setCapability("marionette", true);
		
		driver = new FirefoxDriver(dc);
		//    driver = new FirefoxDriver();
		baseUrl = "http://automatizacion.herokuapp.com";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void testAgendarCita() throws Exception {
		driver.get(baseUrl + "/dzaraza/");
		driver.findElement(By.linkText("Agendar Cita")).click();
		driver.findElement(By.id("datepicker")).click();
		driver.findElement(By.linkText("27")).click();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("109203903");
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("19217363");
		driver.findElement(By.cssSelector("textarea.form-control")).clear();
		driver.findElement(By.cssSelector("textarea.form-control")).sendKeys("terapia recuperar movilidad pie derecho");
		driver.findElement(By.linkText("Guardar")).click();
		driver.findElement(By.linkText("Inicio")).click();
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	
	public boolean isAcceptNextAlert()
	{
		return acceptNextAlert;
	}
	
	public void setAcceptNextAlert(boolean acceptNextAlert)
	{
		this.acceptNextAlert = acceptNextAlert;
	}
}
