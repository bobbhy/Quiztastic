package tech.eilco.quiztastic

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("questions")
    fun getData(@Query("limit") limit:Int,@Query("categories") category: String?):Call<List<Question>>
}