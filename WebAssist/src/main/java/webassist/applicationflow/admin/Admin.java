package webassist.applicationflow.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webassist.util.Util;

public class Admin {

	private WebDriver wd;
	private Util util;

	public Admin(WebDriver wd) {
		this.wd = wd;
		util = new Util(wd);
		PageFactory.initElements(wd, this);
	}

	@FindBy(xpath = "(//*[contains(text(),'Admin')])[1]")
	private WebElement adminTab;

	@FindBy(xpath = "//input[@placeholder='User Name Filter']")
	private WebElement userNameField;

	@FindBy(xpath = "//*[text()='Filter']")
	private WebElement filterButton;

	@FindBy(xpath = "//*[@placeholder='User ID']")
	private WebElement userIDField;

	@FindBy(xpath = "//span[text()='Role']")
	private WebElement selectRoleDrpdwn;

	@FindBy(xpath = "//*[@formcontrolname='roleActive']//div[@class='mat-select-arrow-wrapper']")
	private WebElement selectRoleStatusDrpdwn;

	@FindBy(xpath = "//*[@formcontrolname='confidentialView']//div[@class='mat-select-arrow-wrapper']")
	private WebElement confViewDrpdwn;

	@FindBy(xpath = "//*[text()='Add/Change']")
	private WebElement addChangeUserButton;

	@FindBy(xpath = "//button[text()='Edit']")
	private WebElement editButton;
	
	@FindBy(xpath = "//button[text()='Delete']")
	private WebElement deleteButton;

	@FindBy(xpath = "//*[@role='option']//span[contains(text(),'Y')]")
	private WebElement optY;

	@FindBy(xpath = "//*[@role='option']//span[contains(text(),'N')]")
	private WebElement optN;

	public Admin filterUserName(String username) {
		return clickAdminTab()
				.enterUserName(username)
				.clickFilterButton();
	}

	public boolean addUser(String username, String roleStatus, String confViewStatus) {
		return enterUserID(username)
				.selectRole(username)
				.selectRoleActive(roleStatus)
				.selectConfView(confViewStatus)
				.clickAddOrChangeUserButton()
				.verifyAddUser(username);
	}

	public boolean editRole(String username) {
		return clickFirstRow(username)
				.clickEditButton()
				.selectRoleActiveEdit(username)
				.selectConfViewEdit(username)
				.clickAddOrChangeUserButton()
				.verifyAddUser(username);
	}
	
	public boolean deleteRole(String username) {
		return clickFirstRow(username)
				.clickDeleteButton()
				.verifyDeletedUser(username);
	}

	public Admin clickFirstRow(String username) {
		JavascriptExecutor je = ((JavascriptExecutor) wd);
		je.executeScript("arguments[0].scrollIntoView(true);",editButton);
		wd.findElement(By.xpath("(//virtual-scroller//*[text()='" + username  + "'])[1]")).click();
		util.Wait(2);
		//String deletedRole = wd.findElement(By.xpath("((//virtual-scroller//*[text()='" + username  + "'])[1]/following::*)[1]")).getText();
		return this;
	}

	private Admin clickEditButton() {
		JavascriptExecutor je = ((JavascriptExecutor) wd);
		je.executeScript("arguments[0].scrollIntoView(true);",editButton);
		editButton.click();
		util.Wait(2);
		return this;
	}
	
	private Admin clickDeleteButton() {
		JavascriptExecutor je = ((JavascriptExecutor) wd);
		je.executeScript("arguments[0].scrollIntoView(true);",deleteButton);
		deleteButton.click();
		util.waitForPageToBeVisible();
		return this;
	}

	private Admin clickAdminTab() {
		adminTab.click();
		util.Wait(2);
		return this;
	}

	private Admin enterUserName(String username) {
		if(util.isVisible(userNameField, 60)&
				util.isClickable(userNameField, 60)) {
			userNameField.sendKeys(username);
			util.Wait(2);
		}
		return this;
	}

	private Admin clickFilterButton() {
		filterButton.click();
		util.waitForPageToBeVisible();
		return this;
	}

	private Admin enterUserID(String username) {
		userIDField.sendKeys(username);
		return this;
	}

	private Admin selectRole(String username) {
		selectRoleDrpdwn.click();
		util.Wait(2);
		addRemRoles(username);
		util.Wait(1);
		return this;
	}

	private Admin selectRoleActive(String roleStatus) {
		selectRoleStatusDrpdwn.click();
		util.Wait(2);
		wd.findElement(By.xpath("//span[contains(text(),' " + roleStatus + " ')]")).click();
		util.Wait(1);
		return this;
	}

	private Admin selectRoleActiveEdit(String username) {
		selectRoleStatusDrpdwn.click();
		util.Wait(2);
		if(wd.findElement(By.xpath("((//virtual-scroller//*[text()='" + username  +"'])/following-sibling::*[1])[1]/following-sibling::*[1]"))
				.getText().equalsIgnoreCase("Y")) {
			optN.click();
		}else {
			optY.click();
		}

		util.Wait(1);
		return this;
	}

	private Admin selectConfView(String confViewStatus) {
		confViewDrpdwn.click();
		util.Wait(2);
		wd.findElement(By.xpath("//span[contains(text(),' " + confViewStatus + " ')]")).click();
		util.Wait(1);
		return this;
	}

	private Admin selectConfViewEdit(String username) {
		confViewDrpdwn.click();
		util.Wait(2);
		if(wd.findElement(By.xpath("((//virtual-scroller//*[text()='" + username  +"'])/following-sibling::*[1])[1]/following-sibling::*[2]"))
				.getText().equalsIgnoreCase("Y")) {
			optN.click();
		}else {
			optY.click();
		}
		util.Wait(1);
		return this;
	}

	private Admin clickAddOrChangeUserButton() {
		addChangeUserButton.click();
		util.waitForPageToBeVisible();
		return this;
	}
	
	private boolean verifyAddUser(String username) {
		return util.isVisible("//*[contains(text(),'Updated " + username + "')]", 10);
	}
	
	private boolean verifyDeletedUser(String username) {
		return util.isVisible("//*[contains(text(),'Deleted " + username + "')]", 10);
	}

	private void addRemRoles(String username) {
		String[] roleList = { "VIEW ONLY", "MATCH", "VERIFY", "ADMIN", "SUPER" };
		List<String> masterRolelist = new ArrayList<String>();
		List<String> existingRoleList = new ArrayList<String>();
		masterRolelist.addAll(Arrays.asList(roleList));
		int rolesize = wd.findElements(By.xpath("//virtual-scroller//*[text()='" + username + "']/following-sibling::*[1]"))
				.size();
		List<WebElement> listofroles = wd
				.findElements(By.xpath("//virtual-scroller//*[text()='" + username + "']/following-sibling::*[1]"));
		if (listofroles.size() != 0) {
			for (int i = 0; i < rolesize; i++) {
				existingRoleList.add(listofroles.get(i).getText());
			}
		}
		masterRolelist.removeAll(existingRoleList);
		String[] remRole = masterRolelist.toArray(new String[masterRolelist.size()]);
		if (!(remRole.length == 0)) {
			wd.findElement(By.xpath("//span[contains(text(),' " + remRole[0] + " ')]")).click();
		} else {
			System.out.println("All roles updated");
		}
	}
}
