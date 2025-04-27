package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    private final Duration TIMEOUT = Duration.ofSeconds(10);

    // Элементы страницы
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By topOrderButton = By.xpath("//button[text()='Заказать' and not(contains(@class,'Middle'))]");
    private final By bottomOrderButton = By.xpath("//button[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']");
    private final By questionItems = By.className("accordion__button");
    private final By answerPanels = By.className("accordion__panel");

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

    // Для работы с вопросами

    // Скроллим к нужному вопросу перед кликом
    private void scrollToQuestionItem(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // Кликаем на вопрос по индексу
    public void clickQuestionItem(int index) {
        List<WebElement> questions = driver.findElements(questionItems);
        WebElement question = questions.get(index);
        scrollToQuestionItem(question);
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(question))
                .click();
    }

    // Получаем текст ответа на вопрос по индексу
    public String getAnswerText(int index) {
        List<WebElement> answers = driver.findElements(answerPanels);
        WebElement answer = new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.visibilityOf(answers.get(index)));
        return answer.getText();
    }
}