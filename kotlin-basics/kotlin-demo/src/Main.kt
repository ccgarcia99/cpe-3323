fun main() {
    val unlistedAnimal = "cow"
    val arrayString: Array<String> = arrayOf("dog", "cat", "fey")
    println("One of these aren't animals: " + arrayString.contentToString())
    val nonAnimal = arrayString[2]
    println("Removing $nonAnimal")

    // Convert the array to a mutable list, remove the element at the specified index, and convert back to an array if needed
    val mutableList = arrayString.toMutableList()
    mutableList.removeAt(2)
    println("Adding $unlistedAnimal to the list at the second index...")
    mutableList.add(1, unlistedAnimal)

    // If you need it back as an array
    val updatedArray = mutableList.toTypedArray()

    for (item in updatedArray) {
        println(item)
    }
}
