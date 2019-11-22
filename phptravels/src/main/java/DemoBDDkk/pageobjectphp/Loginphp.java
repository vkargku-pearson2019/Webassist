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

		@FindBy(xpath = "//a[text()='Check-in']")
		private WebElement checkflight;
		

		@FindBy(xpath = "//*[@id='checkin-form']//input[@placeholder='PNR/Booking Ref.']")
		private WebElement bookingref;
		
		@FindBy(xpath = "//*[@id='checkin-form']//option[@value='6E']")
		private WebElement webcheck;
		
		@FindBy(xpath = " //*[@id='checkin-emailorlast']")
		private WebElement Email;
		
		@FindBy(xpath = "//button[@type='submit'][text()='Check-in']")
		private WebElement checkinbutton;
		
		
		@FindBy(xpath = "//span[@class='infoSecondText']")
		private WebElement Errorcode;
		
		
}
