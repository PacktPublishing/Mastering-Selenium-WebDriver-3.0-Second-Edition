package com.masteringselenium.query_page_objects;

import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;


public class PageHeader extends BasePage {

    private Query servicesLink = new Query(By.cssSelector(".nav li:nth-child(1) > a"), driver);
    private Query contactLink = new Query(By.cssSelector(".nav li:nth-child(2) > a"), driver);

    public void goToTheServicesPage() {
        servicesLink.findWebElement().click();
    }

    public void goToTheContactPage() {
        contactLink.findWebElement().click();
    }
}