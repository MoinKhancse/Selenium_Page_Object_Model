package selenium.project.tests;

import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import selenium.project.drivers.PageDriver;
import selenium.project.pages.LoginPage;
import selenium.project.utilities.CommonMethods;
import selenium.project.utilities.ExtentFectory;

public class LoginTest extends  CommonMethods{
	ExtentReports report;
	ExtentTest parrentTest;
	ExtentTest childTest;
	
	@BeforeClass
	public  void openUrl() throws InterruptedException {
		driver.manage().window().maximize();
		PageDriver.getcurrentDriver().get(url);		
		timeOut();
		report = ExtentFectory.getInstance();
		parrentTest = report.createTest("<p style=\"color:#FF6000; font-size:13px\"><b>Login Test</b></p>").assignAuthor("SQA Team").assignDevice("Windows");
	}
	
	@Test(priority = 1)
	public void loginIntoShop() throws IOException, InterruptedException {
		childTest = parrentTest.createNode("<p style=\"color:#3E96E7; font-size:13px\"><b>Login User</b></p>");
		LoginPage loginPage = new LoginPage(childTest);
		loginPage.login();
		timeOut(1000);
		
	}
	
//	@Test(priority = 1)
//	public void createNewUser() throws IOException {
//		childTest = parrentTest.createNode("<p style=\"color:#3E96E7; font-size:13px\"><b>New User</b></p>");
//		LoginPage loginPage = new LoginPage(childTest);
//		loginPage.creatUser();
//		
//	}
	@AfterClass
	public void reportHtml() {
		report.flush();
	}

}
