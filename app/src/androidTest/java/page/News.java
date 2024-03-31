package page;

import static androidx.core.content.res.TypedArrayUtils.getText;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import static kotlinx.coroutines.flow.FlowKt.withIndex;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.RootMatchers;
import page.Navigation;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import ru.iteco.fmhandroid.R;


public class News {
    private static final int addNewsImage = R.id.add_news_image_view;
    private static final int categoryIcon = R.id.text_input_end_icon;
    private static final int headingIcon = R.id.news_item_title_text_input_edit_text;
    private static final int dateIcon = R.id.news_item_publish_date_text_input_edit_text;
    private static final int okButton = android.R.id.button1;
    private static final int timeIcon = R.id.news_item_publish_time_text_input_edit_text;
    private static final int descriptionIcon = R.id.news_item_description_text_input_edit_text;
    private static final int saveButton = R.id.save_button;
    private static final int newsList = R.id.news_list_recycler_view;
    private static final int cancelButton = R.id.cancel_button;
    public static final int filterNewsButton = R.id.filter_news_material_button;
    public static final int sortButton = R.id.sort_news_material_button;
    public static final int textInputIcon = com.google.android.material.R.id.text_input_end_icon;
    public static final int filterButton = R.id.filter_button;


    // Добавить новость:
    public void addNews(String category, String heading, String description) {
        onView(withId(addNewsImage)).perform(click());
        onView(allOf(withId(categoryIcon), withContentDescription("Show dropdown menu"))).perform(click());
        onView(withText(category))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());
        onView(withId(headingIcon)).perform(click(),clearText(),replaceText(heading));
        onView((withId(dateIcon))).perform(click());
        onView((withId(okButton))).perform(click());
        onView((withId(timeIcon))).perform(click());
        onView((withId(okButton))).perform(click());
        onView(withId(descriptionIcon))
                .perform(replaceText(description), closeSoftKeyboard());
        onView((withId(saveButton))).perform(scrollTo(), click());
    }

    // Отмена добавления новости:
    public void cancelNews() {
        onView(withId(addNewsImage)).perform(click());
        onView(allOf(withId(cancelButton), withText("Cancel"))).perform(click());
        onView((withId(okButton))).perform(click());
    }

    // Нажать на новость в Панели управления:
    public void clickNews(int position) {
        ViewInteraction recyclerView = onView(
                allOf(withId(newsList),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                               0)));
        recyclerView.perform(actionOnItemAtPosition(position, click()));
    }

    // Фильтр новостей по категории:
    public void filterNewsCategory(String category) {
        onView((withId(filterNewsButton))).perform(click());
        onView(allOf(withId(textInputIcon), withContentDescription("Show dropdown menu"))).perform(click());
        onView(withText(category))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());
        onView((withId(filterButton))).perform(click());

    }
//
//    // Фильтр новостей по дате:
//    public void filterNewsDate() {
//        onView((withId(filterNewsButton))).perform(click());
//        onView(withId(R.id.news_item_publish_date_start_text_input_edit_text)).perform(click());
//        onView((withId(okButton))).perform(click());
//        onView(withId(R.id.news_item_publish_date_end_text_input_edit_text)).perform(click());
//        onView((withId(okButton))).perform(click());
//        onView((withId(filterButton))).perform(click());
//    }

    // Фильтр новостей с незаполненной формой:
    public void filterNewsEmpty() {
        onView((withId(filterNewsButton))).perform(click());
        onView((withId(filterButton))).perform(click());
    }

    // Отмена операции фильтра новостей:
    public void filterNewsCansel() {
        onView((withId(filterNewsButton))).perform(click());
        onView((withId(R.id.cancel_button))).perform(click());
    }

    // Сортировка новостей:
    public void sortNewsOnControlPanel() {
        onView((withId(sortButton))).perform(click());
    }


     private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}