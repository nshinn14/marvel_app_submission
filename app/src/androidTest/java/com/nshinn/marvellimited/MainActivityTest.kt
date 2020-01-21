package com.nshinn.marvellimited

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {

        // Verify the action bar title is visible and exists
        val textViewActionBar = onView(allOf(withText("Marvel Limited"), isDisplayed()))
        textViewActionBar.check(matches(withText("Marvel Limited")))

        // Verify the refresh message is visible and exists
        val textView = onView(allOf(withId(R.id.swipe_to_refresh_message), isDisplayed()))
        textView.check(matches(isDisplayed()))
        textView.check(matches(withText("Swipe down to get the latest Marvel Comics!")))

        // Verify the refresh message is no longer visible
        val refreshButton = onView(allOf(withId(R.id.refresh), withContentDescription("Refresh"), isDisplayed()))
        refreshButton.check(matches(isClickable()))

    }

}
