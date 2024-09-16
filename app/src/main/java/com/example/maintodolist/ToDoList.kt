package com.example.maintodolist

import com.google.gson.Gson
import java.io.File
import java.util.Calendar

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