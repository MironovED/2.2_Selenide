package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardForm {

    @BeforeEach
    void setUp() {
        open("http://0.0.0.0:9999/");

    }

    @Test
    void checkForValidValues() {
        LocalDate date = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateFormatted = date.format(formatter);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(dateFormatted);
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
        LocalDate date = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateFormatted = date.format(formatter);

        $("[data-test-id='city'] input").val("Сеул");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(dateFormatted);
        $("[data-test-id='name'] .input__control").val("Иванова-Сидорова Анна-Мария");
        $("[data-test-id='phone'] .input__control").val("+79008007060");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='city'].input_invalid").shouldHave(Condition.text("Доставка в выбранный город недоступна"));

    }

    @Test
    void shouldGetErrorIfDateInvalid() {
        LocalDate date = LocalDate.now().plusDays(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateFormatted = date.format(formatter);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(dateFormatted);
        $("[data-test-id='name'] .input__control").val("Иванова-Сидорова Анна-Мария");
        $("[data-test-id='phone'] .input__control").val("+79008007060");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id=date] .input_invalid").shouldHave(Condition.text("Заказ на выбранную дату невозможен"));

    }

    @Test
    void shouldPassedIfDatePlus4DaysFromCurrentDay() {
        LocalDate date = LocalDate.now().plusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateFormatted = date.format(formatter);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(dateFormatted);
        $("[data-test-id='name'] .input__control").val("Иванова-Сидорова Анна-Мария");
        $("[data-test-id='phone'] .input__control").val("+79008007060");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
    void shouldGetErrorIfFieldNameEmpty() {
        LocalDate date = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateFormatted = date.format(formatter);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(dateFormatted);
        $("[data-test-id='name'] .input__control").val("");
        $("[data-test-id='phone'] .input__control").val("+79008007060");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid").shouldHave(Condition.text("Поле обязательно для заполнения"));

    }

    @Test
    void shouldGetErrorIfInvalidValuesFieldName1() {
        LocalDate date = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateFormatted = date.format(formatter);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(dateFormatted);
        $("[data-test-id='name'] .input__control").val("+79008007060");
        $("[data-test-id='phone'] .input__control").val("+79008007060");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldGetErrorIfInvalidValuesFieldName2() {
        LocalDate date = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateFormatted = date.format(formatter);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(dateFormatted);
        $("[data-test-id='name'] .input__control").val("Alina_Fomina");
        $("[data-test-id='phone'] .input__control").val("+79008007060");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid").shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldGetErrorIfFieldPhoneEmpty() {
        LocalDate date = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateFormatted = date.format(formatter);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(dateFormatted);
        $("[data-test-id='name'] .input__control").val("Иван Иванов");
        $("[data-test-id='phone'] .input__control").val("");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid").shouldHave(Condition.text("Поле обязательно для заполнения"));

    }
    @Test
    void shouldGetErrorIfInvalidValuesFieldPhone() {
        LocalDate date = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateFormatted = date.format(formatter);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(dateFormatted);
        $("[data-test-id='name'] .input__control").val("Иван Иванов");
        $("[data-test-id='phone'] .input__control").val("8(900)800-70-60");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid").shouldHave(Condition.text(" указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldGetErrorIfUncheckedCheckbox() {
        LocalDate date = LocalDate.now().plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String dateFormatted = date.format(formatter);

        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").sendKeys(dateFormatted);
        $("[data-test-id='name'] .input__control").val("Иванова-Сидорова Анна-Мария");
        $("[data-test-id='phone'] .input__control").val("+79008007060");
        $(".button").click();
        $("[data-test-id='agreement'].input_invalid").shouldHave(visible);

    }

}
