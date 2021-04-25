package hu.bme.communityquiz.utils

import android.app.Activity
import android.content.Context
import androidx.test.InstrumentationRegistry
import androidx.test.espresso.intent.rule.IntentsTestRule
import org.junit.Rule


open class EspressoTest<T : Activity>(activityClass: Class<T>) {

    @Rule @JvmField
    val activityRule: IntentsTestRule<T> = IntentsTestRule(activityClass, false, false)
    private var context: Context

    init {

        val instrumentation = InstrumentationRegistry.getInstrumentation()
        context = instrumentation.targetContext.applicationContext
    }

}
