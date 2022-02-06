package com.n11.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},//Type "allure serve" in terminal to get reports of test
        features = "src/test/resources/features/",//path to access feature files
        glue = "com/n11/step_definitions",
        tags = ""//scenario name
)

public class CukesRunner {

}
