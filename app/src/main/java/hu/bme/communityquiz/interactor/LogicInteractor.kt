package hu.bme.communityquiz.interactor

import hu.bme.communityquiz.model.Question
import hu.bme.communityquiz.model.Score
import javax.inject.Inject

class LogicInteractor @Inject constructor(private val serverInteractor: ServerInteractor) {
    fun loadQuestions(category:String){
        serverInteractor.getQuiz(category)
        TODO()
    }
    fun getCurrentQuestion():Question{
        TODO()
    }
    fun getLives():Int{
        TODO()
    }
    fun getCorrectAnswerCount():Int{
        TODO()
    }
    fun isGameFinished():Boolean{
        TODO()
    }
    fun chooseAnswer(index:Int){
        TODO()
    }
    fun nextQuestion():Question{
        TODO()
    }
    fun endQuiz(): Score {
        TODO()
    }
}