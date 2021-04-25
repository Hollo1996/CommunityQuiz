package hu.bme.communityquiz.ui.choose_category

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import hu.bme.communityquiz.R
import hu.bme.communityquiz.injector
import hu.bme.communityquiz.ui.answer_question.AnswerQuestionActivity
import hu.bme.communityquiz.ui.score.ScoreActivity
import kotlinx.android.synthetic.main.activity_choose_category.*
import javax.inject.Inject
import kotlin.concurrent.thread

class ChooseCategoryActivity : AppCompatActivity(), ChooseCategoryScreen,
    AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var chooseCategoryPresenter: ChooseCategoryPresenter
    lateinit var categories : List<String>
    var selectedCategory = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_category)
        injector.inject(this)
        thread {
            categories = chooseCategoryPresenter.loadCategories().filterNotNull()
            //categories = listOf("history", "sports", "art")
            if (categories.isNotEmpty())
                selectedCategory = categories[0]

        }.join()
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            categories
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = this

        choose_button.setOnClickListener {
            thread {
                chooseCategoryPresenter.setCategory(selectedCategory)
            }.join()
            val intent = Intent(this, AnswerQuestionActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStart() {
        super.onStart()
        chooseCategoryPresenter.attachScreen(this)
    }

    override fun onStop() {
        super.onStop()
        chooseCategoryPresenter.detachScreen()
    }

    override fun onBackPressed() {
        finish()
        //moveTaskToBack(true)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedCategory =
            if (categories.size > position)
                categories[position]
            else
                ""
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        selectedCategory =
            if (categories.isNotEmpty())
                categories[0]
            else
                ""
    }
}