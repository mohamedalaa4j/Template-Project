package com.template.util.notification

import android.content.SharedPreferences
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessageReceiver : FirebaseMessagingService() {



    private lateinit var notificationHelper: NotificationHelper
    private var orderId=""
    private var redirectType=""
    private var title = ""
    private var message = ""
    private var image: String? = null
    private var link = ""
    private var time = ""

    companion object {
        var sharedPref: SharedPreferences? = null
        var token: String?
            get() {
                return sharedPref?.getString("token", "")
            }
            set(value) {
                sharedPref?.edit()?.putString("token", value)?.apply()
            }
    }

    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        token = newToken
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        notificationHelper = NotificationHelper(this)

        if (remoteMessage.data.isNotEmpty()) {
            title = remoteMessage.data["title"] ?: ""
            message = remoteMessage.data["body"] ?: ""
            image = remoteMessage.data["image"] ?: ""
            link = remoteMessage.data["link"] ?: ""
            time = remoteMessage.data["time"] ?: "00:00"

            orderId = remoteMessage.data["redirectID"] ?: ""
            redirectType = remoteMessage.data["redirectType"] ?: ""

        }

        remoteMessage.notification?.let {
            title = it.title ?: "emptyTitle"
            message = it.body ?: "emptyMessage"
            image = it.imageUrl?.toString() ?:""
        }

//        val notificationDto = NotificationDto(
//            title = title, body = message, image = image, link = link, time = time, orderId = orderId,redirectType = redirectType
//        )
//        notificationHelper.push(notificationDto)
    }

}