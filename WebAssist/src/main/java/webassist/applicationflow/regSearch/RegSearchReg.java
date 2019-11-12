package webassist.applicationflow.regSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webassist.applicationflow.asSearch.ASSearchUIN;
import webassist.util.Util;
import webassist.util.Verify;

public class RegSearchReg {

	private WebDriver wd;
	private Util util;
	private Verify verify;
	private String fieldsName = "Reg #";

	public RegSearchReg(WebDriver wd){
		this.wd = wd;
		util = new Util(wd);
		verify = new Verify(wd);
		PageFactory.initElements(wd, this);
	}
	
	@FindBy(xpath = "//legend[text()='REG Details']")
	private WebElement regDetailsPage;

	@FindBy(xpath = "//*[contains(@class,'tabLarge')]//*[contains(text(),'Reg Search')]")
	private WebElement regSearchTab;
	
	@FindBy(xpath = "//div[@class='mat-select-arrow-wrapper']")
	private WebElement searchTypeDrpdwn;
	
	@FindBy(xpath = "//span[contains(text(),'Reg Search')]")
	private WebElement searchOption;

	@FindBy(xpath = "//*[contains(@placeholder,'Reg')]")
	private WebElement reg;

	@FindBy(xpath = "//button[text()='Lookup']")
	private WebElement lookupButton;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	private WebElement clearButton;

	public boolean RegSearchReg(String ID) {
		return (enterDetailsToSearch(fieldsName,ID)
				.lookup());
	}

	public boolean clearDetails(String searchType,String ID) {
		return clickRegSearchTab()
				.chooseSearchType(searchType)
				.enterDetailsToSearch(fieldsName,ID)
				.clickClearButton()
				.verifyClearFields();
	}

	public boolean validateWrongID() {
		return verify.validateWrongID();
	}	
	
	public boolean validateRegLength(String ID) {
		if(ID.length()>10) {
		return (!util.isClickable(lookupButton, 10));
		}else {
			return false;
		}
	}

	private RegSearchReg clickRegSearchTab() {
		regSearchTab.click();
		util.Wait(3);
		return this;
	}

	private RegSearchReg clickClearButton() {
		clearButton.click();
		util.Wait(1);
		return this;
	}
/*
	private RegSearchReg chooseSearchType() {
		searchTypeDrpdwn.click();
		util.Wait(1);
		searchOption.click();
		return this;
	} 
	*/
	
	private RegSearchReg chooseSearchType(String searchType) {
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

/*	private RegSearchReg enterDetailsToSearch(String ID) {
		reg.clear();
		util.Wait(1);
		reg.sendKeys(ID);
		util.Wait(1);
		return this;
	}*/
	
	private RegSearchReg enterDetailsToSearch(String typeOfID,String ID) {
		wd.findElement(By.xpath("//input[@placeholder='"+typeOfID+"']")).clear();
		util.Wait(1);
		wd.findElement(By.xpath("//input[@placeholder='"+typeOfID+"']")).sendKeys(ID);
		return this;
	}
	
	private boolean lookup() {
		lookupButton.click();
		util.waitForPageToBeVisible();
		return util.isVisible(regDetailsPage, 20);
	}

	public boolean verifyREGDetailsPage(String ansSheetField,String ansSheetFieldValue) {
		return verify.verifyREGDetailsPageValue(ansSheetField, ansSheetFieldValue);
	}
	
	private boolean verifyClearFields() {
		return verify.verifyClearFields(fieldsName);
	}
}
