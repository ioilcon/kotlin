import kotlin.math.sqrt

//1, 2
fun helloWorld() = println("Hello Word")

//3,5
fun helloUser(name : String) {
    println("Hello, $name")
    print("What language do you like? ")
    val favouritePL = readLine()
    when(favouritePL) {
        "Kotlin", "Prolog" -> println("What a sycophant you are ;)")
        "Русский" -> println("It's OK :)")
        else -> println("Not bad :)")
    }
}



fun main() {
    helloWorld()
    val username = readLine()
    helloUser(username.toString())
}