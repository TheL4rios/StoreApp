package com.TheLarios.store.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.TheLarios.store.Data.Item
import com.TheLarios.store.R
import kotlinx.android.synthetic.main.car_list.view.*

class AdapterCarList(val data : List<Item>) : RecyclerView.Adapter<AdapterCarList.Holder>()
{
    class Holder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        fun bindView(item : Item)
        {
            with(item)
            {
                itemView.txtTitleList.text = title
                itemView.txtDescriptionList.text = description
                itemView.txtPriceList.text = "$ ${String.format("%.2f", price)}"
            }
        }
    }


    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindView(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Holder(
            layoutInflater.inflate(
                R.layout.car_list,
                parent,
                false
            )
        )
    }
}