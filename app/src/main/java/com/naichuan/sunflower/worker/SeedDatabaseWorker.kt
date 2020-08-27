package com.naichuan.sunflower.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.naichuan.sunflower.data.AppDatabase
import com.naichuan.sunflower.data.Plant
import com.naichuan.sunflower.utils.PLANT_DATA_FILENAME
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(PLANT_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val plantType = object : TypeToken<List<Plant>>() {}.type
                    val plantList: List<Plant> = Gson().fromJson(jsonReader, plantType)
                    val database = AppDatabase.getInstance(applicationContext)
                    database.plantDao().insertAll(plantList)
                    Result.success()
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error seeding database", e)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }
}