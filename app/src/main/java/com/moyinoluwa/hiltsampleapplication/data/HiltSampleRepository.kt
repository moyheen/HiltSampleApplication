package com.moyinoluwa.hiltsampleapplication.data

import io.reactivex.Observable

interface HiltSampleRepository {

    fun retrieveData(): Observable<String>
}
