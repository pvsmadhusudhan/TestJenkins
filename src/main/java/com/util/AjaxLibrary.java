package com.util;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class AjaxLibrary {
	
	public static void highlightElement(WebDriver driver, WebElement element) throws Exception {
		((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid yellow'", element); // highlight
		Thread.sleep(300); // delay between highlight and unhighlight 
		((JavascriptExecutor)driver).executeScript("arguments[0].style.border=''", element); // unhighlight
	}

	public static void waitForAjax(WebDriver driver) {
		new WebDriverWait(driver, 180).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				return (Boolean) js.executeScript("return jQuery.active == 0");
			}
		});
	}

	public static <T> void jsClick(WebDriver driver, T elementAttr){
		if(elementAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)elementAttr);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		} else {
			WebElement ele = ((WebElement)elementAttr);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		}
		waitForAjax(driver);
	}

	public static <T> void jsType(WebDriver driver, T elementAttr, String value){
		if(elementAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)elementAttr);
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+value+"'", ele);
			ele.sendKeys(Keys.TAB);
		} else {
			WebElement ele = ((WebElement)elementAttr);
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+value+"'", ele);
			ele.sendKeys(Keys.TAB);
		}
		waitForAjax(driver);
	}

	public static <T> void sendKeys(WebDriver driver, T elementAttr, int timeout, String value) {
		if(elementAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)elementAttr);
			new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(ele));
			ele.sendKeys(value);
			ele.sendKeys(Keys.TAB);
		} else {
			WebElement ele = ((WebElement)elementAttr);
			new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(ele));
			ele.sendKeys(value);
			ele.sendKeys(Keys.TAB);
		}
		waitForAjax(driver);
	}

	public static <T> void type(WebDriver driver, T elementAttr, String value) {
		if(elementAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)elementAttr);
			ele.sendKeys(value);
			ele.sendKeys(Keys.TAB);
		} else {
			WebElement ele = ((WebElement)elementAttr);
			ele.sendKeys(value);
			ele.sendKeys(Keys.TAB);
		}
		waitForAjax(driver);
	}

	public static <T> void clearAndType(WebDriver driver, T elementAttr, String value) throws Exception {
		if(elementAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)elementAttr);
			ele.clear();
			ele.sendKeys(value);
			ele.sendKeys(Keys.TAB);
		} else {
			WebElement ele = ((WebElement)elementAttr);
			ele.clear();
			ele.sendKeys(value);
			ele.sendKeys(Keys.TAB);
		}
		waitForAjax(driver);
	}

	public static <T> void clickClearAndType(WebDriver driver, T eleAttr1, T eleAttr2, String value) throws Exception {
		if(eleAttr1.getClass().getName().contains("By") && eleAttr2.getClass().getName().contains("By")) {
			WebElement ele1 = driver.findElement((By)eleAttr1);
			WebElement ele2 = driver.findElement((By)eleAttr2);
			ele1.click();
			Thread.sleep(500);
			ele2.clear();
			ele2.sendKeys(value);
			ele2.sendKeys(Keys.TAB);
		} else {
			WebElement ele1 = ((WebElement)eleAttr1);
			WebElement ele2 = ((WebElement)eleAttr2);
			ele1.click();
			Thread.sleep(500);
			ele2.clear();
			ele2.sendKeys(value);
			ele2.sendKeys(Keys.TAB);
		}
		waitForAjax(driver);
	}
	
	public static <T> void clickSelectAndType(WebDriver driver, T eleAttr1, T eleAttr2, String value) throws Exception {
		if(eleAttr1.getClass().getName().contains("By") && eleAttr2.getClass().getName().contains("By")) {
			WebElement ele1 = driver.findElement((By)eleAttr1);
			WebElement ele2 = driver.findElement((By)eleAttr2);
			ele1.click();
			ele2.sendKeys(Keys.CONTROL+"a");
			ele2.sendKeys(value);
			ele2.sendKeys(Keys.TAB);
		} else {
			WebElement ele1 = ((WebElement)eleAttr1);
			WebElement ele2 = ((WebElement)eleAttr2);
			ele1.click();
			ele2.sendKeys(Keys.CONTROL+"a");
			ele2.sendKeys(value);
			ele2.sendKeys(Keys.TAB);
		}
		waitForAjax(driver);
	}

	public static <T> void clickClearAndType(WebDriver driver, T eleAttr, String value) throws Exception {
		if(eleAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)eleAttr);
			ele.click();
			Thread.sleep(500);
			ele.clear();
			ele.sendKeys(value);
			ele.sendKeys(Keys.TAB);
		} else {
			WebElement ele = ((WebElement)eleAttr);
			ele.click();
			Thread.sleep(500);
			ele.clear();
			ele.sendKeys(value);
			ele.sendKeys(Keys.TAB);
		}
		waitForAjax(driver);
	}

	public static <T> void jsClickClearAndType(WebDriver driver, T eleAttr1, T eleAttr2, String value) throws Exception {
		if(eleAttr1.getClass().getName().contains("By") && eleAttr2.getClass().getName().contains("By")) {
			WebElement ele1 = driver.findElement((By)eleAttr1);
			WebElement ele2 = driver.findElement((By)eleAttr2);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele1);
			Thread.sleep(500);
			ele2.clear();
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+value+"'",ele2);
			ele2.sendKeys(Keys.TAB);
		} else {
			WebElement ele1 = ((WebElement)eleAttr1);
			WebElement ele2 = ((WebElement)eleAttr2);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele1);
			Thread.sleep(500);
			ele2.clear();
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+value+"'",ele2);
			ele2.sendKeys(Keys.TAB);
		}
		waitForAjax(driver);
	}
	
	public static <T> void jsClickSelectAndType(WebDriver driver, T eleAttr1, T eleAttr2, String value) throws Exception {
		if(eleAttr1.getClass().getName().contains("By") && eleAttr2.getClass().getName().contains("By")) {
			WebElement ele1 = driver.findElement((By)eleAttr1);
			WebElement ele2 = driver.findElement((By)eleAttr2);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele1);
			ele2.sendKeys(Keys.CONTROL+"a");
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+value+"'",ele2);
			ele2.sendKeys(Keys.TAB);
		} else {
			WebElement ele1 = ((WebElement)eleAttr1);
			WebElement ele2 = ((WebElement)eleAttr2);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele1);
			ele2.sendKeys(Keys.CONTROL+"a");
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+value+"'",ele2);
			ele2.sendKeys(Keys.TAB);
		}
		waitForAjax(driver);
	}

	public static <T> void jsClickClearAndType(WebDriver driver, T eleAttr, String value) throws Exception {
		if(eleAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)eleAttr);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
			Thread.sleep(500);
			ele.clear();
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+value+"'",ele);
			ele.sendKeys(Keys.TAB);
		} else {
			WebElement ele = ((WebElement)eleAttr);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
			Thread.sleep(500);
			ele.clear();
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+value+"'",ele);
			ele.sendKeys(Keys.TAB);
		}
		waitForAjax(driver);
	}

	public static <T> void clickOn(WebDriver driver, T element, int timeout) {
		if (element.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)element);
			new WebDriverWait(driver, timeout).
			until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
		} else {
			WebElement ele = ((WebElement)element);
			new WebDriverWait(driver, timeout).
			until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
		}
		waitForAjax(driver);
	}

	public static <T> void click(WebDriver driver, T element) {
		if (element.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)element);
			try {
				(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(ele));
				ele.click();
			}
			catch (StaleElementReferenceException  e) {
				// simply retry finding the element in the refreshed DOM
				ele.click();
			}
			catch (WebDriverException e) {
				ele.click();
			}
		} else {
			WebElement ele = ((WebElement)element);
			try {
				(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(ele));
				ele.click();
			}
			catch (StaleElementReferenceException  e) {
				// simply retry finding the element in the refreshed DOM
				ele.click();
			}
			catch (WebDriverException e) {
				ele.click();
			}
		}
		waitForAjax(driver);
	}

	public static <T> void actionClick(WebDriver driver, T eleAttr) {
		Actions act = new Actions(driver);
		if(eleAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)eleAttr);
			act.moveToElement(ele).click().build().perform();
		} else {
			WebElement ele = ((WebElement)eleAttr);
			act.moveToElement(ele).click().build().perform();
		}
		waitForAjax(driver);
	}

	public static <T> void actionClickAtParticularPartOfElementWrtXY(WebDriver driver, T eleAttr, int x, int y) {
		Actions action= new Actions(driver);
		if(eleAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)eleAttr);
			action.moveToElement(ele).moveByOffset(x, y).click().build().perform();
		} else {
			WebElement ele = ((WebElement)eleAttr);
			action.moveToElement(ele).moveByOffset(x, y).click().build().perform();
		}
		waitForAjax(driver);
	}

	public static <T> void StaleElementClick(WebDriver driver, T eleAttr) {
		for(int i=0; i<=3;i++){
			try{
				if(eleAttr.getClass().getName().contains("By")) {
					WebElement ele = driver.findElement((By)eleAttr);
					ele.click();
				} else {
					WebElement ele = ((WebElement)eleAttr);
					ele.click();
				}
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		waitForAjax(driver);
	}

	public static <T> void clickMultipleElements(WebDriver driver, T someElement, T someOtherElement) {
		Actions builder = new Actions(driver);
		if(someElement.getClass().getName().contains("By") && someOtherElement.getClass().getName().contains("By")) {
			WebElement ele1 = driver.findElement((By)someElement);
			WebElement ele2 = driver.findElement((By)someOtherElement);
			builder.keyDown(Keys.CONTROL).click(ele1)
			.click(ele2).keyUp(Keys.CONTROL).build().perform();
		} else {
			WebElement ele1 = ((WebElement)someElement);
			WebElement ele2 = ((WebElement)someOtherElement);
			builder.keyDown(Keys.CONTROL).click(ele1)
			.click(ele2).keyUp(Keys.CONTROL).build().perform();
		}
		waitForAjax(driver);
	}

	public static boolean checkAlert_Accept(WebDriver driver) {
		try {
			Alert a = driver.switchTo().alert();
			String str = a.getText();
			Reporter.log("Alert message is: "+str, true);
			a.accept();
			waitForAjax(driver);
			return true;
		} catch (Exception e) {
			Reporter.log("No alert present", true);
			return false;
		}
	}

	public static boolean checkAlert_Dismiss(WebDriver driver) {
		try {
			Alert a = driver.switchTo().alert();
			String str = a.getText();
			Reporter.log("Alert message is: "+str, true);
			a.dismiss();
			waitForAjax(driver);
			return true;
		} catch (Exception e) {
			Reporter.log("No alert present", true);
			return false;
		}
	}

	public static <T> void checkbox_Checking(WebDriver driver, T eleAttr) {
		boolean checkstatus;
		if(eleAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)eleAttr);
			checkstatus = ele.isSelected();
			if (checkstatus == true) {
				Reporter.log("Checkbox is already checked", true);
			} else {
				ele.click();
				Reporter.log("Checked the checkbox", true);
			}
		} else {
			WebElement ele = ((WebElement)eleAttr);
			checkstatus = ele.isSelected();
			if (checkstatus == true) {
				Reporter.log("Checkbox is already checked", true);
			} else {
				ele.click();
				Reporter.log("Checked the checkbox", true);
			}
		}
		waitForAjax(driver);
	}

	public static <T> void radiobutton_Select(WebDriver driver, T eleAttr) {
		boolean checkstatus;
		if(eleAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)eleAttr);
			checkstatus = ele.isSelected();
			if (checkstatus == true) {
				Reporter.log("RadioButton is already checked", true);
			} else {
				ele.click();
				Reporter.log("Selected the Radiobutton", true);
			}
		} else {
			WebElement ele = ((WebElement)eleAttr);
			checkstatus = ele.isSelected();
			if (checkstatus == true) {
				Reporter.log("RadioButton is already checked", true);
			} else {
				ele.click();
				Reporter.log("Selected the Radiobutton", true);
			}
		}
		waitForAjax(driver);
	}

	public static <T> void checkbox_Unchecking(WebDriver driver, T eleAttr) {
		boolean checkstatus;
		if(eleAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)eleAttr);
			checkstatus = ele.isSelected();
			if (checkstatus == true) {
				ele.click();
				Reporter.log("Checkbox is unchecked", true);
			} else {
				Reporter.log("Checkbox is already unchecked", true);
			}
		} else {
			WebElement ele = ((WebElement)eleAttr);
			checkstatus = ele.isSelected();
			if (checkstatus == true) {
				ele.click();
				Reporter.log("Checkbox is unchecked", true);
			} else {
				Reporter.log("Checkbox is already unchecked", true);
			}
		}
		waitForAjax(driver);
	}

	public static <T> void radioButton_Deselect(WebDriver driver, T eleAttr) {
		boolean checkstatus;
		if(eleAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)eleAttr);
			checkstatus = ele.isSelected();
			if (checkstatus == true) {
				ele.click();
				Reporter.log("Radio Button is deselected", true);
			} else {
				Reporter.log("Radio Button was already Deselected", true);
			}
		} else {
			WebElement ele = ((WebElement)eleAttr);
			checkstatus = ele.isSelected();
			if (checkstatus == true) {
				ele.click();
				Reporter.log("Radio Button is deselected", true);
			} else {
				Reporter.log("Radio Button was already Deselected", true);
			}
		}
		waitForAjax(driver);
	}

	public static <T> void dragAndDrop(WebDriver driver, T fromWebElementAttr, T toWebElementAttr) {
		Actions builder = new Actions(driver);
		if(fromWebElementAttr.getClass().getName().contains("By") && toWebElementAttr.getClass().getName().contains("By")) {
			WebElement ele1 = driver.findElement((By)fromWebElementAttr);
			WebElement ele2 = driver.findElement((By)toWebElementAttr);
			builder.dragAndDrop(ele1, ele2).perform();
		} else {
			WebElement ele1 = ((WebElement)fromWebElementAttr);
			WebElement ele2 = ((WebElement)toWebElementAttr);
			builder.dragAndDrop(ele1, ele2).perform();
		}
		waitForAjax(driver);
	}

	public static <T> void dragAndDrop_Method2(WebDriver driver, T fromWebElementAttr, T toWebElementAttr) {
		Actions builder = new Actions(driver);
		if(fromWebElementAttr.getClass().getName().contains("By") && toWebElementAttr.getClass().getName().contains("By")) {
			WebElement ele1 = driver.findElement((By)fromWebElementAttr);
			WebElement ele2 = driver.findElement((By)toWebElementAttr);
			Action dragAndDrop = builder.clickAndHold(ele1).moveToElement(ele2).release(ele2).build();
			dragAndDrop.perform();
		} else {
			WebElement ele1 = ((WebElement)fromWebElementAttr);
			WebElement ele2 = ((WebElement)toWebElementAttr);
			Action dragAndDrop = builder.clickAndHold(ele1).moveToElement(ele2).release(ele2).build();
			dragAndDrop.perform();
		}
		waitForAjax(driver);
	}
	
	public static <T> void dragAndDrop_Method3(WebDriver driver, T fromWebElementAttr, T toWebElementAttr) throws InterruptedException {
		Actions builder = new Actions(driver);
		if(fromWebElementAttr.getClass().getName().contains("By") && toWebElementAttr.getClass().getName().contains("By")) {
			WebElement ele1 = driver.findElement((By)fromWebElementAttr);
			WebElement ele2 = driver.findElement((By)toWebElementAttr);
			builder.clickAndHold(ele1).moveToElement(ele2).perform();
			Thread.sleep(2000);
			builder.release(ele2).build().perform();
		} else {
			WebElement ele1 = ((WebElement)fromWebElementAttr);
			WebElement ele2 = ((WebElement)toWebElementAttr);
			builder.clickAndHold(ele1).moveToElement(ele2).perform();
			Thread.sleep(2000);
			builder.release(ele2).build().perform();
		}
		waitForAjax(driver);
	}

	public static <T> void doubleClickWebelement(WebDriver driver, T eleAttr)throws InterruptedException {
		Actions builder = new Actions(driver);
		if(eleAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)eleAttr);
			builder.doubleClick(ele).perform();
			Thread.sleep(2000);
		} else {
			WebElement ele = ((WebElement)eleAttr);
			builder.doubleClick(ele).perform();
			Thread.sleep(2000);
		}
		waitForAjax(driver);
	}
	
	public static <T> void selectElementByVisibleText(WebDriver driver, T eleAttr, String Name) {
		if(eleAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)eleAttr);
			Select selectitem = new Select(ele);
			selectitem.selectByVisibleText(Name);
		} else {
			WebElement ele = ((WebElement)eleAttr);
			Select selectitem = new Select(ele);
			selectitem.selectByVisibleText(Name);
		}
		waitForAjax(driver);
	}
	
	public static <T> void selectElementByValue(WebDriver driver, T eleAttr, String value) {
		if(eleAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)eleAttr);
			Select selectitem = new Select(ele);
			selectitem.selectByValue(value);
		} else {
			WebElement ele = ((WebElement)eleAttr);
			Select selectitem = new Select(ele);
			selectitem.selectByValue(value);
		}
		waitForAjax(driver);
	}
	
	public static <T> void selectElementByIndex(WebDriver driver, T eleAttr, int index) {
		if(eleAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)eleAttr);
			Select selectitem = new Select(ele);
			selectitem.selectByIndex(index);
		} else {
			WebElement ele = ((WebElement)eleAttr);
			Select selectitem = new Select(ele);
			selectitem.selectByIndex(index);
		}
		waitForAjax(driver);
	}

	public static void clickCheckboxFromList(WebDriver driver, String xpathOfElement, String valueToSelect) {
		List<WebElement> lst = driver.findElements(By.xpath(xpathOfElement));
		for (int i = 0; i < lst.size(); i++) {
			List<WebElement> dr = lst.get(i).findElements(By.tagName("label"));
			for (WebElement f : dr) {
				Reporter.log("value in the list : " + f.getText(),true);
				if (valueToSelect.equals(f.getText())) {
					f.click();
					waitForAjax(driver);
					break;
				}
			}
		}
	}

	public static <T> void uploadFile(WebDriver driver, T browseButton, String filePath) throws Exception {
		if(browseButton.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)browseButton);
			StringSelection sel2 = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel2,null);
			Reporter.log("Selection: "+sel2, true);
			Thread.sleep(1000);
			ele.click();
			Reporter.log("Browse button clicked", true);
			Robot robot2 = new Robot();
			Thread.sleep(2000);
			robot2.keyPress(KeyEvent.VK_ENTER);
			robot2.keyRelease(KeyEvent.VK_ENTER);
			robot2.keyPress(KeyEvent.VK_CONTROL);
			robot2.keyPress(KeyEvent.VK_V);
			robot2.keyRelease(KeyEvent.VK_CONTROL);
			robot2.keyRelease(KeyEvent.VK_V);
			Thread.sleep(1000);
			robot2.keyPress(KeyEvent.VK_ENTER);
			robot2.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
		} else {
			WebElement ele = ((WebElement)browseButton);
			StringSelection sel2 = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel2,null);
			Reporter.log("Selection: "+sel2, true);
			Thread.sleep(2000);
			ele.click();
			Reporter.log("Browse button clicked", true);
			Robot robot2 = new Robot();
			Thread.sleep(1500);
			robot2.keyPress(KeyEvent.VK_ENTER);
			robot2.keyRelease(KeyEvent.VK_ENTER);
			robot2.keyPress(KeyEvent.VK_CONTROL);
			robot2.keyPress(KeyEvent.VK_V);
			robot2.keyRelease(KeyEvent.VK_CONTROL);
			robot2.keyRelease(KeyEvent.VK_V);
			Thread.sleep(1000);
			robot2.keyPress(KeyEvent.VK_ENTER);
			robot2.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
		}
		waitForAjax(driver);
	}
	
	public static <T> void uploadFile2(WebDriver driver, T browseButton, String filePath) throws Exception {
		if(browseButton.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)browseButton);
			StringSelection sel2 = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel2,null);
			Reporter.log("Selection: "+sel2, true);
			Thread.sleep(1000);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
			Reporter.log("Browse button clicked", true);
			Robot robot2 = new Robot();
			Thread.sleep(2000);
			robot2.keyPress(KeyEvent.VK_ENTER);
			robot2.keyRelease(KeyEvent.VK_ENTER);
			robot2.keyPress(KeyEvent.VK_CONTROL);
			robot2.keyPress(KeyEvent.VK_V);
			robot2.keyRelease(KeyEvent.VK_CONTROL);
			robot2.keyRelease(KeyEvent.VK_V);
			Thread.sleep(1000);
			robot2.keyPress(KeyEvent.VK_ENTER);
			robot2.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
		} else {
			WebElement ele = ((WebElement)browseButton);
			StringSelection sel2 = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel2,null);
			Reporter.log("Selection: "+sel2, true);
			Thread.sleep(1000);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
			Reporter.log("Browse button clicked", true);
			Robot robot2 = new Robot();
			Thread.sleep(2000);
			robot2.keyPress(KeyEvent.VK_ENTER);
			robot2.keyRelease(KeyEvent.VK_ENTER);
			robot2.keyPress(KeyEvent.VK_CONTROL);
			robot2.keyPress(KeyEvent.VK_V);
			robot2.keyRelease(KeyEvent.VK_CONTROL);
			robot2.keyRelease(KeyEvent.VK_V);
			Thread.sleep(1000);
			robot2.keyPress(KeyEvent.VK_ENTER);
			robot2.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);
		}
		waitForAjax(driver);
	}
	
	public static <T> void scrollIntoView(WebDriver driver, T eleAttr) {
		if(eleAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)eleAttr);
			JavascriptExecutor js=(JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", ele);
		} else {
			WebElement ele = ((WebElement)eleAttr);
			JavascriptExecutor js=(JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", ele);
		}
		waitForAjax(driver);
	}

	public static void scrollPageWrtXY(WebDriver driver, int x, int y) {
		((JavascriptExecutor)driver).executeScript("scroll(" +x+ "," +y+ ")");
		waitForAjax(driver);
	}

	public static <T> void check(WebDriver driver, T checkBox) {
		if(checkBox.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)checkBox);
			if (!ele.isSelected())
				ele.click();
		} else {
			WebElement ele = ((WebElement)checkBox);
			if (!ele.isSelected())
				ele.click();
		}
		waitForAjax(driver);
	}	


}
