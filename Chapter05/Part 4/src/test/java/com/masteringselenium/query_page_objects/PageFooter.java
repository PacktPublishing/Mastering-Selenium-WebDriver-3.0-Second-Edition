package com.masteringselenium.query_page_objects;

import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;

public class PageFooter extends BasePage {

    private Query aboutUsLink = new Query(By.cssSelector(".left-footer > a"), driver);

    public AboutPage goToTheAboutUsPage() {
        aboutUsLink.findWebElement().click();
        return new AboutPage();
    }
}