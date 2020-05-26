package basetest;


import base.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


@Slf4j
public class BaseTestAndroid extends BaseTest {

    @BeforeTest(alwaysRun = true)
    public static void setUpBrowser() {
        startAppiumServer();
        setup();
    }

    @AfterTest(alwaysRun = true)
    public static void tearDown() {
        log.info("Closing Browser");
        driver.quit();
        stopAppiumServer();
    }
}