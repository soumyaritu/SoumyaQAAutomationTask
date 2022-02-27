package com.test.news



import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.test.news.features.login.presentation.LoginActivity
import com.test.news.features.news.presentation.NewsActivity
import com.test.utils.userLogin
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed


@RunWith(AndroidJUnit4ClassRunner::class)
class LoginActivityTest {
    @get:Rule
    var mActivityTestRule= ActivityTestRule(LoginActivity::class.java)
    companion object{
        private const val INVALID_USERNAME="Password"
        private  const val INVALID_PASSWORD="user123"
        private const val VALID_USERNAME="user"
        private const val VALID_PASSWORD="password"
        private const val ERROR_USERNAME="Wrong user name"
        private const val ERROR_PASSWORD="Wrong password"

    }

    @Before
    fun setup(){
        Intents.init()

    }
    @After
    fun tearDown(){
        Intents.release()

    }
    //UserStory1: As a user I want to login to the App.
    @Test
    fun iSeeLoginForm()
    {
        onView(withText("News"))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(allOf(withId(R.id.editTextUserName),isDisplayed()))
        onView(allOf(withId(R.id.editTextPassword),isDisplayed()))
        onView(allOf(withId(R.id.buttonLogin),isDisplayed()))
    }
    //Scenario 2 - user login failed

    @Test
    fun iSeeLoginFailedWithInvalidPassword(){
        userLogin(VALID_USERNAME, INVALID_PASSWORD)
        onView(withId(R.id.editTextPassword))
          .check(matches(hasErrorText(ERROR_PASSWORD)))
        Thread.sleep(2000)
    }
    @Test
    fun iSeeLoginFailedWithInvalidUsername(){
        userLogin(INVALID_USERNAME, INVALID_PASSWORD)
        Thread.sleep(1000)
        onView(withId(R.id.editTextUserName))
            .check(matches(hasErrorText(ERROR_USERNAME)))
        Thread.sleep(2000)
    }
    //Scenario 3 - user login succeed (credentials provided below)

    @Test
    fun verifyLoginSuccessWithValidCredentials() {
        Thread.sleep(1000)
        userLogin(VALID_USERNAME, VALID_PASSWORD)
        Thread.sleep(1000)
        intended(IntentMatchers.hasComponent(NewsActivity::class.java.name))
    }
    /*Scenario 4 - user opens app next time (when previously logged in)
     Given - the user opens app next time (when previously logged in)
    Then - user is taken straight to the news screen
     I  couldn't automate this scenario because the Session Management/ Shared Preference
      is not handled in this App(ie, User's user name and password is not saved in the Session, instead it is saved a db*/

}