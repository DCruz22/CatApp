package com.example.catapp.data.api.interceptor

import com.example.catapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class HttpRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.header("x-api-key", BuildConfig.API_KEY)
        return chain.proceed(builder.build())
    }

    companion object {
        const val tag = "HttpRequestInterceptor"
    }
}