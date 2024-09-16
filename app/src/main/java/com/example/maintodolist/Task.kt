package com.example.maintodolist

import java.util.Date

data class Task(
    val id: Int = 0,
    val title: String = "",
    var isCompleted: Boolean = false,
    val createdDate: Date
)
