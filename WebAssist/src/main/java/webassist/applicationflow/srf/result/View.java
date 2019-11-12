package webassist.applicationflow.srf.result;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webassist.util.Util;
import webassist.util.Verify;

public class View {
	
	private WebDriver wd;
	private Util util;
	private Verify verify;
	
	public View(WebDriver wd){
		this.wd = wd;
		util = new Util(wd);
		verify = new Verify(wd);
		PageFactory.initElements(wd, this);
	}

	@FindBy(xpath = "//legend[text()='View Result']")
	private WebElement viewResultPage;
	
	@FindBy(xpath = "//legend[text()='Search Results']")
	private WebElement searchResultPage;

	
	public boolean verifyViewResultPage(String ansSheetField,String ansSheetFieldValue) {
		return verify.verifyASDetailsPageValue(ansSheetField, ansSheetFieldValue);
	}
	
	public boolean verifySearchResultPage() {
		return util.isVisible(searchResultPage, 20);
	}

}
