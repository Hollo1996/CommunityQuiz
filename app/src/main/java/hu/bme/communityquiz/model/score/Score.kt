package hu.bme.communityquiz.model.score

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import hu.bme.communityquiz.model.BigDecimalToIntConverter
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.math.BigDecimal
import java.util.*

@Entity
@ApiModel(description = "")
class Score(
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
    @SerializedName("point")
    var point: BigDecimal? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val score = other as Score
        return id == score.id &&
                category == score.category &&
                point == score.point
    }

    override fun hashCode(): Int {
        return Objects.hash(id, category, point)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Score {\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
        sb.append("    category: ").append(toIndentedString(category)).append("\n")
        sb.append("    point: ").append(toIndentedString(point)).append("\n")
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