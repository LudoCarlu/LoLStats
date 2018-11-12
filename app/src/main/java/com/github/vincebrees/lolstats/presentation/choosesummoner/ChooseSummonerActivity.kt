package com.github.vincebrees.lolstats.presentation.choosesummoner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.vincebrees.lolstats.R
import com.github.vincebrees.lolstats.presentation.BaseActivity

class ChooseSummonerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_summoner_activity)

        supportFragmentManager.beginTransaction()
            .replace(R.id.choose_summoner_container, ChooseSummonerFragment.newInstance())
            .commit()
    }
}
