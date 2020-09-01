package com.letuse.letswatch.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.letuse.letswatch.ui.popular.PopularFragment
import com.letuse.letswatch.ui.popular.PopularViewModel

class HomeViewModel : ViewModel() {

    private var loadingHome : MutableLiveData<Boolean> = MutableLiveData()
    fun getLoadingHome() : LiveData<Boolean> = loadingHome

}