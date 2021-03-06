package webassist.applicationflow.asSearch;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webassist.util.Util;
import webassist.util.Verify;

public class ASSearchPAS {

	private WebDriver wd;
	private Util util;
	private Verify verify;
	private final String fieldsName = "PAS";

	public ASSearchPAS(WebDriver wd){
		this.wd = wd;
		util = new Util(wd);
		verify = new Verify(wd);
		PageFactory.initElements(wd, this);
	}

	@FindBy(xpath = "//legend[text()='AS Details']")
	private WebElement asDetailsPage;

	@FindBy(xpath = "(//*[contains(text(),'AS Search')])[1]")
	private WebElement asSearchTab;

	@FindBy(xpath = "//div[@class='mat-select-arrow-wrapper']")
	private WebElement searchTypeDrpdwn;

	@FindBy(xpath = "//button[text()='Lookup']")
	private WebElement lookupButton;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	private WebElement clearButton;
	
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

	public boolean ASSearchPAS(String searchType,String ID) {
		return (enterDetailsToSearch(fieldsName,ID)
				.lookup());
	}
	
	public boolean pageResults(String comment) {
		return clickDemoPageDataTab().
				clickGeneralDataTab().
				clickcommentTab().
				enterComment(comment).
				verifyComment(comment);
	}

	public boolean clearDetails(String searchType,String ID) {
		return clickASSearchTab()
				.chooseSearchType(searchType)
				.enterDetailsToSearch(fieldsName,ID)
				.clickClearButton()
				.verifyClearField();
	}

	public boolean validateWrongID() {
		return verify.validateWrongID();
	}	

	private ASSearchPAS clickASSearchTab() {
		asSearchTab.click();
		util.Wait(3);
		return this;
	}

	private ASSearchPAS clickClearButton() {
		clearButton.click();
		util.Wait(3);
		return this;
	}

	private ASSearchPAS chooseSearchType(String searchType) {
		if(util.isVisible(searchTypeDrpdwn, 60) && 
				util.isClickable(searchTypeDrpdwn, 60)) {
			util.Wait(3);
			searchTypeDrpdwn.click();
			util.Wait(1);
			wd.findElement(By.xpath("//span[contains(text(),' "+searchType+" ')]")).click();
			util.Wait(2);
		}
		return this;
	} 

	private ASSearchPAS enterDetailsToSearch(String typeOfID,String ID) {
		wd.findElement(By.xpath("//input[@placeholder='"+typeOfID+"']")).clear();
		util.Wait(1);
		wd.findElement(By.xpath("//input[@placeholder='"+typeOfID+"']")).sendKeys(ID);
		util.Wait(2);
		return this;
	}

	private boolean lookup() {
		lookupButton.click();
		util.waitForPageToBeVisible();
		return util.isVisible(asDetailsPage, 20);
	}
	
	private ASSearchPAS clickDemoPageDataTab() {
		JavascriptExecutor je = ((JavascriptExecutor) wd);
		je.executeScript("arguments[0].scrollIntoView(true);",demoPageDataTab);
		demoPageDataTab.click();
		util.waitForPageToBeVisible();
		return this;
	}
	
	private ASSearchPAS clickGeneralDataTab() {
		generalDataTab.click();
		util.waitForPageToBeVisible();
		return this;
	}
	
	private ASSearchPAS clickcommentTab() {
		commentTab.click();
		util.waitForPageToBeVisible();
		return this;
	}

	private ASSearchPAS enterComment(String comment) {
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
