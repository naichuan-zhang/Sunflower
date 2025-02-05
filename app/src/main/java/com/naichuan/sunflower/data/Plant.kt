package com.naichuan.sunflower.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val plantId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int = 7, // how often the plant should be watered, in days
    val imageUrl: String = ""
) {
    /**
     * Determine if the plant should be watered.
     * Returns true if [since]'s date > date of the
     * last watering date + watering interval; false otherwise
     */
    fun shouldBeWatered(since: Calendar, lastWateringDate: Calendar)
        = since > lastWateringDate.apply {
            add(Calendar.DAY_OF_YEAR, wateringInterval)
        }

    override fun toString(): String = name
}