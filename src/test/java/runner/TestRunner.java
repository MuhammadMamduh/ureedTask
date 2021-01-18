package runner;

import io.cucumber.testng.CucumberOptions;
import tests.TestsBase;


    @CucumberOptions(features="src/test/java/features"
            ,glue= {"steps"}
            ,plugin= {"pretty","html:target/cucumber-html-report"})
    public class TestRunner extends TestsBase
    {


    }

