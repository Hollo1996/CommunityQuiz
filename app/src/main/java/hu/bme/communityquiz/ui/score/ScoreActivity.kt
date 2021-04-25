package hu.bme.communityquiz.ui.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.communityquiz.R
import hu.bme.communityquiz.injector
import hu.bme.communityquiz.model.score.Score
import kotlinx.android.synthetic.main.activity_score.*
import java.math.BigDecimal
import javax.inject.Inject
import kotlin.concurrent.thread

class ScoreActivity : AppCompatActivity(), ScoreScreen {

    @Inject
    lateinit var scorePresenter: ScorePresenter
    lateinit var data: List<Pair<Score, Score>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        injector.inject(this)
        thread {
            val global = scorePresenter.loadGlobalScores().filterNotNull()
            val local = scorePresenter.loadLocalScores()
            data = List(global.size) { index ->
                Pair(
                    local.find { it.category?.compareTo(global[index].category ?: "") == 0 }
                        ?: Score( category = global[index].category , point = BigDecimal(0)),
                    global[index]
                )
            }
        }.join()
        scoreList.adapter = ScoreAdapter(this, data)
    }

    override fun onStart() {
        super.onStart()
        scorePresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        scorePresenter.detachScreen()
    }

    override fun onBackPressed() {
        finish()
        //moveTaskToBack(true)
    }
}