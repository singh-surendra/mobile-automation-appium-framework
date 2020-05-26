package apptests;

import basetest.BaseTestAndroid;
import commons.ReusableComponents;
import dataProviders.ConfigFileReader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import listeners.TestAllureListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Title;
import screens.login.LoginScreen;
import screens.search.SearchScreen;
import utils.RandomHelper;

import static constants.Constants.*;

@Listeners({TestAllureListener.class})
@Owner("Surendra Singh")
public class LoginTests extends BaseTestAndroid {

    ConfigFileReader configFileReader = new ConfigFileReader();
    LoginScreen loginScreen = new LoginScreen();
    ReusableComponents reusableComponents = new ReusableComponents();
    SearchScreen searchScreen = new SearchScreen();
    RandomHelper randomHelper = new RandomHelper();

    @Title("Login page basic validations")
    @Description("Verify login page basic validations after app is launched")
    @Test(groups = {"SMOKE"})
    public void loginPageBasicValidations() {
        Assert.assertEquals(reusableComponents.getScreenTitle(), SCREEN_TITLE);
        Assert.assertTrue(loginScreen.checkIfEmailFieldIsDisplayed(), "Email field is not present");
        Assert.assertEquals(loginScreen.getEmailFieldPlaceholderText(), PLACEHOLDER_TEXT_EMAIL_FIELD);
        Assert.assertTrue(loginScreen.checkIfPasswordFieldIsDisplayed(), "Password field is not present");
        Assert.assertEquals(loginScreen.getPasswordFieldPlaceholderText(), PLACEHOLDER_TEXT_PASSWORD_FIELD);
        Assert.assertTrue(loginScreen.checkIfLoginButtonIsDisplayed(), "Login button is not present");
    }

    @Title("Login with correct credentials")
    @Description("Verify login is successful with valid credentials")
    @Test(groups = {"SMOKE"})
    public void loginIsSuccessfulWithCorrectCredentials() {
        loginScreen.enterEmailAddress(configFileReader.getUserName());
        loginScreen.enterPassword(configFileReader.getPassword());
        loginScreen.clickLoginButton();
        Assert.assertEquals(reusableComponents.getScreenTitle(), SCREEN_TITLE);
        Assert.assertTrue(searchScreen.checkIfSearchTextFieldIsDisplayed(), "Search field is missing");
        Assert.assertTrue(searchScreen.checkIfSearchButtonIsDisplayed(), "Search button is missing");
        Assert.assertTrue(searchScreen.checkIfClearSearchButtonIsDisplayed(), "Clear Search button is missing");
    }

    @Title("Login with incorrect credentials")
    @Description("Verify login is not successful with invalid credentials and toast error message is displayed")
    @Test(groups = {"SMOKE"})
    public void verifyToastErrorMessageWithIncorrectCredentials() {
        loginScreen.enterEmailAddress(randomHelper.getRandomAlphanumericString(7));
        loginScreen.enterPassword(randomHelper.getRandomAlphanumericString(8));
        loginScreen.clickLoginButton();
        Assert.assertTrue(loginScreen.getToastMessage(TOAST_ERROR_MESSAGE), "Toast error message  is not displayed");
    }

}
