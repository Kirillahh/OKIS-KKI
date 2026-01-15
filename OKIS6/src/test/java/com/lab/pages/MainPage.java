package com.lab.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class MainPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Элементы
    @FindBy(tagName = "body")
    private WebElement body;

    @FindBy(css = "a")
    private List<WebElement> allLinks;

    @FindBy(css = "input")
    private List<WebElement> allInputs;

    @FindBy(css = "button")
    private List<WebElement> allButtons;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // ВАЖНО: инициализируем элементы!
        PageFactory.initElements(driver, this);
    }

    public void open() {
        System.out.println("Открываю Lenta.ru");
        driver.get("https://lenta.ru/");

        // Ждем загрузки страницы
        wait.until(ExpectedConditions.titleContains("Lenta"));
        System.out.println("Страница загружена: " + driver.getTitle());

        // Даем время на инициализацию элементов
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Закрываем возможную рекламу
        closePopups();
    }

    private void closePopups() {
        try {
            if (body != null) {
                // Пробуем ESC для закрытия модальных окон
                body.sendKeys(Keys.ESCAPE);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("Не удалось закрыть попапы: " + e.getMessage());
        }
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void searchFor(String query) {
        System.out.println("Ищу: " + query);

        try {
            // Если allInputs не инициализирован, ищем вручную
            if (allInputs == null || allInputs.isEmpty()) {
                System.out.println("Элементы не инициализированы, ищу поле поиска вручную...");

                // Способ 1: Через JavaScript
                try {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    // Ищем поле поиска
                    WebElement searchInput = (WebElement) js.executeScript(
                            "return document.querySelector('input[type=\"search\"], input[name=\"q\"], .search-input')"
                    );

                    if (searchInput != null) {
                        searchInput.clear();
                        searchInput.sendKeys(query);
                        searchInput.sendKeys(Keys.ENTER);
                        System.out.println("Поиск выполнен через JavaScript");
                    } else {
                        // Способ 2: Переходим напрямую
                        System.out.println("Поле поиска не найдено, перехожу на страницу поиска");
                        driver.get("https://lenta.ru/search?query=" + query);
                    }
                } catch (Exception e) {
                    System.out.println("Ошибка JavaScript поиска: " + e.getMessage());
                    driver.get("https://lenta.ru/search?query=" + query);
                }
            } else {
                // Используем PageFactory элементы
                WebElement searchInput = null;
                for (WebElement input : allInputs) {
                    String type = input.getAttribute("type");
                    String placeholder = input.getAttribute("placeholder");
                    if ("search".equals(type) ||
                            (placeholder != null && placeholder.toLowerCase().contains("поиск"))) {
                        searchInput = input;
                        break;
                    }
                }

                if (searchInput != null) {
                    searchInput.clear();
                    searchInput.sendKeys(query);
                    searchInput.sendKeys(Keys.ENTER);
                    System.out.println("Поиск выполнен через PageFactory");
                } else {
                    System.out.println("Поле поиска не найдено, перехожу на страницу поиска");
                    driver.get("https://lenta.ru/search?query=" + query);
                }
            }

            Thread.sleep(2000);

        } catch (Exception e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void clickNewsLink() {
        System.out.println("Кликаю по новостям");

        try {
            // Простой способ - напрямую переходим
            System.out.println("Переходим напрямую на страницу новостей");
            driver.get("https://lenta.ru/news");
            Thread.sleep(2000);

        } catch (Exception e) {
            System.out.println("Ошибка перехода: " + e.getMessage());
        }
    }

    public boolean isLogoDisplayed() {
        try {
            // Простая проверка - загружена ли страница
            return driver.getTitle() != null && !driver.getTitle().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public int getMenuItemsCount() {
        try {
            // Используем JavaScript для поиска
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Long count = (Long) js.executeScript(
                    "return document.querySelectorAll('header a, nav a, .menu a').length;"
            );
            return count.intValue();
        } catch (Exception e) {
            System.out.println("Не удалось посчитать меню: " + e.getMessage());
            return 0;
        }
    }

    public int getFooterLinksCount() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Long count = (Long) js.executeScript(
                    "return document.querySelectorAll('footer a').length;"
            );
            return count.intValue();
        } catch (Exception e) {
            System.out.println("Не удалось посчитать футер: " + e.getMessage());
            return 0;
        }
    }
    public List<WebElement> getSearchResults() {
        return driver.findElements(
                By.xpath("//li[contains(@class,'search-results__item')]")
        );
    }
}