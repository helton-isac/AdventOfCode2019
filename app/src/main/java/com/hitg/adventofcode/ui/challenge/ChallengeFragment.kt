package com.hitg.adventofcode.ui.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.hitg.adventofcode.R


class ChallengeFragment : Fragment() {

    companion object {
        fun newInstance() = ChallengeFragment()
    }

    private lateinit var viewModel: ChallengeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args = arguments
        val day = args?.getInt("day", 0)
        val title = args?.getString("title", "")
        val hasFirstStar = args?.getBoolean("hasFirstStar", false) ?: false
        val hasSecondStar = args?.getBoolean("hasSecondStar", false) ?: false

        val view = inflater.inflate(R.layout.challenge_fragment, container, false)
        val txtDay = view.findViewById<TextView>(R.id.txt_day)
        val txtTitle = view.findViewById<TextView>(R.id.challenge_title)
        txtDay.text = String.format("Day %d", day)
        txtTitle.text = title

        val imgFirstStar = view.findViewById<ImageView>(R.id.img_star_1)
        val imgSecondStar = view.findViewById<ImageView>(R.id.img_star_1)
        if (hasFirstStar) {
            imgFirstStar.setImageResource(R.drawable.ic_star_gold_24dp)
            imgFirstStar.contentDescription =
                imgFirstStar.context.getString(R.string.first_star_done)

        } else {
            imgFirstStar.setImageResource(R.drawable.ic_star_black_24dp)
            imgFirstStar.contentDescription =
                imgFirstStar.context.getString(R.string.empty_first_star)
        }
        if (hasSecondStar) {
            imgSecondStar.setImageResource(R.drawable.ic_star_gold_24dp)
            imgSecondStar.contentDescription =
                imgSecondStar.context.getString(R.string.second_star_done)
        } else {
            imgSecondStar.setImageResource(R.drawable.ic_star_black_24dp)
            imgSecondStar.contentDescription =
                imgSecondStar.context.getString(R.string.empty_second_star)
        }




        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ChallengeViewModel::class.java)
    }

}
