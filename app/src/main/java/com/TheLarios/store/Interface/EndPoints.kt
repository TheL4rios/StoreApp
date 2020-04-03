package com.TheLarios.store.Interface

import com.TheLarios.store.Pojos.ResponseProducts
import retrofit2.Call
import retrofit2.http.GET

interface EndPoints
{
    @GET("list")
    fun getList() : Call<ResponseProducts>
}