package com.lab.tests;

import java.util.List;
import org.openqa.selenium.WebElement;
import static org.testng.Assert.assertTrue;
import com.lab.pages.MainPage;
import org.testng.annotations.Test;

public class MainPageTest extends BaseTest {

    @Test
    public void test1_OpenSite() {
        System.out.println("=== ТЕСТ 1: Открытие сайта ===");
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        System.out.println("Успешно: " + driver.getTitle());
    }

    @Test
    public void test2_CheckTitle() {
        System.out.println("=== ТЕСТ 2: Проверка заголовка ===");

        //arrange
        String title;
        MainPage mainPage = new MainPage(driver);
        // Ожидаемый результат
        // заголовок не должен быть null
        //  не должен быть пустой строкой
        // заголовок должен содержать информацию о сайте

        //act
        mainPage.open();
        title = mainPage.getTitle();
        System.out.println("Заголовок: " + title);

        //assert
        assert title != null : "Заголовок null";
        assert !title.isEmpty() : "Заголовок пустой";
        System.out.println("Заголовок корректен");
    }

    @Test
    public void test3_SimpleSearch() {
        System.out.println("=== ТЕСТ 3: Простой поиск ===");

        // ARRANGE
        MainPage mainPage = new MainPage(driver);
        String expectedWord = "новости";

        // ACT
        mainPage.open();
        mainPage.searchFor(expectedWord);
        List<WebElement> results = mainPage.getSearchResults();

        // ASSERT

        int actualCount = results.size(); // получаем количество найденных элементов поиска
        System.out.println("Найдено результатов: " + actualCount);
    // сравнение ожидаемого результата с фактическим
    // ожидаем: найдено не менее 3 результатов поиска
    // фактически: actualCount
        assertTrue(actualCount >= 3, "Найдено меньше 3 результатов");

    // проверяем содержимое первых трех результатов поиска
        for (int i = 0; i < 3; i++) {
            // фактический:
            // текст очередного элемента результата поиска
            String actualText = results.get(i).getText().toLowerCase();
            // ожидаемый:
            // текст должен содержать ключевое слово поиска "новости"
            assertTrue(
                    actualText.contains(expectedWord),
                    "Результат не содержит слово 'новости'"
            );
        }
    }


    @Test
    public void test4_ClickNews() {
        System.out.println("=== ТЕСТ 4: Переход на новости ===");

        //ARRANGE
        MainPage mainPage = new MainPage(driver);

        //ожидаемый результат
        final String EXPECTED_URL_PART = "news";
        //фактический результат
        String actualUrl;

        //ACT
        mainPage.open();
        mainPage.clickNewsLink();
        actualUrl = driver.getCurrentUrl();

        //ASSERT
        assertTrue(
                actualUrl.contains(EXPECTED_URL_PART),
                "URL страницы не содержит 'news'"
        );
    }

    @Test
    public void test5_CountElements() {
        System.out.println("=== ТЕСТ 5: Подсчет элементов ===");

        //ARRANGE
        MainPage mainPage = new MainPage(driver);

        //ожидаемый результат
        final int a = 3;
        final int b = 3;
        //фактический результат
        int actualMenuCount;
        int actualFooterCount;

        //ACT
        mainPage.open();
        actualMenuCount = mainPage.getMenuItemsCount();
        actualFooterCount = mainPage.getFooterLinksCount();

        //ASSERT
        assertTrue(
                actualMenuCount >= a,
                "В меню меньше " + a + " элементов"
        );

        assertTrue(
                actualFooterCount >= b,
                "В футере меньше " + b + " элементов"
        );
    }
}