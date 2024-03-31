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
import page.News;

@LargeTest
//@RunWith(AndroidJUnit4.class)
@RunWith(AllureAndroidJUnit4.class)
@Epic("Раздел Новости")

public class NewsTest {
    Navigation navigation = new Navigation();
    Authorization authorization = new Authorization();
    Matches matches = new Matches();
    News news = new News();

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
    @DisplayName("Тест 10 Создание новости")
    public void creationOfNewsTest() {
        navigation.navigationFromMainPageMainMenu("News");
        navigation.navigationFromNewsPageToControlPanel();
        news.addNews("Объявление", "Создание новости", "Создание новости");
        authorization.waitForLoading();
        navigation.navigationFromMainPageMainMenu("News");
        matches.searchNewsCategory("Создание новости", 0);
    }

    @Test
    @DisplayName("Тест 11 Отмена операции 'Создание новости'")
    public void cancelCancelreationOfNewsTest() {
        navigation.navigationFromMainPageMainMenu("News");
        navigation.navigationFromNewsPageToControlPanel();
        news.cancelNews();
        authorization.waitForLoading();
        matches.examinationValue("Control panel");
    }

    @Test
    @DisplayName("Тест 12 Сортировка новостей в разделе Новости")
    public void sortNewsOnNewsTest() {
        navigation.navigationFromMainPageMainMenu("News");
        news.sortNewsOnControlPanel();
        authorization.waitForLoading();
        news.sortNewsOnControlPanel();
    }

    @Test
    @DisplayName("Тест 13 Фильтр новостей в разделе Новости оставив поля фильтра незаполненными")
    public void filterNewsOnNewsEmptyTest() {
        navigation.navigationFromMainPageMainMenu("News");
        news.filterNewsEmpty();
        matches.examinationValue("News");
    }
    @Test
    @DisplayName("Тест 14 Отмена операции фильтра новостей в разделе Новости")
    public void filterNewsOnNewsCanselTest() {
        navigation.navigationFromMainPageMainMenu("News");
        news.filterNewsCansel();
        matches.examinationValue("News");

    }
    @Test
    @DisplayName("Тест 15 Фильтр новостей в разделе Новости по параметру Категория")
    public void filterNewsOnNewsCategoryTest() {
        navigation.navigationFromMainPageMainMenu("News");
        navigation.navigationFromNewsPageToControlPanel();
        news.addNews("Зарплата", "Зарплата", "Зарплата");
        news.addNews("Объявление", "Объявление", "Объявление");
        navigation.navigationFromMainPageMainMenu("News");
        news.filterNewsCategory("Зарплата");
        navigation.navigationFromMainPageMainMenu("News");
        matches.searchNewsCategory("Зарплата", 0);
    }

    @Test
    @DisplayName("Тест 16 Просмотр новости в разделе 'Новости'")
    public void viewingNewsOnNewsPageTest() {
        navigation.navigationFromMainPageMainMenu("News");
        navigation.navigationFromNewsPageToControlPanel();
        news.addNews("Объявление", "Создание новости", "Создание новости");
        navigation.navigationFromMainPageMainMenu("News");
        news.clickNews(0);
        matches.searchNewsDescription("Создание новости", 0);
    }
    @Test
    @DisplayName("Тест 20 Добавление новости с незаполненными полями")
    public void creationOfNewsEmptyTest() {
        navigation.navigationFromMainPageMainMenu("News");
        navigation.navigationFromNewsPageToControlPanel();
        news.addNews("Объявление", "", "");
        matches.examinationValue("Creating");
    }


}
