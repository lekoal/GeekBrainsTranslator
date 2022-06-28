package com.example.utils

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class InfoWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        appWidgetIds.forEach { widgetId ->
            remoteViewsApplying(context, appWidgetManager, widgetId = widgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (intent.action != MY_ACTION) return

        val intentText = intent.getStringExtra(SEARCH_KEY).toString()

        val appWidgetManager = AppWidgetManager.getInstance(context)

        val widgetName = ComponentName(context, InfoWidget::class.java)

        remoteViewsApplying(context, appWidgetManager, widgetName = widgetName, text = intentText)
    }

    companion object {
        private const val MY_ACTION = "MY_ACTION"
        private const val SEARCH_KEY = "last_search"
    }

    private fun remoteViewsApplying(
        context: Context,
        appWidgetManager: AppWidgetManager,
        widgetId: Int = -1,
        widgetName: ComponentName = ComponentName("", ""),
        text: String = "empty"
    ) {

        if (widgetId != -1) {
            RemoteViews(
                context.packageName,
                R.layout.initial_layout
            ).apply {
                setTextViewText(R.id.widget_textview, text)
                appWidgetManager.updateAppWidget(widgetId, this@apply)
            }
        } else {
            RemoteViews(
                context.packageName,
                R.layout.initial_layout
            ).apply {
                setTextViewText(R.id.widget_textview, text)
                appWidgetManager.updateAppWidget(widgetName, this@apply)
            }
        }

    }
}