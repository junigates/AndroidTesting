package com.example.androidtesting.todomanager

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import com.example.androidtesting.R
import org.hamcrest.core.AllOf.allOf
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TodoActivityTest {


    @get:Rule
    val activityScenarioRule = activityScenarioRule<TodoActivity>()


    @Test
    fun test1_addANewTaskWithButtonAndFillEditTextForNameSouldBePassed(){

        onView(withId(R.id.etTaskName)).perform(typeText("Muhammad Junaid Bashir"))
        onView(withId(R.id.btnAddTask)).perform(click())

    }

    @Test
    fun test2_addANewTaskWithButtonAndFillEditTextForNameShouldBeFailedShowToast(){

        onView(withId(R.id.etTaskName)).perform(typeText(""))
        onView(withId(R.id.btnAddTask)).perform(click())

    }


    @Test
    fun test3_deleteAtask_Should_be_passed(){
        onView(withId(R.id.etTaskDelete)).perform(typeText("4"))
        onView(withId(R.id.btnDeleteTask)).perform(click())
    }

    @Test
    fun test4_deleteAtask_Should_be_Failed(){
        onView(withId(R.id.etTaskDelete)).perform(typeText(""))
        onView(withId(R.id.btnDeleteTask)).perform(click())
    }

    @Test
    fun test5_markTaskAsCompleted(){
        onView(withId(R.id.etTaskDelete)).perform(typeText("2"))
        onView(withId(R.id.btnMarkItComplete)).perform(click())
    }

    @Test
    fun test6_writeSomethingInTheTextView(){

        onView(withId(R.id.etTaskName)).perform(
            typeText("Meditation")
        )

    }

    @Test
    fun test7_writeSomethingInTheTextView(){

        activityScenarioRule.scenario.onActivity { activity->
            activity.writeSomethingInText("Hey Good Morning Bro")
        }

        onView(withId(R.id.tvData)).check(matches(withText("Hey Good Morning Bro")))
    }

    @Test
    fun test8_checkForShareButton(){

        Intents.init()

        val expected_intent = allOf(
            hasAction(Intent.ACTION_CHOOSER),
            hasExtra(Intent.EXTRA_INTENT, allOf(
                hasAction(Intent.ACTION_SEND)
            )),
            hasExtra(Intent.EXTRA_TITLE,"Please select an app")
        )

        onView(withId(R.id.btnShareApp)).perform(click())

        intended(expected_intent)

        Intents.release()

    }
}