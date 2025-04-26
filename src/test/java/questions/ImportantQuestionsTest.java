package questions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageobject.MainPage;

import static org.junit.Assert.assertEquals;

public class ImportantQuestionsTest {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
    }

    @Test
    public void testQuestionOpensAnswer() {
        mainPage.clickQuestionItem();
        String actualAnswer = mainPage.getAnswerText();
        String expectedAnswer = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
        assertEquals("Текст ответа не совпадает с ожидаемым!", expectedAnswer, actualAnswer);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}