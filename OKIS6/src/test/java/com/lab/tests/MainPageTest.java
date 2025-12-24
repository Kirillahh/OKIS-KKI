package com.lab.tests;

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
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        String title = mainPage.getTitle();
        System.out.println("Заголовок: " + title);
        // Простая проверка через assert
        assert title != null : "Заголовок null";
        assert !title.isEmpty() : "Заголовок пустой";
        System.out.println("Заголовок корректен");
    }

    @Test
    public void test3_SimpleSearch() {
        System.out.println("=== ТЕСТ 3: Простой поиск ===");
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.searchFor("новости");
        System.out.println("Поиск выполнен, URL: " + driver.getCurrentUrl());
    }

    @Test
    public void test4_ClickNews() {
        System.out.println("=== ТЕСТ 4: Переход на новости ===");
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickNewsLink();
        System.out.println("На странице новостей: " + driver.getCurrentUrl());
    }

    @Test
    public void test5_CountElements() {
        System.out.println("=== ТЕСТ 5: Подсчет элементов ===");
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        int menuCount = mainPage.getMenuItemsCount();
        int footerCount = mainPage.getFooterLinksCount();

        System.out.println("Ссылок в меню: " + menuCount);
        System.out.println("Ссылок в футере: " + footerCount);

        // Просто логируем, не падаем если 0
        System.out.println("Элементы подсчитаны");
    }
}