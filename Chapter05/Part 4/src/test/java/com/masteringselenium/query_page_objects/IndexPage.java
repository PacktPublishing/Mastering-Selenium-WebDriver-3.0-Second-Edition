package com.masteringselenium.query_page_objects;

import com.lazerycode.selenium.util.Query;
import org.openqa.selenium.By;

public class IndexPage extends BasePage {

    private Query heading = new Query(By.cssSelector("h1"), driver);
    private Query mainText = new Query(By.cssSelector(".col-md-4 > p"), driver);
    private Query button = new Query(By.cssSelector(".btn"), driver);

    public PageHeader header = new PageHeader();
    public PageFooter footer = new PageFooter();

    public boolean mainTextIsDisplayed() {
        return mainText.findWebElements().size() == 1;
    }

    public boolean mainPageButtonIsDisplayed() {
        return button.findWebElements().size() == 1;

    }
}