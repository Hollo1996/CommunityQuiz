package hu.bme.communityquiz.network.api

import hu.bme.communityquiz.model.Score
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface ScoreApi {
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