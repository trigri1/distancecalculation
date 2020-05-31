package com.test.distancecalculation.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import com.test.data.distance.model.CustomerModel
import com.test.distancecalculation.R
import com.test.distancecalculation.ui.base.BaseActivity
import com.test.distancecalculation.utils.gone
import com.test.distancecalculation.utils.observe
import com.test.distancecalculation.utils.visible
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    private val customersAdapter = CustomersAdapter()

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        getViewModel()
        observerViewModel()
        seListeners()
    }

    private fun seListeners() {
        btn_find_guests.setOnClickListener {
            viewModel.onFindGuestsDistance()
        }
    }

    private fun observerViewModel() {
        with(viewModel) {
            observe(dataReady, ::onDataReady)
            observe(customersData, ::onCustomerData)
            observe(error, ::onError)
        }
    }

    private fun onCustomerData(list: List<CustomerModel>?) {
        btn_find_guests.gone()
        tv_message.gone()
        rc_customers.visible()
        list?.let {
            customersAdapter.updateList(it)
        }
    }

    private fun onDataReady(unit: Unit?) = btn_find_guests.visible()
    private fun onError(error: String?) {
        tv_message.visible()
        tv_message.text = error
    }

    private fun setupView() {
        rc_customers.adapter = customersAdapter
        rc_customers.itemAnimator = DefaultItemAnimator()
    }

    private fun getViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }
}
