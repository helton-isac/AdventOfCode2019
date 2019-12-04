package com.hitg.adventofcode.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hitg.adventofcode.R
import kotlinx.android.synthetic.main.main_fragment.view.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var mainList: RecyclerView

    val data: List<String> = listOf("Day 01", "Day 02", "Day 03")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        mainList = view.mainList
        mainList.adapter = MainListAdapter(onClickListener, data)
        mainList.layoutManager = LinearLayoutManager(this.context)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    val onClickListener: View.OnClickListener = View.OnClickListener {
        val itemPosition = mainList.getChildLayoutPosition(it)
        val item = data.get(itemPosition)
        Toast.makeText(this.context, item, Toast.LENGTH_LONG).show()
    }

}
