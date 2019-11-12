package webassist.applicationflow.regSearch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webassist.util.Util;
import webassist.util.Verify;

public class RegSearchSSD {

	private WebDriver wd;
	private Util util;
	private Verify verify;
	private String fieldsName = "SSD";

	public RegSearchSSD(WebDriver wd){
		this.wd = wd;
		util = new Util(wd);
		verify = new Verify(wd);
		PageFactory.initElements(wd, this);
	}

	@FindBy(xpath = "//legend[text()='REG Details']")
	private WebElement regDetailsPage;

	@FindBy(xpath = "(//*[contains(text(),'Reg Search')])[1]")
	private WebElement regSearchTab;

	@FindBy(xpath = "//div[@class='mat-select-arrow-wrapper']")
	private WebElement searchTypeDrpdwn;

	@FindBy(xpath = "//*[@role='option']//span[contains(text(),'Search By SSD')]")
	private WebElement searchOption;

	@FindBy(xpath = "//*[contains(@placeholder,'SSD')]")
	private WebElement ssd;

	@FindBy(xpath = "//button[text()='Lookup']")
	private WebElement lookupButton;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	private WebElement clearButton;

	public boolean RegSearchSSD(String ID) {
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

	private RegSearchSSD clickRegSearchTab() {
		regSearchTab.click();
		util.Wait(2);
		return this;
	}

	private RegSearchSSD clickClearButton() {
		clearButton.click();
		util.Wait(1);
		return this;
	}

	private RegSearchSSD chooseSearchType() {
		searchTypeDrpdwn.click();
		util.Wait(3);
		searchOption.click();
		return this;
	} 

	private RegSearchSSD enterDetailsToSearch(String ID) {
		ssd.clear();
		util.Wait(1);
		ssd.sendKeys(ID);
		util.Wait(1);
		return this;
	}

	private boolean lookup() {
		lookupButton.click();
		util.waitForPageToBeVisible();
		return util.isVisible(regDetailsPage, 20);
	}

	private boolean verifyClearFields() {
		return verify.verifyClearFields(fieldsName);
	}

}
