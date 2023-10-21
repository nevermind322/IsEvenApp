package com.example.network

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiCall<T : Any>(private val proxy: Call<T>) : Call<ApiResult<T>> {

    override fun enqueue(callback: Callback<ApiResult<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val result = try {
                    val body = response.body()
                    if (response.isSuccessful && body != null) ApiResult.Success(body)
                    else ApiResult.Error(code = response.code(), msg = response.message())

                } catch (e: HttpException) {
                    ApiResult.Error(e.code(), e.message())

                } catch (e: Throwable) {
                    ApiResult.Exception(e)
                }
                callback.onResponse(this@ApiCall, Response.success(result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val res = ApiResult.Exception(t)
                callback.onResponse(this@ApiCall, Response.success(res))
            }

        })
    }

    override fun clone() = ApiCall(proxy.clone())

    override fun execute() = throw NotImplementedError()
    override fun isExecuted() = proxy.isExecuted

    override fun cancel() = proxy.cancel()

    override fun isCanceled() = proxy.isCanceled

    override fun request(): Request = proxy.request()

    override fun timeout(): Timeout = proxy.timeout()


}


class ApiCallAdapter(private val resultType: Type) : CallAdapter<Type, Call<ApiResult<Type>>> {
    override fun responseType(): Type = resultType

    override fun adapt(call: Call<Type>): Call<ApiResult<Type>> = ApiCall(call)
}

class ApiClassAdapterFactory private constructor() : CallAdapter.Factory() {
    override fun get(
        returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) {
            return null
        }

        val callType = getParameterUpperBound(0, returnType as ParameterizedType)
        if (getRawType(callType) != ApiResult::class.java) {
            return null
        }

        val resultType = getParameterUpperBound(0, callType as ParameterizedType)
        return ApiCallAdapter(resultType)
    }

    companion object {
        fun create() = ApiClassAdapterFactory()
    }

}

