package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPage {
    private WebDriver driver;
    private By nameInput = By.xpath("//input[@placeholder='* Имя']");
    private By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By stationInput = By.className("select-search__input");
    private By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By nextButton = By.xpath("//button[text()='Далее']");
    private By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By rentalPeriodDropdown = By.className("Dropdown-placeholder");
    private By rentalPeriodOption = By.xpath("//div[contains(text(),'четверо суток')]");
    private By blackColorCheckbox = By.id("black");
    private By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private By finalOrderButton = By.xpath("//button[text()='Заказать']");
    private By confirmYesButton = By.xpath("//button[text()='Да']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillFirstForm(String name, String surname, String address, String station, String phone) {
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surnameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);
        driver.findElement(stationInput).sendKeys(station);
        driver.findElement(stationInput).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(nextButton).click();
    }

    public void fillSecondForm(String date, String comment) {
        driver.findElement(dateInput).sendKeys(date, Keys.ENTER);
        driver.findElement(rentalPeriodDropdown).click();
        driver.findElement(rentalPeriodOption).click();
        driver.findElement(blackColorCheckbox).click();
        driver.findElement(commentInput).sendKeys(comment);
        driver.findElement(finalOrderButton).click();
        driver.findElement(confirmYesButton).click();
    }
}
