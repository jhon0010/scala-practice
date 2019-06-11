import java.io.IOException
import java.net.MalformedURLException


/**
  * 2.1 conditional expressions
  *
  * in Scala, every expression is supposed to have some value.
*/

val x = 0
val s = if (x > 0) 1 else -1

if (x > 0) "positive" else -1  // returns any that id like object in java
if (x > 0) 1 // if the condition does not apply the vale is Unit = () is like void in java

/**
  * 2.2 Statement Termination
  *  use  }, use arithmetic symbols for continue line (+,*,-,/) , use  ; for separate statements in a line.
  */

/**
  * 2.3 Block Expressions and Assignments
  *
  * The value of the block is the value of the last expression.
  * If the last value is an assignment the return value is Unit.
  * s"$$$price" yields a
  * dollar sign followed by the value of price.
  *
  * Read from console input...
  * import scala.io.StdIn
  * val name2 = StdIn.readLine("Your name: ")
  */
val name = "jhon"
val age = 28
print(f"Hello, $name! In six months, you'll be ${age + 0.5}%7.2f years old.%n")

/**
  * Loops
  */

// The while loop is the same as java and c++
var n = 7
var r = 3
while (n > 0) {
  r = r * n
  n -= 1
}

/**
  * Scala has no break or continue statements to break out of a loop
  * *****  The <- symbol means that the variable i can traverse all the values of a collection.*******
  */
for (i <- 1 to 10)
  print(r * i)

val nq = 10
for (nq <- 1 to 10) {
  // Here nq refers to the loop variable
}

// you can also get multiple
for (i <- 1 to 3; j <- 1 to 3) print(f"${10 * i + j}%3d")


/**
  * Each generator can have a guard, a Boolean condition preceded by if:
  */
for (i <- 1 to 3; j <- 1 to 3 if i != j) print(f"${10 * i + j}%3d")


for (i <- 1 to 10) yield i % 3

/**
  * This kind of for that have a yield in the end are call
  *   for comprehension
  */
case class Person(name: String, age: Int)
val persons = Seq(Person("jhon",28), Person("Pedro", 3))
for(p <- persons) yield if (p.name.contains("j")) {p}


// ----- FUNCTIONS

/**
  * With a recursive function, you must specify the return type. For example,
  */
def fac(n: Int): Int = if (n <= 0) 1 else n * fac(n - 1)

/**
  * You can provide default arguments for functions that are used when you don’t specify explicit values.
  */
def decorate(str: String, left: String = "[", right: String = "]") =
  left + str + right

/**
  * You can also specify the parameter names when you supply the arguments. For example,
  */
val chain = decorate(left = "<<<", str = "Hello", right = ">>>")


/**
  * 2.9 Variable Arguments
  */
def sum(args: Int*) = {
  var result = 0
  for (arg <- args) result += arg
  result
}

sum(1,2,3)

// The remedy is to tell the compiler that you want the parameter to be considered an argument
//sequence. Append : _*, like this
sum(1 to 10 :_*) // _* allow pass a sequence as multiple ints


/**
  Procedures = functions that doesn't have any return type.
  The function doesn't have '=' symbol and return Unit
  */
def printBox(word: String) {
  val line = "*" * word.length
  println(f"$line%n|$word|%n$line%n")
}
printBox("JHon")

/**
  * LAZY VALUES
  */
lazy val words = scala.io.Source.fromFile("/usr/share/dict/words").mkString
//words.foreach(println)


/**
  Exceptions =
    works with match patterns matching
  use _ when you don't need use the variable
  */
val url = "http://horstmann.com/fred-tiny.gif"
try {
  // what ever
} catch {
  case _: MalformedURLException => println(s"Bad URL: $url")
  case ex: IOException => ex.printStackTrace()
} finally {
  // use for close resources or make something that always happen
}

// Exercises

/**
  * The signum of a number is 1 if the number is positive, –1 if it is negative, and 0 if it is zero.
  * Write a function that computes this value.
  *
  * @param number
  * @return
  */
def signum (number: Int) =
  if(number > 0) 1
  else if (number == 0) 0
  else -1

println(signum(0))
println(signum(10))
  println(signum(-18))

/**
  * What is the value of an empty block expression {}? What is its type?
  */
val empty = {}

/**
  * Come up with one situation where the assignment x = y = 1 is valid in Scala. (Hint: Pick a
  * suitable type for x.)
  */
// val x2 = y = 1  Invalid

/**
  * Write a Scala equivalent for the Java loop
  */
for(i <- 1 to 10) {
  println(i)
}

/**
Write a procedure countdown(n: Int) that prints the numbers from n to 0
  */
def printNumbersProcedure {
  for(i <- 1 to 10) {
    println(i)
  }
}

/**
Write a for loop for computing the product of the Unicode codes of all letters in a string. For
example, the product of the characters in "Hello" is 9415087488L.
  */

var sum:Long = 1
for(ch <- "Hello") {
  sum *= ch.toInt
}
println(sum)

"Hello".foldLeft(1l)(_ * _.toInt)



/**
  * Default value arguments
  * @param num1
  * @param num2
  * @return
  */
def sum(num1:Int , num2: Int = 3) = {
  num1 + num2
}

sum(1)


