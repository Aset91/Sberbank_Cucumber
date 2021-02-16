package ru.appline.framework.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestPropManager {

    /**
     * Переменная для хранения данных, считанных из файла properties и переданных пользователем
     * переменная для хранения пользовательских properties
     */
    private final Properties properties = new Properties();

    /**
     * Переменная для хранения объекта TestPropManager
     */
    private static TestPropManager INSTANCE = null;

    /**
     * Private конструктор. Происходит загрузка содержимого файла application.properties в переменную
     */
    private TestPropManager(){
        loadApplicationProperties();
        loadCustomProperties();
    }
    /**
     * Метод ленивой инициализации TestPropManager
     */
    public static TestPropManager getTestPropManager() {
        if(INSTANCE == null) {
            INSTANCE = new TestPropManager();
        }
        return INSTANCE;
    }
    /**
     * Метод подгружает содержисое файла application.properties в переменную
     * либо из файла, переданного пользователем через настройку - DropFile
     */
    private void loadApplicationProperties(){

        try {
            properties.load(new FileInputStream(
                    new File("src/main/resources/" +
                            System.getProperty("env", "application") + ".properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Метод заменяет значение, содержащееся в переменной
     * заменяет на те, значения, что передал пользователь через maven '-D{name.key}={value.key}'
     * замена будет происходить только в том случае, если пользователь передаст совпадающий key из application.properties
     */
    private void loadCustomProperties() {
        properties.forEach((key, value) -> System.getProperties()
        .forEach((customUserKey, customUserValue) -> {
            if(key.toString().equals(customUserKey.toString()) &&
            !value.toString().equals(customUserValue.toString())) {
                properties.setProperty(key.toString(), customUserValue.toString());
            }
        }));

    }
    /**
     * Метод возвращает либо значение, записанное в ключе в переменной,
     * либо defaultValue при отсутствии ключа в переменной
     * @param key - ключ, значение которого хотим получить
     * @param defaultValue - значение по умолчанию, которое хотите получить, если отсутсвует ключ
     * @return String - возвращает системное свойство либо переданное default значение
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    /**
     * Метод возвращает значение, записанное в ключе в переменной, если его нет, вернет null
     * @param key - ключ, значение которого хотим получить
     * @return String -строка со значением ключа
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
