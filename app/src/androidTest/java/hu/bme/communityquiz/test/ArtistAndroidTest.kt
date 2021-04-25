package hu.bme.communityquiz.test

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import hu.bme.communityquiz.ui.main.MainActivity
import hu.bme.communityquiz.utils.AndroidTestUtils.setTestInjector
import hu.bme.communityquiz.utils.ElapsedTimeIdlingResource.Companion.waitFor
import hu.bme.communityquiz.utils.EspressoTest
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches
/*
@RunWith(AndroidJUnit4::class)
class ArtistAndroidTest : EspressoTest<ArtistsActivity>(ArtistsActivity::class.java) {

    @Before fun setUp() {
        setTestInjector()
        activityRule.launchActivity(Intent().putExtra(MainActivity.KEY_ARTIST, ARTIST))
    }

    @Test fun testSearch() {
        onView(withId(R.id.etArtist)).check(matches(withText(ARTIST)))
        waitFor(2000) {
            onView(allOf(withId(hu.bme.aut.android.kotifydemo.R.id.tvName), withText(ARTIST))).check(matches(isDisplayed()))
        }
    }

    companion object {
        private const val ARTIST = "AC/DC"
    }


}
*/