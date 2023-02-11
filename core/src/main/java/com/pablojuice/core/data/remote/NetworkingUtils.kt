package com.pablojuice.core.data.remote

import android.content.Context
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.pablojuice.core.data.remote.NetworkHelper.isNetworkAvailable
import com.pablojuice.core.data.remote.converter.EnumConverterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

internal object NetworkingUtils {

    private const val TIME_OUT_CONNECTION = 60L
    private const val STANDARD_HTTP_CACHE_SIZE = 10 * 1024 * 1024L
    private const val STANDARD_MAX_STALE = 60 * 60 * 24 * 28L
    private const val STANDARD_MAX_AGE = 10L

    private const val CACHE_FOLDER_NAME = "cachedResponses"
    private const val CACHE_CONTROL = "Cache-Control"

    fun Context.createRetrofit(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(EnumConverterFactory())
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .addConverterFactory(MoshiConverterFactory.create())
            .client(createOkHttpClient(withCache = true))
            .build()

    private fun Context.createOkHttpClient(
        withCache: Boolean = true,
        addProfilerInterceptor: Boolean = false,
        addBodyLoggingInterceptor: Boolean = false
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(TIME_OUT_CONNECTION, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_CONNECTION, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_CONNECTION, TimeUnit.SECONDS)
        if (withCache) addCacheManager(builder)
        if (addProfilerInterceptor) builder.addInterceptor(OkHttpProfilerInterceptor())
        if (addBodyLoggingInterceptor) builder.addInterceptor(createHttpLoggingInterceptor())
        return builder.build()
    }

    private fun Context.addCacheManager(builder: OkHttpClient.Builder) {
        builder.cache(createHttpCache())
        builder.addNetworkInterceptor(createCacheInterceptor())
        builder.addInterceptor(createOfflineInterceptor())
    }

    private fun Context.createHttpCache() =
        Cache(File(cacheDir, CACHE_FOLDER_NAME), STANDARD_HTTP_CACHE_SIZE)

    private fun createCacheInterceptor() = Interceptor { chain: Interceptor.Chain ->
        val originalResponse = chain.proceed(chain.request())
        if (originalResponse.header(CACHE_CONTROL).allowsCaches()) {
            originalResponse.newBuilder()
                .header(CACHE_CONTROL, "public, max-age=$STANDARD_MAX_AGE")
                .build()
        } else originalResponse
    }

    private fun Context.createOfflineInterceptor() = Interceptor { chain: Interceptor.Chain ->
        var request = chain.request()
        if (!isNetworkAvailable()) {
            request = request.newBuilder()
                .header(
                    CACHE_CONTROL,
                    "public, only-if-cached, max-stale=$STANDARD_MAX_STALE"
                )
                .build()
        }
        chain.proceed(request)
    }

    private fun createHttpLoggingInterceptor() =
        HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

    private fun String?.allowsCaches(): Boolean = this == null ||
            contains("must-revalidate") || contains("no-cache") ||
            contains("no-store") || contains("max-age=0")
}