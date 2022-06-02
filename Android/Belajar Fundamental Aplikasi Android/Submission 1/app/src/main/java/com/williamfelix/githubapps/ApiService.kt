package com.williamfelix.githubapps

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    //@Headers("Authorization: token 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")
    fun getUser(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    //Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<UserDetailResponse>

    @GET("users/{username}/followers")
    //@Headers("Authorization: token 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")
    fun getFollower(
        @Path("username") username: String
    ): Call<List<FollowerResponse>>
}