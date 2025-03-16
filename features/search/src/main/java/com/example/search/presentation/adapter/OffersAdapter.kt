package com.example.search.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.search.databinding.ItemOfferBinding
import com.example.search.domain.model.Offer

class OffersAdapter(private val onOfferClickListener: OfferClickListener) :
    ListAdapter<Offer, OffersAdapter.OffersViewHolder>(OffersDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersViewHolder {
        val binding = ItemOfferBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OffersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OffersViewHolder, position: Int) {
        val offer = getItem(position)
        holder.bind(offer)
        holder.itemView.setOnClickListener { onOfferClickListener.onOfferClick(currentList[position]) }
    }

    class OffersViewHolder(
        private val binding: ItemOfferBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(offer: Offer) {
            with(binding) {
                with(offer) {
                    when (id) {
                        "near_vacancies" -> {
                            offerIv.setImageResource(com.example.uikit.R.drawable.ic_jobs_nearby)
                        }

                        "level_up_resume" -> {
                            offerIv.setImageResource(com.example.uikit.R.drawable.ic_raise_resume)
                        }

                        "temporary_job" -> {
                            offerIv.setImageResource(com.example.uikit.R.drawable.ic_temporary_work)
                        }

                        else -> {
                            offerIv.setImageResource(com.example.uikit.R.drawable.ic_temporary_work)
                            offerIv.visibility = View.INVISIBLE
                        }
                    }
                    if (button.isBlank()) {
                        offerActionTv.visibility = View.GONE
                        offerDescriptionTv.maxLines = 3
                        offerDescriptionTv.text = title.trimStart()
                    } else {
                        offerActionTv.visibility = View.VISIBLE
                        offerDescriptionTv.maxLines = 2
                        offerDescriptionTv.text = title.trimStart()
                        offerActionTv.text = button
                    }
                }

            }
        }
    }
}

fun interface OfferClickListener {
    fun onOfferClick(offer: Offer)
}

private class OffersDiffCallback : DiffUtil.ItemCallback<Offer>() {
    override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
        return oldItem == newItem
    }
}