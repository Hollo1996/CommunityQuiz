package hu.bme.communityquiz.ui.create_question

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import hu.bme.communityquiz.R
import hu.bme.communityquiz.injector
import hu.bme.communityquiz.model.question.Question
import kotlinx.android.synthetic.main.activity_create_question.*
import javax.inject.Inject
import kotlin.concurrent.thread

class CreateQuestionActivity : AppCompatActivity(), CreateQuestionScreen {

    @Inject
    lateinit var createQuestionPresenter: CreateQuestionPresenter
    lateinit var question: Question

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_question)
        injector.inject(this)
        question = Question()
        categoryField.addTextChangedListener {
            question.category = categoryField.text.toString()
        }
        questionField.addTextChangedListener {
            question.question = questionField.text.toString()
        }
        correctAnswerField.addTextChangedListener {
            question.rightAnswer = correctAnswerField.text.toString()
        }
        wrongAnswer1Field.addTextChangedListener {
            question.wrongAnswer1 = wrongAnswer1Field.text.toString()
        }
        wrongAnswer2Field.addTextChangedListener {
            question.wrongAnswer2 = wrongAnswer2Field.text.toString()
        }
        wrongAnswer3Field.addTextChangedListener {
            question.wrongAnswer3 = wrongAnswer3Field.text.toString()
        }
        createButton.setOnClickListener {
            thread {
                createQuestionPresenter.saveQuestion(question)
            }.join()
            onBackPressed()
        }
    }

    override fun onStart() {
        super.onStart()
        createQuestionPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        createQuestionPresenter.detachScreen()
    }


    override fun onBackPressed() {
        finish()
        //moveTaskToBack(true)
    }
}