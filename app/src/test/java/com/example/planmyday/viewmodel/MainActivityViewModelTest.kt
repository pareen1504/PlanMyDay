package com.example.planmyday.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.planmyday.model.TodoData
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainActivityViewModelTest : TestCase() {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private var mainActivityViewModel: MainActivityViewModel? = null
    private var notes: ArrayList<TodoData>? = null
    private var todo1: TodoData? = null
    private var todo2: TodoData? = null

    @Before
    public override fun setUp() {
        notes = arrayListOf()
        todo1 = TodoData(9144,"1","Capital one interview today",false)
        todo2 = TodoData(1234,"1","Unsubscribe Hotstar",true)
        notes?.add(todo1 ?: TodoData())
        notes?.add(todo2?: TodoData())
        mainActivityViewModel = MainActivityViewModel()
        mainActivityViewModel?.todoList?.value = notes
    }

    @Test
    fun `test ViewModel livedata list`(){
        Assert.assertEquals(notes, mainActivityViewModel?.todoList?.value)
    }

    @Test
    fun `test list data`(){
        Assert.assertEquals(mainActivityViewModel?.todoList?.value!![1].completed, todo2?.completed)
        Assert.assertEquals(mainActivityViewModel?.todoList?.value!![0].id, todo2?.id)
    }
}