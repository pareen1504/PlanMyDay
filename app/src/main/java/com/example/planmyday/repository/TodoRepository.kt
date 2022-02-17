package com.example.planmyday.repository

import com.example.planmyday.api.RetrofitManager

class TodoRepository {
    private val apiRequest =  RetrofitManager.api
    val call = apiRequest.getAllTask()
}