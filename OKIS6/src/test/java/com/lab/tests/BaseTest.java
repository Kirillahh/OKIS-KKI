package com.lab.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.out.println("=== НАСТРОЙКА ДРАЙВЕРА ===");
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // УПРОЩЕННЫЕ НАСТРОЙКИ - убираем всё лишнее
        options.addArguments("--start-maximized");
        // options.addArguments("--headless"); // ЗАКОММЕНТИРОВАТЬ для отладки!

        driver = new ChromeDriver(options);

        // УВЕЛИЧИВАЕМ таймауты
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        System.out.println("Драйвер готов к работе");
    }


    @AfterMethod
    public void tearDown() {
        System.out.println("=== ЗАКРЫТИЕ ДРАЙВЕРА ===");

        // ПАУЗА чтобы увидеть результат
        try {
            System.out.println("Жду 2 секунды перед закрытием...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (driver != null) {
            try {
                driver.quit();
                System.out.println("Драйвер закрыт ✓");
            } catch (Exception e) {
                System.out.println("Ошибка закрытия: " + e.getMessage());
            }
        }
        System.out.println("=================================");
    }
    }
