package com.example.favorite.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.common.domain.model.Vacancy
import com.example.uikit.R
import com.example.uikit.databinding.ItemVacancyBinding

class FavoriteVacanciesAdapter(private val onVacancyClickListener: VacancyClickListener) :
    androidx.recyclerview.widget.ListAdapter<Vacancy, FavoriteVacanciesAdapter.FavoriteVacanciesViewHolder>(
        VacanciesDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteVacanciesViewHolder {
        val binding = ItemVacancyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoriteVacanciesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteVacanciesViewHolder, position: Int) {
        val vacancy = getItem(position)
        holder.bind(vacancy, onVacancyClickListener)
        holder.itemView.setOnClickListener {
            onVacancyClickListener.onVacancyClick(currentList[position])
        }
    }

    class FavoriteVacanciesViewHolder(
        private val binding: ItemVacancyBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            vacancy: Vacancy,
            clickListener: VacancyClickListener,
        ) {
            with(binding) {
                favoriteBtn.setOnClickListener {
                    clickListener.onFavoriteClick(vacancy)
                }

                with(vacancy) {
                    if (lookingNumber != 0) {
                        val numOfPeoples = itemView.resources.getQuantityString(
                            R.plurals.peoples_number,
                            lookingNumber,
                            lookingNumber
                        )
                        peoplesNumber.text = numOfPeoples
                        peoplesNumber.visibility = View.VISIBLE
                    } else peoplesNumber.visibility = View.GONE
                    if (title.isNotBlank()) {
                        vacancyName.text = title
                        vacancyName.visibility = View.VISIBLE
                    } else vacancyName.visibility = View.GONE

                    if (addressTown.isNotBlank()) {
                        city.text = addressTown
                        city.visibility = View.VISIBLE
                    } else city.visibility = View.GONE
                    if (company.isNotBlank()) {
                        companyName.text = company
                        companyName.visibility = View.VISIBLE
                        trustedCompanyIc.visibility = View.VISIBLE
                    } else {
                        companyName.visibility = View.GONE
                        trustedCompanyIc.visibility = View.GONE
                    }
                    if (experiencePreviewText.isNotBlank()) {
                        workExperience.text = experiencePreviewText
                        workExperience.visibility = View.VISIBLE
                        workExperienceIc.visibility = View.VISIBLE
                    } else {
                        workExperience.visibility = View.GONE
                        workExperienceIc.visibility = View.GONE
                    }
                    if (publishedDate.isNotBlank()) {
                        val context = itemView.context
                        val publishedText = context.getString(
                            R.string.vacancy_item_published_tv,
                            vacancy.publishedDate
                        )
                        publicationDate.text = publishedText
                        publicationDate.visibility = View.VISIBLE
                    } else publicationDate.visibility = View.GONE
                    if (isFavorite)
                        favoriteBtn.setImageResource(R.drawable.ic_favorite_active)
                    else
                        favoriteBtn.setImageResource(R.drawable.ic_favorite_in_active)
                    salary.text = formatSalary(salaryShort)
                }
            }
        }
    }

    interface VacancyClickListener {
        fun onVacancyClick(vacancy: Vacancy)
        fun onFavoriteClick(vacancy: Vacancy)
    }
}

private fun formatSalary(salary: String): String {
    return salary.replace(" до ", " - ")
}

private class VacanciesDiffCallback : DiffUtil.ItemCallback<Vacancy>() {
    override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
        return oldItem == newItem
    }
}