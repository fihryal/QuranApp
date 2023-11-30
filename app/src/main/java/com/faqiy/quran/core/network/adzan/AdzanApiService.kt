package com.faqiy.quran.core.network.adzan

import retrofit2.http.GET
import retrofit2.http.Path

interface AdzanApiService {

    @GET("sholat/kota/cari/{city}")
    suspend fun searchCity(
        @Path("city") city : String
    ):CityResponse

    @GET("sholat/jadwal/{idCity}/{year}/{month}/{date}")
    suspend fun getDetailAdzanTime(
        @Path("idkota") id: String,
        @Path("year") year: String,
        @Path("month") month: String,
        @Path("date") date: String
    ):DailyResponse
}