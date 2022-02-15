package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;


public class PracticeFormWithPageObjectTest {
  RegistrationPage registrationPage = new RegistrationPage();
  String firstName = "Ivan";
  String lastName = "Isaev";
  String email = "ivan@jirafiki.eu";
  String gender = "Male";
  String userNumber = "1234567890";
  Integer birthDay = 7;
  String birthMonth = "February";
  String birthYear = "1990";
  String artsSubject = "Arts";
  String civicsSubject = "Civics";
  String musicHobby = "Music";
  String readingHobby = "Reading";
  String picturePath = "picture.jpg";
  String currentAddress = "Ulitca dom kvartira";
  String state = "Haryana";
  String city = "Panipat";

  @BeforeAll
  static void beforeAll() {
    Configuration.baseUrl = "https://demoqa.com";
    Configuration.browserSize = "2560x1440";
  }

  @Test
  void successFillTest() {
    registrationPage.openPage()
            .setFirstName(firstName)
            .setLastName(lastName)
            .setUserEmail(email)
            .setGender(gender)
            .setUserNumber(userNumber)
            .setBirthDate(birthDay, birthMonth, birthYear)
            .setSubjects(artsSubject, civicsSubject)
            .setHobbies(musicHobby, readingHobby)
            .uploadPicture(picturePath)
            .setCurrentAddress(currentAddress)
            .setState(state)
            .setCity(city);

    registrationPage.clickSubmitButton();

    registrationPage
            .checkForm("Student Name", firstName + " " + lastName)
            .checkForm("Student Email", email)
            .checkForm("Gender", gender)
            .checkForm("Mobile", userNumber)
            .checkForm("Date of Birth", birthDay + " " + birthMonth + "," + birthYear)
            .checkForm("Subjects", artsSubject + ", " + civicsSubject)
            .checkForm("Hobbies", musicHobby + ", " + readingHobby)
            .checkForm("Picture", picturePath)
            .checkForm("Address", currentAddress)
            .checkForm("State and City", state + " " + city)
            .checkExistsCloseButton();

  }
}
