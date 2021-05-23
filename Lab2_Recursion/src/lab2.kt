import java.util.Scanner;

//1
fun digitsSumUp(number : Int) = digitsProcessingUp(number, 0, {a, b -> a + b})

//2
fun digitsDumDown(number : Int) = digitsProcessingDown(number, 0, {a, b -> a + b})

//3
fun digitsMulUp(number : Int) = digitsProcessingUp(number, 1, {a, b -> a * b})

fun digitsMinUp(number : Int) = digitsProcessingUp(number, 9, {a, b -> if(a < b) a else b})

fun digitsMaxUp(number : Int) = digitsProcessingUp(number, 0, {a, b -> if(a > b) a else b})

fun digitsMulDown(number : Int) = digitsProcessingDown(number, 1, {a, b -> a * b})

fun digitsMinDown(number : Int) = digitsProcessingDown(number, 9, {a, b -> if(a < b) a else b})

fun digitsMaxDown(number : Int) = digitsProcessingDown(number, 0, {a, b -> if(a > b) a else b})

//4 - digitsProcessingUp и digitsProcessingDown с 3мя аргументами выполняют заданный функционал

//5 - digitsProcessingUp и digitsProcessingDown с 4мя аргументами выполняют заданный функционал

//6
// Делит число на все его ненулевые цифры (остаток отбрасывается)
fun test1(number : Int) = digitsProcessingDown(number, number, {a, b -> b / a}, {a -> a != 0})

// Считает количество чётных цифр в числе
fun test2(number : Int) = digitsProcessingDown(number, 0, {_, b -> b + 1}, {a -> a % 2 == 0})

// Разворачивает число (да, без условния :( )
fun test3(number : Int) = digitsProcessingDown(number, 0, {a, b -> b * 10 + a})

//7

fun digitsProcessingUp(number : Int, accumulator : Int, func : (Int, Int) -> Int, pr : (Int) -> Boolean = {_ -> true}) : Int =
    when {
        number == 0 -> accumulator
        pr(number % 10) -> func(number % 10, digitsProcessingUp(number / 10, accumulator, func, pr))
        else -> digitsProcessingUp(number / 10, accumulator, func, pr)
    }

tailrec fun digitsProcessingDown(number : Int, accumulator : Int, func : (Int, Int) -> Int, pr : (Int) -> Boolean = {_ -> true}) : Int =
    if (number == 0) accumulator else
        digitsProcessingDown(number / 10, if (pr(number % 10)) func(number % 10, accumulator) else accumulator, func, pr)

fun main() {
    print("Insert number: ")
    val input = Scanner(System.`in`)
    val number : Int = input.nextInt()
    println(test1(number))
    println(test2(number))
    println(test3(number))
}
