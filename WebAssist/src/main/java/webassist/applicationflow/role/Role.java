package webassist.applicationflow.role;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webassist.applicationflow.home.Home;
import webassist.applicationflow.login.Login;
import webassist.util.Util;

public class Role {

	private WebDriver wd;
	private Util util;
	private Home home;

	public Role(WebDriver wd){
		this.wd = wd;
		util = new Util(wd);
		home = new Home(wd);
		PageFactory.initElements(wd, this);
	}
	
	@FindBy(xpath = "//*[@class='userIcon']" )
	private WebElement userIcon;
	
	@FindBy(xpath = "//*[text()='Change Role']" )
	private WebElement changeRole;

	@FindBy(xpath = "//span[text()='Please Select Your Role']" )
	private WebElement roleName;

	@FindBy(xpath = "//button[text()='Proceed']" )
	private WebElement proceed;

	@FindBy(xpath = "//*[text()='Role Selection']")
	private WebElement roleSelection;

	public boolean isAtRoleSeectionPage() {
		return util.isVisible(roleSelection, 20);
	}
	
	public boolean roleSelection(String role,String userName) {
		return selectRole(role)
				.clickProceed(role,userName);
	}
	
	public Role changeRole() {
		userIcon.click();
		changeRole.click();
		util.Wait(2);
		return this;
	}

	private Role selectRole(String role) {
		if(util.isVisible(roleName, 30) && 
				util.isClickable(roleName, 30)){
			roleName.click();
			util.Wait(2);
			wd.findElement(By.xpath("//*[contains(text(),'" + role + "')]")).click();
		}
		util.Wait(2);
		return this;
	}

	private boolean clickProceed(String role,String userName) {
		proceed.click();
		util.waitForPageToBeVisible();
		return home.isAtHomePage(role,userName);
	}

	
}
