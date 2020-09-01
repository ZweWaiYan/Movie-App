package com.letuse.letswatch.ui.topRated

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
import com.letuse.letswatch.model.topratedModel.Result
import com.letuse.letswatch.ui.popular.PopularAdapter
import com.letuse.letswatch.ui.popular.PopularFragmentDirections
import com.letuse.letswatch.ui.popular.PopularViewModel
import kotlinx.android.synthetic.main.fragment_popular.*
import kotlinx.android.synthetic.main.fragment_top_rated.*

class TopRatedFragment : Fragment() , TopRatedAdapter.ClickListener {

    lateinit var topRatedViewModel: TopRatedViewModel
    lateinit var topRatedAdapter: TopRatedAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topRatedViewModel = ViewModelProvider(this).get(TopRatedViewModel::class.java)

        topRatedAdapter = TopRatedAdapter()

        nav_topRated.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = topRatedAdapter
        }

        topRatedAdapter.setOnClickListener(this)

        topRatedViewModel()
    }


    private fun topRatedViewModel(){
        topRatedViewModel.loadTopRated()
        topRatedViewModel.getTopRated().observe(
            viewLifecycleOwner, Observer { toprated ->
                topRatedAdapter.updateTopRated(toprated.results)
            }
        )
    }

    override fun onClick(result: Result) {
        var action = TopRatedFragmentDirections.actionNavTopRatedToNavDetail(result.id.toString())
        findNavController().navigate(action)
    }
}