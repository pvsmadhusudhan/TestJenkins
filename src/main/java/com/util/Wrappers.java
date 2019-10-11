package com.util;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class Wrappers {
	
	public static void highlightElement(WebDriver driver, WebElement element) throws Exception {
		((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid yellow'", element); // highlight
		Thread.sleep(300); // delay between highlight and unhighlight 
		((JavascriptExecutor)driver).executeScript("arguments[0].style.border=''", element); // unhighlight
	}

	// Methods by using JAVA Generics (You can use both By or Webelement)
	public static <T> void click (WebDriver driver, T elementAttr) {
		if(elementAttr.getClass().getName().contains("By")) {
			driver.findElement((By)elementAttr).click();
		} else {
			((WebElement)elementAttr).click();
		}
	}

	public static <T> void writeText (WebDriver driver, T elementAttr, String text) {
		if(elementAttr.getClass().getName().contains("By")) {
			driver.findElement((By) elementAttr).sendKeys(text);
		} else {
			((WebElement) elementAttr).sendKeys(text);
		}
	}

	public static <T> String readText (WebDriver driver, T elementAttr) {
		if(elementAttr.getClass().getName().contains("By")) {
			return driver.findElement((By) elementAttr).getText();
		} else {
			return ((WebElement) elementAttr).getText();
		}
	}

	public static <T> void waitForClickableThenClick (WebDriver driver, T elementAttr) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		if(elementAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)elementAttr);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
		} else {
			WebElement ele = ((WebElement)elementAttr);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
		}
	}

	public static <T> void waitForVisibleThenClick (WebDriver driver, T elementAttr) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		if(elementAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)elementAttr);
			wait.until(ExpectedConditions.visibilityOf(ele));
			ele.click();
		} else {
			WebElement ele = ((WebElement)elementAttr);
			wait.until(ExpectedConditions.visibilityOf(ele));
			ele.click();
		}
	}

	public static <T> void waitForVisibleThenType (WebDriver driver, T elementAttr, String text) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		if(elementAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)elementAttr);
			wait.until(ExpectedConditions.visibilityOf(ele));
			ele.sendKeys(text);
		} else {
			WebElement ele = ((WebElement)elementAttr);
			wait.until(ExpectedConditions.visibilityOf(ele));
			ele.sendKeys(text);
		}
	}

	public static <T> void waitForPresenceThenClick(WebDriver driver, T elementAttr) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(((By)elementAttr)));
		WebElement ele = driver.findElement((By)elementAttr);
		ele.click();
	}

	public static <T> void jsClick(WebDriver driver, T elementAttr){
		if(elementAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)elementAttr);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		} else {
			WebElement ele = ((WebElement)elementAttr);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
		}
	}

	public static <T> void jsType(WebDriver driver, T elementAttr, String value){
		if(elementAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)elementAttr);
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+value+"'", ele);
		} else {
			WebElement ele = ((WebElement)elementAttr);
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+value+"'", ele);
		}
	}

	public static <T> void clearAndType(WebDriver driver, T elementAttr, String value) {
		if(elementAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)elementAttr);
			ele.clear();
			ele.sendKeys(value);
		} else {
			WebElement ele = ((WebElement)elementAttr);
			ele.clear();
			ele.sendKeys(value);
		}
	}

	public static <T> void clickClearAndType(WebDriver driver, T eleAttr1, T eleAttr2, String value) throws Exception {
		if(eleAttr1.getClass().getName().contains("By") && eleAttr2.getClass().getName().contains("By")) {
			WebElement ele1 = driver.findElement((By)eleAttr1);
			WebElement ele2 = driver.findElement((By)eleAttr2);
			ele1.click();
			Thread.sleep(500);
			ele2.clear();
			ele2.sendKeys(value);
		} else {
			WebElement ele1 = ((WebElement)eleAttr1);
			WebElement ele2 = ((WebElement)eleAttr2);
			ele1.click();
			Thread.sleep(500);
			ele2.clear();
			ele2.sendKeys(value);
		}
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
	}

	public static <T> void clickClearAndType(WebDriver driver, T eleAttr, String value) throws Exception {
		if(eleAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)eleAttr);
			ele.click();
			Thread.sleep(500);
			ele.clear();
			ele.sendKeys(value);
		} else {
			WebElement ele = ((WebElement)eleAttr);
			ele.click();
			Thread.sleep(500);
			ele.clear();
			ele.sendKeys(value);
		}
	}

	public static void jsClickClearAndType(WebDriver driver, WebElement eleAttr1, WebElement eleAttr2, String value) throws Exception {
		if(eleAttr1.getClass().getName().contains("By") && eleAttr2.getClass().getName().contains("By")) {
			WebElement ele1 = driver.findElement((By)eleAttr1);
			WebElement ele2 = driver.findElement((By)eleAttr2);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele1);
			Thread.sleep(500);
			ele2.clear();
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+value+"'",ele2);
		} else {
			WebElement ele1 = ((WebElement)eleAttr1);
			WebElement ele2 = ((WebElement)eleAttr2);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele1);
			Thread.sleep(500);
			ele2.clear();
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+value+"'",ele2);
		}
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
	}

	public static <T> void jsClickClearAndType(WebDriver driver, T eleAttr, String value) throws Exception {
		if(eleAttr.getClass().getName().contains("By")) {
			WebElement ele = driver.findElement((By)eleAttr);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
			Thread.sleep(500);
			ele.clear();
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+value+"'",ele);
		} else {
			WebElement ele = ((WebElement)eleAttr);
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
			Thread.sleep(500);
			ele.clear();
			((JavascriptExecutor)driver).executeScript("arguments[0].value='"+value+"'",ele);
		}
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
		}
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
	}	
	
	public static WebElement getWebElement(WebDriver driver, By locator) {
		return driver.findElement(locator);
	}
	
	
}
