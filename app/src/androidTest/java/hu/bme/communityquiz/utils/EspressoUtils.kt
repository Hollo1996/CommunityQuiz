package hu.bme.communityquiz.utils

import androidx.appcompat.widget.Toolbar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is` as macthesIs


fun matchToolbarTitle(title: CharSequence): ViewInteraction {
    return onView(isAssignableFrom(Toolbar::class.java)).check(matches(withToolbarTitle(macthesIs(title))))
}


private fun withToolbarTitle(
        textMatcher: Matcher<CharSequence>): Matcher<Any> {
    return object : BoundedMatcher<Any, Toolbar>(Toolbar::class.java) {
        public override fun matchesSafely(toolbar: Toolbar): Boolean {
            return textMatcher.matches(toolbar.title)
        }

        override fun describeTo(description: Description) {
            description.appendText("with toolbar title: ")
            textMatcher.describeTo(description)
        }
    }
}
