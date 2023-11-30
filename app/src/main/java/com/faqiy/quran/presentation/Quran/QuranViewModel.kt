package com.faqiy.quran.presentation.Quran

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.faqiy.quran.core.data.QuranRepository
import com.faqiy.quran.core.data.Resource
import com.faqiy.quran.core.domain.model.QuranEdition
import com.faqiy.quran.core.domain.model.Surah
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuranViewModel(private val quranRepository: QuranRepository) : ViewModel() {
    fun getListSurah(): LiveData<Resource<List<Surah>>> = quranRepository.getListSurah().asLiveData()

    fun getDetailSurahWithQuranEdition(number: Int): LiveData<Resource<List<QuranEdition>>> =
        quranRepository.getDetailSurahWithQuranEdition(number).asLiveData()
}