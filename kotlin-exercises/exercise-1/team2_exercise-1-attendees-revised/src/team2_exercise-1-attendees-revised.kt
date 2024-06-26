fun main() {
    var attendees = mutableMapOf<String, String>()
    println("Enter the names of attendees & their activity, one at a time. Enter an empty line in either input to finish:")

    var count = 0
    while (count < 10) {
        print("Enter name ${count + 1}: ")
        val name = readlnOrNull()
        print("Enter activity: ")
        val activity = readlnOrNull()

        if (name.isNullOrEmpty() || activity.isNullOrEmpty()) {
            break
        } else {
            attendees[name] = activity
            count++
        }
    }
    // Sort the map and reassign it to the existing map variable
    attendees = attendees.toSortedMap()

    // Sort the list of attendees
    count = 1
    // Print the sorted list of attendees
    println("The following people have attended (sorted):")
    for ((name, activity) in attendees) {
        println("$count: $name - $activity")
        count++
    }
}
