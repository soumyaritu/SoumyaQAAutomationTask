package com.test.utils


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.test.news.R
import org.hamcrest.Matchers

  // This method calls the User login .
 fun userLogin(userName:String,passWord:String){
   onView(ViewMatchers.withId(R.id.editTextUserName))
   .perform(ViewActions.typeText(userName))
   onView(Matchers.allOf(ViewMatchers.withId(R.id.editTextPassword)))
   .perform(ViewActions.typeText(passWord))
   onView(ViewMatchers.withId(R.id.buttonLogin)).perform(ViewActions.click())

 }

