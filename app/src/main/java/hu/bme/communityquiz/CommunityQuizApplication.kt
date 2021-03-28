package hu.bme.communityquiz

import android.app.Application
import hu.bme.communityquiz.ui.UIModule

class CommunityQuizApplication : Application() {
    lateinit var injector: CommunityQuizApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injector = DaggerCommunityQuizApplicationComponent.builder().uIModule(UIModule(this)).build()
    }
}
