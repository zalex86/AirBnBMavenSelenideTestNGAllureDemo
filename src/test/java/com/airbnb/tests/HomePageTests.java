package com.airbnb.tests;

import com.airbnb.pages.FiltersWindow;
import com.airbnb.pages.HomePage;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Feature("Home Page")
public class HomePageTests extends BaseSetup {

    @Test(description = "Filter window open check")
    @Story("Home Page filter")
    public void openFiltersWindow(){
        HomePage homePage = new HomePage()
                .buttonCloseClick();
        FiltersWindow filtersWindow = homePage.buttonFiltersClick();

        filtersWindow.inputPriceFilterMin
                .shouldBe(Condition.visible)
                .shouldHave(Condition.value("10"));
        filtersWindow.inputPriceFilterMax.shouldHave(Condition.value("200"));

    }
}
