package com.test.news


import android.content.Context
import androidx.recyclerview.widget.RecyclerView

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.test.news.features.news.presentation.NewsActivity
import com.test.utils.NetworkHelper
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class NewsActivityTest {

    lateinit var instrumentationContext: Context
       @Before
       fun setup(){
           instrumentationContext = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().targetContext
       }

    @get:Rule
    var mActivityTestRule = ActivityTestRule(NewsActivity::class.java)


    //Scenario 3 - News image is clicked
    @Test
    fun iSeeExternalLinkOpenedOnImageClicked() {
        Thread.sleep(2000)
        onView(withId(R.id.recyclerViewNews))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
            )

    } //Scenario 1 & 2 are handled in this test- failed to load images
    @Test
    fun iSeeErrorMsgWhenNoInternetConnection() {

        Thread.sleep(3000)         // No Internet Connection
        val networkStatus =  NetworkHelper.checkNetworkStatus(instrumentationContext)
        if (!networkStatus) {
            onView(withText("Failed to load news"))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

        }else{                        // Internet Connection
            for (i in 1..2) {
                onView(withId(R.id.recyclerViewNews)).perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0, swipeLeft()
                    )
                )
            }

        }

    }}



