package com.test.distancecalculation.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.data.distance.model.CustomerModel
import com.test.distancecalculation.R
import kotlinx.android.synthetic.main.item_cstomer.view.*


class CustomersAdapter : RecyclerView.Adapter<CustomersAdapter.ViewHolder>() {

    private var customersList = listOf<CustomerModel>()

    fun updateList(list: List<CustomerModel>) {
        val diffResult = DiffUtil.calculateDiff(
            CustomerDiffCallback(customersList, list)
        )
        diffResult.dispatchUpdatesTo(this)
        customersList = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_cstomer, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(customersList[position])
    }

    override fun getItemCount(): Int {
        return customersList.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(model: CustomerModel) {
            setupView(model)
        }

        private fun setupView(model: CustomerModel) {
            with(itemView) {
                tv_customer_name.text = model.name
                tv_distance_from_office.text =
                    context.getString(R.string.distance_from_office, model.distanceFromOffice)
            }
        }
    }
}