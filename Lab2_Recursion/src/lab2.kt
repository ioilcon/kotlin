import java.util.Scanner;
import kotlin.math.sqrt
import kotlin.math.min
import kotlin.math.max

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

fun digitsNotSimpleSum(number : Int) = digitsProcessingDown(number, 0, {a, b -> a + b}, {a -> !isSimple(a)})

fun digitsCount3(number : Int) = digitsProcessingDown(number, 0, {_, b -> b + 1}, {a -> a < 3})

fun smtnStrange(number : Int) : Int = smtnStrange(number, 2, digitsProcessingDown(number, 0, {a, b -> a + b}, {a -> isSimple(a)}), 0)
tailrec fun smtnStrange(number : Int, current : Int, simpleDigitsSum : Int, accumulator : Int) : Int = when {
    current == number -> accumulator
    number % current != 0 && GCD(current, number) != 1 && GCD(current, simpleDigitsSum) == 1 -> smtnStrange(number, current + 1, simpleDigitsSum, accumulator + 1)
    else -> smtnStrange(number, current + 1, simpleDigitsSum, accumulator)
}

//8

fun digitsProcessingUp(number : Int, accumulator : Int, func : (Int, Int) -> Int, pr : (Int) -> Boolean = {_ -> true}) : Int =
    when {
        number == 0 -> accumulator
        pr(number % 10) -> func(number % 10, digitsProcessingUp(number / 10, accumulator, func, pr))
        else -> digitsProcessingUp(number / 10, accumulator, func, pr)
    }

tailrec fun digitsProcessingDown(number : Int, accumulator : Int, func : (Int, Int) -> Int, pr : (Int) -> Boolean = {_ -> true}) : Int =
    if (number == 0) accumulator else
        digitsProcessingDown(number / 10, if (pr(number % 10)) func(number % 10, accumulator) else accumulator, func, pr)

fun isSimple(number : Int) : Boolean = isSimple(number, 2, sqrt(number.toDouble()))
tailrec fun isSimple(number : Int, current : Int, border : Double) : Boolean = when {
    current > border -> true
    number % current == 0 -> false
    else -> isSimple(number, current + 1, border)
}

fun GCD(first : Int, second : Int) : Int = when {
    first == second -> first
    first == 0 || second == 0 -> 0
    else -> GCD(max(first, second) - min(first, second), min(first, second))
}

fun main() {
    print("Insert number: ")
    val input = Scanner(System.`in`)
    val number : Int = input.nextInt()
    println(test1(number))
    println(test2(number))
    println(test3(number))
}
