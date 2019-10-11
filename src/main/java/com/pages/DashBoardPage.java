/**
 * @author UmaMaheswararao
 */

package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.base.BasePage;
import com.util.AjaxLibrary;
import com.util.GenericLibrary;
import com.util.JavascriptLibrary;

public class DashBoardPage extends BasePage {
	// Objects Repository(OR):........................................................................
	// Home Page Links
	@FindBy(xpath="//span[contains(text(),'Dashboard')]")
	@CacheLookup
	WebElement dashBoardLink;

	@FindBy(xpath="//span[contains(text(),'Build')]")
	static WebElement buildLink;

	@FindBy(xpath="//span[@class='title'][contains(.,'Audit')]")
	static WebElement auditLink;

	@FindBy(xpath="//span[contains(text(),'Analyse')]")
	static WebElement analyseLink;
	
	@FindBy(xpath="//span[@class='title'][contains(.,'Actions')]")
	WebElement actionsLink;

	// DashBoard Tasks
	@FindBy(xpath = "//label[contains(@id,'Labactions')]")
	WebElement actionsBtn;
	
	@FindBy(xpath = "//label[contains(@id,'Labaudits')]")
	WebElement auditsBtn;

	@FindBy(xpath = "//a[contains(@id,'btnShowFilter')]")
	WebElement filterBtn;
	
	@FindBy(xpath = "//select[contains(@id,'ddlAudtisFilter')]")
	WebElement filterOptionDD;

	@FindBy(xpath=".//*[@id='btnActDate']/span/button")
	WebElement calenderBtn;

	@FindBy(xpath=".//*[@id='divA_DESC']")
	WebElement descriptionTxtBox;

	@FindBy(xpath=".//*[@id='A_DESC']")
	WebElement descriptionTxtBoxIn;

	@FindBy(xpath=".//*[@id='divA_ROOTCAUSE']")
	WebElement rootCauseTxtBox;

	@FindBy(xpath=".//*[@id='A_ROOTCAUSE']")
	WebElement rootCauseTxtBoxIn;
	
	@FindBy(xpath=".//*[@id='divA_CONTAINMENT']")
	WebElement containmentTxtBox;
	
	@FindBy(xpath=".//*[@id='A_CONTAINMENT']")
	WebElement containmentTxtBoxIn;
	
	@FindBy(xpath=".//*[@id='divA_CORRECTIVE']")
	WebElement correctiveActionTxtBox;
	
	@FindBy(xpath=".//*[@id='A_CORRECTIVE']")
	WebElement correctiveActionTxtBoxIn;

	@FindBy(xpath=".//*[@id='btnActPeople']")
	WebElement addPeopleBtn;

	@FindBy(xpath=".//*[@id='taskLate']")
	WebElement noOfLateTasks;

	@FindBy(xpath=".//*[@id='taskLive']")
	WebElement noOfUpcomingTasks;

	@FindBy(xpath=".//*[@id='taskUpcoming']")
	WebElement noOfLiveTasks;

	@FindBy(xpath=".//*[@id='taskCompleted']")
	WebElement noOfCompletedTasks;

	@FindBy(xpath="(//th[@class='next'])[1]")
	WebElement nextMonthBtn;

	@FindBy(xpath="(//th[@class='prev'])[1]")
	WebElement previousMonthBtn;

	@FindBy(xpath="//div[@class='bootbox-body'][contains(.,'Post poned to future date only.')]")
	WebElement postponeToPastWarningMessage;

	@FindBy(xpath="//div[text()='No Data']")
	WebElement noDataMessage;
	
	@FindBy(xpath = "//a[contains(@id,'btnComplete')]")
	WebElement completeBtn;
	
	@FindBy(xpath = "//textarea[contains(@id,'postponeReason')]")
	WebElement postponeReasonField;
	
	@FindBy(xpath = "//button[@type='button'][contains(.,'Ok')]")
	WebElement okBtn;

	@FindBy(xpath = "//span[contains(@id,'divError_postponeReason')]")
	WebElement postponeDescErrorMessage;
	
	
	


	// Initializing the Page Objects:................................................................
	public DashBoardPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions:......................................................................................
	public String verify_home_page_title(){
		return driver.getTitle();
	}

	// Navigating to DashBoard Page
	public boolean verify_Dashboard_link(){
		return dashBoardLink.isDisplayed();
	}

	public void verify_Filter_tasks_by_audit_name() {
		GenericLibrary.click(driver, filterBtn);
		GenericLibrary.waitForAjax(driver);
		List<WebElement> options = GenericLibrary.getAllOptionsFromTheDropdown(filterOptionDD);
		int allOptionCount = 0; 
		int sumOfAllOptionsCount = 0;
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getText().equals("All")) {
				AjaxLibrary.selectElementByIndex(driver, filterOptionDD, i);
				int liveCount = Integer.parseInt(noOfLiveTasks.getText());
				int completedCount = Integer.parseInt(noOfCompletedTasks.getText());
				allOptionCount = liveCount + completedCount;
				Reporter.log("'All' option count from all KPIs: "+allOptionCount, true);
			} else {
				AjaxLibrary.selectElementByIndex(driver, filterOptionDD, i);
				int liveCount = Integer.parseInt(noOfLiveTasks.getText());
				int completedCount = Integer.parseInt(noOfCompletedTasks.getText());
				int selectedOptionTotalCount = liveCount + completedCount;
				Reporter.log("KPI's count for the Audit - '"+options.get(i).getText()+"':"+selectedOptionTotalCount,true);
				sumOfAllOptionsCount = sumOfAllOptionsCount + selectedOptionTotalCount;
			}
		}
		Reporter.log("Sum of all options count except 'All' option: "+sumOfAllOptionsCount, true);
		// 'All' option count must be equal to the sum of the total of remaining all options 
		Assert.assertEquals(sumOfAllOptionsCount, allOptionCount, "Filter is NOT working");
		Reporter.log("Filter is working fine", true);
	}
	
	public void verify_Filter_Audits_and_Actions_available_in_the_dashboard() throws Exception{
		// Default results Audits + Actions form KPIs
		int liveTasks = Integer.parseInt(noOfLiveTasks.getText());
		int completedTasks = Integer.parseInt(noOfCompletedTasks.getText());
		int defaultTotal = liveTasks + completedTasks;
		Reporter.log("Default total of Audits & Actions "+defaultTotal, true);
		// Audits filter results from KPIs
		GenericLibrary.click(driver, auditsBtn);
		GenericLibrary.waitForAjax(driver);
		int auditsLiveTasks = Integer.parseInt(noOfLiveTasks.getText());
		int auditsCompletedTasks = Integer.parseInt(noOfCompletedTasks.getText());
		int auditsTotal = auditsLiveTasks + auditsCompletedTasks;
		Reporter.log("Audits filter result: "+auditsTotal, true);
		// UnCheck Audits filter
		GenericLibrary.click(driver, auditsBtn);
		GenericLibrary.waitForAjax(driver);
		// Actions filter results from KPIs
		GenericLibrary.click(driver, actionsBtn);
		GenericLibrary.waitForAjax(driver);
		int actionsLiveTasks = Integer.parseInt(noOfLiveTasks.getText());
		int actionsCompletedTasks = Integer.parseInt(noOfCompletedTasks.getText());
		int actionsTotal = actionsLiveTasks + actionsCompletedTasks;
		Reporter.log("Actions filter result: "+actionsTotal, true);
		// Both filters total
		int filtersTotal = auditsTotal + actionsTotal;
		Reporter.log("Filters total of Audits & Actions "+filtersTotal, true);
		// Verification
		Assert.assertEquals(defaultTotal, filtersTotal, "Audits & Actions filters are NOT working");
		Reporter.log("Audits & Actions filters are working fine", true);
	}

	public void verify_Late_and_Upcoming_Activity_Functionality(String date, String description, 
			String rootCause, String containmentAction, String correctiveAction) throws Exception {
		// Late to Upcoming
		JavascriptLibrary.javascriptClickElement(driver, actionsBtn);
		GenericLibrary.waitForAjax(driver);
		String lateActions = noOfLateTasks.getText();
		String upcomingActions = noOfUpcomingTasks.getText();
		GenericLibrary.click(driver, noOfLateTasks);
		GenericLibrary.waitForAjax(driver);
		if (!lateActions.equalsIgnoreCase("0")) {
			List<WebElement> searchResult = driver.findElements(By.xpath("(//div[@class='mt-action-icon ']//i)"));
			AjaxLibrary.jsClick(driver, searchResult.get(0));
			AjaxLibrary.click(driver, calenderBtn);
			JavascriptLibrary.javascriptClickElement(driver, nextMonthBtn);
			List<WebElement> dayList = driver.findElements(By.cssSelector(".day"));
			for (WebElement ele : dayList) {
				String text = ele.getText();
				if (text.contains(date.replace(".0", ""))) {
					JavascriptLibrary.javascriptClickElement(driver, ele);
					GenericLibrary.waitForAjax(driver);
					break;
				}
			}
			GenericLibrary.click(driver, descriptionTxtBox);
			descriptionTxtBoxIn.clear();
			descriptionTxtBoxIn.sendKeys(description);
			descriptionTxtBoxIn.sendKeys(Keys.TAB);
			GenericLibrary.waitForAjax(driver);
			GenericLibrary.scrollIntoView(driver, rootCauseTxtBox);
			GenericLibrary.click(driver, rootCauseTxtBox);
			rootCauseTxtBoxIn.clear();
			rootCauseTxtBoxIn.sendKeys(rootCause);
			rootCauseTxtBoxIn.sendKeys(Keys.TAB);
			GenericLibrary.waitForAjax(driver);
			GenericLibrary.scrollIntoView(driver, containmentTxtBox);
			GenericLibrary.click(driver, containmentTxtBox);
			containmentTxtBoxIn.clear();
			containmentTxtBoxIn.sendKeys(containmentAction);
			containmentTxtBoxIn.sendKeys(Keys.TAB);
			GenericLibrary.waitForAjax(driver);
			GenericLibrary.scrollIntoView(driver, correctiveActionTxtBox);
			GenericLibrary.click(driver, correctiveActionTxtBox);
			correctiveActionTxtBoxIn.clear();
			correctiveActionTxtBoxIn.sendKeys(correctiveAction);
			correctiveActionTxtBoxIn.sendKeys(Keys.TAB);
			GenericLibrary.waitForAjax(driver);
			
			String lateActions_2 = noOfLateTasks.getText();
			String upcomingActions_2 = noOfUpcomingTasks.getText();
			Reporter.log("********* Late Activity To Upcoming Activity ********", true);
			Reporter.log("========= Before Updating ===========", true);
			Reporter.log("Late Actions: " + lateActions + " And " + "Upcoming Actions: " + upcomingActions, true);
			Reporter.log("========= After Updating ===========", true);
			Reporter.log("Late Actions: " + lateActions_2 + " And " + "Upcoming Actions: " + upcomingActions_2, true);
			Assert.assertTrue(Integer.parseInt(upcomingActions_2) > Integer.parseInt(upcomingActions), "Changing late to upcoming activity is not functional");
			Reporter.log("Changing late to upcoming activity is functional", true);
		} else {
			Assert.assertTrue(noDataMessage.isDisplayed(), "'No data found' but No promt message displayed");
			Reporter.log("No Data found", true);
		}
	}

	public void verify_Upcoming_To_Late_Activity_Functionality(String date, String description, 
			String rootCause, String containmentAction, String correctiveAction) throws Exception {
		// Upcoming to Late
		JavascriptLibrary.javascriptClickElement(driver, actionsBtn);
		GenericLibrary.waitForAjax(driver);
		String lateActions = noOfLateTasks.getText();
		String upcomingActions = noOfUpcomingTasks.getText();
		GenericLibrary.click(driver, noOfUpcomingTasks);
		GenericLibrary.waitForAjax(driver);
		if (!upcomingActions.equalsIgnoreCase("0")) {
			List<WebElement> searchResult = driver.findElements(By.xpath("(//div[@class='mt-action-icon ']//i)"));
			AjaxLibrary.jsClick(driver, searchResult.get(0));
			AjaxLibrary.click(driver, calenderBtn);
			JavascriptLibrary.javascriptClickElement(driver, previousMonthBtn);
			List<WebElement> dayList = driver.findElements(By.cssSelector(".day"));
			for (WebElement ele : dayList) {
				String text = ele.getText();
				if (text.contains(date.replace(".0", ""))) {
					JavascriptLibrary.javascriptClickElement(driver, ele);
					GenericLibrary.waitForAjax(driver);
					break;
				}
			}
			GenericLibrary.click(driver, descriptionTxtBox);
			descriptionTxtBoxIn.clear();
			descriptionTxtBoxIn.sendKeys(description);
			descriptionTxtBoxIn.sendKeys(Keys.TAB);
			GenericLibrary.waitForAjax(driver);
			GenericLibrary.scrollIntoView(driver, rootCauseTxtBox);
			GenericLibrary.click(driver, rootCauseTxtBox);
			rootCauseTxtBoxIn.clear();
			rootCauseTxtBoxIn.sendKeys(rootCause);
			rootCauseTxtBoxIn.sendKeys(Keys.TAB);
			GenericLibrary.waitForAjax(driver);
			GenericLibrary.scrollIntoView(driver, containmentTxtBox);
			GenericLibrary.click(driver, containmentTxtBox);
			containmentTxtBoxIn.clear();
			containmentTxtBoxIn.sendKeys(containmentAction);
			containmentTxtBoxIn.sendKeys(Keys.TAB);
			GenericLibrary.waitForAjax(driver);
			GenericLibrary.scrollIntoView(driver, correctiveActionTxtBox);
			GenericLibrary.click(driver, correctiveActionTxtBox);
			correctiveActionTxtBoxIn.clear();
			correctiveActionTxtBoxIn.sendKeys(correctiveAction);
			correctiveActionTxtBoxIn.sendKeys(Keys.TAB);
			GenericLibrary.waitForAjax(driver);
			
			String lateActions_2 = noOfLateTasks.getText();
			String upcomingActions_2 = noOfUpcomingTasks.getText();
			Reporter.log("********* UpcomingAction To LateAction ********", true);
			Reporter.log("========= Before Updating ============", true);
			Reporter.log("Late Actions: " + lateActions + " And " + "Upcoming Actions: " + upcomingActions, true);
			Reporter.log("========= After Updating ============", true);
			Reporter.log("Late Actions: " + lateActions_2 + " And " + "Upcoming Actions: " + upcomingActions_2, true);
			Assert.assertTrue(Integer.parseInt(upcomingActions_2) < Integer.parseInt(upcomingActions), "Changing upcoming to late activity is not functional");
			Reporter.log("Changing upcoming to late activity is functional", true);
		} else {
			Assert.assertTrue(noDataMessage.isDisplayed(), "'No data found' but No promt message displayed");
			Reporter.log("No Data found", true);
		}
	}

	public void verify_Complte_Activity(String description) throws Exception {
		// Live to Completed
		JavascriptLibrary.javascriptClickElement(driver, actionsBtn);
		GenericLibrary.waitForAjax(driver);
		String live = noOfLiveTasks.getText();
		String completed = noOfCompletedTasks.getText();
		GenericLibrary.click(driver, noOfLiveTasks);
		GenericLibrary.waitForAjax(driver);
		if (!live.equalsIgnoreCase("0")) {
			List<WebElement> searchResult = driver.findElements(By.xpath("(//div[@class='mt-action-icon ']//i)"));
			GenericLibrary.click(driver, searchResult.get(0));
			GenericLibrary.waitForAjax(driver);
			AjaxLibrary.jsClickClearAndType(driver, descriptionTxtBox, descriptionTxtBoxIn, "Completed");
			AjaxLibrary.jsClickClearAndType(driver, rootCauseTxtBox, rootCauseTxtBoxIn, "Completed");
			AjaxLibrary.jsClickClearAndType(driver, containmentTxtBox, containmentTxtBoxIn, "Completed");
			AjaxLibrary.jsClickClearAndType(driver, correctiveActionTxtBox, correctiveActionTxtBoxIn, "Completed");
			GenericLibrary.click(driver, completeBtn);
			GenericLibrary.waitForAjax(driver);
			String live2 = noOfLiveTasks.getText();
			String completed2 = noOfCompletedTasks.getText();
			Reporter.log("********* LiveAction To CompletedAction ********", true);
			Reporter.log("========= Before Updating ============", true);
			Reporter.log("Live Actions: " + live + " And " + "Completed Actions: " + completed, true);
			Reporter.log("========= After Updating ============", true);
			Reporter.log("Live Actions: " + live2 + " And " + "Completed Actions: " + completed2, true);
			Assert.assertTrue(Integer.parseInt(completed2) > Integer.parseInt(completed),
					"Complete activity is not functional");
			Reporter.log("Complete activity is functional", true);
		} else {
			Assert.assertTrue(noDataMessage.isDisplayed(), "No data found but No promt message displayed");
			Reporter.log("No Data found", true);
		}
	}

	public void verify_Postpone_activity(String reason, String date) throws Exception {
		AjaxLibrary.jsClick(driver, actionsBtn);
		int lateCount = Integer.parseInt(noOfLateTasks.getText());
		int upcomingCount = Integer.parseInt(noOfUpcomingTasks.getText());
		Reporter.log("No.of late tasks on KPI before postpone: "+lateCount, true);
		Reporter.log("No.of upcoming tasks on KPI before postpone: "+upcomingCount, true);
		AjaxLibrary.jsClick(driver, noOfLateTasks);
		if (lateCount == 0) {
			Assert.assertTrue(noDataMessage.isDisplayed(),"'No data' message NOT displayed");
			Reporter.log("'No data' message displayed for zero records on KPI", true);
		} else {
			List<WebElement> lateRecords = driver.findElements(By.xpath("//button[text()='Postpone']"));
			JavascriptLibrary.javascriptClickElement(driver, lateRecords.get(0));
			GenericLibrary.waitForAjax(driver);
			postponeReasonField.sendKeys(reason);
			GenericLibrary.click(driver, nextMonthBtn);
			List<WebElement> dateList = driver.findElements(By.cssSelector(".day"));
			for (WebElement ele : dateList) {
				if(ele.getText().equals(date.replace(".0", ""))) {
					GenericLibrary.click(driver, ele);
					break;
				}
			}
			GenericLibrary.waitForAjax(driver);
			JavascriptLibrary.javascriptClickElement(driver, okBtn);
			GenericLibrary.waitForAjax(driver);
			int lateCount2 = Integer.parseInt(noOfLateTasks.getText());
			int upcomingCount2 = Integer.parseInt(noOfUpcomingTasks.getText());
			Reporter.log("No.of late actions on KPI after postpone: "+lateCount2, true);
			Reporter.log("No.of upcoming actions on KPI after postpone: "+upcomingCount2, true);
			Assert.assertTrue((lateCount2 < lateCount) && (upcomingCount2 > upcomingCount), "Activity NOT postponed"); 
			Reporter.log("Activity postponed successfully", true);
		}
	}

	public void verify_Error_message_on_postponing_activity_without_description(String message) throws Exception {
		AjaxLibrary.jsClick(driver, actionsBtn);
		int lateCount = Integer.parseInt(noOfLateTasks.getText());
		AjaxLibrary.jsClick(driver, noOfLateTasks);
		if (lateCount == 0) {
			Assert.assertTrue(noDataMessage.isDisplayed(),"'No data' message NOT displayed");
			Reporter.log("'No data' message displayed for zero records on KPI", true);
		} else {
			List<WebElement> lateRecords = driver.findElements(By.xpath("//button[text()='Postpone']"));
			AjaxLibrary.jsClick(driver, lateRecords.get(0));
			AjaxLibrary.click(driver, okBtn);
			Assert.assertTrue(postponeDescErrorMessage.getText().equalsIgnoreCase(message), "No error message on postpone without description");
			Reporter.log("Error message displayed on postponing activity without description", true);		}
	}

}
