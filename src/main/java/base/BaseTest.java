package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static AppiumDriverLocalService service;
    public static AppiumDriver<MobileElement> driver;


    public static AppiumDriverLocalService startAppiumServer() {

        boolean flag = checkIfServerIsRunnning(4723);
        if (!flag) {
            service = AppiumDriverLocalService.buildDefaultService();
            service.start();
        }
        return service;

    }

    public static boolean checkIfServerIsRunnning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);

            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }


    public static AppiumDriver<MobileElement> setup() {

        try {
            String userCurrDir = System.getProperty("user.dir");
            String appApkFilePath = "";
            String emulatorName = "";
            String apkFilePath = userCurrDir + appApkFilePath;
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, emulatorName);
            caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            caps.setCapability(MobileCapabilityType.FULL_RESET, true);
            caps.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
            caps.setCapability(MobileCapabilityType.APP, apkFilePath);
            driver = new AppiumDriver<>(url, caps);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        } catch (Exception exp) {
            System.out.println("Cause is :" + exp.getCause());
            System.out.println("Message is :" + exp.getMessage());
            exp.printStackTrace();
        }
        return driver;
    }

    public static void stopAppiumServer() {
        service.stop();
        System.out.println("Server is stopped successfully");

    }


}


