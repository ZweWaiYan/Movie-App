package com.letuse.letswatch.ui.popular

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
import com.letuse.letswatch.model.popularModel.Result
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_now_playing.*
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : Fragment(), PopularAdapter.ClickListener {

    lateinit var popularViewModel: PopularViewModel
    lateinit var popularAdapter: PopularAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularViewModel = ViewModelProvider(this).get(PopularViewModel::class.java)

        popularAdapter = PopularAdapter()

        nav_popular.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = popularAdapter
        }

        popularAdapter.setOnClickListener(this)

        popularViewModel()
    }

    //Popular
    private fun popularViewModel() {
        popularViewModel.loadPopular()
        popularViewModel.getPopular().observe(
            viewLifecycleOwner, Observer { popular ->
                popularAdapter.updatePopular(popular.results)
            }
        )
    }

    override fun onClick(result: Result) {
        var action =
            PopularFragmentDirections.actionPopularFragmentToNavDetail(result.id.toString())
        findNavController().navigate(action)
    }

}