package com.example.sofuser.api

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class ApiClient constructor(context: Context){

    private var retrofit: Retrofit

    init {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                    .header(ApiKey.CONTENT_LANGUAGE, Locale.getDefault().language)
                    .method(original.method(), original.body())
                    .build()
            val response = chain.proceed(request)
            // todo deal with the issues the way you need to
            response
        }
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
        httpClient.addInterceptor(logging)

        val client = httpClient.build()
        retrofit =  Retrofit.Builder()
                .baseUrl(ApiKey.PARSE_SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    }

    fun getInterface(): RestApi {
        return retrofit.create(RestApi::class.java)
    }
}