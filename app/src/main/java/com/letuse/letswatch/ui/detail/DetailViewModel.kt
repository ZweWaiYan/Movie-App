package com.letuse.letswatch.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.letuse.letswatch.api.ApiClient
import com.letuse.letswatch.model.detailModel.DetailData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel(){
    private var detail : MutableLiveData<DetailData> = MutableLiveData()

    private var loading : MutableLiveData<Boolean> = MutableLiveData()

    fun getLoading() : LiveData<Boolean> = loading

    fun getdetail() : LiveData<DetailData> = detail

    fun loaddetail(movieID : String){
        var apiClient = ApiClient()
        val callapi = apiClient.getDetailClient(ApiClient.API_KEY,movieID)

        callapi.enqueue(object : Callback<DetailData>{
            override fun onFailure(call: Call<DetailData>, t: Throwable) {
                Log.d("error", t.toString())
               loading.value = false
            }

            override fun onResponse(call: Call<DetailData>, response: Response<DetailData>) {
                detail.value = response.body()!!
                loading.value = false
            }

        })
    }
}