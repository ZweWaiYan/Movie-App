package com.letuse.letswatch.ui.upcoming

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.letuse.letswatch.R
import com.letuse.letswatch.model.upcomingModel.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_popular.view.*
import kotlinx.android.synthetic.main.item_upcoming.view.*

class UpComingAdapter(var UpComingList: List<Result> = ArrayList<Result>()) :
    RecyclerView.Adapter<UpComingAdapter.UpComingViewHolder>() {

    var pClickListener: ClickListener? = null

    fun setOnClickListener(clickListener: ClickListener) {
        this.pClickListener = clickListener
    }

    inner class UpComingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        lateinit var result: Result

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(result: Result) {
            this.result = result
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + result.poster_path)
                .into(itemView.imgupcoming)
            itemView.txtupcoming.text = result.original_title
        }

        override fun onClick(p0: View?) {
            pClickListener?.onClick(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_upcoming, parent, false)
        return UpComingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return UpComingList.size
    }

    override fun onBindViewHolder(holder: UpComingViewHolder, position: Int) {
        holder.bind(UpComingList[position])
    }

    fun updateUpComing(upcomingList: List<Result>) {
        this.UpComingList = upcomingList
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(result: Result)
    }
}