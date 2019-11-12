package webassist.applicationflow.login;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webassist.applicationflow.role.Role;
import webassist.util.Util;
import webassist.util.Verify;

public class Login {
	
	private WebDriver wd;
	private Util util;
	private Verify verify;
	private Role role;
	
	public Login(WebDriver wd){
        this.wd = wd;
        util = new Util(wd);
        verify = new Verify(wd);
        role = new Role(wd);
        PageFactory.initElements(wd, this);
    }
	
	@FindBy(xpath = "//input[@placeholder='Username']" )
	private WebElement username;
	
	@FindBy(xpath = "//input[@placeholder='Password']" )
	private WebElement password;
	
	@FindBy(xpath = "//*[text()='Select Project']/ancestor::mat-select" )
	private WebElement selectProject;
	
	@FindBy(xpath = "//button[text()='Login']" )
	private WebElement loginButton;
	
	public boolean loginToWebAssist(String userName,String pwd,String projectID) {
		return enterUserName(userName)
				.enterPassword(pwd)
				.selectProject(projectID)
				.clickLogin();
	}
	
	public boolean validateBadCredential() {
		return verify.validateWrongID();
	}
	
	private Login enterUserName(String userName) {
		util.isVisible(username, 20);
		username.sendKeys(userName);
		util.Wait(1);
		return this;
	}
	
	private Login enterPassword(String pwd) {
		password.sendKeys(pwd);
		util.Wait(2);
		return this;
	}
	
	private Login selectProject(String projectID) {
		selectProject.click();
		util.Wait(2);
		wd.findElement(By.xpath("//*[contains(text(),'" + projectID + "')]")).click();
		util.Wait(2);
		return this;
	}
	
	private boolean clickLogin() {
		loginButton.click();
		util.waitForPageToBeVisible();
		return role.isAtRoleSeectionPage();
	}
	
}


