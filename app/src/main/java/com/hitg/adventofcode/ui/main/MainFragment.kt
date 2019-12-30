package com.hitg.adventofcode.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hitg.adventofcode.R
import com.hitg.adventofcode.domain.model.Challenge
import com.hitg.adventofcode.ui.challenge.ChallengeFragment
import kotlinx.android.synthetic.main.main_fragment.view.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var mainList: RecyclerView

    private lateinit var adapter: MainListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        mainList = view.mainList
        adapter = MainListAdapter(onClickListener)
        mainList.adapter = adapter
        mainList.layoutManager = LinearLayoutManager(this.context)
        mainList.addItemDecoration(
            DividerItemDecoration(mainList.context, DividerItemDecoration.VERTICAL)
        )

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.allChallenges.observe(this, Observer { challenges ->
            challenges?.let { adapter.setChallenges(it) }
        })
    }

    private val onClickListener: View.OnClickListener = View.OnClickListener {
        // TODO: FIX ON CLICK
        val itemPosition = mainList.getChildLayoutPosition(it)
        val item = Challenge(1, "Ops")// data[itemPosition]
        val transaction = this.activity?.supportFragmentManager?.beginTransaction()


        if (transaction != null) {
            val challengeFragment = ChallengeFragment()
            val args = Bundle()
            args.putInt("day", item.day)
            args.putString("title", item.name)
            args.putBoolean("hasFirstStar", item.firstStar)
            args.putBoolean("hasSecondStar", item.secondStar)
            challengeFragment.arguments = args
            transaction.replace(R.id.container, challengeFragment)
            transaction.addToBackStack(null)
            transaction.commit()
            this.activity?.supportFragmentManager?.executePendingTransactions()
        }

    }


}
