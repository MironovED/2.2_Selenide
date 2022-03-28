package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardForm {

    public String generateDate(int Days){
        return LocalDate.now().plusDays(Days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @BeforeEach
    void setUp() {
        open("http://0.0.0.0:9999/");

    }

    @Test
    void checkForValidValues() {
        String planningDate = generateDate(3);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val("Иванова-Сидорова Анна-Мария");
        $("[data-test-id='phone'] .input__control").val("+79008007060");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
    void shouldGetErrorIfEmptyFields() {
        $(".button").click();
        $("[data-test-id='city'].input_invalid").shouldHave(Condition.text("Поле обязательно для заполнения"));

    }

    @Test
    void shouldGetErrorIfInvalidFieldCity() {
        String planningDate = generateDate(3);

        $("[data-test-id='city'] input").val("Сеул");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val("Иванова-Сидорова Анна-Мария");
        $("[data-test-id='phone'] .input__control").val("+79008007060");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='city'].input_invalid").shouldHave(Condition.text("Доставка в выбранный город недоступна"));

    }

    @Test
    void shouldGetErrorIfDateInvalid() {
        String planningDate = generateDate(2);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val("Иванова-Сидорова Анна-Мария");
        $("[data-test-id='phone'] .input__control").val("+79008007060");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id=date] .input_invalid").shouldHave(Condition.text("Заказ на выбранную дату невозможен"));

    }

    @Test
    void shouldPassedIfDatePlus4DaysFromCurrentDay() {
        String planningDate = generateDate(4);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val("Иванова-Сидорова Анна-Мария");
        $("[data-test-id='phone'] .input__control").val("+79008007060");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
    void shouldGetErrorIfFieldNameEmpty() {
        String planningDate = generateDate(3);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val("");
        $("[data-test-id='phone'] .input__control").val("+79008007060");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid").shouldHave(Condition.text("Поле обязательно для заполнения"));

    }

    @Test
    void shouldGetErrorIfInvalidValuesFieldName1() {
        String planningDate = generateDate(3);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val("+79008007060");
        $("[data-test-id='phone'] .input__control").val("+79008007060");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldGetErrorIfInvalidValuesFieldName2() {
        String planningDate = generateDate(3);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val("Alina_Fomina");
        $("[data-test-id='phone'] .input__control").val("+79008007060");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldGetErrorIfFieldPhoneEmpty() {
        String planningDate = generateDate(3);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val("Иван Иванов");
        $("[data-test-id='phone'] .input__control").val("");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid").shouldHave(Condition.text("Поле обязательно для заполнения"));

    }
    @Test
    void shouldGetErrorIfInvalidValuesFieldPhone() {
        String planningDate = generateDate(3);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val("Иван Иванов");
        $("[data-test-id='phone'] .input__control").val("8(900)800-70-60");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid").shouldHave(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldGetErrorIfUncheckedCheckbox() {
        String planningDate = generateDate(3);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val("Иванова-Сидорова Анна-Мария");
        $("[data-test-id='phone'] .input__control").val("+79008007060");
        $(".button").click();
        $("[data-test-id='agreement'].input_invalid").shouldHave(visible);

    }

    @Test
    void shouldCheckDatePopupWithDateInApplication() {
        String planningDate = generateDate(3);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id='name'] .input__control").val("Иванова-Сидорова Анна-Мария");
        $("[data-test-id='phone'] .input__control").val("+79008007060");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[class='notification__content']").shouldHave(text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));

    }

}
