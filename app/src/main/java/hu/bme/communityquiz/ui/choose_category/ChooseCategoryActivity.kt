package hu.bme.communityquiz.ui.choose_category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.communityquiz.R
import hu.bme.communityquiz.injector
import hu.bme.communityquiz.ui.main.MainPresenter
import hu.bme.communityquiz.ui.main.MainScreen
import javax.inject.Inject

class ChooseCategoryActivity : AppCompatActivity(), ChooseCategoryScreen {

    @Inject
    lateinit var chooseCategoryPresenter: ChooseCategoryPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_category)
        injector.inject(this)
    }

    override fun onStart() {
        super.onStart()
        chooseCategoryPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        chooseCategoryPresenter.detachScreen()
    }
}