package com.github.vincebrees.lolstats.presentation.masteries

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.vincebrees.lolstats.R
import com.github.vincebrees.lolstats.presentation.BaseFragment

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
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.masteries_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButton()
    }

    private fun initObserver() {
    }

    private fun showLoading() {
//TODO("not implemented")
    }

    override fun onStart() {
        super.onStart()
        initButton()
    }

    private fun initButton() {
        
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MasteriesViewModel::class.java)
        viewModel.let { lifecycle.addObserver(it) }
    }
}