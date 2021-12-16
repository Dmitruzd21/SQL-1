package ru.netology.web.test;

import SQL.SQLMethodsWithDbUtils;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Selenide.open;

public class AuthenticationTestWithDbutils {

    @AfterAll
    static void shouldClearAllTables() {
        SQLMethodsWithDbUtils.clearAllTables();
    }

    @Test
    @DisplayName("SuccessfulAuthentication")
    void shouldBeSuccessfulAuthentication() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLMethodsWithDbUtils.getVerificationCodeFor();
        var dashboardPageBefore = verificationPage.validVerify(verificationCode);
    }

    @Test
    @DisplayName("BlockAfterThreeTimesOfInvalidPasswordInput")
    void shouldBlockAfterThreeTimesOfInvalidPasswordInput() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var otherAuthInfo = DataHelper.getOtherAuthInfo();
        loginPage.InvalidLogin(authInfo, otherAuthInfo);
        loginPage.clearAndPutOtherPassword(otherAuthInfo);
        loginPage.clearAndPutOtherPassword(otherAuthInfo);
        var blockInfo = loginPage.getBlockOfLoginInfo();
        blockInfo.shouldBe(Condition.visible);
    }
}
