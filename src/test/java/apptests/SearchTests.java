package apptests;

import basetest.BaseTestAndroid;
import commons.ReusableComponents;
import dataProviders.ConfigFileReader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import listeners.TestAllureListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;
import screens.login.LoginScreen;
import screens.search.SearchScreen;

import static constants.Constants.PLACEHOLDER_TEXT_SEARCH_FIELD;
import static constants.Constants.SCREEN_TITLE;

@Listeners({TestAllureListener.class})
@Owner("Surendra Singh")
public class SearchTests extends BaseTestAndroid {

    LoginScreen loginScreen = new LoginScreen();
    ConfigFileReader configFileReader = new ConfigFileReader();
    ReusableComponents reusableComponents = new ReusableComponents();
    SearchScreen searchScreen = new SearchScreen();

    @BeforeClass
    public void login(){
        loginScreen.enterEmailAddress(configFileReader.getUserName());
        loginScreen.enterPassword(configFileReader.getPassword());
        loginScreen.clickLoginButton();
    }

    @Title("Search page basic validations")
    @Description("Verify search page basic validations after successful login ")
    @Test(groups = {"SMOKE"})
    public void searchPageBasicValidations(){
        Assert.assertEquals(reusableComponents.getScreenTitle(), SCREEN_TITLE);
        Assert.assertTrue(searchScreen.checkIfSearchTextFieldIsDisplayed(), "Search field is missing");
        Assert.assertTrue(searchScreen.checkIfSearchButtonIsDisplayed(), "Search button is missing");
        Assert.assertTrue(searchScreen.checkIfClearSearchButtonIsDisplayed(), "Clear Search button is missing");
    }

    @Title("Search with numeric input of length two")
    @Description("Verify search functionality with numeric input of length two")
    @Test(groups = {"SMOKE"})
    public void searchWithNumericInputOfLengthTwo(){
        int searchParam = 43;
        int expectedSearchResults = 3;
        searchScreen.performSearch(Integer.toString(searchParam));
        Assert.assertEquals(searchScreen.getNumberOfSearchResults(),expectedSearchResults);
        searchScreen.verifyIfSearchResultsContainsSpecificSearchInput(Integer.toString(searchParam));
    }

    @Title("Search with numeric input of length three")
    @Description("Verify search functionality with numeric input of length three")
    @Test(groups = {"SMOKE"})
    public void searchWithNumericInputOfLengthThree(){

        int searchParam = 560;
        int expectedSearchResults = 1;
        searchScreen.performSearch(Integer.toString(searchParam));
        Assert.assertEquals(searchScreen.getNumberOfSearchResults(),expectedSearchResults);
        searchScreen.verifyIfSearchResultsContainsSpecificSearchInput(Integer.toString(searchParam));
    }

    @Title("Search with string input of length two")
    @Description("Verify search functionality with string input of length two")
    @Test(groups = {"SMOKE"})
    public void searchWithStringInputOfLengthTwo(){

        String searchParam = "ar";
        int expectedSearchResults = 6;
        searchScreen.performSearch(searchParam);
        Assert.assertEquals(searchScreen.getNumberOfSearchResults(),expectedSearchResults);
        searchScreen.verifyIfSearchResultsContainsSpecificSearchInput(searchParam);
    }

    @Title("Search with string input of length three")
    @Description("Verify search functionality with string input of length three")
    @Test(groups = {"SMOKE"})
    public void searchWithStringInputOfLengthThree(){
        String searchParam = "arm";
        int expectedSearchResults = 3;
        searchScreen.performSearch(searchParam);
        Assert.assertEquals(searchScreen.getNumberOfSearchResults(),expectedSearchResults);
        searchScreen.verifyIfSearchResultsContainsSpecificSearchInput(searchParam);
    }

    @Title("Reset search functionality")
    @Description("Verify reset search functionality is working fine")
    @Test(groups = {"SMOKE"})
    public void resetSearch(){
        String searchParam = "arm";
        int expectedSearchResults = 3;
        searchScreen.performSearch(searchParam);
        Assert.assertEquals(searchScreen.getNumberOfSearchResults(),expectedSearchResults);
        searchScreen.clickResetSearchButton();
        Assert.assertTrue(searchScreen.getNumberOfSearchResults()>expectedSearchResults);
        Assert.assertEquals(searchScreen.getSearchFieldPlaceholderText(),PLACEHOLDER_TEXT_SEARCH_FIELD);
    }

}
