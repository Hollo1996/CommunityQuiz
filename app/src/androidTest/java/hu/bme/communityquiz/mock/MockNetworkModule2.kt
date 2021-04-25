package hu.bme.communityquiz.mock


import dagger.Module
import dagger.Provides
import hu.bme.communityquiz.network.api.QuizApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class MockNetworkModule2 {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }
    @Provides
    @Singleton
    fun provideMockQuizApi(): QuizApi = MockQuizApi2()
}
