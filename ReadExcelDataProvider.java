package Testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReadExcelDataProvider {
	static File resultFile = new File("C:/input.xlxs","Sheet1"); public static DataFormatter formatter = new DataFormatter();
@Test(dataProvider="readExcelFile")
public void VerifyParkingDetails(String Parkinglot, String Stdate, String Enddate, String Sttime, String Endtime) {
          WebDriver driver = new EdgeDriver();

driver.get("https://www.shino.de/parkcalc/");

driver.manage().window().maximize();



WebElement dropdown1 = driver.findElement(By.name("ParkingLot"));

Select drop = new Select(dropdown1);

drop.selectByValue(Parkinglot);

driver.findElement(By.name("StartingDate")).clear();

driver.findElement(By.name("StartingDate")).sendKeys(Stdate);


               driver.findElement(By.name("StartingDate")).clear();

driver.findElement(By.name("StartingDate")).sendKeys(Stdate);

driver.findElement(By.name("LeavingDate")).clear();

driver.findElement(By.name("LeavingDate")).sendKeys(Enddate);

driver.findElement(By.name("StartingTime")).clear();

driver.findElement(By.name("StartingTime")).sendKeys(Sttime);

driver.findElement(By.name("LeavingTime")).clear();

driver.findElement(By.name("LeavingTime")).sendKeys(Endtime);

driver.findElement(By.name("Submit")).click();




}


@DataProvider public static Object[][] readExcelFile() throws InvalidFormatException, IOException { FileInputStream fis = new FileInputStream(resultFile); 
XSSFWorkbook wb = new XSSFWorkbook(fis); 
XSSFSheet sh = wb.getSheet("Sheet1"); 
System.out.println(sh.getPhysicalNumberOfRows());
System.out.println(sh.getRow(0).getPhysicalNumberOfCells());
int RowNum = sh.getPhysicalNumberOfRows();
int ColNum = sh.getRow(0).getPhysicalNumberOfCells(); String[][] xlData = new String[RowNum-1][ColNum]; for (int i = 0; i < RowNum - 1; i++) { XSSFRow row = sh.getRow(i + 1); for (int j = 0; j < ColNum; j++) { if (row == null) xlData[i][j] = ""; else { XSSFCell cell = row.getCell(j); if (cell == null) xlData[i][j] = ""; else { String value = formatter.formatCellValue(cell); xlData[i][j] = value.trim();
} } } } return xlData; } }