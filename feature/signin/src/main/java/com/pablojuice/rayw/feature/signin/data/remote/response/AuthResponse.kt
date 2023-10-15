package com.pablojuice.rayw.feature.signin.data.remote.response

import com.pablojuice.core.data.remote.api.ApiResponse

data class AuthResponse(
    val user: SimpleUser,
    val token: Tokens
) : ApiResponse

data class Tokens(
    val accessToken: String,
    val refreshToken: String
) : ApiResponse

data class SimpleUser(
    val firstName: String,
    val secondName: String,
    val avatarImage: UserAvatar?,
    val roles: List<UserRole>
) : ApiResponse

data class UserAvatar(
    val name: String,
    val data: List<String>,
    val category: UserAvatarCategory
) : ApiResponse

data class UserAvatarCategory(val type: String) : ApiResponse

data class UserRole(val type: String) : ApiResponse