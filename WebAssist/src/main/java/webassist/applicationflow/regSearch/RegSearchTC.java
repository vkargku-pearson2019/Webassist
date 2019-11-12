package webassist.applicationflow.regSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webassist.util.Util;
import webassist.util.Verify;

public class RegSearchTC {
	
	private WebDriver wd;
	private Util util;
	private Verify verify;
	private String fieldsName = "Test Centre / School #";

	public RegSearchTC(WebDriver wd){
		this.wd = wd;
		util = new Util(wd);
		verify = new Verify(wd);
		PageFactory.initElements(wd, this);
	}

	@FindBy(xpath = "(//*[contains(text(),'Reg Search')])[1]")
	private WebElement regSearchTab;

	@FindBy(xpath = "//div[@class='mat-select-arrow-wrapper']")
	private WebElement searchTypeDrpdwn;

	@FindBy(xpath = "//*[@role='option']//span[contains(text(),'Search by Test Centre')]")
	private WebElement searchOption;
	
	@FindBy(xpath = "(//div[@class='mat-select-arrow-wrapper'])[2]")
	private WebElement testCentreDrpdwn;

	@FindBy(xpath = "//*[contains(@placeholder,'Test Centre')]")
	private WebElement testCentre;

	@FindBy(xpath = "//button[text()='Lookup']")
	private WebElement lookupButton;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	private WebElement clearButton;

	@FindBy(xpath = "//legend[text()='Search Results']")
	private WebElement searchResultsPage;

	@FindBy(xpath = "//*[contains(text(),'maxlength')]")
	private WebElement errorTextLastNameMax;
	
	public boolean RegSearchTC(String searchType,String ID) {
		return (clickTCDrpdwn(searchType)
				.enterDetailsToSearch(ID)
				.lookup());
	}
	
	public boolean RegSearchTCID(String ID) {
		return (enterDetailsToSearch(ID)
				.lookup());
	}

	public boolean clearDetails(String searchType,String ID) {
		return clickRegSearchTab()
				.chooseSearchType()
				.clickTCDrpdwn(searchType)
				.enterDetailsToSearch(ID)
				.clickClearButton()
				.verifyClearFields();
	}

	public boolean validateWrongID() {
		return verify.validateWrongID();
	}	
	
	public boolean validateTCIDLength(String ID) {
		if(ID.length()>6||ID.length()<1) {
		return (!util.isClickable(lookupButton, 10));
		}else {
			return false;
		}
	}
	
	public boolean validateTCLookupButton() {
		return (!util.isClickable(lookupButton, 10));
	}

	private RegSearchTC clickRegSearchTab() {
		regSearchTab.click();
		util.Wait(2);
		return this;
	}

	public RegSearchTC clickClearButton() {
		clearButton.click();
		util.Wait(1);
		return this;
	}

	private RegSearchTC chooseSearchType() {
		searchTypeDrpdwn.click();
		util.Wait(3);
		searchOption.click();
		return this;
	} 
	
	private RegSearchTC clickTCDrpdwn(String searchType) {
		testCentreDrpdwn.click();
		util.Wait(1);
		wd.findElement(By.xpath("//span[text()=' " + searchType + " ']")).click();
		return this;
	}

	private RegSearchTC enterDetailsToSearch(String ID) {
		testCentre.clear();
		util.Wait(2);
		testCentre.sendKeys(ID);
		util.Wait(2);
		return this;
	}

	private boolean lookup() {
		lookupButton.click();
		util.waitForPageToBeVisible();
		return util.isVisible(searchResultsPage, 20);
	}

	private boolean verifyClearFields() {
		return verify.verifyClearFields(fieldsName);
	}

}
