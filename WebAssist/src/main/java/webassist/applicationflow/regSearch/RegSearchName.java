package webassist.applicationflow.regSearch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webassist.util.Util;
import webassist.util.Verify;

public class RegSearchName {

	private WebDriver wd;
	private Util util;
	private Verify verify;
	private String fieldsName = "Last Name,First Name";

	public RegSearchName(WebDriver wd){
		this.wd = wd;
		util = new Util(wd);
		verify = new Verify(wd);
		PageFactory.initElements(wd, this);
	}

	@FindBy(xpath = "(//*[contains(text(),'Reg Search')])[1]")
	private WebElement regSearchTab;

	@FindBy(xpath = "//div[@class='mat-select-arrow-wrapper']")
	private WebElement searchTypeDrpdwn;

	@FindBy(xpath = "//*[@role='option']//span[contains(text(),'Search By Name')]")
	private WebElement searchOption;

	@FindBy(xpath = "//*[contains(@placeholder,'Last')]")
	private WebElement lastName;

	@FindBy(xpath = "//*[contains(@placeholder,'First')]")
	private WebElement firstName;

	@FindBy(xpath = "//button[text()='Lookup']")
	private WebElement lookupButton;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	private WebElement clearButton;

	@FindBy(xpath = "//legend[text()='REG Details']")
	private WebElement regDetailsPage;

	@FindBy(xpath = "//*[contains(text(),'maxlength')]")
	private WebElement errorTextLastNameMax;

	@FindBy(xpath = "//*[contains(text(),'last name must be at least four characters')]")
	private WebElement errorTextLastNameMin;

	public boolean RegSearchName(String last,String first) {
		return (clickRegSearchTab()
				.chooseSearchType()
				.enterDetailsToSearchName(last,first)
				.lookup());
	}

	public boolean clearDetails(String last,String first) {
		return clickRegSearchTab()
				.chooseSearchType()
				.enterDetailsToSearchName(last,first)
				.clickClearButton()
				.verifyClearFields();
	}

	public boolean validateWrongID() {
		return verify.validateWrongID();
	}

	public boolean verifyLastNameMaxLength(String ID) {
		if(ID.length()>15) {
			return util.isVisible(errorTextLastNameMax, 10);
		}else {
			return false;
		}
	}

	public boolean verifyLastNameMinLength(String ID) {
		if(ID.length()<1) {
			return (!util.isClickable(lookupButton, 10));
		}else {
			return false;
		}
	}

	public boolean verifyFirstNameLength(String ID) {
		if(ID.length()>12||ID.length()<1) {
			return (!util.isClickable(lookupButton, 10));
		}else {
			return false;
		}
	}

	private RegSearchName clickRegSearchTab() {
		regSearchTab.click();
		util.Wait(3);
		return this;
	}

	private RegSearchName clickClearButton() {
		clearButton.click();
		util.Wait(3);
		return this;
	}

	private RegSearchName chooseSearchType() {
		searchTypeDrpdwn.click();
		util.Wait(1);
		searchOption.click();
		return this;
	} 

	private RegSearchName enterDetailsToSearchName(String last,String first) {
		lastName.clear();
		lastName.sendKeys(last);
		util.Wait(1);
		firstName.clear();
		firstName.sendKeys(first);
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
