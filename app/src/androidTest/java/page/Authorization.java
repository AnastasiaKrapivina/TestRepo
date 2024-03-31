package page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import ru.iteco.fmhandroid.R;

public class Authorization {
    private static final int loginId = R.id.login_text_input_layout;
    private static final int passwordId = R.id.password_text_input_layout;
    private static final int signButton = R.id.enter_button;

    private static final int buttonLogOut = R.id.authorization_image_button;
    private static final int logOut = android.R.id.title;

    // Подождать загрузку:
    public void waitForLoading() {
        long endTime = (System.currentTimeMillis() + 20000);
        while (System.currentTimeMillis() < endTime) ;
    }

    // Ввод данных для авторизации:
    public void authorizationIn(String login, String password) {
        onView((withId(loginId))).perform(click());
        onView(allOf(childAtPosition(childAtPosition(withId(loginId), 0), 0),
                isDisplayed()))
                .perform(replaceText(login), closeSoftKeyboard());
        onView((withId(passwordId))).perform(click());
        onView(allOf(childAtPosition(childAtPosition(withId(passwordId), 0), 0),
                isDisplayed()))
                .perform(replaceText(password), closeSoftKeyboard());
        onView((withId(signButton))).perform(click());
    }

    // Выход из авторизации:
    public void authorizationExit() {
        onView((withId(buttonLogOut))).perform(click());
        onView((withId(logOut))).perform(click());
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
