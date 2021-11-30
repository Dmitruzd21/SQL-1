package ru.netology.web.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import SQL.SQLMethodsWithJavaSql;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Selenide.open;


class AuthenticationTestWithJavaSql {

    @AfterAll
   static void shouldClearAllTables (){
       SQLMethodsWithJavaSql.clearAllTables();
    }

    @SneakyThrows
    @Test
    @DisplayName("SuccessfulAuthentication")
    void shouldBeSuccessfulAuthentication() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = SQLMethodsWithJavaSql.getVerificationCodeFor();
        var dashboardPageBefore = verificationPage.validVerify(verificationCode);
    }
}