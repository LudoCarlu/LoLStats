package com.github.vincebrees.lolstats.presentation.masteries

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.vincebrees.lolstats.R
import com.github.vincebrees.lolstats.domain.models.ChampionMasteriesModel
import com.github.vincebrees.lolstats.domain.response.SummonerIdErrorCode
import com.github.vincebrees.lolstats.presentation.BaseFragment
import kotlinx.android.synthetic.main.masteries_fragment.*

/**
 * Created by Vincent ETIENNE on 09/11/2018.
 */

class MasteriesFragment : BaseFragment() {

    companion object {
        fun newInstance() = MasteriesFragment()
        private const val TAG = "MasteriesFragment"
    }

    private lateinit var viewModel: MasteriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initObserver()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.masteries_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(context!!)
        masteries_list.layoutManager = linearLayoutManager
        masteries_list.adapter = MasteriesAdapter(context!!, mutableListOf())
        val dividerItemDecoration = DividerItemDecoration(masteries_list.context, linearLayoutManager.orientation)
        masteries_list.addItemDecoration(dividerItemDecoration)
    }

    private fun initObserver() {
        viewModel.getLiveDataState().observe(this, Observer {
                viewState -> viewState?.let {
            when{
                it.isError -> showError()
            }
            if(viewState.isLoading){
                showLoading()
            }else{
                hideLoading()
            }
        }
        })

        viewModel.getLiveDataListChampionModel().observe(this, Observer {
                listModel -> if(listModel != null){
                setupRecyclerView(listModel)
            }else{
                showEmptyList()
            }
        })
    }

    private fun setupRecyclerView(listModel: List<ChampionMasteriesModel>) {
        val adapter = masteries_list.adapter as MasteriesAdapter
        adapter.listModel = listModel
        adapter.notifyDataSetChanged()
    }

    private fun showEmptyList() {
//TODO("not implemented")
    }

    private fun showError() {
//TODO("not implemented")
    }

    private fun showLoading() {
        masteries_loader.visibility = View.VISIBLE
        masteries_list.visibility = View.GONE
    }

    private fun hideLoading(){
        masteries_loader.visibility = View.GONE
        masteries_list.visibility = View.VISIBLE
    }

    override fun onStart() {
        super.onStart()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MasteriesViewModel::class.java)
        viewModel.let { lifecycle.addObserver(it) }
    }
}