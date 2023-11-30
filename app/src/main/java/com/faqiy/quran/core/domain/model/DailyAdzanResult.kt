package com.faqiy.quran.core.domain.model

import com.faqiy.quran.core.data.Resource

data class DailyAdzanResult(
    val listAddress: List<String>,
    val adzanTime: Resource<Jadwal>,
    val currentDdate: List<String>
)
