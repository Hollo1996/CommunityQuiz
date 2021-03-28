package hu.bme.communityquiz.ui.answer_question

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.communityquiz.R
import hu.bme.communityquiz.injector
import hu.bme.communityquiz.interactor.LogicInteractor
import hu.bme.communityquiz.ui.main.MainPresenter
import javax.inject.Inject

class AnswerQuestionActivity : AppCompatActivity(),AnswerQuestionScreen {

    @Inject
    lateinit var answerQuestionPresenter: AnswerQuestionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer_question)
        injector.inject(this)
    }

    override fun onStart() {
        super.onStart()
        answerQuestionPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        answerQuestionPresenter.detachScreen()
    }
}