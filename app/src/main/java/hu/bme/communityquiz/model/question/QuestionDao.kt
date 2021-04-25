package hu.bme.communityquiz.model.question

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuestionDao {
    @Query("SELECT * FROM question")
    fun getAll(): List<Question>

    @Query("SELECT * FROM question WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<Question>

    @Insert
    fun insertAll(vararg scores: Question)

    @Delete
    fun delete(score: Question)
}