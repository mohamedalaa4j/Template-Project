package com.am.template.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class  OptionsEntity(
    @PrimaryKey(autoGenerate = true) var db_id: Int? = null,
    var id_hall: String? = null,
    var id: Int? = null,
    var name: String? = null,
    var limitation: String? = null,
    var quantity: String? = null,
    var price: String? = null,
    var image_url: String? = null
)