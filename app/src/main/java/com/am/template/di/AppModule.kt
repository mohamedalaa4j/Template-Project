package com.am.template.di

import com.am.template.data.local.Converters
import com.am.template.data.local.EventsDB
import com.am.template.data.remote.EventsApi
import com.template.util.MyInterceptor
import com.template.util.parser.GsonParser
import android.app.Application
import androidx.room.Room
import com.am.template.data.repository.HomeRepositoryImpl
import com.am.template.domain.repository.HomeRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): EventsApi =
        Retrofit.Builder()
            .baseUrl(EventsApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(EventsApi::class.java)

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(MyInterceptor())
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideEventsDB(app: Application): EventsDB {
        return Room.databaseBuilder(app, EventsDB::class.java, "EventsDB")
            .addTypeConverter(Converters(GsonParser(Gson())))
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideHomeRepository(EventsApi: EventsApi): HomeRepository {
        return HomeRepositoryImpl(EventsApi)
    }
}