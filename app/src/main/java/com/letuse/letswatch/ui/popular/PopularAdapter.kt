package com.letuse.letswatch.ui.popular

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.letuse.letswatch.R
import com.letuse.letswatch.model.popularModel.Result
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_popular.view.*

class PopularAdapter(var PopularList: List<Result> = ArrayList<Result>()) :
    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {

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
                .into(itemView.imgPopular)
            itemView.txtPopular.text = result.original_title
        }

        override fun onClick(p0: View?) {
            pClickListener?.onClick(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false)
        return PopularViewHolder(view)
    }

    override fun getItemCount(): Int {
        return PopularList.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(PopularList[position])
    }

    fun updatePopular(popularList: List<Result>) {
        this.PopularList = popularList
        notifyDataSetChanged()
    }

    interface ClickListener{
        fun onClick(result: Result)
    }
}
