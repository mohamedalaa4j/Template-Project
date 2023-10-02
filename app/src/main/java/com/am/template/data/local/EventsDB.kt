package com.am.template.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.am.template.data.local.entity.AllImagesEntity
import com.am.template.data.local.entity.CartDataEntity
import com.am.template.data.local.entity.HallEntity
import com.am.template.data.local.entity.OptionsEntity
import com.am.template.data.local.entity.ProductEntity

@Database(
    entities = [CartDataEntity::class, AllImagesEntity::class, ProductEntity::class, HallEntity::class, OptionsEntity::class],
    version = 9,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class EventsDB : RoomDatabase() {
    abstract val dao: EventsDao
}