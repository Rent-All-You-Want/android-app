package com.pablojuice.rayw.feature.rent_create.data.remote.api

import com.pablojuice.core.data.remote.api.Api
import com.pablojuice.rayw.feature.rent_create.data.remote.response.GetRentAttributesResponse
import com.pablojuice.rayw.feature.rent_create.data.remote.response.GetRentCategoriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RentCreateApi : Api {

    @GET("attribute")
    suspend fun getAttributes(): Result<GetRentAttributesResponse>

    @GET("attribute/search")
    suspend fun searchAttributes(@Query("title") query: String): Result<GetRentAttributesResponse>

    @GET("category/{id}/attributes")
    suspend fun getAttributesForCategory(@Path("id") categoryId: String): Result<GetRentAttributesResponse>

    @GET("category/suggest")
    suspend fun getSuggestedCategory(@Query("itemTitle") query: String): Result<GetRentCategoriesResponse>

    @GET("category/subcategories")
    suspend fun getCategories(): Result<GetRentCategoriesResponse>

    @GET("category/children")
    suspend fun getCategoriesByParentCategory(@Query("id") categoryId: String): Result<GetRentCategoriesResponse>
}