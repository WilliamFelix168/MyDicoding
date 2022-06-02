package com.williamfelix.githubapps

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUser(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<UserDetailResponse>
}