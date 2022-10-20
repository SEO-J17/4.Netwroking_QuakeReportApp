package study.seo.a4netwroking_quakereportapp

import android.content.Context
import study.seo.a4netwroking_quakereportapp.data.QuakeInfo

class EarthquakeLoader(context: Context, val url: String) :
    android.content.AsyncTaskLoader
    <MutableList<QuakeInfo>>(context) {
    override fun onStartLoading() {
        forceLoad()
    }

    override fun loadInBackground(): MutableList<QuakeInfo>? {
        return if (url.isEmpty()) {
            null
        } else {
            QueryUtils.fetchEarthquakeData(url)
        }
    }
}