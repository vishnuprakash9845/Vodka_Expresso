package com.thoughtworks.notesvodqa

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isNotChecked
import androidx.test.espresso.matcher.ViewMatchers.isNotSelected
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.endsWith
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LiveDemoTests {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun viewMatcherExample(){
        onView(withId(R.id.add_new_notes))
    }
    @Test
    fun viewActionsExample(){
        onView(withId(R.id.add_new_notes)).perform(click())
    }
    @Test
    fun viewAssertionsExample(){
        onView(withId(R.id.add_new_notes)).check(matches(isDisplayed()))
    }
    @Test
    fun expressoBuildingBlockExample(){
        onView(withId(R.id.add_new_notes)).check(matches(isDisplayed())).perform(click())
    }
    //Assignment-1
    @Test
    fun verifyAddNotesTestButtonClickable(){
        onView(withId(R.id.add_new_notes)).check(matches(isClickable())).perform(click())
        //onView(withId(R.id.add_new_notes)).check(matches(isClickable()))
    }
    @Test
    fun verifyABottomTabsSelection(){
        val notesTab = onView(
            allOf(
                isDescendantOfA(withId(R.id.nav_view)),
                withId(com.google.android.material.R.id.navigation_bar_item_large_label_view),
                withText("Notes")
            )
        )
        notesTab.check(matches(isSelected()))
        onView(withContentDescription("Profile")).check(matches(isNotSelected()))
    }

    @Test
    fun addNotesTest(){
        onView(withId(R.id.add_new_notes)).perform(click())
        val addNotesTextEdit = onView(withId(R.id.add_notes_dialog_edit_text))
        addNotesTextEdit.perform(typeText("Test 2022"))
            .perform(clearText())
            .perform(typeText("Test 2023"))
        onView(withId(R.id.add_notes_dialog)).perform(click())
        onView(withId(R.id.notes_list_view)).perform(swipeUp())
        onView(withText(endsWith("2023"))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }
}