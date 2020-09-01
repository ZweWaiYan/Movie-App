package com.letuse.letswatch.ui.similar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.letuse.letswatch.api.ApiClient
import com.letuse.letswatch.model.popularModel.PopularData
import com.letuse.letswatch.model.similarMovieModel.SimilarMovieData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SimilarViewModel : ViewModel() {
    private var similar : MutableLiveData<SimilarMovieData> = MutableLiveData()

    fun getPopular() : LiveData<SimilarMovieData> = similar

    fun loadPopular(similarid : String){
        var apiClient = ApiClient()
        val callapi = apiClient.getSimilarClient(similarid.toInt(),ApiClient.API_KEY)

        callapi.enqueue(object : Callback<SimilarMovieData> {
            override fun onFailure(call: Call<SimilarMovieData>, t: Throwable) {
                Log.d("error", t.toString())
            }

            override fun onResponse(call: Call<SimilarMovieData>, response: Response<SimilarMovieData>) {
                similar.value = response.body()!!
            }
        })
    }
}