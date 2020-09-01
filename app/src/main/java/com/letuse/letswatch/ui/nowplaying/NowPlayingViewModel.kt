package com.letuse.letswatch.ui.nowplaying

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.letuse.letswatch.api.ApiClient
import com.letuse.letswatch.model.nowplayingModel.NowPlayingData
import com.letuse.letswatch.model.popularModel.PopularData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowPlayingViewModel : ViewModel() {
    private var nowplaying : MutableLiveData<NowPlayingData> = MutableLiveData()

    fun getNowPlaying() : LiveData<NowPlayingData> = nowplaying

    fun loadNowPlaying(){
        var apiClient = ApiClient()
        val callapi = apiClient.getNowPlayingClient(ApiClient.API_KEY)

        callapi.enqueue(object : Callback<NowPlayingData> {
            override fun onFailure(call: Call<NowPlayingData>, t: Throwable) {
                Log.d("error", t.toString())!!
            }

            override fun onResponse(call: Call<NowPlayingData>, response: Response<NowPlayingData>) {
                nowplaying.value = response.body()!!
            }
        })
    }
}