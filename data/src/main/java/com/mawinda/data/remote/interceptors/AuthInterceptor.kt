package com.mawinda.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

/**
 * Adds Authorisation Token
 */
class AuthInterceptor @Inject constructor() : Interceptor {

    companion object {
        init {
            System.loadLibrary("data")
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.addAuthorisation(token = getAccessToken())
        return chain.proceed(request)
    }

    private fun Interceptor.Chain.addAuthorisation(token: String): Request {
        return this.request().newBuilder().addHeader("Authorization", "Bearer ".plus(token)).build()
    }

    private external fun getAccessToken(): String

}