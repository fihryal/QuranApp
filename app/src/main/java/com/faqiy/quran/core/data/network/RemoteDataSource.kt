package com.faqiy.quran.core.data.network

import android.util.Log
import com.faqiy.quran.core.network.adzan.AdzanApiService
import com.faqiy.quran.core.network.adzan.CityItem
import com.faqiy.quran.core.network.adzan.JadwalItem
import com.faqiy.quran.core.network.quran.QuranApiService
import com.faqiy.quran.core.network.quran.QuranEditionItem
import com.faqiy.quran.core.network.quran.SurahItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class RemoteDataSource(
    private val quranApiService: QuranApiService,
    private val adzanApiService: AdzanApiService
) {
    suspend fun getListSurah(): Flow<NetworkResponse<List<SurahItem>>> =
        flow {
            try {
                val surahResponse = quranApiService.getListSurah()
                val listSurah = surahResponse.listSurah
                emit(NetworkResponse.Success(listSurah))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, "error: " + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDetailSurahWithQuranEditions(number: Int): Flow<NetworkResponse<List<QuranEditionItem>>> =
        flow {
            try {
                val ayahResponse = quranApiService.getDetailSurahWithQuranEdition(number)
                val quranEdition = ayahResponse.quranEditionItem
                emit(NetworkResponse.Success(quranEdition))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, "error " + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun searchCity(city:String):Flow<NetworkResponse<List<CityItem>>> =
        flow {
            try {
                val cityResponse = adzanApiService.searchCity(city)
                val cities = cityResponse.listCity
                emit(NetworkResponse.Success(cities))
            }catch (e: Exception) {
                emit(NetworkResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, "error: " + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDailyAdzanTime(id: String, year:String,month:String, date:String):Flow<NetworkResponse<JadwalItem>> = flow<NetworkResponse<JadwalItem>> {
        try {
            val dailyResponse = adzanApiService.getDetailAdzanTime(id,year,month,date)
            val jadwalResponse = dailyResponse.data.jadwalItem
            emit(NetworkResponse.Success(jadwalResponse))
        }catch (e: Exception){
            emit(NetworkResponse.Error(e.toString()))
            Log.e(RemoteDataSource::class.java.simpleName, "error: " + e.localizedMessage)
        }
    }.flowOn(Dispatchers.IO)

}