package study.seo.a4netwroking_quakereportapp.data

import com.google.gson.annotations.SerializedName

data class QuakeProperty(
    @SerializedName("mag")
    val mag: Double,
    @SerializedName("place")
    val place: String,
    @SerializedName("time")
    val time: Long,
    @SerializedName("url")
    val url: String,
) {
    companion object {
        operator fun invoke(property: QuakeProperty): QuakeProperty {
            return with(property) {
                QuakeProperty(mag, (place ?: "USA"), time, url)
            }
        }
    }
}
