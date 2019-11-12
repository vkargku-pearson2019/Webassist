package webassist.applicationflow.srf.result;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webassist.util.Util;
import webassist.util.Verify;

public class Comment {

	private WebDriver wd;
	private Util util;
	private Verify verify;

	public Comment(WebDriver wd){
		this.wd = wd;
		util = new Util(wd);
		verify = new Verify(wd);
		PageFactory.initElements(wd, this);
	}

	@FindBy(xpath = "//a[contains(text(),'Comment')]")
	private WebElement commentTab;

	@FindBy(xpath = "//*[@placeholder='Enter your comment']")
	private WebElement commentTextBox;

	@FindBy(xpath = "//*[text()='Post Comment']")
	private WebElement postCommentButton;
	
	@FindBy(xpath = "//input[@formcontrolname='comment']")
	private WebElement updateComment;
	
	@FindBy(xpath = "//span[contains(text(),'Update Comment')]")
	private WebElement updateCommntButton;
	
	@FindBy(xpath = "//span[contains(text(),'Delete Comment')]")
	private WebElement deleteCommentButton;

	public boolean SRFPostComment(String comment) {
		return clickCommentTab()
				.enterComment(comment)
				.verifyComment(comment);
	}
	
	public boolean SRFUpdateComment(String comment, String newComment) {
		return clickComment(comment)
				.updateComment(newComment)
				.clickUpdateButton()
				.verifyComment(newComment);
	}
	
	public boolean SRFDeleteComment(String comment, String newComment) {
		return clickComment(comment)
				.clickDeleteButton()
				.verifyDeletedComment(newComment);
	}

	private Comment clickCommentTab() {
		commentTab.click();
		util.waitForPageToBeVisible();
		return this;
	}

	private Comment enterComment(String comment) {
		commentTextBox.sendKeys(comment);
		util.Wait(1);
		postCommentButton.click();
		util.waitForPageToBeVisible();
		return this;
	}

	private Comment clickComment(String comment) {
		wd.findElement(By.xpath("//div[contains(text(),'"+comment+"' )]")).click();
		util.Wait(2);
		return this;
	}
	
	private Comment updateComment(String newComment) {
		updateComment.clear();
		util.Wait(1);
		updateComment.sendKeys(newComment);
		util.Wait(2);
		return this;
	}
	
	private Comment clickUpdateButton() {
		updateCommntButton.click();
		util.waitForPageToBeVisible();
		return this;
	}
	
	private Comment clickDeleteButton() {
		deleteCommentButton.click();
		util.waitForPageToBeVisible();
		return this;
	}
	
	private boolean verifyComment(String comment) {
		util.Wait(2);
		return verify.verifyComment(comment);
	}
	
	private boolean verifyDeletedComment(String comment) {
		util.Wait(2);
		return verify.verifyDeletedComment(comment);
	}
}




