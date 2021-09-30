package Pulse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HasbroAgeGateTest {

@Test
public void sampleTest1() throws InterruptedException
{

WebDriverManager.chromedriver().setup();
WebDriver driver = new ChromeDriver();
driver.manage().window().maximize();
driver.get("https://shop.hasbro.com/worldwide");
driver.getTitle();
driver.findElement(By.xpath("//*[@id=\"en_US\"]")).click();
System.out.println(driver.getTitle());
DateFormat dateFormat = new SimpleDateFormat("MM/d/YYYY");
Calendar cal = Calendar.getInstance();
cal.add(Calendar.YEAR,-2);

String NewDate=dateFormat.format(cal.getTime());
System.out.println(NewDate);
String [] split = NewDate.split("/");
for(int i=0 ; i<split.length;i++) {
System.out.println(split[i]);
}
driver.findElement(By.xpath("/html/body/div[1]/footer/div[2]/ul/li[1]/a")).click();
Select month = new Select(driver.findElement(By.name("bmonth")));
month.selectByVisibleText(split[0]);

Select day = new Select(driver.findElement(By.name("bday")));
day.selectByVisibleText(split[1]);

Select year = new Select(driver.findElement(By.name("byear")));
year.selectByVisibleText(split[2]);


driver.findElement(By.xpath("//*[@id=\"age-success\"]/div/a[2]")).click();

String response = driver.findElement(By.xpath("//*[@id=\"age-errors\"]/h2")).getText();

System.out.println(response);

if(!response.contentEquals("We’re sorry, but you are not eligible to access this site.")) {

System.out.println("Age gate Sucessfully ");

}
else
{
System.out.println("Age Gate got failed");
}
driver.quit();

}

}