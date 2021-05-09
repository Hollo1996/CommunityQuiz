package hu.bme.communityquiz.ui.main

import android.R.id
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
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

    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "firstTry")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "firstTry")
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Crashes")
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

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