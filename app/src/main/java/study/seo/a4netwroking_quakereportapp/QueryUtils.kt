package study.seo.a4netwroking_quakereportapp

import android.util.Log
import study.seo.a4netwroking_quakereportapp.data.QuakeInfo
import study.seo.a4netwroking_quakereportapp.data.QuakeProperty
import java.lang.NullPointerException

object QueryUtils {
    fun extractEarthquakes(earthQuakeInfo: QuakeInfo?): MutableList<QuakeProperty> {
        val quakeList: MutableList<QuakeProperty> = mutableListOf()
        try {
            earthQuakeInfo?.features?.forEach { feature ->
                quakeList.add(
                    QuakeProperty(feature.property)
                )
            }
        } catch (e: NullPointerException) {
            Log.e("QueryUtils", "Problem null", e)
        }
        return quakeList
    }
}