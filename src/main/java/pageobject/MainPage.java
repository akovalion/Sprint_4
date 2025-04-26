package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private final Duration TIMEOUT = Duration.ofSeconds(10);

    // Элементы страницы
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By topOrderButton = By.xpath("//button[text()='Заказать' and not(contains(@class,'Middle'))]");
    private final By bottomOrderButton = By.xpath("//button[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']");
    // Локатор вопроса по тексту
    private final By questionItem = By.xpath("//div[@data-accordion-component='AccordionItemButton' and contains(text(),'Сколько это стоит?')]");
    // Локатор ответа к вопросу (относительно структуры)
    private final By questionAnswer = By.xpath("//div[contains(@class,'accordion__item')][.//div[contains(text(),'Сколько это стоит?')]]//div[@data-accordion-component='AccordionItemPanel']/p");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void acceptCookies() {
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(cookieButton))
                .click();
    }

    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }

    public void clickBottomOrderButton() {
        driver.findElement(bottomOrderButton).click();
    }

    public void scrollToQuestionItem() {
        WebElement element = driver.findElement(questionItem);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickQuestionItem() {
        scrollToQuestionItem();
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(questionItem))
                .click();
    }

    public String getAnswerText() {
        return driver.findElement(questionAnswer).getText();
    }
}