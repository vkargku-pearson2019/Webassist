package webassist.login;
import java.io.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import webassist.BaseTest;
import webassist.applicationflow.login.Login;
import webassist.util.GetEnvironmentData;
import webassist.util.Util;


public class LoginTest extends BaseTest {
	private Login login;
	private Util util;
	
	@Test(description="Login to WebAssist with invalid user name")
	public void loginWebAssistIncorrectUserName() throws InterruptedException, IOException {
		login = new Login(wd);
		util= new Util(wd);
		login.loginToWebAssist("invalid", "Kolkata@1", "169-358");
		Assert.assertTrue(login.validateBadCredential(),
				"Application is not showing Bad Credentials !!");
	}
	
 
	@Test(description="Login to WebAssist with invalid password")
	public void loginWebAssistIncorrectPassword() throws InterruptedException, IOException {
		login = new Login(wd);
		util= new Util(wd);
		login.loginToWebAssist("db2admin", "Kolkata", "169-358");
		Assert.assertTrue(login.validateBadCredential(),
				"Application is not showing Bad Credentials !!");
	}


	 @Test(description="Login to WebAssist with valid credentials")
	public void loginWebAssistCorrect() throws InterruptedException, IOException {
		login = new Login(wd);
		util= new Util(wd);
		userName = GetEnvironmentData.getAppEnvProperty("username");
		String password = GetEnvironmentData.getAppEnvProperty("password");
		String projectID = GetEnvironmentData.getAppEnvProperty("projectID");
		extentTest.log(Status.INFO,"User Name : " + userName);
		Assert.assertTrue(login.loginToWebAssist(userName, password, projectID), 
				"Unable to login to Web Assist");
	}
	
	

}
