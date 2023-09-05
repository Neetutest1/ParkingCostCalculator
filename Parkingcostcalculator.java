package ParkingCostCalculator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.IOException;



public class Parkingcostcalculator {

	
	public static void main(String[] args) throws IOException {
		WebDriver driver = new EdgeDriver();
		driver.get("https://www.shino.de/parkcalc/");
		driver.manage().window().maximize();
		
		WebElement dropdown1 = driver.findElement(By.name("ParkingLot"));
		Select drop = new Select(dropdown1);
		drop.selectByValue("Economy");
		driver.findElement(By.id("StartingDate")).clear();
		driver.findElement(By.id("StartingDate")).sendKeys("9/5/2023");
		driver.findElement(By.name("LeavingDate")).clear();
		driver.findElement(By.id("LeavingDate")).sendKeys("9/5/2023");
		driver.findElement(By.id("StartingTime")).clear();
		driver.findElement(By.name("StartingTime")).sendKeys("02:00");
		driver.findElement(By.name("LeavingTime")).clear();
		driver.findElement(By.name("LeavingTime")).sendKeys("04:00");
		WebElement radioPM = driver.findElement(By.cssSelector("input[value='PM'][name='LeavingTimeAMPM']"));
        radioPM.click();
		driver.findElement(By.name("Submit")).click();
		String msg= driver.findElement(By.xpath("//span[@class='SubHead']")).getText();
		System.out.println(msg);
		Assert.assertEquals(msg,"$ 9.00");
	
		
				

	}

}
