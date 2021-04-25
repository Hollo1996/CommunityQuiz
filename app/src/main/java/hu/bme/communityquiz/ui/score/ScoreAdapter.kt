package hu.bme.communityquiz.ui.score

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.communityquiz.R
import hu.bme.communityquiz.model.score.Score

class ScoreAdapter(
    val context:Context,
    val scores : List<Pair<Score,Score>>
) : RecyclerView.Adapter<ScoreAdapter.ViewHolder>() {
    class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val textViews = listOf<TextView>(
            view.findViewById(R.id.categoryNameText),
            view.findViewById(R.id.localScoreText),
            view.findViewById(R.id.globalScoreText)
        )

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.score_line,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViews[0].text = scores[position].first.category
        holder.textViews[1].text = scores[position].first.point?.toInt()?.toString() ?: ""
        holder.textViews[2].text = scores[position].second.point?.toInt()?.toString() ?: ""
    }

    override fun getItemCount(): Int {
        return scores.size
    }
}