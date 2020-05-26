package screens.search;

import io.appium.java_client.MobileElement;
import org.testng.Assert;
import utils.MobileBrowserWait;

import java.util.List;

import static screens.search.SearchScreenLocators.*;
import static utils.MobileActions.*;


public class SearchScreen extends MobileBrowserWait {

    public boolean checkIfSearchTextFieldIsDisplayed() {
        return driver.findElement(SEARCH_TEXT_FIELD_ID).isDisplayed();
    }

    public boolean checkIfSearchButtonIsDisplayed() {
        return driver.findElement(SEARCH_BUTTON_ID).isDisplayed();
    }

    public boolean checkIfClearSearchButtonIsDisplayed() {
        return driver.findElement(CLEAR_SEARCH_BUTTON_ID).isDisplayed();
    }

    public void performSearch(String searchParam) {
        waitUntilElementIsPresent(SEARCH_TEXT_FIELD_ID);
        clearAndfillInFieldWith(SEARCH_TEXT_FIELD_ID, searchParam);
        clickElementBy(SEARCH_BUTTON_ID);
    }

    public void clickResetSearchButton() {
        waitUntilElementIsPresent(CLEAR_SEARCH_BUTTON_ID);
        clickElementBy(CLEAR_SEARCH_BUTTON_ID);
    }

    public int getNumberOfSearchResults() {
        return getNumberOfResultsForElement(SEARCH_ITEM_TEXT_ID);
    }

    public void verifyIfSearchResultsContainsSpecificSearchInput(String input) {
        List<MobileElement> results = driver.findElements(SEARCH_ITEM_TEXT_ID);
        for (int i = 0; i < results.size(); i++) {
            Assert.assertTrue(results.get(i).getText().toLowerCase().contains(input), "Search result validation failed at instance+" + i + "");
        }
    }

    public String getSearchFieldPlaceholderText() {
        waitUntilElementIsPresent(SEARCH_TEXT_FIELD_ID);
        return getTextFromElement(SEARCH_TEXT_FIELD_ID);
    }


}
