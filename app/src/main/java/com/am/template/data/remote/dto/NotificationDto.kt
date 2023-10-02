package com.template.data.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotificationDto(
    @SerializedName("id") var id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("body") var body: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("link") var link: String? = null,
    @SerializedName("time") var time: String? = null,
    @SerializedName("order_id") var orderId: String?=null,
    @SerializedName("redirectType") var redirectType: String?=null
) : Parcelable