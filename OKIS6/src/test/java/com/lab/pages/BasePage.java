package com.lab.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // Метод для закрытия всплывающих окон и рекламы
    public void closeAllPopups() {
        try {
            Thread.sleep(1000); // Даем время на появление рекламы

            // Список селекторов для поиска рекламы и попапов
            String[] popupSelectors = {
                    ".modal", ".popup", ".overlay", "[class*='advert']",
                    ".ad-modal", ".fc-consent-root", "#bx-popup",
                    ".cookie-notification", "[id*='popup']", "[class*='cookie']",
                    ".subscription-popup", ".newsletter-popup", ".age-gate"
            };

            // Пробуем закрыть каждый тип попапа
            for (String selector : popupSelectors) {
                try {
                    List<WebElement> popups = driver.findElements(By.cssSelector(selector));
                    for (WebElement popup : popups) {
                        if (popup.isDisplayed()) {
                            System.out.println("Найдено всплывающее окно: " + selector);

                            // Пытаемся найти кнопку закрытия
                            try {
                                WebElement closeBtn = popup.findElement(By.cssSelector(
                                        ".close, .modal-close, [aria-label='Close'], " +
                                                "[class*='close'], [class*='dismiss'], " +
                                                "[data-dismiss='modal'], .btn-close"
                                ));
                                closeBtn.click();
                                System.out.println("Окно закрыто через кнопку");
                                Thread.sleep(500);
                            } catch (Exception e) {
                                // Если нет кнопки, пробуем ESC или клик вне окна
                                try {
                                    popup.sendKeys(Keys.ESCAPE);
                                    System.out.println("Окно закрыто по ESC");
                                } catch (Exception ex) {
                                    // Клик вне окна
                                    js.executeScript("arguments[0].style.display='none'", popup);
                                    System.out.println("Окно скрыто через JavaScript");
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    // Игнорируем ошибки для этого селектора
                }
            }
        } catch (Exception e) {
            System.out.println("Ошибка при закрытии попапов: " + e.getMessage());
        }
    }

    // Метод для скролла (часто помогает убрать рекламу)
    public void scrollToHideAds() {
        try {
            // Скроллим вниз и вверх
            js.executeScript("window.scrollBy(0, 200);");
            Thread.sleep(300);
            js.executeScript("window.scrollBy(0, -100);");
            Thread.sleep(300);
        } catch (Exception e) {
            // Игнорируем ошибки скролла
        }
    }

    // Улучшенный метод ожидания загрузки страницы
    public void waitForPageLoad() {
        try {
            // Ждем готовности DOM
            wait.until(webDriver ->
                    js.executeScript("return document.readyState").equals("complete"));

            // Закрываем возможные попапы
            closeAllPopups();

            // Делаем скролл для скрытия рекламы
            scrollToHideAds();

            // Дополнительная пауза
            Thread.sleep(1000);

        } catch (Exception e) {
            System.out.println("Ошибка при ожидании загрузки: " + e.getMessage());
        }
    }

    // Метод для безопасного клика (с обработкой рекламы)
    public void safeClick(WebElement element) {
        try {
            closeAllPopups(); // Сначала закрываем рекламу
            element.click();
            waitForPageLoad(); // Ждем загрузки после клика
        } catch (Exception e) {
            System.out.println("Ошибка при клике: " + e.getMessage());
            throw e;
        }
    }
}