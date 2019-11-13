package Stepdefination;

import org.junit.runner.RunWith;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
public class step {

    @Given("^Enter username password$")
    public void enter_username_password() throws Throwable {
        throw new PendingException();
    }

    @When("^wrong id provided$")
    public void wrong_id_provided() throws Throwable {
        throw new PendingException();
    }

   @Then("^verify page not opening$")
    public void verify_page_not_opening() throws Throwable {
        throw new PendingException();
    }

}
//comment