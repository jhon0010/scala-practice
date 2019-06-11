import java.util.TimeZone

import scala.collection.mutable.ArrayBuffer
import scala.util.Random


/**
  * ------------------------------*******
  * Use arrays for fixed length
  */
val nums = new Array[Int](10)

/**
  * Don’t use new when supplying initial values.
  */
val words = Array("Pipe", "Scala")

/**
  * Use () for access the elements inside the array
  */
words(0)



import java.util

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections

import scala.collection.mutable.ArrayBuffer


/**
  * Use array buffer when you need keep variable length array
  */
val integers = ArrayBuffer[Int]()

// Add elements to the new array buffer
integers += 1
integers += (2,5,4,7)
// Add another collection with ++=
integers ++= Array(1,2,5)

// Remove the last three elements of the array
integers.trimEnd(3)

// You can insert or remove elements in the specific position but is a bit inefficient
integers.insert(2, 6)
integers.remove(2)


/**
  * -----------------------------------------------------------
  * Traverse arrays in scala
  */

/**
  * With a for
  * The until method is similar to the to method, except that it excludes the last value.
  */
for (i <- 0 until integers.length) { System.out.println(integers(i)) }
// 2 by 2 elements
for (i <- 0 until integers.length by 2) { System.out.println(integers(i)) }

for ( i <- integers.indices.reverse) {System.out.println(integers(i))}

// if you dont need the index you can traverse all array like this
for (elem <- integers) println


/**
  * -----------------------------------------------------------------
  * Transforming arrays
  */

val a = Array(-1,-2,1,2,3,4,5,6)

val transformArray = for(e <- a) yield e * 2
// You can get if or "filters expressions"inside the for
val transformArrayWithOutOdds = for(e <- a if e % 2 == 0) yield e * 2

/**
  * You can write the same the same before functionality like this
  * Remember _ is all elements like assignment x => x
  */
val doubleValues = a.filter(_ % 2 == 0).map(_ * 2)

val nonNegatives = a.filter(_ >= 0)

val positionsToRemoveNegatives = for(i <- a.indices if a(i) < 0) yield i

/**
  * --------------------------------------------------------------------
  *  Some algorithms in arrays
  *
  */

val numbers = 1 to 20

numbers.sum
numbers.max
numbers.min
numbers.sortWith(_ > _)
numbers.mkString(",")
numbers.mkString("<","\n", ">")

/**
  * Multidimensional arrays
  */

val matrix = Array.ofDim[Double](3,4)
matrix(1)(3)


/**
  * ----------------------------------------------------------------
  * Exercises
  */


//1. Write a code snippet that sets a to an array of n random integers between 0 (inclusive) and n
//(exclusive).

val test:Array[Int] = new Array(10)
val randomNUms = for (i <- 0 until test.length -1) yield Random.nextInt()
randomNUms.length


// 2. Write a loop that swaps adjacent elements of an array of integers. For example, Array(1,
//2, 3, 4, 5) becomes Array(2, 1, 4, 3, 5).

val test2 = Array(1,2,3,4,5)
var newArray = new Array[Int](test2.length)
for (i <- test2.indices) {

  if(i % 2 != 0 ) {
    newArray(i) = test2(i -1)
  }  else {
    if(i == test2.length - 1) {
      newArray(i) = test2(i)
    } else {
      newArray(i) = test2(i + 1)
    }
  }
}

newArray.foreach(println)

// 3. Repeat the preceding assignment, but produce a new array with the swapped values. Use
//for/yield.

val test3 = Array(1,2,3,4,5)
var swapedArray = new Array[Int](test2.length)

for (i <- test3.indices)  yield   if(i % 2 != 0 ) {
  swapedArray(i) = test3(i -1)
}  else {
  if(i == test3.length - 1) {
    swapedArray(i) = test3(i)
  } else {
    swapedArray(i) = test3(i + 1)
  }
}

swapedArray.foreach(println)

// 4. Given an array of integers, produce a new array that contains all positive values of the original
//array, in their original order, followed by all values that are zero or negative, in their original
//order.

val numsTest = Array(0,1,-5,8,-7,0,3)

val negNumbers = ArrayBuffer[Int]()
val posNumbers = ArrayBuffer[Int]()

for (elem <- numsTest) {
  if (elem > 0) posNumbers += elem
  else negNumbers += elem
}

val result = posNumbers ++= negNumbers
result.foreach(println)

//5. How do you compute the average of an Array[Double]?

val doubles = Array(2.4,3.6,8.9,4.1,2.8)
val average = doubles.sum / doubles.length

//6. How do you rearrange the elements of an Array[Int] so that they appear in reverse sorted
//order? How do you do the same with an ArrayBuffer[Int]?

val arr = Array(1,2,3,4,5)
val arrBuff = ArrayBuffer[Int](1,2,3,4,5)
arr.sortWith(_>_)
arrBuff.sortWith(_ > _)


// 7. Write a code snippet that produces all values from an array with duplicates removed. (Hint:
//Look at Scaladoc.)

val duplicatedValues = Array(1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1)
duplicatedValues.distinct


//8. Suppose you are given an array buffer of integers and want to remove all but the first negative
//number. Here is a sequential solution that sets a flag when the first negative number is called,
//then removes all elements beyond.
val ab = ArrayBuffer (2,7,4,-8,12,-7,1,-9,1)
val negIndices = ArrayBuffer[Int]()

for (i <- ab.indices) {
  if (ab(i) < 0) { negIndices += i }
}

negIndices.remove(0)
for (i <- negIndices.reverse) {
  println(i)
  ab.remove(i)
}

ab.foreach(println)

//10. Make a collection of all time zones returned by
//java.util.TimeZone.getAvailableIDs that are in America. Strip off the
//"America/" prefix and sort the result.

TimeZone.getAvailableIDs.filter(_.contains("America/")).map(_.replace("America/",""))
