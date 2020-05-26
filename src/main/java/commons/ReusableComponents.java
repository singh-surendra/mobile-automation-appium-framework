package commons;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.qatools.allure.annotations.Step;
import utils.MobileBrowserWait;

import static commons.ReusableComponentsLocators.*;
import static utils.MobileActions.getTextFromElement;

@Slf4j
public class ReusableComponents extends MobileBrowserWait {

    @Step("Getting screen title")
    public String getScreenTitle() {
        log.info("Getting screen title");
        waitUntilElementIsPresent(PAGE_TITLE_CLASS_NAME);
        return getTextFromElement(PAGE_TITLE_CLASS_NAME);
    }



}

