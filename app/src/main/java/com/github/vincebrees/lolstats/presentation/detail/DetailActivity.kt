package com.github.vincebrees.lolstats.presentation.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.vincebrees.lolstats.R
import com.github.vincebrees.lolstats.domain.response.SummonerIdErrorCode
import com.github.vincebrees.lolstats.presentation.BaseActivity
import com.github.vincebrees.lolstats.presentation.masteries.MasteriesFragment
import com.github.vincebrees.lolstats.presentation.masteries.MasteriesViewModel
import kotlinx.android.synthetic.main.detail_activity.*
import javax.inject.Inject

class DetailActivity : BaseActivity() {

    @Inject lateinit var viewModel : DetailViewModel

    companion object {
        private const val TAG = "HomeActivity"
        private const val EXTRA_CHAMPION_NAME = "championName"

        fun newInstance(context: Context, championName : String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_CHAMPION_NAME, championName)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        initViewModel()
        initObserver()

        viewModel.onStart(intent.getStringExtra(EXTRA_CHAMPION_NAME))
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.let { lifecycle.addObserver(it) }
    }

    private fun initObserver() {
        viewModel.getLiveDataChampion().observe(this, Observer {
                champion -> champion?.let {
            chamption_name.text = champion.name
            champion_title.text = champion.title
            champion_description.text = champion.description
        }
        })
    }
}
