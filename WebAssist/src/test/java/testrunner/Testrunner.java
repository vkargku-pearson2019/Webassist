package testrunner;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions (
       features = "src/test/java/features",
       glue="Stepdefination"
)
public class Testrunner {

}
