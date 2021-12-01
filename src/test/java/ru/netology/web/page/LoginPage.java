package ru.netology.web.page;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement blockOfLoginInfo = $ (Selectors.withText("Доступ в личный кабинет заблокирован, поскольку Вы 3 раза ввели неверный пароль"));

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void InvalidLogin(DataHelper.AuthInfo info, DataHelper.AuthInfo otherInfo) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(otherInfo.getPassword());
        loginButton.click();
    }

    public void clearAndPutOtherPassword(DataHelper.AuthInfo otherInfo) {
        passwordField.sendKeys(Keys.CONTROL + "A");
        passwordField.sendKeys(Keys.BACK_SPACE);
        passwordField.setValue(otherInfo.getPassword());
        loginButton.click();
    }

    public SelenideElement getBlockOfLoginInfo() {
        return blockOfLoginInfo;
    }
}