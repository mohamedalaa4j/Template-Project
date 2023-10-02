package com.template.util.deeplink

import androidx.core.net.toUri

object DeeplinkUtil {

    const val Key = "DeeplinkKey"

    fun toLogin(argument: Boolean = false) =
        "android-app://eramo.events/loginFragment?proceedRequire={$argument}".toUri()

    fun toPolicy() =
        "android-app://eramo.events/policyFragment".toUri()

    fun toDatePicker() =
        "android-app://eramo.events/datePickerDialog".toUri()

}