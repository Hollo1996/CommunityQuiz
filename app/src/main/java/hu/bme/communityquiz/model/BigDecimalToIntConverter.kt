package hu.bme.communityquiz.model

import androidx.room.TypeConverter
import java.math.BigDecimal

class BigDecimalToIntConverter {
    @TypeConverter
    fun fromInt(value: Int?): BigDecimal? {
        return value?.let { BigDecimal(it) }
    }

    @TypeConverter
    fun toInt(bigDecimal: BigDecimal?): Int? {
        return bigDecimal?.toInt()
    }
}