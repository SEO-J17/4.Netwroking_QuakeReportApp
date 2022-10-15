package study.seo.a4netwroking_quakereportapp

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class Formatter {
    fun formatDate(target: Long): String {
        val dateObject = Date(target)
        return SimpleDateFormat("MMM DD, yyyy").format(dateObject)
    }

    fun formatTime(target: Long): String {
        val dateObject = Date(target)
        return SimpleDateFormat("h:mm, a").format(dateObject)
    }

    fun formatDecimal(target: Double): String {
        val doubleNum = target * 2.0
        val formatter = DecimalFormat("0.00")
        return formatter.format(doubleNum)
    }
}