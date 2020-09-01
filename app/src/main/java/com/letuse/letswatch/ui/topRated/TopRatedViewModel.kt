package com.letuse.letswatch.ui.topRated

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.letuse.letswatch.api.ApiClient
import com.letuse.letswatch.model.popularModel.PopularData
import com.letuse.letswatch.model.topratedModel.TopRatedData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedViewModel : ViewModel(){
    private var toprated : MutableLiveData<TopRatedData> = MutableLiveData()

    fun getTopRated() : LiveData<TopRatedData> = toprated

    fun loadTopRated(){
        var apiClient = ApiClient()
        val callapi = apiClient.getTopRatedClient(ApiClient.API_KEY)

       callapi.enqueue(object : Callback<TopRatedData>{
           override fun onFailure(call: Call<TopRatedData>, t: Throwable) {
               Log.d("error", t.toString())
           }

           override fun onResponse(call: Call<TopRatedData>, response: Response<TopRatedData>) {
               toprated.value = response.body()!!
           }
       })
    }
}