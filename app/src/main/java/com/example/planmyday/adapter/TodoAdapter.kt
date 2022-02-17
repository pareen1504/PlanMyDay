package com.example.planmyday.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.planmyday.R
import com.example.planmyday.model.TodoData
import com.google.android.material.checkbox.MaterialCheckBox

class TodoAdapter(private val itemClicked: (TodoData) -> Unit) :
    ListAdapter<TodoData, TodoAdapter.TodoViewHolder>(ListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = getItem(position)
        holder.data(item, itemClicked)
    }

    // this can be implemented using binding as well
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun data(data: TodoData, itemClicked: (TodoData) -> Unit) {
            with(itemView) {
                val id: TextView = this.findViewById(R.id.todo_id)
                val title: TextView = this.findViewById(R.id.todo_description)
                val checkBox: MaterialCheckBox = this.findViewById(R.id.isCompletedCb)
                id.text = data.id.toString()
                title.text = data.title
                checkBox.isChecked = data.completed ?: false

                checkBox.setOnClickListener {
                    itemClicked.invoke(data)
                }
            }
        }
    }
}

class ListDiffCallback : DiffUtil.ItemCallback<TodoData>() {
    override fun areItemsTheSame(oldItem: TodoData, newItem: TodoData): Boolean {
        return oldItem.completed == newItem.completed
    }

    override fun areContentsTheSame(oldItem: TodoData, newItem: TodoData): Boolean {
        return oldItem == newItem
    }

}
