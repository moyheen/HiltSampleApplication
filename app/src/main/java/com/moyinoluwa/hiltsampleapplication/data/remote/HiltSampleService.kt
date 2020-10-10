package com.moyinoluwa.hiltsampleapplication.data.remote

import io.reactivex.Observable
import retrofit2.http.GET

interface HiltSampleService {

    @GET("/latest")
    fun retrieveData(): Observable<ApiResponse>
}
