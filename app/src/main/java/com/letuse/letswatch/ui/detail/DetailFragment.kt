package com.letuse.letswatch.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.letuse.letswatch.R
import com.letuse.letswatch.model.similarMovieModel.Result
import com.letuse.letswatch.ui.similar.SimilarAdapter
import com.letuse.letswatch.ui.similar.SimilarViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_home.*

//
class DetailFragment : Fragment(), SimilarAdapter.ClickListener {

    lateinit var detailViewModel: DetailViewModel
    lateinit var similarViewModel: SimilarViewModel
    lateinit var similarAdapter: SimilarAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Detail
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.getLoading().observe(viewLifecycleOwner, Observer { isloading ->
            if (isloading) {
                progressbar.visibility = View.VISIBLE
            } else {
                progressbar.visibility = View.GONE
            }
        })
        var messageDetail = arguments?.let { DetailFragmentArgs.fromBundle(it) }
        var Detailid: String = messageDetail!!.Data
        detailViewModel(Detailid)

        //Similar
        similarViewModel = ViewModelProvider(this).get(SimilarViewModel::class.java)
        similarAdapter = SimilarAdapter()
        nav_similar.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = similarAdapter
        }
        var messageSimilar = arguments?.let { DetailFragmentArgs.fromBundle(it) }
        var Similarid: String = messageSimilar!!.Data
        similarViewModel(Similarid)
    }

    private fun detailViewModel(movieid: String) {
        detailViewModel.loaddetail(movieid)
        detailViewModel.getdetail().observe(
            viewLifecycleOwner, Observer { detail ->
                txtTitle.text = detail.title
                txtOriginTitle.text = detail.title
                Picasso.get().load("https://image.tmdb.org/t/p/w500/" + detail.poster_path)
                    .into(imgDetail)
                txtreleasedate.text = detail.release_date
                txtstatus.text = detail.status
                txtgenres.text = detail.genres[0].name
                txtpopularity.text = detail.popularity.toString()
                txtoverview.text = detail.overview
            }
        )
    }

    //Similar
    private fun similarViewModel(similarid: String) {
        similarViewModel.loadPopular(similarid)
        similarViewModel.getPopular().observe(
            viewLifecycleOwner, Observer { similar ->
                similarAdapter.updatePopular(similar.results)
            }
        )
    }

    override fun onClick(result: Result) {
        var action = DetailFragmentDirections.actionNavDetailSelf(result.id.toString())
        findNavController().navigate(action)
    }


}