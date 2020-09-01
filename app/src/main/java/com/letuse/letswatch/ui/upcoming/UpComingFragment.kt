package com.letuse.letswatch.ui.upcoming

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
import com.letuse.letswatch.model.upcomingModel.Result
import kotlinx.android.synthetic.main.fragment_popular.*
import kotlinx.android.synthetic.main.fragment_up_coming.*

class UpComingFragment : Fragment() , UpComingAdapter.ClickListener {

    lateinit var upComingAdapter: UpComingAdapter
    lateinit var upComingViewModel: UpComingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_up_coming, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        upComingViewModel = ViewModelProvider(this).get(UpComingViewModel::class.java)

        upComingAdapter = UpComingAdapter()

        nav_upComing.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = upComingAdapter
        }

        upComingAdapter.setOnClickListener(this)

        upComingViewModel()
    }

    private fun upComingViewModel(){
        upComingViewModel.loadUpComing()
        upComingViewModel.getUpComing().observe(
            viewLifecycleOwner, Observer { upcoming ->
                upComingAdapter.updateUpComing(upcoming.results)
            }
        )
    }

    override fun onClick(result: Result) {
        var action = UpComingFragmentDirections.actionUpComingFragment2ToNavDetail(result.id.toString())
        findNavController().navigate(action)
    }
}