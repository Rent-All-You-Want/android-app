package com.pablojuice.core.data.remote.api.factory

import com.pablojuice.core.data.remote.api.Api

abstract class ApiFactory {
    private val map = HashMap<Class<out Api>, Api>()

    inline fun <reified T : Api> get(): T = get(T::class.java)

    fun <T : Api> get(
        type: Class<T>
    ): T {
        if (!map.containsKey(type)) {
            map[type] = create(type)
        }
        return map[type] as T
    }

    abstract fun <T : Api> create(type: Class<T>): Api
}