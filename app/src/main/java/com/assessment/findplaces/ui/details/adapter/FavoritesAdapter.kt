package com.assessment.findplaces.ui.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.assessment.findplaces.databinding.FavoritesCardBinding
import com.assessment.findplaces.domain.model.PlaceModel

class FavoritesAdapter : ListAdapter<PlaceModel, FavoritesAdapter.MyCustomPlacesFavViewHolder>(ReviewDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomPlacesFavViewHolder {
        return MyCustomPlacesFavViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyCustomPlacesFavViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class MyCustomPlacesFavViewHolder private constructor(val binding: FavoritesCardBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: PlaceModel) {
            binding.place = item
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): MyCustomPlacesFavViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoritesCardBinding.inflate(layoutInflater, parent, false)
                return MyCustomPlacesFavViewHolder(binding)
            }
        }
    }

    class ReviewDiffCallback: DiffUtil.ItemCallback<PlaceModel>(){
        override fun areItemsTheSame(oldItem: PlaceModel, newItem: PlaceModel): Boolean {
            return oldItem.placeId == newItem.placeId
        }
        override fun areContentsTheSame(oldItem: PlaceModel, newItem: PlaceModel): Boolean {
            return oldItem == newItem
        }
    }
}