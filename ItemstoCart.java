
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ItemstoCart {

	public static void main(String[] args) throws InterruptedException {
		String[] veggies = { "Cucumber", "Brocolli", "Tomato" };
		WebDriver driver = new ChromeDriver();
		//implicit wait
	//	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//explict wait
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/seleniumPractise/");
		Thread.sleep(2000);
		addItems(driver, veggies);
		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".promoCode")));
		driver.findElement(By.cssSelector(".promoCode")).sendKeys("rahulshettyacademy");
		driver.findElement(By.cssSelector(".promoBtn")).click();
		//explicitwait obj
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".promoInfo")));
		System.out.println(driver.findElement(By.cssSelector(".promoInfo")).getText());

		
		
		
		
		
		
		
		
		
	}

	public static void addItems(WebDriver driver, String[] veggies)

	{
		int j = 0;
		// get all the product names
		List<WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));
		// to find cucumber from all the products dynamically
		for (int i = 0; i < products.size(); i++) {
			// FORMAT TO GET ACTUAL VEGETABLE NAME ONLY FOR ARRAYLIST
			String[] name = products.get(i).getText().split("-");
			String formattedName = name[0].trim();

			// converting array to arrayList
			List veggiesList = Arrays.asList(veggies);

			// DONT REMOVE IF WORKING WITH A LIST if (name.contains("Cucumber"))

			// BREAK After number of items required

			if (veggiesList.contains(formattedName)) {
				j++;
				// click on add to cart
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				// CANNOT USE BREAK WITH ARRAY LIST IF MULTIPLE ITEMS break;
				if (j == veggies.length) {
					break;
				}
			}

		}
	}
}
