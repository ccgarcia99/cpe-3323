import java.util.Scanner

// Enums
enum class ShirtColor {
    RED, GREEN, BLACK, BLUE
}

enum class ShirtSize {
    SMALL, MEDIUM, LARGE
}

enum class ShirtQuote(val index: Int) {
    QUOTE_1(1) {
        override fun getQuote() = "After a storm comes a calm"
    },
    QUOTE_2(2) {
        override fun getQuote() = "Be kind whenever possible, It is always possible"
    },
    QUOTE_3(3) {
        override fun getQuote() = "Have faith in your abilities!"
    };

    abstract fun getQuote(): String
}

enum class Gender {
    MALE, FEMALE, OTHER
}

enum class Location {
    FRONT, BACK
}

// Superclass
open class ShirtMod(
    protected var color: ShirtColor,
    protected var size: ShirtSize,
    protected var quote: ShirtQuote,
    protected var name: String,
    protected var gender: Gender,
    protected var location: Location
)

// Subclass inheriting from the superclass
class CustomShirt(
    color: ShirtColor,
    size: ShirtSize,
    quote: ShirtQuote,
    name: String,
    gender: Gender,
    location: Location,
    var orderNumber: Int,
) : ShirtMod(color, size, quote, name, gender, location) {

    fun displayShirtDetails() {
        println("Order Number: $orderNumber")
        println("Shirt Color: $color")
        println("Shirt Size: $size")
        println("Shirt Quote: ${quote.index}")
        println("Name: $name")
        println("Gender: $gender")
        println("Location: $location")
    }

    fun getOrderSummary(): String {
        return "| $orderNumber | ${name.padEnd(10)} | ${gender.toString().padEnd(6)} | ${color.toString().padEnd(5)} | ${size.toString().padEnd(6)} | ${quote.index}    | ${location.toString().padEnd(5)} |"
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val orders = mutableListOf<CustomShirt>()
    var orderCounter = 1

    while (true) {
        println("Menu:")
        println("A: Create a new T-shirt order")
        println("B: View order summary")
        println("C: Exit")
        println("Enter your choice:")
        when (scanner.next().uppercase()) {
            "A" -> {
                scanner.nextLine() // consume newline left-over

                // Get color from user
                println("Select a shirt color (RED, GREEN, BLACK, BLUE):")
                val colorInput = scanner.next().uppercase()
                val color = try {
                    ShirtColor.valueOf(colorInput)
                } catch (e: IllegalArgumentException) {
                    println("Invalid color selection. Please try again.")
                    continue
                }

                // Get size from user
                println("Select a shirt size (SMALL, MEDIUM, LARGE):")
                val sizeInput = scanner.next().uppercase()
                val size = try {
                    ShirtSize.valueOf(sizeInput)
                } catch (e: IllegalArgumentException) {
                    println("Invalid size selection. Please try again.")
                    continue
                }

                // Display quotes
                println("Select a shirt quote:")
                for (quote in ShirtQuote.values()) {
                    println("${quote.index}. ${quote.getQuote()}")
                }
                val quoteInput = scanner.nextInt()
                val quote = ShirtQuote.values().firstOrNull { it.index == quoteInput }
                if (quote == null) {
                    println("Invalid quote selection. Please try again.")
                    continue
                }

                // Get name from user
                println("Enter your name:")
                scanner.nextLine() // consume newline left-over
                val name = scanner.nextLine()

                // Get gender from user
                println("Select your gender (MALE, FEMALE, OTHER):")
                val genderInput = scanner.next().uppercase()
                val gender = try {
                    Gender.valueOf(genderInput)
                } catch (e: IllegalArgumentException) {
                    println("Invalid gender selection. Please try again.")
                    continue
                }

                // Get location from user
                println("Select the quote location (FRONT, BACK):")
                val locationInput = scanner.next().uppercase()
                val location = try {
                    Location.valueOf(locationInput)
                } catch (e: IllegalArgumentException) {
                    println("Invalid location selection. Please try again.")
                    continue
                }

                // Create custom shirt with user inputs
                val customShirt = CustomShirt(color, size, quote, name, gender, location, orderCounter++)
                orders.add(customShirt)
                customShirt.displayShirtDetails()

                println("T-shirt order created successfully!")
            }
            "B" -> {
                println("Total Number of Orders: ${orders.size}")
                println("Order Summary:")
                println("+------------+------------+--------+-------+--------+-------+-------+")
                println("| Order No.  | Name       | Gender | Color | Size   | Quote | Loc.  |")
                println("+------------+------------+--------+-------+--------+-------+-------+")
                for (order in orders) {
                    println(order.getOrderSummary())
                }
                println("+------------+------------+--------+-------+--------+-------+-------+")
            }
            "C" -> {
                println("Exiting program.")
                return
            }
            else -> println("Invalid choice. Please try again.")
        }
    }
}
