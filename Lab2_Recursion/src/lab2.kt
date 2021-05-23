import java.util.Scanner;

//1
fun digitsSumDown(number : Int) = digitsProcessingDown(number, 0, {a, b -> a + b}, {_ -> true})

fun digitsProcessingDown(number : Int, accumulator : Int, func : (Int, Int) -> Int, pr : (Int) -> Boolean) : Int =
    when {
        number == 0 -> accumulator
        pr(number % 10) -> func(number % 10, digitsProcessingDown(number / 10, accumulator, func, pr))
        else -> digitsProcessingDown(number / 10, accumulator, func, pr)
    }

fun main() {
    print("Insert number: ")
    val input = Scanner(System.`in`)
    val number : Int = input.nextInt()
    println(digitsSumDown(number))
}
