package study.seo.a4netwroking_quakereportapp

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import study.seo.a4netwroking_quakereportapp.data.QuakeInfo
import study.seo.a4netwroking_quakereportapp.databinding.EarthquakeListItemBinding

class QuakeListAdapter(val dataSet: MutableList<QuakeInfo>) : BaseAdapter() {
    private lateinit var binding: EarthquakeListItemBinding
    private lateinit var bindingRootView: View

    override fun getCount(): Int = dataSet.size

    override fun getItem(position: Int): QuakeInfo = dataSet[position]

    override fun getItemId(position: Int): Long = 0

    override fun getView(positon: Int, convertView: View?, parent: ViewGroup): View {
        convertView ?: run {
            binding = EarthquakeListItemBinding.inflate(parent.layoutInflater, parent, false)
            bindingRootView = binding.root.rootView
        }

        with(dataSet[positon]) {
            binding.magnitude.apply {
                text = Formatter.formatDecimal(mag)
                (background as GradientDrawable).setColor(
                    resources.getColor(
                        when (mag) {
                            in 0.0..1.99 -> R.color.magnitude1
                            in 2.0..2.99 -> R.color.magnitude2
                            in 3.0..3.99 -> R.color.magnitude3
                            in 4.0..4.99 -> R.color.magnitude4
                            in 5.0..5.99 -> R.color.magnitude5
                            in 6.0..6.99 -> R.color.magnitude6
                            in 7.0..7.99 -> R.color.magnitude7
                            in 8.0..8.99 -> R.color.magnitude8
                            in 9.0..9.99 -> R.color.magnitude9
                            else -> R.color.magnitude10plus
                        }
                    )
                )
            }

            val areaView = binding.quakeArea
            val primaryView = binding.primaryArea
            val separator = " of "
            if (place.contains(separator)) {
                val str = place.split(separator)
                areaView.text = "${str[0]} $separator"
                primaryView.text = str[1]
            } else {
                areaView.visibility = View.GONE
                primaryView.text = place
            }

            binding.quakeDate
                .text = Formatter.formatDate(time)

            binding.quakeTime
                .text = Formatter.formatTime(time)

            bindingRootView.setOnClickListener {
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(url)
                ).apply { parent.context.startActivity(this@apply) }
            }
        }

        return bindingRootView
    }

    private inline val ViewGroup.layoutInflater: LayoutInflater
        get() = LayoutInflater.from(context)

}

