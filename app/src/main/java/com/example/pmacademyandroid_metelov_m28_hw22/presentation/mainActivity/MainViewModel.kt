package com.example.pmacademyandroid_metelov_m28_hw22.presentation.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pmacademyandroid_metelov_m28_hw22.data.PostsInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: PostsInfoRepository
) : ViewModel() {

    private val _errorLiveData = MutableLiveData<Boolean>()
    val errorLiveData: LiveData<Boolean> = _errorLiveData

    fun updateRepo(){
        viewModelScope.launch(Dispatchers.IO) {
            val isUpdate = repository.updateLocalStorage()
            withContext(Dispatchers.Main) {
                if (!isUpdate){
                    _errorLiveData.postValue(true)
                }
            }
        }

    }
}