package com.am.template.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class HallEntity(
    @PrimaryKey(autoGenerate = true) var db_id: Int? = null,
    var id_cart_id: Int? = null,
    var hall_id: String? = null,
    var hall_name: String? = null,
    var vendor_name: String? = null,
    var vendor_image: String? = null,
    var package_id: String? = null,
    var package_price: String? = null,
    var package_image: String? = null,
    var package_name: String? = null,
    var package_desc: String? = null,
    var date: String? = null,
    var time_from: String? = null,
    var time_to: String? = null,
    var extra_guest: String? = null,
    var guest_price: String? = null

)