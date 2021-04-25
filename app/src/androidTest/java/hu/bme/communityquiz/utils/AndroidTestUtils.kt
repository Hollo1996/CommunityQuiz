package hu.bme.communityquiz.utils

import androidx.test.InstrumentationRegistry
import hu.bme.communityquiz.AndroidTestModule
import hu.bme.communityquiz.CommunityQuizApplication
import hu.bme.communityquiz.DaggerAndroidTestComponent
import hu.bme.communityquiz.DaggerCommunityQuizApplicationComponent
import hu.bme.communityquiz.ui.UIModule

object AndroidTestUtils {

    fun setTestInjector() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as CommunityQuizApplication

        val androidTestComponent = DaggerAndroidTestComponent.builder().androidTestModule(
            AndroidTestModule(app)
        ).build()
        app.injector = androidTestComponent
    }

    fun setProdutionInjector() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as CommunityQuizApplication

        val androidTestComponent = DaggerCommunityQuizApplicationComponent.builder().uIModule(UIModule(app)).build()
        app.injector = androidTestComponent
    }
}
