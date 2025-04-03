package api.utilities;


	//Listener class used to generate Extent reports

	import java.io.File;
	import java.io.IOException;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	import org.testng.ITestContext;
	import org.testng.ITestResult;
	import org.testng.TestListenerAdapter;

	
	
	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;
	import com.aventstack.extentreports.markuputils.ExtentColor;
	import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

	import com.aventstack.extentreports.reporter.configuration.Theme;

	public class ExtentReportManager extends TestListenerAdapter
	{
		public ExtentSparkReporter sparkReporter;
		public ExtentReports extent;
		public ExtentTest test;
			String repName;
		public void onStart(ITestContext testContext)
		{
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
			String repName="Test-Report-"+timeStamp+".html";
			
			sparkReporter= new ExtentSparkReporter(".\\reports\\"+repName);

			sparkReporter.config().setDocumentTitle("RestAssuredBhau Test Project"); // Tile of report
			sparkReporter.config().setReportName("Swagger User API"); // name of the report
			sparkReporter.config().setTheme(Theme.DARK);
			
			
			extent=new ExtentReports();
			
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("User Name",System.getProperty("user.name"));
			extent.setSystemInfo("Environemnt","QA");
			extent.setSystemInfo("user","hp");
			
	
		}
		
		public void onTestSuccess(ITestResult tr)
		{
			test=extent.createTest(tr.getName()); // create new entry in th report
			test.createNode(tr.getName());
			test.assignCategory(tr.getMethod().getGroups());
			test.log(Status.PASS, "Test Passed"); // send the passed information to the report with GREEN color highlighted
		}
		
		public void onTestFailure(ITestResult tr)
		{
			test=extent.createTest(tr.getName()); // create new entry in th report
			test.createNode(tr.getName());
			test.assignCategory(tr.getMethod().getGroups());
			test.log(Status.FAIL, "Test Failed"); ; // send the passed information to the report with GREEN color highlighted
			test.log(Status.FAIL, tr.getThrowable().getMessage());
		}
		
		public void onTestSkipped(ITestResult tr)
		{
			test=extent.createTest(tr.getName()); // create new entry in th report
			test.createNode(tr.getName());
			test.assignCategory(tr.getMethod().getGroups());
			test.log(Status.SKIP, "Test Skipped"); ; // send the passed information to the report with GREEN color highlighted
			test.log(Status.SKIP, tr.getThrowable().getMessage());
		}
		
		public void onFinish(ITestContext testContext)
		{
			extent.flush();
		}
	
}
