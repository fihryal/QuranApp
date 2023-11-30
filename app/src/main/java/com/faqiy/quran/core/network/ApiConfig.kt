package com.faqiy.quran.core.network

import com.faqiy.quran.core.network.adzan.AdzanApiService
import com.faqiy.quran.core.network.quran.QuranApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiConfig {

    private inline fun <reified T> createApiConfig (baseUrl: String): T{
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(T::class.java)
    }

    val quranApiService = createApiConfig<QuranApiService>("https://api.alquran.cloud/v1/")
    val adzanApiService = createApiConfig<AdzanApiService>("https://api.myquran.com/v1/")

}