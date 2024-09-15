package com.example.maintodolist

import java.util.Calendar
import java.util.Date

fun main() {
    val toDoList = ToDoList()

    while (true) {
        println("1-> add task")
        println("2-> remove task")
        println("3-> complete task")
        println("4-> show tasks")
        println("0-> exit")

        when (readLine()) {
            "1" -> {
                print("Add your item: ")
                val item = readLine()
                if (!item.isNullOrBlank()) {
                    toDoList.addTask(item)
                    toDoList.showTask()
                }
            }
            "2" -> {
                print("Input the item number to remove: ")
                val itemForRemoved = readLine()?.toIntOrNull()?.minus(1)
                if (itemForRemoved != null) {
                    toDoList.deleteTask(itemForRemoved)
                    toDoList.showTask()
                } else {
                    println("Invalid input.")
                }
            }
            "3" -> {
                print("Input the item number to mark as completed: ")
                val itemForCompleted = readLine()?.toIntOrNull()?.minus(1)
                if (itemForCompleted != null) {
                    toDoList.completed(itemForCompleted)
                    toDoList.showTask()
                } else {
                    println("Invalid input.")
                }
            }
            "4" -> {
                toDoList.showTask()
            }
            "0" -> {
                println("Exiting...")
                break
            }
            else -> {
                println("Invalid option. Please try again.")
            }
        }
    }
}

data class Task(
    val id: Int = 0,
    val title: String = "",
    var isCompleted: Boolean = false,
    val createdDate: Date = Calendar.getInstance().time
)

class ToDoList {

    private val taskList = mutableListOf<Task>()
    private var nextId = 0

    fun addTask(title: String) {
        taskList.add(Task(id = nextId++, title = title))
        println("Item '$title' added.")
    }

    fun deleteTask(index: Int) {
        if (index in taskList.indices) {
            val removedTask = taskList.removeAt(index)
            println("Item '${removedTask.title}' removed.")
        } else {
            println("Invalid item number.")
        }
    }

    fun completed(index: Int) {
        if (index in taskList.indices) {
            taskList[index].isCompleted = true
            println("Item '${taskList[index].title}' marked as completed.")
        } else {
            println("Invalid item number.")
        }
    }

    fun showTask() {
        if (taskList.isEmpty()) {
            println("Your list is empty.")
        } else {
            for ((index, task) in taskList.withIndex()) {
                val status = if (task.isCompleted) "Completed" else "Pending"

                println("${index + 1}. ${task.title} [$status]")

            }
        }
        println()
    }
}
