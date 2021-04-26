package hu.bme.communityquiz

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.bme.communityquiz.interactor.DataBaseInteractor
import hu.bme.communityquiz.interactor.LogicInteractor
import hu.bme.communityquiz.interactor.ServerInteractor
import hu.bme.communityquiz.ui.answer_question.AnswerQuestionPresenter
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
