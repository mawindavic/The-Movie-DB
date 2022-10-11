package com.mawinda.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

/**
 * Adds Authorisation Token
 */
class AuthInterceptor @Inject constructor(private val token: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.addAuthorisation(token = token)
        return chain.proceed(request)
    }

    private fun Interceptor.Chain.addAuthorisation(token: String): Request {
        return this.request().newBuilder().addHeader("Authorization", "Bearer ".plus(token)).build()
    }
}