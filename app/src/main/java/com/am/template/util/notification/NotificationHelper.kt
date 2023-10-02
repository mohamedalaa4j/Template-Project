package com.template.util.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavDeepLinkBuilder
import com.am.template.R
import com.template.data.remote.dto.NotificationDto
import com.template.util.Constants
import com.template.util.notification.FirebaseMessageReceiver.Companion.sharedPref
import me.leolin.shortcutbadger.ShortcutBadger
import java.io.IOException
import java.net.URL
import kotlin.random.Random

class NotificationHelper(private val context: Context) {


    private val CHANNEL_ID = "64325269103265"
    private val CHANNEL_NAME = "Events Channel"

    fun push(notificationDto: NotificationDto) {

        val pendingIntent: PendingIntent = if (notificationDto.orderId.isNullOrEmpty()) {

            // Goto notification
            NavDeepLinkBuilder(context)
                .setGraph(R.navigation.nav_main)
//                .setArguments(NotificationInfoFragmentArgs(notificationDto).toBundle())
//                .setDestination(R.id.notificationInfoFragment)
                .setDestination(R.id.notificationFragment)
                .createPendingIntent()
        } else
            if (notificationDto.redirectType == "order") {
                NavDeepLinkBuilder(context)
                    .setGraph(R.navigation.nav_main)
//                    .setArguments(OrderDetailsFragmentArgs(orderId = notificationDto.orderId).toBundle())
//                    .setDestination(R.id.orderDetailsFragment)
                    .createPendingIntent()

            } else
                if (notificationDto.redirectType == "booking") {
                    NavDeepLinkBuilder(context)
                        .setGraph(R.navigation.nav_main)
//                        .setArguments(OrderHallDetailsFragmentArgs(notificationDto.orderId.toString()).toBundle())
//                        .setDestination(R.id.orderHallDetailsFragment)
                        .createPendingIntent()
                } else {
                    // Goto notification
                    NavDeepLinkBuilder(context)
                        .setGraph(R.navigation.nav_main)
//                .setArguments(NotificationInfoFragmentArgs(notificationDto).toBundle())
//                .setDestination(R.id.notificationInfoFragment)
                        .setDestination(R.id.notificationFragment)
                        .createPendingIntent()
                }

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(notificationManager)

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setColor(context.resources.getColor(R.color.red))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setContentText(notificationDto.body)
            .setContentTitle(notificationDto.title)
//            .setSmallIcon(R.drawable.pic_icon)
            .setSmallIcon(R.drawable.ic_stat_pic_logo_events)
            .setContentIntent(pendingIntent)
            .setChannelId(CHANNEL_ID)
            .setOnlyAlertOnce(true)
            .setAutoCancel(true)
            .setNumber(sharedPref?.getInt(Constants.NOTIFICATIONS_COUNT_SHARED_PREFERENCES_KEY, 0)!! + 1)

        ShortcutBadger.applyCount(context,sharedPref?.getInt(Constants.NOTIFICATIONS_COUNT_SHARED_PREFERENCES_KEY,0)!! + 1)


//        ShortcutBadger.applyNotification(
//            context, builder.build(),
//            sharedPref?.getInt(Constants.NOTIFICATIONS_COUNT_SHARED_PREFERENCES_KEY, 0)!! + 1
//        )


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setSmallIcon(R.drawable.ic_stat_pic_logo_events);
            builder.color = ContextCompat.getColor(context, R.color.red)
            builder.setColorized(true)
        } else {
            builder.setSmallIcon(R.drawable.ic_stat_pic_logo_events);
        }

        var image: Bitmap? = null
        notificationDto.image?.let {
            try {
                val url = URL(it)
                image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            } catch (e: IOException) {
                println(e)
            }
        }

        image?.let {
            builder.setStyle(NotificationCompat.BigTextStyle().bigText(notificationDto.body))
                .setStyle(NotificationCompat.BigPictureStyle().bigPicture(it))
        }

        notificationManager.notify(Random.nextInt(), builder.build())
    }

    private fun createNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.apply {
                lightColor = Color.GREEN
                enableLights(true)
                setShowBadge(true)

            }
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}