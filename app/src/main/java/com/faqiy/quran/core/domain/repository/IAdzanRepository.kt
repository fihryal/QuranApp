package com.faqiy.quran.core.domain.repository

import androidx.lifecycle.LiveData
import com.faqiy.quran.core.data.Resource
import com.faqiy.quran.core.domain.model.City
import com.faqiy.quran.core.domain.model.Jadwal
import kotlinx.coroutines.flow.Flow

interface IAdzanRepository {

    fun getLocation(): LiveData<List<String>>
    fun searchCity(city: String): Flow<Resource<List<City>>>
    fun getDAilyAdzanTime(
        id: String,
        year: String,
        month: String,
        date: String
    ): Flow<Resource<Jadwal>>
}