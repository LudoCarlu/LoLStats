package com.github.vincebrees.lolstats.presentation.choosesummoner

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.vincebrees.lolstats.R
import com.github.vincebrees.lolstats.domain.response.SummonerIdErrorCode
import com.github.vincebrees.lolstats.presentation.BaseFragment
import com.github.vincebrees.lolstats.presentation.home.HomeActivity
import kotlinx.android.synthetic.main.choose_summoner_activity.*
import kotlinx.android.synthetic.main.choose_summoner_fragment.*

/**
 * Created by Vincent ETIENNE on 09/11/2018.
 */

class ChooseSummonerFragment : BaseFragment() {

    companion object {
        fun newInstance() = ChooseSummonerFragment()
        private const val TAG = "ChooseSummonerFragment"
    }

    private lateinit var viewModel: ChooseSummonerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initObserver()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.choose_summoner_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
    }

    private fun initObserver() {
        viewModel.getLiveDataState().observe(this, Observer {
                viewState -> viewState?.let {
            when{
                it.isLoading -> showLoading()
                it.isSuccess -> navigateToHomeActivity()
                it.isError == SummonerIdErrorCode.TECHNICAL_ERROR -> showTechnicalError()
                it.isError == SummonerIdErrorCode.NOT_FOUND -> showSummonerNotFound()
            }
        }
        })
    }

    private fun disableLoading() {
        choose_summoner_btn_choose.isEnabled = true
        choose_summoner_edit_text.isEnabled = true
        choose_summoner_btn_choose.visibility = View.VISIBLE
        choose_summoner_loader.visibility = View.GONE
    }

    private fun showSummonerNotFound() {
        disableLoading()
        toast(getString(R.string.summoner_not_found))
    }

    private fun showTechnicalError() {
        disableLoading()
        toast(getString(R.string.technical_error))
    }

    private fun navigateToHomeActivity() {
        disableLoading()
        startActivity(HomeActivity.newInstance(context!!))
    }

    private fun showLoading() {
        choose_summoner_btn_choose.isEnabled = false
        choose_summoner_edit_text.isEnabled = false
        choose_summoner_btn_choose.visibility = View.INVISIBLE
        choose_summoner_loader.visibility = View.VISIBLE
    }

    override fun onStart() {
        super.onStart()
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