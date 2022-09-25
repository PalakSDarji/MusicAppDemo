package com.palak.applemusicappdemo.api

import com.palak.applemusicappdemo.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

/**
 * Api client which takes care of retrofit instance.
 * Please ignore deprecated warning on SimpleXmlConverterFactory.
 * Alternate JAXB converter is not supported in Android yet.
 */
class RetrofitApiClient {

    fun retrofitAppleMusicService(): ApiInterface {

        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }
        val client : OkHttpClient = OkHttpClient.Builder()
            .hostnameVerifier { _, _ -> true }
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    fun retrofitPostingService(): PostingApiInterface {

        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client : OkHttpClient = OkHttpClient.Builder()
            .hostnameVerifier { _, _ -> true }
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.POSTING_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(PostingApiInterface::class.java)
    }
}