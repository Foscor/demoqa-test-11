package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {
  @BeforeAll
  static void beforeAll() {
    Configuration.baseUrl = "https://demoqa.com";
    Configuration.browserSize = "2560x1440";
  }


  @Test
  void successFillTest() throws InterruptedException {
    open("/automation-practice-form");
    $(".main-header").shouldHave(text("Practice Form"));

    $("#firstName").setValue("Ivan");
    $("#lastName").setValue("Isaev");
    $("#userEmail").setValue("ivan@jirafiki.eu");

    executeJavaScript("arguments[0].click()", $(By.id("gender-radio-1")));

    $("#userNumber").setValue("1234567890");
    //$("#dateOfBirthInput").clear();

    $("#dateOfBirthInput").click();
    $(".react-datepicker__month-select").selectOptionByValue("1");
    $(".react-datepicker__year-select").selectOptionByValue("1990");
    $(".react-datepicker__day--007").click();


    $("#subjectsInput").setValue("Arts").pressEnter();
    $("#subjectsInput").setValue("Civics").pressEnter();


    executeJavaScript("arguments[0].click()", $(By.id("hobbies-checkbox-2")));
    executeJavaScript("arguments[0].click()", $(By.id("hobbies-checkbox-3")));
    $("#uploadPicture").uploadFile(new File("src/test/resources/picture.jpg"));

    $("#currentAddress").setValue("Novosibirsk");

    $("#react-select-3-input").setValue("Haryana").pressEnter();
    $("#react-select-4-input").setValue("Panipat").pressEnter();
    $("#submit").click();

    $("#example-modal-sizes-title-lg").shouldHave((textCaseSensitive("Thanks for submitting the form")));
    $(".table-responsive").shouldHave(
        textCaseSensitive("Student Name"),    textCaseSensitive("Ivan Isaev"),
        textCaseSensitive("Student Email"),   textCaseSensitive("ivan@jirafiki.eu"),
        textCaseSensitive("Gender"),          textCaseSensitive("Male"),
        textCaseSensitive("Mobile"),          textCaseSensitive("1234567890"),
        textCaseSensitive("Date of Birth"),   textCaseSensitive("07 February,1990"),
        textCaseSensitive("Subjects"),        textCaseSensitive("Arts, Civics"),
        textCaseSensitive("Hobbies"),         textCaseSensitive("Reading, Music"),
        textCaseSensitive("Picture"),         textCaseSensitive("picture.jpg"),
        textCaseSensitive("Address"),         textCaseSensitive("Novosibirsk"),
        textCaseSensitive("State and City"),  textCaseSensitive("Haryana Panipat")
    );

    $("#closeLargeModal").click();
  }
}
