package com.gram.dim.Connector

import com.gram.dim.Model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface Api {
    @GET("main/{area}")
    @Headers("Content-Type: application/json")
    fun getSites(@Path("area") area: String): Call<ArrayList<SelectHistoricalSiteModel>>

    @GET("map/{history_site_name}")
    @Headers("Content-Type: application/json")
    fun getMapLocation(@Path("history_site_name") histroySiteName : String): Call<InfoOfPlaceModel>

    @GET("main/{area}/{history_site_name}")
    @Headers("Content-Type: application/json")
    fun getSiteInform(@Path("area") area: String, @Path("history_site_name") historySiteName: String): Call<HistoricalSiteInformModel>

    @GET("quiz/{area}")
    @Headers("Content-Type: application/json")
    fun getQuiz(@Path("area") area: String): Call<TestModel>

    @GET("vr/image/{site_code}")
    @Headers("Content-Type: application/json")
    fun getVRImage(@Path("site_code") histroy_site_code : String) : Call<VRImageModel>

}
