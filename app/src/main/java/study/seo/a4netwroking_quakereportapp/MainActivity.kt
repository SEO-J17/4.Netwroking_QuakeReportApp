package study.seo.a4netwroking_quakereportapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import study.seo.a4netwroking_quakereportapp.data.QuakeInfo
import study.seo.a4netwroking_quakereportapp.databinding.EarthquakeActivityBinding
import study.seo.a4netwroking_quakereportapp.network.RetrtofitAPI

class MainActivity : AppCompatActivity() {
    private val request_url =
        "https://earthquake.usgs.gov"
    private lateinit var binding: EarthquakeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EarthquakeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder().baseUrl(request_url)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(RetrtofitAPI::class.java)

        api.getQuakeInfo("geojson", "time", 5, 10).enqueue(object : Callback<QuakeInfo> {
            override fun onResponse(call: Call<QuakeInfo>, response: Response<QuakeInfo>) {
                if (response.isSuccessful) {
                    binding.list.adapter =
                        QuakeListAdapter(QueryUtils.extractEarthquakes(response.body()))
                    binding.loadingBar.visibility = View.GONE
                } else {
                    Log.e("SEOJW", "실패!! ")
                }
            }

            override fun onFailure(call: Call<QuakeInfo>, t: Throwable) {
                Log.e("SEOJW", "onFailure 에러: " + t.message.toString());
            }
        })
    }
}




