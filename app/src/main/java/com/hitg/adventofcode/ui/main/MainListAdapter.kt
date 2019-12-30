package com.hitg.adventofcode.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hitg.adventofcode.R
import com.hitg.adventofcode.domain.model.Challenge
import kotlinx.android.synthetic.main.main_list_item.view.*

class MainListAdapter(
    private val onClickListener: View.OnClickListener
) : RecyclerView.Adapter<MainListAdapter.MainListViewHolder>() {

    private var challenges = emptyList<Challenge>() // Cached copy of words

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MainListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.main_list_item,
            parent, false
        )
        view.setOnClickListener(onClickListener)
        return MainListViewHolder(view)
    }

    override fun getItemCount(): Int = challenges.size

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        val dayChallengeValue = challenges[position]
        holder.setDay(dayChallengeValue.day)
        holder.setText(dayChallengeValue.name)
        holder.setStars(dayChallengeValue.firstStar, dayChallengeValue.secondStar)
    }

    internal fun setChallenges(challenges: List<Challenge>) {
        this.challenges = challenges
        notifyDataSetChanged()
    }

    class MainListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun setText(text: String) {
            itemView.txt_text.text = text
        }

        fun setDay(day: Int) {
            itemView.txt_day.text = String.format("Day %d", day)
        }

        fun setStars(hasFirstStar: Boolean, hasSecondStar: Boolean) {
            if (hasFirstStar) {
                itemView.img_star_1.setImageResource(R.drawable.ic_star_gold_24dp)
                itemView.img_star_1.contentDescription =
                    itemView.context.getString(R.string.first_star_done)

            } else {
                itemView.img_star_1.setImageResource(R.drawable.ic_star_black_24dp)
                itemView.img_star_1.contentDescription =
                    itemView.context.getString(R.string.empty_first_star)
            }
            if (hasSecondStar) {
                itemView.img_star_2.setImageResource(R.drawable.ic_star_gold_24dp)
                itemView.img_star_2.contentDescription =
                    itemView.context.getString(R.string.second_star_done)
            } else {
                itemView.img_star_2.setImageResource(R.drawable.ic_star_black_24dp)
                itemView.img_star_2.contentDescription =
                    itemView.context.getString(R.string.empty_second_star)
            }
        }
    }
}