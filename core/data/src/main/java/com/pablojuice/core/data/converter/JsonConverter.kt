package com.pablojuice.core.data.converter

import java.lang.reflect.Type

interface JsonConverter<JSON> {
    fun <T> fromJson(json: JSON, type: Type): T?

    fun <T> toJson(obj: T, type: Type): JSON
}