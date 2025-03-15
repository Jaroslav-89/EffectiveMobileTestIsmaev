package com.example.search.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.common.domain.model.Vacancy
import com.example.uikit.databinding.ItemVacancyBinding

class VacanciesAdapter(private val onVacancyClickListener: VacancyClickListener) :
    ListAdapter<Vacancy, VacanciesAdapter.VacanciesViewHolder>(VacanciesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacanciesViewHolder {
        val binding = ItemVacancyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VacanciesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VacanciesViewHolder, position: Int) {
        val vacancy = getItem(position)
        holder.bind(vacancy, onVacancyClickListener)
        holder.itemView.setOnClickListener {
            onVacancyClickListener.onVacancyClick(currentList[position])
        }
    }

    class VacanciesViewHolder(
        private val binding: ItemVacancyBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            vacancy: Vacancy,
            clickListener: VacancyClickListener,
        ) {
            with(binding) {
                favoriteBtn.setOnClickListener { clickListener.onVacancyClick(vacancy) }
                with(vacancy) {
                    if (lookingNumber != 0) {
                        peoplesNumber.text = lookingNumber.toString()
                        peoplesNumber.visibility = View.VISIBLE
                    }
                    if (title.isNotBlank()) {
                        vacancyName.text = title
                        vacancyName.visibility = View.VISIBLE
                    }

                    if (addressTown.isNotBlank()) {
                        city.text = addressTown
                        city.visibility = View.VISIBLE
                    }
                    if (company.isNotBlank()) {
                        companyName.text = company
                        companyName.visibility = View.VISIBLE
                        trustedCompanyIc.visibility = View.VISIBLE
                    }
                    if (experiencePreviewText.isNotBlank()) {
                        workExperience.text = experiencePreviewText
                        workExperience.visibility = View.VISIBLE
                        workExperienceIc.visibility = View.VISIBLE
                    }
                    if (publishedDate.isNotBlank()) {
                        publicationDate.text = publishedDate
                        publicationDate.visibility = View.VISIBLE
                    }
                    if (isFavorite)
                        favoriteBtn.setImageResource(com.example.uikit.R.drawable.ic_favorite_active)
                    else
                        favoriteBtn.setImageResource(com.example.uikit.R.drawable.ic_favorite_in_active)
                    salary.text = formatSalary(salaryShort)
                }
            }
        }
    }
}

private fun formatSalary(salary: String): String {
    return salary.replace(" до ", " - ")
}

fun interface VacancyClickListener {
    fun onVacancyClick(vacancy: Vacancy)
}

private class VacanciesDiffCallback : DiffUtil.ItemCallback<Vacancy>() {
    override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem == newItem
    }
}