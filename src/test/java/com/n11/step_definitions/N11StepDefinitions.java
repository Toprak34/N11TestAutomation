package com.n11.step_definitions;


import com.n11.pages.CampaignsPageElements;
import com.n11.utilities.ConfigurationReader;
import com.n11.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;


public class N11StepDefinitions {

    WebDriver driver = Driver.get();
    WebDriverWait wait = new WebDriverWait(Driver.get(), 15);

    CampaignsPageElements campaignsPageElements = new CampaignsPageElements();

    @Given("User go to website and go campaigns category")
    public void user_go_to_website_and_go_campaigns_category() {
        //Goes to the given url
        driver.get(ConfigurationReader.get("url"));

        //Confirmation that expected url equals current url
        Assert.assertEquals(ConfigurationReader.get("url"), driver.getCurrentUrl());

        //Wait until close cookies button is clickable
        wait.until(ExpectedConditions.elementToBeClickable(campaignsPageElements.closeCookie));

        //Close cookies
        campaignsPageElements.closeCookies();
    }

    @When("User make a loop for each of the campaigns will be clicked in")
    public void user_make_a_loop_for_each_of_the_campaigns_will_be_clicked_in() {
        //Click each category one by one and get campaign urls in the n11 campaigns page
        campaignsPageElements.getCampaignUrls();
    }

    @Then("All the campaigns url export the excel file")
    public void all_the_campaigns_url_export_the_excel_file() throws IOException {
        //Import all urls which pulled from categories and write to the Excel
        campaignsPageElements.exportTheExcel();
    }
}
