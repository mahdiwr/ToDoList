package com.example.maintodolist

import android.app.DownloadManager.Query
import com.google.gson.Gson
import java.io.File
import java.util.Calendar
import java.util.Date


fun main() {
    val toDoList = ToDoList()
    val filePath = "D:/list"

    while (true) {
        println("1-> Add task")
        println("2-> Remove task")
        println("3-> Complete task")
        println("4-> Show tasks")
        println("5-> Save file")
        println("6-> Input Json")
        println("7-> Sort By Date")
        println("8-> Search task")
        println("0-> Exit")

        when (readlnOrNull()) {
            "1" -> {
                print("Add your item: ")
                val item = readlnOrNull()
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

            "5" -> {
                toDoList.saveListToJsonFile(toDoList.taskList, filePath)
                println("your file was saved")
            }

            "6" -> {
                println("input your path : ")
                val path_file = readlnOrNull()
                toDoList.gsonReader(path_file ?: "")
            }

            "7" -> {
                println("sorted by Date : ${toDoList.sort(toDoList.taskList)}")
                toDoList.taskList.forEach { task ->
                    println("Task: ${task.title}, Created at: ${task.createdDate}")
                }
            }

            "8" -> {
                println("input your key : ")
                val key_word = readlnOrNull()
                toDoList.search(key_word ?: "")
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
    val createdDate: Date
)

class ToDoList {

    val taskList = mutableListOf<Task>()
    var nextId = 0

    fun addTask(title: String) {
        taskList.add(Task(id = nextId++, title = title, createdDate = Calendar.getInstance().time))
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

                println("${index + 1}. ${task.title} [$status] created at : ${task.createdDate}")

            }
        }
        println()
    }

    fun saveListToJsonFile(list: MutableList<Task>, filePath: String) {
        val jsonString = Gson().toJson(list)
        File(filePath, "todo.json").writeText(jsonString)
    }

    fun gsonReader(filePath: String) {
        val jsonString = File(filePath).readText(Charsets.UTF_8)
        val gson = Gson()
        val tasks: List<Task> = gson.fromJson(jsonString, Array<Task>::class.java).toList()
        taskList.addAll(tasks)
        println("your file was added")
    }

    fun sort(taskList: MutableList<Task>) {
        taskList.sortBy { it.createdDate }
    }

    fun search(query: String) {
        val check: ArrayList<Task> = arrayListOf()
        taskList.forEach { task->
            if(task.title==query){
                check.add(task)
            }
        }
        if (check.isEmpty()) {
            println("No tasks found with the query $query.")
        } else {
            println(check)
        }
    }
}

