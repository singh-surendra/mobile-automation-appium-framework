package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MobileActions extends MobileBrowserWait {

    public static void clickElementBy(By by) {
        tryFindElement(by).click();
    }

    public static void clearAndfillInFieldWith(By by, String text) {
        tryFindElement(by).clear();
        tryFindElement(by).sendKeys(text);
    }

    public static WebElement tryFindElement(By by) {
        WebElement element = (WebElement) getFluentWait().until(ExpectedConditions.presenceOfElementLocated(by));
        return element;
    }

    public static String getTextFromElement(By by) {
        return tryFindElement(by).getText().trim();
    }

    public static int getNumberOfResultsForElement(By by) {
        return driver.findElements(by).size();
    }


}

