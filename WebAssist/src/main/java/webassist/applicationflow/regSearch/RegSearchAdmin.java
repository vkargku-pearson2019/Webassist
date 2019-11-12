package webassist.applicationflow.regSearch;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webassist.util.Util;
import webassist.util.Verify;

public class RegSearchAdmin {
	
	private WebDriver wd;
	private Util util;
	private Verify verify;
	private String fieldsName = "Admin Date";

	public RegSearchAdmin(WebDriver wd){
		this.wd = wd;
		util = new Util(wd);
		verify = new Verify(wd);
		PageFactory.initElements(wd, this);
	}
	
	@FindBy(xpath = "(//*[contains(text(),'Reg Search')])[1]")
	private WebElement regSearchTab;
	
	@FindBy(xpath = "//div[@class='mat-select-arrow-wrapper']")
	private WebElement searchTypeDrpdwn;
	
	@FindBy(xpath = "//*[@role='option']//span[contains(text(),'Admin Search')]")
	private WebElement searchOption;
	
	@FindBy(xpath = "//*[contains(@placeholder,'Admin')]")
	private WebElement adminDate;
	
	@FindBy(xpath = "//button[text()='Lookup']")
	private WebElement lookupButton;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	private WebElement clearButton;
	
	@FindBy(xpath = "//legend[text()='Search Results']")
	private WebElement searchResultsPage;
	
	public boolean RegSearchAdmin(String ID) {
		return (enterDetailsToSearch(ID)
				.lookup());
	}

	public boolean clearDetails(String ID) {
		return clickRegSearchTab()
				.chooseSearchType()
				.enterDetailsToSearch(ID)
				.clickClearButton()
				.verifyClearFields();
	}

	public boolean validateWrongID() {
		return verify.validateWrongID();
	}	

	private RegSearchAdmin clickRegSearchTab() {
		regSearchTab.click();
		util.Wait(3);
		return this;
	}

	private RegSearchAdmin clickClearButton() {
		clearButton.click();
		util.Wait(1);
		return this;
	}

	private RegSearchAdmin chooseSearchType() {
		searchTypeDrpdwn.click();
		util.Wait(1);
		searchOption.click();
		return this;
	} 

	private RegSearchAdmin enterDetailsToSearch(String ID) {
		adminDate.clear();
		util.Wait(1);
		adminDate.sendKeys(ID);
		util.Wait(1);
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
