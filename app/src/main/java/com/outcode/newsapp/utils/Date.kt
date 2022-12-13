@file:Suppress(
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
)

package com.outcode.newsapp.utils

import android.annotation.SuppressLint
import androidx.core.util.Preconditions
import com.outcode.newsapp.data.handler.loggerE
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun getDateText(obtainedDate: String?): String? {
    val newDateFormat = SimpleDateFormat("yyyy-MM-dd")
    var date: Date? = null
    try {
        date = newDateFormat.parse(obtainedDate?.split("T".toRegex())?.toTypedArray()?.get(0))
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    newDateFormat.applyPattern("EE d MMM yyyy")
    return newDateFormat.format(date)
}

fun formatDate(obtainedDate: String?): String? {
    val newDateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy")
    var date: Date? = null
    try {
        date = newDateFormat.parse(obtainedDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    newDateFormat.applyPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    return newDateFormat.format(date)
}


@SuppressLint("NewApi")
fun getDate(obtainedDate: String): String? {
    val tz = TimeZone.getDefault()
    val gmt1 = TimeZone.getTimeZone(tz.id)
        .getDisplayName(false, TimeZone.SHORT).substring(3)
    val modifiedDate = obtainedDate.substring(0, obtainedDate.length - 1) + gmt1

loggerE("modify:$modifiedDate")

    val sdf =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH)
    val formatedDate= sdf.format("2021-05-27T07:44:41:0545")

    val newDateFormat = SimpleDateFormat("hh:mm aa")
    var date: Date? = null
    try {
        date = newDateFormat.parse(formatedDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    newDateFormat.applyPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
    return newDateFormat.format(date)


}



//SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
//EEE MMM dd HH:mm:ss zzzz yyyy

fun getDateMonth(obtainedDate: String?): String? {
    obtainedDate ?: return null
    var MyDate1: Date? = null
    val newDateFormat = SimpleDateFormat("MM-dd-yyyy")
    try {
        MyDate1 = newDateFormat.parse(obtainedDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    newDateFormat.applyPattern("MMM yyyy")
    return newDateFormat.format(MyDate1)
}

fun getMonth(obtainedDate: String?): String {
    var date: Date? = null

    val newDateFormat = SimpleDateFormat("MM-dd-yyyy")
    try {
        date = newDateFormat.parse(obtainedDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    val date_format1 = SimpleDateFormat("MM-dd-yyyy")
    var date_str = date_format1.format(date)

    val date_format2 = SimpleDateFormat("MMMM yyyy ")
    date_str = date_format2.format(date)

    return date_str
}


fun isDateValid(startDate: String?, endDate: String?): Boolean {
    val dfDate = SimpleDateFormat("MM-dd-yyyy")
    var b = false
    try {
        b = dfDate.parse(startDate).before(dfDate.parse(endDate))
        loggerE("$startDate/$endDate")
    } catch (e: Exception) {
        e.printStackTrace()
        loggerE(e.message)
    }
    return b
}

@SuppressLint("SimpleDateFormat")
fun isTimeValid(startDate: String?, endDate: String?): Boolean {
    val sdf = SimpleDateFormat("hh:mm")
    var b = false
    try {
        b = sdf.parse(startDate).before(sdf.parse(endDate))
        loggerE("$startDate/$endDate")
    } catch (e: Exception) {
        e.printStackTrace()
        loggerE(e.message)
    }
    return b
}




fun String.getDateWithServerTimeStamp(dateFormatString: String = "yyyy-MM-dd-HH:mm"): String? {
    val dateFormat = SimpleDateFormat(
        "yyyy-mm-dd'T'HH:mm:ss'Z'",
        Locale.getDefault()
    )
    dateFormat.timeZone = TimeZone.getTimeZone("GMT")  // IMP !!!
    try {
        val dateString = dateFormat.parse(this)
        return convertToCustomFormat(dateString.toString(), dateFormatString)
    } catch (e: ParseException) {
        loggerE("datFormat error:${e.localizedMessage}")
        return null
    }
}

private fun convertToCustomFormat(dateStr: String?, dateFormat: String): String {
    val utc = TimeZone.getTimeZone("UTC")
    val sourceFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy")
    val destFormat = SimpleDateFormat(dateFormat)
    sourceFormat.timeZone = utc
    val convertedDate = sourceFormat.parse(dateStr)
    return destFormat.format(convertedDate)
}

