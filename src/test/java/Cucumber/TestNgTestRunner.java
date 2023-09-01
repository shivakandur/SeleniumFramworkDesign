package Cucumber;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber", glue="rahulshettyacademy.stepdefinition",
monochrome=true, plugin= {"html:target/cucumber.html"})
public class TestNgTestRunner {

}
