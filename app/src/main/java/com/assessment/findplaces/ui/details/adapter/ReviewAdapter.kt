package com.assessment.findplaces.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.assessment.findplaces.databinding.ReviewCardBinding
import com.assessment.findplaces.domain.model.ReviewModel

class ReviewAdapter : ListAdapter<ReviewModel, ReviewAdapter.MyCustomViewHolder>(ReviewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomViewHolder {
        return MyCustomViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyCustomViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class MyCustomViewHolder private constructor(val binding: ReviewCardBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ReviewModel) {
            binding.review = item
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): MyCustomViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ReviewCardBinding.inflate(layoutInflater, parent, false)
                return MyCustomViewHolder(binding)
            }
        }
    }

    class ReviewDiffCallback: DiffUtil.ItemCallback<ReviewModel>(){
        override fun areItemsTheSame(oldItem: ReviewModel, newItem: ReviewModel): Boolean {
            return oldItem.time == newItem.time
        }
        override fun areContentsTheSame(oldItem: ReviewModel, newItem: ReviewModel): Boolean {
            return oldItem == newItem
        }
    }
}