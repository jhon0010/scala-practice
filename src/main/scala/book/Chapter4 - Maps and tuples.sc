



val storesWithMicroZonesId =  scala.collection.mutable.Map[Int, Int]()
storesWithMicroZonesId += (1 -> 1)
storesWithMicroZonesId.foreach(println)

/**
  * Tuples = Scala has a general notion of tuples—aggregates of n
  * objects, not necessarily of the same type.
  * A pair is simply a tuple with n = 2
  * Tuples are useful for aggregating values.
  */


/**
  * -----------------------------------------------------------------
  * Maps are a collection of key -> value , in scala yopu can get a mutable and immutable collection.
  *
  * In scala maps are collectiorens of Pairs , the symbol -> make a pair "jhon" -> 1 or ("jhon", 1)
  */
val immutableMap = Map("Jhon"-> 5, "Peter" -> 8)
val mutableMap = scala.collection.mutable.Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
val mutableMapParenthesis = scala.collection.mutable.Map(("Alice", 10), ("Bob", 3), ("Cindy", 8))

val scores = scala.collection.mutable.Map[String, Int]()


// Access to values
immutableMap("Jhon")
mutableMap("Alice")

// Use contains for avoid the exception when the key doesn't exist
val bobsScore = if (scores.contains("Bob")) scores("Bob") else 0

// or use get or else method instance
val bobsScore2 = scores.getOrElse("Bob", 0)


// updating data in mutable map

mutableMap("Jhon") = 10 // Update bc exist
mutableMap("Fred") = 89 // Insert bc not exist
mutableMap += ("n" -> 1, "x" -> 7) // add multiple values
mutableMap -= "n" // remove

val newScores = immutableMap + ("Bob" -> 10, "Fred" -> 7) // New map with update


/**
  * -------------------------------------------------
  * Iterating over maps
  */
for ((k,v) <- mutableMap) {
  println(k,v)
}
mutableMap.values // iterable of values
mutableMap.keys // iterable of keys

// reverse key values to values keys
for ((k,v)  <- mutableMap) yield (v,k)


/**
  * ---------------------------------------------------
  * Sorted Maps
  *
  * Hash tables use the hash codes of the keys to scramble entries, so iterating over the elements yields them in
  * unpredictable order. By default, Scala gives you a map based on a hash table because it is usually
  * more efficient. If you need to visit the keys in sorted order, use a SortedMap instead.
  *
  */

val orderScores = scala.collection.mutable.SortedMap("Alice" -> 10,
  "Fred" -> 7, "Bob" -> 3, "Cindy" -> 8)
for ((k,v) <- orderScores) println(k,v)

// If you want to keep the insertion order use a LinkedHashMap.
val months = scala.collection.mutable.LinkedHashMap("January" -> 1,
  "February" -> 2, "March" -> 3, "April" -> 4, "May" -> 5)

/**
  * ------------------------------------------------
  * Inter operating with Java
  */
import scala.collection.JavaConversions.mapAsScalaMap
val scores20: scala.collection.mutable.Map[String, Int] =
  new java.util.TreeMap[String, Int]


/**
  * -----------------------------------------------
  * Tuples
  */

val tuple = (1,2.3, "JHon")

//access elements,starts by 1 no 0 position
tuple._1

// Access with patterns matching
val (first, second, third) = tuple
second

// This operation return a tuple of string that fill the condition and then when not
"New York".partition(_.isUpper)


/**
  * --------------------------------------------
  * Zipper
  */

val symbols = Array("<","-",">")
val repetitions = Array(2,10,2)
val pairs = symbols.zip(repetitions)
for ((s,r) <- pairs) println(s * r)


/**
  *  ----------------------------------------------------
  *  Exercises
  */

// 1. Set up a map of prices for a number of gizmos that you covet. Then produce a second map with
//the same keys and the prices at a 10 percent discount.

val gizmos = Map("Car" -> 20,  "House" -> 500, "Travel" -> 50)
val percent10 = for((k,v) <- gizmos) yield (k, v - (v * 0.10) )

//2. Write a program that reads words from a file. Use a mutable map to count how often each word
//appears. To read the words, simply use a java.util.Scanner:

val in = new java.util.Scanner(new java.io.File("/home/jhon/rappi_repo/scala-practice/src/main/scala/book/words.txt"))
val wordCount = scala.collection.mutable.Map[String, Int]().withDefaultValue(0)
var wordCountImmutable = Map[String, Int]()


while (in.hasNext()) {
  wordCount(in.next()) += 1
}
wordCount.foreach(println)


//3. Repeat the preceding exercise with an immutable map.
val in2 = new java.util.Scanner(new java.io.File("/home/jhon/rappi_repo/scala-practice/src/main/scala/book/words.txt"))

while (in2.hasNext()) {
  val key = in2.next()
  wordCountImmutable += (key -> (wordCountImmutable.getOrElse(key,0) +1) )
}

wordCountImmutable.foreach(println)

//7. Print a table of all Java properties reported by the getProperties method of the
//java.lang.System class, like this:
import collection.JavaConverters._
val propertiesMap = System.getProperties.asScala
val maxTuple:(String, String) = propertiesMap.maxBy(_._1.length)
val numBlankSpaces = maxTuple._1.length
for((k,v) <- propertiesMap) {
  println(k + (" " * (numBlankSpaces - k.length)) + " | " + v + "\n")
}

//8. Write a function minmax(values: Array[Int]) that returns a pair containing the
//smallest and the largest values in the array.

def minmax(numbers: Array[Int]):(Int, Int) = {
  numbers.min -> numbers.max
}
val numbers = Array(6,7,68,768,7,6,768,7,67,68,7)
minmax(numbers)

//9. Write a function lteqgt(values: Array[Int], v: Int) that returns a triple
//containing the counts of values less than v, equal to v, and greater than v.

def lteqgt(values: Array[Int], v: Int): (Int, Int, Int) = {
  (values.count(_ < v) , values.count(_ == v), values.count(_ > v))
}
lteqgt(numbers,67)

//10. What happens when you zip together two strings, such as "Hello".zip("World")? Come
//up with a plausible use case.
"Hello".zip("World")
