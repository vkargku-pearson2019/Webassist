package webassist.applicationflow.regSearch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webassist.util.Util;
import webassist.util.Verify;

public class RegSearchCBStudent {

	private WebDriver wd;
	private Util util;
	private Verify verify;
	private String fieldsName = "CB Student Id";

	public RegSearchCBStudent(WebDriver wd){
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

	@FindBy(xpath = "//*[@role='option']//span[contains(text(),'Search by CB Student')]")
	private WebElement searchOption;

	@FindBy(xpath = "//*[contains(@placeholder,'CB Student Id')]")
	private WebElement studentID;

	@FindBy(xpath = "//button[text()='Lookup']")
	private WebElement lookupButton;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	private WebElement clearButton;

	public boolean RegSearchCBStudent(String ID) {
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

	public boolean validateStudentIDLength(String ID) {
		if(ID.length()>10) {
		return (!util.isClickable(lookupButton, 10));
		}else {
			return false;
		}
	}

	private RegSearchCBStudent clickRegSearchTab() {
		regSearchTab.click();
		util.Wait(2);
		return this;
	}

	private RegSearchCBStudent clickClearButton() {
		clearButton.click();
		util.Wait(1);
		return this;
	}

	private RegSearchCBStudent chooseSearchType() {
		searchTypeDrpdwn.click();
		util.Wait(3);
		searchOption.click();
		return this;
	} 

	private RegSearchCBStudent enterDetailsToSearch(String ID) {
		studentID.clear();
		util.Wait(1);
		studentID.sendKeys(ID);
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
