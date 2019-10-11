/**
 * @author UmaMaheswararao
 */

package com.Listeners;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ExtentReports.ExtentManager;
import com.ExtentReports.ExtentTestManager;
import com.base.BasePage;
import com.relevantcodes.extentreports.LogStatus;


public class WebEventListener extends BasePage implements ITestListener, WebDriverEventListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	//Before starting all tests, below method runs.
	public void onStart(ITestContext iTestContext) {
		System.out.println("I am in onStart method => " + iTestContext.getName());
		iTestContext.setAttribute("WebDriver", BasePage.driver);
	}

	//After ending all tests, below method runs.
	public void onFinish(ITestContext iTestContext) {
		System.out.println("I am in onFinish method => " + iTestContext.getName());
		//Do tier down operations for ExtentReports reporting!
		ExtentTestManager.endTest();
		ExtentManager.getReporter().flush();
	}

	public void onTestStart(ITestResult iTestResult) {
		System.out.println("I am in onTestStart method => " +  getTestMethodName(iTestResult) + " Start");
		//Start operation for ExtentReports.
		ExtentTestManager.startTest(iTestResult.getMethod().getMethodName(),"");
	}

	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("I am in onTestSuccess method => " +  getTestMethodName(iTestResult) + " Succeed");
		//ExtentReports log operation for passed tests.
		ExtentTestManager.getTest().log(LogStatus.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult iTestResult) {
		System.out.println("I am in onTestFailure method => " +  getTestMethodName(iTestResult) + " Failed");

		ExtentTestManager.getTest().log(LogStatus.FAIL, "EXCEPTION OCCURED IS: " +iTestResult.getThrowable());

		//Get driver from TestBase and assign to local WebDriver variable.
		Object testClass = iTestResult.getInstance();
		WebDriver webDriver = ((BasePage) testClass).getDriver();

		//Take base64Screenshot screenshot.
		String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)webDriver).
				getScreenshotAs(OutputType.BASE64);

		//ExtentReports log and screenshot operations for failed tests.
		ExtentTestManager.getTest().log(LogStatus.FAIL,"Test Failed",
				ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));

	}

	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am in onTestSkipped method => "+  getTestMethodName(iTestResult) + " Skipped");
		//ExtentReports log operation for skipped tests.
		ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
	}
	
	
	//============== Overridden Methods ==================

	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}
	
	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Before navigating to: '" + url + "'");
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		System.out.println("Navigated to:'" + url + "'");
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		System.out.println("Value of the:" + element.toString() + " before any changes made");
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		System.out.println("Element value changed to: " + element.toString());
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		System.out.println("Trying to click on: " + element.toString());
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("Clicked on: " + element.toString());
	}

	public void beforeNavigateBack(WebDriver driver) {
		System.out.println("Navigating back to previous page");
	}

	public void afterNavigateBack(WebDriver driver) {
		System.out.println("Navigated back to previous page");
	}

	public void beforeNavigateForward(WebDriver driver) {
		System.out.println("Navigating forward to next page");
	}

	public void afterNavigateForward(WebDriver driver) {
		System.out.println("Navigated forward to next page");
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Trying to find Element By : " + by.toString());
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Found Element By : " + by.toString());
	}

	/*
	 * non overridden methods of WebListener class
	 */
	public void beforeScript(String script, WebDriver driver) {
	}

	public void afterScript(String script, WebDriver driver) {
	}

	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeAlertDismiss(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub

	}

	public void onException(Throwable error, WebDriver driver) {
		/*System.out.println("Exception occured: " + error);
		try {
			TestUtil.takeScreenshotAtEndOfTest();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
	}

	public <X> void afterGetScreenshotAs(OutputType<X> arg0, X arg1) {
		// TODO Auto-generated method stub
		
	}

	public void afterSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	public <X> void beforeGetScreenshotAs(OutputType<X> arg0) {
		// TODO Auto-generated method stub
		
	}

	public void beforeSwitchToWindow(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeGetText(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterGetText(WebElement element, WebDriver driver, String text) {
		// TODO Auto-generated method stub
		
	}

}
