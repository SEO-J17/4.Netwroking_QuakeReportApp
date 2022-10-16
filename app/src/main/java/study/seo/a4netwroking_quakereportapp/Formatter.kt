package study.seo.a4netwroking_quakereportapp

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class Formatter {
    fun formatDate(target: Long): String {
        return SimpleDateFormat("MMM DD, yyyy").format(Date(target))
    }

    fun formatTime(target: Long): String {
        return SimpleDateFormat("h:mm, a").format(Date(target))
    }

    fun formatDecimal(target: Double): String {
        return DecimalFormat("0.00").format(target)
    }
}