package com.mrhi2021.kotlinbnvfragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter constructor(var context:Context, var items:List<ShoppingItem>) : RecyclerView.Adapter<MyAdapter.VH>(){

    inner class VH constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvTitle:TextView= itemView.findViewById(R.id.tv_title)
        val tvBrand:TextView= itemView.findViewById(R.id.tv_brand)
        val iv:ImageView= itemView.findViewById(R.id.iv)

        fun bind(item:ShoppingItem){
            tvTitle.text= item.title
            tvBrand.text= item.maker
            Glide.with(context).load(item.image).into(iv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView:View= LayoutInflater.from(context).inflate(R.layout.recycler_item, parent,false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}