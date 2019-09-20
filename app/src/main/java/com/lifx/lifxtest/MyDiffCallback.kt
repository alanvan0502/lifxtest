package com.lifx.lifxtest

import androidx.recyclerview.widget.DiffUtil
import com.lifx.lifxtest.model.Data

class MyDiffCallback(val newData: List<Data>, val oldData: List<Data>): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition].uuid == newData[newItemPosition].uuid
    }

    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition] == newData[newItemPosition]
    }
}