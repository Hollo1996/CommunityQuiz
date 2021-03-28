package hu.bme.communityquiz.network.api

import hu.bme.communityquiz.model.Question
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QuizApi {
    /**
     * Get list of random questions of category
     *
     * @param category
     * @return Call<List></List><Question>>
    </Question> */
    @GET("quiz/{category}")
    fun getQuiz(
        @Path("category") category: String?
    ): Call<List<Question?>?>?
}