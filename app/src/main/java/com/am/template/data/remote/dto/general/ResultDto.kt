package com.am.template.data.remote.dto.general

import com.google.gson.annotations.SerializedName

data class ResultDto(
    @SerializedName("success") var success: Int? = null,
    @SerializedName("message_en") var messageEn: String? = null,
    @SerializedName("message_ar") var messageAr: String? = null
)