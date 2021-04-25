package hu.bme.communityquiz.ui.answer_question

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import hu.bme.communityquiz.R
import hu.bme.communityquiz.injector
import hu.bme.communityquiz.model.question.Question
import hu.bme.communityquiz.model.score.Score
import kotlinx.android.synthetic.main.activity_answer_question.*
import java.math.BigDecimal
import javax.inject.Inject

class AnswerQuestionActivity : AppCompatActivity(), AnswerQuestionScreen {

    @Inject
    lateinit var answerQuestionPresenter: AnswerQuestionPresenter
    lateinit var question: Question
    private var chosenAnswer = ""
    private var answers = listOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer_question)
        injector.inject(this)
        createButton.setOnClickListener {
            answerQuestionPresenter.chooseAnswer(chosenAnswer)
            nextTurn()
        }
        question = answerQuestionPresenter.getCurrentQuestion()
        answer1.setOnClickListener(::onRadioButtonClicked)
        answer2.setOnClickListener(::onRadioButtonClicked)
        answer3.setOnClickListener(::onRadioButtonClicked)
        answer4.setOnClickListener(::onRadioButtonClicked)
        updateText()
    }

    private fun nextTurn() {
        answerQuestionPresenter.run {
            if (isGameFinished()) {
                updateText()
                saveScore(
                    Score(
                        category = question.category,
                        point = BigDecimal(getCorrectAnswerCount())
                    )
                )
                onBackPressed()
            } else {
                question = nextQuestion()
                updateText()
            }
        }

    }

    fun updateText() {
        answerQuestionPresenter.run {
            lifeText.text = getLives().toString()
            pointText.text = getCorrectAnswerCount().toString()
            questionText.text = question.question
            answers = listOf(
                question.rightAnswer ?: "",
                question.wrongAnswer1 ?: "",
                question.wrongAnswer2 ?: "",
                question.wrongAnswer3 ?: ""
            )
            chosenAnswer = question.wrongAnswer1 ?: ""
            val answersShuffled = answers.shuffled()
            answer1.text = answersShuffled[0]
            answer2.text = answersShuffled[1]
            answer3.text = answersShuffled[2]
            answer4.text = answersShuffled[3]
        }
    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked
            if (checked)
                chosenAnswer = view.text.toString()
        }
    }


    override fun onStart() {
        super.onStart()
        answerQuestionPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        answerQuestionPresenter.detachScreen()
    }

    override fun onBackPressed() {
        answerQuestionPresenter.endQuiz()
        finish()
        //moveTaskToBack(true)
    }
}