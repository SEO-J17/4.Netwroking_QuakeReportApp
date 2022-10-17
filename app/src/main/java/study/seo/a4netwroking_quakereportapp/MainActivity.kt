package study.seo.a4netwroking_quakereportapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks
import android.content.Loader
import android.view.View
import android.widget.ListView
import android.widget.TextView
import study.seo.a4netwroking_quakereportapp.data.QuakeInfo

class MainActivity : AppCompatActivity(), LoaderCallbacks<MutableList<QuakeInfo>> {
    private val request_url =
        "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10"
    private val loader_id = 1
    private lateinit var listView: ListView
    private lateinit var emptyTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.earthquake_activity)

        listView = findViewById<ListView>(R.id.list)
        emptyTextView = findViewById<TextView>(R.id.empty_view).apply {
            setText(R.string.no_earthquakes)
        }

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
        data?.let {
            emptyTextView.visibility = View.INVISIBLE
            listView.adapter = QuakeListAdapter(this, it)
        } ?: run { emptyTextView.visibility = View.VISIBLE }
    }

    @Deprecated("Deprecated in Java")
    override fun onLoaderReset(loader: Loader<MutableList<QuakeInfo>>?) {
        loader?.reset()
    }
}




