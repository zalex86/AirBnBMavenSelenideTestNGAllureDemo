package com.airbnb.tests;

import com.airbnb.helpers.DriverFactory;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.open;

//@Listeners({EventListener.class})
public class BaseSetup {
    public static String SERVER = "https://www.airbnb.com/";
    public static Logger LOGGER = Logger.getLogger("");

    @BeforeSuite
    public void setupServer() {
        serverSetup();
    }

    @BeforeMethod
    public final void setUpDriver() {
        DriverFactory.setUpDriver();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .savePageSource(false)
                .includeSelenideSteps(false));
        open(SERVER);
    }

//    @AfterMethod
//    public final void closeWebDriver() {
//        Selenide.closeWebDriver();
//    }

    private void serverSetup() {
        SERVER = System.getProperty("server");

    }
}
