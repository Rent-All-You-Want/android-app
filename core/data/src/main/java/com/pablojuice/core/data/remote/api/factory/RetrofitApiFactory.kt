package com.pablojuice.core.data.remote.api.factory

import com.pablojuice.core.data.remote.api.Api
import retrofit2.Retrofit

class RetrofitApiFactory constructor(
    private val retrofit: Retrofit
) : ApiFactory() {

    override fun <T : Api> create(type: Class<T>): Api = retrofit.create(type)
}