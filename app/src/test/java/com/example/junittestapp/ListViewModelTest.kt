package com.example.junittestapp

import com.example.junittestapp.data.Todo
import com.example.junittestapp.data.TodoRepository
import com.example.junittestapp.list.ListViewModel
import com.example.junittestapp.util.TodoTestRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test

class ListViewModelTest {
    lateinit var repository: TodoRepository

    @Before
    fun setup(){
        val now = System.currentTimeMillis()
        val day = 1000 * 60 * 60 * 24

        val todos = ArrayList<Todo>()
        var todo = Todo("1", "Todo 1", null, false, now)
        todos.add(todo)
        todo = Todo("2", "Todo 2", now + day, false, now)
        todos.add(todo)
        todo = Todo("3", "Todo 3", now + day, true, now)
        todos.add(todo)
        todo = Todo("4", "Todo 4", now - day, false, now)
        todos.add(todo)

        repository = TodoTestRepository(todos)
    }

    @Test
    fun test_allTodoso(){
        val expected = 5
        val model = ListViewModel(repository)

        val todos = model.allTodos.value

        assertNotNull(todos)
        assertEquals(expected, todos!!.size)
    }

    @Test
    fun test_upcomingTodosCount(){
        val expected = 2
        val model = ListViewModel(repository)

        val todoCount = model.upcomingTodosCount.value

        assertNotNull(todoCount)
        assertEquals(expected, todoCount)
    }
}