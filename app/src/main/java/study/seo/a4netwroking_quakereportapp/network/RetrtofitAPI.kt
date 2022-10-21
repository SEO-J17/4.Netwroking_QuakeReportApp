package study.seo.a4netwroking_quakereportapp.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import study.seo.a4netwroking_quakereportapp.data.QuakeInfo

interface RetrtofitAPI {
    @GET("/fdsnws/event/1/query")
    fun getQuakeInfo(
        @Query("format") format: String,
        @Query("orderby") orderBy: String,
        @Query("minmag") minMag: Int,
        @Query("limit") limit: Int,
    ): Call<QuakeInfo>
}