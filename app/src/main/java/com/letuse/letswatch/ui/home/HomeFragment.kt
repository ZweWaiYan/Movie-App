package com.letuse.letswatch.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.letuse.letswatch.R
import com.letuse.letswatch.model.popularModel.Result
import com.letuse.letswatch.ui.HomeSlider.HomePopularAdapter
import com.letuse.letswatch.ui.nowplaying.NowPlayingAdapter
import com.letuse.letswatch.ui.nowplaying.NowPlayingFragmentDirections
import com.letuse.letswatch.ui.nowplaying.NowPlayingViewModel
import com.letuse.letswatch.ui.popular.PopularAdapter
import com.letuse.letswatch.ui.popular.PopularFragment
import com.letuse.letswatch.ui.popular.PopularFragmentDirections
import com.letuse.letswatch.ui.popular.PopularViewModel
import com.letuse.letswatch.ui.topRated.TopRatedAdapter
import com.letuse.letswatch.ui.topRated.TopRatedFragmentDirections
import com.letuse.letswatch.ui.topRated.TopRatedViewModel
import com.letuse.letswatch.ui.upcoming.UpComingAdapter
import com.letuse.letswatch.ui.upcoming.UpComingFragmentDirections
import com.letuse.letswatch.ui.upcoming.UpComingViewModel
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_popular.*
import kotlinx.android.synthetic.main.fragment_top_rated.*
import kotlinx.android.synthetic.main.fragment_up_coming.*

class HomeFragment : Fragment(), PopularAdapter.ClickListener, TopRatedAdapter.ClickListener,
    UpComingAdapter.ClickListener, NowPlayingAdapter.ClickListener , HomePopularAdapter.ClickListener {

    lateinit var homeViewModel: HomeViewModel

    lateinit var popularViewModel: PopularViewModel
    lateinit var popularAdapter: PopularAdapter

    lateinit var homePopularAdapter: HomePopularAdapter

    lateinit var topRatedAdapter: TopRatedAdapter
    lateinit var topRatedViewModel: TopRatedViewModel

    lateinit var upComingAdapter: UpComingAdapter
    lateinit var upComingViewModel: UpComingViewModel

    lateinit var nowPlayingAdapter: NowPlayingAdapter
    lateinit var nowPlayingViewModel: NowPlayingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //popular slider
        popularViewModel = ViewModelProvider(this).get(PopularViewModel::class.java)
        homePopularAdapter = HomePopularAdapter()
        nav_popular_home.setSliderAdapter(homePopularAdapter)
        homePopularAdapter.setOnClickListener(this)
        observeViewModel()

        nav_popular_home.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        nav_popular_home.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        nav_popular_home.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        nav_popular_home.setIndicatorSelectedColor(Color.WHITE);
        nav_popular_home.setIndicatorUnselectedColor(Color.GRAY);
        nav_popular_home.setScrollTimeInSec(2); //set scroll delay in seconds :
        nav_popular_home.startAutoCycle();

        //////////////////////////////////////// Slider


        //Top Rated
        topRatedViewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)
        topRatedAdapter = TopRatedAdapter()
        nav_toprated_home.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = topRatedAdapter
        }
        topRatedAdapter.setOnClickListener(this)
        topRatedViewModel()

        //Up Coming
        upComingViewModel = ViewModelProvider(this).get(UpComingViewModel::class.java)
        upComingAdapter = UpComingAdapter()
        nav_upcoming_home.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = upComingAdapter
        }
        upComingAdapter.setOnClickListener(this)
        upComingViewModel()

        //Now Playing
        nowPlayingViewModel = ViewModelProvider(this).get(NowPlayingViewModel::class.java)
        nowPlayingAdapter = NowPlayingAdapter()
        nav_nowplaying_home.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = nowPlayingAdapter
        }
        nowPlayingAdapter.setOnClickListener(this)
        NowplayingViewModel()
    }

    override fun onClick(result: Result) {
        var action = HomeFragmentDirections.actionNavHomeToNavDetail(result.id.toString())
        findNavController().navigate(action)
    }

    //TopRated
    private fun topRatedViewModel() {
        topRatedViewModel.loadTopRated()
        topRatedViewModel.getTopRated().observe(
            viewLifecycleOwner,
            Observer { toprated -> topRatedAdapter.updateTopRated(toprated.results) })
    }

    override fun onClick(result: com.letuse.letswatch.model.topratedModel.Result) {
        var action = HomeFragmentDirections.actionNavHomeToNavDetail(result.id.toString())
        findNavController().navigate(action)
    }

    //Up Coming
    private fun upComingViewModel() {
        upComingViewModel.loadUpComing()
        upComingViewModel.getUpComing().observe(
            viewLifecycleOwner,
            Observer { upcoming -> upComingAdapter.updateUpComing(upcoming.results) })
    }

    override fun onClick(result: com.letuse.letswatch.model.upcomingModel.Result) {
        var action =
            HomeFragmentDirections.actionNavHomeToNavDetail(result.id.toString())
        findNavController().navigate(action)
    }

    //NowPlaying
    private fun NowplayingViewModel() {
        nowPlayingViewModel.loadNowPlaying()
        nowPlayingViewModel.getNowPlaying().observe(
            viewLifecycleOwner, Observer { nowplaying ->
                nowPlayingAdapter.updateNowPlaying(nowplaying.results)
            }
        )
    }

    override fun onClick(result: com.letuse.letswatch.model.nowplayingModel.Result) {
        var action =
            HomeFragmentDirections.actionNavHomeToNavDetail(result.id.toString())
        findNavController().navigate(action)
    }

    //////////////////////////////////////// Slider

    //Slider Popular
    override fun click(result: Result) {
        var action = HomeFragmentDirections.actionNavHomeToNavDetail(result.id.toString())
        findNavController().navigate(action)
    }

    fun observeViewModel(){
        popularViewModel.getPopular().observe(
            viewLifecycleOwner, Observer {
                homePopularAdapter.updateHomePopular(it.results)
            }
        )
    }

    override fun onResume() {
        super.onResume()
        popularViewModel.loadPopular()
    }

}