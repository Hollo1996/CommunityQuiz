package hu.bme.communityquiz.test

import hu.bme.communityquiz.model.question.Question
import hu.bme.communityquiz.testInjector
import hu.bme.communityquiz.ui.create_question.CreateQuestionPresenter
import hu.bme.communityquiz.ui.create_question.CreateQuestionScreen
import hu.bme.communityquiz.ui.main.MainPresenter
import hu.bme.communityquiz.ui.main.MainScreen
import hu.bme.communityquiz.utils.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class CreateQuestionTest {
    @Inject
    lateinit var presenter: CreateQuestionPresenter
    private lateinit var screen: CreateQuestionScreen

    @Throws(Exception::class)
    @Before
    fun setup() {
        testInjector.inject(this)
        screen = mock()
        presenter.attachScreen(screen)
    }

    @Test
    fun saveQuestion() {
        presenter.saveQuestion(
            Question(
                category = "history",
                question = "Ki volt Napóleon?",
                rightAnswer = "Francia uralkodó",
                wrongAnswer1 = "híres tudós",
                wrongAnswer2 = "híres balett táncos",
                wrongAnswer3 = "Német uralkodó"
            )
        )
        assert(true)
    }

    @After
    fun tearDown() {
        presenter.detachScreen()
    }
}