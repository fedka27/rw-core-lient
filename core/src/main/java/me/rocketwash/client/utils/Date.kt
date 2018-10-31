package me.rocketwash.client.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


const val dateTimePattern: String = "yyyy-MM-dd HH:mm:ss"
const val displayDateTimePattern: String = "dd MMM yyyy HH:mm:ss"

fun String.toDateWithFormat(patternOfDate: String): Date? {
    return try {
        SimpleDateFormat(patternOfDate, Locale.getDefault()).parse(this)
    } catch (e: ParseException) {
        log_e(e.localizedMessage)
        null
    }
}

/**
 * @return display date as [displayDateTimePattern]
 * */
fun Date.toDisplayFormat(): String? {
    return this.toSimpleFormat(displayDateTimePattern)
}

fun Date.toDateTimeFormat(): String {
    return SimpleDateFormat(dateTimePattern, Locale.getDefault()).format(this)
}

fun Date.toSimpleFormat(pattern: String): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(this)
}

/**
 * @param year - is year
 * @param month - is month
 * @param dayOfMonth - is day of month
 * @return Calendar [Calendar] with date of params
 * */
fun getCalendarFromArgs(year: Int, month: Int, dayOfMonth: Int): Calendar {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, dayOfMonth)
    return calendar
}

fun Date?.equalsIgnoreTime(date: Date?): Boolean {
    if (date == null || this == null) return false
    val calendar1 = Calendar.getInstance().apply {
        time = this@equalsIgnoreTime
    }
    val calendar2 = Calendar.getInstance().apply {
        time = date
    }

    return calendar1.equalsDateIgnoreTime(calendar2)
}

fun Calendar.equalsDayOfMonth(calendar: Calendar): Boolean {
    return this[Calendar.DAY_OF_MONTH] == calendar[Calendar.DAY_OF_MONTH]
}

fun Calendar.equalsMonth(calendar: Calendar): Boolean {
    return this[Calendar.MONTH] == calendar[Calendar.MONTH]
}

fun Calendar.equalsYear(calendar: Calendar): Boolean {
    return this[Calendar.YEAR] == calendar[Calendar.YEAR]
}

fun Calendar.equalsDateIgnoreTime(calendar: Calendar): Boolean {
    return this.equalsYear(calendar)
            && this.equalsMonth(calendar)
            && this.equalsDayOfMonth(calendar)
}