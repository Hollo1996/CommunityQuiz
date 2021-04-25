package hu.bme.communityquiz.network.api

import hu.bme.communityquiz.model.question.Question
import hu.bme.communityquiz.model.score.Score
import retrofit2.Call
import retrofit2.http.*

interface QuizApi {
    /**
     * Get list of categories
     *
     * @return Call<List></List><String>>
    </String> */
    @GET("category")
    fun getCategories(): Call<List<String?>?>?

    /**
     * Save quiz question
     *
     * @param body Updated user object
     * @return Call<Void>
    </Void> */
    @PUT("question")
    fun saveQuestion(
        @Body body: Question?
    ): Call<Void?>?

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

    /**
     * List global scores
     *
     * @return Call<List></List><Score>>
    </Score> */
    @GET("score")
    fun listGlobalScores(): Call<List<Score?>?>?

    /**
     * Save score
     *
     * @param body
     * @return Call<Void>
    </Void> */
    @PUT("score")
    fun saveScore(
        @Body body: Score?
    ): Call<Void?>?
}