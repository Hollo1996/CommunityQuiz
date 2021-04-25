package hu.bme.communityquiz.model.question

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.swagger.annotations.ApiModel
import com.google.gson.annotations.SerializedName
import io.swagger.annotations.ApiModelProperty
import java.lang.StringBuilder
import java.util.*

@Entity
@ApiModel(description = "")
data class Question(
    /**
     */
    @PrimaryKey
    @get:ApiModelProperty(required = true, value = "")
    @SerializedName("id")
    var id: String = UUID.randomUUID().toString(),

    /**
     */
    @get:ApiModelProperty(required = true, value = "")
    @SerializedName("category")
    var category: String? = null,

    /**
     */
    @get:ApiModelProperty(required = true, value = "")
    @SerializedName("question")
    var question: String? = null,

    /**
     */
    @get:ApiModelProperty(required = true, value = "")
    @SerializedName("rightAnswer")
    @ColumnInfo(name = "right_answer")
    var rightAnswer: String? = null,

    /**
     */
    @get:ApiModelProperty(required = true, value = "")
    @SerializedName("wrongAnswer1")
    @ColumnInfo(name = "wrong_answer_1")
    var wrongAnswer1: String? = null,

    /**
     */
    @get:ApiModelProperty(required = true, value = "")
    @SerializedName("wrongAnswer2")
    @ColumnInfo(name = "wrong_answer_2")
    var wrongAnswer2: String? = null,

    /**
     */
    @get:ApiModelProperty(required = true, value = "")
    @SerializedName("wrongAnswer3")
    @ColumnInfo(name = "wrong_answer_3")
    var wrongAnswer3: String? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val question = other as Question
        return id == question.id &&
                category == question.category &&
                this.question == question.question &&
                rightAnswer == question.rightAnswer &&
                wrongAnswer1 == question.wrongAnswer1 &&
                wrongAnswer2 == question.wrongAnswer2 &&
                wrongAnswer3 == question.wrongAnswer3
    }

    override fun hashCode(): Int {
        return Objects.hash(
            id,
            category,
            question,
            rightAnswer,
            wrongAnswer1,
            wrongAnswer2,
            wrongAnswer3
        )
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Question {\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
        sb.append("    category: ").append(toIndentedString(category)).append("\n")
        sb.append("    question: ").append(toIndentedString(question)).append("\n")
        sb.append("    rightAnswer: ").append(toIndentedString(rightAnswer)).append("\n")
        sb.append("    wrongAnswer1: ").append(toIndentedString(wrongAnswer1)).append("\n")
        sb.append("    wrongAnswer2: ").append(toIndentedString(wrongAnswer2)).append("\n")
        sb.append("    wrongAnswer3: ").append(toIndentedString(wrongAnswer3)).append("\n")
        sb.append("}")
        return sb.toString()
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private fun toIndentedString(o: Any?): String {
        return o?.toString()?.replace("\n", "\n    ") ?: "null"
    }
}