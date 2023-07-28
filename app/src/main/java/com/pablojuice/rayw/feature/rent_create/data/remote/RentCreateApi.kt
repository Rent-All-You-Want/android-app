package com.pablojuice.rayw.feature.rent_create.data.remote

import com.pablojuice.core.data.remote.api.Api
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RentCreateApi : Api {

    @POST("category/{id}/attributes")
    suspend fun register(
        @Path("id") tenantsId: String,
        @Query("???") sId: String
    ): Result<com.pablojuice.rayw.feature.signin.data.remote.response.RegisterResponse>
}