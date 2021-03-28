package hu.bme.communityquiz.ui.answer_question

import hu.bme.communityquiz.interactor.DataBaseInteractor
import hu.bme.communityquiz.interactor.LogicInteractor
import hu.bme.communityquiz.model.Question
import hu.bme.communityquiz.model.Score
import hu.bme.communityquiz.network.NetworkModule
import hu.bme.communityquiz.ui.Presenter
import javax.inject.Inject

class AnswerQuestionPresenter @Inject constructor(
        private val logicInteractor: LogicInteractor,
        private val dataBaseInteractor: DataBaseInteractor
) : Presenter<AnswerQuestionScreen>() {

    fun getCurrentQuestion(): Question = logicInteractor.getCurrentQuestion()

    fun getLives():Int = logicInteractor.getLives()

    fun getCorrectAnswerCount():Int = logicInteractor.getCorrectAnswerCount()

    fun isGameFinished():Boolean = logicInteractor.isGameFinished()

    fun chooseAnswer(index:Int) = logicInteractor.chooseAnswer(index)

    fun nextQuestion(): Question = logicInteractor.nextQuestion()

    fun endQuiz(): Score = logicInteractor.endQuiz()

    fun saveScore(score:Score) = dataBaseInteractor.saveScore(score)
}