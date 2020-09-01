package com.letuse.letswatch.ui.nowplaying

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.letuse.letswatch.R
import com.squareup.picasso.Picasso
import com.letuse.letswatch.model.nowplayingModel.Result
import kotlinx.android.synthetic.main.item_nowplaying.view.*
import kotlinx.android.synthetic.main.item_popular.view.*

class NowPlayingAdapter(var NowplayingList: List<Result> = ArrayList<Result>()) :
    RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder>() {

    var pClickListener : ClickListener?= null

    fun setOnClickListener(clickListener: ClickListener){
        this.pClickListener = clickListener
    }

    inner class NowPlayingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener{

        lateinit var result: Result

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(result: Result) {
            this.result = result
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + result.poster_path)
                .into(itemView.imgnowplaying)
            itemView.txtnowplaying.text = result.original_title
        }

        override fun onClick(p0: View?) {
            pClickListener?.onClick(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nowplaying, parent, false)
        return NowPlayingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return NowplayingList.size
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bind(NowplayingList[position])
    }

    fun updateNowPlaying(nowplayList: List<Result>) {
        this.NowplayingList = nowplayList
        notifyDataSetChanged()
    }

    interface ClickListener{
        fun onClick(result: Result)
    }
}
