package study.seo.a4netwroking_quakereportapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import study.seo.a4netwroking_quakereportapp.data.QuakeInfo

class QuakeListAdapter(val context: Context, val dataSet: MutableList<QuakeInfo>) : BaseAdapter() {
    override fun getCount(): Int = dataSet.size

    override fun getItem(position: Int): Any = dataSet[position]

    override fun getItemId(p0: Int): Long {
        return 0
    }

    @SuppressLint("ViewHolder", "ResourceAsColor")
    override fun getView(positon: Int, p1: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.earthquake_list_item, null)

        val info = dataSet[positon]
        val mag = view.findViewById<TextView>(R.id.magnitude)
        val area = view.findViewById<TextView>(R.id.quake_area)
        val date = view.findViewById<TextView>(R.id.quake_date)
        val time = view.findViewById<TextView>(R.id.quake_time)
        val mUrl = info.url


        view.setOnClickListener(View.OnClickListener {
            val mUri = Uri.parse(mUrl)
            val goWeb = Intent(Intent.ACTION_VIEW, mUri)
            goWeb.run { context.startActivity(goWeb) }
        })

        //TODO:컬러안바뀌는 오류 해결하기
        val gradientDrawable: GradientDrawable = mag.background as GradientDrawable
        gradientDrawable.setColor(R.color.magnitude7)


        mag.text = Formatter().formatDecimal(info.mag)
        if (info.place.contains("of")) {
            val temp = info.place.split("of")
            area.text = temp[0]
            view.findViewById<TextView>(R.id.primary_area).text = temp[1]
        } else {
            area.text = info.place
        }
        date.text = Formatter().formatDate(info.time)
        time.text = Formatter().formatTime(info.time)

        return view
    }
}