package com.example.siyai_front_android.utils

import android.icu.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

/**
 * Проверяет, прошло ли необходимое кол-во часов от даты
 */
fun Date.isHoursPassed(hours: Int): Boolean {
    val now = Date()
    val diffInMillis = now.time - this.time
    val hoursPassed = TimeUnit.MILLISECONDS.toHours(diffInMillis)
    return hoursPassed >= hours
}

private const val ISO_DATE = "yyyy-MM-dd"
private const val ISO_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ssZ"

fun Date.toISODateString(): String {
    return SimpleDateFormat(ISO_DATE, Locale.getDefault())
        .format(this)
}

fun Date.toISODateTimeString(): String {
    return SimpleDateFormat(ISO_DATE_TIME, Locale.getDefault())
        .format(this)
}

fun String.parseISODate(): Date {
    return SimpleDateFormat(ISO_DATE, Locale.getDefault())
        .parse(this)
}

fun String.parseISODateTime(): Date {
    return SimpleDateFormat(ISO_DATE_TIME, Locale.getDefault())
        .parse(this)
}

fun Long.formatDate(): String {
    val formatter = java.text.SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return formatter.format(Date(this))
}