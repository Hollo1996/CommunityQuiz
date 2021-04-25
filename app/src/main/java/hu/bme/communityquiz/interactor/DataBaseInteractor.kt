package hu.bme.communityquiz.interactor

import android.content.Context
import androidx.room.Room
import hu.bme.communityquiz.model.QuizDataBase
import hu.bme.communityquiz.model.score.Score
import javax.inject.Inject
import kotlin.concurrent.thread

class DataBaseInteractor @Inject constructor(private val applicationContext: Context) {
    private val db = Room.databaseBuilder(
        applicationContext,
        QuizDataBase::class.java, "database-name"
    ).build()

    fun saveScore(value: Score) {
        val scores = db.scoreDao().getAll()
        if (scores.any { it.category?.compareTo(value.category ?: "") == 0 }) {
            val oldScore = scores.first { it.category?.compareTo(value.category ?: "") == 0 }
            oldScore.point = value.point
            db.scoreDao().update(oldScore)
        } else
            db.scoreDao().insertAll(value)
    }

    fun loadScores(): List<Score> {
        return db.scoreDao().getAll()
    }

}