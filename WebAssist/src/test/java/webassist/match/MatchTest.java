package webassist.match;

import org.testng.Assert;
import org.testng.annotations.Test;

import webassist.BaseTest;
import webassist.applicationflow.match.Match;
import webassist.applicationflow.nomatchF.Matchnomatch;

public class MatchTest extends BaseTest {
	private Match match;
	private Matchnomatch matchN;
	/*
	@Test(description="Match the Possible match from the List") 
	private void MatchTest() throws InterruptedException {
		match = new Match(wd);
		Assert.assertTrue(match.matchTest(userName),
				"Match not Working");
	}
	*/
	
	@Test(description="No Match the Possible match from the List") 
	private void NoMatchTest() throws InterruptedException {
		match = new Match(wd);
		Assert.assertTrue(match.noMatchTest(userName), 
				"No Match not Working");
	}
	
	
	@Test(description="No Match the Possible match from the List & set it to Match from Super") 
	private void NoMatchTestF() throws InterruptedException {
		matchN = new Matchnomatch(wd);
		Assert.assertTrue(matchN.noMatchTest(userName), 
				"No Match not Working");
	}
	
	/*
	@Test(description="Alert the Possible match from the List") 
	private void AlertTest() throws InterruptedException {
		match = new Match(wd);
		Assert.assertTrue(match.alertTest(userName), 
				"Alert not Working");
	}
	*/

}
