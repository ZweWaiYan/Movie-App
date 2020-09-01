package com.letuse.letswatch.ui.popular

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.letuse.letswatch.api.ApiClient
import com.letuse.letswatch.model.popularModel.PopularData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularViewModel : ViewModel() {

    private var popular : MutableLiveData<PopularData> = MutableLiveData()

    fun getPopular() : LiveData<PopularData> = popular

    fun loadPopular(){
        var apiClient = ApiClient()
        val callapi = apiClient.getPopularClient(ApiClient.API_KEY)

        callapi.enqueue(object : Callback<PopularData> {
            override fun onFailure(call: Call<PopularData>, t: Throwable) {
                Log.d("error", t.toString())
            }

            override fun onResponse(call: Call<PopularData>, response: Response<PopularData>) {
                popular.value = response.body()!!
            }
        })
    }
}