package hu.bme.communityquiz.interactor

import dagger.Module
import dagger.Provides
import hu.bme.communityquiz.network.api.CategoryApi
import hu.bme.communityquiz.network.api.QuestionApi
import hu.bme.communityquiz.network.api.QuizApi
import hu.bme.communityquiz.network.api.ScoreApi
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun provideDataBaseInteractor() = DataBaseInteractor()

    @Provides
    @Singleton
    fun provideServerInteractor(
            categoryApi: CategoryApi,
            quizApi: QuizApi,
            questionApi: QuestionApi,
            scoreApi: ScoreApi
    ) = ServerInteractor(categoryApi,quizApi,questionApi,scoreApi)

    @Provides
    @Singleton
    fun provideLogicInteractor() = LogicInteractor()

}
