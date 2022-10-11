package com.mawinda.data.remote

import com.mawinda.data.remote.model.RemoteResult
import com.mawinda.data.remote.model.StatusResponseWithData
import retrofit2.Response

abstract class SafeNetworkApi {

    /**
     * Safe Api Request to handle error in single place
     */
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): RemoteResult<T> {
        return try {
            val response = call.invoke()
            when {
                response.isSuccessful -> RemoteResult.Success(response.body())
                else -> RemoteResult.Error(Exception(response.errorBody()?.string()))
            }

        } catch (ex: Exception) {
            RemoteResult.Error(ex)
        }
    }


    /**
     * Converts Remote Result to Status Response
     */
    inline fun <reified T : Any> RemoteResult<T>.toStatusResponse(): StatusResponseWithData<T> {
        return when (this) {
            is RemoteResult.Success<T> -> with(this) {
                StatusResponseWithData(isSuccess = true, data = this.data as T)
            }
            else -> StatusResponseWithData(
                isSuccess = false,
                msg = (this as RemoteResult.Error).exception.localizedMessage ?: ""
            )

        }
    }

}