package hu.bme.communityquiz

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.bme.communityquiz.ui.main.MainPresenter
import javax.inject.Singleton

@Module
class TestModule(private val context: Context) {

    @Provides
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideMainPresenter() = MainPresenter()
}
