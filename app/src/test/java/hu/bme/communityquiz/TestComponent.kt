package hu.bme.communityquiz

import dagger.Component
import hu.bme.communityquiz.mock.MockNetworkModule
import hu.bme.communityquiz.interactor.InteractorModule
import hu.bme.communityquiz.test.*
import javax.inject.Singleton

@Singleton
@Component(modules = [MockNetworkModule::class, TestModule::class, InteractorModule::class])
interface TestComponent : CommunityQuizApplicationComponent {
    fun inject(answerQuestionTest: AnswerQuestionTest)
    fun inject(chooseCategoryTest: ChooseCategoryTest)
    fun inject(createQuestionTest: CreateQuestionTest)
    fun inject(mainTest: MainTest)
    fun inject(scoreTest: ScoreTest)
}
