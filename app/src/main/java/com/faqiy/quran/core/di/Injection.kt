package com.faqiy.quran.core.di

import android.content.Context
import com.faqiy.quran.core.data.network.RemoteDataSource
import com.faqiy.quran.core.data.QuranRepository
import com.faqiy.quran.core.data.AdzanRepository
import com.faqiy.quran.core.data.local.CalendarPreferences
import com.faqiy.quran.core.data.local.LocationPreferences
import com.faqiy.quran.core.network.ApiConfig

object Injection {

    private val quranApiService = ApiConfig.quranApiService
    private val adzanApiService = ApiConfig.adzanApiService
    private val remoteDataSource = RemoteDataSource(quranApiService, adzanApiService)

    fun provideQuranRepository(): QuranRepository = QuranRepository(remoteDataSource)

    fun provideAdzanRepository(context: Context): AdzanRepository {
        val locationPreferences = LocationPreferences(context)
        val calendarPreferences = CalendarPreferences()
        return AdzanRepository(remoteDataSource, locationPreferences,calendarPreferences)
    }

}