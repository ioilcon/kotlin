import kotlin.math.sqrt
import kotlin.math.max
import kotlin.math.min
import java.util.Scanner;
import java.math.BigInteger;

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

//6
fun digitsSum(number : Int) = digitsProcessing(number, 0, {a, b -> a + b}, {_ -> true})
fun digitsSum(number : BigInteger) = digitsProcessing(number, 0, {a, b -> a + b}, {_ -> true})

//7
fun digitsMax(number : Int) = digitsProcessing(number, 0, {a, b -> if (a > b) a else b}, {_ -> true})

fun digitsMin(number : Int) = digitsProcessing(number, 9, {a, b -> if (a < b) a else b}, {_ -> true})

fun digitsMul(number : Int) = digitsProcessing(number, 1, {a, b -> a * b}, {_ -> true})

//8
fun digitsNotSimpleSum(number : Int) = digitsProcessing(number, 0, {a, b -> a + b}, {a -> !isSimple(a)})

fun digitsCount3(number : Int) = digitsProcessing(number, 0, {_, b -> b + 1}, {a -> a < 3})

fun smtnStrange(number : Int) : Int = smtnStrange(number, 2, digitsProcessing(number, 0, {a, b -> a + b}, {a -> isSimple(a)}), 0)
tailrec fun smtnStrange(number : Int, current : Int, simpleDigitsSum : Int, accumulator : Int) : Int = when {
    current == number -> accumulator
    number % current != 0 && GCD(current, number) != 1 && GCD(current, simpleDigitsSum) == 1 -> smtnStrange(number, current + 1, simpleDigitsSum, accumulator + 1)
    else -> smtnStrange(number, current + 1, simpleDigitsSum, accumulator)
}

//9
tailrec fun funSelector() {
    print("Insert number: ")
    val input = Scanner(System.`in`)
    val number : Int = input.nextInt()
    print("Insert task number or '0' to exit: ")
    val task = readLine()
    when(task) {
        "1", "2" -> helloWorld()
        "3", "5" -> helloUser("User")
        "4" -> println("(-_-|)")
        "6" -> println("digitsSum: ${digitsSum(number)}")
        "7" -> {
            println("digitsMax: ${digitsMax(number)}")
            println("digitsMin: ${digitsMin(number)}")
            println("digitsMul: ${digitsMul(number)}")
        }
        "8" -> {
            println("digitsNotSimpleSum: ${digitsNotSimpleSum(number)}")
            println("digitsCount3: ${digitsCount3(number)}")
            println("smtnStrange: ${smtnStrange(number)}")
        }
        "9" -> println("(|-_-)")
        "10" -> {
            println("digits2in1000: ${digits2in1000()}")
            println("palindromeSum: ${palindromeSum()}")
            println("max100in100: ${max100in100()}")
        }
        "0" -> return
        else -> println("|o_o|?")
    }
    funSelector()
}

//10 - 16
fun digits2in1000() : Int {
    val number = BigInteger.ONE.shiftLeft(1000).toString()
    return charsProcedding(number, 0, 0, {a, b -> a+b}, {_ -> true})
}

//10 - 36
fun palindromeSum() = palindromeSum(1, 1000000, 0)
tailrec  fun palindromeSum(current : Int, border : Int, sum: Long) : Long = when {
    current == border -> sum
    isPalindrome(current.toString()) && isPalindrome(current.toString(2)) -> palindromeSum(current + 1, border, sum + current)
    else -> palindromeSum(current + 1, border, sum)
}

//10 - 56
fun max100in100() = max100in100(1, 0)
tailrec fun max100in100(current : Int, max : Int) : Int = when {
    current > 100 -> max
    aIn100(current) > max -> max100in100(current + 1, aIn100(current))
    else -> max100in100(current + 1, max)
}

fun aIn100(a : Int) = aIn100(a, 0, 1, a.toBigInteger())
tailrec fun aIn100(a : Int, max : Int, pow : Int, current: BigInteger) : Int = when {
    pow > 100 -> max
    digitsSum(current * a.toBigInteger()) > max -> aIn100(a, digitsSum(current * a.toBigInteger()), pow + 1, current * a.toBigInteger())
    else -> aIn100(a, max, pow + 1, current * a.toBigInteger())
}

fun isSimple(number : Int) : Boolean = isSimple(number, 2, sqrt(number.toDouble()))
tailrec fun isSimple(number : Int, current : Int, border : Double) : Boolean = when {
    current > border -> true
    number % current == 0 -> false
    else -> isSimple(number, current + 1, border)
}

fun isPalindrome(number : String) : Boolean = isPalindrome(number, 0, number.length / 2)
tailrec fun isPalindrome(number : String, current : Int, border : Int) : Boolean = when {
    current == border -> true
    number[current] != number[number.length - current - 1] -> false
    else -> isPalindrome(number, current + 1, border)
}

fun GCD(first : Int, second : Int) : Int = when {
    first == second -> first
    first == 0 || second == 0 -> 0
    else -> GCD(max(first, second) - min(first, second), min(first, second))
}

tailrec fun digitsProcessing(number : Int, accumulator : Int, func : (Int, Int) -> Int, pr : (Int) -> Boolean) : Int =
    if (number == 0) accumulator else
        digitsProcessing(number / 10, if (pr(number % 10)) func(number % 10, accumulator) else accumulator, func, pr)

tailrec fun digitsProcessing(number : BigInteger, accumulator : Int, func : (Int, Int) -> Int, pr : (Int) -> Boolean) : Int =
    if (number == BigInteger.ZERO) accumulator else
        digitsProcessing(number / BigInteger.TEN, if (pr((number % BigInteger.TEN).toInt())) func((number % BigInteger.TEN).toInt(), accumulator) else accumulator, func, pr)

tailrec fun charsProcedding(str : String, index : Int, accumulator : Int, func : (Int, Int) -> Int, pr : (Int) -> Boolean) : Int =
    if(index == str.length) accumulator else
        charsProcedding(str, index + 1, if (pr(str[index] - '0')) func(str[index] - '0', accumulator) else accumulator, func, pr)

fun main() {
    funSelector()
}