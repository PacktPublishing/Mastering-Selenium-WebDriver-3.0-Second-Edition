package com.masteringselenium.tests;

import com.masteringselenium.DriverBase;
import com.masteringselenium.query_page_objects.AboutPage;
import com.masteringselenium.query_page_objects.IndexPage;
import com.masteringselenium.query_page_objects.PageFooter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckAboutPageTextIT extends DriverBase {

    private WebDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        driver = getDriver();
    }

    @Test
    public void checkThatAboutPageHasTextStep1() {
        driver.get("http://web.masteringselenium.com/index.html");
        IndexPage indexPage = new IndexPage();

        assertThat(indexPage.mainTextIsDisplayed()).isEqualTo(true);
        assertThat(indexPage.mainPageButtonIsDisplayed()).isEqualTo(true);

        PageFooter footer = new PageFooter();
        footer.goToTheAboutUsPage();
        AboutPage aboutPage = new AboutPage();

        assertThat(aboutPage.aboutUsTextIsDisplayed()).isEqualTo(true);
    }

    @Test
    public void checkThatAboutPageHasTextStep2() {
        driver.get("http://web.masteringselenium.com/index.html");
        IndexPage indexPage = new IndexPage();

        assertThat(indexPage.mainTextIsDisplayed()).isEqualTo(true);
        assertThat(indexPage.mainPageButtonIsDisplayed()).isEqualTo(true);

        indexPage.footer.goToTheAboutUsPage();
        AboutPage aboutPage = new AboutPage();

        assertThat(aboutPage.aboutUsTextIsDisplayed()).isEqualTo(true);
    }

    @Test
    public void checkThatAboutPageHasTextStep3() {
        driver.get("http://web.masteringselenium.com/index.html");
        IndexPage indexPage = new IndexPage();

        assertThat(indexPage.mainTextIsDisplayed()).isEqualTo(true);
        assertThat(indexPage.mainPageButtonIsDisplayed()).isEqualTo(true);

        AboutPage aboutPage = indexPage.footer.goToTheAboutUsPage();

        assertThat(aboutPage.aboutUsTextIsDisplayed()).isEqualTo(true);
    }

    @Test
    public void checkThatAboutPageHasTextStep4() {
        driver.get("http://web.masteringselenium.com/index.html");
        indexPage = new IndexPage();

        assertThat(indexPage.mainTextIsDisplayed()).isEqualTo(true);
        assertThat(indexPage.mainPageButtonIsDisplayed()).isEqualTo(true);

        aboutPage = indexPage.footer.goToTheAboutUsPage();

        assertThat(aboutPage.aboutUsTextIsDisplayed()).isEqualTo(true);
    }

}
