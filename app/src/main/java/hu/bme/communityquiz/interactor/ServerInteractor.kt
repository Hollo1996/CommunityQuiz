package hu.bme.communityquiz.interactor

import hu.bme.communityquiz.model.Question
import hu.bme.communityquiz.model.Score
import hu.bme.communityquiz.network.api.CategoryApi
import hu.bme.communityquiz.network.api.QuestionApi
import hu.bme.communityquiz.network.api.QuizApi
import hu.bme.communityquiz.network.api.ScoreApi
import java.lang.Error
import javax.inject.Inject

class ServerInteractor @Inject constructor(
        private val categoryApi: CategoryApi,
        private val quizApi: QuizApi,
        private val questionApi: QuestionApi,
        private val scoreApi: ScoreApi
) {
    fun getCategories() = categoryApi.categories?.execute()?.body()
            ?: throw NullPointerException("No call was returned")

    fun getQuiz(category: String) = quizApi.getQuiz(category)?.execute()?.body()
            ?: throw NullPointerException("No call was returned")

    fun saveQuestion(value: Question) = questionApi.saveQuestion(value)?.execute()?.body()
            ?: throw NullPointerException("No call was returned")

    fun saveScore(value: Score) = scoreApi.saveScore(value)?.execute()?.body()
            ?: throw NullPointerException("No call was returned")

    fun listGlobalScores() = scoreApi.listGlobalScores()?.execute()?.body()
            ?: throw NullPointerException("No call was returned")
}