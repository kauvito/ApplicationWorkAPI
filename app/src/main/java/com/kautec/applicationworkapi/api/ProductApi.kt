package com.kautec.applicationworkapi.api

import com.kautec.applicationworkapi.Model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductApi {

    @GET("getdata.php")
    fun getProductApi():Call<List<Product>>
}