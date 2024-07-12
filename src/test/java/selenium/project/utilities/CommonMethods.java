package selenium.project.utilities;

import java.io.IOException;

import com.github.javafaker.Faker;

import selenium.project.drivers.BaseDriver;

public class CommonMethods extends BaseDriver{
	public void timeOut() throws InterruptedException {
		Thread.sleep(3000);
	}
	public void timeOut(int time) throws InterruptedException {
		Thread.sleep(time);
		
	}
	
	public static void testDataGenerator() throws IOException {
		Faker faker = new Faker();
		
		String email = faker.internet().emailAddress();
		String password = faker.internet().password();
		
		ExcelUtils excel = new ExcelUtils();
		excel.writeExcelData(email, password);
		
		System.out.println(email);
		System.out.println(password);
	}

}
