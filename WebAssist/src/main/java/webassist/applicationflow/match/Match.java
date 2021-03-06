package webassist.applicationflow.match;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import webassist.applicationflow.role.Role;
import webassist.applicationflow.superSearch.Search;
import webassist.util.Util;

public class Match {

	private WebDriver wd;
	private Util util;
	private Role oRole;
	private Search search;
	private String role = "SUPER";
	String[] uinNumber;

	public Match(WebDriver wd){
		this.wd = wd;
		util = new Util(wd);
		oRole = new Role(wd);
		search = new Search(wd);
		PageFactory.initElements(wd, this);
	}

	@FindBy(xpath = "//*[@class='tabLarge']//div[text()='Search']")
	private WebElement searchTab;

	@FindBy(xpath = "//button[text()='Get Work']")
	private WebElement getWorkButton;

	@FindBy(xpath = "//*[contains(@class,'tableRow')][1]")
	private WebElement possibleMatch;

	@FindBy(xpath = "//button[text()='Match']")
	private WebElement matchTab;

	@FindBy(xpath = "//button[text()='No Match']")
	private WebElement noMatchTab;

	@FindBy(xpath = "//button[text()='Alert']")
	private WebElement alertTab;

	@FindBy(xpath = "//*[contains(text(),'Yes')]")
	private WebElement popupYesButton;

	@FindBy(xpath = "//*[contains(text(),'No')]")
	private WebElement popupNoButton;

	@FindBy(xpath = "//*[contains(@class,'logDescription')]")
	private WebElement logText;

	@FindBy(xpath = "//button[text()='Proceed']" )
	private WebElement proceed;

	@FindBy(xpath = "(//*[contains(@class,'tableRow ')])[1]" )
	private WebElement matchedItem;

	@FindBy(xpath = "//*[contains(@class,'showMatchPan')]" )
	private WebElement matchStatus;
	
	@FindBy(xpath = "//*[contains(text(),'IA')]" )
	private WebElement possiblematches;
	

	public boolean matchTest(String userName) {
		return clickSearchTab()
				.clickGetWorkButton()
				.clickPossibleMatch()
				.clickMatchTab()
				.clickPopupButton()
				.fetchUIN()
				.changeRoleToSuper(userName)
				.uinSearch()
				.clickMatchedItem()
				.matchVerification();
	}
	
	
	

	public boolean noMatchTest(String userName) {
		return clickSearchTab()
				.clickGetWorkButton()
				.clickPossibleMatch()
				.clickNoMatchTab()
				.clickPopupButton()
				.fetchUIN()
				.changeRoleToSuper(userName)
				.uinSearch()
				.clickMatchedItem()
				.matchVerification();
	}
	


	public boolean alertTest(String userName) {
		return clickGetWorkButton()
				.clickPossibleMatch()
				.clickAlertTab()
				.clickPopupButton()
				.fetchUIN()
				.changeRoleToSuper(userName)
				.uinSearch()
				.clickMatchedItem()
				.matchVerification();
	}
	

	private Match clickSearchTab() {
		searchTab.click();
		util.waitForPageToBeVisible();
		util.Wait(3);
		return this;
	}

	private Match clickGetWorkButton() {
		if(util.isVisible(getWorkButton, 60)&
				util.isClickable(getWorkButton, 60)) {
			getWorkButton.click();
			util.waitForPageToBeVisible();
			util.Wait(3);
		}
		return this;
	}

	private Match clickPossibleMatch() {
		JavascriptExecutor je = ((JavascriptExecutor) wd);
		je.executeScript("arguments[0].scrollIntoView(true);",possibleMatch);
		possibleMatch.click();
		util.Wait(3);
		return this;
	}

	private Match clickMatchTab() {
		JavascriptExecutor je = ((JavascriptExecutor) wd);
		je.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		matchTab.click();
		util.Wait(3);
		return this;
	}

	private Match clickNoMatchTab() {
		JavascriptExecutor je = ((JavascriptExecutor) wd);
		je.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		noMatchTab.click();
		util.Wait(3);
		return this;
	}

	private Match clickAlertTab() {
		JavascriptExecutor je = ((JavascriptExecutor) wd);
		je.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
		alertTab.click();
		util.Wait(3);
		return this;
	}

	private Match clickPopupButton() {
		popupYesButton.click();
		util.Wait(3);
		util.waitForPageToBeVisible();
		util.Wait(5);
		return this;
	}

	private Match fetchUIN() {
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(logText.getText());
		while(m.find()) {
			String uin =m.group(0);
			uinNumber = uin.split("\\s+");	        
			break;
		}
		return this;
	}

	private Match changeRoleToSuper(String userName) {
		oRole.changeRole();
		oRole.roleSelection(role, userName);
		return this;
	}

	private Match uinSearch() {
		search.uinSearch(uinNumber[0]);
		return this;
	}

	private Match clickMatchedItem() {
		JavascriptExecutor je = ((JavascriptExecutor) wd);
		je.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		matchedItem.click();
		util.waitForPageToBeVisible();
		util.Wait(2);
		return this;
	}
	
	
	private Match clickPossiblenmatches() {
		JavascriptExecutor je = ((JavascriptExecutor) wd);
		je.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		possiblematches.click();
		util.waitForPageToBeVisible();
		util.Wait(2);
		return this;
	}

	private boolean matchVerification() {
		return (search.uinVerification(uinNumber[0])&
				search.isMatchedCorrectly(matchStatus.getText()
						.replaceAll("Matcher","")
						.replaceAll("DB2ADMIN","")
						.replaceAll("\\(","")
						.replaceAll("\\)","")
						.replaceAll("-","")
						.trim()));
	}
}
