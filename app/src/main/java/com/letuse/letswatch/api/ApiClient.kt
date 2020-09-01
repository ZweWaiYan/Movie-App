package com.letuse.letswatch.api

import com.letuse.letswatch.model.detailModel.DetailData
import com.letuse.letswatch.model.nowplayingModel.NowPlayingData
import com.letuse.letswatch.model.popularModel.PopularData
import com.letuse.letswatch.model.searchModel.SearchData
import com.letuse.letswatch.model.similarMovieModel.SimilarMovieData
import com.letuse.letswatch.model.topratedModel.TopRatedData
import com.letuse.letswatch.model.upcomingModel.UpComingData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val BASE_URL = "https://api.themoviedb.org/3/movie/"

    companion object {
        val API_KEY = "cfdad7456adae302794611f3dcb19946"
    }

    private val apiInterface: ApiInterface

    init {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    //Popular
    fun getPopularClient(apiKey: String): Call<PopularData> {
        return apiInterface.getPopularInterface(apiKey)
    }

    //Detail Movie
    fun getDetailClient(apiKey: String, movieid: String): Call<DetailData> {
        return apiInterface.getDetailInterface(movieid.toInt(), apiKey)
    }

    //Top Rated
    fun getTopRatedClient(apiKey: String): Call<TopRatedData>{
        return apiInterface.getTopRatedInterface(apiKey)
    }

    //Similar Movie
    fun getSimilarClient(similarid: Int , apiKey: String): Call<SimilarMovieData> {
        return apiInterface.getSimilarInterface(similarid , apiKey)
    }

    //Up Coming
    fun getUpComingClient(apiKey: String): Call<UpComingData>{
        return apiInterface.getUpComingInterface(apiKey)
    }

    //Now Playing
    fun getNowPlayingClient(apiKey: String): Call<NowPlayingData>{
        return apiInterface.getNowPlayingInterface(apiKey)
    }

    //Search
    fun getSearchClient(apiKey: String , query : String): Call<SearchData>{
        return apiInterface.getSearchInterface(apiKey , query)
    }

}