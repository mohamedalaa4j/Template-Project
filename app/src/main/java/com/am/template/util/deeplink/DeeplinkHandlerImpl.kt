package com.am.template.util.deeplink

import android.content.Intent
import androidx.navigation.NavController
import com.am.template.R
import com.template.util.deeplink.DeeplinkHandler
import com.template.util.deeplink.DeeplinkUtil

class DeeplinkHandlerImpl : DeeplinkHandler {
    override fun handleDeeplink(intent: Intent?, navController: NavController) {
        val deeplink = intent?.getStringExtra(DeeplinkUtil.Key)
        deeplink?.let { value ->
//            when (value) {
//                "15" -> navController.navigate(R.id.aboutUsFragment)
//                "18" -> navController.navigate(R.id.contactUsFragment)
//            }
        }
    }
}