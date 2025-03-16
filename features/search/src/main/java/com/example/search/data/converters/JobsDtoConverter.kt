package com.example.search.data.converters

import com.example.common.domain.model.Vacancy
import com.example.network.dto.JobsResponse
import com.example.network.dto.OfferDto
import com.example.network.dto.VacancyDto
import com.example.uikit.R
import com.example.search.domain.model.Jobs
import com.example.search.domain.model.Offer
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

object JobsDtoConverter {
    fun convert(response: JobsResponse): Jobs {
        return Jobs(
            offers = response.offers?.map { offerDto ->
                convertOffer(offerDto)
            } ?: emptyList(),
            vacancies = response.vacancies?.map { vacancyDto ->
                convertVacancy(vacancyDto)
            } ?: emptyList()
        )
    }

    private fun convertOffer(offerDto: OfferDto): Offer {
        return Offer(
            id = offerDto.id ?: "",
            title = offerDto.title ?: "",
            link = offerDto.link ?: "",
            button = offerDto.button?.text ?: "",
        )
    }

    private fun convertVacancy(vacancyDto: VacancyDto): Vacancy {
        return Vacancy(
            id = vacancyDto.id,
            lookingNumber = vacancyDto.lookingNumber ?: 0,
            title = vacancyDto.title ?: "",
            addressTown = vacancyDto.address?.town ?: "",
            addressStreet = vacancyDto.address?.street ?: "",
            addressHouse = vacancyDto.address?.house ?: "",
            company = vacancyDto.company ?: "",
            experiencePreviewText = vacancyDto.experience?.previewText ?: "",
            experienceText = vacancyDto.experience?.text ?: "",
            publishedDate = vacancyDto.publishedDate?.toUiPublishedDate() ?: "",
            isFavorite = vacancyDto.isFavorite ?: false,
            salaryShort = vacancyDto.salary?.short ?: "",
            salaryFull = vacancyDto.salary?.full ?: "",
            schedules = vacancyDto.schedules ?: emptyList(),
            appliedNumber = vacancyDto.appliedNumber ?: 0,
            description = vacancyDto.description ?: "",
            responsibilities = vacancyDto.responsibilities ?: "",
            questions = vacancyDto.questions ?: emptyList(),
        )
    }
}

private fun String.toUiPublishedDate(): String {
    return try {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("d MMMM", Locale.getDefault())
        val date = LocalDate.parse(this, inputFormatter)
        date.format(outputFormatter)
    } catch (e: Exception) {
        ""
    }
}
