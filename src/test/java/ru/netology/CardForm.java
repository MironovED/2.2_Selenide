package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardForm {

    @BeforeEach
    void setUp(){
        open("http://0.0.0.0:9999/");
    }

    @Test
    void shouldCardForm(){
        $("[data-test-id='city'] input").val("Тверь");
        $("[class='menu-item__control']").click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").val("15.04.2022");
        $("[data-test-id='name'] .input__control").val("Иванова-Сидорова Анна-Мария");
        $("[data-test-id='phone'] .input__control").val("+79008007060");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

}
