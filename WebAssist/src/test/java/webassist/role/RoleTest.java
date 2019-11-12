package webassist.role;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.role.Role;

public class RoleTest extends BaseTest {

	private Role oRole;

	String[] roleNames = {"VIEW ONLY", "MATCH", "VERIFY", "ADMIN", "SUPER"};
	
	@Test(description="Select desired Role")  
	@Parameters({"role"})
	public void enterRole(String role) throws InterruptedException {
		oRole = new Role(wd);
		Assert.assertTrue(oRole.roleSelection(role,userName), 
				"Unable to select Role");
	}

	@Test(description="Select different Roles")  
	public void enterRoleName() throws InterruptedException {
		oRole = new Role(wd);
		for(int i=0; i<roleNames.length; i++) {
			if(i==0) {
				Assert.assertTrue(oRole.roleSelection(roleNames[i],userName), 
						"Unable to select Role");
			}else {
				oRole.changeRole();
				Assert.assertTrue(oRole.roleSelection(roleNames[i],userName), 
						"Unable to select Role");
			}
		}
	}
}
