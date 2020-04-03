package com.TheLarios.store

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.TheLarios.store.DB.DB
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_description.*

class DetailActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val db = DB.getInstance(this)

        intent?.extras?.let {
            Glide.with(this).load(it.getString("url")).into(this.imgDetail)
            txtTitleDetail.text = it.getString("title")
            txtDescriptionDetail.text = it.getString("description")
            txtPriceDetail.text = "$ ${String.format("%.2f", it.getFloat("price"))}"
            val url = it.getString("url")
            btnBuy.setOnClickListener {
                db?.insert(txtTitleDetail.text.toString(),
                           txtDescriptionDetail.text.toString(),
                           txtPriceDetail.text.toString().substring(1, txtPriceDetail.text.length-1).toFloat(),
                           url)
                startActivity(Intent(this, ShopCarActivity::class.java))
            }
        }
    }
}
