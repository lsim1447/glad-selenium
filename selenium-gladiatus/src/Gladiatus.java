import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Gladiatus {
	
	public static void login(WebDriver driver,JavascriptExecutor jse, Actions actions, String username, String password) throws InterruptedException {
		driver.get("https://s22-hu.gladiatus.gameforge.com/game/index.php?mod=overview&sh=6896d257ed43e2c0592d8a235b522c9a");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		WebElement usernameInput = driver.findElement(By.id("login_username"));
		usernameInput.sendKeys(username);
		WebElement passwordInput = driver.findElement(By.id("login_password"));
		passwordInput.sendKeys(password);
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		jse.executeScript("document.getElementById('loginsubmit').click();");
		Thread.sleep(3000);
		
		actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
		driver.get(driver.getCurrentUrl());
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	
	public static void circusTurma(WebDriver driver,JavascriptExecutor jse, Actions actions, int time) throws InterruptedException {
//		actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
//		driver.get(driver.getCurrentUrl());
//		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		//List<WebElement> elements = driver.findElements(By.className("cooldown_bar_link"));
		boolean everythingIsFine = true;
		
		while (everythingIsFine) {
			List<WebElement> elements = driver.findElements(By.className("cooldown_bar_link"));
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			elements.get(3).click();
			System.out.println("Circus Turma btn has been clicked.");
			Thread.sleep(2000);
			List<WebElement> opponents = driver.findElements(By.className("attack"));
			opponents.get(1).click();
			System.out.println("The battle has been finished.");
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).perform();
			Thread.sleep(time);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.gecko.driver","E:\\sss\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		Actions actions = new Actions(driver);
		//login
		login(driver, jse, actions,"Messi", "barca4ever");
		
		//circus turma
		circusTurma(driver, jse, actions, 38000);
	}

}
