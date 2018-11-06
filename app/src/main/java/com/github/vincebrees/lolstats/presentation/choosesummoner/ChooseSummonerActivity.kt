package com.github.vincebrees.lolstats.presentation.choosesummoner

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.vincebrees.lolstats.R

class ChooseSummonerActivity : AppCompatActivity() {

    private var viewModel: ChooseSummonerViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_summoner_activity)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ChooseSummonerViewModel::class.java)
        viewModel?.let { lifecycle.addObserver(it) }
    }
}
