package hu.bme.communityquiz.test

import hu.bme.communityquiz.testInjector
import hu.bme.communityquiz.ui.choose_category.ChooseCategoryPresenter
import hu.bme.communityquiz.ui.choose_category.ChooseCategoryScreen
import hu.bme.communityquiz.ui.score.ScorePresenter
import hu.bme.communityquiz.ui.score.ScoreScreen
import hu.bme.communityquiz.utils.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject

@RunWith(RobolectricTestRunner::class)
class ChooseCategoryTest {
    @Inject
    lateinit var presenter: ChooseCategoryPresenter
    private lateinit var screen: ChooseCategoryScreen

    @Throws(Exception::class)
    @Before
    fun setup() {
        testInjector.inject(this)
        screen = mock()
        presenter.attachScreen(screen)
    }

    @Test
    fun listCategories(){
        val categories = presenter.loadCategories().filterNotNull()
        assert(categories.isNotEmpty())
        assert(categories.all { it.isNotBlank() })
    }

    @After
    fun tearDown() {
        presenter.detachScreen()
    }
}