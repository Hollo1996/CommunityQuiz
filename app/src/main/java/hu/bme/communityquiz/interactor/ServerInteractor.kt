package hu.bme.communityquiz.interactor

import hu.bme.communityquiz.model.question.Question
import hu.bme.communityquiz.model.score.Score
import hu.bme.communityquiz.network.api.QuizApi
import javax.inject.Inject

class ServerInteractor @Inject constructor(
    private val quizApi: QuizApi,
) {
    fun getCategories() = quizApi.getCategories()?.execute()?.body()
            ?: throw NullPointerException("No call was returned")

    fun getQuiz(category: String) = quizApi.getQuiz(category)?.execute()?.body()
            ?: throw NullPointerException("No call was returned")

    fun saveQuestion(value: Question) = quizApi.saveQuestion(value)?.execute()?.body()

    fun saveScore(value: Score) = quizApi.saveScore(value)?.execute()?.body()

    fun listGlobalScores() = quizApi.listGlobalScores()?.execute()?.body()
            ?: throw NullPointerException("No call was returned")
}