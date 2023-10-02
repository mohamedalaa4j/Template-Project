package com.template.util.notification

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class MyNotificationListener : NotificationListenerService() {
    private var notificationCount = 0

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        // Increment the notification count
        notificationCount++

        Log.e("NotificationPosted","NotificationPosted")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        // Decrement the notification count
        notificationCount--

        Log.e("onNotificationRemoved","onNotificationRemoved")


        // Decrement the notification badge count
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val currentCount = notificationCount
        val newCount = currentCount - 1
        val notificationBuilder = NotificationCompat.Builder(this, sbn.notification.channelId)
//            .setSmallIcon(sbn.notification.smallIcon)
            .setContentTitle(sbn.notification.extras.getString(NotificationCompat.EXTRA_TITLE))
            .setContentText(sbn.notification.extras.getString(NotificationCompat.EXTRA_TEXT))
            .setNumber(newCount)
        notificationManager.notify(sbn.id, notificationBuilder.build())
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun onNotificationClicked(sbn: StatusBarNotification) {
//        // Decrement the notification badge count
//        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//        val currentCount = notificationCount
//        val newCount = currentCount - 1
//        val notificationBuilder = NotificationCompat.Builder(this, sbn.notification.channelId)
////            .setSmallIcon(sbn.notification.smallIcon)
//            .setContentTitle(sbn.notification.extras.getString(NotificationCompat.EXTRA_TITLE))
//            .setContentText(sbn.notification.extras.getString(NotificationCompat.EXTRA_TEXT))
//            .setNumber(newCount)
//        notificationManager.notify(sbn.id, notificationBuilder.build())
//    }
}