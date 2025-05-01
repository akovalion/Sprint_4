package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;
    private final Duration TIMEOUT = Duration.ofSeconds(10);

    // Элементы страницы
    private final By nameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By stationInput = By.className("select-search__input");
    private final By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");
    private final By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodDropdown = By.className("Dropdown-placeholder");
    private final By rentalPeriodOption = By.xpath("//div[contains(text(),'четверо суток')]");
    private final By blackColorCheckbox = By.id("black");
    private final By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final By submitOrderButton = By.xpath("//button[contains(@class,'Button_Middle__1CSJM') and text()='Заказать']"); // исправленный локатор
    private final By confirmYesButton = By.xpath("//button[text()='Да']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Заполнение первой части формы заказа
    public void fillFirstForm(String name, String surname, String address, String station, String phone) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(stationInput).sendKeys(station);
        driver.findElement(stationInput).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(nextButton).click();
    }

    // Заполнение второй части формы заказа
    public void fillSecondForm(String date, String comment) {
        driver.findElement(dateInput).sendKeys(date, Keys.ENTER);
        driver.findElement(rentalPeriodDropdown).click();
        driver.findElement(rentalPeriodOption).click();
        driver.findElement(blackColorCheckbox).click();
        driver.findElement(commentInput).sendKeys(comment);

        // Кликаем по правильной кнопке "Заказать"
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(submitOrderButton))
                .click();

        // После нажатия "Заказать" - ждем появления кнопки "Да" и кликаем
        new WebDriverWait(driver, TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(confirmYesButton))
                .click();
    }

    // Метод для проверки успешности оформления заказа
    public boolean isOrderSuccessful() {
        try {
            return new WebDriverWait(driver, TIMEOUT)
                    .until(ExpectedConditions.urlContains("/order/"));
        } catch (TimeoutException e) {
            return false; // Если не изменился URL — считаем, что заказ не оформлен
        }
    }
}

