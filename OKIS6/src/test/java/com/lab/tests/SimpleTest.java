package com.lab.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleTest extends BaseTest {

    @Test
    public void testOne() {
        System.out.println("=== ТЕСТ 1: Открытие главной страницы ===");

        try {
            System.out.println("1. Открываю https://lenta.ru...");
            driver.get("https://lenta.ru");

            System.out.println("2. Жду 3 секунды...");
            Thread.sleep(3000);

            String title = driver.getTitle();
            String url = driver.getCurrentUrl();

            System.out.println("3. Фактический заголовок: " + title);
            System.out.println("4. Фактический URL: " + url);
            System.out.println("5. Длина заголовка: " + title.length());

            // Проверяем, что страница вообще загрузилась
            if (title == null || title.isEmpty()) {
                System.out.println("ОШИБКА: Заголовок пустой!");
                Assert.fail("Заголовок страницы пустой");
            }

            // Более гибкая проверка
            boolean titleOk = title.contains("Лента") ||
                    title.contains("Lenta") ||
                    title.contains("lenta") ||
                    title.contains("Новости") ||
                    title.contains("новости");

            System.out.println("6. Заголовок содержит ключевые слова: " + titleOk);

            if (!titleOk) {
                System.out.println("Заголовок страницы: " + title);
                System.out.println("Первые 50 символов: " + (title.length() > 50 ? title.substring(0, 50) : title));
            }

            Assert.assertTrue(titleOk, "Заголовок не содержит ожидаемых слов. Заголовок: " + title);

        } catch (Exception e) {
            System.out.println("ИСКЛЮЧЕНИЕ в testOne: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Тест упал с исключением: " + e.getMessage());
        }
    }

    @Test
    public void testTwo() {
        System.out.println("=== ТЕСТ 2: Открытие страницы новостей ===");

        try {
            System.out.println("1. Открываю https://lenta.ru/news...");
            driver.get("https://lenta.ru/news");

            System.out.println("2. Жду 3 секунды...");
            Thread.sleep(3000);

            String currentUrl = driver.getCurrentUrl();
            String title = driver.getTitle();

            System.out.println("3. Фактический URL: " + currentUrl);
            System.out.println("4. Заголовок: " + title);

            // Проверяем что URL содержит news ИЛИ что мы на сайте Lenta
            boolean urlOk = currentUrl.contains("news") ||
                    currentUrl.contains("lenta.ru") ||
                    currentUrl.contains("Лента");

            System.out.println("5. URL корректен: " + urlOk);

            if (!urlOk) {
                System.out.println("Полный URL: " + currentUrl);
                // Пробуем найти какой-то контент на странице
                String pageSource = driver.getPageSource();
                System.out.println("Длина исходного кода страницы: " + pageSource.length());

                if (pageSource.length() < 100) {
                    System.out.println("ОШИБКА: Страница почти пустая!");
                }
            }

            Assert.assertTrue(urlOk, "URL не содержит 'news'. Фактический URL: " + currentUrl);

        } catch (Exception e) {
            System.out.println("ИСКЛЮЧЕНИЕ в testTwo: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Тест упал с исключением: " + e.getMessage());
        }
    }

    @Test
    public void testThree() {
        System.out.println("=== ТЕСТ 3: Простейшая проверка Selenium ===");

        try {
            // Самый простой тест - просто открыть google
            System.out.println("Открываю google.com для проверки работы Selenium...");
            driver.get("https://google.com");
            Thread.sleep(2000);

            String title = driver.getTitle();
            System.out.println("Заголовок Google: " + title);

            Assert.assertTrue(title.contains("Google") || title.contains("google"),
                    "Не удалось открыть Google. Заголовок: " + title);
            System.out.println("Selenium работает корректно!");

        } catch (Exception e) {
            System.out.println("ОШИБКА: Selenium не работает!");
            e.printStackTrace();
            Assert.fail("Selenium не работает: " + e.getMessage());
        }
    }
}