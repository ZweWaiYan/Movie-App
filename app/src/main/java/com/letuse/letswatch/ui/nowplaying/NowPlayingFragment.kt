package com.letuse.letswatch.ui.nowplaying

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.letuse.letswatch.R
import com.letuse.letswatch.model.nowplayingModel.Result
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlinx.android.synthetic.main.fragment_popular.*

class NowPlayingFragment : Fragment() , NowPlayingAdapter.ClickListener {

    lateinit var nowPlayingAdapter: NowPlayingAdapter
    lateinit var nowPlayingViewModel: NowPlayingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nowPlayingViewModel = ViewModelProvider(this).get(NowPlayingViewModel::class.java)

        nowPlayingAdapter = NowPlayingAdapter()

        nav_popular.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = nowPlayingAdapter
        }

        nowPlayingAdapter.setOnClickListener(this)

        popularViewModel()
    }


    private fun popularViewModel() {
        nowPlayingViewModel.loadNowPlaying()
        nowPlayingViewModel.getNowPlaying().observe(
            viewLifecycleOwner, Observer { nowplaying ->
                nowPlayingAdapter.updateNowPlaying(nowplaying.results)
            }
        )
    }

    override fun onClick(result:Result) {
        var action = NowPlayingFragmentDirections.actionNowPlayingFragment2ToNavDetail(result.id.toString())
        findNavController().navigate(action)
    }

}

