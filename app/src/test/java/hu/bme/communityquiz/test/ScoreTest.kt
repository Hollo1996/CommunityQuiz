package hu.bme.communityquiz.test

import hu.bme.communityquiz.model.score.Score
import hu.bme.communityquiz.testInjector
import hu.bme.communityquiz.ui.main.MainPresenter
import hu.bme.communityquiz.ui.main.MainScreen
import hu.bme.communityquiz.ui.score.ScorePresenter
import hu.bme.communityquiz.ui.score.ScoreScreen
import hu.bme.communityquiz.utils.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import javax.inject.Inject
import kotlin.concurrent.thread

@RunWith(RobolectricTestRunner::class)
class ScoreTest {
    @Inject
    lateinit var presenter: ScorePresenter
    private lateinit var screen: ScoreScreen
    private lateinit var global: List<Score>

    @Throws(Exception::class)
    @Before
    fun setup() {
        testInjector.inject(this)
        screen = mock()
        presenter.attachScreen(screen)
    }

    @Test
    fun listGlobalScore() {
        global = presenter.loadGlobalScores().filterNotNull()
        assert(global.isNotEmpty())
        assert(global.all { it.category != null && it.category!!.isNotBlank() })
        assert(global.all { it.point != null && it.point!!.toInt() >= 0 })
    }

    @Test
    fun listLocalScores() {
        thread {
            val local = presenter.loadLocalScores()
            assert(local.all { it.category != null && it.category!!.isNotBlank() })
            assert(local.all { it.point != null && it.point!!.toInt() >= 0 })
            assert(local.all { global.any { g -> g.category?.compareTo(it.category ?: "") == 0 } })
        }.join()
    }

    @After
    fun tearDown() {
        presenter.detachScreen()
    }
}