package com.example.junittestapp

import com.example.junittestapp.list.determineCardColor
import org.junit.Assert.assertEquals

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ListUtilsTest(
    private val expected: Int,
    private val dueDate: Long?,
    private val done: Boolean,
    private val scenario: String
) {
    companion object{
        val now = System.currentTimeMillis()
        val day = 1000 * 60 * 60 * 24

        @JvmStatic
        @Parameterized.Parameters(name = "determineCardColor: {3}")
        fun todos() = listOf(
            arrayOf(R.color.todoDone, null, true, "Done, no date"),
            arrayOf(R.color.todoNotDue, null, false, "Not done, no date"),
            arrayOf(R.color.todoOverDue, now - day, false,  "Not done, due yesterday")
        )
    }

    @Test
    fun test_determinCardColor(){
        val actual = determineCardColor(dueDate, done)

        assertEquals(expected, actual)
    }

    @Test
    fun test_determineCardColorNotDone(){
        val actual = determineCardColor(dueDate, done)
        assertEquals(expected, actual)
    }

    @Test
    fun test_determineCardColorOverDue(){
        val actual = determineCardColor(dueDate, done)
        assertEquals(expected, actual)
    }
}