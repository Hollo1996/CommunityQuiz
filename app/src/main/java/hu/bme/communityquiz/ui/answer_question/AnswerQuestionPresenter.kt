package hu.bme.communityquiz.ui.answer_question

import hu.bme.communityquiz.interactor.DataBaseInteractor
import hu.bme.communityquiz.interactor.LogicInteractor
import hu.bme.communityquiz.interactor.ServerInteractor
import hu.bme.communityquiz.interactor.ServerInteractor_Factory
import hu.bme.communityquiz.model.question.Question
import hu.bme.communityquiz.model.score.Score
import hu.bme.communityquiz.ui.Presenter
import javax.inject.Inject
import kotlin.concurrent.thread

class AnswerQuestionPresenter @Inject constructor(
        private val logicInteractor: LogicInteractor,
        private val dataBaseInteractor: DataBaseInteractor,
        private val serverInteractor: ServerInteractor
) : Presenter<AnswerQuestionScreen>() {

    fun getCurrentQuestion(): Question = logicInteractor.getCurrentQuestion()

    fun getLives():Int = logicInteractor.getLives()

    fun getCorrectAnswerCount():Int = logicInteractor.getCorrectAnswerCount()

    fun isGameFinished():Boolean = logicInteractor.isGameFinished()

    fun chooseAnswer(text:String) = logicInteractor.chooseAnswer(text)

    fun nextQuestion(): Question = logicInteractor.nextQuestion()

    fun endQuiz(): Score = logicInteractor.endQuiz()

    fun saveScore(score: Score) = thread {
        dataBaseInteractor.saveScore(score)
        serverInteractor.saveScore(score)
    }
}