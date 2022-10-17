package study.seo.a4netwroking_quakereportapp

import android.content.Context
import androidx.loader.content.AsyncTaskLoader
import study.seo.a4netwroking_quakereportapp.data.QuakeInfo

class EarthquakeLoader(context: Context, val url: String) :
    android.content.AsyncTaskLoader
    <MutableList<QuakeInfo>>(context) {
    @Deprecated("Deprecated in Java")
    override fun onStartLoading() {
        forceLoad()
    }

    @Deprecated("Deprecated in Java")
    override fun loadInBackground(): MutableList<QuakeInfo>? {
        return if (url.isEmpty()) {
            null
        } else {
            QueryUtils().fetchEarthquakeData(url)
        }
    }
}