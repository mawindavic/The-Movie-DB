package com.mawinda.data

import com.mawinda.data.local.LocalDataSource
import com.mawinda.data.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class MovieDBRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource
) {


}