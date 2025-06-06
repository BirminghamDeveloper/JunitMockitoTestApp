package com.example.junittestapp.list

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.junittestapp.R
import com.example.junittestapp.data.Todo
import java.util.*
import kotlin.collections.ArrayList

class TodoAdapter(val onClickListener: OnClickListener) :
    RecyclerView.Adapter<TodoAdapter.TodoHolder>() {
    private var allTodos: List<Todo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_item, parent, false)
        return TodoHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allTodos.size
    }

    fun setTodos(todos: List<Todo>) {
        this.allTodos = todos
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.bind(allTodos[position], onClickListener)
    }

    inner class TodoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val completed = itemView.findViewById<CheckBox>(R.id.completed)
        val title = itemView.findViewById<TextView>(R.id.title)
        val start = itemView.findViewById<TextView>(R.id.start)
        val dueLabel = itemView.findViewById<TextView>(R.id.due_label)
        val due = itemView.findViewById<TextView>(R.id.due)
        val card = itemView.findViewById<CardView>(R.id.card)

        fun bind(todo: Todo, clickListener: OnClickListener) {
            completed.isChecked = todo.completed
            completed.setOnClickListener { clickListener.onCheckboxChecked(todo.id) }

            title.text = todo.title
            val calendar = Calendar.getInstance()
            val dateFormat = DateFormat.getDateFormat(itemView.context)

            calendar.timeInMillis = todo.created
            start.text = dateFormat.format(calendar.time)

            if (todo.dueDate != null) {
                calendar.timeInMillis = todo.dueDate!!
                due.text = dateFormat.format(calendar.time)
            } else {
                due.visibility = View.INVISIBLE
                dueLabel.visibility = View.INVISIBLE
            }

            card.setCardBackgroundColor(
                itemView.context.getColor(
                    determineCardColor(
                        todo.dueDate,
                        todo.completed
                    )
                )
            )
        }
    }

    interface OnClickListener {
        fun onCheckboxChecked(id: String)
    }
}