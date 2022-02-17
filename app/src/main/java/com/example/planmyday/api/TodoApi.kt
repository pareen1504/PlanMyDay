package com.example.planmyday.api

import com.example.planmyday.constants.AppConstants.GET_TODOS
import com.example.planmyday.model.TodoData
import retrofit2.Call
import retrofit2.http.GET

interface TodoApi {
    @GET(GET_TODOS)
    fun getAllTask(): Call<List<TodoData>>
}