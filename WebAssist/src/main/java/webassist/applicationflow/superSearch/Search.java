package webassist.applicationflow.superSearch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webassist.applicationflow.asSearch.ASSearchUIN;
import webassist.util.Util;

public class Search {

	private WebDriver wd;
	private Util util;

	public Search(WebDriver wd){
		this.wd = wd;
		util = new Util(wd);
		PageFactory.initElements(wd, this);
	}

	@FindBy(xpath = "//*[@class='tabLarge']//*[text()='Search']")
	private WebElement searchTab;

	@FindBy(xpath = "//div[@class='mat-select-arrow-wrapper']")
	private WebElement searchTypeDrpdwn;
	
	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	private WebElement clearButton;

	@FindBy(xpath = "//*[contains(text(),'UIN Search')]")
	private WebElement uinSearch;

	@FindBy(xpath = "//input[@placeholder='UIN']")
	private WebElement uinField;
	
	@FindBy(xpath = "//mat-option//*[contains(text(),'Registration Search')]")
	private WebElement regSearch;

	@FindBy(xpath = "//input[@placeholder='Registration Id']")
	private WebElement regField;
	
	@FindBy(xpath = "//*[contains(text(),'SSN Search')]")
	private WebElement ssnSearch;

	@FindBy(xpath = "//input[@placeholder='Social Security Number']")
	private WebElement ssnField;
	
	@FindBy(xpath = "//*[contains(text(),'Name Search')]")
	private WebElement nameSearch;

	@FindBy(xpath = "//input[@placeholder='First Name']")
	private WebElement firstNameField;
	
	@FindBy(xpath = "//input[@placeholder='Last Name']")
	private WebElement lastNameField;
	
	@FindBy(xpath = "//*[@value='0']")
	private WebElement walkInOptionNo;
	
	@FindBy(xpath = "//*[@value='1']")
	private WebElement walkInOptionYes;
	
	@FindBy(xpath = "//*[contains(text(),'DOB Search')]")
	private WebElement dobSearch;
	
	@FindBy(xpath = "//input[@placeholder='DOB']")
	private WebElement dobField;

	@FindBy(xpath = "//button[text()='Look up']")
	private WebElement lookUpButton;

	@FindBy(xpath = "(//div[contains(@class,'tableRow')]//div[10])[1]")
	private WebElement uinText;

	@FindBy(xpath = "//*[contains(text(),'Matcher - Match (DB2ADMIN)')]")
	private WebElement matchText;

	@FindBy(xpath = "//*[contains(text(),'Matcher - No Match (DB2ADMIN)')]")
	private WebElement noMatchText;

	@FindBy(xpath = "//*[contains(text(),'Matcher - Alert (DB2ADMIN)')]")
	private WebElement alertText;

	public Search uinSearch(String uin) {
		return clickSearchTab()
				.clickDropdown()
				.chooseSearchTypeAsUIN(uin)
				.clickLookupButton();
	}
	
	private Search clickClearButton() {
		clearButton.click();
		util.Wait(3);
		return this;
	}
	
	private Search clickSearchTab() {
		searchTab.click();
		util.Wait(2);
		return this;
	}
	
	private Search clickDropdown() {
		if(util.isVisible(searchTypeDrpdwn, 60)&
				util.isClickable(searchTypeDrpdwn, 60)) {
			searchTypeDrpdwn.click();
			util.Wait(2);
		}
		return this;
	}
	
	private Search chooseSearchTypeAsUIN(String uin) {
		uinSearch.click();
		util.Wait(2);
		uinField.sendKeys(uin);
		util.Wait(2);
		return this;
	}
	
	private Search chooseSearchTypeAsReg(String reg) {
		regSearch.click();
		util.Wait(2);
		regField.sendKeys(reg);
		util.Wait(2);
		return this;
	}
	
	private Search chooseSearchTypeAsSSN(String ssn) {
		ssnSearch.click();
		util.Wait(2);
		ssnField.sendKeys(ssn);
		util.Wait(2);
		return this;
	}
	
	private Search chooseSearchTypeAsName(String first,String last) {
		nameSearch.click();
		util.Wait(2);
		firstNameField.sendKeys(first);
		util.Wait(2);
		lastNameField.sendKeys(last);
		util.Wait(2);
		walkInOptionNo.click();
		util.Wait(2);
		return this;
	}
	
	private Search chooseSearchTypeAsDOB(String dob,String first,String last) {
		dobSearch.click();
		util.Wait(2);
		dobField.sendKeys(dob);
		util.Wait(2);
		firstNameField.sendKeys(first);
		util.Wait(2);
		lastNameField.sendKeys(last);
		util.Wait(2);
		return this;
	}
	
	private Search clickLookupButton() {
		lookUpButton.click();
		util.waitForPageToBeVisible();
		return this;
	}

	public boolean uinVerification(String uin) {
		boolean flag = false;
		if(uinText.getText().equalsIgnoreCase(uin)) {
			flag = true;
		}else {
			flag = false;
		}
		return flag; 
	}

	public boolean isMatchedCorrectly(String matchType){
		switch (matchType){
		case "Match":
			return util.isVisible(matchText, 20);
		case "No Match":
			return util.isVisible(noMatchText, 20);
		case "Alert":
			return util.isVisible(alertText, 20);
		default:
			return false;
		}
	}
}
