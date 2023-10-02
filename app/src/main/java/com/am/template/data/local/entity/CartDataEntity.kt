package com.am.template.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartDataEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    var productIdFk: String? = null,
    var productName: String? = null,
    var mainCat: String? = null,
    var statusText: String? = null,
    var modelNumber: String? = null,
    var productQty: Int? = null,
    var productPrice: String? = null,
    var withInstallation: String? = null,
    var userId: String? = null,
    var dateAr: String? = null,
    var dateS: String? = null,
    var manufacturerArName: String? = null,
    var manufacturerEnName: String? = null,
    var modelArName: String? = null,
    var modelEnName: String? = null,
    var powerArName: String? = null,
    var powerEnName: String? = null,
    var inStock: String? = null,
    var availableAmount: String? = null,
    var status: String? = null,
    var shippingPrice: String? = null,
    var productNameEn: String? = null,
    var productNameAr: String? = null,
    var modelRkm: String? = null,
    var installation_cost: String? = null,
    var mainCatArName: String? = null,
    var mainCatEnName: String? = null,
    var subCatArName: String? = null,
    var subCatEnName: String? = null,
    var allImageEntity: List<AllImagesEntity>? = null,
    var quantityPrice: String? = null,
)