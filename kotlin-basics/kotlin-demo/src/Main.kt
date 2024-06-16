class Car(private var make: String, private var model: String, private var color: String) {
    fun printDetails() {
        println("$color $make $model")
    }

    fun maxSpeed(carSpeed: Int) {
        println("Top speed of the $make $model is ${carSpeed}km/h!")
    }
}

fun main() {
    val carOne = Car("BMW", "i3", "Orange")
    val carTwo = Car("Porsche", "911", "Yellow")

    carOne.printDetails()
    carOne.maxSpeed(200)
    carTwo.printDetails()
    carTwo.maxSpeed(250)
}
