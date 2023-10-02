package com.am.template.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AllImagesEntity(
    var imgId: String? = null,
    var productIdFk: String? = null,
    var image: String? = null,
    var created: String? = null,
    var userId: String? = null,
    var alt: String? = null,
    @PrimaryKey val productImagesId: Int? = imgId?.toInt(),
)
