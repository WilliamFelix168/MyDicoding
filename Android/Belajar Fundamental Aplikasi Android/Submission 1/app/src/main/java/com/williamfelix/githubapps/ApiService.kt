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