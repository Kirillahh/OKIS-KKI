package com.lab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class NewsPage {
    private WebDriver driver;

    public NewsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Простые методы для демонстрации
    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isNewsPage() {
        return driver.getCurrentUrl().contains("/news");
    }

    public void openRandomNews() {
        // Просто переходим на другую новость
        driver.get("https://lenta.ru/news/2024/12/23/something");
    }

    public void goBack() {
        driver.navigate().back();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }
}