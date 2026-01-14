package com.lab.tests;

import org.testng.annotations.Test;

public class NewsPageTest extends BaseTest {

    @Test
    public void testNewsPageOpen() {
        System.out.println("=== ТЕСТ 1: Открытие страницы новостей ===");
        driver.get("https://lenta.ru/news");
        System.out.println("Заголовок: " + driver.getTitle());
        assert driver.getCurrentUrl().contains("news");
    }

    @Test
    public void testPageTitleNotEmpty() {
        System.out.println("=== ТЕСТ 2: Заголовок не пустой ===");
        driver.get("https://lenta.ru/news");
        String title = driver.getTitle();
        System.out.println("Заголовок: " + title);
        assert title != null && !title.isEmpty();
    }

    @Test
    public void testNavigation() {
        System.out.println("=== ТЕСТ 3: Навигация ===");
        driver.get("https://lenta.ru/");
        String url1 = driver.getCurrentUrl();

        driver.get("https://lenta.ru/news");
        String url2 = driver.getCurrentUrl();

        System.out.println("С главной на новости: " + url1 + " -> " + url2);
        assert !url1.equals(url2);
    }

    @Test
    public void testBackButton() {
        System.out.println("=== ТЕСТ 4: Кнопка назад ===");
        driver.get("https://lenta.ru/");
        String mainPage = driver.getCurrentUrl();

        driver.get("https://lenta.ru/news");
        driver.navigate().back();

        String afterBack = driver.getCurrentUrl();
        System.out.println("После назад: " + afterBack);
        assert afterBack.equals(mainPage);
    }

    @Test
    public void testRefresh() {
        System.out.println("=== ТЕСТ 5: Обновление страницы ===");
        driver.get("https://lenta.ru/news");
        String beforeRefresh = driver.getTitle();

        driver.navigate().refresh();
        String afterRefresh = driver.getTitle();

        System.out.println("До обновления: " + beforeRefresh);
        System.out.println("После обновления: " + afterRefresh);
        assert beforeRefresh.equals(afterRefresh);
    }

    @Test
    public void testUrlContainsNews() {
        System.out.println("=== ТЕСТ 6: URL содержит 'news' ===");
        driver.get("https://lenta.ru/news");
        assert driver.getCurrentUrl().contains("news");
    }

    @Test
    public void testPageLoads() {
        System.out.println("=== ТЕСТ 7: Страница загружается ===");
        driver.get("https://lenta.ru/news");
        String source = driver.getPageSource();
        assert source.length() > 1000; // Страница не пустая
    }

    @Test
    public void testNegativeCase() {
        System.out.println("=== ТЕСТ 8: Негативный сценарий ===");
        // Пробуем перейти на несуществующую страницу
        driver.get("https://lenta.ru/not-exist-page");
        // Должна быть ошибка 404 или редирект
        System.out.println("Текущий URL: " + driver.getCurrentUrl());
        assert !driver.getCurrentUrl().contains("not-exist-page");
    }
}