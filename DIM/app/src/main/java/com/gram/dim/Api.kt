package com.gram.dim

import com.gram.dim.Model.SelectHistoricalSiteModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface Api {
    @GET("main/{area}")
    @Headers("Content-Type: application/json")
    fun getSites(@Path("area") area: String): Call<ArrayList<SelectHistoricalSiteModel>>

}
