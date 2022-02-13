package com.assessment.findplaces.data.network.config

import com.assessment.findplaces.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * here we create a Retrofit Object
 * Retrofit will help us with the HTTP calls to communicate with our API
 */
object RetrofitHelper {

    fun getRetrofit() : Retrofit {
        // first we create an OkHttpClient and add an Interceptor
        val myCustomHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(RequestInterceptor())
            .build()

        // then we return an Retrofit Object to be used in other classes
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MAPS_API_URL)
            .client(myCustomHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}