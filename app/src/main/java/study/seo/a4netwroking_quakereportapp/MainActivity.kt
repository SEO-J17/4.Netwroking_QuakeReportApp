package study.seo.a4netwroking_quakereportapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find a reference to the {@link ListView} in the layout
        val earthquakeListView = findViewById<ListView>(R.id.list)

        // Create a new {@link ArrayAdapter} of earthquakes
        val dataSet = QueryUtils.extractEarthquakes()

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.adapter = QuakeListAdapter(this, dataSet)

    }
}