package com.example.planmyday.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.planmyday.model.TodoData
import com.example.planmyday.repository.TodoRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    val todoList = MutableLiveData<List<TodoData>>(mutableListOf())

    // before calling this we need to check device has active internet connection
    fun getAllTodos() {
        TodoRepository().call.enqueue(object : Callback<List<TodoData>> {
            override fun onResponse(call: Call<List<TodoData>>,
                response: Response<List<TodoData>>
            ) {
                //we can have event based call using sealed classes here.
                Log.e("response",response.body().toString())
                if (response.isSuccessful) {
                    todoList.postValue(response.body())
                } else {
                    //Handle error
                }
            }

            override fun onFailure(call: Call<List<TodoData>>, t: Throwable) {
               // TODO("Handle Error cases here")
            }

        })
    }

}