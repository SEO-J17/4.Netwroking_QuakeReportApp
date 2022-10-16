package study.seo.a4netwroking_quakereportapp

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import study.seo.a4netwroking_quakereportapp.data.QuakeInfo

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUSET_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.earthquake_activity)

        val listView = findViewById<ListView>(R.id.list)
        EarthquakeAsyncTask().execute(REQUSET_URL).get()?.let {
            val listAdapter = QuakeListAdapter(this, it)
            listView.adapter = listAdapter
        }

        //listView.emptyView = findViewById<TextView>(R.id.empty_view)
        //EarthquakeAsyncTask().execute(QueryUtils.toString())
        //val dataSet = QueryUtils.toString()
        //earthquakeListView.adapter = QuakeListAdapter(this, dataSet)

    }

    @SuppressLint("StaticFieldLeak")
    inner class EarthquakeAsyncTask : AsyncTask<String, Unit, MutableList<QuakeInfo>>() {
        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg urls: String?): MutableList<QuakeInfo>? {
            if (urls.isEmpty()) return null
            return urls[0]?.let { QueryUtils().fetchEarthquakeData(it) }
        }

        @Deprecated("Deprecated in Java")
        override fun onPostExecute(data: MutableList<QuakeInfo>?) {
            data?.let {
                findViewById<ListView>(R.id.list).adapter =
                    QuakeListAdapter(this@MainActivity, data)
            }
        }
    }
}