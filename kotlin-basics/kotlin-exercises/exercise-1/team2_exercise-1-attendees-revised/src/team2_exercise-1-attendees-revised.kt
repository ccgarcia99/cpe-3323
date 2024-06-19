fun main() {
    var attendees = mutableMapOf<String, String>()
    println("Enter the names of attendees, one at a time. Enter an empty line in either input to finish:")

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
    // Sort the map and assign it on an empt
    attendees = attendees.toSortedMap()
    // Sort the list of attendees
    count = 1
    // Print the sorted list of attendees
    println("The following people have attended (sorted):")
    for (attendee in attendees) {
        println("$count: $attendee")
        count++
    }
}
