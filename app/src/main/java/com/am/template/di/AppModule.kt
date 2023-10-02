package com.am.template.di

import android.app.Application
import android.content.Context
import com.am.template.data.repository.RepositoryImpl
import com.am.template.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun provideRepository(@ApplicationContext context : Context): Repository {
        return RepositoryImpl(context)
    }
}