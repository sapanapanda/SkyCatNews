package com.example.catnews.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catnews.databinding.ItemStoryImageBinding
import com.example.catnews.databinding.ItemStoryParagraphBinding
import com.example.catnews.network.response.ContentItem

class StoryDetailAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // Define constants for the layout types
    private val type1 = "paragraph"
    private val type2 = "image"
    private val viewType1 = 1
    private val viewType2 = 2
    private val contentList = mutableListOf<ContentItem>()

    fun updateData(list: List<ContentItem>) {
        contentList.apply {
            clear()
            addAll(list)
            notifyDataSetChanged()
        }
    }

    inner class ParagraphViewHolder(private val binding: ItemStoryParagraphBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val paragraph = binding.tvParagraph
    }

    inner class ImageViewHolder(private val binding: ItemStoryImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image = binding.ivImage
    }

    // Determine the item type based on the position or data
    override fun getItemViewType(position: Int): Int {
        val item = contentList[position]
        return when (item.type) {
            type1 -> viewType1
            type2 -> viewType2
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }


    // Create ViewHolder by inflating the item layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        if (viewType == viewType1) {
            val binding = ItemStoryParagraphBinding.inflate(inflater, parent, false)
            return ParagraphViewHolder(binding)
        } else {
            val binding = ItemStoryImageBinding.inflate(inflater, parent, false)
            return ImageViewHolder(binding)
        }

    }

    // Bind the data to the views based on the item type
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = contentList[position]
        when (holder.itemViewType) {
            viewType1 -> {
                val type1ViewHolder = holder as ParagraphViewHolder
                type1ViewHolder.paragraph.text = item.text
                type1ViewHolder.paragraph.contentDescription = item.accessibilityText
            }

            viewType2 -> {
                val type2ViewHolder = holder as ImageViewHolder
            }
        }
    }


    // Return the number of items in the list
    override fun getItemCount(): Int {
        return contentList.size
    }
}
