package DemoBDDkk.pageobjectphp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resourcesphp.phputilfile;

public class Loginphp {
		private phputilfile util;
		private WebDriver wd;

		public Loginphp(WebDriver wd) {
			this.wd = wd;
			PageFactory.initElements(wd, this);
		}

		@FindBy(xpath = "//*[@class='ink animate']")
		private WebElement LoginAccount;
		
		@FindBy(xpath = "//*[text()='Login']")
		private WebElement LoginOption;
		

		@FindBy(xpath = "//*[@placeholder='Email']")
		private WebElement EnterEmail;
	
		WebDriverWait(browser, 20).until(EC.element_to_be_clickable((By.XPATH, "//a[@class='addSuppData-trigger pts' and starts-with(@data-target, 'edit_')]/i[@class='material-icons black-text tiny-small' and contains(., 'edit')]"))).click()
		
		
		@FindBy(xpath = "//*[@placeholder='Password']")
		private WebElement EnterPassword;
		
		@FindBy(xpath = "//*[text()='Login']//parent::a")
		private WebElement Loginbutton;
		
		
		public Loginphp clicklogin() {
			return clickloginbutton();
		}
		
		public Loginphp clickloginbutton() {
			LoginAccount.click();
			LoginOption.click();
			EnterEmail.sendKeys("kkg@gmail.com");
			EnterPassword.sendKeys("xyz");
			Loginbutton.click();
			return this;
		}
		
		
		
}
