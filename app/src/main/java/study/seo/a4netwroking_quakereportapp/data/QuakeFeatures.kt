package study.seo.a4netwroking_quakereportapp.data

import com.google.gson.annotations.SerializedName

data class QuakeFeatures(
    @SerializedName("properties")
    val property: QuakeProperty,
)