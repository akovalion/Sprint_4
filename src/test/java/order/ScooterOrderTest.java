package order;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageobject.MainPage;
import pageobject.OrderPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ScooterOrderTest {
    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;

    private final String name;
    private final String surname;
    private final String address;
    private final String station;
    private final String phone;
    private final String date;
    private final String comment;
    private final boolean clickTopButton;

    public ScooterOrderTest(String name, String surname, String address, String station, String phone, String date, String comment, boolean clickTopButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.comment = comment;
        this.clickTopButton = clickTopButton;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Иван", "Иванов", "ул. Пушкина, д.1", "Черкизовская", "89999999999", "27.04.2025", "Позвонить заранее", true},
                {"Пётр", "Петров", "ул. Лермонтова, д.2", "Черкизовская", "88888888888", "28.04.2025", "Не звонить", false}
        });
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
    }

    @Test
    public void testSuccessfulScooterOrder() {
        if (clickTopButton) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBottomOrderButton();
        }
        orderPage = new OrderPage(driver);
        orderPage.fillFirstForm(name, surname, address, station, phone);
        orderPage.fillSecondForm(date, comment);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
