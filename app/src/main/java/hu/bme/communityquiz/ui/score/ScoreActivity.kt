package hu.bme.communityquiz.ui.score

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.communityquiz.R
import hu.bme.communityquiz.injector
import hu.bme.communityquiz.ui.main.MainPresenter
import javax.inject.Inject

class ScoreActivity : AppCompatActivity(),ScoreScreen {

    @Inject
    lateinit var scorePresenter: ScorePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        injector.inject(this)
    }

    override fun onStart() {
        super.onStart()
        scorePresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        scorePresenter.detachScreen()
    }
}