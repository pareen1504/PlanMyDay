package com.example.planmyday

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.planmyday.adapter.TodoAdapter
import com.example.planmyday.databinding.ActivityMainBinding
import com.example.planmyday.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var recyclerview: RecyclerView
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerview = binding.recyclerView

        // we can have singleEvent Livedata so for backNavigation or config changes are handled.
        viewModel.todoList.observe(this) {
            todoAdapter.submitList(it ?: emptyList())
        }

        loadData()
    }

    private fun loadData() {
        //limiting api call to reduce server load
        if (viewModel.todoList.value!!.isEmpty()) {
            viewModel.getAllTodos()
        }

        todoAdapter = TodoAdapter { data ->
            val list = viewModel.todoList.value ?: emptyList()
            list.filter { it.id == data.id }.forEach { it.completed = !data.completed!! }
            viewModel.todoList.value = list.toMutableList()
        }
        recyclerview.adapter = todoAdapter
    }
}