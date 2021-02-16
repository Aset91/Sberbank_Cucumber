//package ru.appline.framework.utils;
//
////import com.sun.xml.internal.ws.developer.StreamingAttachment;
//import io.qameta.allure.Attachment;
//import io.qameta.allure.junit4.AllureJunit4;
//import org.junit.runner.notification.Failure;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import ru.appline.framework.managers.DriverManager;
//
//public class MyAllureListener extends AllureJunit4 {
//
//    @Attachment(value = "screenshot", type = "image/png")
//    public static byte[] addScreenshot() {
//        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
//    }
//    @Override
//    public void testFailure(final Failure failure) {
//        addScreenshot();
//        super.testFailure(failure);
//    }
//
//
//
//}
