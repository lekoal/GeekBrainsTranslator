package com.example.utils

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews

class InfoWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        appWidgetIds.forEach { widgetId ->
            RemoteViews(
                context.packageName,
                R.layout.initial_layout
            ).apply {
                setTextViewText(R.id.widget_textview, text)
                appWidgetManager.updateAppWidget(widgetId, this@apply)
            }
        }
    }

    companion object {
        var text = ""
    }
}