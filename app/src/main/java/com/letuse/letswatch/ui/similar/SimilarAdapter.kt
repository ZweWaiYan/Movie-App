package com.letuse.letswatch.ui.similar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.letuse.letswatch.R
import com.letuse.letswatch.model.similarMovieModel.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_similar.view.*

class SimilarAdapter(var SimilarList: List<Result> = ArrayList<Result>()) :
    RecyclerView.Adapter<SimilarAdapter.SimilarViewHolder>() {

    var pClickListener : ClickListener?= null

    fun setOnClickListener(clickListener: ClickListener){
        this.pClickListener = clickListener
    }

    inner class SimilarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener{

        lateinit var result: Result

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(result: Result) {
            this.result = result
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + result.poster_path)
                .into(itemView.imgSimlar)
            itemView.txtSimlar.text = result.original_title
        }

        override fun onClick(p0: View?) {
            pClickListener?.onClick(result)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_similar, parent, false)
        return SimilarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return SimilarList.size
    }

    override fun onBindViewHolder(holder: SimilarViewHolder, position: Int) {
        holder.bind(SimilarList[position])
    }

    fun updatePopular(popularList: List<Result>) {
        this.SimilarList = popularList
        notifyDataSetChanged()
    }

    interface ClickListener{
        fun onClick(result: Result)
    }
}