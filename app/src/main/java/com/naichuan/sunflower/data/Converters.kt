package com.naichuan.sunflower.data

import androidx.room.TypeConverter
import java.util.*

/**
 * Type converters to allow Room to reference complex data types.
 */
class Converters {

    @TypeConverter
    fun calendarToDateStamp(calendar: Calendar): Long
            = calendar.timeInMillis

    @TypeConverter
    fun dateStampToCalendar(value: Long): Calendar
            = Calendar.getInstance().apply { timeInMillis = value }
}