package com.hitg.adventofcode.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hitg.adventofcode.R
import com.hitg.adventofcode.domain.model.*
import com.hitg.adventofcode.ui.day01.Day01Fragment
import kotlinx.android.synthetic.main.main_fragment.view.*
import java.util.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var mainList: RecyclerView

    private val data: List<DayChallenge> = createTestList()

    fun createTestList(): List<DayChallenge> {
        val testList = ArrayList<DayChallenge>()
        testList.add(DayChallenge01())
        testList.add(DayChallenge02())
        testList.add(DayChallenge03())
        testList.add(DayChallenge04())
        testList.add(DayChallenge05())
        testList.add(DayChallenge06())
        testList.add(DayChallenge07())
        testList.add(DayChallenge08())
        testList.add(DayChallenge09())
        testList.add(DayChallenge10())
        testList.add(DayChallenge11())
        testList.add(DayChallenge12())
        testList.add(DayChallenge13())
        testList.add(DayChallenge14())
        testList.add(DayChallenge15())
        testList.add(DayChallenge16())
        testList.add(DayChallenge17())
        testList.add(DayChallenge18())
        testList.add(DayChallenge19())
        testList.add(DayChallenge20())
        testList.add(DayChallenge21())
        testList.add(DayChallenge22())
        testList.add(DayChallenge23())
        testList.add(DayChallenge24())
        testList.add(DayChallenge25())


        return testList
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        mainList = view.mainList
        mainList.adapter = MainListAdapter(onClickListener, data)
        mainList.layoutManager = LinearLayoutManager(this.context)
        mainList.addItemDecoration(
            DividerItemDecoration(mainList.context, DividerItemDecoration.VERTICAL)
        )

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    val onClickListener: View.OnClickListener = View.OnClickListener {
        val itemPosition = mainList.getChildLayoutPosition(it)
        val item = data[itemPosition]
        Toast.makeText(this.context, item.getTitle(), Toast.LENGTH_LONG).show()
        val transaction = this.activity?.supportFragmentManager?.beginTransaction()

        if (transaction != null) {
            transaction.replace(R.id.container, Day01Fragment.newInstance())
            transaction.addToBackStack(null)
            transaction.commit()
            this.activity?.supportFragmentManager?.executePendingTransactions()
        }

    }


}
