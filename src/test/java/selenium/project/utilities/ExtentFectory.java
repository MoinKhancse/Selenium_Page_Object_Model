package selenium.project.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentFectory {
	
	public static final ExtentReports extentReports = new ExtentReports()
			;
	public synchronized static ExtentReports getInstance() {
		ExtentSparkReporter reporter = new ExtentSparkReporter("./reports/report.html");
		reporter.config().setReportName("Automation By Moin Khan");
		extentReports.attachReporter(reporter);
		return extentReports;
	}

}
