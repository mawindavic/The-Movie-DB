package com.mawinda.data.remote

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) : SafeNetworkApi() {
    private val dispatchers: CoroutineDispatcher by lazy {
        Dispatchers.IO
    }
}