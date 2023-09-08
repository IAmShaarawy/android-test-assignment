package com.example.shacklehotelbuddy.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("X-RapidAPI-Key", "b73a82f19bmsh91a41fe90ed1aa0p183ef1jsn9562ae2f6d5a")
                .addHeader("X-RapidAPI-Host", "hotels4.p.rapidapi.com")
                .build()
        )
    }
}