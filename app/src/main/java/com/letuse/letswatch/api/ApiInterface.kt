package com.letuse.letswatch.api

import com.letuse.letswatch.model.detailModel.DetailData
import com.letuse.letswatch.model.nowplayingModel.NowPlayingData
import com.letuse.letswatch.model.popularModel.PopularData
import com.letuse.letswatch.model.searchModel.SearchData
import com.letuse.letswatch.model.similarMovieModel.SimilarMovieData
import com.letuse.letswatch.model.topratedModel.TopRatedData
import com.letuse.letswatch.model.upcomingModel.UpComingData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    //Popular
    @GET("popular")
    fun getPopularInterface(
        @Query("api_key") apiKey : String
//        @Query("page") page : Int
    ): Call<PopularData>

    //Detail
    @GET("{movie_id}")
    fun getDetailInterface(
        @Path("movie_id") movieid : Int,
        @Query("api_key") apiKey : String
    ): Call<DetailData>

    //Top Rated
    @GET("top_rated")
    fun getTopRatedInterface(
        @Query("api_key") apiKey : String
    ): Call<TopRatedData>

    //Similar Movie
    @GET("{movie_id}/similar")
    fun getSimilarInterface(
        @Path("movie_id") similar_id : Int,
        @Query("api_key") apiKey : String
    ): Call<SimilarMovieData>

    @GET("upcoming")
    fun getUpComingInterface(
        @Query("api_key") apiKey : String
    ): Call<UpComingData>

    @GET("now_playing")
    fun getNowPlayingInterface(
        @Query("api_key") apiKey: String
    ): Call<NowPlayingData>

    @GET("search-movies")
    fun getSearchInterface(
        @Query("api_key") apiKey: String,
        @Query("query") query : String
    ): Call<SearchData>
}