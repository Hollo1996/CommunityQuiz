package hu.bme.communityquiz

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.bme.communityquiz.ui.UIModule
import hu.bme.communityquiz.ui.main.MainPresenter
import java.util.concurrent.Executor
import javax.inject.Singleton

@Module
class AndroidTestModule(context: Context) {

    private val uiModule: UIModule = UIModule(context)

    @Provides
    fun provideContext(): Context {
        return uiModule.context()
    }

    @Provides
    @Singleton
    fun provideMainPresenter(): MainPresenter {
        return uiModule.mainPresenter()
    }

}
