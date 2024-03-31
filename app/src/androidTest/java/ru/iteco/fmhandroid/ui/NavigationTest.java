package ru.iteco.fmhandroid.ui;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import page.Authorization;
import page.Matches;
import page.Navigation;

@LargeTest
////@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)
@Epic("Навигация")

public class NavigationTest {
    Navigation navigation = new Navigation();
    Authorization authorization = new Authorization();
    Matches matches = new Matches();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void testLogOut() {

        authorization.waitForLoading();

        try {
            authorization.authorizationIn("login2", "password2");
            authorization.waitForLoading();

        } catch (Exception es) {

        }
    }

    @Test
    @DisplayName("Тест 3 Переход с главной страницы в раздел 'Новости' через меню 'бургер'")
    public void navigationFromMainPageToNewsFromMainMenuTest() {
        navigation.navigationFromMainPageMainMenu("News");
        matches.examinationValue("News");
    }

    @Test
    @DisplayName("Тест 4 Переход с главной страницы в раздел 'Новости' через кнопку 'Все новости'")
    public void navigationFromMainPageToNewsFromAllNewsButtonTest() {
        navigation.navigationFromMainPageFromAllNewsButton();
        matches.examinationValue("News");
    }

    @Test
    @DisplayName("Тест 5 Переход с главной страницы в раздел 'О приложении' через меню 'бургер'")
    public void navigationFromMainPageToAboutTest() {
        navigation.navigationFromMainPageMainMenu("About");
        matches.examinationValue("Version:");
    }

    @Test
    @DisplayName("Тест 6 Переход с главной страницы в раздел тематических цитат 'Главное -жить любя'")
    public void navigationFromMainPageToOurMissionTest() {
        navigation.navigationFromMainPageToOurMission();
        matches.examinationValue("Love is all");
    }

    @Test
    @DisplayName("Тест 7 Переход из раздела 'Новости' в раздел 'О приложении' через меню 'бургер'")
    public void navigationFromNewsPageToAboutFromMainMenuTest() {
        navigation.navigationFromMainPageMainMenu("News");
        navigation.navigationFromMainPageMainMenu("About");
        matches.examinationValue("Version:");
    }

    @Test
    @DisplayName("Тест 8 Переход из раздела 'Новости' в раздел 'Панель управления'")
    public void navigationFromNewsPageToControlPanelTest() {
        navigation.navigationFromMainPageMainMenu("News");
        navigation.navigationFromNewsPageToControlPanel();
        matches.examinationValue("Control panel");
    }

    @Test
    @DisplayName("Тест 9 Переход из раздела 'О приложении' на главную страницу")
    public void navigationFromAboutToMainPageTest() {
        navigation.navigationFromMainPageMainMenu("About");
        navigation.navigationFromAboutToMainPage();
        matches.examinationValue("News");
    }

}
