package com.n11.pages;

import com.n11.utilities.Driver;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CampaignsPageElements {
    public CampaignsPageElements() {
        PageFactory.initElements(Driver.get(), this);}

    static WebDriver driver = Driver.get();
    //Close cookie button element
    @FindBy(xpath ="//*[@id=\"cookieUsagePopIn\"]/span")
    public WebElement closeCookie;


    //Adds category names on the campaigns page to the list
    @FindBy(xpath ="//*[@class=\"campPromTab\"]/li")
    public  List<WebElement> categoryTitle;


    //gets each category title name and clicks in a loop
    public WebElement getCategoryTitle(int categoryNumber) {
        return driver.findElement(By.xpath("//*[@class=\"campPromTab\"]/li[" + categoryNumber + "]"));
    }

    //
    public List<WebElement> getCampaignsNumbers(int categoryNumber) {
        return driver.findElements(By.xpath("//*[@class=\"tabPanel\"]/div/section[" + categoryNumber + "]/ul/li/a"));
    }

    //
    public WebElement getCampaigns(int categoryNumber, int campaignNumber) {
        return driver.findElement(By.xpath("//*[@class=\"tabPanel\"]/div/section[" + categoryNumber + "]/ul/li[" + campaignNumber + "]/a"));
    }

    ArrayList<String> categoryName = new ArrayList<>();
    ArrayList<String> campaignsUrlText = new ArrayList<>();


    public void closeCookies() {
        //Click and close cookies
        closeCookie.click();
    }

    public void getCampaignUrls() {

        //Count all each category in a loop
        for (int i = 1; i <= categoryTitle.size(); i++) {
            //Click each category
            getCategoryTitle(i).click();
            //Count all campaigns each category in a loop
            for (int j = 1; j <= getCampaignsNumbers(i).size(); j++) {
                //Get next category
                categoryName.add(getCategoryTitle(i).getText());
                //Add campaign urls in each category to an array list
                campaignsUrlText.add(getCampaigns((i), j).getAttribute("href"));
            }
        }
    }

    public void exportTheExcel() throws IOException {

        String excelFileName = "N11CampaignsUrls.xls";

        String sheetName = "CampaignUrls";

        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet(sheetName);

        //Count category name list in a loop
        for (int i = 0; i<categoryName.size(); i++){

            //Create rows and cells then set a value which is category name
            sheet.createRow(i).createCell(0).setCellValue(categoryName.get(i));

            //Create rows and cells then set a value which is campaign urls
            sheet.getRow(i).createCell(1).setCellValue(campaignsUrlText.get(i));
        }
        FileOutputStream fos = new FileOutputStream(excelFileName);

        //Write this workbook to a stream
        workbook.write(fos);
        fos.flush();
        fos.close();
    }
}



