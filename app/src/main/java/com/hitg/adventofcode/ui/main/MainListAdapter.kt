package com.hitg.adventofcode.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hitg.adventofcode.R
import kotlinx.android.synthetic.main.main_list_item.view.*

class MainListAdapter : RecyclerView.Adapter<MainListAdapter.MainListViewHolder>() {

    val data: List<String> = listOf("Day 01", "Day 02", "Day 03")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MainListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.main_list_item,
            parent, false
        )
        return MainListViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        holder.setText(data.get(position))
    }

    class MainListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun setText(text: String) {
            itemView.text.text = text
        }
    }
}