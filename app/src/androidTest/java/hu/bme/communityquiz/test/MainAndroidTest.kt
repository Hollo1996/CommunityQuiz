package hu.bme.communityquiz.test

import android.content.Intent
import androidx.test.runner.AndroidJUnit4
import hu.bme.communityquiz.ui.main.MainActivity
import hu.bme.communityquiz.utils.AndroidTestUtils.setTestInjector
import hu.bme.communityquiz.utils.EspressoTest
import hu.bme.communityquiz.utils.matchToolbarTitle
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainAndroidTest : EspressoTest<MainActivity>(MainActivity::class.java) {


    @Before
    fun setUp() {
        setTestInjector()
        activityRule.launchActivity(Intent())
    }

    @Test
    fun testNavigateToArtistActivity() {
        //onView(withId(R.id.etArtist)).perform(typeText(ARTIST), closeSoftKeyboard())
        //onView(withId(R.id.btnShowArtists)).perform(click())
        //matchToolbarTitle(ARTIST_ACTIVITY_TITLE)
    }

    companion object {
        private const val ARTIST = "AC/DC"
        private const val ARTIST_ACTIVITY_TITLE = "ArtistsActivity"
    }




}