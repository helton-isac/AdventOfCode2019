package com.hitg.adventofcode.ui.challenge

import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.hitg.adventofcode.R
import kotlinx.android.synthetic.main.challenge_fragment.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


class ChallengeFragment : Fragment() {

    private lateinit var viewModel: ChallengeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.challenge_fragment, container, false)
        val args = arguments ?: return view

        val day: Int = args.getInt("day", 0)
        val title = args.getString("title", "")
        val hasFirstStar = args.getBoolean("hasFirstStar", false)
        val hasSecondStar = args.getBoolean("hasSecondStar", false)

        view.findViewById<TextView>(R.id.txt_day).text =
            String.format(getString(R.string.day_text), day)
        view.findViewById<TextView>(R.id.challenge_title).text = title

        val imgFirstStar = view.findViewById<ImageView>(R.id.img_star_1)
        val imgSecondStar = view.findViewById<ImageView>(R.id.img_star_2)
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
        val challengeLink = getString(R.string.adventofcode_link) + day

        view.findViewById<ImageView>(R.id.imgLink).setOnClickListener {
            val uri = Uri.parse(challengeLink)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }

        view.findViewById<ImageView>(R.id.imgPuzzleInput).setOnClickListener {
            if (puzzleInputWebView.visibility == View.GONE) {
                val sb = StringBuilder("<html><body>")
                sb.append(readTextFile("input$day"))
                sb.append("</body></html>")
                puzzleInputWebView.loadData(sb.toString(), "text/html", "UTF-8")
                puzzleInputWebView.visibility = View.VISIBLE
                puzzleInputWebView.settings.builtInZoomControls = true
                puzzleInputWebView.setBackgroundColor(0x00000000)
                puzzleInputWebView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null)
            } else {
                puzzleInputWebView.visibility = View.GONE
            }
        }

        view.findViewById<ImageView>(R.id.imgShareChallenge).setOnClickListener {
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

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ChallengeViewModel::class.java)
    }

    private fun readTextFile(rawFileName: String): String {
        val res: Resources = resources
        val resId = res.getIdentifier(
            rawFileName, "raw",
            activity?.packageName
        )
        if (resId != 0) {
            val stream: InputStream = res.openRawResource(resId)
            val bufferedReader = BufferedReader(InputStreamReader(stream))
            return bufferedReader.use { it.readText() }
        }
        return ""
    }

}
