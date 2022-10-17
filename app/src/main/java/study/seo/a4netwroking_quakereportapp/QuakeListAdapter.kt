package study.seo.a4netwroking_quakereportapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import study.seo.a4netwroking_quakereportapp.data.QuakeInfo

class QuakeListAdapter(val context: Context, val dataSet: MutableList<QuakeInfo>) : BaseAdapter() {
    override fun getCount(): Int = dataSet.size

    override fun getItem(position: Int): Any = dataSet[position]

    override fun getItemId(p0: Int): Long = 0

    @SuppressLint("ViewHolder", "ResourceAsColor")
    override fun getView(positon: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater
            .from(context)
            .inflate(R.layout.earthquake_list_item, parent, false)


        with(dataSet[positon]) {
            view.findViewById<TextView>(R.id.magnitude)
                .text = Formatter().formatDecimal(mag)


            view.findViewById<TextView>(R.id.quake_area).apply {
                val primaryView = view.findViewById<TextView>(R.id.primary_area)
                val separator = " of "
                if (place.contains(separator)) {
                    val str = place.split(separator)
                    text = str[0] + separator
                    primaryView.text = str[1]
                } else {
                    visibility = View.GONE
                    primaryView.text = place
                }
            }

            view.findViewById<TextView>(R.id.quake_date)
                .text = Formatter().formatDate(time)


            view.findViewById<TextView>(R.id.quake_time)
                .text = Formatter().formatTime(time)


            view.setOnClickListener {
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(url)
                ).apply { context.startActivity(this@apply) }
            }
        }

//        //TODO:컬러안바뀌는 오류 해결하기
//        val gradientDrawable: GradientDrawable = mag.background as GradientDrawable
//        gradientDrawable.setColor(R.color.magnitude7)

        return view
    }
}