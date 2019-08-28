package com.lifx.lifxtest

import androidx.lifecycle.ViewModel
import com.lifx.lifxtest.model.Data
import io.reactivex.Observable

class AppViewModel: ViewModel() {

    fun loadData(): Observable<List<Data>> {
        return Repository.loadData()
    }
}