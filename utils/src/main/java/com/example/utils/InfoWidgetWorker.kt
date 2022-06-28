package com.example.utils

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class InfoWidgetWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    private val lastSearch = inputData.getString(SEARCH_KEY)

    override fun doWork(): Result {
        InfoWidget.text = lastSearch.toString()
        return Result.success()
    }

    companion object {
        private const val SEARCH_KEY = "last_search"
    }

}