package com.github.vincebrees.lolstats.presentation.masteries

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.vincebrees.lolstats.R
import com.github.vincebrees.lolstats.domain.models.ChampionMasteriesModel
import com.github.vincebrees.lolstats.presentation.detail.DetailActivity
import kotlinx.android.synthetic.main.masteries_view_holder.view.*

class MasteriesAdapter(val context: Context, var listModel: List<ChampionMasteriesModel>) : RecyclerView.Adapter<MasteriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.masteries_view_holder, parent, false)
        return ViewHolder(context, itemView)
    }

    override fun getItemCount() = listModel.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(listModel[position])


    class ViewHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(model: ChampionMasteriesModel) {
            with(itemView) {
                masteries_champion_name.text = model.name
                masteries_champion_points.text = model.championPoints.toString()
                masteries_champion_chest_granted.text = if(model.chestGranted) "Coffre non disponible" else "Coffre disponible"

                itemView.setOnClickListener { context.startActivity(DetailActivity.newInstance(context, model.name)) }
            }
        }
    }
}
