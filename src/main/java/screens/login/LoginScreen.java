package screens.login;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import utils.MobileBrowserWait;

import static screens.login.LoginScreenLocators.*;
import static utils.MobileActions.*;

@Slf4j
public class LoginScreen extends MobileBrowserWait {

    public boolean checkIfEmailFieldIsDisplayed() {
        return driver.findElement(EMAIL_FIELD_ID).isDisplayed();
    }

    public boolean checkIfPasswordFieldIsDisplayed() {
        return driver.findElement(PASSWORD_FIELD_ID).isDisplayed();
    }

    public boolean checkIfLoginButtonIsDisplayed() {
        return driver.findElement(LOGIN_BUTTON_ID).isDisplayed();
    }

    @Step("Entering email address")
    public void enterEmailAddress(String userName) {
        log.info("Entering email address {}", userName);
        waitUntilElementIsPresent(EMAIL_FIELD_ID);
        clearAndfillInFieldWith(EMAIL_FIELD_ID, userName);
    }

    @Step("Entering password")
    public void enterPassword(String password) {
        log.info("Entering password");
        waitUntilElementIsPresent(PASSWORD_FIELD_ID);
        clearAndfillInFieldWith(PASSWORD_FIELD_ID, password);
    }


    @Step("Clicking on login button")
    public void clickLoginButton() {
        log.info("Clicking on Login button");
        waitUntilElementClickable(LOGIN_BUTTON_ID);
        clickElementBy(LOGIN_BUTTON_ID);
    }

    @Step("Checking if toast error message is displayed or not")
    public boolean getToastMessage(String message) {
        try {
            log.info("Checking if toast error message is displayed or not");
            Thread.sleep(500);
            String xmlFormat = driver.getPageSource();
            if (xmlFormat.contains(message)) {
                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getEmailFieldPlaceholderText() {
        waitUntilElementIsPresent(EMAIL_FIELD_ID);
        return getTextFromElement(EMAIL_FIELD_ID);
    }

    public String getPasswordFieldPlaceholderText() {
        waitUntilElementIsPresent(PASSWORD_FIELD_ID);
        return getTextFromElement(PASSWORD_FIELD_ID);
    }


}
