package com.pablojuice.rayw.feature.rent_create.data.remote.response

import com.pablojuice.core.data.remote.api.ApiResponse

class GetRentAttributesResponse : ArrayList<RentAttribute>(), ApiResponse

data class RentAttribute(val id: Int, val title: String)