package miniProjectFiles;

import org.openqa.selenium.chrome.ChromeDriver;


import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.WebElement;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;

public class DriverSetup {
	
	public static WebDriver driver;
	
	public static String browser;
	
	public static WebDriver ChromeBrowser() {
		
		driver=new ChromeDriver();
		
		return driver;
		
	}
	
	public static WebDriver EdgeBrowser() {
		
		driver=new EdgeDriver();
		
		return driver;
		
	}
	
	public static WebDriver webDriver() {
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Select the browser :");
		
		System.out.println("1.Chrome\n2.Edge");
		
		browser=sc.nextLine();
		
		if(browser.equalsIgnoreCase("Chrome")) {
			
			ChromeBrowser();
		}
		if(browser.equalsIgnoreCase("Edge")) {
			EdgeBrowser();
		}
		
		return driver;
		
	}
}
