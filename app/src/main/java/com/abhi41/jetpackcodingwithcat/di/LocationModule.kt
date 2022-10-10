package com.abhi41.jetpackcodingwithcat.di

import android.content.Context
import com.abhi41.jetpackcodingwithcat.examples.TracklocationBackground.DefaultLocationClient
import com.abhi41.jetpackcodingwithcat.examples.TracklocationBackground.LocationClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Singleton
    @Provides
    fun provideLocationClient(
        @ApplicationContext context: Context,
    ): LocationClient {
        return DefaultLocationClient(
            context,
            LocationServices.getFusedLocationProviderClient(context)
        )
    }
}