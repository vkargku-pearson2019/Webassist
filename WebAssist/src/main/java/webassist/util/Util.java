package webassist.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {
	
	private WebDriver wd;
	private WebDriverWait wait;
	
	public Util(WebDriver wd) {
		this.wd = wd;
	}
	
	/**
	 * Navigate to url
	 * @param url
	 */
	public void navigateTo(String url) {
		wd.get(url);
	}
	
	/**
	 * Static Wait
	 * @param second
	 */
	public void Wait(int second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Dynamic Wait
	 */
	public void waitForPageToBeVisible() {
		Util util = new Util(wd);
		wait = new WebDriverWait(wd, 50);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'Loading...')]")));
		util.Wait(2);
	}
	
	/**
	 * Wait for element to be visible using xpath
	 */
   
	public boolean isVisible(String xpath,int timeInSec) {
		wait = new WebDriverWait(wd, timeInSec);
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			wait = null;
			return true;
		} catch(Exception e) {
			wait = null;
			return false;
		}
	}
	
	/**
	 * Wait for element to be visible using web element
	 */
	
	public boolean isVisible(WebElement we,int timeInSec) {
		wait = new WebDriverWait(wd, timeInSec);
		try{
			wait.until(ExpectedConditions.visibilityOf(we));
			wait = null;
			return true;
		} catch(Exception e) {
			wait = null;
			return false;
		}
	}
	
	/**
	 * Wait for element to be clickable using xpath
	 */

	public boolean isClickable(String xpath,int timeInSec) {
		wait = new WebDriverWait(wd, timeInSec);
		try{
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			wait = null;
			return true;
		} catch(Exception e) {
			wait = null;
			return false;
		}
	}
	
	/**
	 * Wait for element to be clickable using web element
	 */
	
	public boolean isClickable(WebElement we,int timeInSec) {
		wait = new WebDriverWait(wd, timeInSec);
		try{
			wait.until(ExpectedConditions.elementToBeClickable(we));
			wait = null;
			return true;
		} catch(Exception e) {
			wait = null;
			return false;
		}
	}
	
	/**
	 * Get system time
	 */

	public String getTime() {
		return new SimpleDateFormat("yyyy/MM/dd_HH:mm").format(Calendar.getInstance().getTime());	
	}
	
	
	
	

}
