package study.seo.a4netwroking_quakereportapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks
import android.content.Loader
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import study.seo.a4netwroking_quakereportapp.data.QuakeInfo

class MainActivity : AppCompatActivity(), LoaderCallbacks<MutableList<QuakeInfo>> {
    private val request_url =
        "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10"
    private val loader_id = 1
    private lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.earthquake_activity)

        listView = findViewById<ListView>(R.id.list)
        loaderManager.initLoader(loader_id, null, this)
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateLoader(
        id: Int,
        args: Bundle?
    ): android.content.Loader<MutableList<QuakeInfo>> =
        EarthquakeLoader(this, request_url)

    @Deprecated("Deprecated in Java")
    override fun onLoadFinished(
        loader: android.content.Loader<MutableList<QuakeInfo>>?,
        data: MutableList<QuakeInfo>?
    ) {
        findViewById<ProgressBar>(R.id.loading_bar).visibility = View.GONE
        data?.let {
            listView.adapter = QuakeListAdapter(this, it)
        } ?: findViewById<TextView>(R.id.empty_view).setText(R.string.no_earthquakes)
    }

    @Deprecated("Deprecated in Java")
    override fun onLoaderReset(loader: Loader<MutableList<QuakeInfo>>?) {
        loader?.reset()
    }
}




