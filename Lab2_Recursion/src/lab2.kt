import java.util.Scanner;
import java.math.BigInteger;
import kotlin.math.sqrt
import kotlin.math.min
import kotlin.math.max

//1
fun digitsSumUp(number : Int) = digitsProcessingUp(number, 0, {a, b -> a + b})

//2
fun digitsSumDown(number : Int) = digitsProcessingDown(number, 0, {a, b -> a + b})

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
fun op(op : Char) : (Int, Int) -> Int = when (op) {
    '+' -> {a, b -> a + b}
    '-' -> {a, b -> a - b}
    '*' -> {a, b -> a * b}
    '/' -> {a, b -> if (b != 0) a / b else throw IllegalArgumentException("Unknown operation")}
    '%' -> {a, b -> if (b != 0) a % b else throw IllegalArgumentException("Unknown operation")}
    else -> throw IllegalArgumentException("Unknown operation")
}

//9 - 16
fun digits2in1000() : Int = charsProcessing(BigInteger.ONE.shiftLeft(1000).toString(), 0, 0, {a, b -> a+b}, {_ -> true})

//9 - 36
fun palindromeSum() = palindromeSum(1, 1000000, 0)
tailrec  fun palindromeSum(current : Int, border : Int, sum: Long) : Long = when {
    current == border -> sum
    isPalindrome(current.toString()) && isPalindrome(current.toString(2)) -> palindromeSum(current + 1, border, sum + current)
    else -> palindromeSum(current + 1, border, sum)
}

//9 - 56
fun max100in100() = max100in100(1, 0)
tailrec fun max100in100(current : Int, max : Int) : Int = when {
    current > 100 -> max
    aIn100(current) > max -> max100in100(current + 1, aIn100(current))
    else -> max100in100(current + 1, max)
}

fun aIn100(a : Int) = aIn100(a, 0, 1, a.toBigInteger())
tailrec fun aIn100(a : Int, max : Int, pow : Int, current: BigInteger) : Int = when {
    pow > 100 -> max
    digitsProcessingDown(current * a.toBigInteger(), 0, { a, b -> a + b}) > max -> aIn100(a, digitsProcessingDown(current * a.toBigInteger(), 0, { a, b -> a + b}), pow + 1, current * a.toBigInteger())
    else -> aIn100(a, max, pow + 1, current * a.toBigInteger())
}

fun digitsProcessingUp(number : Int, accumulator : Int, func : (Int, Int) -> Int, pr : (Int) -> Boolean = {_ -> true}) : Int =
    when {
        number == 0 -> accumulator
        pr(number % 10) -> func(number % 10, digitsProcessingUp(number / 10, accumulator, func, pr))
        else -> digitsProcessingUp(number / 10, accumulator, func, pr)
    }

tailrec fun digitsProcessingDown(number : Int, accumulator : Int, func : (Int, Int) -> Int, pr : (Int) -> Boolean = {_ -> true}) : Int =
    if (number == 0) accumulator else
        digitsProcessingDown(number / 10, if (pr(number % 10)) func(number % 10, accumulator) else accumulator, func, pr)

tailrec fun digitsProcessingDown(number : BigInteger, accumulator : Int, func : (Int, Int) -> Int, pr : (Int) -> Boolean = {_ -> true}) : Int =
    if (number == BigInteger.ZERO) accumulator else
        digitsProcessingDown(number / BigInteger.TEN, if (pr((number % BigInteger.TEN).toInt())) func((number % BigInteger.TEN).toInt(), accumulator) else accumulator, func, pr)

tailrec fun charsProcessing(str : String, index : Int, accumulator : Int, func : (Int, Int) -> Int, pr : (Int) -> Boolean = {_ -> true}) : Int =
    if(index == str.length) accumulator else
        charsProcessing(str, index + 1, if (pr(str[index] - '0')) func(str[index] - '0', accumulator) else accumulator, func, pr)

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

fun isPalindrome(number : String) : Boolean = isPalindrome(number, 0, number.length / 2)
tailrec fun isPalindrome(number : String, current : Int, border : Int) : Boolean = when {
    current == border -> true
    number[current] != number[number.length - current - 1] -> false
    else -> isPalindrome(number, current + 1, border)
}

fun main() {
    print("Insert number: ")
    val input = Scanner(System.`in`)
    val number : Int = input.nextInt()
    println(test1(number))
    println(test2(number))
    println(test3(number))
    println(digits2in1000())
    println(palindromeSum())
    println(max100in100())
}
