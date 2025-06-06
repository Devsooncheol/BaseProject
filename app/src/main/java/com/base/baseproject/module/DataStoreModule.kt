package com.base.baseproject.module

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.base.baseproject.SettingsTest
import com.base.baseproject.ui.home.datastroe.proto.serializer.SettingsTestSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun providePreferencesDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create {
            context.dataStoreFile("BaseProjectSample.preferences_pb")
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
object ProtoDataStoreModule {

    @Provides
    @Singleton
    fun provideProtoDataStore(
        @ApplicationContext context: Context
    ): DataStore<SettingsTest> {
        return DataStoreFactory.create(
            serializer = SettingsTestSerializer,
            produceFile = { context.dataStoreFile("BaseProjectSample_Proto.preferences_pb") }
        )
    }
}