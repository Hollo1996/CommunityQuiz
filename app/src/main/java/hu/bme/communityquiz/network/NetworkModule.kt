package hu.bme.communityquiz.network

import dagger.Module
import dagger.Provides
import hu.bme.communityquiz.network.api.CategoryApi
import hu.bme.communityquiz.network.api.QuestionApi
import hu.bme.communityquiz.network.api.QuizApi
import hu.bme.communityquiz.network.api.ScoreApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.GsonConverterFactory
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }


    @Provides
    @Singleton
    fun provideCategoryApi(client: OkHttpClient): CategoryApi {
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(NetworkConfig.TOKEN_ENDPOINT_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(CategoryApi::class.java)
    }


    @Provides
    @Singleton
    fun provideQuestionApi(client: OkHttpClient): QuestionApi {
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(NetworkConfig.TOKEN_ENDPOINT_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(QuestionApi::class.java)
    }


    @Provides
    @Singleton
    fun provideQuizApi(client: OkHttpClient): QuizApi {
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(NetworkConfig.TOKEN_ENDPOINT_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(QuizApi::class.java)
    }


    @Provides
    @Singleton
    fun provideScoreApi(client: OkHttpClient): ScoreApi {
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(NetworkConfig.TOKEN_ENDPOINT_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(ScoreApi::class.java)
    }
}
