package com.test.distancecalculation.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.test.data.distance.model.CustomerModel

class CustomerDiffCallback(
    private val oldList: List<CustomerModel>,
    private val newList: List<CustomerModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].userId == newList[newItemPosition].userId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].userId == newList[newItemPosition].userId &&
                oldList[oldItemPosition].name == newList[newItemPosition].name &&
                oldList[oldItemPosition].distanceFromOffice == newList[newItemPosition].distanceFromOffice
    }
}