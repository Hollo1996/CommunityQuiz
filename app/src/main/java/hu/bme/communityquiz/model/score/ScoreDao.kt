package hu.bme.communityquiz.model.score

import androidx.room.*

@Dao
interface ScoreDao {
    @Query("SELECT * FROM score")
    fun getAll(): List<Score>

    @Query("SELECT * FROM score WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<Score>

    @Query("SELECT * FROM score WHERE category LIKE :category")
    fun findByCategory(category: String): Score

    @Insert
    fun insertAll(vararg scores: Score)

    @Update
    fun update( scores: Score)

    @Delete
    fun delete(score: Score)
}