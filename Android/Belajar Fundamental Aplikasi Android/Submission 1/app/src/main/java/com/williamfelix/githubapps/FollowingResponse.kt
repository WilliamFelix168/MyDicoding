package com.williamfelix.githubapps

import com.google.gson.annotations.SerializedName

data class FollowingResponse(
	@field:SerializedName("FollowingResponse")
	val followerResponse: List<FollowingResponseItem>
)

data class FollowingResponseItem(

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("login")
	val login: String? = null
)
