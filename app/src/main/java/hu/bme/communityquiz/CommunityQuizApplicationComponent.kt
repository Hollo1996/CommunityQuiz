package hu.bme.communityquiz

import dagger.Component
import hu.bme.communityquiz.interactor.InteractorModule
import hu.bme.communityquiz.network.NetworkModule
import hu.bme.communityquiz.ui.UIModule
import hu.bme.communityquiz.ui.answer_question.AnswerQuestionActivity
import hu.bme.communityquiz.ui.choose_category.ChooseCategoryActivity
import hu.bme.communityquiz.ui.create_question.CreateQuestionActivity
import hu.bme.communityquiz.ui.main.MainActivity
import hu.bme.communityquiz.ui.score.ScoreActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [UIModule::class, NetworkModule::class, InteractorModule::class])
interface CommunityQuizApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(chooseCategoryActivity: ChooseCategoryActivity)
    fun inject(scoreActivity: ScoreActivity)
    fun inject(createQuestionActivity: CreateQuestionActivity)
    fun inject(answerQuestionActivity: AnswerQuestionActivity)
}
