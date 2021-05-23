import java.util.Scanner;

//1
fun digitsSumUp(number : Int) = digitsProcessingUp(number, 0, {a, b -> a + b}, {_ -> true})

//2
fun digitsDumDown(number : Int) = digitsProcessingDown(number, 0, {a, b -> a + b}, {_ -> true})

//3
fun digitsMulUp(number : Int) = digitsProcessingUp(number, 1, {a, b -> a * b}, {_ -> true})

fun digitsMinUp(number : Int) = digitsProcessingUp(number, 9, {a, b -> if(a < b) a else b}, {_ -> true})

fun digitsMaxUp(number : Int) = digitsProcessingUp(number, 0, {a, b -> if(a > b) a else b}, {_ -> true})

fun digitsMulDown(number : Int) = digitsProcessingDown(number, 1, {a, b -> a * b}, {_ -> true})

fun digitsMinDown(number : Int) = digitsProcessingDown(number, 9, {a, b -> if(a < b) a else b}, {_ -> true})

fun digitsMaxDown(number : Int) = digitsProcessingDown(number, 0, {a, b -> if(a > b) a else b}, {_ -> true})

//4

fun digitsProcessingUp(number : Int, accumulator : Int, func : (Int, Int) -> Int, pr : (Int) -> Boolean) : Int =
    when {
        number == 0 -> accumulator
        pr(number % 10) -> func(number % 10, digitsProcessingUp(number / 10, accumulator, func, pr))
        else -> digitsProcessingUp(number / 10, accumulator, func, pr)
    }

tailrec fun digitsProcessingDown(number : Int, accumulator : Int, func : (Int, Int) -> Int, pr : (Int) -> Boolean) : Int =
    if (number == 0) accumulator else
        digitsProcessingDown(number / 10, if (pr(number % 10)) func(number % 10, accumulator) else accumulator, func, pr)

fun main() {
    print("Insert number: ")
    val input = Scanner(System.`in`)
    val number : Int = input.nextInt()
    println(digitsSumUp(number))
}
