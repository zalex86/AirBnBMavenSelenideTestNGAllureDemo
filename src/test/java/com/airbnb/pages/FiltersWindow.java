package com.airbnb.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

// page_url = https://www.airbnb.com/
public class FiltersWindow {
    public SelenideElement inputPriceFilterMin = $("input[id='price_filter_min']");

    public SelenideElement inputPriceFilterMax = $("#price_filter_max");
}