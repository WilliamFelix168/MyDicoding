package com.williamfelix.githubapps

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerModel: ViewModel() {
    private val _listFollower = MutableLiveData<List<FollowerResponseItem>>()
    val listFollower: LiveData<List<FollowerResponseItem>> = _listFollower

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun findFollower(query: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollower(query)
        client.enqueue(object : Callback<List<FollowerResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowerResponseItem>>,
                response: Response<List<FollowerResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.d("TAG",responseBody.toString())
                        _listFollower.value = responseBody
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FollowerResponseItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object{
        private const val TAG = "FollowerModel"
    }
}

