package hu.bme.communityquiz.network.api

import hu.bme.communityquiz.model.Question
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT

interface QuestionApi {
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
}