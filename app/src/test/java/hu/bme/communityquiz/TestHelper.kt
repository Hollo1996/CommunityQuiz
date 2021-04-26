package hu.bme.communityquiz


import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.robolectric.shadows.ShadowLog

val testInjector: TestComponent
    get() {
        ShadowLog.stream = System.out
        val context = ApplicationProvider.getApplicationContext() as Context
        return DaggerTestComponent.builder().testModule(TestModule(context)).build()
    }

