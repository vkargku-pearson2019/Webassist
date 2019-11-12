package webassist.applicationflow.superSearch;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


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

	@FindBy(xpath = "//*[contains(text(),'UIN Search')]")
	private WebElement uinSearch;

	@FindBy(xpath = "//input[@placeholder='UIN']")
	private WebElement uinField;

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
		searchTab.click();
		util.Wait(2);
		searchTypeDrpdwn.click();
		util.Wait(2);
		uinSearch.click();
		util.Wait(2);
		uinField.sendKeys(uin);
		util.Wait(2);
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
