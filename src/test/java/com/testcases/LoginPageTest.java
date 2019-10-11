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

public class LoginPageTest extends BasePage {
	
	LoginPage loginPage;
	DashBoardPage dashBoardPage;
	
	@BeforeMethod
	public void setUp() {
		initialization();
		loginPage = new LoginPage();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	
	@Test
	public void LoginPage_Title_Test(){
		String title = loginPage.validate_LoginPage_Title();
		Assert.assertEquals(title, "login");
		Reporter.log("LoginPage Title Verified", true);
		
		
		System.out.println("Test finished");
	}

	@Test(dependsOnMethods="LoginPage_Title_Test")
	public void Login_Test() throws Exception{
		dashBoardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Reporter.log("User Loggedin Successfully & Dash Board Page Opened", true);
	}

	@Test(dependsOnMethods="LoginPage_Title_Test")
	public void verify_Valuechain_wesite_navigation_on_clicking_Valuechain_logo_Test() throws Exception{
		loginPage.verify_Valuechain_wesite_navigation_on_clicking_Valuechain_logo("Valuechain | Smart Manufacturing Software to Create World-Class Supply Chains");
		Reporter.log("User able to navigate valuechain website on clicking Valuechain log", true);
	}


	/*
	@DataProvider
	public Iterator<Object[]> getLoginTestData() {
		ArrayList<Object[]> testData = Container.getLoginDataFromExcel();
		return testData.iterator();
	}

	@Test(dataProvider="getLoginTestData")
	public void verify_Login_form(String username, String password) throws Exception{
		loginPage.verify_Login_form(username, password);
		Reporter.log("User Loggedin Successfully & DashBoard Page Opened", true);
	}
	*/				












}
