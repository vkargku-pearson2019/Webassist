package webassist.admin;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import webassist.BaseTest;
import webassist.applicationflow.admin.Admin;
import webassist.data.GetTestData;

public class AdminTest extends BaseTest{
	
	private Admin admin;
	private String path = "testdata/Admin/Admin.properties";
	private String username = GetTestData.getOutputTestData(path, "username");
	private String roleStatus = GetTestData.getOutputTestData(path, "roleStatus");
	private String confViewStatus = GetTestData.getOutputTestData(path, "confViewStatus");
	
	@Test(description="Admin page search Username using Search Filter Button")
	private void AdminUsernameFilter() throws InterruptedException{ 
		admin = new Admin(wd);
		admin.filterUserName(username);
		extentTest.log(Status.INFO,"Admin Page Search with Username : " + username);
	}
	
	@Test(description="Admin page Add User",
			dependsOnMethods="AdminUsernameFilter")
	private void AdminUsernameAddRole() throws InterruptedException{ 
		Assert.assertTrue(admin.addUser(username, roleStatus, confViewStatus), 
				"Unable to add User");
	}
	
	@Test(description="Admin page edit User",
			dependsOnMethods="AdminUsernameAddRole")
	private void AdminUsernameEditUser() throws InterruptedException{ 
		Assert.assertTrue(admin.editRole(username), 
				"Unable to edit User");
	}
	
	@Test(description="Admin page delete User",
			dependsOnMethods="AdminUsernameEditUser")
	private void AdminUsernameDeleteUser() throws InterruptedException{ 
		Assert.assertTrue(admin.deleteRole(username), 
				"Unable to delete User");
	}
}
