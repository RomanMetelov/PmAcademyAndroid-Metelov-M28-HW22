package com.example.pmacademyandroidMetelovM28Hw22.presentation.newPostFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pmacademyandroidMetelovM28Hw22.domain.newPost.NewPostValidationUseCase
import com.example.pmacademyandroidMetelovM28Hw22.domain.newPost.VerificationStatus
import com.example.pmacademyandroidMetelovM28Hw22.domain.newPost.model.NewPostModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateNewPostViewModel @Inject constructor(
    private val validationUseCase: NewPostValidationUseCase,
    private val mapInputErrorsToString: MapInputErrorsToString
) : ViewModel() {

    private val _stringErrorLiveData = MutableLiveData<VerificationStatus<String>>()
    val stringErrorLiveData
        get() = _stringErrorLiveData as LiveData<VerificationStatus<String>>

    fun sendDataToCache(title: String, body: String) {
        viewModelScope.launch {
            _stringErrorLiveData.value =
                when (val result = validationUseCase(NewPostModel(title, body))) {
                    is VerificationStatus.Normal -> VerificationStatus.Normal
                    is VerificationStatus.Error -> VerificationStatus.Error(
                        mapInputErrorsToString(result.errors)
                    )
                }
        }
    }
}
