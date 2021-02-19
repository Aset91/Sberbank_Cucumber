package ru.appline.framework.utils;

import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestStepFinished;
import io.qameta.allure.Allure;
import io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.appline.framework.managers.DriverManager;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class MyCucumberListener extends AllureCucumber5Jvm {


    private static InputStream addScreenshot() {
        byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        return new ByteArrayInputStream(screenshot);
    }


    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepFinished.class, testStepFinished -> {
                    if (testStepFinished.getResult().getError() != null) {
                        Allure.addAttachment("failureScreenshot", "image/png", addScreenshot(), "png");
                    }
                }
        );
        super.setEventPublisher(publisher);
    }
}
