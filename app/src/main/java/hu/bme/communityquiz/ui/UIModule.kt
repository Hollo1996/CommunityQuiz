package hu.bme.communityquiz.ui

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.bme.communityquiz.ui.answer_question.AnswerQuestionPresenter
import hu.bme.communityquiz.ui.choose_category.ChooseCategoryPresenter
import hu.bme.communityquiz.ui.choose_category.ChooseCategoryScreen
import hu.bme.communityquiz.ui.create_question.CreateQuestionPresenter
import hu.bme.communityquiz.ui.main.MainPresenter
import hu.bme.communityquiz.ui.score.ScorePresenter
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class UIModule(private val context: Context) {

    @Provides
    fun context() = context

    @Provides
    @Singleton
    fun mainPresenter() = MainPresenter()

    @Provides
    @Singleton
    fun chooseCategoryPresenter() = ChooseCategoryPresenter()

    @Provides
    @Singleton
    fun scorePresenter() = ScorePresenter()

    @Provides
    @Singleton
    fun createQuestionPresenter() = CreateQuestionPresenter()

    @Provides
    @Singleton
    fun answerQuestion() = AnswerQuestionPresenter()

    /*
    @Provides
    @Singleton
    fun artistsPresenter(executor: Executor, artistsInteractor: ArtistsInteractor) = ArtistsPresenter(executor, artistsInteractor)

    @Provides
    @Singleton
    fun networkExecutor(): Executor = Executors.newFixedThreadPool(1)
    */
}
