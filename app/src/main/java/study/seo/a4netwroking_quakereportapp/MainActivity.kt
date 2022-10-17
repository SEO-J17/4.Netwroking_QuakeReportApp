package study.seo.a4netwroking_quakereportapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks
import android.content.Loader
import android.util.Log
import android.widget.ListAdapter
import android.widget.ListView
import study.seo.a4netwroking_quakereportapp.data.QuakeInfo
import java.lang.NullPointerException

class MainActivity : AppCompatActivity(), LoaderCallbacks<MutableList<QuakeInfo>> {
    private val request_url =
        "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10"
    private val loader_id = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.earthquake_activity)

//        EarthquakeAsyncTask().execute(REQUSET_URL).get()?.let { dataSet ->
//            findViewById<ListView>(R.id.list).adapter = QuakeListAdapter(this, dataSet)
//        }

        loaderManager.initLoader(loader_id, null, this)

        //listView.emptyView = findViewById<TextView>(R.id.empty_view)
        //EarthquakeAsyncTask().execute(QueryUtils.toString())
        //val dataSet = QueryUtils.toString()
        //earthquakeListView.adapter = QuakeListAdapter(this, dataSet)


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
        try {
            data?.let {
                findViewById<ListView>(R.id.list).adapter = QuakeListAdapter(this, it)
            }
        } catch (e: NullPointerException) {
            Log.e("Main", "data가 null입니다.")
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onLoaderReset(loader: Loader<MutableList<QuakeInfo>>?) {
        loader?.reset()
    }


//    @SuppressLint("StaticFieldLeak")
//    inner class EarthquakeAsyncTask : AsyncTask<String, Unit, MutableList<QuakeInfo>>() {
//        @Deprecated("Deprecated in Java")
//        override fun doInBackground(vararg urls: String?): MutableList<QuakeInfo>? {
//            if (urls.isEmpty()) return null
//            return urls[0]?.let { QueryUtils().fetchEarthquakeData(it) }
//        }
//
//        @Deprecated("Deprecated in Java")
//        override fun onPostExecute(data: MutableList<QuakeInfo>?) {
//            data?.let {
//                findViewById<ListView>(R.id.list).adapter =
//                    QuakeListAdapter(this@MainActivity, data)
//            }
//        }
}



