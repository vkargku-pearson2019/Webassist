package webassist.applicationflow.srf;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webassist.applicationflow.srf.result.Comment;
import webassist.applicationflow.srf.result.View;
import webassist.util.Util;
import webassist.util.Verify;

public class SRF {

	private WebDriver wd;
	private Util util;
	private Verify verify;
	private View view;
	private Comment ocomment;
	private String fieldsName = "Admin Date,UIN,Centre / School";

	public SRF(WebDriver wd){
		this.wd = wd;
		util = new Util(wd);
		verify = new Verify(wd);
		view = new View(wd);
		ocomment = new Comment(wd);
		PageFactory.initElements(wd, this);
	}

	@FindBy(xpath = "(//*[contains(text(),'SRF')])[1]")
	private WebElement srf;

	@FindBy(xpath = "//*[contains(@placeholder,'Admin Date')]")
	private WebElement addate;

	@FindBy(xpath = "//*[contains(@placeholder,'UIN')]")
	private WebElement uin;

	@FindBy(xpath = "//*[contains(@placeholder,'Centre')]")
	private WebElement centre;

	@FindBy(xpath = "//button[contains(text(),'Search')]")
	private WebElement search;

	@FindBy(xpath = "//button[contains(text(),'Clear')]")
	private WebElement clearButton;
	
	public boolean clearDetails(String adminDate,String uinNumber,String centreCode) {
		return clickSRFTab()
				.enterAdmindate(adminDate)
				.enterUIN(uinNumber)
				.enterCentre(centreCode)
				.clickClear()
				.verifyClearField();
	}

	public boolean SRFsearch(String adminDate,String uinNumber,String centreCode) {
		return clickSRFTab()
				.enterAdmindate(adminDate)
				.enterUIN(uinNumber)
				.enterCentre(centreCode)
				.clickSearch();
	}

	public boolean postCommentSRF(String comment) {
		return ocomment.SRFPostComment(comment);
	}
	
	public boolean updateCommentSRF(String comment,String newComment) {
		return ocomment.SRFUpdateComment(comment,newComment);
	}
	
	public boolean deleteCommentSRF(String comment,String newComment) {
		return ocomment.SRFDeleteComment(comment,newComment);
	}

	public boolean verifyViewResult(String ansSheetField,String ansSheetFieldValue) {
		return view.verifyViewResultPage(ansSheetField, ansSheetFieldValue);
	}

	public boolean verifyError() {
		return (!util.isClickable(search, 20));
	}

	public boolean validateWrongID() {
		return verify.validateWrongID();
	}	

	private SRF clickSRFTab() {
		srf.click();
		util.Wait(2);
		return this;
	}

	private SRF enterAdmindate(String adminDate) {
		addate.clear();
		util.Wait(1);
		addate.sendKeys(adminDate);
		util.Wait(1);
		return this;
	}
	
	private SRF enterUIN(String uinNumber) {
		uin.clear();
		util.Wait(1);
		uin.sendKeys(uinNumber);
		util.Wait(1);
		return this;
	}

	private SRF enterCentre(String centreCode) {
		centre.clear();
		util.Wait(1);
		centre.sendKeys(centreCode);
		util.Wait(1);
		return this;
	}
	
	private SRF clickClear(){
		clearButton.click();
		util.Wait(1);
		return this;
	}
	
	private boolean clickSearch() {
		search.click();
		util.waitForPageToBeVisible();
		return view.verifySearchResultPage();
	}
	
	private boolean verifyClearField() {
		return verify.verifyClearFields(fieldsName);
	}
}







