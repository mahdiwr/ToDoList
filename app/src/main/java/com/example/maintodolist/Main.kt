package com.example.maintodolist

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





