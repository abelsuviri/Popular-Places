package com.abelsuviri.popularplaces.tests;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;

import com.abelsuviri.popularplaces.R;
import com.abelsuviri.popularplaces.tests.utils.ElapsedTimeIdlingResource;
import com.abelsuviri.popularplaces.ui.MainActivity;
import com.abelsuviri.popularplaces.ui.PlacesListActivity;

import org.junit.Rule;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import androidx.test.espresso.IdlingPolicies;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static androidx.test.InstrumentationRegistry.getTargetContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertNotNull;

/**
 * @author Abel Suviri
 */

@RunWith(AndroidJUnit4.class)
public class SearchCitySteps {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public IntentsTestRule<MainActivity> intentsRule = new IntentsTestRule<>(MainActivity.class);;

    private Activity activity;

    @Before
    public void setup() {
        activityTestRule.launchActivity(new Intent());
        activity = activityTestRule.getActivity();
    }

    @After
    public void tearDown() {
        activityTestRule.finishActivity();
    }

    @Given("^the user is in MainActivity")
    public void the_user_is_in_MainActivity() {
        assertNotNull(activity);
    }

    /**
     * Test for invalid city
     */

    @When("^the user types a non existing city (.*)$")
    public void the_user_types_a_non_existing_city(String city) {
        onView(withId(R.id.cityName)).perform(typeText(city));
    }

    @When("^the user clicks on the search button")
    public void the_user_clicks_on_the_search_button() {
        onView(withId(R.id.search)).perform(click());
    }

    @Then("^an error dialog is shown")
    public void anErrorDialogIsShown() {
        onView(withText("RETRY")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("RETRY")).inRoot(isDialog()).perform(click());
    }

    /**
     * Test for valid city
     */

    @When("^the user types an existing city (.*)$")
    public void the_user_types_an_existing_city(String city) {
        onView(withId(R.id.cityName)).perform(typeText(city));
    }

    @Then("^the app navigates to the PlacesListActivity")
    public void the_app_navigates_to_the_PlacesListActivity() {
        IdlingPolicies.setMasterPolicyTimeout(5000 * 2, TimeUnit.MILLISECONDS);
        IdlingPolicies.setIdlingResourceTimeout(5000 * 2, TimeUnit.MILLISECONDS);

        IdlingResource idlingResource = new ElapsedTimeIdlingResource(5000);
        IdlingRegistry.getInstance().register(idlingResource);

        Intents.init();
        intended(hasComponent(new ComponentName(getTargetContext(), PlacesListActivity.class)));
        Intents.release();
    }
}
