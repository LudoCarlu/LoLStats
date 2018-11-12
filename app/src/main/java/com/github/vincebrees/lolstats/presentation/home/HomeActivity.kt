package com.github.vincebrees.lolstats.presentation.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.vincebrees.lolstats.R
import com.github.vincebrees.lolstats.presentation.BaseActivity
import com.github.vincebrees.lolstats.presentation.masteries.MasteriesFragment

class HomeActivity : BaseActivity() {

    companion object {
        private const val TAG = "HomeActivity"

        fun newInstance(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        supportFragmentManager.beginTransaction()
            .replace(R.id.home_container, MasteriesFragment.newInstance())
            .commit()
    }
}
