package study.seo.a4netwroking_quakereportapp.data

import org.json.JSONObject

data class QuakeInfo(
    val mag: Double,
    val place: String,
    val time: Long,
    val url: String
) {
    companion object {
        operator fun invoke(property: JSONObject): QuakeInfo {
            return with(property) {
                QuakeInfo(
                    getDouble("mag"),
                    getString("place"),
                    getLong("time"),
                    getString("url"),
                )
            }
        }
    }
}
