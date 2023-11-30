package com.faqiy.quran.core.data

import com.faqiy.quran.core.data.network.NetworkBoundResource
import com.faqiy.quran.core.data.network.NetworkResponse
import com.faqiy.quran.core.data.network.RemoteDataSource
import com.faqiy.quran.core.domain.model.QuranEdition
import com.faqiy.quran.core.domain.model.Surah
import com.faqiy.quran.core.domain.repository.IQuranRepository
import com.faqiy.quran.core.network.quran.QuranEditionItem
import com.faqiy.quran.core.network.quran.SurahItem
import com.faqiy.quran.util.DataMapper
import kotlinx.coroutines.flow.Flow

class QuranRepository(private val remoteDataSource: RemoteDataSource) : IQuranRepository {
    override fun getListSurah(): Flow<Resource<List<Surah>>> {
        return object : NetworkBoundResource<List<Surah>, List<SurahItem>>(){
            override fun fetchFromNetwork(data: List<SurahItem>): Flow<List<Surah>> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<NetworkResponse<List<SurahItem>>> {
                return remoteDataSource.getListSurah()
            }
        }.asFlow()
    }

    override fun getDetailSurahWithQuranEdition(number: Int): Flow<Resource<List<QuranEdition>>> {
        return object : NetworkBoundResource<List<QuranEdition>, List<QuranEditionItem>>(){
            override fun fetchFromNetwork(data: List<QuranEditionItem>): Flow<List<QuranEdition>> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<NetworkResponse<List<QuranEditionItem>>> {
                return remoteDataSource.getDetailSurahWithQuranEditions(number)
            }
        }.asFlow()
    }
}