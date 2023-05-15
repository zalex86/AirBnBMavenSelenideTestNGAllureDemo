//package com.airbnb.helpers;
//
//import com.codeborne.selenide.Selenide;
//import com.codeborne.selenide.WebDriverRunner;
//import io.qameta.allure.Allure;
//import io.qameta.allure.Attachment;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import java.util.logging.Level;
//
//import static com.airbnb.tests.BaseSetup.LOGGER;
//
//public class EventListener implements ITestListener {
//
//    @Override
//    public void onTestStart(ITestResult iTestResult) {
//        LOGGER.log(Level.INFO, "Тест стартовал " + iTestResult.getMethod().getDescription());
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult tr) {
//        LOGGER.log(Level.INFO, "Тест завершился успешно " + tr.getMethod().getDescription());
////        logNetwork();
//        Selenide.closeWebDriver();
//    }
//
//    @Override
//    public void onTestFailure(ITestResult tr) {
//        String testDescription = tr.getMethod().getDescription();
//        LOGGER.log(Level.INFO, "Тест завершился c ошибкой " + testDescription);
//        WebDriver driver = WebDriverRunner.getWebDriver();
//        Allure.getLifecycle().addAttachment("screenshot",
//                "image/png", "png",
//                ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)
//        );
//
////        try {
////            addAttachment();
////        } catch (NullPointerException e){
////            System.out.println(e);
////        }
//
////        logNetwork();
//        Selenide.closeWebDriver();
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult tr) {
//        addAttachment();
//        LOGGER.log(Level.INFO, "Тест скипнулся " + tr.getMethod().getDescription());
////        logNetwork();
//        Selenide.closeWebDriver();
//    }
//
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
//        Selenide.closeWebDriver();
//    }
//
//    @Override
//    public void onStart(ITestContext iTestContext) {}
//
//    @Override
//    public void onFinish(ITestContext iTestContext) {
//        Selenide.closeWebDriver();
//    }
//
//    @Attachment(value = "{attachName}", type = "application/json")
//    public String attachJson(String attachName, String json) {
//        return json;
//    }
//
//    private String getNetwork(){
//        String scriptToExecute = "var performance = window.performance || window.mozPerformance || " +
//                "window.msPerformance || window.webkitPerformance || {}; " +
//                "var network = performance.getEntries() || {}; return network;";
//        return ((JavascriptExecutor)WebDriverRunner.getWebDriver()).executeScript(scriptToExecute).toString();
//    }
//
//    private void logNetwork() {
//        if (System.getProperty("logging").equals("true")) {
//            attachJson("network", getNetwork());
//        } else {
//            attachJson("network", "no json");
//        }
//    }
//
//    @Attachment(value = "{0}", type = "image/png")
//    public static byte[] saveScreenshot(String description, WebDriver webDriver) {
//        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
//    }
//
//    private void addAttachment() {
//        try{
//            WebDriver driver = WebDriverRunner.getWebDriver();
//            saveScreenshot(driver.getCurrentUrl(), driver);
//        } catch (IllegalStateException e){
//            e.printStackTrace();
//        }
//    }
//}