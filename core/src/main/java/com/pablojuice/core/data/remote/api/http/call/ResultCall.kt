package com.pablojuice.core.data.remote.api.http.call

import com.pablojuice.core.data.remote.api.http.error.exception.NetworkException
import okhttp3.Request
import okio.Timeout
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ResultCall<T>(
    private val delegate: Call<T>
) : Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) {
        delegate.enqueue(
            object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) {
                        callback.onResponse(
                            this@ResultCall,
                            Response.success(
                                response.code(),
                                Result.success(response.body() ?: TODO())
                            )
                        )
                    } else {
                        callback.onResponse(
                            this@ResultCall,
                            Response.success(
                                Result.failure(NetworkException(message = response.errorMessage()))
                            )
                        )
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    callback.onResponse(
                        this@ResultCall,
                        Response.success(Result.failure(NetworkException(t.toMessage(), t)))
                    )
                }
            }
        )
    }

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun execute(): Response<Result<T>> =
        Response.success(Result.success(delegate.execute().body() ?: TODO()))

    override fun cancel() = delegate.cancel()

    override fun clone(): Call<Result<T>> = ResultCall(delegate.clone())

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    private fun <T> Response<T>.errorMessage() =
        errorBody()?.string()?.let { JSONObject(it).get("description").toString() }

    private fun Throwable.toMessage() = when (this) {
        is IOException -> "No internet connection"
        is HttpException -> "Something went wrong!"
        else -> localizedMessage
    }
}

