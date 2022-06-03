package com.williamfelix.githubapps

import com.google.gson.annotations.SerializedName

data class FollowerResponse(
	@field:SerializedName("FollowerResponse")
	val followerResponse: List<FollowerResponseItem>
)

data class FollowerResponseItem(

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("login")
	val login: String? = null
)
