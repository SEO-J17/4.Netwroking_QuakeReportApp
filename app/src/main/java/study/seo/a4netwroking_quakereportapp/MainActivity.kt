package study.seo.a4netwroking_quakereportapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.LoaderManager.LoaderCallbacks
import android.content.Loader
import android.view.View
import study.seo.a4netwroking_quakereportapp.data.QuakeInfo
import study.seo.a4netwroking_quakereportapp.databinding.EarthquakeActivityBinding

class MainActivity : AppCompatActivity(), LoaderCallbacks<MutableList<QuakeInfo>> {
    private val request_url =
        "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10"
    private val loader_id = 1
    private lateinit var binding: EarthquakeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EarthquakeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loaderManager.initLoader(loader_id, null, this)
    }

    override fun onCreateLoader(
        id: Int,
        args: Bundle?
    ): Loader<MutableList<QuakeInfo>> =
        EarthquakeLoader(this, request_url)

    override fun onLoadFinished(
        loader: Loader<MutableList<QuakeInfo>>?,
        data: MutableList<QuakeInfo>?
    ) {
        binding.loadingBar.visibility = View.GONE
        data?.let {
            binding.list.adapter = QuakeListAdapter(it)
        }
    }

    override fun onLoaderReset(loader: Loader<MutableList<QuakeInfo>>?) {
        loader?.reset()
    }
}




