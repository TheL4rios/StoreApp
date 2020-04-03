package com.TheLarios.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.TheLarios.store.Adapters.AdapterItem
import com.TheLarios.store.DB.DB
import com.TheLarios.store.Data.Item
import kotlinx.android.synthetic.main.activity_shop_car.*

class ShopCarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_car)
    }

    override fun onResume() {
        super.onResume()

        val db = DB.getInstance(this)

        recyclerViewShopCar.layoutManager = GridLayoutManager(this, 1)

        val array : ArrayList<Item>? = db?.select()

        val itemShop = (0..(array?.size?.minus(1) ?: 1)).map {
            Item(
                "Titulo: ${array?.get(it)?.title}",
                "Descripción: ${array?.get(it)?.description}",
                array?.get(it)?.price ?: 0f,
                array?.get(it)?.urlImg ?: ""
            )
        }

        val adapter = AdapterItem(itemShop)
        recyclerViewShopCar.adapter = adapter
    }
}
