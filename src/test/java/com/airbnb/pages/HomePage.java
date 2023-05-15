package com.airbnb.pages;

import com.airbnb.helpers.Waiters;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private final SelenideElement buttonLocationAnywhere = $("button[data-index='0']");
    private final SelenideElement buttonCheckOutAnyWeek = $("button[data-index='1']");
    private final SelenideElement divLittleSearchIcon = $("div[data-icon='true']");
    private final SelenideElement buttonFilters = $("button[data-testid='category-bar-filter-button']");
    private final SelenideElement buttonClose = $("button[aria-label='Close']");

    @Step("Click on Filters button")
    @Attachment()
    public FiltersWindow buttonFiltersClick() {
        buttonFilters.click();
        return new FiltersWindow();
    }

    @Step("Close ad block")
    public HomePage buttonCloseClick() {
        Waiters.waitElementVisibility(buttonClose);
        buttonClose.click();
        return this;
    }
}