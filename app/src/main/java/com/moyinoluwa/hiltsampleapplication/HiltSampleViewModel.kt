package com.moyinoluwa.hiltsampleapplication

import androidx.lifecycle.ViewModel
import com.moyinoluwa.hiltsampleapplication.data.HiltSampleRepository
import javax.inject.Inject

class HiltSampleViewModel @Inject constructor(
    private val hiltSampleRepository: HiltSampleRepository
) : ViewModel() {

    init {
        hiltSampleRepository.retrieveData()
    }
}
