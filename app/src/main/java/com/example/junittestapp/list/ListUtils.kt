package com.example.junittestapp.list

import com.example.junittestapp.R


fun determineCardColor(dueDate: Long?, done: Boolean): Int {
    var color = R.color.todoNotDue

    if (done) return R.color.todoDone

    if (dueDate != null) {
        val now = System.currentTimeMillis()
        val day = 1000 * 60 * 60 * 24
        val daysUntilDue = (dueDate.minus(now)) / day

        color = when {
            daysUntilDue <= 0 -> R.color.todoOverDue
            daysUntilDue <= 7 -> R.color.todoDueStrong
            daysUntilDue <= 14 -> R.color.todoDueMedium
            else -> R.color.todoDueLight
        }
    }

    return color
}
