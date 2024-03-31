package page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import ru.iteco.fmhandroid.R;

public class Navigation {
    private static final int mainMenu = R.id.main_menu_image_button;
    private static final int  allNewsButton = R.id.all_news_text_view;
    private static final int ourMissionButton = R.id.our_mission_image_button;
    private static final int aboutBackButton = R.id.about_back_image_button;
    private static final int controlPanelButton = R.id.edit_news_material_button;

    // Переход  с главной страницы через главное меню в разделы приложения
    public void navigationFromMainPageMainMenu(String chapter) {
        onView(withId(mainMenu)).perform(click());
        onView(withText(chapter)).perform(click());
    }

    // Переход  с главной страницы в раздел Новости через кнопку Все новости
    public void navigationFromMainPageFromAllNewsButton() {
        onView(allOf(withId(allNewsButton), withText("All news"))).perform(click());
    }

    // Переход  с главной страницы в раздел цитат
    public void navigationFromMainPageToOurMission() {
        onView(allOf(withId(ourMissionButton), withContentDescription("Our Mission"))).perform(click());
    }

    // Переход  с из раздела Новости в раздел О приложении  через главное меню
    public void navigationFromNewsPageToControlPanel() {
        onView(withId(controlPanelButton)).perform(click());
    }

    // Переход  с из раздела О приложении на главную страницу
    public void navigationFromAboutToMainPage() {
        onView(withId(aboutBackButton)).perform(click());
    }
}
