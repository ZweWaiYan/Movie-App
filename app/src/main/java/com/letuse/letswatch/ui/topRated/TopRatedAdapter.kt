package com.letuse.letswatch.ui.topRated

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.letuse.letswatch.R
import com.squareup.picasso.Picasso
import com.letuse.letswatch.model.topratedModel.Result
import kotlinx.android.synthetic.main.item_popular.view.*
import kotlinx.android.synthetic.main.item_toprated.view.*

class TopRatedAdapter(var TopRatedList : List<Result> = ArrayList<Result>()) :
    RecyclerView.Adapter<TopRatedAdapter.PopularViewHolder>() {

    var pClickListener : ClickListener?= null

    fun setOnClickListener(clickListener: ClickListener){
        this.pClickListener = clickListener
    }

    inner class PopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener{

        lateinit var result: Result

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(result: Result) {
            this.result = result
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + result.poster_path)
                .into(itemView.imgtoprated)
            itemView.txttoprated.text = result.original_title
        }

        override fun onClick(p0: View?) {
            pClickListener?.onClick(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_toprated, parent, false)
        return PopularViewHolder(view)
    }

    override fun getItemCount(): Int {
        return TopRatedList.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(TopRatedList[position])
    }

    fun updateTopRated(popularList: List<Result>) {
        this.TopRatedList = popularList
        notifyDataSetChanged()
    }

    interface ClickListener{
        fun onClick(result: Result)
    }
}