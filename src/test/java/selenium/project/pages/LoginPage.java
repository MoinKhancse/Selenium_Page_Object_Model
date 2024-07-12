package selenium.project.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import selenium.project.drivers.PageDriver;
import selenium.project.utilities.CommonMethods;
import selenium.project.utilities.ExcelUtils;
import selenium.project.utilities.GetScreenShot;

public class LoginPage extends CommonMethods {
	
	ExtentTest test;
	
	public LoginPage(ExtentTest test) {
		PageFactory.initElements(PageDriver.getcurrentDriver(), this);
		this.test=test;
	}
	@FindBys({
		@FindBy(xpath="//input[@id='email']")
	})
	WebElement userEmail;
	
	@FindBys({
		@FindBy(xpath="//input[@id='password']"),
	})
	WebElement password;
	
	@FindBys({
		@FindBy(xpath="//input[@value='Login']"),
	})
	WebElement signIn;
	
	@FindBys({
		@FindBy(xpath="//a[normalize-space()='New User']")
	})
	WebElement newUser;
	
	@FindBys({
		@FindBy(xpath="//input[@id='firstname']")
	})
	WebElement firstName;
	
	@FindBys({
		@FindBy(xpath="//input[@id='lastname']")
	})
	WebElement lastName;
	
	@FindBys({
		@FindBy(xpath="//input[@id='username']")
	})
	WebElement userName;
	
	@FindBys({
		@FindBy(xpath="//input[@id='password']")
	})
	WebElement regPassword;
	
	@FindBys({
		@FindBy(xpath="//input[@value='Register']")
	})
	WebElement register;

	
	@FindBys({
		@FindBy(xpath="//a[contains(text(),'Back to Login')]")
	})
	WebElement backLogin;
	
	//Report
	public void passCass(String message) {
		test.pass("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");	
	}
	@SuppressWarnings("unused")
	public void passCassWithSC(String message, String scName) throws IOException {
		test.pass("<p style=\"color:#85BC63; font-size:13px\"><b> click button success </b></p>");
		String screenShotPath = GetScreenShot.capture(PageDriver.getcurrentDriver(), "" + scName + "" );
		String dest = System.getProperty("user.dir") + "\\ScreenShot\\" + "" + scName + ".png";
		test.pass(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
	}
	//Fail
	@SuppressWarnings("unused")
	public void failCass(String message, String scName) throws IOException {
		test.fail("<p style=\"color:#85BC63; font-size:13px\"><b>" + message + "</b></p>");
		Throwable t = new InterruptedException("Exception");
		test.fail(t);
		String screenShotPath = GetScreenShot.capture(PageDriver.getcurrentDriver(),"" + scName + "" );
		String dest = System.getProperty("user.dir") + "\\ScreenShot\\" + "" + scName + ".png";
		test.fail(MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
		PageDriver.getcurrentDriver().quit();
		
		
	}
	
	public void login() throws IOException {
		testDataGenerator();
		ExcelUtils.readExcel();
		try {
			test.info("Please Enter Your Email Address");
			if(userEmail.isDisplayed()) {
				userEmail.sendKeys(ExcelUtils.email);
				passCass("You have Success");
				timeOut(1000);
				try {
					test.info("Please Enter Your Password");
					if(password.isDisplayed()) {
						password.sendKeys(ExcelUtils.password);
						passCass("You have Success");
						timeOut(1000);
					}
					try {
						test.info("Please Enter click Singin");
						if(signIn.isDisplayed()) {
							signIn.click();
							passCassWithSC("You have successfully click ", "Login Page");
							timeOut(1000);
						}
					} catch (Exception e) {
						failCass("Sign button not found", "Login Fail");
					}
					
				} catch (Exception e) {
					failCass("User password Not Found", "Pass Fail");
				}
			}
			
		} catch (Exception e) {
			failCass("User Email Not Found", "Email Fail");
		}
	}
	
	public void creatUser() throws IOException {
		test.info("click on new user");
		try {
			if(newUser.isDisplayed()) {
				newUser.click();
				timeOut(1000);
				passCassWithSC("click on new user button ", "Pass New User");
			}
			
			
		} catch (Exception e) {
			failCass("New User Button Not Found", "Fail New User");
		}
	}
	
	public void register() throws IOException {
		test.info("Please Enter Your Fast Name");
		try {
			if(firstName.isDisplayed()) {
				firstName.sendKeys("Moin");
				passCass("Fast Name Success");
				timeOut(1000);				
			}
			try {
				test.info("Please Enter Your Last Name");
				if(lastName.isDisplayed()) {
					lastName.sendKeys("khan");
					passCass("Last Name Success");
					timeOut(1000);
				}
				try {
					test.info("Please Enter Your User Name");
					if(userName.isDisplayed()) {
						userName.sendKeys("Moin khan");
						passCass("User Name Success");
						timeOut();
					}
					try {
						test.info("Please Enter Password");
						if(regPassword.isDisplayed()) {
							regPassword.sendKeys("01717511288");
							passCass("Password Success");
							timeOut();
						}
						try {
							test.info("Please click register Button");
							if(register.isDisplayed()) {
								passCassWithSC("click on new user button ", "Pass New User");
								register.click();
								timeOut();
								passCass("Register Success");
							}
						} catch (Exception e) {
							failCass("Register Locator Not Found", "Fail Register");
						}
					} catch (Exception e) {
						failCass("Password Locator Not Found", "Fail Password");
					}
				} catch (Exception e) {
					failCass("User Name Locator Not Found", "Fail User Name");
				}
			} catch (Exception e) {
				failCass("Last Name Locator Not Found", "Fail Last Name");
			}
		} catch (Exception e) {
			failCass("Fast Name Locator Not Found", "Fail Fast Name");
		}
	}
	
	public void BackLogin() throws InterruptedException {
		test.info("click back");
		if(backLogin.isDisplayed()) {
			backLogin.click();
			timeOut();
		}
	}

}
