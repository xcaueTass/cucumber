package aprendercucumber.runners;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/", 
					glue = "aprendercucumber.steps", 
					tags = "~@ignore", 
					plugin = {"pretty", "html:target/report-html",	"json:target/report.json" }, 
					monochrome = true, 
					snippets = SnippetType.CAMELCASE, 
					dryRun = false, 
					strict = true)
public class RunnerCucumberTest {

	@BeforeClass
	public static void reset() {
		String usuario = "caue@caue";
		String senha = "teste123";
		System.setProperty("webdriver.chrome.driver", "target/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://seubarriga.wcaquino.me/");
		driver.findElement(By.id("email")).sendKeys(usuario);
		driver.findElement(By.name("senha")).sendKeys(senha);
		driver.findElement(By.tagName("button")).click();
		driver.findElement(By.linkText("reset")).click();
		driver.quit();
	}
}
