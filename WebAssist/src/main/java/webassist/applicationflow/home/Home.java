package webassist.applicationflow.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webassist.util.Util;

public class Home {

	private WebDriver wd;
	private Util util;
	private String roleText = "You are logged in as";

	public Home(WebDriver wd){
		this.wd = wd;
		util = new Util(wd);
		PageFactory.initElements(wd, this);
	}

	@FindBy(xpath = "//*[contains(text(),'Hello')]" )
	private WebElement homeButton;

	@FindBy(xpath = "(//*[contains(text(),'Search')])[1]" )
	private WebElement searchButton;

	@FindBy(xpath = "(//*[contains(text(),'AS Search')])[1]" )
	private WebElement asSearchButton;

	@FindBy(xpath = "(//*[contains(text(),'Reg Search')])[1]" )
	private WebElement regSearchButton;

	@FindBy(xpath = "(//*[contains(text(),'SRF')])[1]" )
	private WebElement srfButton;
	
	@FindBy(xpath = "(//*[contains(text(),'Admin')])[1]" )
	private WebElement adminButton;

	public boolean isAtHomePage(String role,String userName){
		if(validateUserName(userName) & 
				util.isVisible("//*[text()='" + roleText + " " + role + "']", 20)) {
			switch (role){
			case "SUPER":
				return (util.isVisible(searchButton, 20) &
						util.isVisible(asSearchButton, 20) &
						util.isVisible(regSearchButton, 20) &
						util.isVisible(srfButton, 20));
			case "VIEW ONLY":
				return util.isVisible(searchButton, 20);
			case "VERIFY":
				return util.isVisible(searchButton, 20);
			case "MATCH":
				return util.isVisible(searchButton, 20);
			case "ADMIN":
				return util.isVisible(adminButton, 20);
			default:
				return false;
			}
		} else {
			return false;
		}
	}
	
	private boolean validateUserName(String userName) {
		return (util.isVisible(homeButton, 20) & 
				homeButton.getText().contains(userName));
	}


}
