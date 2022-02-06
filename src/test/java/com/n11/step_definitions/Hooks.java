package com.n11.step_definitions;

import com.n11.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;

public class Hooks {

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions action;

    @Before
    public void setUp() {
        driver = Driver.get();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
        action = new Actions(driver);
        wait = new WebDriverWait(driver, 10);

    }

    @After
    public void tearDown(Scenario n11Test) {
        //Take screenshot in allure report
        if (n11Test.isFailed()){
            Allure.addAttachment(n11Test.getName(),new ByteArrayInputStream(((TakesScreenshot)driver)
                    .getScreenshotAs(OutputType.BYTES)));
        }
        Driver.closeDriver();
    }
}
