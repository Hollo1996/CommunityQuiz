package hu.bme.communityquiz.mock

import hu.bme.communityquiz.model.question.Question
import hu.bme.communityquiz.model.score.Score
import hu.bme.communityquiz.network.api.QuizApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.math.BigDecimal


class MockQuizApi2 : QuizApi {

    override fun getCategories(): Call<List<String?>?> {

        return object : Call<List<String?>?> {
            @Throws(IOException::class)
            override fun execute(): Response<List<String?>?> {
                return Response.success<List<String?>?>(listOf("history"))
            }

            override fun enqueue(callback: Callback<List<String?>?>) {

            }

            override fun isExecuted(): Boolean {
                return false
            }

            override fun cancel() {

            }

            override fun isCanceled(): Boolean {
                return false
            }

            override fun clone(): Call<List<String?>?> {
                return this
            }
        }
    }

    val questions = mutableListOf<Question>()
    override fun saveQuestion(body: Question?): Call<Void?>? {
        if (body != null) {
            questions.add(body)
            return object : Call<Void?> {
                override fun clone(): Call<Void?> = this

                override fun execute(): Response<Void?> {
                    return Response.success<Void?>(null)
                }

                override fun enqueue(callback: Callback<Void?>?) {}

                override fun isExecuted(): Boolean = false

                override fun cancel() {}

                override fun isCanceled(): Boolean = false
            }
        } else
            return null
    }

    override fun getQuiz(category: String?): Call<List<Question?>?>? {
        if (category != null) {
            return object : Call<List<Question?>?> {
                override fun clone(): Call<List<Question?>?> = this

                override fun execute(): Response<List<Question?>?> {
                    return Response.success<List<Question?>?>(questions.filter { it.category?.compareTo(category) == 0 })
                }

                override fun enqueue(callback: Callback<List<Question?>?>?) {}

                override fun isExecuted(): Boolean = false

                override fun cancel() {}

                override fun isCanceled(): Boolean = false
            }
        } else
            return null
    }

    val scores = mutableListOf(Score(category = "base",point = BigDecimal(0)))
    override fun listGlobalScores(): Call<List<Score?>?> {
        return object : Call<List<Score?>?> {
            override fun clone(): Call<List<Score?>?> = this

            override fun execute(): Response<List<Score?>?> {
                return Response.success<List<Score?>?>(scores.toList())
            }

            override fun enqueue(callback: Callback<List<Score?>?>) {}

            override fun isExecuted(): Boolean = false

            override fun cancel() {}

            override fun isCanceled(): Boolean = false
        }
    }

    override fun saveScore(body: Score?): Call<Void?>? {
        if (body != null) {
            if(body.point?.toInt() ?: -1 > scores.find { it.point?.toInt() == body.point?.toInt() }?.point?.toInt() ?: -1){
                scores.add(body)
            }
            return object : Call<Void?> {
                override fun clone(): Call<Void?> = this

                override fun execute(): Response<Void?> {
                    return Response.success<Void?>(null)
                }

                override fun enqueue(callback: Callback<Void?>?) {}

                override fun isExecuted(): Boolean = false

                override fun cancel() {}

                override fun isCanceled(): Boolean = false
            }
        } else
            return null
    }


}
