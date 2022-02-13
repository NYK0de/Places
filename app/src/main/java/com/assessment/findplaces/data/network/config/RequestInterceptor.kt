package com.assessment.findplaces.data.network.config

import com.assessment.findplaces.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Class to intercept each http request and add KEY parameter to the url
 * this is required by the google places API
 */
class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val newUrl = request
            .url()
            .newBuilder()
            .addQueryParameter(BuildConfig.PARAM_KEY_NAME, BuildConfig.MAPS_API_KEY)
            .build()

        request = request.newBuilder().url(newUrl).build()

        return chain.proceed(request)
    }

}