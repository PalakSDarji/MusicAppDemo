package com.palak.applemusicappdemo.di

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.palak.applemusicappdemo.api.ApiInterface
import com.palak.applemusicappdemo.api.PostingApiInterface
import com.palak.applemusicappdemo.api.RetrofitApiClient
import com.palak.applemusicappdemo.db.SongAppDb
import com.palak.applemusicappdemo.db.SongEntryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * General Module to provide third party dependencies which are not to be created by us or by dagger.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun getApiClient(): RetrofitApiClient {
        return RetrofitApiClient()
    }

    @Singleton
    @Provides
    fun getAppleMusicRetrofit(apiClient : RetrofitApiClient): ApiInterface {
        return apiClient.retrofitAppleMusicService()
    }

    @Singleton
    @Provides
    fun getPostingRetrofit(apiClient : RetrofitApiClient): PostingApiInterface {
        return apiClient.retrofitPostingService()
    }

    @Singleton
    @Provides
    fun provideGson(gsonBuilder: GsonBuilder) : Gson {
        return gsonBuilder.create()
    }

    @Singleton
    @Provides
    fun provideGsonBuilder() : GsonBuilder {
        return GsonBuilder()
    }

    @Singleton
    @Provides
    fun provideSongAppDatabase(application : Application) : SongAppDb{
        return SongAppDb.getDb(application.applicationContext)
    }

    @Singleton
    @Provides
    fun provideSongEntryDao(songAppDao : SongAppDb) : SongEntryDao{
        return songAppDao.songEntryDao()
    }

}