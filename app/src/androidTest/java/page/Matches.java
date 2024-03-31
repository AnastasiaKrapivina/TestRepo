package page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static kotlinx.coroutines.flow.FlowKt.withIndex;
import android.view.View;
import androidx.test.espresso.ViewInteraction;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;

import ru.iteco.fmhandroid.R;

public class Matches {

    // Прорверить переход на страницу:
    public void examinationValue(String value) {
        onView(withText(value)).check(matches(isDisplayed()));
    }

    // Прорверить всплывающее окно при входе с ошибочными данными:
    public void examinationContentDescription(String value) {
        onView(allOf(withContentDescription(value), isDisplayed()));
    }

    // Прорверить всплывающее окно:
    public void checkToastMessageText() {
        ViewInteraction imageView = onView(
                allOf(withContentDescription("app background image"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));
    }

// Прорверить появления новости по заголовку:
    public void searchNewsCategory(String heading, int position) {
        onView(
                allOf(withIndex(withId(R.id.news_item_title_text_view), position),
                        isDisplayed()))
                .check(matches(withText(heading)));
    }
    // Прорверить содержание новости:
    public void searchNewsDescription(String description, int position) {
        onView(
                allOf(withIndex(withId(R.id.news_item_description_text_view), position),
                        isDisplayed()))
                .check(matches(withText(description)));
    }


    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }


}
