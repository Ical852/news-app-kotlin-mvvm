package com.lazday.news.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lazday.news.R
import com.lazday.news.databinding.AdapterCategoryBinding
import com.lazday.news.source.news.CategoryModel

class CategoryAdapter(
    private val categories: List<CategoryModel>,
    private val listener: OnAdapterListener
): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    interface OnAdapterListener {
        fun onClick(category: CategoryModel)
    }
    private val items = arrayListOf<TextView>()
    class ViewHolder(val binding: AdapterCategoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.binding.category.text = category.name
        items.add(holder.binding.category)
        holder.itemView.setOnClickListener {
            listener.onClick(category)
            setColor(holder.binding.category)
        }
        setColor(items[0])
    }

    override fun getItemCount() = categories.size

    private fun setColor(textView: TextView) {
        items.forEach { it.setBackgroundResource(R.color.white) }
        textView.setBackgroundResource(android.R.color.darker_gray)
    }
}