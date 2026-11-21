
// Represent a task with a name and a completion status
class Task(val name: String, var isCompleted: Boolean = false)

// Ask the user for a task name and add it to the list
fun addTask(tasks: MutableList<Task>) {
    println("\n===== ADD TASK =====")

    print("Enter task name: ")
    val name = readln().trim()

    // Do not allow empty task names
    if (name.isBlank()) {
        println("Task name cannot be empty.")
        return
    }

    // Create and save the new task
    val newTask = Task(name)
    tasks.add(newTask)

    println("Task added successfully!")
}

// Display all tasks and their current status
fun showTasks(tasks: List<Task>) {
    println("\n===== TASK LIST =====")

    // Show a message when the list is empty
    if (tasks.isEmpty()) {
        println("No tasks found.")
        return
    }

    // Display each task with its number
    for (i in tasks.indices) {
        val task = tasks[i]
        val number = i + 1

        // Choose the status text
        val status = if (task.isCompleted) "Completed" else "Pending"

        println("$number. ${task.name} - $status")
    }

    // Count completed and pending tasks
    var completedCount = 0

    for (task in tasks) {
        if (task.isCompleted) {
            completedCount++
        }
    }

    val pendingCount = tasks.size - completedCount

    // Display the task summary
    println("Total tasks: ${tasks.size}")
    println("Completed tasks: $completedCount")
    println("Pending tasks: $pendingCount")

}

// Ask for a task number and mark it as completed
fun completeTask(tasks: MutableList<Task>) {
    println("\n===== COMPLETE TASK =====")

    // Stop if there are no tasks
    if (tasks.isEmpty()) {
        println("No tasks available.")
        return
    }

    showTasks(tasks)

    print("Enter task number: ")
    val number = readln().toIntOrNull()

    // Check if the number exists in the list
    if (number == null || number < 1 || number > tasks.size) {
        println("Invalid task number.")
        return
    }

    // Find the selected task
    val task = tasks[number - 1]

    // Avoid completing the same task twice
    if (task.isCompleted) {
        println("This task is already completed.")
        return
    }

    // Update the completion status
    task.isCompleted = true
    println("Task completed successfully!")
}

// Remove a task selected by its number
fun removeTask(tasks: MutableList<Task>) {
    println("\n===== REMOVE TASK =====")

    // Stop if the list is empty
    if (tasks.isEmpty()) {
        println("No tasks available.")
        return
    }

    showTasks(tasks)

    print("Enter task number: ")
    val number = readln().toIntOrNull()

    // Check if the selected number is valid
    if (number == null || number < 1 || number > tasks.size) {
        println("Invalid task number.")
        return
    }

    // Remove the selected task from the list
    val removedTask = tasks.removeAt(number - 1)

    println("Task removed: ${removedTask.name}")
}

// Display the available menu options
fun showMenu() {
    println("\n===== SIMPLE TASK MANAGER =====")
    println("1. Add task")
    println("2. Show tasks")
    println("3. Complete task")
    println("4. Remove task")
    println("5. Exit")
    print("Choose an option: ")
}

// Run the task manager until the user exits
fun main() {
    // Store all tasks in a modifiable list
    val tasks = mutableListOf<Task>()

    // Keep the program running until the user chooses Exit
    var running = true

    println("Welcome to Simple Task Manager!")

    while (running) {
        // Display the menu and read the user's choice
        showMenu()
        val choice = readln().trim()

        // Execute the selected menu option
        when (choice) {
            "1" -> addTask(tasks)
            "2" -> showTasks(tasks)
            "3" -> completeTask(tasks)
            "4" -> removeTask(tasks)
            "5" -> {
                println("Thank you for using Simple Task Manager!")
                running = false
            }
            else -> println("Invalid option. Please try again.")
        }
    }
}
