package com.am.template.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) var db_id: Int? = null,
    var id: Int? = null,
    var quantity: Int? = null,
    var name: String? = null,
//    var isFavourite: String? = null,
//    var category: String? = null,
//    var availability: String? = null,
//    var modelNumber: String? = null,
//    var inStock: String? = null,
//    var inStockLeft: String? = null,
//    var primaryImage: String? = null,
    var realPrice: String? = null,
//    var fakePrice: String? = null,
//    var taxesCost: Double? = null,
//    var sliders: List<ProductDetailsResponse.Data.Slider?>? = null,
//    var buyItWith: List<ProductDetailsResponse.Data.BuyItWith?>? = null,
//    var features: String? = null,
//    var extras: String? = null,
//    var instructions: String? = null,
    var image: String? = null,
//    var vendorName: String? = null,
//    var vendorImage: String? = null
)
//{
//    data class Slider(
//        var id: Int? = null,
//        var name: String? = null,
//        var image: String? = null,
//        var real_price: String? = null
//    )
//
//    data class BuyItWith(
//        var id: Int? = null,
//        var name: String? = null,
//        var image: String? = null,
//        var realPrice: String? = null
//    )
//}