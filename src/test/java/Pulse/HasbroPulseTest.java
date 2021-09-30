package Pulse;



import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Driver;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HasbroPulseTest {
	/* Positive Scenario*/
@Test
public void PositiveCase() throws Exception {
	 File src = new File("E:\\Automation\\Positive.xlsx");
     FileInputStream fis = new FileInputStream(src);
     XSSFWorkbook workbook = new XSSFWorkbook(fis);
     XSSFSheet sheet = workbook.getSheetAt(0);
     Row row = sheet.getRow(0);
     Cell cell = row.getCell(0);
     int colCount = row.getLastCellNum();
     int rowCount = sheet.getLastRowNum() + 1;
    
     String result;
     String result1;
     String sku[] = new String[rowCount];
     int len = sku.length;
  
     for (int i = 0; i < rowCount; i++) {
         sku[i]=sheet.getRow(i).getCell(0).getStringCellValue();
        
     }
     File src1 = new File("E:\\Automation\\Positive.xlsx");
     FileInputStream fis1 = new FileInputStream(src1);
     XSSFWorkbook workbook1 = new XSSFWorkbook(fis1);
     XSSFSheet sheet1 = workbook1.getSheetAt(1);
     Row row1 = sheet1.getRow(0);
     Cell cell1 = row1.getCell(0);
     int colCount1 = row1.getLastCellNum();
    
     int rowCount1 = sheet1.getLastRowNum() +1;
    
     String url[]=new String [rowCount1];
     int size=url.length;
    
     for (int i = 0; i < rowCount1; i++) {
         url[i]=sheet1.getRow(i).getCell(0).getStringCellValue();
        
     }

     WebDriverManager.chromedriver().setup();
     WebDriver driver = new ChromeDriver();
     driver.manage().window().maximize();
    
     driver.get(url[0]);
    // driver.findElement(By.xpath("//*[@id='en_US']")).click();
     driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     for (int i = 1;  i < rowCount; i++) {
     driver.manage().deleteAllCookies();
        
         //driver.findElement(By.xpath("//*[@class='search-input']")).sendKeys(sku[i]);
         //driver.findElement(By.xpath("//*[@type='submit']")).sendKeys(Keys.ENTER);
     /*Star-Wars*/
     driver.findElement(By.xpath("//*[@id='search-input']")).sendKeys(sku[i]);
     driver.findElement(By.xpath("//div[@class='searchBox']//*[@type='submit']")).sendKeys(Keys.ENTER);
         XSSFCell cell2 = sheet.getRow(0).createCell(2);
         XSSFCell cell3 = sheet.getRow(0).createCell(3);
         cell2.setCellValue("Actual Result");
         cell3.setCellValue("Pulse/No Pulse");
         if(!driver.findElements(By.xpath("//*[@class='noProducts']")).isEmpty()) {
        	
        	 if(driver.findElements(By.xpath("//*[@class='HoverWrapper']")).isEmpty()) {
        		 String result3 = "No Products";
        		 String result4 = "No URL";
        		 cell3 = sheet.getRow(i).createCell(2);
                 cell3.setCellValue(result4);
                     cell3 = sheet.getRow(i).createCell(3);
                     cell3.setCellValue(result3);
        	 }
        		 
        	 else {
         driver.findElement(By.xpath("//*[@class='HoverWrapper']")).click();
         
         
         if(!driver.findElements(By.xpath("//*[@class='manual_retailer_update']")).isEmpty())
         {
          
             DateFormat dateFormat = new SimpleDateFormat("MM/d/YYYY");
         Calendar calendar = Calendar.getInstance();
         calendar.add(Calendar.YEAR,-16);
        
         String NewDate = dateFormat.format(calendar.getTime());
        
         String [] Datespliter = NewDate.split("/");
        
         for(int j=0 ; j<Datespliter.length;j++) {
        
         }
         driver.findElement(By.xpath("//*[@class='retailer_link']")).click();
         Select month = new Select(driver.findElement(By.id("pulsebirthMonth")));
         month.selectByVisibleText(Datespliter[0]);

         Select day = new Select(driver.findElement(By.id("pulsebirthDay")));
         day.selectByVisibleText(Datespliter[1]);

         Select year = new Select(driver.findElement(By.id("pulsebirthYear")));
         year.selectByVisibleText(Datespliter[2]);
        
         driver.findElement(By.xpath("//*[@onclick='pulseageCheck()']")).click();
        
         String response = driver.findElement(By.xpath("//*[@id=\"age-errors\"]/h3")).getText();

         System.out.println(response);

         if(!response.equals("We'Re Sorry, But You Are Not Eligible To Access This Site.")) {

        
         result = "Hasbro Pulse";
         result1="Age gate Pass";
             cell3 = sheet.getRow(i).createCell(3);
             cell3.setCellValue(result + "-" + result1 );
            
             ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
             
             
             driver.switchTo().window(newTb.get(1));
             String s= driver.getCurrentUrl();
             cell2 = sheet.getRow(i).createCell(2);
             cell2.setCellValue(s);
driver.close();
             driver.switchTo().window(newTb.get(0));
             
         }
         else
         {
        
         result = "Hasbro Pulse";
         result1="Age gate Failed";
             cell3 = sheet.getRow(i).createCell(3);
             cell3.setCellValue(result + "-" + result1 );
         }
            
            
         }
         else
         {
            
             result = "No Hasbro Pulse";


              cell3 = sheet.getRow(i).createCell(3);
             cell3.setCellValue(result);
             String result5= "No URL";
             cell2 = sheet.getRow(i).createCell(2);
             cell2.setCellValue(result5);
         }
        
     }
     
         }
     }

     driver.close();
     
     XSSFCell cell4 = sheet.getRow(0).createCell(4);
     cell4.setCellValue("OutCome");
     for(int l=1;l<rowCount;l++) {
    	// System.out.println(sheet.getRow(l).getCell(1));
    //	 System.out.println(sheet.getRow(l).getCell(2));
    	 String a=sheet.getRow(l).getCell(1).getStringCellValue();
    	 String b=sheet.getRow(l).getCell(2).getStringCellValue();
      if(a.equalsIgnoreCase(b))
      {
      
    	//System.out.println("True"); 
   	XSSFCell cell5 = sheet.getRow(l).createCell(4);
   	 cell5.setCellValue("True");
     }
      else
      {
    	//  System.out.println("False"); 
	XSSFCell cell5 = sheet.getRow(l).createCell(4);
 	 cell5.setCellValue("False");
      }
     }
     FileOutputStream fos=new FileOutputStream(src1);
     workbook.write(fos);
     System.out.println("Done");        
 }


/* Negative Scenario */
@Test
public void NegativeCase() throws Exception {
File src = new File("E:\\Automation\\Negative.xlsx");
        FileInputStream fis = new FileInputStream(src);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        int colCount = row.getLastCellNum();
       
        int rowCount = sheet.getLastRowNum() + 1;
       
        String result;
        String result1;
        String sku[] = new String[rowCount];
        int len = sku.length;
     
        for (int i = 0; i < rowCount; i++) {
            sku[i]=sheet.getRow(i).getCell(0).getStringCellValue();
           
        }
        File src1 = new File("E:\\Automation\\Negative.xlsx");
        FileInputStream fis1 = new FileInputStream(src1);
        XSSFWorkbook workbook1 = new XSSFWorkbook(fis1);
        XSSFSheet sheet1 = workbook1.getSheetAt(1);
        Row row1 = sheet1.getRow(0);
        Cell cell1 = row1.getCell(0);
        int colCount1 = row1.getLastCellNum();
       
        int rowCount1 = sheet1.getLastRowNum() +1;
       
        String url[]=new String [rowCount1];
        int size=url.length;
       
        for (int i = 0; i < rowCount1; i++) {
            url[i]=sheet1.getRow(i).getCell(0).getStringCellValue();
           
        }

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
       
        driver.get(url[0]);
        //driver.findElement(By.xpath("//*[@id='en_US']")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        for (int i = 1;  i < rowCount; i++) {
        driver.manage().deleteAllCookies();
           
            //driver.findElement(By.xpath("//*[@class='search-input']")).sendKeys(sku[i]);
           // driver.findElement(By.xpath("//*[@type='submit']")).sendKeys(Keys.ENTER);
        /*Star-Wars*/
        driver.findElement(By.xpath("//*[@id='search-input']")).sendKeys(sku[i]);
        driver.findElement(By.xpath("//div[@class='searchBox']//*[@type='submit']")).sendKeys(Keys.ENTER);
            driver.findElement(By.xpath("//*[@class='HoverWrapper']")).click();
            XSSFCell cell2 = sheet.getRow(0).createCell(1);
            cell2.setCellValue("Result");
            if(!driver.findElements(By.xpath("//*[@class='manual_retailer_update']")).isEmpty())
            {
             
                DateFormat dateFormat = new SimpleDateFormat("MM/d/YYYY");
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR,-16);
            calendar.add(Calendar.DATE, 1);
           
            String NewDate = dateFormat.format(calendar.getTime());
           System.out.println(NewDate);
            String [] Datespliter = NewDate.split("/");
           
            for(int j=0 ; j<Datespliter.length;j++) {
           
            }
            driver.findElement(By.xpath("//*[@class='retailer_link']")).click();
            Select month = new Select(driver.findElement(By.id("pulsebirthMonth")));
            month.selectByVisibleText(Datespliter[0]);

            Select day = new Select(driver.findElement(By.id("pulsebirthDay")));
            day.selectByVisibleText(Datespliter[1]);

            Select year = new Select(driver.findElement(By.id("pulsebirthYear")));
            year.selectByVisibleText(Datespliter[2]);
           
            driver.findElement(By.xpath("//*[@onclick='pulseageCheck()']")).click();
           
            String response = driver.findElement(By.xpath("//*[@id=\"age-errors\"]/h3")).getText();

            System.out.println(response);

            if(!response.equals("We'Re Sorry, But You Are Not Eligible To Access This Site.")) {

           
            result = "Hasbro Pulse";
            result1="Age gate Pass";
                cell2 = sheet.getRow(i).createCell(1);
                cell2.setCellValue(result + "-" + result1 );

            }
            else
            {
           
            result = "Hasbro Pulse";
            result1="Age gate Failed";
                cell2 = sheet.getRow(i).createCell(1);
                cell2.setCellValue(result + "-" + result1 );
            }
               
               
            }
            else
            {
               
                result = "No Hasbro Pulse";


                 cell2 = sheet.getRow(i).createCell(1);
                cell2.setCellValue(result);
            }
           
        }

        driver.close();
       
        FileOutputStream fos=new FileOutputStream(src1);
        workbook.write(fos);
        System.out.println("Done");        
    }
}
