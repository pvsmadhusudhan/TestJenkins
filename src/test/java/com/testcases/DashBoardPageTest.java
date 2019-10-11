/**
 * @author UmaMaheswararao
 */

package com.testcases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.BasePage;
import com.pages.DashBoardPage;
import com.pages.LoginPage;
import com.util.ExcelUtility;

public class DashBoardPageTest extends BasePage {
	
	LoginPage loginPage;
	DashBoardPage dashBoardPage;
	ExcelUtility reader;
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
		dashBoardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		reader = new ExcelUtility("./src/main/java/com/testdata/AuditsData.xlsx");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	public void verify_home_page_title_Test(){
		String dashBoardTitle = dashBoardPage.verify_home_page_title();
		Assert.assertTrue(dashBoardTitle.contains("Valuechain.com"),"Home page title not matched");
		Reporter.log("Title Verified - Test PASS", true);
	}

	@Test
	public void verify_Dashboard_link_Test(){
		Assert.assertTrue(dashBoardPage.verify_Dashboard_link(), "Dashboard Link Not Present - Test FAIL");
		Reporter.log("DashBoardLink Verified - Test PASS", true);
	}

	@Test(dependsOnMethods="verify_Dashboard_link_Test")
	public void verify_Filter_tasks_by_audit_name_Test() throws Exception {
		dashBoardPage.verify_Filter_tasks_by_audit_name();
	}

	@Test(dependsOnMethods="verify_Dashboard_link_Test")
	public void verify_Filter_Audits_and_Actions_available_in_the_dashboard_Test() throws Exception {
		dashBoardPage.verify_Filter_Audits_and_Actions_available_in_the_dashboard();
	}

	@Test(dependsOnMethods="verify_Dashboard_link_Test")
	public void Late_To_Upcoming_Activity_Functionality_Test() throws Exception {
		dashBoardPage.verify_Late_and_Upcoming_Activity_Functionality(reader.getCellData("Dashboard", 0, 1), 
				reader.getCellData("Dashboard", 1, 1), reader.getCellData("Dashboard", 2, 1), 
				reader.getCellData("Dashboard", 2, 1), reader.getCellData("Dashboard", 2, 1));
	}

	@Test(dependsOnMethods="verify_Dashboard_link_Test")
	public void Upcoming_To_Late_Activity_Functionality_Test() throws Exception {
		dashBoardPage.verify_Upcoming_To_Late_Activity_Functionality(reader.getCellData("Dashboard", 0, 2), 
				reader.getCellData("Dashboard", 1, 2), reader.getCellData("Dashboard", 2, 2), 
				reader.getCellData("Dashboard", 3, 2), reader.getCellData("Dashboard", 4, 2));
	}

	@Test(dependsOnMethods="verify_Dashboard_link_Test")
	public void Complete_Activity_Test() throws Exception {
		dashBoardPage.verify_Complte_Activity(reader.getCellData("Dashboard", 0, 3));
	}

	@Test(dependsOnMethods="verify_Dashboard_link_Test")
	public void verify_Postpone_activity_Test() throws Exception {
		dashBoardPage.verify_Postpone_activity(reader.getCellData("Dashboard", 0, 4),
				reader.getCellData("Dashboard", 1, 4));
	}

	@Test(dependsOnMethods="verify_Dashboard_link_Test")
	public void verify_Postpone_the_already_postponed_activity_to_previous_date_not_previous_than_current_date_Test() throws Exception {
		dashBoardPage.verify_Error_message_on_postponing_activity_without_description(reader.getCellData("Dashboard", 0, 5));
		Reporter.log("Postpone the already postponed activity is functional", true);
	}



	



}
