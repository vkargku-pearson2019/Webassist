package webassist.applicationflow.asSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webassist.util.Util;
import webassist.util.Verify;

public class ASSearchBatch {

	private WebDriver wd;
	private Util util;
	private Verify verify;
	private String fieldsName = "Admin,TC/S,AS Reg,Last name,First name,Batch No";

	public ASSearchBatch(WebDriver wd){
		this.wd = wd;
		util = new Util(wd);
		verify = new Verify(wd);
		PageFactory.initElements(wd, this);
	}

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	private WebElement clearButton;

	@FindBy(xpath = "//legend[text()='AS Details']")
	private WebElement asDetailsPage;

	@FindBy(xpath = "//legend[text()='Search Results']")
	private WebElement searchResultsPage;

	@FindBy(xpath = "//*[text()='Please fill in atleast another field other than Admin']")
	private WebElement errorText;

	@FindBy(xpath = "(//*[contains(text(),'AS Search')])[1]")
	private WebElement asSearchTab;

	@FindBy(xpath = "//div[@class='mat-select-arrow-wrapper']")
	private WebElement searchTypeDrpdwn;

	@FindBy(xpath = "//button[text()='Lookup']")
	private WebElement lookupButton;

	@FindBy(xpath = "//*[@formcontrolname='srf_testCentre_school']")
	private WebElement testCentreDrpdwn;

	@FindBy(xpath = "//input[contains(@placeholder,'Admin')]")
	private WebElement admin;

	@FindBy(xpath = "//input[contains(@placeholder,'TC')]")
	private WebElement tc;

	@FindBy(xpath = "//input[contains(@placeholder,'Reg')]")
	private WebElement reg;

	@FindBy(xpath = "//input[contains(@placeholder,'Last name')]")
	private WebElement lastName;

	@FindBy(xpath = "//input[contains(@placeholder,'First name')]")
	private WebElement firstName;

	@FindBy(xpath = "//input[contains(@placeholder,'Batch')]")
	private WebElement batch;

	@FindBy(xpath = "//a[contains(text(),'Demo Page Data')]")
	private WebElement demoPageDataTab;

	@FindBy(xpath = "//a[contains(text(),'General Data')]")
	private WebElement generalDataTab;

	@FindBy(xpath = "//a[contains(text(),'Comment')]")
	private WebElement commentTab;

	@FindBy(xpath = "//*[@placeholder='Enter your comment']")
	private WebElement commentTextBox;

	@FindBy(xpath = "//*[text()='Post Comment']")
	private WebElement postCommentButton;

	public boolean ASSearchBatch(String searchType,String adminID,String tcID,String regID,String last,String first,String batchID) {
		return clickASSearchTab()
				.chooseSearchType(searchType)
				.enterDetailsToSearchBatch(adminID,tcID,regID,last,first,batchID)
			   .lookup();
	}

	public boolean pageResults(String comment) {
		return clickDemoPageDataTab()
				.clickGeneralDataTab()
				.clickCommentTab()
				.enterComment(comment)
				.verifyComment(comment);
	}

	public boolean clearDetails(String searchType,String adminID,String tcID,String regID,String last,String first,String batchID) {
		return clickASSearchTab()
				.chooseSearchType(searchType)
				.enterDetailsToSearchBatch(adminID,tcID,regID,last,first,batchID)
				.clickClearButton()
				.verifyClearField();
	}

	public boolean verifysearchResultsPage() {
		return util.isVisible(searchResultsPage, 10);
	}

	public boolean verifyError() {
		return util.isVisible(errorText, 10);
	}

	public boolean validateWrongID() {
		return verify.validateWrongID();
	}

	private ASSearchBatch clickClearButton() {
		clearButton.click();
		util.Wait(3);
		return this;
	}

	private ASSearchBatch clickASSearchTab() {
		asSearchTab.click();
		util.Wait(5);
		return this;
	}

	private ASSearchBatch chooseSearchType(String searchType) {
		if(util.isVisible(searchTypeDrpdwn, 60) && 
				util.isClickable(searchTypeDrpdwn, 60)) {
			util.Wait(5);
			searchTypeDrpdwn.click();
			util.Wait(1);
			wd.findElement(By.xpath("//span[contains(text(),' "+searchType+" ')]")).click();
			util.Wait(1);
		}
		return this;
	}

	private ASSearchBatch enterDetailsToSearchBatch(String adminID,String tcID,String regID,String last,String first,String batchID) {
		admin.clear();
		admin.sendKeys(adminID);
		util.Wait(1);
		tc.clear();
		tc.sendKeys(tcID);
		util.Wait(1);
		reg.clear();
		reg.sendKeys(regID);
		util.Wait(1);
		lastName.clear();
		lastName.sendKeys(last);
		util.Wait(1);
		firstName.clear();
		firstName.sendKeys(first);
		util.Wait(1);
		batch.clear();
		batch.sendKeys(batchID);
		return this;
	}

	private boolean lookup() {
		lookupButton.click();
		util.waitForPageToBeVisible();
		return util.isVisible(asDetailsPage, 20);
	}

	private ASSearchBatch clickDemoPageDataTab() {
		JavascriptExecutor je = ((JavascriptExecutor) wd);
		je.executeScript("arguments[0].scrollIntoView(true);",demoPageDataTab);
		demoPageDataTab.click();
		util.waitForPageToBeVisible();
		return this;
	}

	private ASSearchBatch clickGeneralDataTab() {
		generalDataTab.click();
		util.waitForPageToBeVisible();
		return this;
	}

	private ASSearchBatch clickCommentTab() {
		commentTab.click();
		util.waitForPageToBeVisible();
		return this;
	}

	private ASSearchBatch enterComment(String comment) {
		commentTextBox.sendKeys(comment);
		util.Wait(1);
		postCommentButton.click();
		util.waitForPageToBeVisible();
		return this;
	}

	public boolean verifyASDetailsPage(String ansSheetField,String ansSheetFieldValue) {
		return verify.verifyASDetailsPageValue(ansSheetField, ansSheetFieldValue);
	}

	private boolean verifyComment(String comment) {
		return verify.verifyComment(comment);
	}

	private boolean verifyClearField() {
		return verify.verifyClearFields(fieldsName);
	}
}

