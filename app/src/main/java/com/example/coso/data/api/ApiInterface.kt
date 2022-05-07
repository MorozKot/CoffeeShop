package com.example.coso.data.api

import com.example.coso.data.models.CoffeeApiModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiInterface {

    @GET("loadCoffee.php")
    fun loadCoffeeApi(): Call<ArrayList<CoffeeApiModel>>

    @FormUrlEncoded
    @POST("insertToServer.php")
    fun insert(
        @Field("nameOrder") name: String?,
        @Field("phoneOrder") phone: String?,
        @Field("descriptionOrder") description: String?,
        @Field("priceOrderOrder") priceOrder: String?,
    ): Call<ResponseBody?>?
}