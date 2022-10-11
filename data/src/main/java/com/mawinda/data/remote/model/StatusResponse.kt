package com.mawinda.data.remote.model

/**
 * Handle Responses with No Data
 */
open class StatusResponse(open val isSuccess: Boolean, open val msg: String = "") {
    fun response(success: (msg: String) -> Unit, failure: (msg: String) -> Unit) {
        if (isSuccess) {
            success.invoke(msg)
        } else {
            failure.invoke(msg)
        }

    }
}

/**
 * Handle Remote Responses with Data
 */
data class StatusResponseWithData<T : Any>(
    override val isSuccess: Boolean,
    override val msg: String = "", val data: T? = null
) : StatusResponse(isSuccess = isSuccess, msg = msg) {
    fun response(success: (msg: String, data: T?) -> Unit, failure: (msg: String) -> Unit) {
        if (isSuccess) {
            success.invoke(msg, data)
        } else {
            failure.invoke(msg)
        }

    }
}