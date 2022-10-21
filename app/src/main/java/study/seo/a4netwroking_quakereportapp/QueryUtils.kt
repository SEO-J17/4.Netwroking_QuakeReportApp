package study.seo.a4netwroking_quakereportapp

import android.util.Log
import org.json.JSONException
import study.seo.a4netwroking_quakereportapp.data.QuakeInfo
import study.seo.a4netwroking_quakereportapp.data.QuakeProperty

object QueryUtils {
    fun extractEarthquakes(earthQuakeInfo: QuakeInfo?): MutableList<QuakeProperty> {
        val quakeList: MutableList<QuakeProperty> = mutableListOf()
        try {
            earthQuakeInfo?.features?.forEach { feature ->
                quakeList.add(
                    QuakeProperty(feature.property)
                )
            }
        } catch (e: JSONException) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e)
        }
        return quakeList
    }

}