package com.pablojuice.core.data.converter

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.lang.reflect.Type
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MoshiConverter @Inject constructor(
    private val moshi: Moshi
) : StringJsonConverter {

    private val cache: Map<Class<*>, JsonAdapter<*>> = emptyMap()

    override fun <T> fromJson(json: String, type: Type): T? = adapterFor<T>(type).fromJson(json)

    override fun <T> toJson(obj: T, type: Type): String = adapterFor<T>(type).toJson(obj)

    private fun <T> adapterFor(type: Type) =
        (cache[type] ?: moshi.adapter<T>(type)) as JsonAdapter<T>
}