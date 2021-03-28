package hu.bme.communityquiz.network.api

import retrofit2.Call
import retrofit2.http.GET

interface CategoryApi {
    /**
     * Get list of categories
     *
     * @return Call<List></List><String>>
    </String> */
    @get:GET("category")
    val categories: Call<List<String?>?>?
}