package com.teach.eats

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.teach.eats.fragments.help.HelpFragment
import com.teach.eats.fragments.title.TitleFragment
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is` as Is
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TestSuite {
    @Test
    fun testTitleFragment() {
        val scenario = launchFragmentInContainer<TitleFragment>()
        onView(withId(R.id.title_header)).check(matches(withText("Welcome!")))
        onView(withId(R.id.start_button)).check(matches(withText("Start")))
        onView(withId(R.id.help_button)).check(matches(withText("Help")))
    }
    @Test
    fun testHelpFragment() {
        val scenario = launchFragmentInContainer<HelpFragment>()
        onView(withId(R.id.help_return_button)).check(matches(withText("Home")))
        onView(withId(R.id.about)).check(matches(withText("About")))
    }
    @Test
    fun testNavigationFromTitleToHelp() {
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        runOnUiThread {
            navController.setGraph(R.navigation.navigation)
        }

        // Create a graphical FragmentScenario for the TitleFragment
        val titleScenario = launchFragmentInContainer<TitleFragment>()

        // Set the NavController property on the fragment
        titleScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // Verify that performing a click changes the NavController’s state
        onView(withId(R.id.help_button)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id, Is(R.id.helpFragment))
    }
    @Test
    fun testNavigationFromHelpToTitle() {
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        runOnUiThread {
            navController.setGraph(R.navigation.navigation)
            navController.setCurrentDestination(R.id.helpFragment)
        }

        // Create a graphical FragmentScenario for the HelpFragment
        val helpScenario = launchFragmentInContainer<HelpFragment>()

        // Set the NavController property on the fragment
        helpScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // Verify that performing a click changes the NavController’s state
        onView(withId(R.id.help_return_button)).perform(ViewActions.click())
        assertThat(navController.currentDestination?.id, Is(R.id.titleFragment))
    }
}