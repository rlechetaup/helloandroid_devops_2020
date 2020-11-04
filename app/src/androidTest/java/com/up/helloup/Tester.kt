package com.up.helloup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import com.up.helloup.CustomAssertions.Companion.listCount
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers.instanceOf

class Tester {

    fun checkText(text: Int): Tester {
        onView(withText(text)).check(matches(isDisplayed()))
        return this
    }

    fun checkText(text: String): Tester {
        onView(withText(text)).check(matches(isDisplayed()))
        return this
    }

    fun checkDisplayed(vararg viewsIds: Int): Tester {
        for(id in viewsIds) {
            onView(withId(id)).check(matches(isDisplayed()))
        }
        return this
    }

    fun checkHint(vararg pairs: Pair<Int, Int>): Tester {
        for (pair in pairs) {
            onView(withId(pair.first)).check(matches(withHint(pair.second)))
        }
        return this
    }

    fun click(viewId: Int): Tester {
        onView(withId(viewId)).perform(click())
        return this
    }

    fun checkDialog(title: Int, msg: Int) {
        onView(withText(title)).inRoot(isDialog()).check(matches(isDisplayed()))
        onView(withText(msg)).inRoot(isDialog()).check(matches(isDisplayed()))
    }

    fun typeText(viewId: Int, text: String, closeKeyboard: Boolean = true) :Tester{
        if(closeKeyboard) {
            onView(withId(viewId)).perform(click()).perform(typeText(text))
        } else {
            onView(withId(viewId)).perform(click()).perform(typeText(text), closeSoftKeyboard())
        }
        return this
    }

    fun checkTitle(title: String): Tester {
        onView(
            allOf(
                instanceOf(TextView::class.java),
                withParent(withResourceName("action_bar"))
            )
        )
            .check(matches(withText(title)))
        return this
    }

    fun checkCount(count: Int) : Tester {
        onView(withId(R.id.recyclerView)).check(listCount(count))
        return this
    }

    fun wait(millis: Long): Tester {
        onView(isRoot()).perform(waitFor(millis))
        return this
    }

    fun clickItem(idx: Int): Tester {
        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(idx,click()))
        return this
    }

    fun back(): Tester {
        Espresso.pressBack()
        return this
    }
}