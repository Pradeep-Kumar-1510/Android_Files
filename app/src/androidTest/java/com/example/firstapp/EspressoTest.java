package com.example.firstapp;

import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.example.firstapp.activity.EspressoActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest {

    @Rule
    public ActivityScenarioRule<EspressoActivity> activityScenarioRule
            = new ActivityScenarioRule<>(EspressoActivity.class);

    @Test
    public void testViewsDisplayed() {

        Espresso.onView(withId(R.id.text1))
                .check(matches(isDisplayed()));

        Espresso.onView(withId(R.id.text2))
                .check(matches(isDisplayed()));

        Espresso.onView(withId(R.id.editText1))
                .check(matches(isDisplayed()));
    }


    @Test
    public void testEditText() {

        Espresso.onView(withId(R.id.editText1))
                .perform(typeText("Test Username"));

    }

    @Test
    public void testButton() {

        Espresso.onView(withId(R.id.editText1)).perform(ViewActions.typeText("Text to clear"));
        Espresso.onView(withId(R.id.button1)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.editText1)).check(matches(withText(" ")));
    }
    public void testCheckBox() {

        Espresso.onView(withId(R.id.checkBox1)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.checkBox1)).check(matches(isChecked()));
        Espresso.onView(withId(R.id.checkBox1)).perform(ViewActions.click());
        Espresso.onView(withId(R.id.checkBox1)).check(matches(isNotChecked()));
    }
    @Test
    public void testImageViewVisibility() {

        Espresso.onView(withId(R.id.imageView1)).check(matches(isDisplayed()));
    }
}
