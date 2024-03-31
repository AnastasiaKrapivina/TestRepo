package ru.iteco.fmhandroid.ui;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import page.Authorization;
import page.Matches;

@LargeTest
@RunWith(AndroidJUnit4.class)
//@RunWith(AllureAndroidJUnit4.class)
@Epic("Авторизация пользователя")

public class AuthorizationTest {
    Authorization authorization = new Authorization();
    Matches matches = new Matches ();


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void testLogOut() {

        authorization.waitForLoading();

        try {
            authorization.authorizationExit();
         } catch (Exception es) {

        }
    }

    @Test
    @DisplayName("Тест 1 Ввод допустимых значений в поля формы авторизации")
    public void authorizationValidTest() {
        authorization.waitForLoading();
        authorization.authorizationIn("login2", "password2");
        authorization.waitForLoading();
        matches.examinationValue("News");
    }
    @Test
    @DisplayName("Тест 2 Выход из приложения")
    public void authorizationExitTest() {
        authorization.waitForLoading();
        authorization.authorizationIn("login2", "password2");
        authorization.waitForLoading();
        authorization.authorizationExit();
        matches.examinationValue("Authorization");
    }

    @Test
    @DisplayName("Тест 17 Отправка незаполненных полей формы авторизации")
    public void authorizationInvalid() {
        authorization.waitForLoading();
        authorization.authorizationIn(" ", " ");
        authorization.waitForLoading();
        matches.checkToastMessageText();
        matches.examinationContentDescription("Login and password cannot be empty");
    }

    @Test
    @DisplayName("Тест 18 Ввод недопустимого значения в поле 'Логин'")
    public void authorizationInvalidLogin() {
        authorization.waitForLoading();
        authorization.authorizationIn("login", "password2");
        authorization.waitForLoading();
        matches.checkToastMessageText();
        matches.examinationContentDescription("Wrong login or password");

    }
    @Test
    @DisplayName("Тест 19 Ввод недопустимого значения в поле 'Пароль'")
    public void authorizationInvalidPassword() {
        authorization.waitForLoading();
        authorization.authorizationIn("login2", "password");
        authorization.waitForLoading();
        matches.checkToastMessageText();
        matches.examinationContentDescription("Wrong login or password");
    }


}
