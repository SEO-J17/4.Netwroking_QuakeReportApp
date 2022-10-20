package study.seo.a4netwroking_quakereportapp

import android.util.Log
import org.json.JSONException
import org.json.JSONObject
import study.seo.a4netwroking_quakereportapp.data.QuakeInfo
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.nio.charset.Charset

object QueryUtils {
    private fun createURL(stringUrl: String): URL? {
        return try {
            URL(stringUrl)
        } catch (e: MalformedURLException) {
            Log.e("QueryUtils", "Problem building the URL", e)
            return null
        }
    }


    private fun makeHttpRequest(url: URL): String {
        try {
            (url.openConnection() as? HttpURLConnection)?.apply {
                readTimeout = 10000
                connectTimeout = 15000
                requestMethod = "GET"
                connect()

                if (responseCode == 200) {
                    inputStream?.let { inputStream ->
                        return readFromStream(inputStream)
                    }
                }
            } ?: Log.e("QueryUtils", "url connection이 NULL입니다")
        } catch (e: IOException) {
            Log.e("QueryUtils", "Problem retrieving the earthquake JSON results.", e)
        }
        return ""
    }


    private fun readFromStream(inputStream: InputStream): String {
        val result = StringBuilder()
        BufferedReader(
            InputStreamReader(
                inputStream,
                Charset.forName("UTF-8")
            )
        ).forEachLine { line ->
            result.append(line)
        }

        return result.toString()
    }


    private fun extractEarthquakes(earthQuakeJSON: String): MutableList<QuakeInfo>? {
        val quakeList: MutableList<QuakeInfo> = mutableListOf()
        if (earthQuakeJSON.isEmpty()) return null
        try {
            JSONObject(earthQuakeJSON).getJSONArray("features").apply {
                for (index in 0 until length()) {
                    val property = getJSONObject(index).getJSONObject("properties")
                    quakeList.add(QuakeInfo(property))
                }
            }
        } catch (e: JSONException) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e)
        }
        return quakeList
    }


    fun fetchEarthquakeData(requestUrl: String): MutableList<QuakeInfo>? {
        try {
            return createURL(requestUrl)?.let { makeHttpRequest(it) }
                ?.let { extractEarthquakes(it) }
        } catch (e: IOException) {
            Log.e("QueryUtils", "Problem making the HTTP request.", e);
        }
        return null
    }
}