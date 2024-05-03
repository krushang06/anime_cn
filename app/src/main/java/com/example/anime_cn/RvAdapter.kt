package com.example.anime_cn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anime_cn.apiData2.Datas


class RvAdapter(
    private var animes: ArrayList<Datas>,
    private var isLoading: Boolean = false  ///

) : RecyclerView.Adapter<RvAdapter.AnimeViewHolder>() {
//    private val ITEM_VIEW = 1  ///
//    private val ITEM_VIEW_LOADING = 2  ///

    fun setData(updatedList: ArrayList<Datas>) {
        animes.addAll(updatedList)
        notifyDataSetChanged()
    }
//    override fun getItemViewType(position: Int): Int {   ///new
//        return if (position < animes.size) ITEM_VIEW else ITEM_VIEW_LOADING
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        return if (viewType == itemCount) {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item_raw, parent, false)
            AnimeViewHolder(view)
        } else {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.item_raw, parent, false)
            return AnimeViewHolder(view)

        }
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val animeItem = animes[position]
        holder.bind(animeItem)
    }

    class AnimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image_view_product: ImageView = itemView.findViewById(R.id.image_view_product)
        val text_name_product: TextView = itemView.findViewById(R.id.text_name_product)
        val text_small: TextView = itemView.findViewById(R.id.text_small)

        fun bind(datas: Datas) {

            val firstname: String = datas.email
            text_name_product.text = firstname

            val lastname: String = datas.lastname
            text_small.text = lastname

//            datas.titles.forEach {
//                val japaneseTitle = data.titles.find { it.type == "Japanese" }?.title
//                text_name_product.text = japaneseTitle ?: ""
//            }

            val avatar = datas.avatar
            Glide.with(itemView.context)
                .load(avatar)
                .into(image_view_product)

        }
    }

    override fun getItemCount(): Int {
        return if (isLoading) animes.size + 1
                else animes.size

    }

}

