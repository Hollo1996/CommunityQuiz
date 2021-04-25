package hu.bme.communityquiz.interactor

import android.content.Context
import dagger.Module
import dagger.Provides
import hu.bme.communityquiz.network.api.QuizApi
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun provideDataBaseInteractor(applicationContext: Context) = DataBaseInteractor(applicationContext)

    @Provides
    @Singleton
    fun provideServerInteractor(
            quizApi: QuizApi,
    ) = ServerInteractor(quizApi)

    @Provides
    @Singleton
    fun provideLogicInteractor(serverInteractor: ServerInteractor) = LogicInteractor(serverInteractor)

}
