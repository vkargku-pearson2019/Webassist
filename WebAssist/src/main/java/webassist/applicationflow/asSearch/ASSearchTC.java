package webassist.applicationflow.asSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webassist.util.Util;
import webassist.util.Verify;

public class ASSearchTC {
	
	private WebDriver wd;
	private Util util;
	private Verify verify;
	private String fieldsName = "AS Test Centre/School,Admin";
	
	public ASSearchTC(WebDriver wd){
        this.wd = wd;
        util = new Util(wd);
        verify = new Verify(wd);
       PageFactory.initElements(wd, this);
    }
	
	@FindBy(xpath = "//legend[text()='Search Results']")
	private WebElement searchResultsPage;
	
	@FindBy(xpath = "(//*[contains(text(),'AS Search')])[1]")
	private WebElement asSearchTab;
	
	@FindBy(xpath = "//div[@class='mat-select-arrow-wrapper']")
	private WebElement searchTypeDrpdwn;
	
	@FindBy(xpath = "//button[text()='Lookup']")
	private WebElement lookupButton;
	
	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	private WebElement clearButton;
	
	@FindBy(xpath = "//input[contains(@placeholder,'Test Centre')]")
	private WebElement testCentre;
	
	@FindBy(xpath = "//input[contains(@placeholder,'Admin')]")
	private WebElement admin;
	
	public boolean ASSearchTC(String searchType,String tcID,String adminID) {
			return (enterDetailsToSearchTC(tcID,adminID)
					.lookup());
	}
	
	public boolean clearDetails(String searchType,String tcID,String adminID) {
		return clickASSearchTab()
				.chooseSearchType(searchType)
				.enterDetailsToSearchTC(tcID,adminID)
				.clickClearButton()
				.verifyClearField();
	}
	
	public boolean validateWrongID() {
		return verify.validateWrongID();
	}	
	
	private ASSearchTC clickASSearchTab() {
		asSearchTab.click();
		util.Wait(3);
		return this;
	}
	
	private ASSearchTC clickClearButton() {
		clearButton.click();
		util.Wait(3);
		return this;
	}
	
	private ASSearchTC chooseSearchType(String searchType) {
		if(util.isVisible(searchTypeDrpdwn, 60)&
				util.isClickable(searchTypeDrpdwn, 60)) {
			util.Wait(3);
			searchTypeDrpdwn.click();
			util.Wait(1);
			wd.findElement(By.xpath("//span[contains(text(),' "+searchType+" ')]")).click();
			util.Wait(2);
		}
		return this;
	} 
	
	private ASSearchTC enterDetailsToSearchTC(String tcID,String adminID) {
		testCentre.clear();
		testCentre.sendKeys(tcID);
		util.Wait(1);
		admin.clear();
		admin.sendKeys(adminID);
		util.Wait(2);
		return this;
	}
	
	private boolean lookup() {
		lookupButton.click();
		util.waitForPageToBeVisible();
		return util.isVisible(searchResultsPage, 20);
	}
	
	private boolean verifyClearField() {
		return verify.verifyClearFields(fieldsName);
	}
}
