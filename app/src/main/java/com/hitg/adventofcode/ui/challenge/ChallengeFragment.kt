package com.hitg.adventofcode.ui.challenge

import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hitg.adventofcode.R
import com.hitg.adventofcode.domain.model.Challenge
import com.hitg.adventofcode.repository.TxtRepo
import kotlinx.android.synthetic.main.challenge_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ChallengeFragment : Fragment() {

    private val day: Int by lazy {
        arguments?.getInt("day", 0) ?: 0
    }

    private lateinit var viewModel: ChallengeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.challenge_fragment, container, false)
    }

    private fun updateView(challenge: Challenge) {
        txt_day.text = "Day " + challenge.day.toString()
        challenge_title.text = challenge.name
        updateImgStar(img_star_1, 1, challenge.firstStar)
        updateImgStar(img_star_2, 2, challenge.secondStar)

        val challengeLink = getString(R.string.adventofcode_link) + day
        imgLink.setOnClickListener {
            val uri = Uri.parse(challengeLink)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        imgPuzzleInput.setOnClickListener {
            if (txtInputWebView.visibility == View.GONE) {
                txtInputWebView.text = readTextFile("input$day")
                txtInputWebView.movementMethod = ScrollingMovementMethod()
                txtInputWebView.visibility = View.VISIBLE
            } else {
                txtInputWebView.visibility = View.GONE
            }
        }

        imgShareChallenge.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Advent of Code 2019")
                val shareMessage = "\nAdvent of Code 2019, day $day\n\n$challengeLink"
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (ignored: Exception) {
            }
        }
        txtFirstAnswer.text = challenge.firstAnswer
        txtSecondAnswer.text = challenge.secondAnswer
        fabRun.setOnClickListener {
            GlobalScope.async {
                viewModel.runChallenge()
            }
        }
    }

    private fun updateImgStar(imgStar: ImageView, starNumber: Int, hasStar: Boolean) {
        if (hasStar) {
            imgStar.setImageResource(R.drawable.ic_star_gold_24dp)
            imgStar.contentDescription =
                if (starNumber == 1)
                    imgStar.context.getString(R.string.first_star_done)
                else
                    imgStar.context.getString(R.string.second_star_done)
        } else {
            imgStar.setImageResource(R.drawable.ic_star_black_24dp)
            imgStar.contentDescription =
                if (starNumber == 1)
                    imgStar.context.getString(R.string.empty_first_star)
                else
                    imgStar.context.getString(R.string.empty_second_star)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = ChallengeViewModelModelFactory(day, this)
        viewModel = ViewModelProviders.of(this, factory)
            .get(ChallengeViewModel::class.java)
        viewModel.challenge.observe(this, Observer { challenge ->
            challenge?.let {
                updateView(it)
            }
        })
    }

    private fun readTextFile(rawFileName: String): String {
        val res: Resources = resources
        val resId = res.getIdentifier(rawFileName, "raw", activity?.packageName)
        if (resId != 0) {
            return TxtRepo.readTextFile(res.openRawResource(resId))
        }
        return ""
    }
}
