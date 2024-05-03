
package com.example.anime_cn

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anime_cn.apiData2.Datas
import com.example.anime_cn.apiData2.UserAvatar
import kotlinx.coroutines.launch

class AnimeViewModel : ViewModel() {
    private val _animestion = MutableLiveData<ArrayList<Datas>>()
    val animestion: LiveData<ArrayList<Datas>> = _animestion

    private val _progressBar = MutableLiveData(false)
    val progressBar_: LiveData<Boolean> = _progressBar

    private var page = 1
    private var totalPages = 1
//    private var isLoading = false
fun fetchAnime() {
        viewModelScope.launch {
            try {
                _progressBar.postValue(true)
                val retIn = RetrofitClient.create()
                val response = retIn.getUser(page.toString())

                if (response.isSuccessful) {

                    response.body()?.let { responseBody ->

                        val userData: ArrayList<Datas> = responseBody.data
                        totalPages = responseBody.totalPages

                        _animestion.postValue(userData)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
//                isLoading = false
                _progressBar.postValue(false)
            }
        }
    }

    fun getNextPage() {

        val newPage = page++

        if (newPage < totalPages){
            fetchAnime()
        }
    }
}



