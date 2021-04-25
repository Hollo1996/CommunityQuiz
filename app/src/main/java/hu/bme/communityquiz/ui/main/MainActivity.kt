package hu.bme.communityquiz.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.communityquiz.R
import hu.bme.communityquiz.injector
import hu.bme.communityquiz.ui.choose_category.ChooseCategoryActivity
import hu.bme.communityquiz.ui.create_question.CreateQuestionActivity
import hu.bme.communityquiz.ui.score.ScoreActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainScreen {

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injector.inject(this)
        start_button.setOnClickListener {
            val intent = Intent(this, ChooseCategoryActivity::class.java)
            startActivity(intent)
        }
        createButton.setOnClickListener {
            val intent = Intent(this, CreateQuestionActivity::class.java)
            startActivity(intent)
        }
        score_button.setOnClickListener {
            val intent = Intent(this, ScoreActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        mainPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.detachScreen()
    }
}