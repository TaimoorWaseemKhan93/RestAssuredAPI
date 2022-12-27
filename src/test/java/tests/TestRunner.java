package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"stepdefs"},
        plugin = { "pretty", "html:target/cucumber" },
        tags = "@ApiTestCases")

public class TestRunner extends AbstractTestNGCucumberTests
{

}
