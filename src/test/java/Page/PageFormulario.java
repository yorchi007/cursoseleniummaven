package Page;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;

import ConfiguracionBase.ConfigBase;
import Utilitarios.UtilitariosButton;
import Utilitarios.UtilitariosTextField;
import bean.BeanFormulario;

public class PageFormulario extends ConfigBase{

	@FindBy(name = "entry.2005620554")
	WebElement inputnombreApellido;
	
	@FindBy(name = "entry.1140037502")
	WebElement inputmail;
	
	@FindBy(xpath = "//span[contains(text(),'Siguiente')]")
	WebElement botonSiguiente;


	@FindBy(xpath = "//span[contains(text(),'Enviar')]")
	WebElement enviar;



	public PageFormulario()
	{
	String baseUrl = "https://docs.google.com/forms/d/e/1FAIpQLSfzLAD7DNf6fz1tu_gtbuyDZVbpSbeR_PNS3zOtlj416VZ1jA/viewform";
	PageFactory.initElements(driver, this); // si no se inicializan los WebElement da error de null pointer exception
	driver.get(baseUrl);
	}
		
	public void registrarFormulario( ITestResult testResult,BeanFormulario beanFormulario) throws InterruptedException, IOException
	{
		UtilitariosTextField.txtField_putString(beanFormulario.getNombreApellido(), inputnombreApellido, driver, testResult);
		UtilitariosTextField.txtField_putString(beanFormulario.getCorreo(), inputmail, driver, testResult);

		UtilitariosButton.btn_clic(botonSiguiente, driver, testResult);
		UtilitariosButton.btn_clic(enviar,driver, testResult);

		
	}
	
}
