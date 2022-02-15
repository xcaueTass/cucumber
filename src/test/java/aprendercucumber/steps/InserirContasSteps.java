package aprendercucumber.steps;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class InserirContasSteps {

	private WebDriver driver;

	@Given("^que estou acessando a aplicacao$")
	public void queEstouAcessoandoAAplicaO() {
		System.setProperty("webdriver.chrome.driver", "target/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://seubarriga.wcaquino.me/");
	}

	@When("^informo o usuario \"([^\"]*)\"$")
	public void informoOUsuario(String usuario) {
		driver.findElement(By.id("email")).sendKeys(usuario);
	}

	@When("^a senha \"([^\"]*)\"$")
	public void aSenha(String senha) {
		driver.findElement(By.name("senha")).sendKeys(senha);
	}

	@When("^seleciono entrar$")
	public void selecionoEntrar() {
		driver.findElement(By.tagName("button")).click();
	}

	@Then("^visualizo a pagina inicial$")
	public void visualizoAPaginaInicial() {
		String text = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		assertEquals("Bem vindo, caue!", text);
	}

	@When("^seleciono contas$")
	public void selecionoContas() {
		driver.findElement(By.linkText("Contas")).click();
	}

	@When("^seleciono Adicionar$")
	public void selecionoAdicionar() {
		driver.findElement(By.linkText("Adicionar")).click();
	}

	@When("^informo a conta \"([^\"]*)\"$")
	public void informoAConta(String nomeConta) {
		driver.findElement(By.id("nome")).sendKeys(nomeConta);
	}

	@When("^seleciono Salvar$")
	public void selecionoSalvar() {
		driver.findElement(By.tagName("button")).click();
	}

	@Then("^recebo a mensagem \"([^\"]*)\"$")
	public void receboAMensagem(String valorExperado) {
		String text = driver.findElement(By.xpath("//div[starts-with(@class, 'alert alert-')]")).getText();
		text = text.replace("Já", "Ja");
		assertEquals(valorExperado, text);
	}

	@After(order = 1)
	public void screenshot(Scenario cenario) throws IOException {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("target/screenshot/" + cenario.getId() + ".jpg"));
	}

	@After(order = 0)
	public void fecharBrowser() {
		driver.quit();
	}
}
