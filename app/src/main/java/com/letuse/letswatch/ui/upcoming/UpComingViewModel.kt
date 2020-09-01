package com.letuse.letswatch.ui.upcoming

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.letuse.letswatch.api.ApiClient
import com.letuse.letswatch.model.popularModel.PopularData
import com.letuse.letswatch.model.upcomingModel.UpComingData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpComingViewModel : ViewModel() {
    private var upcoming : MutableLiveData<UpComingData> = MutableLiveData()

    fun getUpComing() : LiveData<UpComingData> = upcoming

    fun loadUpComing(){
        var apiClient = ApiClient()
        val callapi = apiClient.getUpComingClient(ApiClient.API_KEY)

        callapi.enqueue(object : Callback<UpComingData> {
            override fun onFailure(call: Call<UpComingData>, t: Throwable) {
                Log.d("error", t.toString())
            }

            override fun onResponse(call: Call<UpComingData>, response: Response<UpComingData>) {
                upcoming.value = response.body()!!
            }
        })
    }
}