package com.example.catnews.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catnews.databinding.ItemNewListBinding
import com.example.catnews.network.response.CatNewsItem


class NewsListAdapter(private val listener: OnClickListener) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private val newsList = mutableListOf<CatNewsItem>()


    fun updateData(list: List<CatNewsItem>) {
        newsList.apply {
            clear()
            addAll(list)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(private val binding: ItemNewListBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(catNewsItem: CatNewsItem, onClickListener: OnClickListener) {
            binding.apply {
                tvHeadline.text = catNewsItem.headline
                tvTeaser.text = catNewsItem.teaserText
                tvCreationDate.text = catNewsItem.creationDate
                ivItem.contentDescription = catNewsItem.accessibilityText

                binding.container.setOnClickListener {
                    catNewsItem.id?.let {
                        onClickListener.onItemClicked(it)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsItem = newsList[position]
        holder.bind(newsItem, listener)

    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}
