fun main() {
    val attendees = mutableListOf<String>()
    println("Enter the names of attendees, one at a time. Enter an empty line to finish:")

    var count = 0
    while (count < 10) {
        print("Enter name ${count + 1}: ")
        val input = readlnOrNull()
        
        if (input.isNullOrEmpty()) {
            break
        } else {
            attendees.add(input)
            count++
        }
    }

    // Sort the list of attendees
    attendees.sort()

    // Print the sorted list of attendees
    println("The following people have attended (sorted):")
    for (attendee in attendees) {
        println(attendee)
    }
}
