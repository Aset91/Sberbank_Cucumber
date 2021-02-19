package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import ru.appline.framework.managers.InitManager;

public class Hooks {

    @Before
    public void beforeEach() {
        InitManager.initFramework();
    }

    @After
    public void afterEach(Scenario scenario) {
//        if (scenario.isFailed()) {
//            Allure.addAttachment("failureScreenshot", "image/png", addScreenshot(), "png");
//        }
        InitManager.quitFramework();
    }

//    private static InputStream addScreenshot() {
//        byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
//        return new ByteArrayInputStream(screenshot);
//    }
}
