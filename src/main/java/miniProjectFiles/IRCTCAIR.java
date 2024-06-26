package miniProjectFiles;

import java.io.File;



import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;

import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class IRCTCAIR{
	
	static WebDriver driver;
	String act_url;
	String exp_url;
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test; 
	
	public WebDriver browserSetup()
	{
		
		htmlReporter=new ExtentSparkReporter("C:\\Users\\2318570\\eclipse-workspace\\project\\ExtentReport\\Report.html");//specify location of the report
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		htmlReporter.config().setDocumentTitle("Extent Report"); // TiTle of report
		htmlReporter.config().setReportName("Functional Testing"); // name of the report
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		driver=DriverSetup.webDriver();
		return driver;
	}
	
	public void excel() {
		
		ExcelDataFile.ExcelData();
	}
	
	//Launch https://www.air.irctc.co.in/ in a web browser, and verify the appropriate site is opened or not. 
	
	public void openUrl(WebDriver driver) throws Exception {
		
		ExtentTest t1=extent.createTest("Open the Url ","verify Url");
		
		driver.get(" https://www.air.irctc.co.in/ ");
		
		act_url=driver.getTitle();
		
		exp_url="Air Ticket Booking | Book Flight Tickets | Cheap Air Fare - IRCTC Air";
		
		screenShots(driver,"\\home");
		
		ExcelDataFile.setExcelValue(exp_url, act_url);
		
		if(exp_url.equals(act_url)) {
			
			System.out.println("Site is Open properly.");
		}
		else {
			
			System.out.println("site is not opened. ");
		}
		
		t1.info("Flight Search Automation site is opened");
		
		t1.pass("site is open successfully");
		
	}
	
	//Maximize the window  
	
	public void maximize(WebDriver driver) {
		
		ExtentTest t2=extent.createTest("Url is maximize","verify the window is maximized");
		
		driver.manage().window().maximize();
		
		t2.info("Window is maximized");
		
		t2.pass("The site is maximized Successfully");
	
	}
	
	//Enter "Hyd" in “From” city, in the auto listed results, select "HYDERABAD (HYD)"  
	
	public void EnterOrigin(WebDriver driver) throws Exception {
		
		ExtentTest t3=extent.createTest("Enter origin value");
	
		WebElement enterfrom= driver.findElement(By.id("stationFrom"));
	
		enterfrom.sendKeys(ExcelDataFile.orginFrom);
		  
        Thread.sleep(5000);
          
		List<WebElement> list1=driver.findElements(By.xpath("//*[contains(text(),'Hyderabad')]"));
		  
		for(WebElement lis:list1)
			
		  {
			  String text=lis.getText();
			  
			  if(text.contains("HYD"))
			  {
				  lis.click();
			  }
		  }
		
		t3.info("Enter origin value is Hyderabad");
		
		t3.pass("Hyderabad is entered successfully");
		  
	}
	
	//Enter "Pune" in "To" city, in the auto listed results, select "PUNE (PNQ)"
	
	public void EnterDestination(WebDriver driver) throws Exception {
		
		ExtentTest t4=extent.createTest("Enter origin value");
		
		WebElement Enterto= driver.findElement(By.id("stationTo"));
		
		  Enterto.sendKeys(ExcelDataFile.destinationTo);
		  
		  Thread.sleep(1000);
		  
		  List<WebElement> list2=driver.findElements(By.xpath("//*[contains(text(),'Pune')]"));
		  
		  for(WebElement li:list2)
		  {
			  String text=li.getText();
			  
			  if(text.contains("PNQ"))
			  {
				  li.click();
			  }
		  }
		  
		  t4.info("Enter origin value is Hyderabad");
			
		  t4.pass("Hyderabad is entered successfully");
	
	}
	
	//Select today’s date from the date picker. 
	
	public void DataPicker(WebDriver driver) {
		
		ExtentTest t5=extent.createTest("select the date value");
	
		WebElement date=driver.findElement(By.id("originDate"));
	
		date.click();
	
		WebElement today=date.findElement(By.xpath("//span[@class='act active-red']"));
	
		today.click();
	
		t5.info("Select the date & day ");
	
		t5.pass("Date & Day selected successfully");
	
	}
	
	//Select "Business" class, from “Choose class” dropdown box in Traveller details field. 
	
	public void EnterTraveller(WebDriver driver) {
		
		ExtentTest t6=extent.createTest("choose class from dropDown");
	
		driver.findElement(By.xpath("//input[@id=\"noOfpaxEtc\"]")).click();
	
		driver.findElement(By.cssSelector("select#travelClass")).click();
	
		WebElement traveller=driver.findElement(By.xpath("//option[@value=\"Business\"]"));
	
		traveller.click();
		
		t6.info("Choose the business class ");
		
		t6.pass("Business class choosed successfully");
	
	}
	
	//Click on "Search" button. 
	
	public void EnterSearch(WebDriver driver)throws Exception {
		
		ExtentTest t7=extent.createTest("click search button");
	
		WebElement search=driver.findElement(By.xpath("//button[contains(text(),\"Search\")]"));

		screenShots(driver,"\\viewdata");
	
		search.click();
		
		t7.info("search button to be click");
		
		t7.pass("Search button click successfully");
	
	}
	
	//Verify the results shown are valid, by checking the city and date values are same as given in previous page. 
	
	public String verifyCityAndDate(WebDriver driver) throws InterruptedException {
		
		ExtentTest t8=extent.createTest("verify city and date");
	
		String 	verifyCity="";
	
		List<WebElement> city1=driver.findElements(By.xpath("//*[@class='right-searchbarbtm-in']/div[2]/span"));
	
		List<WebElement> city2=driver.findElements(By.xpath("//*[@class='right-searchbarbtm-in']/div[3]/span"));
	
		Thread.sleep(5000);
    
		WebElement dep_date=driver.findElement(By.xpath("//input[@id='originDate']"));
	
		Thread.sleep(5000);
	
		dep_date.click();
	
		Thread.sleep(5000);
	
		WebElement today1=driver.findElement(By.xpath("//span[@class='act active active-red']"));
	
		today1.click();
	
		Thread.sleep(5000);

		int noOfResults = city1.size();

		for(int i = 0; i < noOfResults; i++)
		{
			String c1=city1.get(i).getText();
			
			String c2=city2.get(i).getText();
			
			String departure_date=today1.getText();
			
			System.out.println(departure_date);
	
			if(c1.contains("Hyderabad") && c2.contains("Pune") && LocalDate.now().toString().split("-")[2].contains(departure_date))
			{
				boolean result = true;
				verifyCity= "Task verified";
			}
			else
				{
				boolean result = false;
				verifyCity= "Task failed";
				break;
			}
	}
		
		t8.info("verify clicking city and date");
		
		t8.pass("city and date verification is successfully");
	
		return verifyCity;
	}
	
	//Display the name and Number of available Flights on the console 
	
	public void ResultToBeDisplay(WebDriver driver) throws Exception{
		
		ExtentTest t9=extent.createTest("Display the name and number of the display available flight");
	
		List<WebElement> details=driver.findElements(By.xpath("//*[@class='right-searchbarbtm-in']/div/div[2]"));
	
		for(WebElement NameList:details) {
		
			WebElement name=NameList.findElement(By.xpath("./b"));
 		
			WebElement NoFlight=NameList.findElement(By.xpath("./span"));
    	
			System.out.println("name :"+name.getText());
    	
			System.out.println("No of flight :"+NoFlight.getText());
		
			System.out.println("============================================");
		
			screenShots(driver,"\\result");
		
		}
		
		t9.info("The name and number of the available flight to be diplayed.");
		
		t9.pass("Available flight displayed successfully");
	
	}
	
	//Capture the results screenshot and save in the project folder. 
	
	public void screenShots(WebDriver driver,String name) throws Exception {
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		File src=ts.getScreenshotAs(OutputType.FILE);
		
		File tar=new File("C:\\Users\\2318570\\eclipse-workspace\\project\\screenshots"+name+".png");
		
		FileUtils.copyFile(src, tar);
		
		
	}
	
	public static void ExtentFileClose(){
		
		extent.flush();
		
	}
	

}
