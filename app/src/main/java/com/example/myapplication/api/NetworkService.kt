package com.example.myapplication.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {
    private const val BASE_URL = "http://wartest-env.eba-vptnwyk4.eu-central-1.elasticbeanstalk.com/"
    private var mInstance: NetworkService? = null

    private val loggingInterceptor = HttpLoggingInterceptor()

    private val okClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    var gson = GsonBuilder()
        .setLenient()
        .create()!!

    private var mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    fun getInstance(): NetworkService? {
        if (mInstance == null) {
            mInstance = NetworkService
        }
        return mInstance
    }

    fun getJSONApi(): Api? {
        return mRetrofit.create(Api::class.java)
    }
}