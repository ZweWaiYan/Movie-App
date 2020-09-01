package com.letuse.letswatch.ui.HomeSlider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.letuse.letswatch.R
import com.letuse.letswatch.model.popularModel.Result
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home_popular.view.*
import kotlinx.android.synthetic.main.item_popular.view.*

class HomePopularAdapter(var listResult: List<Result> = ArrayList<Result>()) : SliderViewAdapter<HomePopularAdapter.HomePopularViewHolder>(){
    var pclickListener:ClickListener?=null

    fun setOnClickListener(clickListener: ClickListener){
        this.pclickListener = clickListener
    }

    private var limit : Int = 5
    inner class HomePopularViewHolder(itemView: View) : SliderViewAdapter.ViewHolder(itemView),View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        lateinit var result: Result

        fun bind(result: Result){
            this.result = result
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + result.backdrop_path).into(itemView.imghomepopular)
//            itemView.txthomepopular.text = result.title
        }

        override fun onClick(p0: View?) {
            pclickListener?.click(result)
        }
    }

    fun updateHomePopular(resultList:List<Result>){
        this.listResult=resultList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?): HomePopularViewHolder {
        var view = LayoutInflater.from(parent?.context).inflate(R.layout.item_home_popular,parent,false)
        return HomePopularViewHolder(view)
    }

    override fun getCount(): Int {
        if (listResult.size>limit){
            return limit
        }else{
            return listResult.size
        }
    }

    override fun onBindViewHolder(viewHolder: HomePopularViewHolder?, position: Int) {
        viewHolder?.bind(listResult[position])
    }

    interface ClickListener{
        fun click(result: Result)
    }
}
