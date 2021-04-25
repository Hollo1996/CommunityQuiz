package hu.bme.communityquiz.network

import dagger.Module
import dagger.Provides
import hu.bme.communityquiz.network.api.QuizApi
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
    fun provideQuizApi(client: OkHttpClient): QuizApi {
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(NetworkConfig.SERVER_ENDPOINT_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(QuizApi::class.java)
    }
}
