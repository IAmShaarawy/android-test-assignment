package com.example.shacklehotelbuddy.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("properties/v2/list")
    suspend fun getTendingRepos(@Body searchRequestBody: SearchRequestBody): SearchResponseBody
}