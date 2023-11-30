package com.faqiy.quran.presentation.Adzan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.faqiy.quran.core.data.AdzanRepository
import com.faqiy.quran.core.data.Resource
import com.faqiy.quran.core.domain.model.DailyAdzanResult

class AdzanViewModel (private val adzanRepository: AdzanRepository): ViewModel() {

    fun getDetailAdzanTime(): LiveData<Resource<DailyAdzanResult>> =
        adzanRepository.getDailyAdzanTimeResult()
}