package com.template.util.deeplink

import android.content.Intent
import androidx.navigation.NavController

interface DeeplinkHandler {
    fun handleDeeplink(intent: Intent?, navController: NavController)
}