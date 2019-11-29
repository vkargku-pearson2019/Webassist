package DemoBDDkk.testfilesphp;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import  DemoBDDkk.pageobjectphp.Loginphp;
import resourcesphp.phputilfile;

public class Loginphptest extends basetestphp {
	
	private Loginphp loginphp;
	private phputilfile util;
	
	
@Test
	private void Loginphptravels() throws InterruptedException{ 
		loginphp = new Loginphp(wd);
		util=new phputilfile(wd);
		loginphp.clicklogin();
		}

	}


