package com.pablojuice.rayw.feature.rent_create.data.remote.response

import com.pablojuice.core.data.remote.api.ApiResponse

class GetRentCategoriesResponse : ArrayList<RentCategory>(), ApiResponse

data class RentCategory(val id: Int, val title: String)