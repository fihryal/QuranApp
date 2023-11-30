package com.faqiy.quran.core.domain.repository

import com.faqiy.quran.core.data.Resource
import com.faqiy.quran.core.domain.model.QuranEdition
import com.faqiy.quran.core.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface IQuranRepository {
    fun getListSurah(): Flow<Resource<List<Surah>>>

    fun getDetailSurahWithQuranEdition(number: Int) : Flow<Resource<List<QuranEdition>>>

}