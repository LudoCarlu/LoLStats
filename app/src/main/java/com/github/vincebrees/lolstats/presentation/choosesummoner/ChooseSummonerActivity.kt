package com.github.vincebrees.lolstats.presentation.choosesummoner

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.vincebrees.lolstats.R
import kotlinx.android.synthetic.main.choose_summoner_activity.*

class ChooseSummonerActivity : AppCompatActivity() {

    private lateinit var viewModel: ChooseSummonerViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_summoner_activity)
        initViewModel()
    }

    override fun onResume() {
        super.onResume()
        initButton()
    }

    private fun initButton() {
        choose_summoner_btn_choose.setOnClickListener { viewModel.onClickedValidate(choose_summoner_edit_text.text.toString().trim()) }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ChooseSummonerViewModel::class.java)
        viewModel.let { lifecycle.addObserver(it) }
    }
}
