package com.rondi.core.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rondi.core.R
import com.rondi.core.databinding.CategoryItemBinding
import com.rondi.core.domain.model.Genre

@Suppress("DEPRECATION")
class GenreListAdapter : RecyclerView.Adapter<GenreListAdapter.ViewHolder>() {
    private val listGenre = ArrayList<Genre>()
    private var selectedPosition = -1

    fun setData(listGenre: List<Genre>) {
        val diffResult = DiffUtil.calculateDiff(calculateDiffCallback(this.listGenre, listGenre))
        this.listGenre.clear()
        this.listGenre.addAll(listGenre)
        diffResult.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreListAdapter.ViewHolder {
        val binding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreListAdapter.ViewHolder, position: Int) {
        holder.bind(listGenre[position])
    }

    override fun getItemCount(): Int = listGenre.size

    private fun calculateDiffCallback(
        oldList: List<Genre>,
        newList: List<Genre>
    ): DiffUtil.Callback {
        return object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = oldList.size

            override fun getNewListSize(): Int = newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition].id == newList[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == newList[newItemPosition]
            }
        }
    }

    inner class ViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(genre: Genre) {
            binding.tvCategoryName.text = genre.name
            binding.root.setOnClickListener {
                selectedPosition = position
                notifyDataSetChanged()
            }

            if (selectedPosition == position) {
                binding.tvCategoryName.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.colorGreen
                    )
                )
            } else {
                binding.tvCategoryName.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.black
                    )
                )
            }

        }
    }


}