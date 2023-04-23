package com.pablojuice.core.data.remote.converter

import com.pablojuice.core.utils.StringUtils
import com.squareup.moshi.Json
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class EnumConverterFactory : Converter.Factory() {

    override fun stringConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, String>? {
        var converter: Converter<*, String>? = null
        if (type is Class<*> && type.isEnum) {
            converter =
                Converter { value: Any? ->
                    getSerializedNameValue(value as Enum<*>)
                }
        }
        return converter
    }

    private fun <E : Enum<*>> getSerializedNameValue(e: E): String {
        try {
            return e::class.java.getField(e.name).getAnnotation(Json::class.java)?.name
                ?: StringUtils.EMPTY
        } catch (ex: NoSuchFieldException) {
            ex.printStackTrace()
        }
        return StringUtils.EMPTY
    }
}