package miniProjectFiles;

import java.time.Duration;


import org.openqa.selenium.WebDriver;

public class MainClass  {
	
	public static WebDriver driver;
	
	public static void main(String args[]) throws Exception
	{
		IRCTCAIR method=new IRCTCAIR();
		
		driver=method.browserSetup();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		method.excel();
		
		method.openUrl(driver);
		
		method.maximize(driver);
		
		method.EnterOrigin(driver);
		
		method.EnterDestination(driver);
		
		method.DataPicker(driver);
		
		method.EnterTraveller(driver);
		
		method.EnterSearch(driver);
		
		System.out.println(method.verifyCityAndDate(driver));
		
		method.ResultToBeDisplay(driver);
		
		method.ExtentFileClose();
		
		
		
		
	
	}

}
