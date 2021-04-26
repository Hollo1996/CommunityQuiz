package hu.bme.communityquiz.test

import hu.bme.communityquiz.interactor.DataBaseInteractor
import hu.bme.communityquiz.interactor.LogicInteractor
import hu.bme.communityquiz.model.score.Score
import hu.bme.communityquiz.testInjector
import hu.bme.communityquiz.ui.answer_question.AnswerQuestionPresenter
import hu.bme.communityquiz.ui.answer_question.AnswerQuestionScreen
import hu.bme.communityquiz.utils.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.math.BigDecimal
import javax.inject.Inject
import kotlin.concurrent.thread

@RunWith(RobolectricTestRunner::class)
class AnswerQuestionTest {
    @Inject
    lateinit var presenter: AnswerQuestionPresenter

    @Inject
    lateinit var logicInteractor: LogicInteractor
    lateinit var databaseInteractor: DataBaseInteractor
    private lateinit var screen: AnswerQuestionScreen

    @Throws(Exception::class)
    @Before
    fun setup() {
        testInjector.inject(this)
        screen = mock()
        presenter.attachScreen(screen)
        logicInteractor.loadQuestions("base")
    }

    @Test
    fun getQuestion() {
        val question = presenter.getCurrentQuestion()
        assert(question.category?.compareTo("base") == 0)
        assert(
            question.category?.isBlank() == false &&
                    question.question?.isBlank() == false &&
                    question.rightAnswer?.isBlank() == false &&
                    question.wrongAnswer1?.isBlank() == false &&
                    question.wrongAnswer2?.isBlank() == false &&
                    question.wrongAnswer3?.isBlank() == false
        )
        assert(!presenter.isGameFinished())
    }

    @Test
    fun getNextQuestion() {
        val question = presenter.nextQuestion()
        assert(question.category?.compareTo("base") == 0)
        assert(
            question.category?.isBlank() == false &&
                    question.question?.isBlank() == false &&
                    question.rightAnswer?.isBlank() == false &&
                    question.wrongAnswer1?.isBlank() == false &&
                    question.wrongAnswer2?.isBlank() == false &&
                    question.wrongAnswer3?.isBlank() == false
        )
        assert(presenter.getLives() == 3)
        assert(presenter.getCorrectAnswerCount() == 0)
        assert(!presenter.isGameFinished())
    }

    @Test
    fun chooseRightAnswer() {
        val question = presenter.getCurrentQuestion()
        assert(question.category?.compareTo("base") == 0)
        assert(
            question.category?.isBlank() == false &&
                    question.question?.isBlank() == false &&
                    question.rightAnswer?.isBlank() == false &&
                    question.wrongAnswer1?.isBlank() == false &&
                    question.wrongAnswer2?.isBlank() == false &&
                    question.wrongAnswer3?.isBlank() == false
        )
        presenter.chooseAnswer(question.rightAnswer ?: "")
        assert(presenter.getLives() == 3)
        assert(presenter.getCorrectAnswerCount() == 1)
        assert(!presenter.isGameFinished())
    }

    @Test
    fun chooseWrongAnswer() {
        val question = presenter.nextQuestion()
        assert(question.category?.compareTo("base") == 0)
        assert(
            question.category?.isBlank() == false &&
                    question.question?.isBlank() == false &&
                    question.rightAnswer?.isBlank() == false &&
                    question.wrongAnswer1?.isBlank() == false &&
                    question.wrongAnswer2?.isBlank() == false &&
                    question.wrongAnswer3?.isBlank() == false
        )
        presenter.chooseAnswer(question.wrongAnswer1 ?: "")
        assert(presenter.getLives() == 2)
        assert(presenter.getCorrectAnswerCount() == 0)
        assert(!presenter.isGameFinished())
    }

    @Test
    fun endGame() {
        presenter.nextQuestion()
        presenter.nextQuestion()
        assert(presenter.isGameFinished())
    }

    @Test
    fun saveScore() {
        presenter.saveScore(
            Score(category = "base", point = BigDecimal(1))
        )
        thread {
            val scores = databaseInteractor.loadScores()
            assert(scores.filter { it.category?.compareTo("base") == 0 }.size == 1)
            assert(scores.first { it.category?.compareTo("base") == 0 }.point?.toInt() == 1)
        }.join()
    }

    @After
    fun tearDown() {
        presenter.detachScreen()
    }
}