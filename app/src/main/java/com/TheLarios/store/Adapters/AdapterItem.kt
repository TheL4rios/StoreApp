package com.TheLarios.store.Adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import com.TheLarios.store.DetailActivity
import com.TheLarios.store.Data.Item
import com.TheLarios.store.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item.view.*

class AdapterItem(val data : List<Item?>?) : RecyclerView.Adapter<AdapterItem.Holder>()
{
    class Holder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        @SuppressLint("SetTextI18n")
        fun bindView(item : Item?)
        {
            item?.let {
                with(it)
                {
                    itemView.txtTitleItem.text = title
                    itemView.txtDescriptionItem.text = description
                    itemView.txtPriceItem.text = "$ ${String.format("%.2f", price)}"

                    Glide.with(itemView.context).load(urlImg).into(itemView.imgItem)

                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailActivity::class.java)
                        intent.putExtra("title", title)
                        intent.putExtra("description", description)
                        intent.putExtra("price", price)
                        intent.putExtra("url", urlImg)

                        val p1 : Pair<View, String> = Pair.create(itemView.imgItem, "transitionImg")
                        val p2 : Pair<View, String> = Pair.create(itemView.txtTitleItem, "transitionTitle")
                        val p3 : Pair<View, String> = Pair.create(itemView.txtDescriptionItem, "transitionDescription")
                        val p4 : Pair<View, String> = Pair.create(itemView.txtPriceItem, "transitionPrice")

                        val options : ActivityOptionsCompat =
                            ActivityOptionsCompat.makeSceneTransitionAnimation(itemView.context as Activity, p1, p2 ,p3, p4)
                        itemView.context.startActivity(intent, options.toBundle())
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder
    {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Holder(
            layoutInflater.inflate(
                R.layout.item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: Holder, position: Int) {
        data?.let {
            holder.bindView(data[position])
        }
    }
}