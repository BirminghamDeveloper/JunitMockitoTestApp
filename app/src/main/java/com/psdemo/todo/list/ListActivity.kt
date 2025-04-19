package com.psdemo.todo.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.junittestapp.databinding.ActivityMainBinding
import com.psdemo.todo.add.AddActivity
import com.psdemo.todo.obtainViewModel

class ListActivity : AppCompatActivity(), TodoAdapter.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listViewModel: ListViewModel
    private var adapter = TodoAdapter(this)

    override fun onCheckboxChecked(id: String) {
        listViewModel.toggleTodo(id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

        listViewModel = obtainViewModel(ListViewModel::class.java)

        binding.contentMain.listTodos.layoutManager = LinearLayoutManager(this)
        binding.contentMain.listTodos.adapter = adapter

        listViewModel.allTodos.observe(this) { todos ->
            todos?.let {
                adapter.setTodos(it)
            }
        }

        listViewModel.upcomingTodosCount.observe(this) { count ->
            binding.contentMain.soonValue.text = count.toString()
        }
    }
}
