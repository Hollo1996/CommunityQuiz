package hu.bme.communityquiz.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hu.bme.communityquiz.model.question.*
import hu.bme.communityquiz.model.score.*

@Database(entities = [Question::class, Score::class], version = 3)
@TypeConverters(BigDecimalToIntConverter::class)
abstract class QuizDataBase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun scoreDao(): ScoreDao
}
