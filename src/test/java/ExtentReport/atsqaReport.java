package ExtentReport;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
//import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;

import io.github.bonigarcia.wdm.WebDriverManager;

public class atsqaReport {
	public static WebDriver driver;
	//creating objects for the report classes. to enable create the methods through those objects
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	//creating methods for each class
  @BeforeTest
  public void setExtent() {
	  //instantiating the report class
	 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/myReport.html");
	  htmlReporter.config().setDocumentTitle("Automation Report");//Title of report
	  htmlReporter.config().setReportName("Functional Report");//Name of the report
	  htmlReporter.config().setTheme(Theme.DARK);
	  
	  extent = new ExtentReports();
	  
	  extent.attachReporter(htmlReporter);
	  extent.setSystemInfo("HostName", "LocalHost");
	  extent.setSystemInfo("OS", "Windows10");
	  extent.setSystemInfo("Tester Name","Truphena");
	  extent.setSystemInfo("Browser","ChromeBrowser");
  }
  
  @AfterTest
  public void endReport() {
	  extent.flush();
	  //insert here the property of the class intending to have a report for e.g Jumia, AtSQA or DemoQA
    }
  @AfterMethod
  public void setup() {
	  ChromeOptions options = new ChromeOptions();
		options.addArguments ("--disable-ntifications");
		options.setPageLoadStrategy(PageLoadStrategy.NONE);
		WebDriverManager.chromedriver().setup();
		//driver = new ChromeDriver();
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\hp\\OneDrive\\Desktop\\automated testing\\test projects\\chromedriver v103\\chromedriver.exe");
		driver= new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(45,TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(45,TimeUnit.SECONDS);
		//return driver;
	driver.get("https://atsqa.org/account/login?redirect=%2Faccount");
  }
@Test(priority=1)
public static void certification() throws InterruptedException  {
Thread.sleep(5000);
WebElement mousehover = driver.findElement(By.xpath("//a[@id='dropdown02']"));
Actions action = new Actions(driver);
action.moveToElement(mousehover).build().perform();
driver.findElement(By.xpath("//a[@id='dropdown02']")).click();
Thread.sleep(5000);
driver.findElement(By.xpath("//a[contains(text(),'Test AEngineering')]")).click();
Thread.sleep(5000);
driver.navigate().back();
Thread.sleep(1000);
}
@Test(priority=2)
public static void login() {
driver.findElement(By.xpath("//input[@id='i_email']")).sendKeys("truephenakerubo@gmail.com");
driver.findElement(By.xpath("//input[@id='i_password']")).sendKeys("Mabelcan@1988");
driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
}
@Test
public void atsqacert() {
	test = extent.createTest("atsqacert");
	Assert.assertTrue(true);
}
@AfterMethod
  public void reportStatus(ITestResult result) {
	  if(result.getStatus()==ITestResult.FAILURE) {// to add name in extent report
	 test.log(Status.FAIL, "TEST CASE FAILED IS" + result.getThrowable()); //to add error/exception in extent report
	  ScreenShotHandler.takeScreenShotOnFailure();
		  }
else if (result.getStatus() ==ITestResult.SKIP) {
	//	  test.log(Status.SKIP),"Test Case SKIPPED IS" + result.getName());
	  	  }
  else if (result.getStatus() ==ITestResult.SUCCESS) {
	//  test.log(Status.PASS),"Test Case PASSED IS" + result.getName());
  	  }
  	  
  }

}

