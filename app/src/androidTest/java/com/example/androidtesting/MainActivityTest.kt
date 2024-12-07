package com.example.androidtesting

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtras
import androidx.test.espresso.intent.matcher.IntentMatchers.hasType
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test

class MainActivityTest{

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()


    @Test
    fun checkNextStudentButtonClick(){

        onView(withId(R.id.btnNextStudent)).perform(click())
        onView(withId(R.id.btnNextStudent)).perform(click())
        onView(withId(R.id.btnNextStudent)).perform(click())
        onView(withId(R.id.btnNextStudent)).perform(click())
        onView(withId(R.id.btnNextStudent)).perform(click())
        onView(withId(R.id.btnNextStudent)).perform(click())

    }

    @Test
    fun testPreviousClicks(){
        onView(withId(R.id.btnNextPrevious)).perform(click())
        onView(withId(R.id.btnNextPrevious)).perform(click())
        onView(withId(R.id.btnNextPrevious)).perform(click())
        onView(withId(R.id.btnNextPrevious)).perform(click())
        onView(withId(R.id.btnNextPrevious)).perform(click())
        onView(withId(R.id.btnNextPrevious)).perform(click())

    }

    @Test
    fun shareIntentFunctionality(){

        Intents.init()
        val expected = allOf(
            hasAction(Intent.ACTION_CHOOSER),
            hasExtra(Intent.EXTRA_INTENT, allOf(
                hasAction(Intent.ACTION_SEND),
                hasExtra(Intent.EXTRA_TEXT, "junaid"),
                hasType("text/plain")
            )),
            hasExtra(Intent.EXTRA_TITLE, "Please select")
        )
        onView(withId(R.id.btnShareApp)).perform(click())
        intended(expected)
        Intents.release()

    }


}