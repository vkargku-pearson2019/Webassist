package webassist.admin;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.admin.Admin;

public class AdminTest extends BaseTest{
	
	private Admin admin;
	
	@Test(description="Admin page search Username using Search Filter Button")
	@Parameters({"username"}) 
	private void AdminUsernameFilter(String username) throws InterruptedException{ 
		admin = new Admin(wd);
		admin.filterUserName(username);
	}
	
	@Test(description="Admin page Add User",
			dependsOnMethods="AdminUsernameFilter")
	@Parameters({"username","roleStatus","confViewStatus"}) 
	private void AdminUsernameAddRole(String username,String roleStatus,String confViewStatus) throws InterruptedException{ 
		admin = new Admin(wd);
		Assert.assertTrue(admin.addUser(username, roleStatus, confViewStatus), 
				"Unable to add User");
	}
	
	@Test(description="Admin page edit User",
			dependsOnMethods="AdminUsernameAddRole")
	@Parameters({"username"}) 
	private void AdminUsernameEditUser(String username) throws InterruptedException{ 
		admin = new Admin(wd);
		Assert.assertTrue(admin.editRole(username), 
				"Unable to edit User");
	}
	
	@Test(description="Admin page delete User",
			dependsOnMethods="AdminUsernameEditUser")
	@Parameters({"username"}) 
	private void AdminUsernameDeleteUser(String username) throws InterruptedException{ 
		admin = new Admin(wd);
		Assert.assertTrue(admin.deleteRole(username), 
				"Unable to delete User");
	}
}
