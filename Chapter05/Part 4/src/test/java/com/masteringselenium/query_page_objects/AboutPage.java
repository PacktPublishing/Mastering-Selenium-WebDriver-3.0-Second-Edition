package com.masteringselenium.query_page_objects;

import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;

public class AboutPage extends BasePage {

    private Query heading = new Query(By.cssSelector("h1"), driver);
    private Query aboutUsText = new Query(By.cssSelector(".col-md-4 > p"));

    public PageHeader header = new PageHeader();
    public PageFooter footer = new PageFooter();

    public boolean aboutUsTextIsDisplayed() {
        return aboutUsText.findWebElements().size() == 1;
    }
}