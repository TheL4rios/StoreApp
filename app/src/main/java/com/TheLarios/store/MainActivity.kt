package com.TheLarios.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.TheLarios.store.Adapters.AdapterItem
import com.TheLarios.store.Data.Item
import com.TheLarios.store.Interface.EndPoints
import com.TheLarios.store.Pojos.PayLoadItem
import com.TheLarios.store.Pojos.ResponseProducts
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.93:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val endPoint = retrofit.create(EndPoints::class.java)
        val call = endPoint.getList()
        call.enqueue(object : Callback<ResponseProducts> {
            override fun onFailure(call: Call<ResponseProducts>, t: Throwable) {
                Log.e("Error",t.message.toString())
            }

            override fun onResponse(call: Call<ResponseProducts>, response: Response<ResponseProducts>) {
                if(response.code() == 200)
                {
                    fillRecycler(response.body()?.payLoad)
                }
            }

        })
    }

    private fun fillRecycler(payLoad: List<PayLoadItem?>?)
    {
        val products = payLoad?.map {
            it?.let { product ->
                Item(product.name ?: "", product.description ?: "", product.price?.toFloat() ?: 0f, product.imgUrl ?: "")
            }
        }
        recyclerView.adapter = AdapterItem(products)
    }
}
