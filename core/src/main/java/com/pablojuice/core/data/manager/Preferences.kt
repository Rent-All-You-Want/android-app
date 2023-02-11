package com.pablojuice.core.data.manager

interface Preferences<KEY> {

    fun <T> put(key: KEY, value: T)

    fun <T> get(key: KEY): T?

    fun clear()
}