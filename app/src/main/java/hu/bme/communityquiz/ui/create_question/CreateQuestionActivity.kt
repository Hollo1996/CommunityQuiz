package hu.bme.communityquiz.ui.create_question

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.communityquiz.R
import hu.bme.communityquiz.injector
import hu.bme.communityquiz.ui.main.MainPresenter
import javax.inject.Inject

class CreateQuestionActivity : AppCompatActivity(), CreateQuestionScreen {

    @Inject
    lateinit var createQuestionPresenter: CreateQuestionPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_question)
        injector.inject(this)
    }

    override fun onStart() {
        super.onStart()
        createQuestionPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        createQuestionPresenter.detachScreen()
    }
}