package com.example.pmacademyandroid_metelov_m28_hw22.presentation.showPostsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pmacademyandroid_metelov_m28_hw22.data.PostsInfoRepository
import com.example.pmacademyandroid_metelov_m28_hw22.domain.GetAllPostsUseCase
import com.example.pmacademyandroid_metelov_m28_hw22.presentation.PostUiMapper
import com.example.pmacademyandroid_metelov_m28_hw22.presentation.PostUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class ShowAllPostsViewModel @Inject constructor(
        private val allPostsUseCase: GetAllPostsUseCase,
        postsInfoRepository: PostsInfoRepository,
        private val postUiMapper: PostUiMapper
) : ViewModel(), Observer {

    init {
        postsInfoRepository.addObserverFun(this)
    }

    private val _postsLiveData = MutableLiveData<List<PostUiModel>>()
    val postsLiveData: LiveData<List<PostUiModel>>
        get() = _postsLiveData

    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            val listPostsUiModel = postUiMapper.map(allPostsUseCase.invoke())
            _postsLiveData.postValue(listPostsUiModel)
        }
    }

    override fun update(o: Observable?, arg: Any?) {
        getPosts()
    }
}